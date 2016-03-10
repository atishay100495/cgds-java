package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.lang.model.type.NullType;

import org.cbioportal.cgds_java.framework.json.annotation.JsonField;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

public abstract class AbstractJsonDeserializer extends JsonDeserializerUtils {
	
	public <T> T parseJson(Class<T> cl, T element, JsonObject json) {
		Field[] classFields = null;
		Object object = null;
		boolean accessible = false;
		ParameterizedType pType = null;
		Type[] typeArgs = null;
		Class<?> collectionType = null;
		Map<String, Object> unparsableFieldValues = new HashMap<String, Object>();
		Field genericField = null;
		Map<String, Field> jsonFields = null;
		Set<String> jsonKeySet = json.keySet();
		Map<String, Type> genericTypesMap = null;

		classFields = getFields(cl);
		genericTypesMap = resolveGenericTypeVariables(cl);
		
		jsonFields = getJsonFields(classFields);
		genericField = getUnparsedKeyField(classFields);

		for (String jsonKey : jsonKeySet) {
			Field field = jsonFields.get(jsonKey);
			if(field == null) {
				unparsableFieldValues.put(jsonKey, json.get(jsonKey));
				continue;
			}

			collectionType = field.getAnnotation(JsonField.class).collectionType();
			accessible = field.isAccessible();
			if(!accessible) {
				try {
					field.setAccessible(true);
					accessible = true;
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}

			if (!json.isNull(jsonKey) && accessible) {
				try {
					object = objectParser(json, field.getType(), jsonKey);
					if (object != null) {
						if((object instanceof States) && object.equals(States.NON_PARSABLE)) {
							unparsableFieldValues.put(jsonKey, json.get(jsonKey));
							continue;
						}
						field.set(element, object);
						continue;
					}

					if(collectionType.equals(null) || collectionType.equals(NullType.class)) {
						Object value = null;
						
						if ((value = getValue(field.getGenericType(), json, jsonKey, genericTypesMap)) != null && !value.equals(States.NON_PARSABLE)) {

							field.set(element, value);

						} else {
							
							unparsableFieldValues.put(jsonKey, json.get(jsonKey));
						}
					} else if(collectionType.equals(List.class)) {

						pType = (ParameterizedType) field.getGenericType();
						if (pType.getRawType().equals((Type) List.class)) {

							List<?> elementsList = null;
							typeArgs = pType.getActualTypeArguments();
							elementsList = createListFromJson(typeArgs[0], json.getJsonArray(jsonKey));
							if(elementsList != null)
								field.set(element,elementsList);
							else
								unparsableFieldValues.put(jsonKey, json.get(jsonKey));
						}

					} else if(collectionType.equals(Map.class)) {

						pType = (ParameterizedType) field.getGenericType();
						if (pType.getRawType().equals((Type)Map.class)) {
							Map<?, ?> elementsMap = null;
							typeArgs = pType.getActualTypeArguments();
							elementsMap = createMapFromJson(json, jsonKey, (Class<?>)typeArgs[0], (Class<?>) typeArgs[1], genericTypesMap);
							if(elementsMap != null)
								field.set(element, elementsMap);
							else
								unparsableFieldValues.put(jsonKey, json.get(jsonKey));
						}
					}

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if(genericField != null) {
				if(((ParameterizedType)genericField.getGenericType()).getRawType().equals(Map.class)) {
					if(!genericField.isAccessible())
						genericField.setAccessible(true);
					genericField.set(element, unparsableFieldValues);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return element;
	}
	
	protected Map<String, Type> resolveGenericTypeVariables(final Class<?> _cls) {
		Map<String, Type> typesMap = new HashMap<>();
		Type[] typeArguemnts = null;
		Type classType = _cls.getGenericSuperclass();
		while (!classType.equals(Object.class)) {
			if(!(classType instanceof ParameterizedType)) {
				classType = ((Class<?>)classType).getGenericSuperclass();
				continue;
			}
			typeArguemnts = ((ParameterizedType)classType).getActualTypeArguments();
			for(int i = 0; i < typeArguemnts.length; i++) {
				if(typeArguemnts[i] instanceof TypeVariable<?>) {
					if(typesMap.containsKey(((TypeVariable<?>)typeArguemnts[i]).getName())) {
						typesMap.put(((TypeVariable<?>)typeArguemnts[i]).getName(), typesMap.get(((TypeVariable<?>)typeArguemnts[i]).getName()));
					}
				} else {
					typesMap.put(((Class<?>)((ParameterizedType)classType).getRawType()).getTypeParameters()[i].getName(), typeArguemnts[i]);
				}
			}
			classType = ((Class<?>)((ParameterizedType)classType).getRawType()).getGenericSuperclass();
		}
		
		if(typesMap.isEmpty())
			return null;
		return typesMap;
	}

	public <T> T parseJson(Class<T> cl, JsonObject json) {
		T element = null;
		try {
			element = cl.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		return parseJson(cl, element, json);
	}

	private List<?> createListFromJson(Type type, JsonArray jsonArray) throws Exception {
		List<?> elementsList = null;
		
		elementsList = getArrayValue(type, jsonArray);

		return elementsList;
	}

	@SuppressWarnings("unchecked")
	private <T> Map<?, T> createMapFromJson(JsonObject json, String fieldName, Class<?> keyType, Type valueType, Map<String, Type> genericTypesMap) throws Exception {
		Map<String, T> elementsMap = null;
		Set<String> jsonKeys = null;
		JsonObject jsonObject = json.getJsonObject(fieldName);
		if (keyType.isAssignableFrom(String.class)) {
			jsonKeys = jsonObject.keySet();
			elementsMap = new HashMap<String, T>();
			for(String key : jsonKeys) {
				if(!jsonObject.isNull(key)) {
					elementsMap.put(key, (T)(getValue(valueType, jsonObject, key, genericTypesMap)));
				}
			}
		}
		return elementsMap;
	}

	protected abstract Object objectParser(JsonObject json, Class<?> type, String fieldName);
	
	private Map<String, Field> getJsonFields(Field[] fields) {
		Map<String, Field> jsonFields = new HashMap<String, Field>();
		String jsonKey = null;
		if(fields != null) {
			for(Field field : fields) {
				if(field.isAnnotationPresent(JsonField.class)) {
					jsonKey = field.getAnnotation(JsonField.class).value();
					jsonFields.put(jsonKey, field);
				}
			}
		}
		return jsonFields;
	}
	
	private Field getUnparsedKeyField(Field[] fields) {
		Field resultField = null;
		if(fields != null) {
			for(Field field : fields) {
				if(field.isAnnotationPresent(JsonObjectClass.GenericField.class) && ((ParameterizedType) field.getGenericType()).getRawType().equals((Type)Map.class)) {
					resultField = field;
				}
			}
		}
		return resultField;
	}
	
	private <T> Field[] getFields(Class<T> cl) {
		List<Field> fields = new ArrayList<Field>();
		Field[] fieldsArray = new Field[1], tempFieldArray = null;
		Class<?> superClass = cl.getSuperclass();
		for(Field field : cl.getDeclaredFields())
			if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()))
				fields.add(field);
		if(superClass != null) {
			tempFieldArray = getFields(superClass);
			for(Field field : tempFieldArray)
				if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()))
					fields.add(field);
		}
		if(fields.isEmpty())
			return (new Field[0]);
		return fields.toArray(fieldsArray);
	}
}
