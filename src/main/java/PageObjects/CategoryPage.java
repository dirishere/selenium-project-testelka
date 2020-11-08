package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage{
  private WebDriverWait wait;
  public FooterPage footer;

  public CategoryPage(WebDriver driver){
    super(driver);
    footer = new FooterPage(driver);
    wait = new WebDriverWait(driver, 5);
  }

  private By viewCartButtonLocator = By.cssSelector(".added_to_cart");
  private String addToCartButtonCssSelector = ".post-<product_id>>.add_to_cart_button";

  public CategoryPage goTo(String url){
    driver.navigate().to(url);
    return this;
  }

  public CategoryPage addToCart(String productId){
    driver.findElement(By.cssSelector(addToCartButtonCssSelector.replace("<product_id>", productId))).click();
    wait.until(ExpectedConditions.attributeContains(By.cssSelector(addToCartButtonCssSelector.
            replace("<product_id>", productId)), "class", "added"));
    return this;
  }

  public CartPage viewCart() {
    wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
    return new CartPage(driver);
  }
}