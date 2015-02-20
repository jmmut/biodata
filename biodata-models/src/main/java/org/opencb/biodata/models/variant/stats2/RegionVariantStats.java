package org.opencb.biodata.models.variant.stats2;

import org.opencb.biodata.models.variant.Variant;

import java.util.Map;

/**
 * Created by jmmut on 20/02/15.
 */
public class RegionVariantStats {
    private String id;
    public enum Type {
        chr, gene, region, xref
    }
    private Type type;
    private int variantsCount;
    private Map<Variant.VariantType, VariantTypeStats> variantTypeStats;

    public RegionVariantStats() {
    }

    public RegionVariantStats(Type type, String id, int variantsCount, Map<Variant.VariantType, VariantTypeStats> variantTypeStats) {
        this.type = type;
        this.id = id;
        this.variantsCount = variantsCount;
        this.variantTypeStats = variantTypeStats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getVariantsCount() {
        return variantsCount;
    }

    public void setVariantsCount(int variantsCount) {
        this.variantsCount = variantsCount;
    }

    public Map<Variant.VariantType, VariantTypeStats> getVariantTypeStats() {
        return variantTypeStats;
    }

    public void setVariantTypeStats(Map<Variant.VariantType, VariantTypeStats> variantTypeStats) {
        this.variantTypeStats = variantTypeStats;
    }
}
