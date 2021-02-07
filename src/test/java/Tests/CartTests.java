package Tests;

import PageObjects.CategoryPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class CartTests extends BaseTest {
  @Test
  public void addToCartFromProductPageTest(){
    ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
    productPage.footer.closeDemoNotice();
    boolean isProductInCart = productPage.addToCart().viewCart().isProductInCart(testData.getProduct().getId());

    Assertions.assertTrue(isProductInCart, "Remove button was not found for a product id=" +
            testData.getProduct().getId() + ". Was the product added to cart?");
  }

  @Test
  public void addToCartFromCategoryPageTest(){
    CategoryPage categoryPage = new CategoryPage(driver).goTo(configuration.getBaseUrl() + testData.getCategoryURL());
    categoryPage.footer.closeDemoNotice();
    boolean isProductInCart = categoryPage.addToCart(testData.getProduct().getId()).viewCart().isProductInCart(testData.getProduct().getId());

    Assertions.assertTrue(isProductInCart, "Remove button was not found for a product id=" +
            testData.getProduct().getId() + ". Was the product added to cart?");
  }

  @Test
  public void addOneProductTenTimesTest(){
    ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
    productPage.footer.closeDemoNotice();
    int productQuantity = productPage.addToCart(10).viewCart().getProductQuantity();

    Assertions.assertEquals(10, productQuantity, "Remove button was not found for a product id=" +
            testData.getProduct().getId() + " Was the product added to cart?");
  }

  @Test
  public void addTenProductsToCartTest(){
    ProductPage productPage = new ProductPage(driver);
    for (String product: testData.getProductPages()) {
      productPage.goTo(configuration.getBaseUrl() + "/product" + product).addToCart();
    }
    //productPage.viewCart(); -> viewCart as element of ProductPage (green button)
    //viewCart as element of the page header:
    int numberOfItems = productPage.header.viewCart().getNumberOfProducts();

    Assertions.assertEquals(10, numberOfItems, "Number of items in the cart is not correct. " +
            "Expected 10, but was: " + numberOfItems);
  }

  @Test
  public void changeAmountOfSelectedTripInCartTest() {
    ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
    productPage.footer.closeDemoNotice();
    int numberOfItems = productPage.addToCart().viewCart().changeNumberOfProducts(8).updateCart().getProductQuantity();

    Assertions.assertEquals(8, numberOfItems, "Changed number of items in the cart is not correct. " +
            "Expected 5, but was: " + numberOfItems);
  }

  @Test
  public void removeProductInCartTest() {
    ProductPage productPage = new ProductPage(driver).goTo(configuration.getBaseUrl() + testData.getProduct().getUrl());
    productPage.footer.closeDemoNotice();
    boolean isCartEmpty = productPage.addToCart().viewCart().removeProduct(testData.getProduct().getId()).isCartEmpty();

    Assertions.assertTrue(isCartEmpty, "Cart is not empty");
  }
}