package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.sharedDictionary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testBase {

    private WebDriver driver;
    private final sharedDictionary sharedDictionary;

    public testBase(sharedDictionary sharedDictionary){
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


