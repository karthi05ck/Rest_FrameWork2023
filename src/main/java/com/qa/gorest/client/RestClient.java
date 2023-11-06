package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestClient {

//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "655c32f2251ad16064b6d628745dc727fd22ebfeac5a8aff0197895f47581f79";
//	
	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
//	static {
//		specBuilder = new RequestSpecBuilder();
//	}
	
	public RestClient(Properties prop, String baseURI) {
		RestAssured.useRelaxedHTTPSValidation();
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}
	
	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
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
	public RequestSpecification createRequestSpec(boolean includeAuth) {
		//RestAssured.useRelaxedHTTPSValidation();
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		return specBuilder.build();
	}
	public RequestSpecification createRequestSpec(Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if(headersMap!= null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		if(headersMap!= null) {
			specBuilder.addHeaders(headersMap);
		}
		if(queryParams!= null) {
			specBuilder.addQueryParams(queryParams);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Object requestBody, String contentType,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
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
	public Response get(String serviceUrl, boolean includeAuth, boolean log) {
		
	
	if(log) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		.when()
		.get(serviceUrl);
	}
	return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
}
	public Response get(String serviceUrl, Map<String,String> headersMap, boolean includeAuth, boolean log) {
		
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all()
			.when()
			.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String,String> headersMap, Map<String,String> queryParams, boolean includeAuth, boolean log) {
		
		
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap,queryParams,includeAuth)).log().all()
			.when()
			.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,queryParams,includeAuth)).when().get(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String,String> headersMap, boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
					.when()
					.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
				.when()
				.post(serviceUrl);
		
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String,String> headersMap,boolean includeAuth, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
					.when()
					.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
				.when()
				.put(serviceUrl);
		
	}
	
}
