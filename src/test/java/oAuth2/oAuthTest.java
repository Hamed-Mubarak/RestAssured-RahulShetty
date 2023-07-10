package oAuth2;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class oAuthTest {

    private static WebDriver driver;

    public static  void  main(String[] args) throws InterruptedException {
/*
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https://www.googleapis.com/auth/userinfo.email" +
                "&auth_url=https://accounts.google.com/o/oauth2/v2/auth" +
                "&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com" +
                "&response_type=code" +
                "&redirect_uri=https://rahulshettyacademy.com/getCourse.php" +
                "&state=validateHamed");
        driver.findElement(By.id("identifierId")).clear();
        driver.findElement(By.id("identifierId")).sendKeys("hamed.abdelaziz@rabbitmart.com");
        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.name("Passwd")).clear();
        driver.findElement(By.name("Passwd")).sendKeys("Hamed@Rabbit");
        driver.findElement(By.name("Passwd")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        //String url = driver.getCurrentUrl();
        "https://rahulshettyacademy.com/getCourse.php\n" +
                "?state=validateHamed\n" +
                "&code=4%2F0AZEOvhW7PuPNLlH-tgDnfsm5EARSaaKpuQgzv790fmvdSMIWSgjXtIzenwhbhysduQpPsw\n" +
                "&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0\n" +
                "&hd=rabbitmart.com\n" +
                "&prompt=consent#\n";
*/

        String url= "https://rahulshettyacademy.com/getCourse.php" +
                "?code=4%2FvAHBm2GRDwCkyjMalxQUWKWUFuHnWE2wQNW-tSM0PELkOnp49xG3HmgZwaCHbP7q_Ukve8r3IOq0VELMJLvXqEc" +
                "&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid" +
                "&authuser=1" +
                "&hd=rabbitmart.com" +
                "&prompt=consent";
        String partialCode=url.split("code=")[1];
        String code=partialCode.split("&scope")[0];
        System.out.println("Authorization code is: " + code);

        String accessTokenResponse =
        given()
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
        .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");
        System.out.println("Access token: " + accessToken);

        String response =
        given()
                .queryParam("access_token",accessToken)
                .contentType("application/json")
        .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);
 /*
        When x the API is passed i have to tell the IDE that the default response received is JSON
        and convert it to Java Object POJO class
        GetCourse getCourse=
        given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)

        .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(response);

  */

    }
}

