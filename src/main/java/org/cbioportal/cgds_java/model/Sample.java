package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class Sample extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("sample_type") protected String sample_type;
	@JsonField("patient_id") protected String patient_id;
	@JsonField("study_id") protected String study_id;
	
	public String getId() {
		return id;
	}
	public String getSampleType() {
		return sample_type;
	}
	public String getPatientId() {
		return patient_id;
	}
	public String getStudyId() {
		return study_id;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Sample:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("sample_type: " + this.getSampleType() + "\n");
		strBuilder.append("patient_id: " + this.getPatientId() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		return  strBuilder.toString();
	}
	
}