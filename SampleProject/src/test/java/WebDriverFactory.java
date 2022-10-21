import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер для браузера Google Chrome");
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер для браузера Mozilla Firefox");
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                logger.info("Драйвер для браузера Edge");
                return new EdgeDriver();
            case "ie":
                WebDriverManager.iedriver().setup();
                logger.info("Драйвер для браузера Internet Explorer");
                return new InternetExplorerDriver();
            case "opera":
                WebDriverManager.operadriver().setup();
                logger.info("Драйвер для браузера Opera");
                return new OperaDriverManager().getWebDriver();
            default:
                throw new RuntimeException("Введено некорректное название браузера");
        }
    }

}
