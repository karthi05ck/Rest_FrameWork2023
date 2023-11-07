package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpsStatus;

public class CircuitTest extends BaseTest {
	
	@BeforeMethod
	public void getSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	@Test
	public void getCircuitTest() {
	restClient.get(CIRCUIT_ENDPOINT, false,true)
	.then().log().all()
	.assertThat().statusCode(APIHttpsStatus.OK_200.getCode());
	}

}
