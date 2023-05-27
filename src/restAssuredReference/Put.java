package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
public class Put {


	public static void main(String[] args) {
		//step 1 : Declare Base Uri
		

		
		
		RestAssured.baseURI="https://reqres.in/";
		//step 2: Configure Request Body
		String responseBody = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\",\r\n"
				+ "    \"updatedAt\": \"2023-05-07T18:16:19.142Z\"\r\n"
				+ "}").log().all().when().put("/api/users/2").then().log().all().extract().response().asString();
		int statusCode = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\",\r\n"
				+ "    \"updatedAt\": \"2023-05-07T18:16:19.142Z\"\r\n"
				+ "}").when().put("/api/users/2").then().extract().statusCode();
		
		System.out.println(responseBody);
		System.out.println(statusCode);
		
		//step 3 : parse the response body
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		
		
		//step 4 : validate the response body parameter
		Assert.assertEquals(statusCode, 200) ;
		Assert.assertEquals(res_name,"morpheus") ;
		Assert.assertEquals(res_job, "zion resident");
		
		}

}
