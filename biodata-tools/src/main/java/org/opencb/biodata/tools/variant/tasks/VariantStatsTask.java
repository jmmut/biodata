/*
 * Copyright 2015 OpenCB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opencb.biodata.tools.variant.tasks;

import java.io.IOException;
import java.util.List;
import org.opencb.biodata.formats.variant.io.VariantReader;
import org.opencb.biodata.models.variant.VariantSourceEntry;
import org.opencb.biodata.models.variant.Variant;
import org.opencb.biodata.models.variant.VariantSource;
import org.opencb.biodata.models.variant.stats.VariantSourceStats;
import org.opencb.biodata.models.variant.stats.VariantAggregatedStats;
import org.opencb.biodata.models.variant.stats.VariantStats;
import org.opencb.biodata.tools.variant.stats.VariantStatsCalculator;
import org.opencb.commons.run.Task;

/**
 * @author Alejandro Aleman Ramos &lt;aaleman@cipf.es&gt;
 * @author Cristina Yenyxe Gonzalez Garcia &lt;cyenyxe@ebi.ac.uk&gt;
 */
public class VariantStatsTask extends Task<Variant> {

    private VariantReader reader;
    private VariantSource source;
    private VariantSourceStats stats;

    public VariantStatsTask(VariantReader reader, VariantSource study) {
        super();
        this.reader = reader;
        this.source = study;
        stats = new VariantSourceStats(study.getFileId(), study.getStudyId());
    }

    public VariantStatsTask(VariantReader reader, VariantSource study, int priority) {
        super(priority);
        this.reader = reader;
        this.source = study;
        stats = new VariantSourceStats(study.getFileId(), study.getStudyId());
    }

    @Override
    public boolean apply(List<Variant> batch) throws IOException {
//        VariantStats.calculateStatsForVariantsList(batch, source.getPedigree());
        for (Variant variant : batch) {
            for (VariantSourceEntry file : variant.getSourceEntries().values()) {
                VariantStats variantStats = null;
                switch (source.getAggregation()) {
                    case NONE:
                        variantStats = new VariantStats(variant);
                        break;
                    case BASIC:
//                        variantStats = new VariantAggregatedStats(variant);
                        break;
                    case EVS:
                        // TODO Should create an object!
                        break;
                }
                file.setStats(VariantStatsCalculator.calculate(variant, file.getSamplesData(), file.getAttributes(), source.getPedigree()));
            }
        }
        
        stats.updateFileStats(batch);
        stats.updateSampleStats(batch, source.getPedigree());
        return true;
    }

    @Override
    public boolean post() {
        source.setStats(stats.getFileStats());
        return true;
    }
}
