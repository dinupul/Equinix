package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public WebDriver driver;

    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the driver on the basis of given browser
     *
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
            System.out.println("Please pass the correct browser value " + browser);
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
