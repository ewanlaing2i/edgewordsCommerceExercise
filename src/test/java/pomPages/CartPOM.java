package pomPages;

import org.example.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;


public class CartPOM {


    WebDriver driver;

    public CartPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "strong > .amount.woocommerce-Price-amount > bdi")
    WebElement subTotal;

    @FindBy(id = "coupon_code")
    WebElement couponTextBox;

    @FindBy(name = "apply_coupon")
    WebElement couponButton;

    @FindBy(css = ".cart-discount.coupon-edgewords > th")
    WebElement couponApplied;


    @FindBy(linkText = "[Remove]")
    WebElement removeCouponButton;

    @FindBy(linkText = "My account")
    WebElement accountPageLink;

    @FindBy(css = ".alt.button.checkout-button.wc-forward")
    WebElement checkoutLink;


    public BigDecimal getCurrentValue(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(subTotal));
        String subtotalText = subTotal.getText();
        String totalWithoutCurrencySymbol = subtotalText.replace("£", "");
        BigDecimal totalAsBD = BigDecimal.valueOf(Double.parseDouble(totalWithoutCurrencySymbol));
        BigDecimal shippingValue = BigDecimal.valueOf(3.95);
        BigDecimal currentValue = totalAsBD.subtract(shippingValue);
        System.out.println("The current basket item value is " + currentValue);
        return currentValue;

    }

    public void applyCoupon(String couponCode){
        couponTextBox.click();
        couponTextBox.sendKeys(couponCode);
        couponButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(couponApplied));
    }

    public void removeCoupon(){     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(removeCouponButton));
        wait.until(ExpectedConditions.elementToBeClickable(removeCouponButton));

        removeCouponButton.click();
    }

    public void goToAccountPage(){
        accountPageLink.click();
    }


    public void checkout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(subTotal));
        checkoutLink.click();
    }






}

