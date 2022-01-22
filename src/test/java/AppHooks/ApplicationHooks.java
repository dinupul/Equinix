package AppHooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.util.Properties;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getproperty() {
        configReader = new ConfigReader();
        prop = configReader.initprop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.close();
    }

    @After(order = 1)
    public void teardown(Scenario sc) {
        if (sc.isFailed()) {
            // Take screenshot
            String screenShotName = sc.getName().replaceAll(" ", "_");
            byte[] sourcepath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            sc.attach(sourcepath, "image/png", screenShotName);
        }
    }

}
