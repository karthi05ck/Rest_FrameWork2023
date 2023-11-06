package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class GetUserTest extends BaseTest {
	
	//RestClient restClient;
	
	@Test
	public void getAllUsersTst() {
//		restClient =new RestClient();
		restClient.get("/public/v2/users", true, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
	@Test
	public void getUserTest() {
//		restClient =new RestClient();
		restClient.get("/public/v2/users/5672956", true, true)
		.then().log().all()
		.assertThat().statusCode(200)
		.and().body("id", equalTo(5672956));
		
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
//		restClient =new RestClient();
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "naveen");
		queryParams.put("status", "inactive");
		restClient.get("/public/v2/users/",null, queryParams,true, true)
		.then().log().all()
		.assertThat().statusCode(200);
	}

}
