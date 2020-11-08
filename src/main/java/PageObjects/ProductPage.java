package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{
  public HeaderPage header;
  public FooterPage footer;
  private WebDriverWait wait;

  public ProductPage(WebDriver driver){
    super(driver);
    header = new HeaderPage(driver);
    footer = new FooterPage(driver);
    wait = new WebDriverWait(driver, 7);
  }

  private By addToCartButtonLocator = By.cssSelector("button[name='add-to-cart']");
  private By viewCartButtonLocator = By.cssSelector(".woocommerce-message>.button");
  private By productQuantityFieldLocator = By.cssSelector("input[class='input-text qty text']");

  public ProductPage goTo(String productUrl){
    driver.navigate().to(productUrl);
    return this;
  }

  public ProductPage addToCart(){
    WebElement addButton = driver.findElement(addToCartButtonLocator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
    addButton.click();
    wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator));
    return this;
  }

  public ProductPage addToCart(int productAmount){
    WebElement submitAddButton = driver.findElement(addToCartButtonLocator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitAddButton);
    Actions actions = new Actions(driver);
    WebElement quantityInput = driver.findElement(productQuantityFieldLocator);
    quantityInput.clear();
    actions.sendKeys(quantityInput, String.valueOf(productAmount)).build().perform();
    actions.click(submitAddButton).build().perform();
    wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator));
    return this;
  }

  public CartPage viewCart(){
    wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonLocator)).click();
    return new CartPage(driver);
  }
}