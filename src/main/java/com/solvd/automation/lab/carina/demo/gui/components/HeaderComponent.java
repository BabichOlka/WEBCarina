package com.solvd.automation.lab.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automation.lab.carina.demo.constant.TimeConstant;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaSignUpPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(id = "logo")
    private ExtendedWebElement logo;

    @FindBy(id = "topsearch")
    private ExtendedWebElement searchBar;

    @FindBy(className = "tip-icon")
    private ExtendedWebElement tipIcon;

    @FindBy(className = "fb-icon")
    private ExtendedWebElement fbIcon;

    @FindBy(className = "tw-icon")
    private ExtendedWebElement twIcon;

    @FindBy(className = "ig-icon")
    private ExtendedWebElement igIcon;

    @FindBy(className = "yt-icon")
    private ExtendedWebElement ytIcon;

    @FindBy(className = "rss-icon")
    private ExtendedWebElement rssIcon;

    @FindBy(id = "login-active")
    private ExtendedWebElement loginIcon;

    @FindBy(xpath = ".//a[@href='register.php3']")
    private ExtendedWebElement signUp;

    @FindBy(xpath = ".//span[@id='login-popup2']")
    private LoginForm loginForm;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public boolean isUIObjectPresent() {
        return isUIObjectPresent(TimeConstant.PAGE_OPENED_TO);
    }
    public List<ExtendedWebElement> heder =
            Arrays.asList(searchBar, tipIcon,
                    twIcon, fbIcon, twIcon, igIcon,
                    ytIcon, rssIcon, loginIcon, signUp);

    public boolean validateBaseElementsOnPageHeader() {
        SoftAssert softAssert = new SoftAssert();
        heder.forEach(locator ->
                softAssert.assertNotNull(
                        locator.isPresent(),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
        return true;}

    public boolean isUIObjectPresent(final long timeout) {
        return logo.isPresent(timeout);
    }

    public GSMArenaSignUpPage openGSMArenaSignUpPage() {
        signUp.click();
        return new GSMArenaSignUpPage(driver);
    }


    public LoginForm getLoginForm() {
        loginIcon.click();
        return loginForm;
    }

}