package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class DriverFactory {

    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the driver on the basis of given browser
     * @param browser
     */
    public WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            threadLocalDriver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            threadLocalDriver.set(new FirefoxDriver());
        } else {
            log.error("Please pass a correct browser value " + browser);
        }

        getDriver().manage().window().maximize();
        return getDriver();

    }

    /**
     * used to get the driver with Threadlocal
     * @return
     */
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

}
