package org.cbioportal.cgds_java.framework.resource;

import java.util.Map;
import java.util.logging.Logger;

import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

@JsonObjectClass
public abstract class AbstractResource {
	
	protected static Logger logger = Logger.getLogger("AbstractResource");
	
	@JsonObjectClass.GenericField protected Map<String, JsonValue> unparsedObjectsMap;

}
