package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPOM {



    WebDriver driver;

    public CheckoutPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "billing_first_name")
    WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    WebElement lastNameInput;

    @FindBy(id = "billing_address_1")
    WebElement addressInput;

    @FindBy(id = "billing_city")
    WebElement cityInput;

    @FindBy(id = "billing_postcode")
    WebElement postcodeInput;

    @FindBy(id = "billing_phone")
    WebElement phoneInput;

    @FindBy(id = "place_order")
    WebElement placeOrderButton;

    public void checkoutOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys("Ewan");
        lastNameInput.click();
        lastNameInput.clear();
        lastNameInput.sendKeys("Laing");
        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys("101 Fictional Avenue");
        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys("Glasgow");
        postcodeInput.click();
        postcodeInput.clear();
        postcodeInput.sendKeys("G11 6HU");
        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys("12345678912");
        placeOrderButton.click();
    }
}

