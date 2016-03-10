package org.cbioportal.cgds_java.framework.json;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;

import org.cbioportal.cgds_java.framework.json.annotation.JsonObjectClass;
import org.cbioportal.cgds_java.framework.json.annotation.Serializer;

public class JsonSerializer {

	protected static AbstractJsonSerializer abstractJsonSerializer = new AbstractJsonSerializer() {
		@Override
		protected JsonValue objectSerializer(String jsonFieldName) {
			return null;
		}
	};
	
	public static <T> JsonValue serialize(Class<T> cl, T t) {
		if(cl.isAnnotationPresent(JsonObjectClass.class)) {
			Method[] classMethods = null;
			classMethods = cl.getMethods();
			for (Method method : classMethods) {
				Class<?>[] methodParameters = null;
				methodParameters = method.getParameterTypes();
				if (method.isAnnotationPresent(Serializer.class)
						&& JsonValue.class.isAssignableFrom(method.getReturnType())
						&& Modifier.isPublic(method.getModifiers())
						&& methodParameters.length == 0) {
					try {
						method.setAccessible(true);
						return (JsonValue)method.invoke(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			return abstractJsonSerializer.createJson(cl, t);
		} else {
			throw (new RuntimeException("The class is not annotated with annotation 'JsonObjectClass' !!!"));
		}
	}
	
	public static <T> JsonValue serialize(Class<T> cl, List<T> listT) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		if(listT != null && !listT.isEmpty()) 
			for(T t : listT)
				jsonArrayBuilder.add(serialize(cl, t));
		
		return jsonArrayBuilder.build();
	}
}
