package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderReceivedPOM {



    WebDriver driver;

    public OrderReceivedPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "woocommerce-order-overview__order")
    WebElement orderNumberElement;

    @FindBy(linkText = "My account")
    WebElement myAccountLink;



    public String getOrderNumber(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(orderNumberElement));
        String orderFullText = orderNumberElement.getText();
        String orderNumber = orderFullText.replaceAll("[^0-9]", "");
        return orderNumber;
    }

    public void goToAccountPage(){
        myAccountLink.click();
    }
}

