package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

public class AmazonTest {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser, Method method){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        switch (browser.toUpperCase()){
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
        driver.get("https://www.amazon.in/");

    }

    @Test(priority = 1)
    public void verifyTitleIsAsExpected() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");

    }

    @Test(priority = 2)
    public void verifyLogoIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#nav-logo-sprites")).isDisplayed());
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}
