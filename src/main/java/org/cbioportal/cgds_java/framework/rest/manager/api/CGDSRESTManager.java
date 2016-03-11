package org.cbioportal.cgds_java.framework.rest.manager.api;

import java.util.Map;

import org.cbioportal.cgds_java.framework.rest.result.RESTResult;

public interface CGDSRESTManager extends RESTManager {

	public RESTResult getCancerTypes(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getSampleClinicalData(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getPatientClinicalData(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getSampleClinicalAttributes(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getPatientClinicalAttributes(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getGenes(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getGeneticProfiles(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getSampleLists(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getPatients(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getGeneticProfileData(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getSamples(String commandURL, Map<String, String[]> queryParameters);

	public RESTResult getStudies(String commandURL, Map<String, String[]> queryParameters);

}
