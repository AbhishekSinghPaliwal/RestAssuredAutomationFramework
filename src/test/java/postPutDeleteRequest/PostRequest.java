/**
 * @author Abhishek Singh
 * 
 */
package postPutDeleteRequest;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class PostRequest {

	@Test
	public void testPostRequest() {

		RequestSpecification request = given();
		request.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("author", "Abhishek 06");
		json.put("created", "2018-08-20T15:08:07.200");
		json.put("message", "Hello WebServices Programming6");

		request.body(json.toJSONString());
		Response response = request.post("http://localhost:8080/messenger3/webapi/messages");
		int statusCode = response.getStatusCode();
		System.out.println("Status code after msg creation is " + statusCode);
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	public void verifyDeleteRequest() {

		RequestSpecification request = given();
		// request.header("Content-Type","application/json");
		Response response = request.delete("http://localhost:8080/messenger3/webapi/messages/6");
		int statusCode = response.getStatusCode();
		System.out.println("Status code is " + statusCode);
		// Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void testPutRequest() {

		RequestSpecification request = given();
		request.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("author", "Abhishek 4copy");
		json.put("created", "2018-08-20T15:08:07.200");
		json.put("message", "Hello WebServices Programming6 copy");

		request.body(json.toJSONString());
		Response response = request.put("http://localhost:8080/messenger3/webapi/messages/6");
		int statusCode = response.getStatusCode();
		System.out.println("Status code after update msg " + statusCode);

		Assert.assertEquals(statusCode, 200);

	}

}
