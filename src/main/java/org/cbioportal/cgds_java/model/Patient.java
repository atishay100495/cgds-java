package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class Patient extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("study_id") protected String study_id;
	
	public String getId() {
		return id;
	}
	public String getStudyId() {
		return study_id;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Patient:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		return  strBuilder.toString();
	}
}