
package basic;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    // comment push
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .body("scope",equalTo("APP")).extract().response().asString();
        System.out.println(response);
        JsonPath js = ReusableMethods.rawToJson(response);
        String placeId = js.getString("place_id");
        System.out.println("place_id = "+ placeId);

        String newAddress = "36, El Etihad St";
        given().queryParam("key","qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"place_id\" : \""+ placeId + "\",\n" +
                        "    \"address\": \"" + newAddress + "\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();
        JsonPath getJs = ReusableMethods.rawToJson(getPlaceResponse);
        String updatedAddress = getJs.getString("address");
        Assert.assertEquals(updatedAddress,newAddress);
        System.out.println("UpdatedAddress is: "+updatedAddress);

    }


}
