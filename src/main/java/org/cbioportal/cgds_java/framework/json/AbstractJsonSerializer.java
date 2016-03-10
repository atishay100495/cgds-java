package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.lang.model.type.NullType;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

public abstract class AbstractJsonSerializer extends JsonSerializerUtils {
	
	public static boolean ignoreRedundantFields = false;
	
	public static interface States extends JsonValue {

		JsonValue NON_PARSABLE = new JsonValue() {
			@Override
			public ValueType getValueType() {
				return ValueType.NULL;
			}
		};
		JsonValue NOT_REQUIRED = new JsonValue() {
			@Override
			public ValueType getValueType() {
				return ValueType.NULL;
			}
		};
	};
	
	public static void setIgnoreRedundantFields(boolean ignoreRedundantFields) {
		AbstractJsonSerializer.ignoreRedundantFields = ignoreRedundantFields;
	}
	
	@SuppressWarnings("unchecked")
	public <T> JsonValue createJson(Class<T> cl, T object) {
		Field[] classFields = null;
		String jsonFieldName = null;
		boolean accessible = false;
		ParameterizedType pType = null;
		JsonValue jsonValue = null;
		JsonObjectBuilder objectBuilder = null;
		Object fieldObject = null;
		Class<?> collectionType = null;
		Type[] typeArgs = null;
		
		cl = ((Class<T>) object.getClass());
		objectBuilder = Json.createObjectBuilder();
		
		classFields = getFields(cl);
		for (Field field : classFields) {

			if (field.isAnnotationPresent(JsonField.class) && !(((JsonField)field.getAnnotation(JsonField.class)).redundant() && ignoreRedundantFields)) {

				jsonFieldName = field.getAnnotation(JsonField.class).value();
				accessible = field.isAccessible();
				collectionType = field.getAnnotation(JsonField.class).collectionType();

				if(!accessible) {
					try {
						field.setAccessible(true);
						accessible = true;
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
				
				try {
					fieldObject = field.get(object);
					if(accessible) {
						jsonValue = objectSerializer(jsonFieldName);
						if (jsonValue != null) {
							if(jsonValue.equals(States.NOT_REQUIRED))
								continue;
							objectBuilder = objectBuilder.add(jsonFieldName, jsonValue);
							continue;
						}
					}
					if (accessible && fieldObject != null && !fieldObject.equals(null)) {
						
						if(collectionType == null || collectionType.equals(NullType.class)) {
							
							Class<?> fieldClass = field.getType();
							objectBuilder = serializeValue(objectBuilder, jsonFieldName, fieldClass, fieldObject);
							
						} else if(collectionType.equals(List.class)) {
							
							pType = (ParameterizedType) field.getGenericType();
							typeArgs = pType.getActualTypeArguments();
							objectBuilder = objectBuilder.add(jsonFieldName, serializeList(fieldObject, typeArgs[0]));
							
						} else if(collectionType.equals(Map.class)) {
							
							pType = (ParameterizedType) field.getGenericType();
							typeArgs = pType.getActualTypeArguments();
							objectBuilder = objectBuilder.add(jsonFieldName, serializeMap(fieldObject, (Class<?>)typeArgs[0], typeArgs[1]));
						}
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (field.isAnnotationPresent(JsonObjectClass.GenericField.class)) {
				Set<String> keySet = null;
				Map<String, ?> unparsableFieldValues = null;
				try {
					pType = (ParameterizedType)field.getGenericType();
					if(pType.getRawType().equals(Map.class)) {
						typeArgs = pType.getActualTypeArguments();
						
						if(!field.isAccessible())
							field.setAccessible(true);
						unparsableFieldValues = (Map<String, ?>)field.get(object);
						if(unparsableFieldValues != null && !unparsableFieldValues.isEmpty()) {
							keySet = unparsableFieldValues.keySet();
							for(String key : keySet) {
								if(String.class.isAssignableFrom((Class<?>)typeArgs[1]))
									objectBuilder = objectBuilder.add(key, (String)unparsableFieldValues.get(key));
								else
									objectBuilder = objectBuilder.add(key, (JsonValue)unparsableFieldValues.get(key));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return objectBuilder.build();
	}
	
	private JsonArrayBuilder serializeList(Object fieldObject, Type type) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(Object object : ((List<?>)fieldObject))
			jsonArrayBuilder = serializeArray(jsonArrayBuilder, object, type);

		return jsonArrayBuilder;
	}
	
	@SuppressWarnings("unchecked")
	private JsonObjectBuilder serializeMap(Object fieldObject, Class<?> keyType, Type type) {
		Set<String> keySet = null;
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		
		if(String.class.isAssignableFrom(keyType)) {
			keySet = ((Map<String,?>)fieldObject).keySet();
			try {
				for (String key : keySet) {
					jsonObjectBuilder = serializeValue(jsonObjectBuilder, key, type, ((Map<String,?>)fieldObject).get(key));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObjectBuilder;
	}
	
	protected abstract JsonValue objectSerializer(String jsonFieldName);
	
	private <T> Field[] getFields(Class<T> cl) {
		List<Field> fields = new ArrayList<Field>();
		Field[] fieldsArray = new Field[1], tempFieldArray = null;
		Class<?> superClass = cl.getSuperclass();
		for(Field field : cl.getDeclaredFields())
			fields.add(field);
		if(superClass != null) {
			tempFieldArray = getFields(superClass);
			for(Field field : tempFieldArray)
				fields.add(field);
		}
		if(fields.isEmpty())
			return (new Field[0]);
		return fields.toArray(fieldsArray);
	}
}
