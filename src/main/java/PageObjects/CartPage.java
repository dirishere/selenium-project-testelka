package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
  private WebDriverWait wait;

  public CartPage(WebDriver driver){
    super(driver); //wywołuje konstruktor klasy nadrzędnej (czyli BasePage)
    wait = new WebDriverWait(driver, 10);
  }

  private By shopTableLocator = By.cssSelector("form>.shop_table");
  private By productQuantityFieldLocator = By.cssSelector("div.quantity>input");
  private By cartItemLocator = By.cssSelector(".cart_item");
  private By updateProductButtonLocator = By.cssSelector("[name='update_cart']");
  private By loaderLocator = By.cssSelector(".blockOverlay");
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
    return driver.findElements(cartItemLocator).size();
  }

  private void waitForShopTable() {
    wait.until(ExpectedConditions.presenceOfElementLocated(shopTableLocator));
  }

  public CartPage changeNumberOfProducts(int quantity) {
    WebElement amountInput = driver.findElement(productQuantityFieldLocator);
    amountInput.clear();
    amountInput.sendKeys(String.valueOf(quantity));

    return new CartPage(driver);
  }

  public CartPage updateCart() {
    waitForShopTable();
    WebElement updateCartButton = driver.findElement(updateProductButtonLocator);
    wait.until(ExpectedConditions.elementToBeClickable(updateCartButton));
    updateCartButton.click();

    return new CartPage(driver);
  }

  public CartPage removeProduct(String productId) {
    waitForShopTable();
    By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
    WebElement removeButton = driver.findElement(removeProductLocator);
    removeButton.click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));

    return new CartPage(driver);
  }

  public boolean isCartEmpty() {
    int shopTableElements = driver.findElements(shopTableLocator).size();
    if (shopTableElements == 1){
      return false;
    } else if (shopTableElements == 0) {
      return true;
    } else {
      throw new IllegalArgumentException("Wrong number of shop table elements: there can be only one or none.");
    }
  }
}