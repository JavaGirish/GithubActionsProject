package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AmazonTest {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "version"})
    public void setUp(String browserName, String version, Method method) {
        /*
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); //!!!should be enabled for Jenkins
        options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
        options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins
        WebDriverManager.chromedriver().setup();
        */
        String testMethodName = method.getName();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, version);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("name", testMethodName);
        try {
            driver = new RemoteWebDriver(new URL("http://34.131.87.254:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
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
