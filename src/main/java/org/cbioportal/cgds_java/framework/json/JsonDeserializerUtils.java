package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

class JsonDeserializerUtils {

	protected static Logger logger = Logger.getLogger("JsonDeserializerUtils");

	protected static enum States {
		NON_PARSABLE, NOT_REQUIRED
	};

	protected Object getValue(Type type, JsonObject jsonObject, String jsonKey, Map<String, Type> genericTypesMap) {

		try {
			if (type instanceof ParameterizedType) {

				Type[] typeArgs = ((ParameterizedType) type).getActualTypeArguments();
				if (typeArgs[0] instanceof TypeVariable)
					typeArgs[0] = genericTypesMap.get(((TypeVariable<?>) typeArgs[0]).getName());

				if (((Class<?>) ((ParameterizedType) type).getRawType()).isAssignableFrom(JsonHashMap.class)) {

					return JsonHashMap.deserialize(jsonObject.getJsonObject(jsonKey), typeArgs[0]);

				} else if (((Class<?>) ((ParameterizedType) type).getRawType()).isAssignableFrom(JsonArrayList.class)) {

					return JsonArrayList.deserialize(jsonObject.getJsonArray(jsonKey), typeArgs[0]);

				}

			} else {
				if (type instanceof TypeVariable<?>)
					type = genericTypesMap.get(((TypeVariable<?>) type).getName());

				Class<?> cls = (Class<?>) type;
				if (cls.isAssignableFrom(String.class)) {

					return (jsonObject.getString(jsonKey));

				} else if (cls.isAssignableFrom(Integer.class)) {

					return (jsonObject.getInt(jsonKey));

				} else if (cls.isAssignableFrom(Boolean.class)) {

					return (jsonObject.getBoolean(jsonKey));

				} else if (cls.isAssignableFrom(Double.class)) {

					return (jsonObject.getJsonNumber(jsonKey).doubleValue());

				} else if (cls.isAssignableFrom(URI.class)) {

					return (new URI(jsonObject.getString(jsonKey)));

				} else if (cls.equals(JsonValue.class)) {

					return (jsonObject.get(jsonKey));

				} else if (cls.equals(JsonObject.class)) {

					return (jsonObject.getJsonObject(jsonKey));

				} else if (cls.equals(JsonArray.class)) {

					return (jsonObject.getJsonArray(jsonKey));

				} else if (cls.isAssignableFrom(Date.class)) {

					return (getDateFormat().parse(jsonObject.getString(jsonKey)));

				} else if (cls.isAnnotationPresent(JsonObjectClass.class)) {

					return (JsonDeserializer.parseJson(cls, jsonObject.getJsonObject(jsonKey)));

				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return States.NON_PARSABLE;
	}

	@SuppressWarnings("unchecked")
	protected List<?> getArrayValue(Type type, JsonArray jsonArray) {
		List<?> elementsList = null;
		try {
			if (type instanceof ParameterizedType) {

				Type[] typeArgs = ((ParameterizedType) type).getActualTypeArguments();
				if (((ParameterizedType) type).getRawType().equals((Type) JsonHashMap.class)) {

					elementsList = new ArrayList<JsonHashMap<?>>();
					for (int i = 0; i < jsonArray.size(); i++) {
						((List<JsonHashMap<?>>) elementsList)
								.add(JsonHashMap.deserialize(jsonArray.getJsonObject(i), typeArgs[0]));
					}

				} else if (((ParameterizedType) type).getRawType().equals((Type) JsonArrayList.class)) {

					elementsList = new ArrayList<JsonArrayList<?>>();
					for (int i = 0; i < jsonArray.size(); i++) {
						((List<JsonArrayList<?>>) elementsList)
								.add(JsonArrayList.deserialize(jsonArray.getJsonArray(i), typeArgs[0]));
					}

				}

			} else {
				Class<?> cls = (Class<?>) type;
				if (cls.isAssignableFrom(String.class)) {

					elementsList = new ArrayList<String>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<String>) elementsList).add(jsonArray.getString(i));

				} else if (cls.isAssignableFrom(URI.class)) {

					elementsList = new ArrayList<URI>();
					for (int i = 0; i < jsonArray.size(); i++) {
						try {
							((List<URI>) elementsList).add(new URI(jsonArray.getString(i)));
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
					}

				} else if (cls.isAssignableFrom(Integer.class)) {

					elementsList = new ArrayList<Integer>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<Integer>) elementsList).add(jsonArray.getInt(i));

				} else if (cls.isAssignableFrom(Boolean.class)) {

					elementsList = new ArrayList<Boolean>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<Boolean>) elementsList).add(jsonArray.getBoolean(i));

				} else if (cls.isAssignableFrom(Double.class)) {

					elementsList = new ArrayList<Double>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<Double>) elementsList).add(jsonArray.getJsonNumber(i).doubleValue());

				} else if (cls.isAssignableFrom(Date.class)) {

					elementsList = new ArrayList<Date>();
					for (int i = 0; i < jsonArray.size(); i++) {
						try {
							((List<Date>) elementsList).add(getDateFormat().parse(jsonArray.getString(i)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else if (cls.isAssignableFrom(JsonValue.class)) {

					elementsList = new ArrayList<JsonValue>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<JsonValue>) elementsList).add(jsonArray.get(i));

				} else if (cls.isAssignableFrom(JsonObject.class)) {

					elementsList = new ArrayList<JsonObject>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<JsonObject>) elementsList).add(jsonArray.getJsonObject(i));

				} else if (cls.isAssignableFrom(JsonArray.class)) {

					elementsList = new ArrayList<JsonArray>();
					for (int i = 0; i < jsonArray.size(); i++)
						((List<JsonArray>) elementsList).add(jsonArray.getJsonArray(i));

				} else {

					Class<?> mysteryClass = (Class<?>) cls;

					if (mysteryClass.isAnnotationPresent(JsonObjectClass.class)) {
						return JsonDeserializer.parseJson(mysteryClass, jsonArray);
					} else
						return null;
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return elementsList;
	}

	protected DateFormat getDateFormat() {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
	}
}
