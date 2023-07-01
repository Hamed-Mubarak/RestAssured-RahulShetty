package oAuth2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class SeleniumTest {


    private static WebDriver driver;

    @Test
    public static void testSelenium(){
  //      ChromeOptions options = new ChromeOptions();
  //      options.addArguments("--remote-allow-origins=*");
        driver = new SafariDriver();
        driver.get("https://www.google.com/");

    }
}