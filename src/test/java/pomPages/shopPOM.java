package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class shopPOM {



    WebDriver driver;

    public shopPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//main[@id='main']/ul//a[@href='?add-to-cart=27']")
    WebElement addBeanie;

    @FindBy(className = "cart-contents")
    WebElement cartLink;

    @FindBy(className = "added_to_cart")
    WebElement cartConfirmation;




    public void addBeanieToCart(){

        addBeanie.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(cartConfirmation));
    }

    public void viewCart(){
        cartLink.click();

    }



}

