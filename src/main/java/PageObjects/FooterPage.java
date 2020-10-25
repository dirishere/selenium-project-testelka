package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterPage extends BasePage{
  protected FooterPage(WebDriver driver){
    super(driver);
  }

  private By demoNoticeLocator = By.cssSelector(".woocommerce-store-notice__dismiss-link");

  public void closeDemoNotice(){
    driver.findElement(demoNoticeLocator).click();
  }
}
