package Tests;

import Drivers.DriverFactory;
import Utils.ConfigurationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
  protected WebDriver driver;
  protected String baseUrl = ConfigurationManager.getInstance().getBaseUrl();

  @BeforeEach
  public void testSetup() throws MalformedURLException {
    DriverFactory driverFactory = new DriverFactory();
    driver = driverFactory.create();

    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.manage().window().setSize(new Dimension(1290, 730));
    driver.manage().window().setPosition(new Point(8, 30));
  }

  @AfterEach
  public void driverQuit() {
    driver.quit();
  }
}