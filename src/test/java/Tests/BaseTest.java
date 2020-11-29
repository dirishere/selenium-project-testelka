package Tests;

import Drivers.Browser;
import Drivers.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
  protected WebDriver driver;
  protected static String baseUrl;
  private static String hubUrl;
  private static String browser;

  @BeforeAll
  public static void loadConfig() throws IOException {
    Properties properties = new Properties();
    properties.load(new FileInputStream("src/configs/Configurations.properties"));
    hubUrl = properties.getProperty("hubUrl");
    baseUrl = properties.getProperty("baseUrl");
    browser = properties.getProperty("browser");
  }

  @BeforeEach
  public void testSetup() throws MalformedURLException {
    DriverFactory driverFactory = new DriverFactory();
    driver = driverFactory.create(Browser.valueOf(browser), hubUrl);

    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.manage().window().setSize(new Dimension(1290, 730));
    driver.manage().window().setPosition(new Point(8, 30));
  }

  @AfterEach
  public void driverQuit() {
    driver.quit();
  }
}