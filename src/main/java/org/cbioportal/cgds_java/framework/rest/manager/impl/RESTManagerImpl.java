package org.cbioportal.cgds_java.framework.rest.manager.impl;

import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.cbioportal.cgds_java.framework.rest.RESTClient;
import org.cbioportal.cgds_java.framework.rest.manager.api.RESTManager;

public abstract class RESTManagerImpl implements RESTManager {

	public static final String APPLICATION_JSON = "application/json";
	protected MultivaluedMap<String, Object> HEADERS = new MultivaluedHashMap<String, Object>();

	protected static Logger logger = Logger.getLogger("RESTManagerImpl");

	protected RESTClient restClient;

}

