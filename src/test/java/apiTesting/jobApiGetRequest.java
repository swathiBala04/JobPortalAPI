package apiTesting;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class jobApiGetRequest {


	void jobGetRequest(){


		//Passing the URI
		RestAssured.baseURI = ("https://numpyninja-joblistapi.herokuapp.com/Jobs");

		//Creating Request variable
		RequestSpecification getRequest = RestAssured.given();


		//Responsse variable
		Response getResponse = getRequest.request(Method.GET);

		String responseBody = getResponse.getBody().asString();

		System.out.println(responseBody);


		//assert status code

		int statusCode = getResponse.getStatusCode();
		System.out.println("Status code is : " + statusCode);
		Reporter.log(responseBody);
		Assert.assertEquals(statusCode,200 );

		//assert status line

		String statusLine = getResponse.getStatusLine();
		System.out.println("Status line is : " + statusLine);
		Reporter.log(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		//get session id

		String sessionID = getResponse.getSessionId();
		System.out.println("SessionID is : " + sessionID);


	}

	@Test
	void apiPostMethod() {

		//		// set the baseURI
		RestAssured.baseURI = ("https://numpyninja-joblistapi.herokuapp.com/Jobs");

		//Send request

		RequestSpecification postMethod = RestAssured.given();

		//		//create a JSON object to pass values

		JSONObject httpPost = new JSONObject();


		httpPost.put("Job Id", "7000");
		httpPost.put("Job Title", "SDET QA");
		httpPost.put("Job Location", "Austin Texas");
		httpPost.put("Job Company Name", "InfoSys");
		httpPost.put("Job Type", "Full time");
		httpPost.put("Job Posted time", "1 day ago");
		httpPost.put("Job Description", "Join Immediately");


		System.out.println(httpPost);
		System.out.println(httpPost.toString());
		given().body(httpPost.toJSONString()).when().post("https://numpyninja-joblistapi.herokuapp.com/Jobs");


		postMethod.header("Content-Type","application/json");
		postMethod.body(httpPost.toJSONString());

		//response object
		Response response = postMethod.request(Method.POST);	
		String responseBody = response.getBody().asString();
		System.out.println("Res body is" + responseBody);

		int statusCode = response.getStatusCode();
		System.out.println("Status code is : " + statusCode);
		Reporter.log(responseBody);
		Assert.assertEquals(statusCode,200 );


	}

}
