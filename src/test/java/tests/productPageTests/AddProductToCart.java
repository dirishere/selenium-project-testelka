package tests.productPageTests;

import TestHelpers.TestStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class AddProductToCart {
  private WebDriver driver;
  Actions actions;

  @RegisterExtension
  TestStatus status = new TestStatus();

  @BeforeEach
  public void driverSetup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

    driver.manage().window().setSize(new Dimension(1295, 730));
    driver.manage().window().setPosition(new Point(10, 40));
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");

    actions = new Actions(driver);
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

    WebElement submitButton = driver.findElement(By.cssSelector("button[name='add-to-cart']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    actions.click(submitButton).build().perform();
    WebElement alert = driver.findElement(By.cssSelector("div[class='woocommerce-message']"));
    Assertions.assertEquals("Zobacz koszyk\n" + "“Fuerteventura – Sotavento” został dodany do koszyka.", alert.getText(), "Product has not been added to the cart.");
  }

  @Test
  public void addMoreThan10TripsToCartTest() {
    WebElement demoInfo = driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']"));
    actions.click(demoInfo).build().perform();

    WebElement quantity = driver.findElement(By.cssSelector("input[class='input-text qty text']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quantity);
    actions.sendKeys(quantity, Keys.BACK_SPACE).sendKeys(quantity, "11").build().perform();
    WebElement submitButton = driver.findElement(By.cssSelector("button[name='add-to-cart']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    actions.click(submitButton).build().perform();
    WebElement alert = driver.findElement(By.cssSelector("div[class='woocommerce-message']"));
    Assertions.assertEquals("Zobacz koszyk\n" + "11 × “Fuerteventura – Sotavento” zostało dodanych do koszyka.", alert.getText(), "Products have not been added to the cart.");
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
