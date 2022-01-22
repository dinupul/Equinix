package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import pages.CelebrityPage;
import pages.MainPage;
import utils.ConfigReader;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static utils.ElementUtil.trimDob;

@Slf4j
public class Search {
    private ConfigReader configReader = new ConfigReader();
    private Properties prop = configReader.initprop();
    private ExcelReader excel = new ExcelReader();
    private CelebrityPage celebrityPage = new CelebrityPage(DriverFactory.getDriver());
    private MainPage mainPage = new MainPage(DriverFactory.getDriver());

    @And("User verify simple searchbar is present")
    public void userVerifySimpleSearchbarIsPresent() {
        Assert.assertTrue(mainPage.verifySimpleSearchExist());
    }

    @And("User search a celebrity name in and extract the data")
    public void userSearchACelebrityNameInAndExtractTheData(DataTable dataTable) throws IOException {
        String[] data = excel.readExcelSingleColumn();
        String[][] extractedData = new String[data.length][2];
        List<String> names = dataTable.asList(String.class);

        for (String name : names) {
            DriverFactory.getDriver().findElement(mainPage.simpleSearch).click();
            mainPage.typeName(name);
            mainPage.pressEnter();
            DriverFactory.getDriver().findElement(mainPage.simpleSearch).click();
            Assert.assertTrue(celebrityPage.getCelebrityPageTitle(name));

            log.info(trimDob(name + " DOB: " + celebrityPage.getDOB().trim()));
            log.info(name + " Spouse: " + celebrityPage.getSpouseDetails().trim());
            celebrityPage.objectcatcher(extractedData);
        }

    }

    @Then("user write extracted data in logs")
    public void userWriteExtractedDataInLogs() {
    }
}
