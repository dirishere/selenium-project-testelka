package tests.categoriesPageTests;

import TestHelpers.TestStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class AddProductToCart {
  private WebDriver driver;
  Actions actions;
  private WebDriverWait wait;

  @RegisterExtension
  TestStatus status = new TestStatus();

  @BeforeEach
  public void driverSetup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

    driver.manage().window().setSize(new Dimension(1295, 730));
    driver.manage().window().setPosition(new Point(10, 40));
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.navigate().to("https://fakestore.testelka.pl/product-category/windsurfing/");

    actions = new Actions(driver);

    wait = new WebDriverWait(driver, 10);
  }

  @AfterEach
  public void driverQuit(TestInfo info) throws IOException {
    if (status.isFailed) {
      System.out.println("Test screenshot is available at: " + takeScreenShot(info));
    }
    driver.quit();
  }

  @Test
  public void addOneTripToCartTest() {
    WebElement demoInfo = driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']"));
    actions.click(demoInfo).build().perform();

    WebElement submitButton = driver.findElement(By.cssSelector("a[href='?add-to-cart=393']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    actions.click(submitButton).build().perform();
    WebElement seeCart = driver.findElement(By.cssSelector("a[class='added_to_cart wc-forward']"));
    Assertions.assertTrue(seeCart.isDisplayed(), "Products has not been added to the cart.");
  }

  @Test
  public void addMoreThan10DifferentTripsToCartTest() {
    WebElement demoInfo = driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']"));
    actions.click(demoInfo).build().perform();

    WebElement submitButton1 = driver.findElement(By.cssSelector("a[href='?add-to-cart=386']"));
    WebElement submitButton2 = driver.findElement(By.cssSelector("a[href='?add-to-cart=393']"));
    WebElement submitButton3 = driver.findElement(By.cssSelector("a[href='?add-to-cart=391']"));
    WebElement submitButton4 = driver.findElement(By.cssSelector("a[href='?add-to-cart=50']"));
    WebElement submitButton5 = driver.findElement(By.cssSelector("a[href='?add-to-cart=389']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton5);
    actions.click(submitButton1).build().perform();
    actions.click(submitButton1).build().perform();
    actions.click(submitButton2).build().perform();
    actions.click(submitButton2).build().perform();
    actions.click(submitButton3).build().perform();
    actions.click(submitButton3).build().perform();
    actions.click(submitButton4).build().perform();
    actions.click(submitButton4).build().perform();
    actions.click(submitButton5).build().perform();
    actions.click(submitButton5).build().perform();
    By price = By.cssSelector("span[class='woocommerce-Price-amount amount']");
    wait.until(ExpectedConditions.textToBe(price, "36 998,00 zł"));
    String priceText = driver.findElement(price).getText();
    Assertions.assertEquals("36 998,00 zł", priceText, "Products have not been added to the cart.");
  }

  private String takeScreenShot(TestInfo info) throws IOException {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    LocalDateTime timeNow = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss");
    String path = "C:\\tests_screens\\" + info.getDisplayName() + formatter.format(timeNow) + ".png";
    FileHandler.copy(screenshot, new File(path));
    return path;
  }
}
