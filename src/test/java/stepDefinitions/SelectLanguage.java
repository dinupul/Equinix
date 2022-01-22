package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;
import utils.ConfigReader;
import utils.ExcelReader;

import java.io.IOException;
import java.util.Properties;

public class SelectLanguage {
    private String titleMain;
    private ConfigReader configReader = new ConfigReader();
    Properties prop = configReader.initprop();
    private ExcelReader excel = new ExcelReader();


    private MainPage mainPage = new MainPage(DriverFactory.getDriver());

    @Given("User is already available on wikipedia main page")
    public void userIsAlreadyAvailableOnWikipediaMainPage() {
        String url = prop.getProperty("URL");
        DriverFactory.getDriver().get(url);
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
            mainPage.clickLanguage();
            mainPage.verifySimpleSearchExist();
            Assert.assertEquals(mainPage.verifyLanguageRender(),data[i][1]);
        }
    }

    @And("User Verify Language is changed to english finally")
    public void userVerifyLanguageIsChangedToEnglishFinally() {
        Assert.assertEquals(mainPage.verifyLanguageRender(),"Read");
    }

}
