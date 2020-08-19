package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automation.lab.carina.demo.constant.TimeConstant;
import com.solvd.automation.lab.carina.demo.gui.components.HeaderComponent;
import com.solvd.automation.lab.carina.demo.gui.components.SearchBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GSMArenaHomePage extends AbstractPage {


    @FindBy(xpath = "//a[@class=\"module-phones-link\"]")
    List<ExtendedWebElement> phoneLink;

    @FindBy(xpath = "//div[contains(@class, 'top-bar')]")
    private HeaderComponent header;
    @FindBy(xpath = "//*[@id=\"social-connect\"]/a[8]/span")
    private ExtendedWebElement signUpFromParent;

    @FindBy(xpath = "//h3[text()='Login failed.']")
    private ExtendedWebElement wrongPasswordLink;
    @FindBy(id = "topsearch-text")
    private ExtendedWebElement searchBar;

    public GSMArenaHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {

        return isPageOpened(TimeConstant.PAGE_OPENED_TO);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public String getLoginFaultMessage() {
        return wrongPasswordLink.getText();
    }

    public boolean isPageOpened(final long timeout) {
        return header.isUIObjectPresent(timeout);
    }

    public GSMPhonePage openPhonePageFromGrid(int i) {
        phoneLink.get(i).click();
        return new GSMPhonePage(driver);
    }
    public GSMArenaSignUpPage openGSMArenaSignUpPage() {
        signUpFromParent.click();
        return new GSMArenaSignUpPage(driver);
    }
    public SearchBar openGSMArenaSearchBar() {
        searchBar.click();
        return new SearchBar(driver);
    }

}




