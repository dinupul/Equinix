package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private WebDriver driver;

    //By Locators
    public By language;
    public By simpleSearch = By.xpath("//input[@name='search']");
    public By read = By.xpath("//li[@id='ca-view']");


    //constructor of the page class
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * To set object of the language that should be clicked on runtime
     * @param eleTitle
     */
    public void setObject(String eleTitle) {
        language = By.xpath("//a[@title='" + eleTitle + "']");
    }

    //Page Actions
    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void clickLanguage() {
        driver.findElement(language).click();
    }

    public void clickSearch(){
        driver.findElement(simpleSearch).click();
    }

    public boolean verifySimpleSearchExist() {
        return driver.findElement(simpleSearch).isDisplayed();
    }

    public String verifyLanguageRender() {
        return driver.findElement(read).getText();
    }

    public void pressEnter() {
        driver.findElement(simpleSearch).sendKeys(Keys.ENTER);
    }

    public void typeName(String name) {
        driver.findElement(simpleSearch).sendKeys(name);
    }

}
