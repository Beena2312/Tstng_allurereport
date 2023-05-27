package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
public class Delete {


	public static void main(String[] args) {
		//step 1 : Declare Base Uri
		

		RestAssured.baseURI="https://reqres.in/";
		//step 2: Configure Request Body
		String responseBody = given().header("Content-Type","application/json")
				.log().all().when().delete("/api/users/2").then().log().all().extract().response().asString();
		int statusCode = given().header("Content-Type","application/json")
				.when().delete("/api/users/2").then().extract().statusCode();
		
		
		System.out.println(statusCode);
		
		}

}
