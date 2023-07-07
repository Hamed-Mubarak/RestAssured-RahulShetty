package pojo.deSerialization;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class DeserializeTest {

    @Test
    public static void testDeserialization(){

        RequestSpecification deserializeBaseReq = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com")
            .addQueryParam("key","qaclick123")
            .setContentType(ContentType.JSON).build();
        RequestSpecification deserializeReq = given()
                .spec(deserializeBaseReq)
                .queryParam("place_id","626d8ffd924d35f40235bc5e990f3d5c");
        Response deserializeBaseResp = deserializeReq
                .when().get("/maps/api/place/get/json")
                .then().extract().response();
        GetPlaceBody deserializeBojoResp = deserializeBaseResp.as(GetPlaceBody.class);
        String st = deserializeBojoResp.getTypes();
        System.out.println(st);


    }

}
