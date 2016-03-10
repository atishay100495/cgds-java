package org.cbioportal.cgds_java.model;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.resource.AbstractResource;

@JsonObjectClass
public class GeneticProfileData extends AbstractResource{

	@JsonField("genetic_profile_id") protected String genetic_profile_id;
	@JsonField("entrez_gene_id") protected String entrez_gene_id;
	@JsonField("hugo_gene_symbol") protected String hugo_gene_symbol;
	@JsonField("sample_id") protected String sample_id;
	@JsonField("study_id") protected String study_id;
	
	//Additional Field for Non-mutation profiles
	@JsonField("profile_data") protected String profile_data;
	
	@JsonField("sequencing_center") protected String sequencing_center;
	@JsonField("mutation_status") protected String mutation_status;
	@JsonField("mutation_type") protected String mutation_type;
	@JsonField("validation_status") protected String validation_status;
	@JsonField("amino_acid_change") protected String amino_acid_change;
	@JsonField("functional_impact_score") protected String functional_impact_score;
	@JsonField("xvar_link") protected String xvar_link;
	@JsonField("xvar_link_pdb") protected String xvar_link_pdb;
	@JsonField("xvar_link_msa") protected String xvar_link_msa;
	@JsonField("chr") protected String chr;
	@JsonField("start_position") protected String start_position;
	@JsonField("end_position") protected String end_position;
	@JsonField("protein_start_position") protected String protein_start_position;
	@JsonField("protein_end_position") protected String protein_end_position;
	@JsonField("reference_allele") protected String reference_allele;
	@JsonField("variant_allele") protected String variant_allele;
	@JsonField("reference_read_count_tumor") protected String reference_read_count_tumor;
	@JsonField("variant_read_count_tumor") protected String variant_read_count_tumor;
	@JsonField("reference_read_count_normal") protected String reference_read_count_normal;
	@JsonField("variant_read_count_normal") protected String variant_read_count_normal;
	
	public String getGeneticProfileId() {
		return genetic_profile_id;
	}
	public String getEntrezGeneId() {
		return entrez_gene_id;
	}
	public String getHugoGeneSymbol() {
		return hugo_gene_symbol;
	}
	public String getSampleId() {
		return sample_id;
	}
	public String getStudyId() {
		return study_id;
	}
	public String getProfileData() {
		return profile_data;
	}
	public String getSequencingCenter() {
		return sequencing_center;
	}
	public String getMutationStatus() {
		return mutation_status;
	}
	public String getMutationType() {
		return mutation_type;
	}
	public String getValidationStatus() {
		return validation_status;
	}
	public String getAminoAcidChange() {
		return amino_acid_change;
	}
	public String getFunctionalImpactScore() {
		return functional_impact_score;
	}
	public String getXvarLink() {
		return xvar_link;
	}
	public String getXvarLinkPdb() {
		return xvar_link_pdb;
	}
	public String getXvarLinkMsa() {
		return xvar_link_msa;
	}
	public String getChr() {
		return chr;
	}
	public String getStartPosition() {
		return start_position;
	}
	public String getEnd_Position() {
		return end_position;
	}
	public String getProteinStartPosition() {
		return protein_start_position;
	}
	public String getProteinEndPosition() {
		return protein_end_position;
	}
	public String getReferenceAllele() {
		return reference_allele;
	}
	public String getVariantAllele() {
		return variant_allele;
	}
	public String getReferenceReadCountTumor() {
		return reference_read_count_tumor;
	}
	public String getVariantReadCountTumor() {
		return variant_read_count_tumor;
	}
	public String getReferenceReadCountNormal() {
		return reference_read_count_normal;
	}
	public String getVariantReadCountNormal() {
		return variant_read_count_normal;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("Genetic Profile Data:\n");
		strBuilder.append("genetic_profile_id: " + this.getGeneticProfileId() + "\n");
		strBuilder.append("entrez_gene_id: " + this.getEntrezGeneId() + "\n");
		strBuilder.append("hugo_gene_symbol: " + this.getHugoGeneSymbol() + "\n");
		strBuilder.append("sample_id: " + this.getSampleId() + "\n");
		strBuilder.append("study_id: " + this.getStudyId() + "\n");
		strBuilder.append("profile_data: " + this.getProfileData() + "\n");
		strBuilder.append("sequencing_center: " + this.getSequencingCenter() + "\n");
		strBuilder.append("mutation_status: " + this.getMutationStatus() + "\n");
		strBuilder.append("mutation_type: " + this.getMutationType() + "\n");
		strBuilder.append("validation_status: " + this.getValidationStatus() + "\n");
		strBuilder.append("amino_acid_change: " + this.getAminoAcidChange() + "\n");
		strBuilder.append("functional_impact_score: " + this.getFunctionalImpactScore() + "\n");
		strBuilder.append("xvar_link: " + this.getXvarLink() + "\n");
		strBuilder.append("xvar_link_pdb: " + this.getXvarLinkPdb() + "\n");
		strBuilder.append("xvar_link_msa: " + this.getXvarLinkMsa() + "\n");
		strBuilder.append("chr: " + this.getChr() + "\n");
		strBuilder.append("start_position: " + this.getStartPosition() + "\n");
		strBuilder.append("end_position: " + this.getEnd_Position() + "\n");
		strBuilder.append("protein_start_position: " + this.getProteinStartPosition() + "\n");
		strBuilder.append("protein_end_position: " + this.getProteinEndPosition() + "\n");
		strBuilder.append("reference_allele: " + this.getReferenceAllele() + "\n");
		strBuilder.append("variant_allele: " + this.getVariantAllele() + "\n");
		strBuilder.append("reference_read_count_tumor: " + this.getReferenceReadCountTumor() + "\n");
		strBuilder.append("variant_read_count_tumor: " + this.getVariantReadCountTumor() + "\n");
		strBuilder.append("reference_read_count_normal: " + this.getReferenceReadCountNormal() + "\n");
		strBuilder.append("variant_read_count_normal: " + this.getVariantReadCountNormal() + "\n");
		return  strBuilder.toString();
	}
	
}

