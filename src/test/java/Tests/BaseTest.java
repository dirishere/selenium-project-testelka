package Tests;

import Drivers.Browser;
import Drivers.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
  protected WebDriver driver;

  @BeforeEach
  public void testSetup() throws MalformedURLException {
    DriverFactory driverFactory = new DriverFactory();
    driver = driverFactory.create(Browser.CHROME);

    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    driver.manage().window().setSize(new Dimension(1290, 730));
    driver.manage().window().setPosition(new Point(8, 30));
  }

  @AfterEach
  public void driverQuit() {
    driver.quit();
  }
}