
package basic;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Basics {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": 29.9891544,\n" +
                        "    \"lng\": 31.3063091\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Hamed Home\",\n" +
                        "  \"phone_number\": \"(+2)01090074666\",\n" +
                        "  \"address\": \"113, Refaat Hassan St, Hadaba Wosta, Mukkatam\",\n" +
                        "  \"types\": [\n" +
                        "    \"Home\",\n" +
                        "    \"Work\"\n" +
                        "  ],\n" +
                        "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                        "  \"language\": \"Arabic\"\n" +
                        "}\n")
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);

    }
}
