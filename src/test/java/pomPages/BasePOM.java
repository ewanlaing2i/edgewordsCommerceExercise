package pomPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePOM {

    @FindBy(className = "woocommerce-store-notice__dismiss-link")
    WebElement dismissLink;


    public void dismissWarning(){
        dismissLink.click();
    }

}
