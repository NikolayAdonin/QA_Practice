import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TimeOutsTests {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TimeOutsTests.class);
    String env = System.getProperty("browser", "chrome");
    String site = "https://www.dns-shop.ru/";

    @BeforeEach
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.getDriver(env.toLowerCase());
        logger.info("Драйвер стартовал!");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }

    @Test
    public void timeOutsTest1() {
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        driver.get(site);
        logger.info("Открыта страница DNS - " + site);
    }

    @Test
    public void timeOutsTest2() {
        driver.manage().timeouts().pageLoadTimeout(1,TimeUnit.MILLISECONDS);
        try{
            driver.get(site);
        }catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        logger.info("Открыта страница DNS - " + site);
    }

}
