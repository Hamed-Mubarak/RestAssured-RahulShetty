package payloadStrategies;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Parameterization {

    /*
    # Before data provider,  addBook() method was without function arguments
      and body(PayLoad.addBook("ssfsf","1234") was accepting values
    # After data provider, addBook() method accepts function provider addBook(String isbn, String aisle)
      and body(PayLoad.addBook(isbn,isle) accepts variable names
    */
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String isle){
        RestAssured.baseURI="http://216.10.245.166";
        String response =
        given().header("Content-Type", "application/json")
               .body(PayLoad.addBook(isbn,isle))
        .when().post("/Library/Addbook.php")
        .then().assertThat().statusCode(200)
               .extract().response().asString();
        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"abcd","1234"}, {"efgh","5678"}, {"ijkl","9012"}};
    }

}
