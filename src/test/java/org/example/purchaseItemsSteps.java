package org.example;

import io.cucumber.java.en.*;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import pomPages.*;
import utilities.testBase.*;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class purchaseItemsSteps {

    private WebDriver driver;
    private final sharedDictionary sharedDictionary;

    private BigDecimal expectedResult;

    public purchaseItemsSteps(sharedDictionary sharedDictionary){
        this.sharedDictionary = sharedDictionary;
        this.driver = (WebDriver)sharedDictionary.readDictionary("mydriver");
    }

    @Given("I have added an item to the basket")
    public void i_have_added_an_item_to_the_basket() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
        loginPOM loginPage = new loginPOM(driver);
        homePOM homePage = new homePOM(driver);
        homePage.dismissWarning();
        shopPOM shopPage = new shopPOM(driver);
        loginPage.enterUsername("Ewan.Laing@2itesting.com");
        loginPage.enterPassword("2passiwordtesting");
        loginPage.login();
        homePage.enterShop();
        shopPage.addBeanieToCart();
        shopPage.viewCart();
    }
    @When("I enter a valid discount code")
    public void i_enter_a_valid_discount_code() {
        cartPOM cartPage = new cartPOM(driver);
        BigDecimal totalBeforeDiscount = cartPage.getCurrentValue();
        BigDecimal discount = BigDecimal.valueOf(.85);
        expectedResult = discount.multiply(totalBeforeDiscount);
        System.out.println(expectedResult);
        cartPage.applyCoupon();;
    }
    @Then("My total is reduced by the appropriate amount")
    public void my_total_is_reduced_by_the_appropriate_amount() {
        accountPOM accountPage = new accountPOM(driver);
        cartPOM cartPage = new cartPOM(driver);
        BigDecimal totalAfterDiscount = cartPage.getCurrentValue();
        assertThat("Expected result does not match actual", expectedResult, Matchers.comparesEqualTo(totalAfterDiscount));
        cartPage.goToAccountPage();
        accountPage.logout();
    }

    @When("I checkout the item")
    public void i_checkout_the_item() {
        cartPOM cartPage = new cartPOM(driver);
        checkoutPOM checkoutPage = new checkoutPOM(driver);
        cartPage.checkout();
        checkoutPage.checkoutOrder();
    }
    @Then("The item's order number is present in my current orders")
    public void the_item_s_order_number_is_present_in_my_current_orders() {
        orderReceivedPOM orderRecievedPage = new orderReceivedPOM(driver);
        accountPOM accountPage = new accountPOM(driver);
        ordersPOM ordersPage = new ordersPOM(driver);
        String orderNumber = orderRecievedPage.getOrderNumber();
        System.out.println("expected order number is " + orderNumber);
        orderRecievedPage.goToAccountPage();
        accountPage.goToOrders();
        String orderNumberActual = ordersPage.orderNumberActual();
        System.out.println("actual order number is " + orderNumberActual);
        assertThat("Order numbers do not match",orderNumber, Matchers.comparesEqualTo(orderNumberActual));
        ordersPage.logout();
    }


}
