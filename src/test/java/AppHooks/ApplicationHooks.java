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
import static stepDefinitions.Search.spouseDOB;
import static utils.ElementUtil.reportlogs;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getproperty() {
        configReader = new ConfigReader();
        prop = configReader.initProp();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.manage().deleteAllCookies();
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

    @After(order = 2)
    public void addLogs(Scenario sc) {
        if (sc.getName().equals("Extract DOB and Spouse Details")) {
            String details = reportlogs(spouseDOB);
            sc.log(details);
        }

    }
}
