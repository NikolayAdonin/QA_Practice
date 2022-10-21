import com.sun.jna.Platform;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserOptionTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(BrowserWindowsTest.class);
    String site = "https://www.dns-shop.ru/";

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Драйвер для браузера Google Chrome");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("unexpectedAlertBehaviour","dismiss");
        capabilities.setCapability("unhandledPromtBehaviour","dismiss");

        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME);

        ChromeOptions options = new ChromeOptions();
        options.setCapability("pageLoadStrategy", PageLoadStrategy.NORMAL);

        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,false);

        options.merge(capabilities);

        //for chrome and opera
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        //for firefox
        //FirefoxOptions options = new FirefoxOptions();
        //options.setCapability("pageLoadStrategy", PageLoadStrategy.NORMAL);
        //options.addArguments("--kiosk");
        //options.addArguments("-headless");

        return new ChromeDriver(options);
    }

    @BeforeEach
    public void setUp() {
        driver = getDriver();
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
    public void browserOptionsTest() {
        driver.get(site);
        logger.info("Открыта страница DNS - " + site);

        String searchInputXpath = "(//*[@placeholder=\"Поиск по сайту\"])[2]";
        WebElement searchInput = driver.findElement(By.xpath(searchInputXpath));
        String searchText = "Samsung";
        searchInput.sendKeys(searchText);

        String searchButtonXpath = "(//span[@class=\"ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch\"])[2]";
        WebElement searchButton = driver.findElement(By.xpath(searchButtonXpath));
        searchButton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
