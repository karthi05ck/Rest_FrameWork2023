package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {
	
	//RestClient restClient;
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] getUserTestData() {
		
		return new Object[][] {
			{"kar", "male", "inactive"},
			{"kar12", "female", "active"},
			{"sevb", "male", "active"},
			{"se82b", "female", "active"},
		};
	}
	
	@Test(dataProvider="getUserTestData")
	public void getAllUsersTest(String name, String gender, String status) {
		
		//POST
		User user = new User(name,StringUtils.getRandomEmailId(), gender, status );
		//restClient = new RestClient();
		int userId = restClient.post("/public/v2/users", "json", user, true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpsStatus.CREATED_201.getCode())
		.extract().path("id");
		
		System.out.println("User id is :" +userId);
		
		//GET - create one more object to avoid error
		RestClient getClient = new RestClient(prop, baseURI);
		getClient.get("/public/v2/users/"+userId,true,true)
		.then().log().all()
		.assertThat().statusCode(200)
		.and().body("id", equalTo(userId));

		

	}

}
