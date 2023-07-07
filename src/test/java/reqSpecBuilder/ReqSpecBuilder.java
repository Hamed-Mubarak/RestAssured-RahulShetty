package reqSpecBuilder;

import files.ReusableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.serialization.AddPlaceBody;
import pojo.serialization.LocationReq;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqSpecBuilder {

    @Test
    public static void requestSpecBuilder(){

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
        LocationReq l = new LocationReq();
        l.setLat(29.9891544);
        l.setLng(31.3063091);
        a.setLocation(l);

        // define the most common data sent in the request
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        // define the most common data sent in the response
        ResponseSpecification responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        // We separate the request and the response
        RequestSpecification requestBody = given().spec(req).body(a);

        Response res = requestBody.when().post("maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();

        String responseString = res.asString();
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
