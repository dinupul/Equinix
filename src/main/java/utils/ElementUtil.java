package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ElementUtil {

    public static String extractSpouseDetails(By xp1, By xp2, WebDriver driver) {
        String spouseDetails = null;
        List webelement = driver.findElements(xp1);


        if (webelement.size() == 0) {
            webelement = driver.findElements(xp2);


            if (webelement.size() == 0) {
                spouseDetails = "Spouse details unavailable or PROVIDED OBJECTS DOESNT AVAILABLE";

            } else {
                spouseDetails = driver.findElement(xp2).getText().trim();
            }
        } else {
            spouseDetails = driver.findElement(xp1).getText().trim();
        }
        return spouseDetails;
    }

    public static String trimDob(String string){
        String[] array = string.split("\\(age");
        return array[0] ;
    }
}
