package payloadStrategies;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadFromFile {
    @Test
    public void readFromFile() throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response =
                given().queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(new String(Files.readAllBytes(Paths.get("/Applications/1-Rabbit/API Course/APIs/API Places Google Maps"))))
                        .when().post("maps/api/place/add/json")
                        .then().assertThat().statusCode(200)
                        .header("Server", "Apache/2.4.41 (Ubuntu)")
                        .body("scope", equalTo("APP")).extract().response().asString();
        System.out.println(response);
        JsonPath js = ReusableMethods.rawToJson(response);
        String placeId = js.getString("place_id");
        System.out.println("place_id = " + placeId);
    }
}
