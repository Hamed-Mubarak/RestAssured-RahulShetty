package oAuth2;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
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

        String url= "\n" +
                "        https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss\n" +
                "        &code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M\n" +
                "        &scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid\n" +
                "        &authuser=0\n" +
                "        &session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c\n" +
                "        &prompt=none#";
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
                .queryParams("session_state","f4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                .queryParams("scope","email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
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
    }
}

