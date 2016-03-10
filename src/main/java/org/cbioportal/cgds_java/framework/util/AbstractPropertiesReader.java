package org.cbioportal.cgds_java.framework.util;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AbstractPropertiesReader {

	protected final Logger logger;
	private Properties properties;
	protected final String PROPERTIES_FILE;
	protected static final Properties overrideProperties;
	
	static {
		String overridePropertiesFile = System.getProperty("override.properties", null);
		if(overridePropertiesFile != null && !overridePropertiesFile.equals("")) {
			overrideProperties = new Properties(PropertiesFileReader.readFile(overridePropertiesFile));
		} else {
			overrideProperties = null;
		}
	}

	public AbstractPropertiesReader(String propertiesFile, Logger logger) {
		this.logger = logger;
		this.PROPERTIES_FILE = propertiesFile;
		this.properties = new Properties(PropertiesFileReader.readFile(propertiesFile));
		if(overrideProperties != null && !overrideProperties.isEmpty())
			this.properties.putAll(overrideProperties);
	}
	
	protected static String getSystemProperty(String propertyName, String defaultValue) {
		return System.getProperty(propertyName, defaultValue);
	}

	protected String getSystemProperty(String propertyName) {
		String systemProperty = System.getProperty(propertyName);
		if (systemProperty == null) {
			logger.severe("System Property - " + propertyName + " not set !!");
			throw new RuntimeException("System Property - " + propertyName
					+ " not set !!");
		}
		return systemProperty;
	}

	protected String getProperty(String property) {
		String value = properties.getProperty(property);
		if (value == null) {
			logger.log(Level.FINE, "Property - " + property + " not defined in file - " + PROPERTIES_FILE);
		}
		return value;
	}

	protected String getProperty(String property, String defaultValue) {
		String value = properties.getProperty(property, defaultValue);
		return value;
	}
	
	public final Properties getProperties() {
		return properties;
	}
	
	public final Properties getProperties(String prefix) {
		String key = null;
		if(prefix != null && !prefix.isEmpty()) {
			Properties properties = new Properties();
			Enumeration<?> propertyNames = this.properties.propertyNames();
			while(propertyNames.hasMoreElements())
				if((key = (String)propertyNames.nextElement()).startsWith(prefix + "."))
					properties.setProperty(key, getProperty(key));
			return properties;
		} else
			return properties;
	}
	
	public static class Properties extends java.util.Properties {
		
		private static final long serialVersionUID = 1L;
		private static final Pattern REPLACE_PATTERN = Pattern.compile("\\$\\{(?<propertyName>[\\w\\.-]+)\\}");
		
		public Properties(java.util.Properties properties) {
			super(properties);
		}
		
		public Properties() {
			super();
		}

		@Override
		public String getProperty(String key) {
			Matcher matcher = null;
			String value = super.getProperty(key);
			while (value != null && (matcher = REPLACE_PATTERN.matcher(value)).find()) {
				value = matcher.replaceFirst(getProperty(matcher.group("propertyName")));
			}
			return value;
		}

		@Override
		public String getProperty(String key, String defaultValue) {
			Matcher matcher = null;
			String value = super.getProperty(key, defaultValue);
			while (value != null && (matcher = REPLACE_PATTERN.matcher(value)).find()) {
				value = matcher.replaceFirst(getProperty(matcher.group("propertyName")));
			}
			return value;
		}
	}
}
