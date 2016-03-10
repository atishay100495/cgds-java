package org.cbioportal.cgds_java.framework.rest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.cbioportal.cgds_java.framework.rest.util.CookieHandler;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;


public class RESTClient {
	
	public static final int READ_TIMEOUT = 600 * 1000;
	public static final int CONNECT_TIMEOUT = 180 * 1000;
	public static final int MAX_ENTITY_SIZE_LOGGED = Integer.getInteger("restClient.log.maxEntitySize", (1024 * 1024 * 2));

	protected Client client;
	protected WebTarget webTarget;

	protected static Logger logger = Logger.getLogger("RESTClient");

	public RESTClient(String baseURL) {
		this(baseURL, true);
	}
	
	public RESTClient(String baseURL, boolean logEntity) {
		ClientBuilder clientBuilder = null;
		clientBuilder = ClientBuilder.newBuilder();
		clientBuilder = clientBuilder.sslContext(getSslContext());
		clientBuilder = clientBuilder.hostnameVerifier(getHostnameVerifier());
		clientBuilder = clientBuilder.property(ClientProperties.FOLLOW_REDIRECTS, true);
		client = clientBuilder.build();
		if(logEntity)
			client = client.register(new LoggingFilter(logger, MAX_ENTITY_SIZE_LOGGED));
		else
			client = client.register(new LoggingFilter(logger, logEntity));
		client = client.register(JsonProcessingFeature.class).property(JsonGenerator.PRETTY_PRINTING, true);
		client = client.register(new CookieHandler());
		client = client.property(ClientProperties.CONNECT_TIMEOUT, Integer.valueOf(CONNECT_TIMEOUT));
		client = client.property(ClientProperties.READ_TIMEOUT, Integer.valueOf(READ_TIMEOUT));
		webTarget = client.target(baseURL);
	}
	
	protected RESTClient(String baseURL, Class<?>[] additionalClassesForRegistration, boolean logEntity) {
		ClientBuilder clientBuilder = null;
		clientBuilder = ClientBuilder.newBuilder();
		if(additionalClassesForRegistration != null) {
			for(Class<?> cls : additionalClassesForRegistration) {
				clientBuilder = clientBuilder.register(cls);
			}
		}
		clientBuilder = clientBuilder.sslContext(getSslContext());
		clientBuilder = clientBuilder.hostnameVerifier(getHostnameVerifier());
		clientBuilder = clientBuilder.property(ClientProperties.FOLLOW_REDIRECTS, true);
		client = clientBuilder.build();
		if(logEntity)
			client = client.register(new LoggingFilter(logger, MAX_ENTITY_SIZE_LOGGED));
		else
			client = client.register(new LoggingFilter(logger, logEntity));
		client = client.register(JsonProcessingFeature.class).property(JsonGenerator.PRETTY_PRINTING, true);
		client = client.register(new CookieHandler());
		client = client.property(ClientProperties.CONNECT_TIMEOUT, Integer.valueOf(CONNECT_TIMEOUT));
		client = client.property(ClientProperties.READ_TIMEOUT, Integer.valueOf(READ_TIMEOUT));
		webTarget = client.target(baseURL);
	}

	
	protected RESTClient(String baseURL, Class<?>[] additionalClassesForRegistration) {
		this(baseURL, additionalClassesForRegistration, true);
	}

	protected RESTClient(String baseURL, String userName, String password, boolean isDigest, boolean logEntity) {
		ClientBuilder clientBuilder = null;
		HttpAuthenticationFeature feature = null;
		clientBuilder = ClientBuilder.newBuilder();
		clientBuilder = clientBuilder.sslContext(getSslContext());
		clientBuilder = clientBuilder.hostnameVerifier(getHostnameVerifier());
		if (isDigest) {
			feature = HttpAuthenticationFeature.digest(userName, password);
		} else {
			feature = HttpAuthenticationFeature.basic(userName, password);
		}
		clientBuilder = clientBuilder.property(ClientProperties.FOLLOW_REDIRECTS, true);
		client = clientBuilder.build();
		if(logEntity)
			client = client.register(new LoggingFilter(logger, MAX_ENTITY_SIZE_LOGGED));
		else
			client = client.register(new LoggingFilter(logger, logEntity));
		client = client.register(JsonProcessingFeature.class).property(JsonGenerator.PRETTY_PRINTING, true);
		client = client.register(new CookieHandler());
		client = client.register(feature);
		client = client.property(ClientProperties.CONNECT_TIMEOUT, Integer.valueOf(CONNECT_TIMEOUT));
		client = client.property(ClientProperties.READ_TIMEOUT, Integer.valueOf(READ_TIMEOUT));
		webTarget = client.target(baseURL);
	}
	
	protected RESTClient(String baseURL, String userName, String password, boolean isDigest) {
		this(baseURL, userName, password, isDigest, true);
	}

	/*
	 * The part below is required to accept un-trusted connections.
	 */
	public SSLContext getSslContext() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, certs, new SecureRandom());
			SSLContext.setDefault(ctx);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		return ctx;
	}
	
	public static HostnameVerifier getHostnameVerifier() {
		return (new HostnameVerifier() {
			
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}

	public Response sendPOSTRequest(String commandURL, JsonObject entity) {
		Builder builder = webTarget.path(commandURL)
				.request(MediaType.APPLICATION_JSON);
		return builder.post(Entity.json(entity));
	}

	public Response sendPOSTRequest(String commandURL, Entity<?> entity, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = webTarget.path(commandURL)
				.request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.post(entity);
	}
	
	public Response sendPOSTRequest(String commandURL,Map<String, String[]> queryParameters, Entity<?> entity, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = null;
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		builder = webTarget.request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.post(entity);
	}

	public Response sendGETRequest(String commandURL, Map<String, String[]> queryParameters) {
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		return webTarget.
				request(MediaType.APPLICATION_JSON).get();
	}

	public Response sendGETRequest(String commandURL, Map<String, String[]> queryParameters, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = null;
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		builder = webTarget.request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.get();
	}

	public Response sendDELETERequest(String commandURL) {
		Builder builder = webTarget.path(commandURL).request(MediaType.APPLICATION_JSON);
		return builder.delete();
	}
    
	public Response sendDELETERequest(String commandURL, Map<String, String[]> queryParameters) {
		Builder builder = null;
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		builder = webTarget.request(MediaType.APPLICATION_JSON);
		return builder.delete();
	}
	
	public Response sendDELETERequest(String commandURL, Map<String, String[]> queryParameters, MultivaluedMap<String, Object> headers) {
		Builder builder = null;
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		builder = webTarget.request(MediaType.APPLICATION_JSON);
		if(headers != null && !headers.isEmpty()) {
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		}
		return builder.delete();
	}
	
	public Response sendDELETERequest(String commandURL, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = webTarget.path(commandURL).request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.delete();
	}

	public Response sendHEADRequest(String commandURL) {
		Builder builder = webTarget.path(commandURL).request(MediaType.APPLICATION_JSON);
		return builder.head();
	}

	public Response sendHEADRequest(String commandURL, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = webTarget.path(commandURL).request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.head();
	}

	public Response sendPUTRequest(String commandURL, JsonObject entity) {
		Builder builder = webTarget.path(commandURL).request(MediaType.APPLICATION_FORM_URLENCODED);
		return builder.put(Entity.json(entity));
	}
	
	public Response sendPUTRequest(String commandURL, Entity<?> entity, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = webTarget.path(commandURL).request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.put(entity);
	}
	
	public Response sendPUTRequest(String commandURL,Map<String, String[]> queryParameters, Entity<?> entity, MultivaluedMap<String, Object> headers, String... mediaTypes) {
		Builder builder = null;
		WebTarget webTarget = this.webTarget.path(commandURL);
		if(queryParameters != null && queryParameters.size() > 0)
			for(String key : queryParameters.keySet())
				webTarget = webTarget.queryParam(key, (Object[])queryParameters.get(key));
		builder = webTarget.request(mediaTypes);
		if(headers != null && !headers.isEmpty())
			for(String headerKey : headers.keySet()) {
				builder = builder.header(headerKey, headers.getFirst(headerKey));
			}
		return builder.put(entity);
	}
}
