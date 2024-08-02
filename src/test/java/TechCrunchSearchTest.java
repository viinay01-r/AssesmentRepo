import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TechCrunchSearchTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");  // Start browser maximized
        options.addArguments("--disable-notifications");  
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifySearchFunctionality() {
        driver.get("https://techcrunch.com");
        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Search")));
        searchIcon.click();
        WebElement searchBar = driver.findElement(By.name("s"));
        searchBar.sendKeys("AI");
        searchBar.submit();
        boolean searchResultsDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("AI"))).isDisplayed();
        Assert.assertTrue(searchResultsDisplayed, "Test Passed: Search results page is displayed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();        }
    }
}
