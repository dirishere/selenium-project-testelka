package TestyPOM;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Test;

public class PaymentTest extends BaseTest {

  private String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";

  private String name = "Daria";
  private String lastName = "Testowa";
  private String countryCode = "PL";
  private String address = "Testelkowa 2/15";
  private String postalCode = "80-333";
  private String city = "Bydgoszcz";
  private String phone = "800800800";
  private String emailAddress = "test@abctest123.pl";


  @Test
  public void buyWithoutAccountTest(){
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.footer.closeDemoNotice();
    CartPage cartPage = productPage.addToCart().viewCart();
    CheckoutPage checkoutPage = cartPage.goToCheckout();
    checkoutPage
            .typeName(name)
            .typeLastName(lastName)
            .chooseCountry(countryCode)
            .typeAddress(address)
            .typePostalCode(postalCode)
            .typeCity(city)
            .typePhone(phone)
            .typeEmail(emailAddress);
  }
}
