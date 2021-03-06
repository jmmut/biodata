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

package org.opencb.biodata.models.core;

import java.util.List;

public class MiRNAGene {

	public String miRBaseAccession;
	public String miRBaseID;
	public String status;
	public String sequence;
	public List<String> alias;
	public List<MiRNAMature> matures;
	
	public MiRNAGene(String miRBaseAccession, String miRBaseID, String status,
			String sequence, List<String> alias, List<MiRNAMature> matures) {
		this.miRBaseAccession = miRBaseAccession;
		this.miRBaseID = miRBaseID;
		this.status = status;
		this.sequence = sequence;
		this.alias = alias;
		this.matures = matures;
	}

	public void addMiRNAMature(String miRBaseAccession, String miRBaseID, String sequence) {
		matures.add(new MiRNAMature(miRBaseAccession, miRBaseID, sequence));
	}
	
	public String getMiRBaseAccession() {
		return miRBaseAccession;
	}

	public void setMiRBaseAccession(String miRBaseAccession) {
		this.miRBaseAccession = miRBaseAccession;
	}

	
	public String getMiRBaseID() {
		return miRBaseID;
	}

	public void setMiRBaseID(String miRBaseID) {
		this.miRBaseID = miRBaseID;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	
	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	
	public List<MiRNAMature> getMatures() {
		return matures;
	}

	public void setMatures(List<MiRNAMature> matures) {
		this.matures = matures;
	}

	public class MiRNAMature {
		public String miRBaseAccession;
		public String miRBaseID;
		public String sequence;
		public Integer cdnaStart;
		public Integer cdnaEnd;

		public MiRNAMature(String miRBaseAccession, String miRBaseID, String matureSequence) {
			this.miRBaseAccession = miRBaseAccession;
			this.miRBaseID = miRBaseID;
			this.sequence = matureSequence;
			this.cdnaStart = MiRNAGene.this.sequence.indexOf(matureSequence)+1;
			this.cdnaEnd = cdnaStart+matureSequence.length()-1;
		}
		
		public String getMiRBaseAccession() {
			return miRBaseAccession;
		}
		public void setMiRBaseAccession(String miRBaseAccession) {
			this.miRBaseAccession = miRBaseAccession;
		}
		
		public String getMiRBaseID() {
			return miRBaseID;
		}
		public void setMiRBaseID(String miRBaseID) {
			this.miRBaseID = miRBaseID;
		}
		
		public String getSequence() {
			return sequence;
		}
		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
		
	}
}
