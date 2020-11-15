package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
  private WebDriverWait wait;

  private By firstNameFieldLocator = By.cssSelector("#billing_first_name");
  private By lastNameNameFieldLocator = By.cssSelector("#billing_last_name");
  private By countrySelectionDropdownLocator = By.id("billing_country");
  private By addressFieldLocator = By.cssSelector("#billing_address_1");
  private By postalCodeFieldLocator = By.cssSelector("#billing_postcode");
  private By cityFieldLocator = By.cssSelector("#billing_city");
  private By phoneFieldLocator = By.cssSelector("#billing_phone");
  private By emailFieldLocator = By.cssSelector("#billing_email");
  private By loadingIconLocator = By.cssSelector(".blockOverlay");
  private By cardNumberFieldLocator = By.cssSelector("[name='cardnumber']");
  private By expirationDateFieldLocator = By.cssSelector("[name='exp-date']");
  private By cvcFieldLocator = By.cssSelector("[name='cvc']");
  private By termsCheckBoxLocator = By.cssSelector("input[id='terms']");
  private By orderButtonLocator = By.cssSelector("button[id='place_order']");

  public CheckoutPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, 10);
  }

  public CheckoutPage typeName(String name) {
    wait.until(ExpectedConditions.elementToBeClickable(firstNameFieldLocator)).sendKeys(name);
    return this;
  }

  public CheckoutPage typeLastName(String lastName) {
    wait.until(ExpectedConditions.elementToBeClickable(lastNameNameFieldLocator)).sendKeys(lastName);
    return this;
  }

  public CheckoutPage chooseCountry(String countryCode) {
    WebElement countrySelectionDropdown = driver.findElement(countrySelectionDropdownLocator);
    Select country = new Select(countrySelectionDropdown);
    country.selectByValue(countryCode);
    return this;
  }

  public CheckoutPage typeAddress(String address) {
    wait.until(ExpectedConditions.elementToBeClickable(addressFieldLocator)).sendKeys(address);
    return this;
  }

  public CheckoutPage typePostalCode(String postalCode) {
    wait.until(ExpectedConditions.elementToBeClickable(postalCodeFieldLocator)).sendKeys(postalCode);
    return this;
  }

  public CheckoutPage typeCity(String city) {
    wait.until(ExpectedConditions.elementToBeClickable(cityFieldLocator)).sendKeys(city);
    return this;
  }

  public CheckoutPage typePhone(String phone) {
    wait.until(ExpectedConditions.elementToBeClickable(phoneFieldLocator)).sendKeys(phone);
    return this;
  }

  public CheckoutPage typeEmail(String emailAddress) {
    wait.until(ExpectedConditions.elementToBeClickable(emailFieldLocator)).sendKeys(emailAddress);
    return this;
  }

  public CheckoutPage typeCardNumber(String cardNumber) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(0);
    wait.until(ExpectedConditions.elementToBeClickable(cardNumberFieldLocator)).sendKeys(cardNumber);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage typeCardExpirationDate(String expirationDate) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(1);
    wait.until(ExpectedConditions.elementToBeClickable(expirationDateFieldLocator)).sendKeys(expirationDate);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage typeCvcCode(String cvcCode) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(2);
    wait.until(ExpectedConditions.elementToBeClickable(cvcFieldLocator)).sendKeys(cvcCode);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage selectAcceptTerms() {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.findElement(termsCheckBoxLocator).click();
    return this;
  }

  public OrderReceivedPage order() {
    driver.findElement(orderButtonLocator).click();
    return new OrderReceivedPage(driver);
  }
}