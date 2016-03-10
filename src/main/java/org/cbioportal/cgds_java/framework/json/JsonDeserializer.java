package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.cbioportal.cgds_java.framework.json.annotation.Deserializer;
import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;

public class JsonDeserializer {

	protected static AbstractJsonDeserializer jsonDeserializer = new AbstractJsonDeserializer() {
		@Override
		protected Object objectParser(JsonObject json, Class<?> type, String fieldName) {
			return null;
		}
	};
	
	public static <T> T parseJson(Class<T> cl, T t, JsonObject jsonObject) {
		if(cl.isAnnotationPresent(JsonObjectClass.class)) {
			Method[] classMethods = null;
			classMethods = cl.getDeclaredMethods();
			for (Method method : classMethods) {
				Class<?>[] methodParameters = null;
				methodParameters = method.getParameterTypes();
				if (method.isAnnotationPresent(Deserializer.class)
						&& cl.isAssignableFrom(method.getReturnType())
						&& Modifier.isPublic(method.getModifiers())
						&& Modifier.isStatic(method.getModifiers())
						&& methodParameters.length == 1
						&& methodParameters[0].isAssignableFrom(JsonObject.class)) {

					try {
						return (T)method.invoke(t, jsonObject);
					} catch (Exception e) {
					}
				}
			}
			return jsonDeserializer.parseJson(cl, t, jsonObject);
		} else {
			throw (new RuntimeException("The class is not annotated with annotation 'JsonObjectClass' !!!"));
		}
	}
	
	public static <T> T parseJson(Class<T> cl, JsonObject jsonObject) {
		if(cl.isAnnotationPresent(JsonObjectClass.class)) {
			Method[] classMethods = null;
			classMethods = cl.getDeclaredMethods();
			for (Method method : classMethods) {
				Class<?>[] methodParameters = null;
				methodParameters = method.getParameterTypes();
				if (method.isAnnotationPresent(Deserializer.class)
						&& cl.isAssignableFrom(method.getReturnType())
						&& Modifier.isPublic(method.getModifiers())
						&& Modifier.isStatic(method.getModifiers())
						&& methodParameters.length == 1
						&& methodParameters[0].isAssignableFrom(JsonObject.class)) {

					try {
						return (T)method.invoke(null, jsonObject);
					} catch (Exception e) {
					}
				}
			}
			return jsonDeserializer.parseJson(cl, jsonObject);
		} else {
			throw (new RuntimeException("The class is not annotated with annotation 'JsonObjectClass' !!!"));
		}
	}
	
	public static <T> List<T> parseJson(Class<T> cl, JsonArray jsonArray) {
		List<T> elements = new ArrayList<T>();
		JsonObject jsonObject;
		if(cl.isAnnotationPresent(JsonObjectClass.class)) {
			Method[] classMethods = null;
			classMethods = cl.getDeclaredMethods();
			for (Method method : classMethods) {
				Class<?>[] methodParameters = null;
				methodParameters = method.getParameterTypes();
				if (method.isAnnotationPresent(Deserializer.class)
						&& List.class.isAssignableFrom(method.getReturnType())
						&& Modifier.isPublic(method.getModifiers())
						&& Modifier.isStatic(method.getModifiers())
						&& methodParameters.length == 1
						&& methodParameters[0].isAssignableFrom(JsonArray.class)) {

					try {
						return (List<T>)method.invoke(null, jsonArray);
					} catch (Exception e) {
					}
				}
			}
			for(int i = 0; i < jsonArray.size(); i++) {
				jsonObject = jsonArray.getJsonObject(i);
				elements.add(JsonDeserializer.parseJson(cl, jsonObject));
			}
			return elements;
		} else {
			throw (new RuntimeException("The class is not annotated with annotation 'JsonObjectClass' !!!"));
		}
	}
}
