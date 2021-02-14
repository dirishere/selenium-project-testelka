package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
  private WebDriverWait wait;

  public CheckoutPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, 10);
  }

  @FindBy(css="#billing_first_name") @CacheLookup private WebElement firstNameField;
  @FindBy(css="#billing_last_name") @CacheLookup private WebElement lastNameField;
  @FindBy(css="select[id='billing_country']") @CacheLookup private WebElement countrySelectionDropdown;
  @FindBy(css="#billing_address_1") @CacheLookup private WebElement addressField;
  @FindBy(css="#billing_postcode") @CacheLookup private WebElement postalCodeField;
  @FindBy(css="#billing_city") @CacheLookup private WebElement cityField;
  @FindBy(css="#billing_phone") @CacheLookup private WebElement phoneField;
  @FindBy(css="#billing_email") @CacheLookup private WebElement emailField;
  @FindBy(css="input[name='cardnumber']") @CacheLookup private WebElement cardNumberField;
  @FindBy(css="input[name='cvc']") @CacheLookup private WebElement cvcField;
  @FindBy(css="input[id='terms']") @CacheLookup private WebElement termsCheckBox;
  @FindBy(css="button[id='place_order']") @CacheLookup private WebElement orderButton;

  private By loadingIconLocator = By.cssSelector(".blockOverlay");
  private By expirationDateFieldLocator = By.cssSelector("[name='exp-date']");

  public CheckoutPage typeName(String name) {
    wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(name);
    return this;
  }

  public CheckoutPage typeLastName(String lastName) {
    wait.until(ExpectedConditions.elementToBeClickable(lastNameField)).sendKeys(lastName);
    return this;
  }

  public CheckoutPage chooseCountry(String countryCode) {
    Select country = new Select(countrySelectionDropdown);
    country.selectByValue(countryCode);
    return this;
  }

  public CheckoutPage typeAddress(String address) {
    wait.until(ExpectedConditions.elementToBeClickable(addressField)).sendKeys(address);
    return this;
  }

  public CheckoutPage typePostalCode(String postalCode) {
    wait.until(ExpectedConditions.elementToBeClickable(postalCodeField)).sendKeys(postalCode);
    return this;
  }

  public CheckoutPage typeCity(String city) {
    wait.until(ExpectedConditions.elementToBeClickable(cityField)).sendKeys(city);
    return this;
  }

  public CheckoutPage typePhone(String phone) {
    wait.until(ExpectedConditions.elementToBeClickable(phoneField)).sendKeys(phone);
    return this;
  }

  public CheckoutPage typeEmail(String emailAddress) {
    wait.until(ExpectedConditions.elementToBeClickable(emailField)).sendKeys(emailAddress);
    return this;
  }

  public CheckoutPage typeCardNumber(String cardNumber) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(0);
    wait.until(ExpectedConditions.elementToBeClickable(cardNumberField)).sendKeys(cardNumber);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage typeCardExpirationDate(String expirationDate) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(1);
    wait.until(ExpectedConditions.visibilityOfElementLocated(expirationDateFieldLocator)).sendKeys(expirationDate);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage typeCvcCode(String cvcCode) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    driver.switchTo().frame(2);
    wait.until(ExpectedConditions.elementToBeClickable(cvcField)).sendKeys(cvcCode);
    driver.switchTo().defaultContent();
    return this;
  }

  public CheckoutPage selectAcceptTerms() {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconLocator));
    termsCheckBox.click();
    return this;
  }

  public OrderReceivedPage order() {
    orderButton.click();
    return new OrderReceivedPage(driver);
  }
}