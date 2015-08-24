package org.opencb.biodata.tools.variant.stats;

import org.opencb.biodata.models.feature.AllelesCode;
import org.opencb.biodata.models.feature.Genotype;
import org.opencb.biodata.models.pedigree.Condition;
import org.opencb.biodata.models.pedigree.Individual;
import org.opencb.biodata.models.pedigree.Pedigree;
import org.opencb.biodata.models.variant.Variant;
import org.opencb.biodata.models.variant.VariantSourceEntry;
import org.opencb.biodata.models.variant.stats.VariantStats;

import java.util.List;
import java.util.Map;

/**
 * Created by jmmut on 2015-08-24.
 *
 * @author Jose Miguel Mut Lopez &lt;jmmut@ebi.ac.uk&gt;
 */
public class VariantStatsCalculator {
    public static VariantStats calculate(Variant variant, Map<String, Map<String, String>> samplesData, 
                                         Map<String, String> attributes, Pedigree pedigree) {
        VariantStats variantStats = new VariantStats(variant);
        calculate(variant, samplesData, attributes, pedigree, variantStats);
        return variantStats;
    }
    
    public static VariantStats calculate(Variant variant, Map<String, Map<String, String>> samplesData, 
                                         Map<String, String> attributes, Pedigree pedigree, VariantStats stats) {
        int[] allelesCount = new int[2];
        int totalAllelesCount = 0, totalGenotypesCount = 0;

        float controlsDominant = 0, casesDominant = 0;
        float controlsRecessive = 0, casesRecessive = 0;

        stats.setNumSamples(samplesData.size());
        stats.setMissingAlleles(0);
        stats.setMissingGenotypes(0);
        if (pedigree != null) {
            stats.setMendelianErrors(0);
        }

        for (Map.Entry<String, Map<String, String>> sample : samplesData.entrySet()) {
            String sampleName = sample.getKey();
            Genotype g = new Genotype(sample.getValue().get("GT"), stats.getRefAllele(), stats.getAltAllele());
            stats.addGenotype(g);

            // Check missing alleles and genotypes
            switch (g.getCode()) {
                case ALLELES_OK:
                    // Both alleles set
                    allelesCount[g.getAllele(0)]++;
                    allelesCount[g.getAllele(1)]++;

                    totalAllelesCount += 2;
                    totalGenotypesCount++;

                    // Counting genotypes for Hardy-Weinberg (all phenotypes)
                    if (g.isAlleleRef(0) && g.isAlleleRef(1)) { // 0|0
                        stats.getHw().incN_AA();
                    } else if ((g.isAlleleRef(0) && g.getAllele(1) == 1) || (g.getAllele(0) == 1 && g.isAlleleRef(1))) {  // 0|1, 1|0
                        stats.getHw().incN_Aa();

                    } else if (g.getAllele(0) == 1 && g.getAllele(1) == 1) {
                        stats.getHw().incN_aa();
                    }

                    break;
                case HAPLOID:
                    // Haploid (chromosome X/Y)
                    allelesCount[g.getAllele(0)]++;
                    totalAllelesCount++;
                    break;
                case MULTIPLE_ALTERNATES:
                    // Alternate with different "index" than the one that is being handled
                    break;
                default:
                    // Missing genotype (one or both alleles missing)
                    stats.setMissingGenotypes(stats.getMissingGenotypes() + 1);
                    if (g.getAllele(0) < 0) {
                        stats.setMissingAlleles(stats.getMissingAlleles() + 1);
                    } else {
                        allelesCount[g.getAllele(0)]++;
                        totalAllelesCount++;
                    }

                    if (g.getAllele(1) < 0) {
                        stats.setMissingAlleles(stats.getMissingAlleles() + 1);
                    } else {
                        allelesCount[g.getAllele(1)]++;
                        totalAllelesCount++;
                    }
                    break;

            }

            // Include statistics that depend on pedigree information
            if (pedigree != null) {
                if (g.getCode() == AllelesCode.ALLELES_OK || g.getCode() == AllelesCode.HAPLOID) {
                    Individual ind = pedigree.getIndividual(sampleName);
//                    if (MendelChecker.isMendelianError(ind, g, variant.getChromosome(), file.getSamplesData())) {
//                        stats.setMendelianErrors(stats.getMendelianErrors() + 1);
//                    }
                    if (g.getCode() == AllelesCode.ALLELES_OK) {
                        // Check inheritance models
                        if (ind.getCondition() == Condition.UNAFFECTED) {
                            if (g.isAlleleRef(0) && g.isAlleleRef(1)) { // 0|0
                                controlsDominant++;
                                controlsRecessive++;

                            } else if ((g.isAlleleRef(0) && !g.isAlleleRef(1)) || (!g.isAlleleRef(0) || g.isAlleleRef(1))) { // 0|1 or 1|0
                                controlsRecessive++;

                            }
                        } else if (ind.getCondition() == Condition.AFFECTED) {
                            if (!g.isAlleleRef(0) && !g.isAlleleRef(1) && g.getAllele(0) == g.getAllele(1)) {// 1|1, 2|2, and so on
                                casesRecessive++;
                                casesDominant++;
                            } else if (!g.isAlleleRef(0) || !g.isAlleleRef(1)) { // 0|1, 1|0, 1|2, 2|1, 1|3, and so on
                                casesDominant++;

                            }
                        }

                    }
                }
            }

        }  // Finish all samples loop

        // Set counts for each allele
        stats.setRefAlleleCount(allelesCount[0]);
        stats.setAltAlleleCount(allelesCount[1]);

        // Calculate MAF and MGF
        calculateAlleleFrequencies(stats, totalAllelesCount);
        calculateGenotypeFrequencies(stats, totalGenotypesCount);

        // Calculate Hardy-Weinberg statistic
        stats.getHw().calculate();

        // Update variables finally used to update file_stats_t structure
        if ("PASS".equalsIgnoreCase(attributes.get("FILTER"))) {
            stats.setPassedFilters(true);
        }

        if (attributes.containsKey("QUAL") && !(".").equals(attributes.get("QUAL"))) {
            float qualAux = Float.valueOf(attributes.get("QUAL"));
            if (qualAux >= 0) {
                stats.setQuality(qualAux);
            }
        }

        if (pedigree != null) {
            // Once all samples have been traversed, calculate % that follow inheritance model
            controlsDominant = controlsDominant * 100 / (stats.getNumSamples() - stats.getMissingGenotypes());
            casesDominant = casesDominant * 100 / (stats.getNumSamples() - stats.getMissingGenotypes());
            controlsRecessive = controlsRecessive * 100 / (stats.getNumSamples() - stats.getMissingGenotypes());
            casesRecessive = casesRecessive * 100 / (stats.getNumSamples() - stats.getMissingGenotypes());

            stats.setCasesPercentDominant(casesDominant);
            stats.setControlsPercentDominant(controlsDominant);
            stats.setCasesPercentRecessive(casesRecessive);
            stats.setControlsPercentRecessive(controlsRecessive);
        }

        return stats;
    }


    /**
     * Calculates the statistics for some variants read from a set of files, and 
     * optionally given pedigree information. Some statistics like inheritance 
     * patterns can only be calculated if pedigree information is provided.
     *
     * @param variants The variants whose statistics will be calculated
     * @param ped Optional pedigree information to calculate some statistics
     */
    public static void calculateStatsForVariantsList(List<Variant> variants, Pedigree ped) {
        for (Variant variant : variants) {
            for (VariantSourceEntry file : variant.getSourceEntries().values()) {
                VariantStats stats = VariantStatsCalculator.calculate(variant, file.getSamplesData(), file.getAttributes(), ped);
                file.setStats(stats); // TODO Correct?
            }
        }
    }
    
    public static void calculateAlleleFrequencies(VariantStats variantStats, int totalAllelesCount) {
        if (totalAllelesCount < 0) {
            throw new IllegalArgumentException("The number of alleles must be equals or greater than zero");
        }

        if (totalAllelesCount == 0) {
            // Nothing to calculate here
            variantStats.setMaf(-1);
            variantStats.setMafAllele(null);
            return;
        }

        variantStats.setRefAlleleFreq(variantStats.getRefAlleleCount() / (float) totalAllelesCount);
        variantStats.setAltAlleleFreq(variantStats.getAltAlleleCount() / (float) totalAllelesCount);

        if (variantStats.getRefAlleleFreq() <= variantStats.getAltAlleleFreq()) {
            variantStats.setMaf(variantStats.getRefAlleleFreq());
            variantStats.setMafAllele(variantStats.getRefAllele());
        } else {
            variantStats.setMaf(variantStats.getAltAlleleFreq());
            variantStats.setMafAllele(variantStats.getAltAllele());
        }
    }

    public static void calculateGenotypeFrequencies(VariantStats variantStats, int totalGenotypesCount) {
        if (totalGenotypesCount < 0) {
            throw new IllegalArgumentException("The number of genotypes must be equals or greater than zero");
        }

        if (variantStats.getGenotypesCount().isEmpty() || totalGenotypesCount == 0) {
            // Nothing to calculate here
            variantStats.setMgf(-1);
            variantStats.setMgfGenotype(null);
            return;
        }

        // Set all combinations of genotypes to zero
        Map<Genotype, Float> genotypesFreq = variantStats.getGenotypesFreq();
        genotypesFreq.put(new Genotype("0/0", variantStats.getRefAllele(), variantStats.getAltAllele()), 0.0f);
        genotypesFreq.put(new Genotype("0/1", variantStats.getRefAllele(), variantStats.getAltAllele()), 0.0f);
        genotypesFreq.put(new Genotype("1/1", variantStats.getRefAllele(), variantStats.getAltAllele()), 0.0f);

        // Insert the genotypes found in the file
        for (Map.Entry<Genotype, Integer> gtCount : variantStats.getGenotypesCount().entrySet()) {
            if (gtCount.getKey().getCode() == AllelesCode.ALLELES_MISSING) {
                // Missing genotypes shouldn't have frequencies calculated
                continue;
            }

            float freq = (totalGenotypesCount > 0) ? gtCount.getValue() / (float) totalGenotypesCount : 0;
            genotypesFreq.put(gtCount.getKey(), freq);
        }

        // Traverse the genotypes to see which one has the MGF
        float currMgf = Float.MAX_VALUE;
        Genotype currMgfGenotype = null;

        for (Map.Entry<Genotype, Float> gtCount : genotypesFreq.entrySet()) {
            float freq = gtCount.getValue();
            if (freq < currMgf) {
                currMgf = freq;
                currMgfGenotype = gtCount.getKey();
            }
        }

        if (currMgfGenotype != null) {
            variantStats.setMgf(currMgf);
            variantStats.setMgfGenotype(currMgfGenotype.toString());
        }
    }
}
