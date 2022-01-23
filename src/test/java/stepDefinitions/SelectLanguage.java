package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import utils.ConfigReader;
import utils.ExcelReader;

import java.io.IOException;
import java.util.Properties;

import static factory.DriverFactory.getDriver;
import static utils.ElementUtil.wait;

public class SelectLanguage {
    private String titleMain;
    private ConfigReader configReader = new ConfigReader();
    Properties prop = configReader.initProp();
    private ExcelReader excel = new ExcelReader();
    private MainPage mainPage = new MainPage(getDriver());

    @Given("User is already available on wikipedia main page")
    public void userIsAlreadyAvailableOnWikipediaMainPage() {
        String url = prop.getProperty("URL");
        getDriver().get(url);
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(mainPage.simpleSearch)));
    }

    @When("User get the title of the home page")
    public void userGetTheTitleOfTheHomePage() {
        titleMain = mainPage.getHomePageTitle();
    }

    @Then("Title of the main page contains {string}")
    public void titleOfTheMainPageContains(String expectedTitle) {
        Assert.assertTrue(titleMain.contains(expectedTitle));
    }

    @Then("User click a language present under language settings tab and verify if they are rendered")
    public void userClickALanguagePresentUnderLanguageSettingsTabAndVerifyIfTheyAreRendered() throws IOException {
        String[][] data = excel.readExcelDoubleColumn();
        for(int i=0 ;i<data.length;i++ ){
            mainPage.setObject(data[i][0]);
            wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(mainPage.language)));
            mainPage.clickLanguage();
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(mainPage.simpleSearch)));
            mainPage.verifySimpleSearchExist();
            Assert.assertEquals(mainPage.verifyLanguageRender(),data[i][1]);
        }
    }

    @And("User Verify Language is changed to english finally")
    public void userVerifyLanguageIsChangedToEnglishFinally() {
        Assert.assertEquals(mainPage.verifyLanguageRender(),"Read");
    }

}
