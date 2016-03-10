package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.json.annotation.Serializer;

@SuppressWarnings("serial")
@JsonObjectClass
public final class JsonArrayList<E> extends ArrayList<E> implements List<E> {
	
	public JsonArrayList() {
		super();
	}

	public JsonArrayList(Collection<? extends E> c) {
		super(c);
	}

	public JsonArrayList(int initialCapacity) {
		super(initialCapacity);
	}
	
	@Serializer
	public JsonValue serialize() {
		JsonArrayBuilder arrayBuilder = null;
		JsonSerializerUtils jsonSerializerUtils = new JsonSerializerUtils();
		arrayBuilder = Json.createArrayBuilder();
		for(E e : this) {
			arrayBuilder = jsonSerializerUtils.serializeArray(arrayBuilder, e, e.getClass());
		}
		return arrayBuilder.build();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> JsonArrayList<E> deserialize(JsonArray jsonArray, Type valueType) {
		JsonArrayList<E> jsonArrayList = null;
		JsonDeserializerUtils deserializerUtils = new JsonDeserializerUtils();
		if(jsonArray != null) {
				List<?> elementsList = null;
				jsonArrayList = new JsonArrayList<E>();
				elementsList = deserializerUtils.getArrayValue(valueType, jsonArray);
				jsonArrayList.addAll((List<E>)elementsList);
		}
		return jsonArrayList;
	}
}
