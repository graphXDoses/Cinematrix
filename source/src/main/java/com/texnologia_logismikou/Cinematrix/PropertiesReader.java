package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static Properties properties;
	private static PropertiesReader instance = null;

    private PropertiesReader(String propertyFileName) throws IOException {
        InputStream is = App.class.getClassLoader()
            .getResourceAsStream(propertyFileName);
        properties = new Properties();
        properties.load(is);
    }

    public static String getProperty(String propertyName) {
    	if(instance == null)
    	{
			try {
				instance = new PropertiesReader("properties-from-pom.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        return(properties.getProperty(propertyName));
    }
}
