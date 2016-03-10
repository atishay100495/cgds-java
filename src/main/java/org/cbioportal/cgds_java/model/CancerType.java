package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class CancerType extends AbstractResource{

	@JsonField("id") protected String id;
	@JsonField("name") protected String name;
	@JsonField("color") protected String color;

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Cancer Type:\n");
		strBuilder.append("id: " + this.getId() + "\n");
		strBuilder.append("name: " + this.getName() + "\n");
		strBuilder.append("color: " + this.getColor() + "\n");
		return  strBuilder.toString();
	}
	
}