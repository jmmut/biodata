package org.opencb.biodata.tools.variant.annotation;

import org.junit.Test;
import org.opencb.biodata.models.variant.VariantSourceEntry;
import org.opencb.biodata.models.variant.Variant;

import java.util.ArrayList;
import java.util.List;

public class VariantGenesAnnotatorTest {

    @Test
    public void testAnnot() throws Exception {

        Variant v1 = new Variant("13", 1, 1, "C", "T");
        Variant v2 = new Variant("13", 32889611, 32889611, "C", "T");

        List<Variant> variants = new ArrayList<>();

        variants.add(v1);
        variants.add(v2);

        VariantAnnotator va = new VariantGenesAnnotator();
        VariantSourceEntry avf = new VariantSourceEntry("TEST", "TEST");

        for (Variant v : variants) {
            v.addSourceEntry(avf);
        }

        va.annot(variants);

    }
}
