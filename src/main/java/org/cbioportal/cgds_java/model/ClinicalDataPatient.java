package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class ClinicalDataPatient extends AbstractResource{

	@JsonField("attr_id") protected String attr_id;
	@JsonField("attr_val") protected String attr_val;
	@JsonField("study_id") protected String study_id;
	@JsonField("patient_id") protected String patient_id;
	
	public String getAttrId() {
		return attr_id;
	}
	public String getAttrVal() {
		return attr_val;
	}
	public String getStudyId() {
		return study_id;
	}
	public String getPatientId() {
		return patient_id;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Clinical Data Patient:\n");
		strBuilder.append("attr_id: " + this.getAttrId() + "\n");
		strBuilder.append("attr_val: " + this.getAttrVal() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		strBuilder.append("patient_id: " + this.getPatientId() + "\n");
		return  strBuilder.toString();
	}
}


