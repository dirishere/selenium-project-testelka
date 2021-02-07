package Drivers;

import Utils.ConfigurationReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
  private RemoteWebDriver driver;

  public WebDriver create(ConfigurationReader configuration) {
    switch (Browser.valueOf(configuration.getBrowser())) {
      case CHROME:
        return getChromeDriver(configuration);
      case FIREFOX:
        return getFirefoxDriver(configuration);
      case SAFARI:
        return getSafariDriver(configuration);
      default:
        throw new IllegalArgumentException("It seems that provided browser doesn't exit");
    }
  }

  private WebDriver getChromeDriver(ConfigurationReader configuration) {
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.VERSION, "66");
    return getDriver(options, configuration);
  }

  private WebDriver getFirefoxDriver(ConfigurationReader configuration) {
    FirefoxOptions options = new FirefoxOptions();
    return getDriver(options, configuration);
  }

  private WebDriver getSafariDriver(ConfigurationReader configuration) {
    SafariOptions options = new SafariOptions();
    return getDriver(options, configuration);
  }

  private WebDriver getDriver(MutableCapabilities options, ConfigurationReader configuration) {
    try{
      driver = new RemoteWebDriver(new URL(configuration.getHubUrl()), options);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      System.out.println(e + " was thrown. HubUrl is incorect or missing. Check config file.");
    }
    return driver;
  }
}