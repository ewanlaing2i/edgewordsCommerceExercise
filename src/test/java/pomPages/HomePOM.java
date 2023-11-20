package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePOM extends BasePOM{



    WebDriver driver;

    public HomePOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Shop")
    WebElement shopLink;







    public void enterShop(){
        shopLink.click();
    }




}

