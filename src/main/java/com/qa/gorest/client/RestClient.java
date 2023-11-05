package com.qa.gorest.client;

import java.util.Map;

import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestClient {

	private static final String BASE_URI = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79";
	
	private static RequestSpecBuilder specBuilder;
	
	static {
		specBuilder = new RequestSpecBuilder();
	}
	
	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
		}
	
	private void setRequestContentType(String contentType) {
		switch(contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("Please pass the right content Type:" );
			throw new APIFrameworkException("Invalid content type.....");
		}
		
	}
	public RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		return specBuilder.build();
	}
	public RequestSpecification createRequestSpec(Map<String, String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headersMap!= null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headersMap!= null) {
			specBuilder.addHeaders(headersMap);
		}
		if(queryParams!= null) {
			specBuilder.addQueryParams(queryParams);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	//Http Method Utils
	public Response get(String serviceUrl, boolean log) {
		
	
	if(log) {
		return RestAssured.given(createRequestSpec()).log().all()
		.when()
		.get(serviceUrl);
	}
	return RestAssured.given(createRequestSpec()).when().get(serviceUrl);
}
	public Response get(String serviceUrl, Map<String,String> headersMap, boolean log) {
		
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all()
			.when()
			.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String,String> headersMap, Map<String,String> queryParams, boolean log) {
		
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap,queryParams)).log().all()
			.when()
			.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,queryParams)).when().get(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String,String> headersMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
					.when()
					.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String,String> headersMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap))
				.when()
				.put(serviceUrl);
		
	}
	
}
