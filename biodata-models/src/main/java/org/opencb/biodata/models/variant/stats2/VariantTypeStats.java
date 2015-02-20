package org.opencb.biodata.models.variant.stats2;

import java.util.Map;

/**
 * Created by jmmut on 20/02/15.
 */
public class VariantTypeStats {
    private FeaturedRegionVariantStats stats;
    private Map<String, FeaturedRegionVariantStats> consequenceTypeStats;

    public VariantTypeStats() {
    }

    public VariantTypeStats(Map<String, FeaturedRegionVariantStats> consequenceTypeStats, FeaturedRegionVariantStats stats) {
        this.consequenceTypeStats = consequenceTypeStats;
        this.stats = stats;
    }

    public FeaturedRegionVariantStats getStats() {
        return stats;
    }

    public void setStats(FeaturedRegionVariantStats stats) {
        this.stats = stats;
    }

    public Map<String, FeaturedRegionVariantStats> getConsequenceTypeStats() {
        return consequenceTypeStats;
    }

    public void setConsequenceTypeStats(Map<String, FeaturedRegionVariantStats> consequenceTypeStats) {
        this.consequenceTypeStats = consequenceTypeStats;
    }
}
