package org.opencb.biodata.models.variant.clinical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by antonior on 5/22/14.
 * @author Luis Miguel Cruz.
 * @since October 08, 2014 
 */
public class Cosmic implements Comparable {

    /** Alternate Allele */
    private String alternate;

    /** Alternate Reference */
    private String reference;

    /** Chromosome */
    private String chromosome;

    /** Variant position start */
    private int start;
    
    /** Variant position end */
    private int end;

    /** Gene_name */
    private String Gene_name;

    /** Mutation GRCh37 strand */
    private String Mutation_GRCh37_strand;

    /** Primary site */
    private String Primary_site;

    /** Mutation zygosity */
    private String Mutation_zygosity;

    /** Mutation AA */
    private String Mutation_AA;

    /** Tumour origin */
    private String Tumour_origin;

    /** Histology subtype */
    private String Histology_subtype;

    /** Accession Number */
    private String Accession_Number;

    /** Mutation ID */
    private String Mutation_ID;

    /** Mutation CDS */
    private String Mutation_CDS;

    /** Sample name */
    private String Sample_name;

    /** Primary histology */
    private String Primary_histology;

    /** Mutation GRCh37 genome position */
    private String Mutation_GRCh37_genome_position;

    /** Mutation Description */
    private String Mutation_Description;

    /** Genome-wide screen */
    private String Genome_wide_screen;

    /** ID_tumour */
    private String ID_tumour;

    /** ID_sample */
    private String ID_sample;

    /** Mutation somatic status */
    private String Mutation_somatic_status;

    /** Site subtype */
    private String Site_subtype;

    /** Gene CDS length */
    private int gene_CDS_length;

    /** HGNC ID */
    private String HGNC_id;

    /** Pubmed PMID */
    private String Pubmed_PMID;

    /** Age (may be null) */
    private Float age;

    /** Comments */
    private String comments;

    private boolean snp;

    private String fathmmPrediction;

    private Integer idStudy;

    public Cosmic(String[] fields) {
        // COSMIC file is a tab-delimited file with the following fields (columns)
        // 0 Gene name
        // 1 Accession Number
        // 2 Gene CDS length
        // 3 HGNC ID
        // 4 Sample name
        // 5 ID sample
        // 6 ID tumour
        // 7 Primary site
        // 8 Site subtype
        // 9 Primary histology
        // 10 Histology subtype
        // 11 Genome-wide screen
        // 12 Mutation ID
        // 13 Mutation CDS
        // 14 Mutation AA
        // 15 Mutation Description
        // 16 Mutation zygosity
        // 17 Mutation GRCh37 genome position
        // 18 Mutation GRCh37 strand
        // 19 Snp
        // 20 FATHMM Prediction
        // 21 Mutation somatic status
        // 22 PubMed PMID
        // 23 ID STUDY
        // 24 Tumour origin
        // 25 Age
        // 26 Comments

    	this.Gene_name = fields[0];
        this.Accession_Number = fields[1];
        this.gene_CDS_length = Integer.parseInt(fields[2]);
        this.HGNC_id = fields[3];
        this.Sample_name = fields[4];
        this.ID_sample = fields[5];
        this.ID_tumour = fields[6];
        this.Primary_site = fields[7];
        this.Site_subtype = fields[8];
        this.Primary_histology = fields[9];
        this.Histology_subtype = fields[10];
        this.Genome_wide_screen = fields[11];
        this.Mutation_ID = fields[12];
        this.Mutation_CDS = fields[13];
        this.Mutation_AA = fields[14];
        this.Mutation_Description = fields[15];
        this.Mutation_zygosity = fields[16];
        this.Mutation_GRCh37_genome_position = fields[17];
        this.Mutation_GRCh37_strand = fields[18];
        if(!fields[19].isEmpty() && fields[19].equalsIgnoreCase("y")){
            this.snp = true;
        }
        this.fathmmPrediction = fields[20];
        this.Mutation_somatic_status = fields[21];
        this.Pubmed_PMID = fields[22];
        try {
            this.idStudy = Integer.parseInt(fields[23]);
        } catch (NumberFormatException e) {

        }
        this.Tumour_origin = fields[24];
        if(fields.length >= 26 && fields[25] != null && !fields[25].isEmpty()){
            try {
                this.age = Float.parseFloat(fields[25]);
            } catch (NumberFormatException e) {

            }
        }
        if(fields.length >= 27){
            this.comments = fields[26];
        }

        // Calculate start and end
        this.calculateStartAndEnd();

        // Calculate reference and alternate
        this.calculateAltAndRef();

        // Calculate genome position
        this.recalculateGenomePosition();
    }


    // ----------------------- GETTERS / SETTERS --------------------------------
    
    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getGene_name() {
        return Gene_name;
    }

    public void setGene_name(String gene_name) {
        Gene_name = gene_name;
    }

    public String getMutation_GRCh37_strand() {
        return Mutation_GRCh37_strand;
    }

    public void setMutation_GRCh37_strand(String mutation_GRCh37_strand) {
        Mutation_GRCh37_strand = mutation_GRCh37_strand;
    }

    public String getPrimary_site() {
        return Primary_site;
    }

    public void setPrimary_site(String primary_site) {
        Primary_site = primary_site;
    }

    public String getMutation_zygosity() {
        return Mutation_zygosity;
    }

    public void setMutation_zygosity(String mutation_zygosity) {
        Mutation_zygosity = mutation_zygosity;
    }

    public String getMutation_AA() {
        return Mutation_AA;
    }

    public void setMutation_AA(String mutation_AA) {
        Mutation_AA = mutation_AA;
    }

    public String getTumour_origin() {
        return Tumour_origin;
    }

    public void setTumour_origin(String tumour_origin) {
        Tumour_origin = tumour_origin;
    }

    public String getHistology_subtype() {
        return Histology_subtype;
    }

    public void setHistology_subtype(String histology_subtype) {
        Histology_subtype = histology_subtype;
    }

    public String getAccession_Number() {
        return Accession_Number;
    }

    public void setAccession_Number(String accession_Number) {
        Accession_Number = accession_Number;
    }

    public String getMutation_ID() {
        return Mutation_ID;
    }

    public void setMutation_ID(String mutation_ID) {
        Mutation_ID = mutation_ID;
    }

    public String getMutation_CDS() {
        return Mutation_CDS;
    }

    public void setMutation_CDS(String mutation_CDS) {
        Mutation_CDS = mutation_CDS;
    }

    public String getSample_name() {
        return Sample_name;
    }

    public void setSample_name(String sample_name) {
        Sample_name = sample_name;
    }

    public String getPrimary_histology() {
        return Primary_histology;
    }

    public void setPrimary_histology(String primary_histology) {
        Primary_histology = primary_histology;
    }

    public String getMutation_GRCh37_genome_position() {
        return Mutation_GRCh37_genome_position;
    }

    public void setMutation_GRCh37_genome_position(String mutation_GRCh37_genome_position) {
        Mutation_GRCh37_genome_position = mutation_GRCh37_genome_position;
    }

    public String getMutation_Description() {
        return Mutation_Description;
    }

    public void setMutation_Description(String mutation_Description) {
        Mutation_Description = mutation_Description;
    }

    public String getGenome_wide_screen() {
        return Genome_wide_screen;
    }

    public void setGenome_wide_screen(String genome_wide_screen) {
        Genome_wide_screen = genome_wide_screen;
    }

    public String getID_tumour() {
        return ID_tumour;
    }

    public void setID_tumour(String ID_tumour) {
        this.ID_tumour = ID_tumour;
    }

    public String getID_sample() {
        return ID_sample;
    }

    public void setID_sample(String ID_sample) {
        this.ID_sample = ID_sample;
    }

    public String getMutation_somatic_status() {
        return Mutation_somatic_status;
    }

    public void setMutation_somatic_status(String mutation_somatic_status) {
        Mutation_somatic_status = mutation_somatic_status;
    }

    public String getSite_subtype() {
        return Site_subtype;
    }

    public void setSite_subtype(String site_subtype) {
        Site_subtype = site_subtype;
    }

    public int getGene_CDS_length() {
        return gene_CDS_length;
    }

    public void setGene_CDS_length(int gene_CDS_length) {
        this.gene_CDS_length = gene_CDS_length;
    }

    public boolean isSnp() {
        return snp;
    }

    public void setSnp(boolean snp) {
        this.snp = snp;
    }

    public String getFathmmPrediction() {
        return fathmmPrediction;
    }

    public void setFathmmPrediction(String fathmmPrediction) {
        this.fathmmPrediction = fathmmPrediction;
    }

    public Integer getIdStudy() {
        return idStudy;
    }

    public void setIdStudy(Integer idStudy) {
        this.idStudy = idStudy;
    }

    public String getHGNC_id() {
        return HGNC_id;
    }

    public void setHGNC_id(String HGNC_id) {
        this.HGNC_id = HGNC_id;
    }

    public String getPubmed_PMID() {
        return Pubmed_PMID;
    }

    public void setPubmed_PMID(String pubmed_PMID) {
        Pubmed_PMID = pubmed_PMID;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    // ----------------------- ADITIONAL FUNCTIONS --------------------------------
    
    public int compareTo(Object o) {
        Cosmic otherCosmic = (Cosmic)o;
        int chr1 = Integer.parseInt(this.getChromosome());
        int chr2 = Integer.parseInt(otherCosmic.getChromosome());
        if (chr1 != chr2) {
            return chr1 - chr2;
        } else {
            return this.getStart() - otherCosmic.getStart();
        }
    }

    private void calculateStartAndEnd() throws NumberFormatException {
        if(this.Mutation_GRCh37_genome_position != null && !this.Mutation_GRCh37_genome_position.isEmpty()){
            String[] position = this.Mutation_GRCh37_genome_position.split(":")[1].split("-");
            try {
                this.start = Integer.parseInt(position[0]);
                this.end = Integer.parseInt(position[1]);
            } catch (NumberFormatException e) {
            }
        }
    }
    
    private void calculateAltAndRef(){
    	if (this.Mutation_CDS.contains(">")) {
    		// Change (one or more nucleotides). Get number of nucleotides of alternative
    		this.alternate = this.Mutation_CDS.split(">")[1];
            String refAux = this.Mutation_CDS.split(">")[0];
            
            // Although more than one nucleotide is allowed, in this version just one nucleotide change is allowed
            Matcher matcher = Pattern.compile("((A|C|G|T)+)").matcher(refAux);

            if(matcher.find()) {
            	// Either change or deletion
            	this.reference = matcher.group(); // Get the first group (entire pattern -> group() is equivalente to group(0)
            }
        } else if (this.Mutation_CDS.contains("del")) {
            // Deletion
            this.reference = this.Mutation_CDS.split("del")[1];
            this.alternate = "-";
        } else if (this.Mutation_CDS.contains("dup")) {
        	/*
        	 * TODO: The only Duplication in Cosmic V68 is a structural variation.
        	 * we are not going to modify a variation of more than one nucleotide 
        	 */ 
        } else if (this.Mutation_CDS.contains("ins")) {
            // Insertion
            this.reference = "-";
            this.alternate = this.Mutation_CDS.split("ins")[1];
        }

        // Check strand
        if (this.Mutation_CDS.equals("-")) {
       	 // Negative strand
            if (!this.alternate.equals("-")){
                this.alternate = getCDNA(this.alternate);
            } if (!this.reference.equals("-")){
                this.reference = getCDNA(this.reference);
            }
        }
    }

    private void recalculateGenomePosition(){
        String selectedChromosome = this.Mutation_GRCh37_genome_position.split(":")[0];
        if(!selectedChromosome.isEmpty()){
            if (selectedChromosome.equals("23")){
                this.chromosome = "X";
            } else if (selectedChromosome.equals("24")) {
                this.chromosome = "Y";
            } else if (selectedChromosome.equals("25")) {
                this.chromosome = "MT";
            } else {
                this.chromosome = selectedChromosome;
            }

            // GRCh37 position
            this.setMutation_GRCh37_genome_position(
                    this.getChromosome() + ":" +
                            this.getMutation_GRCh37_genome_position().split(":")[1]);
        }

        /*if (!this.getMutation_NCBI36_genome_position().isEmpty()) {
            this.setMutation_NCBI36_genome_position(
                    this.getChromosome() + ":" +
                            this.getMutation_NCBI36_genome_position().split(":")[1]);
        }*/
    }
    
    /**
     * Function that converts a nucleotide string into its complementary in reverse order
     * @param nucleotides string of nucleotides
     * @return complementary string of nucleotides
     */
    private String getCDNA(String nucleotides) {
        StringBuffer cDNA = new StringBuffer("");

        // For each nucleotide, get its complement base
        for(int i=nucleotides.length()-1; i>=0;i--) {
            switch(nucleotides.charAt(i)){
                case 'A': cDNA.append("T");
                    break;
                case 'C': cDNA.append("G");
                    break;
                case 'G': cDNA.append("C");
                    break;
                case 'T': cDNA.append("A");
                    break;
            }
        }

        return cDNA.toString();
    }
}