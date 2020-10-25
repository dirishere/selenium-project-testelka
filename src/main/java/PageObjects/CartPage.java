package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
  private WebDriverWait wait;

  public CartPage(WebDriver driver){
    super(driver); //wywołuje konstruktor klasy nadrzędnej (czyli BasePage)
    wait = new WebDriverWait(driver, 7);
  }

  private By shopTableLocator = By.cssSelector(".shop_table");
  private By productQuantityFieldLocator = By.cssSelector("div.quantity>input");
  private By cartItemSelector = By.cssSelector(".cart_item");
  private String removeProductButtonCssSelector = "a[data-product_id='<product_id>']";

  public int getProductQuantity(){
    waitForShopTable();
    return Integer.parseInt(driver.findElement(productQuantityFieldLocator).getAttribute("value"));
  }

  public boolean isProductInCart(String productId) {
    waitForShopTable();
    By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
    int productRecords = driver.findElements(removeProductLocator).size();
    boolean presenceOfProduct = false;
    if(productRecords==1){
      presenceOfProduct = true;
    } else if(productRecords>1){
      throw new IllegalArgumentException("There is more than one record for the product in cart.");
    }
    return presenceOfProduct;
  }

  public int getNumberOfProducts() {
    waitForShopTable();
    return driver.findElements(cartItemSelector).size();
  }

  private void waitForShopTable() {
    wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
  }
}