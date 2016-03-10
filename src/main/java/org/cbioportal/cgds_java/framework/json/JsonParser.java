package org.cbioportal.cgds_java.framework.json;

import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class JsonParser {
	
	private static Logger logger = Logger.getLogger("JsonParser");
	
	public static JsonObject getJsonObject(String jsonString) {
		JsonObject json = null;
		try {
			JsonReader reader = Json.createReader(new StringReader(jsonString));
			json = reader.readObject();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to parse String to JsonObject: " + e.getStackTrace().toString());
		}
		return json;
	}
	
	public static JsonObject getJsonObject(String jsonString, boolean throwException) {
		JsonObject json = null;
		try {
			JsonReader reader = Json.createReader(new StringReader(jsonString));
			json = reader.readObject();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to parse String to JsonObject: " + e.getStackTrace().toString());
			if(throwException)
				throw e;
		}
		return json;
	}
	
	public static JsonObject getJsonObject(InputStream in) {
		JsonObject json = null;
		try {
			JsonReader reader = Json.createReader(in);
			json = reader.readObject();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to read from InputStream to JsonObject: " + e.getStackTrace().toString());
		}
		return json;
	}
	
	public static JsonArray getJsonArray(String jsonString) {
		JsonArray json = null;
		try {
			JsonReader reader = Json.createReader(new StringReader(jsonString));
			json = reader.readArray();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to parse String to JsonArray: " + e.getStackTrace().toString());
		}
		return json;
	}
	
	public static JsonArray getJsonArray(String jsonString, boolean throwException) {
		JsonArray json = null;
		try {
			JsonReader reader = Json.createReader(new StringReader(jsonString));
			json = reader.readArray();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to parse String to JsonArray: " + e.getStackTrace().toString());
			if(throwException)
				throw e;
		}
		return json;
	}
	
	public static JsonArray getJsonArray(InputStream in) {
		JsonArray json = null;
		try {
			JsonReader reader = Json.createReader(in);
			json = reader.readArray();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.severe("Unable to read from InputStream to JsonArray: " + e.getStackTrace().toString());
		}
		return json;
	}
}
