package org.opencb.biodata.models.variant.stats2;

import java.util.Map;

/**
 * Created by jmmut on 20/02/15.
 */
public class SampleVariantStats {
    private int variantsCount;
    private int snpCount;
    private int passCount;
    private float meanQuality;
    private Map<String, Integer> genotypeCounts;
    private Map<String, Integer> alleleCounts;
    private Map<String, Integer> attributes;

    public SampleVariantStats() {
    }

    public SampleVariantStats(int variantsCount, int snpCount, int passCount, float meanQuality, Map<String, Integer> genotypeCounts, Map<String, Integer> alleleCounts, Map<String, Integer> attributes) {
        this.variantsCount = variantsCount;
        this.snpCount = snpCount;
        this.passCount = passCount;
        this.meanQuality = meanQuality;
        this.genotypeCounts = genotypeCounts;
        this.alleleCounts = alleleCounts;
        this.attributes = attributes;
    }

    public int getVariantsCount() {
        return variantsCount;
    }

    public void setVariantsCount(int variantsCount) {
        this.variantsCount = variantsCount;
    }

    public int getSnpCount() {
        return snpCount;
    }

    public void setSnpCount(int snpCount) {
        this.snpCount = snpCount;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public float getMeanQuality() {
        return meanQuality;
    }

    public void setMeanQuality(float meanQuality) {
        this.meanQuality = meanQuality;
    }

    public Map<String, Integer> getGenotypeCounts() {
        return genotypeCounts;
    }

    public void setGenotypeCounts(Map<String, Integer> genotypeCounts) {
        this.genotypeCounts = genotypeCounts;
    }

    public Map<String, Integer> getAlleleCounts() {
        return alleleCounts;
    }

    public void setAlleleCounts(Map<String, Integer> alleleCounts) {
        this.alleleCounts = alleleCounts;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Integer> attributes) {
        this.attributes = attributes;
    }
}
