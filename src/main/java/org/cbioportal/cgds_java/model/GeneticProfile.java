package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class GeneticProfile extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("name") protected String name;
	@JsonField("description") protected String description;
	@JsonField("datatype") protected String datatype;
	@JsonField("study_id") protected String study_id;
	@JsonField("genetic_alteration_type") protected String genetic_alteration_type;
	@JsonField("show_profile_in_analysis_tab") protected String show_profile_in_analysis_tab;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getDatatype() {
		return datatype;
	}
	public String getStudyId() {
		return study_id;
	}
	public String getGeneticAlterationType() {
		return genetic_alteration_type;
	}
	public String getShowProfileInAnalysisTab() {
		return show_profile_in_analysis_tab;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Genetic Profile:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("name: " + this.getName() + "\n");
		strBuilder.append("description: " + this.getDescription() + "\n");
		strBuilder.append("datatype: " + this.getDatatype() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		strBuilder.append("genetic_alteration_type: " + this.getGeneticAlterationType() + "\n");
		strBuilder.append("show_profile_in_analysis_tab: " + this.getShowProfileInAnalysisTab() + "\n");
		return  strBuilder.toString();
	}
	
}

