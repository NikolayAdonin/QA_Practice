import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverMethodsTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(WebDriverMethodsTest.class);
    String env = System.getProperty("browser", "chrome");
    String site = "https://www.dns-shop.ru/";

    @BeforeEach
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.getDriver(env.toLowerCase());
        logger.info("Драйвер остановлен");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен");
        }
    }

    @Test
    public void searchNTSTest() {
        driver.get(site);
        logger.info("Открыта сраница Yandex - " + site);

        String title = driver.getTitle();
        logger.info("title - " + title.toString());

        String currentUrl = driver.getCurrentUrl();
        logger.info("current URL - " + currentUrl.toString());

        String searchInputXpath = "(//*[@placeholder=\"Поиск по сайту\"])[1]";
        WebElement searchInput = driver.findElement(By.xpath(searchInputXpath));
        String searchText = "Samsung";
        searchInput.sendKeys(searchText);

        String searchButtonXpath = "(//span[@class=\\\"ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch\\\"])[1]";
        WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
        searchButton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
