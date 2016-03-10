package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

class JsonSerializerUtils {

	protected static Logger logger = Logger.getLogger("JsonSerializerUtils");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected JsonObjectBuilder serializeValue(JsonObjectBuilder objectBuilder, String jsonFieldName, Type fieldType, Object object) {
		JsonValue jsonValue = null;
		if(fieldType instanceof ParameterizedType) {
			
			if(((ParameterizedType)fieldType).getRawType().equals((Type)JsonHashMap.class)) {
				objectBuilder = objectBuilder.add(jsonFieldName, ((JsonHashMap<?>)object).serialize());
			} else if(((ParameterizedType)fieldType).getRawType().equals((Type)JsonArrayList.class)) {
				objectBuilder = objectBuilder.add(jsonFieldName, ((JsonArrayList<?>)object).serialize());
			}
			
		} else {
			Class<?> fieldTypeClass = (Class<?>)fieldType;
			if (fieldTypeClass.isAssignableFrom(String.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (String) object);

			} else if (fieldTypeClass.isAssignableFrom(Integer.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (Integer)object);

			} else if (fieldTypeClass.isAssignableFrom(Boolean.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (Boolean)object);

			} else if (fieldTypeClass.isAssignableFrom(Double.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (Double)object);

			} else if (fieldTypeClass.isAssignableFrom(URI.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, ((URI) object).toASCIIString());

			} else if (fieldTypeClass.equals(JsonValue.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (JsonValue) object);

			} else if (fieldTypeClass.equals(JsonObject.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (JsonObject) object);

			} else if (fieldTypeClass.equals(JsonArray.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, (JsonArray)object);

			} else if (fieldTypeClass.isAssignableFrom(Date.class)) {

				objectBuilder = objectBuilder.add(jsonFieldName, getDateFormat().format((Date) object));

			} else {

				if (fieldTypeClass.isAnnotationPresent(JsonObjectClass.class)) {
					jsonValue = JsonSerializer.serialize((Class)fieldTypeClass, fieldTypeClass.cast(object));
					objectBuilder = objectBuilder.add(jsonFieldName, jsonValue);
				}
			}
		}
		

		return objectBuilder;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected JsonArrayBuilder serializeArray(JsonArrayBuilder jsonArrayBuilder, Object object, Type type) {
		JsonValue jsonValue = null;
		
		if(type instanceof ParameterizedType) {
			if(((ParameterizedType)type).getRawType().equals((Type)JsonHashMap.class)) {
				
					jsonArrayBuilder = jsonArrayBuilder.add(((JsonHashMap<?>)object).serialize());
				
			} else if(((ParameterizedType)type).getRawType().equals((Type)JsonArrayList.class)) {
				
					jsonArrayBuilder = jsonArrayBuilder.add(((JsonArrayList<?>)object).serialize());
				
			}
		} else {
			Class<?> typeClass = (Class<?>)type;
			try {
				if (typeClass.isAssignableFrom(String.class)) {
					
						jsonArrayBuilder = jsonArrayBuilder.add((String)object);

				} else if (typeClass.isAssignableFrom(URI.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add(((URI)object).toASCIIString());

				} else if (typeClass.isAssignableFrom(Integer.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((Integer)object);

				} else if (typeClass.isAssignableFrom(Boolean.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((Boolean)object);

				} else if (typeClass.isAssignableFrom(Double.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((Double)object);

				} else if (typeClass.isAssignableFrom(Date.class)) {
					
						jsonArrayBuilder = jsonArrayBuilder.add(getDateFormat().format((Date)object));

				} else if (typeClass.isAssignableFrom(JsonValue.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((JsonValue)object);

				} else if (typeClass.isAssignableFrom(JsonObject.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((JsonObject)object);

				} else if (typeClass.isAssignableFrom(JsonArray.class)) {

						jsonArrayBuilder = jsonArrayBuilder.add((JsonArray)object);

				} else {

					Class mysteryClass = typeClass;
					if (mysteryClass.isAnnotationPresent(JsonObjectClass.class)) {
						
							jsonValue = JsonSerializer.serialize(mysteryClass, mysteryClass.cast(object));
							jsonArrayBuilder = jsonArrayBuilder.add(jsonValue);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArrayBuilder;
	}
	

	protected DateFormat getDateFormat() {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
	}
}
