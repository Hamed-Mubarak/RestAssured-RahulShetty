package payloadStrategies;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import files.PayLoad;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    public void addBook(){
        RestAssured.baseURI="http://216.10.245.166";
        String response =
        given().header("Content-Type", "application/json")
               .body(PayLoad.addBook("asdfg","12345"))
        .when().post("/Library/Addbook.php")
        .then().assertThat().statusCode(200)
               .extract().response().asString();
        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }
}
