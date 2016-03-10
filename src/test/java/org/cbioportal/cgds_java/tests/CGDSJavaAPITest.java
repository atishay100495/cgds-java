package org.cbioportal.cgds_java.tests;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cbioportal.cgds_java.api.CGDSAPI;
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
import org.testng.Assert;
import org.testng.annotations.Test;

public class CGDSJavaAPITest {

	public static Logger logger = Logger.getLogger("CGDSJavaAPITest");

	@Test
	public void testGetCancerTypes() throws Throwable {
		logger.log(Level.INFO, "Testing getCancerTypes()");
		List<CancerType> cancerTypes = CGDSAPI.getCancerTypes();
		for (CancerType cancerType : cancerTypes) {
			logger.info(cancerType.toString());
		}
		logger.info("Number of items: " + cancerTypes.size() + "");
		Assert.assertTrue(!cancerTypes.isEmpty(), "List is empty");

		String cancer_type_ids = "brca";
		cancerTypes = CGDSAPI.getCancerTypes(cancer_type_ids);
		for (CancerType cancerType : cancerTypes) {
			logger.info(cancerType.toString());
		}
		Assert.assertTrue(!cancerTypes.isEmpty(), "List is empty");

		cancer_type_ids = "brca,unec";
		cancerTypes = CGDSAPI.getCancerTypes(cancer_type_ids);
		for (CancerType cancerType : cancerTypes) {
			logger.info(cancerType.toString());
		}
		Assert.assertTrue(!cancerTypes.isEmpty(), "List is empty");
	}

	@Test
	public void testGetSampleClinicalData() throws Throwable {
		logger.log(Level.INFO, "Testing getSampleClinicalData()");
		String study_id = "brca_tcga_pub";
		String attribute_ids = "TUMOR";
		List<ClinicalDataSample> clinicalDataSamplesList = CGDSAPI.getSampleClinicalData(study_id, attribute_ids);
		for (ClinicalDataSample clinicalDataSample : clinicalDataSamplesList) {
			logger.info(clinicalDataSample.toString());
		}
		logger.info("Number of items: " + clinicalDataSamplesList.size() + "");
		Assert.assertTrue(!clinicalDataSamplesList.isEmpty(), "List is empty");

		String sample_ids = "TCGA-A2-A0T2-01";
		clinicalDataSamplesList = CGDSAPI.getSampleClinicalData(study_id, attribute_ids, sample_ids);
		for (ClinicalDataSample clinicalDataSample : clinicalDataSamplesList) {
			logger.info(clinicalDataSample.toString());
		}
		logger.info("Number of items: " + clinicalDataSamplesList.size() + "");
		Assert.assertTrue(!clinicalDataSamplesList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetPatientClinicalData() throws Throwable {
		logger.log(Level.INFO, "Testing getPatientClinicalData()");
		String study_id = "acc_tcga";
		String attribute_ids = "PROSPECTIVE_COLLECTION";
		List<ClinicalDataPatient> clinicalDataPatientsList = CGDSAPI.getPatientClinicalData(study_id, attribute_ids);
		for (ClinicalDataPatient clinicalDataPatient : clinicalDataPatientsList) {
			logger.info(clinicalDataPatient.toString());
		}
		logger.info("Number of items: " + clinicalDataPatientsList.size() + "");
		Assert.assertTrue(!clinicalDataPatientsList.isEmpty(), "List is empty");

		String patient_ids = "TCGA-OR-A5K6";
		clinicalDataPatientsList = CGDSAPI.getPatientClinicalData(study_id, attribute_ids, patient_ids);
		for (ClinicalDataPatient clinicalDataPatient : clinicalDataPatientsList) {
			logger.info(clinicalDataPatient.toString());
		}
		logger.info("Number of items: " + clinicalDataPatientsList.size() + "");
		Assert.assertTrue(!clinicalDataPatientsList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetSampleClinicalAttributes() throws Throwable {
		logger.log(Level.INFO, "Testing getSampleClinicalAttributes()");
		List<ClinicalAttributeSample> clinicalAttributesSamplesList = CGDSAPI.getSampleClinicalAttributes();
		for (ClinicalAttributeSample clinicalAttributesSample : clinicalAttributesSamplesList) {
			logger.info(clinicalAttributesSample.toString());
		}
		logger.info("Number of items: " + clinicalAttributesSamplesList.size() + "");
		Assert.assertTrue(!clinicalAttributesSamplesList.isEmpty(), "List is empty");

		String study_id = "blca_tcga";
		clinicalAttributesSamplesList = CGDSAPI.getSampleClinicalAttributes(study_id);
		for (ClinicalAttributeSample clinicalAttributesSample : clinicalAttributesSamplesList) {
			logger.info(clinicalAttributesSample.toString());
		}
		logger.info("Number of items: " + clinicalAttributesSamplesList.size() + "");
		Assert.assertTrue(!clinicalAttributesSamplesList.isEmpty(), "List size is not 1");

		String sample_ids = "sample_id1,sample_id2";
		clinicalAttributesSamplesList = CGDSAPI.getSampleClinicalAttributes(study_id, sample_ids);
		for (ClinicalAttributeSample clinicalAttributesSample : clinicalAttributesSamplesList) {
			logger.info(clinicalAttributesSample.toString());
		}
		logger.info("Number of items: " + clinicalAttributesSamplesList.size() + "");
		Assert.assertTrue(clinicalAttributesSamplesList != null, "List is null");
	}

	@Test
	public void testGetPatientClinicalAttributes() throws Throwable {
		logger.log(Level.INFO, "Testing getPatientClinicalAttributes()");
		List<ClinicalAttributePatient> clinicalAttributesPatientsList = CGDSAPI.getPatientClinicalAttributes();
		for (ClinicalAttributePatient clinicalAttributesPatient : clinicalAttributesPatientsList) {
			logger.info(clinicalAttributesPatient.toString());
		}
		logger.info("Number of items: " + clinicalAttributesPatientsList.size() + "");
		Assert.assertTrue(!clinicalAttributesPatientsList.isEmpty(), "List is empty");

		String study_id = "acc_tcga";
		clinicalAttributesPatientsList = CGDSAPI.getPatientClinicalAttributes(study_id);
		for (ClinicalAttributePatient clinicalAttributesPatient : clinicalAttributesPatientsList) {
			logger.info(clinicalAttributesPatient.toString());
		}
		logger.info("Number of items: " + clinicalAttributesPatientsList.size() + "");
		Assert.assertTrue(!clinicalAttributesPatientsList.isEmpty(), "List is empty");

		String patient_ids = "TCGA-OR-A5K6";
		clinicalAttributesPatientsList = CGDSAPI.getPatientClinicalAttributes(study_id, patient_ids);
		for (ClinicalAttributePatient clinicalAttributesPatient : clinicalAttributesPatientsList) {
			logger.info(clinicalAttributesPatient.toString());
		}
		logger.info("Number of items: " + clinicalAttributesPatientsList.size() + "");
		Assert.assertTrue(!clinicalAttributesPatientsList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetGenes() throws Throwable {
		logger.log(Level.INFO, "Testing getGenes()");
		List<Gene> genesList = CGDSAPI.getGenes();
		for (Gene gene : genesList) {
			logger.info(gene.toString());
		}
		logger.info("Number of items: " + genesList.size() + "");
		Assert.assertTrue(!genesList.isEmpty(), "List is empty");

		String hugo_gene_symbols = "CDKN2AP16INK4A";
		genesList = CGDSAPI.getGenes(hugo_gene_symbols);
		for (Gene gene : genesList) {
			logger.info(gene.toString());
		}
		logger.info("Number of items: " + genesList.size() + "");
		Assert.assertTrue(!genesList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetGeneticProfiles() throws Throwable {
		logger.log(Level.INFO, "Testing getGeneticProfiles()");
		List<GeneticProfile> geneticProfilesList = CGDSAPI.getGeneticProfiles();
		for (GeneticProfile geneticProfile : geneticProfilesList) {
			logger.info(geneticProfile.toString());
		}
		logger.info("Number of items: " + geneticProfilesList.size() + "");
		Assert.assertTrue(!geneticProfilesList.isEmpty(), "List is empty");

		String study_id = "acc_tcga";
		geneticProfilesList = CGDSAPI.getGeneticProfiles(study_id);
		for (GeneticProfile geneticProfile : geneticProfilesList) {
			logger.info(geneticProfile.toString());
		}
		logger.info("Number of items: " + geneticProfilesList.size() + "");
		Assert.assertTrue(!geneticProfilesList.isEmpty(), "List is empty");

		String genetic_profile_ids = "acc_tcga_gistic,acc_tcga_methylation_hm450";
		geneticProfilesList = CGDSAPI.getGeneticProfilesByGeneticProfileIds(genetic_profile_ids);
		for (GeneticProfile geneticProfile : geneticProfilesList) {
			logger.info(geneticProfile.toString());
		}
		logger.info("Number of items: " + geneticProfilesList.size() + "");
		Assert.assertTrue(!geneticProfilesList.isEmpty(), "List is empty");

		geneticProfilesList = CGDSAPI.getGeneticProfiles(study_id, genetic_profile_ids);
		for (GeneticProfile geneticProfile : geneticProfilesList) {
			logger.info(geneticProfile.toString());
		}
		logger.info("Number of items: " + geneticProfilesList.size() + "");
		Assert.assertTrue(!geneticProfilesList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetSampleLists() throws Throwable {
		logger.log(Level.INFO, "Testing getSampleLists()");
		List<SampleList> sampleLists = CGDSAPI.getSampleLists();
		for (SampleList sampleList : sampleLists) {
			logger.info(sampleList.toString());
		}
		logger.info("Number of items: " + sampleLists.size() + "");
		Assert.assertTrue(!sampleLists.isEmpty(), "List is empty");

		String study_id = "acc_tcga";
		sampleLists = CGDSAPI.getSampleLists(study_id);
		for (SampleList sampleList : sampleLists) {
			logger.info(sampleList.toString());
		}
		logger.info("Number of items: " + sampleLists.size() + "");
		Assert.assertTrue(!sampleLists.isEmpty(), "List is empty");

		String sample_ids = "CGA-OR-A5J1-01";
		sampleLists = CGDSAPI.getSampleListsBySampleIds(sample_ids);
		for (SampleList sampleList : sampleLists) {
			logger.info(sampleList.toString());
		}
		logger.info("Number of items: " + sampleLists.size() + "");
		Assert.assertTrue(!sampleLists.isEmpty(), "List is empty");

		sampleLists = CGDSAPI.getSampleLists(study_id, sample_ids);
		for (SampleList sampleList : sampleLists) {
			logger.info(sampleList.toString());
		}
		logger.info("Number of items: " + sampleLists.size() + "");
		Assert.assertTrue(!sampleLists.isEmpty(), "List is empty");
	}

	@Test
	public void testGetPatients() throws Throwable {
		logger.log(Level.INFO, "Testing getPatients()");
		String study_id = "acc_tcga";
		List<Patient> patientsList = CGDSAPI.getPatients(study_id);
		for (Patient patient : patientsList) {
			logger.info(patient.toString());
		}
		logger.info("Number of items: " + patientsList.size() + "");
		Assert.assertTrue(!patientsList.isEmpty(), "List is empty");

		String patient_ids = "TCGA-OR-A5K6";
		patientsList = CGDSAPI.getPatients(study_id, patient_ids);
		for (Patient patient : patientsList) {
			logger.info(patient.toString());
		}
		logger.info("Number of items: " + patientsList.size() + "");
		Assert.assertTrue(!patientsList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetGeneticProfileData() throws Throwable {
		logger.log(Level.INFO, "Testing getGeneticProfileData()");
		String genetic_profile_ids = "brca_tcga_pub_mutations";
		String genes = "BRCA1";
		List<GeneticProfileData> geneticProfileDataList = CGDSAPI.getGeneticProfileData(genetic_profile_ids, genes);
		for (GeneticProfileData geneticProfileData : geneticProfileDataList) {
			logger.info(geneticProfileData.toString());
		}
		logger.info("Number of items: " + geneticProfileDataList.size() + "");
		Assert.assertTrue(!geneticProfileDataList.isEmpty(), "List is empty");

		String sample_ids = "TCGA-A2-A0D2-01";
		geneticProfileDataList = CGDSAPI.getGeneticProfileData(genetic_profile_ids, genes, sample_ids);
		for (GeneticProfileData geneticProfileData : geneticProfileDataList) {
			logger.info(geneticProfileData.toString());
		}
		logger.info("Number of items: " + geneticProfileDataList.size() + "");
		Assert.assertTrue(!geneticProfileDataList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetSamples() throws Throwable {
		logger.log(Level.INFO, "Testing getSamples()");
		String study_id = "brca_tcga_pub";
		List<Sample> samplesList = CGDSAPI.getSamples(study_id);
		for (Sample sample : samplesList) {
			logger.info(sample.toString());
		}
		logger.info("Number of items: " + samplesList.size() + "");
		Assert.assertTrue(!samplesList.isEmpty(), "List is empty");

		String sample_ids = "TCGA-A2-A0T2-01";
		samplesList = CGDSAPI.getSamples(study_id, sample_ids);
		for (Sample sample : samplesList) {
			logger.info(sample.toString());
		}
		logger.info("Number of items: " + samplesList.size() + "");
		Assert.assertTrue(!samplesList.isEmpty(), "List is empty");
	}

	@Test
	public void testGetStudies() throws Throwable {
		logger.log(Level.INFO, "Testing getStudies()");
		List<Study> studiesList = CGDSAPI.getStudies();
		for (Study study : studiesList) {
			logger.info(study.toString());
		}
		logger.info("Number of items: " + studiesList.size() + "");
		Assert.assertTrue(!studiesList.isEmpty(), "List is empty");

		String study_id = "brca_tcga_pub";
		studiesList = CGDSAPI.getStudies(study_id);
		for (Study study : studiesList) {
			logger.info(study.toString());
		}
		logger.info("Number of items: " + studiesList.size() + "");
		Assert.assertTrue(!studiesList.isEmpty(), "List is empty");
	}

}