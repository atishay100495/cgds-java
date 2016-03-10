package org.cbioportal.cgds_java.framework.rest.helper.impl;

import java.util.HashMap;
import java.util.Map;

import org.cbioportal.cgds_java.framework.rest.helper.api.RESTHelper;
import org.cbioportal.cgds_java.framework.rest.manager.api.CGDSRESTManager;
import org.cbioportal.cgds_java.framework.rest.result.CGDSRESTResult;
import org.cbioportal.cgds_java.framework.util.CGDSPropertiesReader;
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

public class CGDSRESTHelper<T extends CGDSRESTManager> implements RESTHelper {

	protected static final CGDSPropertiesReader CGDS_PROPERTIES_READER = CGDSPropertiesReader.getInstance();

	protected static final String CANCER_TYPES_ENDPOINT = CGDS_PROPERTIES_READER.getCancerTypesEndpoint();
	protected static final String CLINICAL_DATA_SAMPLES_ENDPOINT = CGDS_PROPERTIES_READER.getClinicalDataSamplesEndpoint();
	protected static final String CLINICAL_DATA_PATIENTS_ENDPOINT = CGDS_PROPERTIES_READER.getClinicalDataPatientsEndpoint();
	protected static final String CLINICAL_ATTRIBUTES_SAMPLES_ENDPOINT = CGDS_PROPERTIES_READER.getClinicalAttributesSamplesEndpoint();
	protected static final String CLINICAL_ATTRIBUTES_PATIENTS_ENDPOINT = CGDS_PROPERTIES_READER.getClinicalAttributesPatientsEndpoint();
	protected static final String GENES_ENDPOINT = CGDS_PROPERTIES_READER.getGenesEndpoint();
	protected static final String GENETIC_PROFILES_ENDPOINT = CGDS_PROPERTIES_READER.getGeneticProfilesEndpoint();
	protected static final String SAMPLE_LISTS_ENDPOINT = CGDS_PROPERTIES_READER.getSampleListsEndpoint();
	protected static final String PATIENTS_ENDPOINT = CGDS_PROPERTIES_READER.getPatientsEndpoint();
	protected static final String GENETIC_PROFILE_DATA_ENDPOINT = CGDS_PROPERTIES_READER.getGeneticProfileDataEndpoint();
	protected static final String SAMPLES_ENDPOINT = CGDS_PROPERTIES_READER.getSamplesEndpoint();
	protected static final String STUDIES_ENDPOINT = CGDS_PROPERTIES_READER.getStudiesEndpoint();
	
	protected final T restManager;

	public CGDSRESTHelper(T restManager) {
		this.restManager = restManager;
	}
	
	//getCancerTypes()
	
	@SuppressWarnings("hiding")
	protected <T extends CancerType> CGDSRESTResult<T> getCancerTypes(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getCancerTypes(CANCER_TYPES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<CancerType> getCancerTypes() {
		return getCancerTypes(CancerType.class, null);
	}
	
	public CGDSRESTResult<CancerType> getCancerTypes(String cancer_type_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getCancerTypesQpOptCancerTypeIds(), new String[]{cancer_type_ids});
		return getCancerTypes(CancerType.class, queryParams);
	}
	
	
	//getSampleClinicalData()
	
	@SuppressWarnings("hiding")
	protected <T extends ClinicalDataSample> CGDSRESTResult<T> getSampleClinicalData(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getSampleClinicalData(CLINICAL_DATA_SAMPLES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<ClinicalDataSample> getSampleClinicalData(String study_id, String attribute_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataSamplesQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataSamplesQpReqAttributeIds(), new String[]{attribute_ids});
		return getSampleClinicalData(ClinicalDataSample.class, queryParams);
	}
	
	public CGDSRESTResult<ClinicalDataSample> getSampleClinicalData(String study_id, String attribute_ids, String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataSamplesQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataSamplesQpReqAttributeIds(), new String[]{attribute_ids});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataSamplesQpOptSampleIds(), new String[]{sample_ids});
		return getSampleClinicalData(ClinicalDataSample.class, queryParams);
	}
	
	
	//getPatientClinicalData()
	
	@SuppressWarnings("hiding")
	protected <T extends ClinicalDataPatient> CGDSRESTResult<T> getPatientClinicalData(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getPatientClinicalData(CLINICAL_DATA_PATIENTS_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<ClinicalDataPatient> getPatientClinicalData(String study_id, String attribute_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataPatientsQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataPatientsQpReqAttributeIds(), new String[]{attribute_ids});
		return getPatientClinicalData(ClinicalDataPatient.class, queryParams);
	}
	
	public CGDSRESTResult<ClinicalDataPatient> getPatientClinicalData(String study_id, String attribute_ids, String patient_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataPatientsQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataPatientsQpReqAttributeIds(), new String[]{attribute_ids});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalDataPatientsQpOptPatientIds(), new String[]{patient_ids});
		return getPatientClinicalData(ClinicalDataPatient.class, queryParams);
	}

	
	//getSampleClinicalAttributes()
	
	@SuppressWarnings("hiding")
	protected <T extends ClinicalAttributeSample> CGDSRESTResult<T> getSampleClinicalAttributes(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getSampleClinicalAttributes(CLINICAL_ATTRIBUTES_SAMPLES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<ClinicalAttributeSample> getSampleClinicalAttributes() {
		return getSampleClinicalAttributes(ClinicalAttributeSample.class, null);
	}
	
	public CGDSRESTResult<ClinicalAttributeSample> getSampleClinicalAttributes(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesSamplesQpOptStudyId(), new String[]{study_id});
		return getSampleClinicalAttributes(ClinicalAttributeSample.class, queryParams);
	}
	
	public CGDSRESTResult<ClinicalAttributeSample> getSampleClinicalAttributes(String study_id, String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesSamplesQpOptStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesSamplesQpOptSampleIds(), new String[]{sample_ids});
		return getSampleClinicalAttributes(ClinicalAttributeSample.class, queryParams);
	}
	
	
	//getPatientClinicalAttributes()
	
	@SuppressWarnings("hiding")
	protected <T extends ClinicalAttributePatient> CGDSRESTResult<T> getPatientClinicalAttributes(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getPatientClinicalAttributes(CLINICAL_ATTRIBUTES_PATIENTS_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<ClinicalAttributePatient> getPatientClinicalAttributes() {
		return getPatientClinicalAttributes(ClinicalAttributePatient.class, null);
	}
	
	public CGDSRESTResult<ClinicalAttributePatient> getPatientClinicalAttributes(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesPatientsQpOptStudyId(), new String[]{study_id});
		return getPatientClinicalAttributes(ClinicalAttributePatient.class, queryParams);
	}
	
	public CGDSRESTResult<ClinicalAttributePatient> getPatientClinicalAttributes(String study_id, String patient_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesPatientsQpOptStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getClinicalAttributesPatientsQpOptPatientIds(), new String[]{patient_ids});
		return getPatientClinicalAttributes(ClinicalAttributePatient.class, queryParams);
	}
	
	
	//getGenes()
	
	@SuppressWarnings("hiding")
	protected <T extends Gene> CGDSRESTResult<T> getGenes(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getGenes(GENES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<Gene> getGenes() {
		return getGenes(Gene.class, null);
	}
	
	public CGDSRESTResult<Gene> getGenes(String hugo_gene_symbols) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGenesQpOptHugoGeneSymbols(), new String[]{hugo_gene_symbols});
		return getGenes(Gene.class, queryParams);
	}
	
	
	
	//getGeneticProfiles()
	
	@SuppressWarnings("hiding")
	protected <T extends GeneticProfile> CGDSRESTResult<T> getGeneticProfiles(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getGeneticProfiles(GENETIC_PROFILES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<GeneticProfile> getGeneticProfiles() {
		return getGeneticProfiles(GeneticProfile.class, null);
	}
	
	public CGDSRESTResult<GeneticProfile> getGeneticProfiles(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfilesQpOptStudyId(), new String[]{study_id});
		return getGeneticProfiles(GeneticProfile.class, queryParams);
	}
	
	public CGDSRESTResult<GeneticProfile> getGeneticProfilesByGeneticProfileIds(String genetic_profile_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfilesQpOptGeneticProfileIds(), new String[]{genetic_profile_ids});
		return getGeneticProfiles(GeneticProfile.class, queryParams);
	}
	
	public CGDSRESTResult<GeneticProfile> getGeneticProfiles(String study_id, String genetic_profile_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfilesQpOptStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfilesQpOptGeneticProfileIds(), new String[]{genetic_profile_ids});
		return getGeneticProfiles(GeneticProfile.class, queryParams);
	}
	
	
	
	//getSampleLists()
	
	@SuppressWarnings("hiding")
	protected <T extends SampleList> CGDSRESTResult<T> getSampleLists(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getSampleLists(SAMPLE_LISTS_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<SampleList> getSampleLists() {
		return getSampleLists(SampleList.class, null);
	}
	
	public CGDSRESTResult<SampleList> getSampleLists(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getSampleListsQpOptStudyId(), new String[]{study_id});
		return getSampleLists(SampleList.class, queryParams);
	}
	
	public CGDSRESTResult<SampleList> getSampleListsBySampleIds(String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getSampleListsQpOptSampleIds(), new String[]{sample_ids});
		return getSampleLists(SampleList.class, queryParams);
	}
	
	public CGDSRESTResult<SampleList> getSampleLists(String study_id, String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getSampleListsQpOptStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getSampleListsQpOptSampleIds(), new String[]{sample_ids});
		return getSampleLists(SampleList.class, queryParams);
	}
	
	
	
	//getPatients()
	
	@SuppressWarnings("hiding")
	protected <T extends Patient> CGDSRESTResult<T> getPatients(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getPatients(PATIENTS_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<Patient> getPatients(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getPatientsQpReqStudyId(), new String[]{study_id});
		return getPatients(Patient.class, queryParams);
	}
	
	public CGDSRESTResult<Patient> getPatients(String study_id, String patient_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getPatientsQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getPatientsQpOptPatientIds(), new String[]{patient_ids});
		return getPatients(Patient.class, queryParams);
	}
	
	
	
	//getGeneticProfileData()
	
	@SuppressWarnings("hiding")
	protected <T extends GeneticProfileData> CGDSRESTResult<T> getGeneticProfileData(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getGeneticProfileData(GENETIC_PROFILE_DATA_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<GeneticProfileData> getGeneticProfileData(String genetic_profile_ids, String genes) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfileDataQpReqGeneticProfileIds(), new String[]{genetic_profile_ids});
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfileDataQpReqGenes(), new String[]{genes});
		return getGeneticProfileData(GeneticProfileData.class, queryParams);
	}
	
	public CGDSRESTResult<GeneticProfileData> getGeneticProfileData(String genetic_profile_ids, String genes, String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfileDataQpReqGeneticProfileIds(), new String[]{genetic_profile_ids});
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfileDataQpReqGenes(), new String[]{genes});
		queryParams.put(CGDS_PROPERTIES_READER.getGeneticProfileDataQpOptSampleIds(), new String[]{sample_ids});
		return getGeneticProfileData(GeneticProfileData.class, queryParams);
	}

	
	
	//getSamples()
	
	@SuppressWarnings("hiding")
	protected <T extends Sample> CGDSRESTResult<T> getSamples(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getSamples(SAMPLES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<Sample> getSamples(String study_id) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getSamplesQpReqStudyId(), new String[]{study_id});
		return getSamples(Sample.class, queryParams);
	}
	
	public CGDSRESTResult<Sample> getSamples(String study_id, String sample_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getSamplesQpReqStudyId(), new String[]{study_id});
		queryParams.put(CGDS_PROPERTIES_READER.getSamplesQpOptSampleIds(), new String[]{sample_ids});
		return getSamples(Sample.class, queryParams);
	}
	
	
	
	//getStudies()
	
	@SuppressWarnings("hiding")
	protected <T extends Study> CGDSRESTResult<T> getStudies(Class<T> cls, Map<String, String[]> queryParameters) {
		return (new CGDSRESTResult<T>(cls, restManager.getStudies(STUDIES_ENDPOINT, queryParameters)));
	}

	public CGDSRESTResult<Study> getStudies() {
		return getStudies(Study.class, null);
	}
	
	public CGDSRESTResult<Study> getStudies(String study_ids) {
		Map<String, String[]> queryParams = new HashMap<String, String[]>();
		queryParams.put(CGDS_PROPERTIES_READER.getStudiesQpOptStudyIds(), new String[]{study_ids});
		return getStudies(Study.class, queryParams);
	}
	
	
	@Override
	public String getDescription() {
		return "CGDS REST Helper";
	}
	
}
