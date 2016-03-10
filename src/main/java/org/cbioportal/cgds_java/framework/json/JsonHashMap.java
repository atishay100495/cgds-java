package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.json.annotation.Serializer;

@SuppressWarnings("serial")
@JsonObjectClass
public class JsonHashMap<V> extends HashMap<String, V> implements Map<String, V>, Cloneable {

	public JsonHashMap() {
		super();
	}

	public JsonHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public JsonHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	public JsonHashMap(Map<String, ? extends V> m) {
		super(m);
	}
	
	public HashMap<String, V> getAsHashMap() {
		HashMap<String, V> hashMap = new HashMap<>(this);
		return hashMap;
	}
	
	@Serializer
	public JsonValue serialize() {
		JsonObjectBuilder objectBuilder = null;
		JsonSerializerUtils jsonSerializerUtils = new JsonSerializerUtils();
		objectBuilder = Json.createObjectBuilder();
		for(String key : this.keySet()) {
			objectBuilder = jsonSerializerUtils.serializeValue(objectBuilder, key, this.get(key).getClass(), this.get(key));
		}
		return objectBuilder.build();
	}
	
	@SuppressWarnings("unchecked")
	public static <V> JsonHashMap<V> deserialize(JsonObject jsonObject, Type valueType) {
		JsonHashMap<V> jsonHashMap = null;
		JsonDeserializerUtils deserializerUtils = new JsonDeserializerUtils();
		if(jsonObject != null) {
			jsonHashMap = new JsonHashMap<V>();
				for(String key : jsonObject.keySet()) {
					jsonHashMap.put(key, (V)deserializerUtils.getValue(valueType, jsonObject, key, null));
				}
		}
		return jsonHashMap;
	}
}
