package org.cbioportal.cgds_java.framework.rest.result;

import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.cbioportal.cgds_java.framework.json.JsonDeserializer;

public class CGDSRESTResult<T> extends RESTResult implements CGDSResult<T> {
	
	private static final long serialVersionUID = 1L;
	
	protected final Class<T> cls;
	protected T t = null;
	protected List<T> ts = null;
    
	public CGDSRESTResult(Class<T> cls, RESTResult restResult) {
		super(restResult.getClientResponse());
		this.cls = cls;
	}

	public CGDSRESTResult(Class<T> cls, Response response) {
		super(response);
		this.cls = cls;
	}
	
	protected CGDSRESTResult(CGDSRESTResult<T> result) {
		super(result.getClientResponse());
		this.cls = result.cls;
		this.t = result.t;
		this.ts = result.ts;
	}

	@Override
	public int getExitValue() {
		Response response = getClientResponse();
		return (response != null && response.getStatusInfo().getFamily().equals(Family.SUCCESSFUL)? 0: response.getStatus());
	}
	
	public void setObject(T t) {
		this.t = t;
	}

	@Override
	public T getObject() {
		JsonObject jsonObject = null;
		T t = null;
		if(this.t == null) {
			jsonObject = readResponse();
			if(jsonObject != null) {
				t = JsonDeserializer.parseJson(cls, jsonObject);
				this.t = t;
			}
		} else 
			t = this.t;
		return t;
	}
	
	@Override
	public List<T> getObjects() {
		JsonValue jsonValue = null;
		List<T> ts = null;
		if(this.ts == null) {
			jsonValue = readJsonResponse();
			if(jsonValue != null && (jsonValue instanceof JsonArray)) {
				ts = JsonDeserializer.parseJson(cls, (JsonArray)jsonValue);
				this.ts = ts;
			}
		} else
			ts = this.ts;
		return ts;
	}
}

