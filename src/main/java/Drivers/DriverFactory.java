package Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
  private String hubURL = "http://localhost:4444/wd/hub";

  public WebDriver create(Browser browserType) throws MalformedURLException {
    switch (browserType) {
      case CHROME:
        return getChromeDriver();
      case FIREFOX:
        return getFirefoxDriver();
      default:
        throw new IllegalArgumentException("It seems that provided browser doesn't exit");
    }
  }

  private WebDriver getChromeDriver() throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.VERSION, "66");
    return new RemoteWebDriver(new URL(hubURL), options);
  }

  private WebDriver getFirefoxDriver() throws MalformedURLException {
    FirefoxOptions options = new FirefoxOptions();
    return new RemoteWebDriver(new URL(hubURL), options);
  }
}