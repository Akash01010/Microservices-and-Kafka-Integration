package com.sapient.ops.utility;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	public static Properties PROPERTIES;
	public static InputStream inputStream=null;
	
	static {
		try {
			PROPERTIES = new Properties();
			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			InputStream inputStream = contextClassLoader.getResourceAsStream("configuration.properties");
			if (inputStream != null) {
				PROPERTIES.load(inputStream);
			  
			} else {
			  System.out.println("Properties file not found!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
