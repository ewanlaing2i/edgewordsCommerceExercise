package org.example;

import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import pomPages.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;

public class PurchaseItemsSteps {

    private WebDriver driver;
    private final SharedDictionary sharedDictionary;

    private BigDecimal expectedResult;

    private BigDecimal totalBeforeDiscount;

    private String orderNumber;

    public PurchaseItemsSteps(SharedDictionary sharedDictionary){
        this.sharedDictionary = sharedDictionary;
        this.driver = (WebDriver)sharedDictionary.readDictionary("mydriver");
    }

    @Given("I have added a Beanie to the basket")
    public void i_have_added_an_item_to_the_basket() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
        LoginPOM loginPage = new LoginPOM(driver);
        HomePOM homePage = new HomePOM(driver);
        homePage.dismissWarning();
        ShopPOM shopPage = new ShopPOM(driver);
        loginPage.enterUsername("Ewan.Laing@2itesting.com");
        loginPage.enterPassword("2passiwordtesting");
        loginPage.login();
        homePage.enterShop();
        shopPage.addBeanieToCart();
        shopPage.viewCart();
    }
    @When("I enter the discount code {string}")
    public void i_enter_a_valid_discount_code(String couponCode) {
        CartPOM cartPage = new CartPOM(driver);
        totalBeforeDiscount = cartPage.getCurrentValue();

        cartPage.applyCoupon(couponCode);;
    }
    @Then("My total is reduced by {string}%")
    public void my_total_is_reduced_by_the_appropriate_amount(String discountAmount) {
        AccountPOM accountPage = new AccountPOM(driver);
        CartPOM cartPage = new CartPOM(driver);
        BigDecimal discountValue = new BigDecimal(discountAmount);
        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal discountPercentage = hundred.subtract(discountValue);
        BigDecimal discount = discountPercentage.divide(hundred, 2, RoundingMode.HALF_DOWN);
        expectedResult = discount.multiply(totalBeforeDiscount);
        System.out.println(expectedResult);
        BigDecimal totalAfterDiscount = cartPage.getCurrentValue();
        assertThat("Expected result does not match actual", expectedResult, Matchers.comparesEqualTo(totalAfterDiscount));
        cartPage.goToAccountPage();
        accountPage.logout();
    }

    @When("I checkout the item")
    public void i_checkout_the_item() {
        CartPOM cartPage = new CartPOM(driver);
        CheckoutPOM checkoutPage = new CheckoutPOM(driver);
        cartPage.checkout();
        checkoutPage.checkoutOrder();
    }

    @Then("I am given an order number")
    public void iAmGivenAnOrderNumber() {
        OrderReceivedPOM orderRecievedPage = new OrderReceivedPOM(driver);
        orderNumber = orderRecievedPage.getOrderNumber();
        System.out.println("expected order number is " + orderNumber);
    }
    @Then("The item's order number is present in my current orders")
    public void the_item_s_order_number_is_present_in_my_current_orders() {
        OrderReceivedPOM orderRecievedPage = new OrderReceivedPOM(driver);
        AccountPOM accountPage = new AccountPOM(driver);
        OrdersPOM ordersPage = new OrdersPOM(driver);
        orderRecievedPage.goToAccountPage();
        accountPage.goToOrders();
        String orderNumberActual = ordersPage.orderNumberActual();
        System.out.println("actual order number is " + orderNumberActual);
        assertThat("Order numbers do not match",orderNumber, Matchers.comparesEqualTo(orderNumberActual));
        ordersPage.logout();
    }



}
