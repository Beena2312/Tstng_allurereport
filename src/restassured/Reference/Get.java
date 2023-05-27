package restassured.Reference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class Get {


	public static void main(String[] args) {
		    //TODO Auto-generated method stub
		//step 1 : Declare Base Uri
		

		RestAssured.baseURI="https://reqres.in/";
		//step 2: Configure Request Body
		String responseBody = given().header("Content-Type","application/json").when().get("/api/users?page=2")
			.then().log().all().extract().response().asString();
		int statusCode = given().header("Content-Type","application/json")
				.when().get("/api/users?page=2").then().extract().statusCode();
	
		
		System.out.println(responseBody);
		
		// Step 3 : expected result
		int id []= {7,8,9,10,11,12};
	String[] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
	 	
		//step 3 : parse the response body
		JsonPath jspresponse= new JsonPath(responseBody);
		
		int count = jspresponse.getList("data").size();
		//int count = id.lenghth;
		System.out.println(count);
		//validate each object
		for(int i=0;i<count; i++)
		{
		//Step 4 : expected result
			int exp_id =id[i];
			String exp_email = email[i];
			
			 String res_id=jspresponse.getString("data["+i+"].id");
			 int  res_int_id=Integer.parseInt(res_id);
			 String res_email=jspresponse.getString("data["+i+"].email");
			 
		// Step 5 : Validate responseBody
			 Assert.assertEquals(res_int_id, exp_id,"ID at index " + i);
			 Assert.assertEquals(res_email, exp_email, "email at index" + i);
		}
	}
	
}	
		
	


