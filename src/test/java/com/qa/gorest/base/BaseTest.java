package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		
		
		config = new ConfigurationManager();
		prop = config.init_prop();
		//String baseURI = prop.getProperty("baseURI");
		restClient = new RestClient(prop, baseURI);
		
	}
}
