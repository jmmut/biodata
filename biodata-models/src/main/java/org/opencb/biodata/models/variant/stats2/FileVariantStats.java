package org.opencb.biodata.models.variant.stats2;

/**
 * Created by jmmut on 20/02/15.
 */
public class FileVariantStats {
    private String header;
    private int variantsCount;
    private int samplesCount;

    public FileVariantStats() {
    }

    public FileVariantStats(String header, int variantsCount, int samplesCount) {
        this.header = header;
        this.variantsCount = variantsCount;
        this.samplesCount = samplesCount;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getVariantsCount() {
        return variantsCount;
    }

    public void setVariantsCount(int variantsCount) {
        this.variantsCount = variantsCount;
    }

    public int getSamplesCount() {
        return samplesCount;
    }

    public void setSamplesCount(int samplesCount) {
        this.samplesCount = samplesCount;
    }
}
