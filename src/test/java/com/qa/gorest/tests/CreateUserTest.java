package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest {
	
	RestClient restClient;
	
	
	@Test
	public void getAllUsersTest() {
		
		//POST
		User user = new User("nav123",StringUtils.getRandomEmailId(), "male", "inactive" );
		restClient = new RestClient();
		int userId = restClient.post("/public/v2/users", "json", user, true)
		.then().log().all()
		.assertThat().statusCode(201)
		.extract().path("id");
		
		System.out.println("User id is :" +userId);
		
		//GET
		restClient.get("/public/v2/users/"+userId,true)
		.then().log().all()
		.assertThat().statusCode(200)
		.and().body("id", equalTo(userId));

		//Testing from home

	}

}
