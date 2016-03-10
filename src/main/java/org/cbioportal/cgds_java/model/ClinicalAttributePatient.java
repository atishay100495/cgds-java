package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class ClinicalAttributePatient extends AbstractResource{

	@JsonField("attr_id") protected String attr_id;
	@JsonField("display_name") protected String display_name;
	@JsonField("description") protected String description;
	@JsonField("datatype") protected String datatype;
	@JsonField("is_patient_attribute") protected String is_patient_attribute;
	@JsonField("priority") protected String priority;
	
	public String getAttrId() {
		return attr_id;
	}
	public String getDisplayName() {
		return display_name;
	}
	public String getDescription() {
		return description;
	}
	public String getDatatype() {
		return datatype;
	}
	public String getIsPatientAttribute() {
		return is_patient_attribute;
	}
	public String getPriority() {
		return priority;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Clinical Attribute Patient:\n");
		strBuilder.append("attr_id: " + this.getAttrId() + "\n");
		strBuilder.append("display_name: " + this.getDisplayName() + "\n");
		strBuilder.append("description: " + this.getDescription() + "\n");
		strBuilder.append("datatype: " + this.getDatatype() + "\n");
		strBuilder.append("is_patient_attribute: " + this.getIsPatientAttribute() + "\n");
		strBuilder.append("priority: " + this.getPriority() + "\n");
		return  strBuilder.toString();
	}
	
}

