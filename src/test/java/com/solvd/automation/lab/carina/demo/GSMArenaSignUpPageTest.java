package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.automation.lab.carina.demo.locators.SignUpPageLocators;
import com.solvd.automation.lab.carina.demo.locators.SignUpPageLocators.*;
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



public class GSMArenaSignUpPageTest extends AbstractTest {

    private WebDriver driver = getDriver();
    private static DriverHelper driverHelper;
    private static final Logger LOGGER = Logger.getLogger(GSMArenaSignUpPageTest.class);

    private static final List<By> SIGN_UP_FORM_ELEMENTS = Arrays.asList(
            SignUpPageLocators.TITLE,
            SignUpPageLocators.SIGN_UP_WHY_TITLE,
            SignUpPageLocators.SIGN_UP_FORM
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
        SIGN_UP_FORM_ELEMENTS.forEach(locator ->
                softAssert.assertNotNull(
                        driverHelper.findExtendedWebElement(locator),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
    }

    @Test
    public void testSignFormIsOpened() {
        ExtendedWebElement signUpButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        signUpButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT),
                "SignUpForm form was not opened!");

        SoftAssert softAssert = new SoftAssert();
        SIGN_UP_FORM_ELEMENTS.forEach(locator ->
                softAssert.assertNotNull(
                        driverHelper.findExtendedWebElement(locator),
                        String.format("%s is not found on the login form.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
    }

    @Test
    public void testCorrectData() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT), "SignUpForm form was not opened!");
        driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).type(TestData.CORRECT_NICKNAME);
        driverHelper.findExtendedWebElement(SignUpForm.EMAIL_INPUT).type(TestData.CORRECT_LOGIN);
        driverHelper.findExtendedWebElement(SignUpForm.PASSWORD_INPUT).type(TestData.CORRECT_PASSWORD);
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_1_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_2_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.SUBMIT_BUTTON).click();

        String actualSuccessMessage =
                driverHelper.findExtendedWebElement(SignUpForm.SIGNUP_SUCCESS_LINK).getText();

        Assert.assertEquals(actualSuccessMessage, TestData.SIGNUP_SUCCESS_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }

    @Test
    public void testExistingNickName() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT), "SignUpForm form was not opened!");
        driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).type(TestData.EXISTING_NICKNAME);
        SoftAssert softAssert = new SoftAssert();
        String actualNickMessage =
                driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).getAttribute("validationMessage");

        softAssert.assertEquals(actualNickMessage, TestData.SIGNUP_EXISTING_NICKNAME_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }

    @Test
    public void testShortNickName() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT), "SignUpForm form was not opened!");
        driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).type(TestData.SHORT_NICKNAME);
        SoftAssert softAssert = new SoftAssert();
        String actualNickMessage =
                driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).getAttribute("validationMessage");

        softAssert.assertEquals(actualNickMessage, TestData.SIGNUP_SHORT_NICKNAME_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }

    @Test
    public void testExistingEmail() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT), "SignUpForm form was not opened!");
        driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).type(TestData.CORRECT_NICKNAME);
        driverHelper.findExtendedWebElement(SignUpForm.EMAIL_INPUT).type(TestData.EXISTING_LOGIN);
        driverHelper.findExtendedWebElement(SignUpForm.PASSWORD_INPUT).type(TestData.CORRECT_PASSWORD);
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_1_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_2_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.SUBMIT_BUTTON).click();
        SoftAssert softAssert = new SoftAssert();

        String actualEmailMessage =
                driverHelper.findExtendedWebElement(SignUpForm.SIGNUP_EXISTING_EMAIL_ERROR_LINK).getText();

        softAssert.assertEquals(actualEmailMessage, TestData.SIGNUP_EXISTING_EMAIL_MESSAGE_EXPECTED,
                "Message is not as expected!");

    }

    @Test
    public void testInvalidEmail() {
        ExtendedWebElement loginButton = driverHelper.findExtendedWebElement(Header.SIGN_UP);
        loginButton.click();
        Assert.assertNotNull(driverHelper.findExtendedWebElement(SignUpForm.SIGN_UP_FORM_PARENT), "SignUpForm form was not opened!");
        driverHelper.findExtendedWebElement(SignUpForm.NICKNAME_INPUT).type(TestData.CORRECT_NICKNAME);
        driverHelper.findExtendedWebElement(SignUpForm.EMAIL_INPUT).type(TestData.INVALID_LOGIN);
        driverHelper.findExtendedWebElement(SignUpForm.PASSWORD_INPUT).type(TestData.CORRECT_PASSWORD);
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_1_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.I_AGREE_2_CHECKBOX).click();
        driverHelper.findExtendedWebElement(SignUpForm.SUBMIT_BUTTON).click();
        SoftAssert softAssert = new SoftAssert();

        String actualEmailMessage =
                driverHelper.findExtendedWebElement(SignUpForm.SIGNUP_INVALID_EMAIL_ERROR_LINK).getText();

        softAssert.assertEquals(actualEmailMessage, TestData.SIGNUP_INVALID_EMAIL_MESSAGE_EXPECTED,
                "Message is not as expected!");

    }


}
