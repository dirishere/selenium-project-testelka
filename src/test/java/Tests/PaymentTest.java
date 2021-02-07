package Tests;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.OrderReceivedPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest extends BaseTest {
  @Test
  public void buyWithoutAccountTest(){
    ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
    productPage.footer.closeDemoNotice();
    CartPage cartPage = productPage.addToCart().viewCart();
    CheckoutPage checkoutPage = cartPage.goToCheckout();
    OrderReceivedPage orderReceivedPage = checkoutPage
            .typeName(testData.getCustomer().getName())
            .typeLastName(testData.getCustomer().getLastName())
            .chooseCountry(testData.getAddress().getCountryCode())
            .typeAddress(testData.getAddress().getStreet())
            .typePostalCode(testData.getAddress().getPostalCode())
            .typeCity(testData.getAddress().getCity())
            .typePhone(testData.getContact().getPhone())
            .typeEmail(testData.getContact().getEmailAddress())
            .typeCardNumber(testData.getCard().getNumber())
            .typeCardExpirationDate(testData.getCard().getExpirationDate())
            .typeCvcCode(testData.getCard().getCvc())
            .selectAcceptTerms()
            .order();
    boolean isOrderSuccessful = orderReceivedPage.isOrderSuccessful();
    Assertions.assertTrue(isOrderSuccessful, "No order successful message found.");
  }
}
