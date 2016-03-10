package org.cbioportal.cgds_java.framework.rest.result;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.cbioportal.cgds_java.framework.json.JsonParser;


public class RESTResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected static Logger logger = Logger.getLogger("RESTResult");
	
    private String output;
    private String error;
    private int exitValue;
    private boolean wasTimedOut = false;
    private Response clientResponse;
    private JsonValue jsonObject;
    
    public RESTResult() {
    	
    }
    
    public RESTResult(Response clientResponse) {
    	setClientResponse(clientResponse);
    	getClientResponse().bufferEntity();
    	StatusType status = null;
    	String contentType = null;
    	status = clientResponse.getStatusInfo();
    	this.exitValue = (status.getFamily().equals(Family.SUCCESSFUL)? 0 : 1);
    	if(status.getFamily().equals(Family.SUCCESSFUL)) {
    		if(status.getStatusCode() != 204) {
    			if((contentType = clientResponse.getHeaderString("Content-Type")) == null || contentType.contains("json")) {
            		try {
            			jsonObject = readJson(clientResponse.readEntity(String.class));
    					this.output = jsonObject.toString();
    				} catch (Exception e) {
    	    			if(e instanceof IllegalStateException) {
    						jsonObject = readJson((String)clientResponse.getEntity());
    						this.output = jsonObject.toString();
    	    			} else {
    						jsonObject = null;
    		    			this.output = "";
    	    			}
    	    		}
    			} else {
    				jsonObject = null;
    				try {
						output = clientResponse.readEntity(String.class);
					} catch (IllegalStateException e) {
						output = ((String)clientResponse.getEntity());
					} catch (Exception e) {
		    			this.output = "";
					}
    			}
    		} else {
    			jsonObject = null;
    			this.output = "";
    		}
    	} else {
    		this.error = status.getReasonPhrase();
    		try {
    			jsonObject = readJson(clientResponse.readEntity(String.class));
				this.error = this.error + jsonObject.toString();
			} catch (Exception e) {
    			if(e instanceof IllegalStateException) {
    				jsonObject = readJson((String)clientResponse.getEntity());
					this.error = this.error + jsonObject.toString();
    			} else {
					jsonObject = null;
    			}
    		}
    	}
    }
    
    private static JsonValue readJson(String responseString) {
    	JsonValue jsonValue = null;
		try {
			try {
				jsonValue = JsonParser.getJsonObject(responseString, true);
			} catch (JsonException e) {
				try {
					jsonValue = JsonParser.getJsonArray(responseString, true);
				} catch (Exception e1) {
					throw e1;
				}
			}
		} catch(JsonParsingException pe1) {
			pe1.printStackTrace();
			jsonValue = Json.createObjectBuilder().build();
		} catch (Throwable e) {
			e.printStackTrace();
			jsonValue = Json.createObjectBuilder().build();
		}
		return jsonValue;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String outputStream) {
        this.output = outputStream;
    }

    public String getError() {
        return error;
    }

    public void setError(String errorStream) {
        this.error = errorStream;
    }

    public int getExitValue() {
        return exitValue;
    }
    
    public boolean wasTimedOut() {
    	return this.wasTimedOut;
    }

    public void setCommandTimedOut(boolean wasTimedOut) {
    	this.wasTimedOut = wasTimedOut;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

	public Response getClientResponse() {
		return clientResponse;
	}

	public void setClientResponse(Response clientResponse) {
		this.clientResponse = clientResponse;
	}
	
	public JsonObject readResponse() {
		if(jsonObject instanceof JsonObject)
			return (JsonObject)jsonObject;
		return null;
	}
	
	public JsonValue readJsonResponse() {
		return jsonObject;
	}
}

