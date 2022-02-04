package login.web.com.globaltradingnetwork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://difc.globaltradingnetwork.com/web/login");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginButton")));
    }

    @Test
    public void EmptyFieldsTest() {
        driver.findElement(By.id("LoginButton")).click();
        String empty_Field_message = driver.findElement(By.xpath("//div[@class='login_error_msg']")).getText();
        System.out.println("Error Message For Empty Fields: " + empty_Field_message);
    }

    @Test
    public void RandomDataTest() throws InterruptedException {
        driver.findElement(By.id("form-input-live-u")).sendKeys("TestUsername");
        driver.findElement(By.id("form-input-live-p")).sendKeys("TestPassword");
        driver.findElement(By.id("LoginButton")).click();
        // to avoid getting Authenticating msg
        Thread.sleep(17000);
        String invalid_data_message = driver.findElement(By.xpath("//div[@class='login_error_msg']")).getText();
        System.out.println("Error Message for invalid data: " + invalid_data_message);
    }

    @AfterMethod
    public void afterAll(){
        driver.close();
    }
}
