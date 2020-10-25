package StareTesty.cartPageTests;

import TestHelpers.TestStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class EditSelectedProduct {
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

    driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");

    actions = new Actions(driver);

    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void changeAmountOfSelectedTripInCartTest() {
    WebElement demoInfo = driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']"));
    actions.click(demoInfo).build().perform();

    WebElement submitButton = driver.findElement(By.cssSelector("button[name='add-to-cart']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    actions.click(submitButton).build().perform();

    driver.navigate().to("https://fakestore.testelka.pl/koszyk/");
    By amount = By.cssSelector("input[type='number']");
    WebElement amountInput = driver.findElement(amount);
    Assertions.assertEquals("1", amountInput.getAttribute("value"), "Amount should be equals 1.");

    amountInput.sendKeys(Keys.DELETE);
    amountInput.sendKeys("5");
    WebElement updateCart = driver.findElement(By.cssSelector("button[type='submit']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateCart);
    actions.click(updateCart);
    Assertions.assertEquals("5", amountInput.getAttribute("value"), "Amount should be equals 5.");
  }

  @AfterEach
  public void driverQuit(TestInfo info) throws IOException {
    if (status.isFailed) {
      System.out.println("Test screenshot is available at: " + takeScreenShot(info));
    }
    driver.quit();
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
