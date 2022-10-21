import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    String env = System.getProperty("browser","chrome");

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver = WebDriverFactory.getDriver(env.toLowerCase());
        logger.info("Драйвер стартовал.");
    }

    @AfterEach
    public void setDown(){
        if(driver != null){
            driver.quit();
            logger.info("Драйвер остановлен");
        }
    }

    @Test
    public void openPage(){
        var site = "https://ya.ru/";
        driver.get(site);
        logger.info("Открыта страница Yandex - " + site);
    }

}
