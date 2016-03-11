package org.cbioportal.cgds_java.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesFileReader {

	static final Logger logger = Logger.getLogger("PropertiesFileReader");

	public static Properties readFile(String propertiesFile) {
		Properties properties = new Properties();
		InputStream iStream;
		try {
			iStream = new FileInputStream(propertiesFile);
			properties.load(iStream);
			return properties;
		} catch (FileNotFoundException e) {
			String errorMessage = "Unable to find the properties file - " + propertiesFile;
			logger.log(Level.SEVERE, errorMessage, e);
			throw new RuntimeException(errorMessage, e);
		} catch (IOException e) {
			String errorMessage = "Unable to read the properties file - " + propertiesFile;
			logger.log(Level.SEVERE, errorMessage, e);
			throw new RuntimeException(errorMessage, e);
		}
	}

}
