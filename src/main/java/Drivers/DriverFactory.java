package Drivers;

import Utils.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
  public WebDriver create() throws MalformedURLException {
    Browser browserType = Browser.valueOf(ConfigurationManager.getInstance().getBrowser());
    switch (browserType) {
      case CHROME:
        return getChromeDriver();
      case FIREFOX:
        return getFirefoxDriver();
      case SAFARI:
        return getSafariDriver();
      default:
        throw new IllegalArgumentException("It seems that provided browser doesn't exit");
    }
  }

  private WebDriver getChromeDriver() throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.VERSION, "66");
    return new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getHubUrl()), options);
  }

  private WebDriver getFirefoxDriver() throws MalformedURLException {
    FirefoxOptions options = new FirefoxOptions();
    return new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getHubUrl()), options);
  }

  private WebDriver getSafariDriver() throws MalformedURLException {
    SafariOptions options = new SafariOptions();
    return new RemoteWebDriver(new URL(ConfigurationManager.getInstance().getHubUrl()), options);
  }
}