package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class CircuitTest extends BaseTest {
	
	@Test
	public void getCircuitTest() {
	restClient.get("/api/f1/2019/circuits.json", false,true)
	.then().log().all()
	.assertThat().statusCode(200);
	}

}
