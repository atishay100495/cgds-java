package org.cbioportal.cgds_java.model;

import java.util.List;

import org.cbioportal.cgds_java.framework.json.JsonArrayList;
import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class SampleList extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("name") protected String name;
	@JsonField("description") protected String description;
	@JsonField("study_id") protected String study_id;
	@JsonField("sample_ids") protected JsonArrayList<String> sample_ids;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getStudyId() {
		return study_id;
	}
	public List<String> getSampleIds() {
		return sample_ids;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Sample List:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("name: " + this.getName() + "\n");
		strBuilder.append("description: " + this.getDescription() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		strBuilder.append("sample_ids: " + this.getSampleIds() + "\n");
		return  strBuilder.toString();
	}
	
}


