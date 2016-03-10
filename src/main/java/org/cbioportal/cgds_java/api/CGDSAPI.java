package org.cbioportal.cgds_java.api;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.Response.Status.Family;

import org.cbioportal.cgds_java.framework.resource.AbstractResource;
import org.cbioportal.cgds_java.framework.rest.helper.impl.CGDSRESTHelper;
import org.cbioportal.cgds_java.framework.rest.manager.impl.CGDSRESTManagerImpl;
import org.cbioportal.cgds_java.framework.rest.result.CGDSRESTResult;
import org.cbioportal.cgds_java.model.CancerType;
import org.cbioportal.cgds_java.model.ClinicalAttributePatient;
import org.cbioportal.cgds_java.model.ClinicalAttributeSample;
import org.cbioportal.cgds_java.model.ClinicalDataPatient;
import org.cbioportal.cgds_java.model.ClinicalDataSample;
import org.cbioportal.cgds_java.model.Gene;
import org.cbioportal.cgds_java.model.GeneticProfile;
import org.cbioportal.cgds_java.model.GeneticProfileData;
import org.cbioportal.cgds_java.model.Patient;
import org.cbioportal.cgds_java.model.Sample;
import org.cbioportal.cgds_java.model.SampleList;
import org.cbioportal.cgds_java.model.Study;

public class CGDSAPI {

	public static Logger logger = Logger.getLogger("CGDSAPI");
	public static CGDSRESTHelper<CGDSRESTManagerImpl> cgdsRESTHelper = new CGDSRESTHelper<CGDSRESTManagerImpl>(new CGDSRESTManagerImpl());
	
	
	public static <T extends AbstractResource> List<T> getListFromRESTResult(CGDSRESTResult<T> result) {
		List<T> list = null;
		if(result != null && result.getExitValue() == 0 && result.getClientResponse().getStatusInfo().getFamily().equals(Family.SUCCESSFUL) && result.getObjects() != null) {
			list = result.getObjects();
		}
		return list;
	}
	
	public static List<CancerType> getCancerTypes() {
		return getListFromRESTResult(cgdsRESTHelper.getCancerTypes());
	}
	
	public static List<CancerType> getCancerTypes(String cancer_type_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getCancerTypes(cancer_type_ids));
	}
	
	public static List<ClinicalDataSample> getSampleClinicalData(String study_id, String attribute_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleClinicalData(study_id, attribute_ids));
	}
	
	public static List<ClinicalDataSample> getSampleClinicalData(String study_id, String attribute_ids, String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleClinicalData(study_id, attribute_ids, sample_ids));
	}
	
	public static List<ClinicalDataPatient> getPatientClinicalData(String study_id, String attribute_ids) {
		return  getListFromRESTResult(cgdsRESTHelper.getPatientClinicalData(study_id, attribute_ids));
	}
	
	public static List<ClinicalDataPatient> getPatientClinicalData(String study_id, String attribute_ids, String patient_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getPatientClinicalData(study_id, attribute_ids, patient_ids));
	}
	
	public static List<ClinicalAttributeSample> getSampleClinicalAttributes() {
		return getListFromRESTResult(cgdsRESTHelper.getSampleClinicalAttributes());
	}
	
	public static List<ClinicalAttributeSample> getSampleClinicalAttributes(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleClinicalAttributes(study_id));
	}
	
	public static List<ClinicalAttributeSample> getSampleClinicalAttributes(String study_id, String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleClinicalAttributes(study_id, sample_ids));
	}
	
	public static List<ClinicalAttributePatient> getPatientClinicalAttributes() {
		return getListFromRESTResult(cgdsRESTHelper.getPatientClinicalAttributes());
	}
	
	public static List<ClinicalAttributePatient> getPatientClinicalAttributes(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getPatientClinicalAttributes(study_id));
	}
	
	public static List<ClinicalAttributePatient> getPatientClinicalAttributes(String study_id, String patient_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getPatientClinicalAttributes(study_id, patient_ids));
	}
	
	public static List<Gene> getGenes() {
		return getListFromRESTResult(cgdsRESTHelper.getGenes());
	}
	
	public static List<Gene> getGenes(String hugo_gene_symbols) {
		return getListFromRESTResult(cgdsRESTHelper.getGenes(hugo_gene_symbols));
	}
	
	public static List<GeneticProfile> getGeneticProfiles() {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfiles());
	}
	
	public static List<GeneticProfile> getGeneticProfiles(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfiles(study_id));
	}
	
	public static List<GeneticProfile> getGeneticProfilesByGeneticProfileIds(String genetic_profile_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfilesByGeneticProfileIds(genetic_profile_ids));
	}
	
	public static List<GeneticProfile> getGeneticProfiles(String study_id, String genetic_profile_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfiles(study_id, genetic_profile_ids));
	}
	
	public static List<SampleList> getSampleLists() {
		return getListFromRESTResult(cgdsRESTHelper.getSampleLists());
	}
	
	public static List<SampleList> getSampleLists(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleLists(study_id));
	}
	
	public static List<SampleList> getSampleListsBySampleIds(String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleListsBySampleIds(sample_ids));
	}
	
	public static List<SampleList> getSampleLists(String study_id, String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSampleLists(study_id, sample_ids));
	}
	
	public static List<Patient> getPatients(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getPatients(study_id));
	}
	
	public static List<Patient> getPatients(String study_id, String patient_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getPatients(study_id, patient_ids));
	}
	
	public static List<GeneticProfileData> getGeneticProfileData(String genetic_profile_ids, String genes) {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfileData(genetic_profile_ids, genes));
	}
	
	public static List<GeneticProfileData> getGeneticProfileData(String genetic_profile_ids, String genes, String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getGeneticProfileData(genetic_profile_ids, genes, sample_ids));
	}
	
	public static List<Sample> getSamples(String study_id) {
		return getListFromRESTResult(cgdsRESTHelper.getSamples(study_id));
	}
	
	public static List<Sample> getSamples(String study_id, String sample_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getSamples(study_id, sample_ids));
	}
	
	public static List<Study> getStudies() {
		return getListFromRESTResult(cgdsRESTHelper.getStudies());
	}
	
	public static List<Study> getStudies(String study_ids) {
		return getListFromRESTResult(cgdsRESTHelper.getStudies(study_ids));
	}
	
}
