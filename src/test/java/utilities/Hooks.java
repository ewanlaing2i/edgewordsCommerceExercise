package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.SharedDictionary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private WebDriver driver;
    private final SharedDictionary sharedDictionary;

    public Hooks(SharedDictionary sharedDictionary){
        this.sharedDictionary = sharedDictionary;
    }

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        sharedDictionary.addToDictionary("mydriver", driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}


