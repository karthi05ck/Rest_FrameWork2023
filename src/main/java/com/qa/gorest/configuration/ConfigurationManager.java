package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

public class ConfigurationManager {
	
	private Properties prop;
	private FileInputStream ip;
	
	
	
	public Properties init_prop() {
		prop = new Properties();
		String envName = System.getProperty("env");
		try {
		if(envName==null) {
			ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
		}
		else
			switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
				break;
			case "dev":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config_dev.properties");
				break;
			case "prod":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config_prod.properties");
				break;

			default:
				System.out.println("Please pass the correct environment...");
				throw new APIFrameworkException("Please pass the valid env..The environment passed is :" +envName);
				
			}
		
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		}
		catch(IOException e) {
		
		e.printStackTrace();
		}
	return prop;
	}

//		try {
//			ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			//prop.load(ip);
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		return prop;
//	}

	
	
}
