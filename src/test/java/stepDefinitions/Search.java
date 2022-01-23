package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CelebrityPage;
import pages.MainPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static factory.DriverFactory.getDriver;
import static utils.ElementUtil.editDob;
import static utils.ElementUtil.wait;

@Slf4j
public class Search {
    public static Map<String, ArrayList<String>> spouseDOB = new HashMap<>();
    private CelebrityPage celebrityPage = new CelebrityPage(getDriver());
    private MainPage mainPage = new MainPage(getDriver());

    @And("User verify simple searchbar is present")
    public void userVerifySimpleSearchbarIsPresent() {
        Assert.assertTrue(mainPage.verifySimpleSearchExist());
    }

    @And("User search a celebrity name and extract the data")
    public void userSearchACelebrityNameAndExtractTheData(DataTable dataTable) {
        List<String> names = dataTable.asList(String.class);

        for (String name : names) {
            mainPage.clickSearch();
            mainPage.typeName(name);
            mainPage.pressEnter();
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(celebrityPage.dob)));
            Assert.assertTrue(celebrityPage.getCelebrityPageTitle(name));

            spouseDOB.put(name, new ArrayList<>());
            spouseDOB.get(name).add(editDob(" DOB: " + celebrityPage.getDOB().trim()));
            spouseDOB.get(name).add(" Spouse: " + celebrityPage.getSpouseDetails().trim());
        }

    }

    @Then("user write extracted data in logs")
    public void userWriteExtractedDataInLogs() {
        spouseDOB.forEach((key, value) -> log.info(key + ", " + value));
    }
}
