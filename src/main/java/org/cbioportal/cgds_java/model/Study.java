package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class Study extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("type_of_cancer") protected String type_of_cancer;
	@JsonField("name") protected String name;
	@JsonField("short_name") protected String short_name;
	@JsonField("description") protected String description;
	@JsonField("pmid") protected String pmid;
	@JsonField("citation") protected String citation;
	@JsonField("groups") protected String groups;
	
	public String getId() {
		return id;
	}
	public String getTypeOfCancer() {
		return type_of_cancer;
	}
	public String getName() {
		return name;
	}
	public String getShortName() {
		return short_name;
	}
	public String getDescription() {
		return description;
	}
	public String getPmid() {
		return pmid;
	}
	public String getCitation() {
		return citation;
	}
	public String getGroups() {
		return groups;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Study:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("type_of_cancer: " + this.getTypeOfCancer() + "\n");
		strBuilder.append("name: " + this.getName() + "\n");
		strBuilder.append("short_name: " + this.getShortName() + "\n");
		strBuilder.append("description: " + this.getDescription() + "\n");
		strBuilder.append("pmid: " + this.getPmid() + "\n");
		strBuilder.append("citation: " + this.getCitation() + "\n");
		strBuilder.append("groups: " + this.getGroups() + "\n");
		return  strBuilder.toString();
	}
}