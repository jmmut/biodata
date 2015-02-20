package org.opencb.biodata.models.variant.stats2;

/**
 * Created by jmmut on 20/02/15.
 */
public class FeaturedRegionVariantStats {
    private int variantsCount;
    private int transitionsCount;
    private int tranversalCount;
    private float accumulatedQuality;
    private float meanQuality;

    public FeaturedRegionVariantStats() {
    }

    public FeaturedRegionVariantStats(int variantsCount, int transitionsCount, int tranversalCount, float accumulatedQuality, float meanQuality) {
        this.variantsCount = variantsCount;
        this.transitionsCount = transitionsCount;
        this.tranversalCount = tranversalCount;
        this.accumulatedQuality = accumulatedQuality;
        this.meanQuality = meanQuality;
    }

    public int getVariantsCount() {
        return variantsCount;
    }

    public void setVariantsCount(int variantsCount) {
        this.variantsCount = variantsCount;
    }

    public int getTransitionsCount() {
        return transitionsCount;
    }

    public void setTransitionsCount(int transitionsCount) {
        this.transitionsCount = transitionsCount;
    }

    public int getTranversalCount() {
        return tranversalCount;
    }

    public void setTranversalCount(int tranversalCount) {
        this.tranversalCount = tranversalCount;
    }

    public float getAccumulatedQuality() {
        return accumulatedQuality;
    }

    public void setAccumulatedQuality(float accumulatedQuality) {
        this.accumulatedQuality = accumulatedQuality;
    }

    public float getMeanQuality() {
        return meanQuality;
    }

    public void setMeanQuality(float meanQuality) {
        this.meanQuality = meanQuality;
    }
}
