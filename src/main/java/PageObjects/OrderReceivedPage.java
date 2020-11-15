package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderReceivedPage extends BasePage{
  private WebDriverWait wait;
  private By orderReceivedMessgaeLocator = By.cssSelector(".woocommerce-thankyou-order-received");

  public OrderReceivedPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, 20);
  }

  public boolean isOrderSuccessful() {
    wait.until(ExpectedConditions.urlContains("/zamowienie/zamowienie-otrzymane"));
    int numberOfSuccessfulMessages = driver.findElements(orderReceivedMessgaeLocator).size();
    if(numberOfSuccessfulMessages != 1) {
      throw new IllegalArgumentException("Number of success messages is " + numberOfSuccessfulMessages + ". It should be 1.");
    }
    return true;
  }
}