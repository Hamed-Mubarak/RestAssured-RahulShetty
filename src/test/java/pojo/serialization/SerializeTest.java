package pojo.serialization;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static  io.restassured.RestAssured.*;

public class SerializeTest {
    @Test
    public static void serializationTest(){

        AddPlaceBody a = new AddPlaceBody();
        a.setAccuracy(50);
        a.setAddress("113, Refaat Hassan St, Hadaba Wosta, Mukkatam");
        a.setLanguage("Arabic");
        a.setName("Hamed");
        a.setPhone_number("01090074666");
        a.setWebsite("https://rahulshettyacademy.com");
        // as we expect List of strings, so we have to convert the List of Strings to Array list
        //  and take an object of array list of strings and pass it
        List<String> myTypesList = new ArrayList<>();
        myTypesList.add("Shoe Park");
        myTypesList.add("shop");
        a.setTypes(myTypesList);
        // as we expect Location class, so we have to take an object from location class first and pass it
        Location l = new Location();
        l.setLat(29.9891544);
        l.setLng(31.3063091);
        a.setLocation(l);

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response resp = given().queryParam("key","qaclick123")
                .body(a)
        .when().post("maps/api/place/add/json")
        .then().assertThat().statusCode(200).extract().response();

        String responseString = resp.asString();
        System.out.println(responseString);
        // to get any property from the response and interact with it
        JsonPath js = ReusableMethods.rawToJson(responseString);
        String placeId = js.getString("place_id");
        System.out.println("place_id = "+ placeId);
/*
        To Deserialize the get place api
        AddPlaceBody ab =
                given().queryParams("key","qaclick123").queryParams("place_id",placeId)
                        .expect().defaultParser(Parser.JSON)
                        .when().get("/maps/api/place/get/json").as(AddPlaceBody.class);
        System.out.println(ab.getAccuracy());
*/

    }
}
