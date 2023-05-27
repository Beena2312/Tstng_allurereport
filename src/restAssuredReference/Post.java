package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
public class Post {


	public static void main(String[] args) {
		//step 1 : Declare Base Uri and request body variable
		String BaseURI="https://reqres.in/";
		String RequesBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Fetch RequestBody parameters values
		
		JsonPath jsprequest=new JsonPath(RequesBody);
	    String req_name=jsprequest.getString("name");
		String req_job=jsprequest.getString("job");
		//Generate date in format as shown and received in response
		LocalDateTime Date=LocalDateTime.now();
		String exp_Date=Date.toString().substring(0,11);

		RestAssured.baseURI="https://reqres.in/";
		//step 2: Configure Request Body
		String requestBody = given().header("Content-Type","application/json").body(RequesBody).when().post("/api/users").then().log().all().extract().response().asString();
		int statusCode = given().header("Content-Type","application/json").body(RequesBody).when().post("/api/users").then().extract().statusCode();
		
		System.out.println(requestBody);
		
		
		//step 3 : parse the response body
		JsonPath jsp = new JsonPath(requestBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
		res_createdAt=res_createdAt.substring(0,11);
		
		//step 4 : validate the response body parameter
		Assert.assertEquals(statusCode,201) ;
		Assert.assertEquals(res_name,req_name) ;
		Assert.assertEquals(req_job,req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt,exp_Date);
	
		
		
		
              
	
	}

}
