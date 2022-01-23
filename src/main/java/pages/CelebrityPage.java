package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.ElementUtil.extractSpouseDetails;

public class CelebrityPage {

    private WebDriver driver;
    private String title;

    //By locator
    public By dob = By.xpath("//th[text()='Born']//following::td[1]");
    public By spouse1 = By.xpath("//span[text()='Spouse(s)']//following::td[1]");
    public By spouse2 = By.xpath("//th[text()='Spouse(s)']//following::td[1]");

    //constructor
    public CelebrityPage(WebDriver driver) {
        this.driver = driver;
    }

    //Page Actions
    public boolean getCelebrityPageTitle(String text) {
        title = driver.getTitle();
        return title.contains(text);
    }

    public String getSpouseDetails() {
        return extractSpouseDetails(spouse1, spouse2, driver);
    }

    public String getDOB() {
        return driver.findElement(dob).getText();
    }

}
