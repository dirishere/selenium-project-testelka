package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage{
  private WebDriverWait wait;

  public CartPage(WebDriver driver){
    super(driver); //wywołuje konstruktor klasy nadrzędnej (czyli BasePage)
    wait = new WebDriverWait(driver, 10);
  }

  @FindBy(css="form>.shop_table") @CacheLookup private WebElement shopTable;
  @FindBy(css="form>.shop_table") @CacheLookup private List<WebElement> shopTables;
  @FindBy(css="div.quantity>input") @CacheLookup private WebElement productQuantityField;
  @FindBy(css=".cart_item") @CacheLookup private List<WebElement> cartItems;
  @FindBy(css="[name='update_cart']") @CacheLookup private WebElement updateProductButton;
  @FindBy(css=".checkout-button") @CacheLookup private WebElement checkoutButton;

  private String removeProductButtonCssSelector = "a[data-product_id='<product_id>']";
  private By loaderLocator = By.cssSelector(".blockOverlay");

  public int getProductQuantity(){
    waitForShopTable();
    return Integer.parseInt(productQuantityField.getAttribute("value"));
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
    return cartItems.size();
  }

  private void waitForShopTable() {
    wait.until(ExpectedConditions.visibilityOf(shopTable));
  }

  public CartPage changeNumberOfProducts(int quantity) {
    productQuantityField.clear();
    productQuantityField.sendKeys(String.valueOf(quantity));

    //return new CartPage(driver);
    return this;
  }

  public CartPage updateCart() {
    waitForShopTable();
    wait.until(ExpectedConditions.elementToBeClickable(updateProductButton));
    updateProductButton.click();

    return this;
  }

  public CartPage removeProduct(String productId) {
    waitForShopTable();
    By removeProductLocator = By.cssSelector(removeProductButtonCssSelector.replace("<product_id>", productId));
    WebElement removeButton = driver.findElement(removeProductLocator);
    removeButton.click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));

    return this;
  }

  public boolean isCartEmpty() {
    int shopTableElements = shopTables.size();
    if (shopTableElements == 1){
      return false;
    } else if (shopTableElements == 0) {
      return true;
    } else {
      throw new IllegalArgumentException("Wrong number of shop table elements: there can be only one or none.");
    }
  }

  public CheckoutPage goToCheckout() {
    waitForShopTable();
    checkoutButton.click();
    return new CheckoutPage(driver);
  }
}