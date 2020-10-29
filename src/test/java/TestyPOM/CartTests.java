package TestyPOM;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CartTests extends BaseTest{

  private String productId = "386";
  private String productUrl = "https://fakestore.testelka.pl/product/egipt-el-gouna/";
  private String categoryUrl = "https://fakestore.testelka.pl/product-category/windsurfing/";

  String[] productPages = {"/egipt-el-gouna/","/wspinaczka-via-ferraty/", "/wspinaczka-island-peak/",
  "/fuerteventura-sotavento/", "/grecja-limnos/", "/windsurfing-w-karpathos/",
  "/wyspy-zielonego-przyladka-sal/","/wakacje-z-yoga-w-kraju-kwitnacej-wisni",
  "/wczasy-relaksacyjne-z-yoga-w-toskani/", "/yoga-i-pilates-w-hiszpanii/"};

  @Test
  public void addToCartFromProductPageTest(){
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.footer.closeDemoNotice();
    boolean isProductInCart = productPage.addToCart().viewCart().isProductInCart(productId);

    Assertions.assertTrue(isProductInCart, "Remove button was not found for a product id=" + productId + ". Was the product added to cart?");
  }

  @Test
  public void addToCartFromCategoryPageTest(){
    CategoryPage categoryPage = new CategoryPage(driver).goTo(categoryUrl);
    categoryPage.footer.closeDemoNotice();
    boolean isProductInCart = categoryPage.addToCart(productId).viewCart().isProductInCart(productId);

    Assertions.assertTrue(isProductInCart, "Remove button was not found for a product id=" + productId + ". Was the product added to cart?");
  }

  @Test
  public void addOneProductTenTimesTest(){
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.footer.closeDemoNotice();
    int productQuantity = productPage.addToCart(10).viewCart().getProductQuantity();

    Assertions.assertEquals(10, productQuantity, "Remove button was not found for a product id=" + productId + " Was the product added to cart?");
  }

  @Test
  public void addTenProductsToCartTest(){
    ProductPage productPage = new ProductPage(driver);
    for (String product: productPages) {
      productPage.goTo("https://fakestore.testelka.pl/product" + product).addToCart();
    }
    //productPage.viewCart(); -> viewCart as element of ProductPage (green button)
    //viewCart as element of the page header:
    int numberOfItems = productPage.header.viewCart().getNumberOfProducts();

    Assertions.assertEquals(10, numberOfItems, "Number of items in the cart is not correct. Expected 10, but was: " + numberOfItems);
  }

  @Test
  public void changeAmountOfSelectedTripInCartTest() {
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.footer.closeDemoNotice();
    int numberOfItems = productPage.addToCart().viewCart().changeNumberOfProducts(8).updateCart().getProductQuantity();

    Assertions.assertEquals(8, numberOfItems, "Changed number of items in the cart is not correct. Expected 5, but was: " + numberOfItems);
  }

  @Test
  public void removeProductInCartTest() {
    ProductPage productPage = new ProductPage(driver).goTo(productUrl);
    productPage.footer.closeDemoNotice();
    boolean isCartEmpty = productPage.addToCart().viewCart().removeProduct(productId).isCartEmpty();

    Assertions.assertTrue(isCartEmpty, "Cart is not empty");
  }
}