package org.opencb.biodata.models.variant.effect;

/**
 *
 * @author Cristina Yenyxe Gonzalez Garcia <cyenyxe@ebi.ac.uk>
 * 
 * MOTIF_SCORE_CHANGE - The difference in motif score of the reference and variant sequences for the TFBP
 */
public class RegulatoryEffect {
    
    /**
     * Source and identifier of a transcription factor binding profile aligned at this position
     */
    private String motifName;
    
    /**
     * Relative position of the variation in the aligned TFBP
     */
    private int motifPosition;
    
    /**
     * If the variant falls in a high information position of a transcription factor binding profile (TFBP)
     */
    private boolean highInformationPosition;
    
    /**
     * List of cell types and classifications for regulatory feature
     */
    private String cellType;

    public RegulatoryEffect() {
    }

    public RegulatoryEffect(String motifName, int motifPosition, boolean highInformationPosition, String cellType) {
        this.motifName = motifName;
        this.motifPosition = motifPosition;
        this.highInformationPosition = highInformationPosition;
        this.cellType = cellType;
    }

    public String getMotifName() {
        return motifName;
    }

    public void setMotifName(String motifName) {
        this.motifName = motifName;
    }

    public int getMotifPosition() {
        return motifPosition;
    }

    public void setMotifPosition(int motifPosition) {
        this.motifPosition = motifPosition;
    }

    public boolean isHighInformationPosition() {
        return highInformationPosition;
    }

    public void setHighInformationPosition(boolean highInformationPosition) {
        this.highInformationPosition = highInformationPosition;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }
    
    
}