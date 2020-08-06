package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automation.lab.carina.demo.locators.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class GSMArenaHomePage extends AbstractPage {
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
    @FindBy(xpath = "//a[@href='register.php3']")
    private ExtendedWebElement signUp;
    @FindBy(className = "icon-count")
    private ExtendedWebElement loginIconOfRegisteredUser;

    @FindBy(id = "login-popup2")
    private ExtendedWebElement loginFormParent;
    @FindBy(xpath = "//p[text()='Login']")
    private ExtendedWebElement loginTitle;
    @FindBy(id = "email")
    private ExtendedWebElement emailInput;
    @FindBy(id = "upass")
    private ExtendedWebElement passwordInput;
    @FindBy(id = "nick-submit")
    private ExtendedWebElement submitButton;
    @FindBy(partialLinkText = "I forgot my password")
    private ExtendedWebElement forgotPasswordLink;
    @FindBy(xpath = "//h3[text()='Login failed.']")
    private ExtendedWebElement wrongPasswordLink;
    @FindBy(xpath = "//p[text()='Reason: Wrong password.']")
    private ExtendedWebElement wrongPasswordReasonLink;
    @FindBy
    List<ExtendedWebElement> phoneLink;

    @FindBy(xpath = "//h3[text()='Login successful.']")
    private ExtendedWebElement loginSuccessMessage;


    public List<ExtendedWebElement> heder =
            Arrays.asList(searchBar, tipIcon,
                    twIcon, fbIcon, twIcon, igIcon,
                    ytIcon, rssIcon, loginIcon, signUp);

    public GSMArenaHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {

        return logo.isElementPresent();
    }

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
        return true;
    }

    public GSMPhonePage openPhonePageFromGrid(int i) {
        phoneLink.get(i).click();
        return new GSMPhonePage(driver);
    }

    public GSMArenaSignUpPage openGSMArenaSignUpPage() {
        signUp.click();
        return new GSMArenaSignUpPage(driver);
    }

    public GSMArenaHomePage checkLoginForm() {
        loginIcon.click();
        return this;
    }

    public GSMArenaHomePage userLoginFault() {
        emailInput.type(TestData.INCORRECT_LOGIN);
        passwordInput.type(TestData.INCORRECT_PASSWORD);
        submitButton.click();
        return this;
    }

    public String getLoginFaultMessage() {
        assertElementPresent(wrongPasswordLink);
        return wrongPasswordLink.getText();
    }

    public GSMArenaHomePage userLoginSuccess() {
        emailInput.type(TestData.CORRECT_LOGIN);
        passwordInput.type(TestData.CORRECT_PASSWORD);
        submitButton.click();
        return this;
    }

    public String getLoginSuccessMessage() {
        assertElementPresent(loginSuccessMessage);
        return loginSuccessMessage.getText();
    }

    public GSMArenaHomePage userLoginInvalidEmail() {
        emailInput.type(TestData.BAD_LOGIN);
        passwordInput.type(TestData.CORRECT_PASSWORD);
        submitButton.click();
        return this;
    }


    public String getEmailInput() {
        assertElementPresent(emailInput);
        return emailInput.getAttribute("validationMessage");
    }

    public GSMArenaHomePage invalidPassword() {
        emailInput.type(TestData.CORRECT_LOGIN);
        passwordInput.type(TestData.BAD_PASSWORD);
        submitButton.click();
        return this;
    }

    public String getPasswordInput() {
        assertElementPresent(passwordInput);
        return passwordInput.getAttribute("validationMessage");
    }

    public GSMArenaHomePage incorrectPasswordLogIn() {
        emailInput.type(TestData.CORRECT_LOGIN);
        passwordInput.type(TestData.INCORRECT_PASSWORD);
        submitButton.click();
        return this;
    }

    public String getloginFauitDescription() {
        assertElementPresent(wrongPasswordReasonLink);
        return wrongPasswordReasonLink.getText();
    }

}




