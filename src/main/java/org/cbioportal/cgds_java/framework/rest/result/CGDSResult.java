package org.cbioportal.cgds_java.framework.rest.result;

import java.util.List;

import javax.json.JsonValue;

public interface CGDSResult<T> {

	public T getObject();

	public List<T> getObjects();

	public JsonValue readJsonResponse();

}
