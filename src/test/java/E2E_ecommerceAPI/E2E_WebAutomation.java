package E2E_ecommerceAPI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class E2E_WebAutomation {

    private static WebDriver driver;
    private static By emailField = By.id("userEmail");
    private static By passwordField = By.id("userPassword");
    private static By loginButton = By.id("login");
    public static WebElement getEmailField(){
        return driver.findElement(emailField);
    }
    public static WebElement getPasswordField(){
        return driver.findElement(passwordField);
    }
    public static WebElement getLoginButton(){
        return driver.findElement(loginButton);
    }
    public static void enterEmailAddress(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    public static void enterPassword(String password){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    public static void clickOnLoginButton(){
        driver.findElement(loginButton).click();
    }
    @Test
    public static void webAutomation()  {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.rahulshettyacademy.com/client");
        enterEmailAddress("abdelaziz.hamed.m77@gmail.com");
        enterPassword("Hamed1992");
        clickOnLoginButton();

    }
}
