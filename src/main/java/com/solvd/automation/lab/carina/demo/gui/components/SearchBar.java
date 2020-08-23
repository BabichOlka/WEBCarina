package com.solvd.automation.lab.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaSignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class SearchBar extends AbstractUIObject {
    @FindBy(id = "topsearch-text")
    private ExtendedWebElement searchBar;

    @FindBy(xpath = " //*[@class=\"search-buttons\"]/a[@class=\"go\"]")
    private ExtendedWebElement buttonGo;

    @FindBy(xpath = " //*[@class=\"search-buttons\"]/a[@class=\"advanced\"]")
    private ExtendedWebElement buttonAdvanced;

    @FindBy(xpath = " //*[@id=\"news\"]/h3")
    private ExtendedWebElement notFoundResultMessage;

    @FindBy(xpath = "//*[@id=\"review-body\"]/div/ul/li")
    private List<ExtendedWebElement> searchResult;


    public SearchBar(WebDriver driver) {
        super(driver);
    }


    public boolean validateBaseElementsOnPageHeader() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(searchBar.isPresent(),
                        String.format("%s is not found on the page.", searchBar.toString()));
        softAssert.assertNotNull(searchBar.isPresent(),
                String.format("%s is not found on the page.", buttonGo.toString()));
        softAssert.assertNotNull(searchBar.isPresent(),
                String.format("%s is not found on the page.",buttonAdvanced.toString()));
                                softAssert.assertAll();
        return true;}

    public SearchBar openGSMArenaSearchBar() {
        searchBar.click();
        return new SearchBar(driver);
    }
    public SearchBar search(String searchInfo) {
        searchBar.click();
        searchBar.type(searchInfo);
        buttonGo.click();
        return this;
    }
    public String getNotFoundResultMessage() {
        assertElementPresent(notFoundResultMessage);
        return notFoundResultMessage.getText();
    }
    public boolean searchResult(String searchInfo) {
        searchBar.click();
        searchBar.type(searchInfo);
        buttonGo.click();
        SoftAssert softAssert = new SoftAssert();
        searchResult.forEach(locator ->
                softAssert.assertTrue(
                        locator.getText().toLowerCase().contains(searchInfo.toLowerCase()),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                ));
        softAssert.assertAll();
        return true;
    }
}
