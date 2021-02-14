package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {
  protected HeaderPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(css=".cart-contents") @CacheLookup private WebElement totalPrice;

  public CartPage viewCart() {
    totalPrice.click();
    return new CartPage(driver);
  }
}
