package TestyPOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
  protected WebDriver driver;

  @BeforeEach
  public void testSetup() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.manage().window().setSize(new Dimension(1290, 730));
    driver.manage().window().setPosition(new Point(8, 30));
  }

  @AfterEach
  public void driverQuit() {
    driver.quit();
  }
}