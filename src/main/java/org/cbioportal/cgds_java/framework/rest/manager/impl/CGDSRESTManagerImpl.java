package org.cbioportal.cgds_java.framework.rest.manager.impl;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.cbioportal.cgds_java.framework.rest.RESTClient;
import org.cbioportal.cgds_java.framework.rest.manager.api.CGDSRESTManager;
import org.cbioportal.cgds_java.framework.rest.result.RESTResult;
import org.cbioportal.cgds_java.framework.util.CGDSPropertiesReader;

public class CGDSRESTManagerImpl extends RESTManagerImpl implements CGDSRESTManager{

	protected static final CGDSPropertiesReader CGDS_PROPERTIES_READER = CGDSPropertiesReader.getInstance();	

	protected final String cgdsRestEndpoint = CGDS_PROPERTIES_READER.getCGDSRESTEndpoint();

	public CGDSRESTManagerImpl() {
		this.restClient = new RESTClient(cgdsRestEndpoint);
	}
	
	public RESTResult get(String commandURL, Map<String, String[]> queryParameters) {
		Response response = null;
		response = restClient.sendGETRequest(commandURL, queryParameters, HEADERS, APPLICATION_JSON);
		return (new RESTResult(response));
	}
	
	@Override
	public String getDescription() {
		return "CGDS REST Manager Implementation";
	}

	@Override
	public RESTResult getCancerTypes(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getSampleClinicalData(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getPatientClinicalData(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getSampleClinicalAttributes(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getPatientClinicalAttributes(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getGenes(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getGeneticProfiles(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getSampleLists(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getPatients(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getGeneticProfileData(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getSamples(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}

	@Override
	public RESTResult getStudies(String commandURL, Map<String, String[]> queryParameters) {
		return get(commandURL, queryParameters);
	}
	
}
