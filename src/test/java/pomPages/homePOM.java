package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePOM {



    WebDriver driver;

    public homePOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Shop")
    WebElement shopLink;

    @FindBy(className = "woocommerce-store-notice__dismiss-link")
    WebElement dismissLink;





    public void enterShop(){
        shopLink.click();
    }

    public void dismissWarning(){
        dismissLink.click();
    }



}

