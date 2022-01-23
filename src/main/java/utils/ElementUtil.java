package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static factory.DriverFactory.getDriver;

/**
 * This class consists of utility methods
 */
public class ElementUtil {

    public static String reportlogs(Map<String, ArrayList<String>> details) {
        String alldetails = "";
        for (Map.Entry<String, ArrayList<String>> entry : details.entrySet()) {
            String key = entry.getKey();
            ArrayList value = entry.getValue();
            alldetails += "Celebrity Name:" + key + " " + "" + value + "";
        }
        return alldetails;
    }

    public static String editDob(String string) {
        String[] array = string.split("\\(age");
        return array[0];
    }

    public static Wait wait = new FluentWait(getDriver())
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(2))
            .ignoring(NoSuchElementException.class);

    public static String extractSpouseDetails(By xp1, By xp2, WebDriver driver) {
        String spouseDetails = null;
        List webelement = driver.findElements(xp1);

        if (webelement.size() == 0) {
            webelement = driver.findElements(xp2);


            if (webelement.size() == 0) {
                spouseDetails = "Spouse details unavailables";

            } else {
                spouseDetails = driver.findElement(xp2).getText().trim();
            }
        } else {
            spouseDetails = driver.findElement(xp1).getText().trim();
        }
        return spouseDetails;
    }

}
