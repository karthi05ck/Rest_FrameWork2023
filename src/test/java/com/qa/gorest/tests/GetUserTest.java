package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;

public class GetUserTest extends BaseTest {
	
	//RestClient restClient;
	
	@BeforeMethod
	public void getSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void getAllUsersTst() {
//		restClient =new RestClient();
		restClient.get(GOREST_ENDPOINT, true, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
	@Test
	public void getUserTest() {
//		restClient =new RestClient();
		restClient.get(GOREST_ENDPOINT+"/5676865", true, true)
		.then().log().all()
		.assertThat().statusCode(200)
		.and().body("id", equalTo(5676865));
		
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
//		restClient =new RestClient();
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "naveen");
		queryParams.put("status", "inactive");
		restClient.get(GOREST_ENDPOINT+"/",null, queryParams,true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpsStatus.OK_200.getCode());
	}

}
