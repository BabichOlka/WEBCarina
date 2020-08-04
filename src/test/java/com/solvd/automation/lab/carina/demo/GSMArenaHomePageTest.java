package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.automation.lab.carina.demo.locators.LoginPageLocators;
import com.solvd.automation.lab.carina.demo.locators.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.solvd.automation.lab.carina.demo.locators.HomePageLocators.Header;
import static com.solvd.automation.lab.carina.demo.locators.HomePageLocators.LoginForm;


public class GSMArenaHomePageTest extends AbstractTest {

    private WebDriver driver = getDriver();
    private static DriverHelper driverHelper;

    private static final Logger LOGGER = Logger.getLogger(GSMArenaHomePageTest.class);

    private static final List<By> HEADER_ELEMENTS = Arrays.asList(
            Header.TIP_ICON, Header.SEARCH_BAR, Header.FB_ICON, Header.TW_ICON, Header.IG_ICON, Header.YT_ICON,
            Header.RSS_ICON, Header.LOGIN_ICON, Header.SIGN_UP);

    private static final List<By> LOGIN_FORM_ELEMENTS = Arrays.asList(
            LoginForm.LOGIN_TITLE, LoginForm.EMAIL_INPUT, LoginForm.PASSWORD_INPUT, LoginForm.SUBMIT_BUTTON, LoginForm.FORGOT_PASSWORD_LINK
    );


    @BeforeTest
    public void initializeDriverHelper() {
        LOGGER.info("Will initialize driver helper.");
        driverHelper = new DriverHelper(driver);
        LOGGER.info("Driver helper was initialized.");

    }

    @BeforeMethod
    public void openHomePage() {
        getDriver().get(Configuration.get(Configuration.Parameter.URL));
        ExtendedWebElement logo = driverHelper.findExtendedWebElement(Header.LOGO);
        Assert.assertTrue(logo.isElementPresent(), "Home page was not opened!");
    }

    @Test
    public void validateBaseElementsOnPageHeader() {
        SoftAssert softAssert = new SoftAssert();
        HEADER_ELEMENTS.forEach(locator ->
                softAssert.assertNotNull(
                        driverHelper.findExtendedWebElement(locator),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
    }

    @Test(description = "Scenario 0")
    public void testLoginFormIsOpened() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        SoftAssert softAssert = new SoftAssert();
        LOGIN_FORM_ELEMENTS.forEach(locator ->
                softAssert.assertNotNull(
                        driverHelper.findExtendedWebElement(locator),
                        String.format("%s is not found on the login form.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
    }

    @Test
    public void testUserLoginFault() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT).type(TestData.INCORRECT_LOGIN);
        driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT).type(TestData.INCORRECT_PASSWORD);

        driverHelper.findExtendedWebElement(LoginForm.SUBMIT_BUTTON).click();

        String actualFaultMessage =
                driverHelper.findExtendedWebElement(LoginPageLocators.LoginFaultScreen.LOGIN_FAULT_MESSAGE).getText();
        Assert.assertEquals(actualFaultMessage, TestData.LOGIN_FAILED_MESSAGE_EXPECTED,
                "Login fault message is not as expected!");
    }

    @Test(description = "Scenario 1")
    public void testCorrectUserCorrectLogin() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT).type(TestData.CORRECT_LOGIN);
        driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT).type(TestData.CORRECT_PASSWORD);

        driverHelper.findExtendedWebElement(LoginForm.SUBMIT_BUTTON).click();

        ExtendedWebElement iconOfRegisteredUser = driverHelper.findExtendedWebElement((Header.LOGIN_ICON_Of_REGISTERED_USER));
        Assert.assertTrue(iconOfRegisteredUser.isPresent(),
                "Information about the registered user is not present!");
    }
    @Test (description = "Scenario 2")
    public void testUserLoginInvalidEmail() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT).type("sssss");
        driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT).type(TestData.CORRECT_PASSWORD);

        driverHelper.findExtendedWebElement(LoginForm.SUBMIT_BUTTON).click();

        ExtendedWebElement actualErrorOfEmailMessage =
                driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT);
        Assert.assertEquals(actualErrorOfEmailMessage.getAttribute("validationMessage"), TestData.LOGIN_ERROR_OF_EMAIL_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }
    @Test (description = "Scenario 3")
    public void testUserLoginInvalidPassword() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT).type(TestData.CORRECT_LOGIN);
        driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT).type("ss");

        driverHelper.findExtendedWebElement(LoginForm.SUBMIT_BUTTON).click();

        ExtendedWebElement actualErrorOfPasswordMessage =
                driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT);
        Assert.assertEquals(actualErrorOfPasswordMessage.getAttribute("validationMessage"), TestData.LOGIN_ERROR_OF_PASSWORD_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }
    @Test (description = "Scenario 4")
    public void testUserLoginWrongData() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.LOGIN_ICON);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(LoginForm.LOGIN_FORM_PARENT), "Login form was not opened!");

        driverHelper.findExtendedWebElement(LoginForm.EMAIL_INPUT).type(TestData.CORRECT_LOGIN);
        driverHelper.findExtendedWebElement(LoginForm.PASSWORD_INPUT).type("1234567");

        driverHelper.findExtendedWebElement(LoginForm.SUBMIT_BUTTON).click();

        String actualErrorOfWrongPasswordMessage =
                driverHelper.findExtendedWebElement(LoginForm.WRONG_PASSWORD_LINK).getText()+driverHelper.findExtendedWebElement(LoginForm.WRONG_PASSWORD_REASON_LINK).getText();
        Assert.assertEquals(actualErrorOfWrongPasswordMessage, TestData.LOGIN_ERROR_OF_WRONG_PASSWORD_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }
}
