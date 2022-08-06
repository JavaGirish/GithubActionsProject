package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

    @Test(priority = 3)
    public void SearchTest() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Apple Macbook Air");
        driver.findElement(By.id("nav-search-submit-button")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@class,'a-size-medium')]"));
        elements.stream().filter(e -> e.getText().contains("Macbook Air")).forEach(e -> System.out.println(e.getText()));
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}
