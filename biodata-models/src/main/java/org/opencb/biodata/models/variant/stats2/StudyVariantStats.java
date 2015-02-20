package org.opencb.biodata.models.variant.stats2;

import java.util.List;

/**
 * Created by jmmut on 20/02/15.
 */
public class StudyVariantStats {
    private FeaturedRegionVariantStats stats;
    private int samplesCount;
    private List<RegionVariantStats> chromosomeStats;
    private List<RegionVariantStats> customRegionStats;

    public StudyVariantStats() {
    }

    public StudyVariantStats(FeaturedRegionVariantStats stats, int samplesCount, List<RegionVariantStats> chromosomeStats, List<RegionVariantStats> customRegionStats) {
        this.stats = stats;
        this.samplesCount = samplesCount;
        this.chromosomeStats = chromosomeStats;
        this.customRegionStats = customRegionStats;
    }

    public FeaturedRegionVariantStats getStats() {
        return stats;
    }

    public void setStats(FeaturedRegionVariantStats stats) {
        this.stats = stats;
    }

    public int getSamplesCount() {
        return samplesCount;
    }

    public void setSamplesCount(int samplesCount) {
        this.samplesCount = samplesCount;
    }

    public List<RegionVariantStats> getChromosomeStats() {
        return chromosomeStats;
    }

    public void setChromosomeStats(List<RegionVariantStats> chromosomeStats) {
        this.chromosomeStats = chromosomeStats;
    }

    public List<RegionVariantStats> getCustomRegionStats() {
        return customRegionStats;
    }

    public void setCustomRegionStats(List<RegionVariantStats> customRegionStats) {
        this.customRegionStats = customRegionStats;
    }
}
