package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class Gene extends AbstractResource{

	@JsonField("hugo_gene_symbol") protected String hugo_gene_symbol;
	@JsonField("entrez_gene_id") protected String entrez_gene_id;
	
	public String getHugoGeneSymbol() {
		return hugo_gene_symbol;
	}
	public String getEntrezGeneId() {
		return entrez_gene_id;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Gene:\n");
		strBuilder.append("hugo_gene_symbol: " + this.getHugoGeneSymbol() + "\n");
		strBuilder.append("entrez_gene_id: " + this.getEntrezGeneId() + "\n");
		return  strBuilder.toString();
	}
}

