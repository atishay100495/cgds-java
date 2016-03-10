package org.cbioportal.cgds_java.framework.util;

import java.util.logging.Logger;

public class CGDSPropertiesReader extends AbstractPropertiesReader {

	private static CGDSPropertiesReader singleton = null;

	private static Logger logger = Logger.getLogger("CGDSPropertiesReader");

	private static final String CGDS_PROPERTIES_FILE = "cgds.properties";

	private static final String CGDS_REST_ENDPOINT = "cgds.rest.endpoint";

	private static final String CGDS_CANCER_TYPES_ENDPOINT = "cgds.rest.cancertypes.endpoint";
	private static final String CGDS_CANCER_TYPES_QP_OPT_CANCER_TYPE_IDS = "cgds.rest.cancertypes.queryparameter.optional.cancer_type_ids";

	private static final String CGDS_CLINICAL_DATA_SAMPLES_ENDPOINT = "cgds.rest.clinicaldata.samples.endpoint";
	private static final String CGDS_CLINICAL_DATA_SAMPLES_QP_REQ_STUDY_ID = "cgds.rest.clinicaldata.samples.queryparameter.required.study_id";
	private static final String CGDS_CLINICAL_DATA_SAMPLES_QP_REQ_ATTRIBUTE_IDS = "cgds.rest.clinicaldata.samples.queryparameter.required.attribute_ids";
	private static final String CGDS_CLINICAL_DATA_SAMPLES_QP_OPT_SAMPLE_IDS = "cgds.rest.clinicaldata.samples.queryparameter.optional.sample_ids";

	private static final String CGDS_CLINICAL_DATA_PATIENTS_ENDPOINT = "cgds.rest.clinicaldata.patients.endpoint";
	private static final String CGDS_CLINICAL_DATA_PATIENTS_QP_REQ_STUDY_ID = "cgds.rest.clinicaldata.patients.queryparameter.required.study_id";
	private static final String CGDS_CLINICAL_DATA_PATIENTS_QP_REQ_ATTRIBUTE_IDS = "cgds.rest.clinicaldata.patients.queryparameter.required.attribute_ids";
	private static final String CGDS_CLINICAL_DATA_PATIENTS_QP_OPT_PATIENT_IDS = "cgds.rest.clinicaldata.patients.queryparameter.optional.patient_ids";

	private static final String CGDS_CLINICAL_ATTRIBUTES_SAMPLES_ENDPOINT = "cgds.rest.clinicalattributes.samples.endpoint";
	private static final String CGDS_CLINICAL_ATTRIBUTES_SAMPLES_QP_OPT_STUDY_ID = "cgds.rest.clinicalattributes.samples.queryparameter.optional.study_id";
	private static final String CGDS_CLINICAL_ATTRIBUTES_SAMPLES_QP_OPT_SAMPLE_IDS = "cgds.rest.clinicalattributes.samples.queryparameter.optional.sample_ids";

	private static final String CGDS_CLINICAL_ATTRIBUTES_PATIENTS_ENDPOINT = "cgds.rest.clinicalattributes.patients.endpoint";
	private static final String CGDS_CLINICAL_ATTRIBUTES_PATIENTS_QP_OPT_STUDY_ID = "cgds.rest.clinicalattributes.patients.queryparameter.optional.study_id";
	private static final String CGDS_CLINICAL_ATTRIBUTES_PATIENTS_QP_OPT_PATIENT_IDS = "cgds.rest.clinicalattributes.patients.queryparameter.optional.patient_ids";

	private static final String CGDS_GENES_ENDPOINT = "cgds.rest.genes.endpoint";
	private static final String CGDS_GENES_QP_OPT_HUGO_GENE_SYMBOLS = "cgds.rest.genes.queryparameter.optional.hugo_gene_symbols";

	private static final String CGDS_GENETIC_PROFILES_ENDPOINT = "cgds.rest.geneticprofiles.endpoint";
	private static final String CGDS_GENETIC_PROFILES_QP_OPT_STUDY_ID = "cgds.rest.geneticprofiles.queryparameter.optional.study_id";
	private static final String CGDS_GENETIC_PROFILES_QP_OPT_GENETIC_PROFILE_IDS = "cgds.rest.geneticprofiles.queryparameter.optional.genetic_profile_ids";

	private static final String CGDS_SAMPLE_LISTS_ENDPOINT = "cgds.rest.samplelists.endpoint";
	private static final String CGDS_SAMPLE_LISTS_QP_OPT_STUDY_ID = "cgds.rest.samplelists.queryparameter.optional.study_id";
	private static final String CGDS_SAMPLE_LISTS_QP_OPT_SAMPLE_IDS = "cgds.rest.samplelists.queryparameter.optional.sample_ids";

	private static final String CGDS_PATIENTS_ENDPOINT = "cgds.rest.patients.endpoint";
	private static final String CGDS_PATIENTS_QP_REQ_STUDY_ID = "cgds.rest.patients.queryparameter.required.study_id";
	private static final String CGDS_PATIENTS_QP_OPT_PATIENT_IDS = "cgds.rest.patients.queryparameter.optional.patient_ids";

	private static final String CGDS_GENETIC_PROFILE_DATA_ENDPOINT = "cgds.rest.geneticprofiledata.endpoint";
	private static final String CGDS_GENETIC_PROFILE_DATA_QP_REQ_GENETIC_PROFILE_IDS = "cgds.rest.geneticprofiledata.queryparameter.required.genetic_profile_ids";
	private static final String CGDS_GENETIC_PROFILE_DATA_QP_REQ_GENES = "cgds.rest.geneticprofiledata.queryparameter.required.genes";
	private static final String CGDS_GENETIC_PROFILE_DATA_QP_OPT_SAMPLE_IDS = "cgds.rest.geneticprofiledata.queryparameter.optional.sample_ids";

	private static final String CGDS_SAMPLES_ENDPOINT = "cgds.rest.samples.endpoint";
	private static final String CGDS_SAMPLES_QP_REQ_STUDY_ID = "cgds.rest.samples.queryparameter.required.study_id";
	private static final String CGDS_SAMPLES_QP_OPT_SAMPLE_IDS = "cgds.rest.samples.queryparameter.optional.sample_ids";

	private static final String CGDS_STUDIES_ENDPOINT = "cgds.rest.studies.endpoint";
	private static final String CGDS_STUDIES_QP_OPT_STUDY_IDS = "cgds.rest.studies.queryparameter.optional.study_ids";

	private CGDSPropertiesReader() {
		super(getSystemProperty(CGDS_PROPERTIES_FILE,
				getSystemProperty("project.home", "") + "/config/cgds.properties"), logger);
	}

	public static CGDSPropertiesReader getInstance() {
		synchronized (CGDSPropertiesReader.class) {
			if (singleton == null)
				singleton = new CGDSPropertiesReader();
		}
		return singleton;
	}

	public String getCGDSRESTEndpoint() {
		return getProperty(CGDS_REST_ENDPOINT, "http://www.cbioportal.org/api");
	}

	public String getCancerTypesEndpoint() {
		return getProperty(CGDS_CANCER_TYPES_ENDPOINT, "/cancertypes");
	}

	public String getCancerTypesQpOptCancerTypeIds() {
		return getProperty(CGDS_CANCER_TYPES_QP_OPT_CANCER_TYPE_IDS, "cancer_type_ids");
	}

	public String getClinicalDataSamplesEndpoint() {
		return getProperty(CGDS_CLINICAL_DATA_SAMPLES_ENDPOINT, "/clinicaldata/samples");
	}

	public String getClinicalDataSamplesQpReqStudyId() {
		return getProperty(CGDS_CLINICAL_DATA_SAMPLES_QP_REQ_STUDY_ID, "study_id");
	}

	public String getClinicalDataSamplesQpReqAttributeIds() {
		return getProperty(CGDS_CLINICAL_DATA_SAMPLES_QP_REQ_ATTRIBUTE_IDS, "attribute_ids");
	}

	public String getClinicalDataSamplesQpOptSampleIds() {
		return getProperty(CGDS_CLINICAL_DATA_SAMPLES_QP_OPT_SAMPLE_IDS, "sample_ids");
	}

	public String getClinicalDataPatientsEndpoint() {
		return getProperty(CGDS_CLINICAL_DATA_PATIENTS_ENDPOINT, "/clinicaldata/patients");
	}

	public String getClinicalDataPatientsQpReqStudyId() {
		return getProperty(CGDS_CLINICAL_DATA_PATIENTS_QP_REQ_STUDY_ID, "study_id");
	}

	public String getClinicalDataPatientsQpReqAttributeIds() {
		return getProperty(CGDS_CLINICAL_DATA_PATIENTS_QP_REQ_ATTRIBUTE_IDS, "attribute_ids");
	}

	public String getClinicalDataPatientsQpOptPatientIds() {
		return getProperty(CGDS_CLINICAL_DATA_PATIENTS_QP_OPT_PATIENT_IDS, "patient_ids");
	}

	public String getClinicalAttributesSamplesEndpoint() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_SAMPLES_ENDPOINT, "/clinicalattributes/samples");
	}

	public String getClinicalAttributesSamplesQpOptStudyId() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_SAMPLES_QP_OPT_STUDY_ID, "study_id");
	}

	public String getClinicalAttributesSamplesQpOptSampleIds() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_SAMPLES_QP_OPT_SAMPLE_IDS, "sample_ids");
	}

	public String getClinicalAttributesPatientsEndpoint() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_PATIENTS_ENDPOINT, "/clinicalattributes/patients");
	}

	public String getClinicalAttributesPatientsQpOptStudyId() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_PATIENTS_QP_OPT_STUDY_ID, "study_id");
	}

	public String getClinicalAttributesPatientsQpOptPatientIds() {
		return getProperty(CGDS_CLINICAL_ATTRIBUTES_PATIENTS_QP_OPT_PATIENT_IDS, "patient_ids");
	}

	public String getGenesEndpoint() {
		return getProperty(CGDS_GENES_ENDPOINT, "/genes");
	}

	public String getGenesQpOptHugoGeneSymbols() {
		return getProperty(CGDS_GENES_QP_OPT_HUGO_GENE_SYMBOLS, "hugo_gene_symbols");
	}

	public String getGeneticProfilesEndpoint() {
		return getProperty(CGDS_GENETIC_PROFILES_ENDPOINT, "/geneticprofiles");
	}

	public String getGeneticProfilesQpOptStudyId() {
		return getProperty(CGDS_GENETIC_PROFILES_QP_OPT_STUDY_ID, "study_id");
	}

	public String getGeneticProfilesQpOptGeneticProfileIds() {
		return getProperty(CGDS_GENETIC_PROFILES_QP_OPT_GENETIC_PROFILE_IDS, "genetic_profile_ids");
	}

	public String getSampleListsEndpoint() {
		return getProperty(CGDS_SAMPLE_LISTS_ENDPOINT, "/samplelists");
	}

	public String getSampleListsQpOptStudyId() {
		return getProperty(CGDS_SAMPLE_LISTS_QP_OPT_STUDY_ID, "study_id");
	}

	public String getSampleListsQpOptSampleIds() {
		return getProperty(CGDS_SAMPLE_LISTS_QP_OPT_SAMPLE_IDS, "sample_ids");
	}

	public String getPatientsEndpoint() {
		return getProperty(CGDS_PATIENTS_ENDPOINT, "/patients");
	}

	public String getPatientsQpReqStudyId() {
		return getProperty(CGDS_PATIENTS_QP_REQ_STUDY_ID, "study_id");
	}

	public String getPatientsQpOptPatientIds() {
		return getProperty(CGDS_PATIENTS_QP_OPT_PATIENT_IDS, "patient_ids");
	}

	public String getGeneticProfileDataEndpoint() {
		return getProperty(CGDS_GENETIC_PROFILE_DATA_ENDPOINT, "/geneticprofiledata");
	}

	public String getGeneticProfileDataQpReqGeneticProfileIds() {
		return getProperty(CGDS_GENETIC_PROFILE_DATA_QP_REQ_GENETIC_PROFILE_IDS, "genetic_profile_ids");
	}

	public String getGeneticProfileDataQpReqGenes() {
		return getProperty(CGDS_GENETIC_PROFILE_DATA_QP_REQ_GENES, "genes");
	}

	public String getGeneticProfileDataQpOptSampleIds() {
		return getProperty(CGDS_GENETIC_PROFILE_DATA_QP_OPT_SAMPLE_IDS, "sample_ids");
	}

	public String getSamplesEndpoint() {
		return getProperty(CGDS_SAMPLES_ENDPOINT, "/samples");
	}

	public String getSamplesQpReqStudyId() {
		return getProperty(CGDS_SAMPLES_QP_REQ_STUDY_ID, "study_id");
	}

	public String getSamplesQpOptSampleIds() {
		return getProperty(CGDS_SAMPLES_QP_OPT_SAMPLE_IDS, "sample_ids");
	}

	public String getStudiesEndpoint() {
		return getProperty(CGDS_STUDIES_ENDPOINT, "/studies");
	}

	public String getStudiesQpOptStudyIds() {
		return getProperty(CGDS_STUDIES_QP_OPT_STUDY_IDS, "study_ids");
	}

}
