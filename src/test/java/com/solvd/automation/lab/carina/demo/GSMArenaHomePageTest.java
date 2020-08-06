package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
import com.solvd.automation.lab.carina.demo.gui.pages.HomePage;
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


    private static final Logger LOGGER = Logger.getLogger(GSMArenaHomePageTest.class);

    GSMArenaHomePage homePage = new GSMArenaHomePage(getDriver());


    @Test
    public void testValidateBaseElementsOnPageHeader() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.validateBaseElementsOnPageHeader(),
                "HomePageBaseElement page is not opened");
    }

    @Test
    public void testUserLoginFault() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        Assert.assertNotNull(page.checkLoginForm(), "Login form was not opened!");

        page.userLoginFault();
        Assert.assertEquals(page.getLoginFaultMessage(), TestData.LOGIN_ERROR_OF_EMAIL_MESSAGE_EXPECTED, "Login fault message is not as expected!");
    }

    @Test
    public void testUserLoginSuccess() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        Assert.assertNotNull(page.checkLoginForm(), "Login form was not opened!");

        page.userLoginSuccess();
        Assert.assertEquals(page.getLoginSuccessMessage(), TestData.LOGIN_SUCCESS_MESSAGE_EXPECTED, "Login success message is not as expected!");
    }

    @Test
    public void testUserLoginInvalidEmail() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        Assert.assertNotNull(page.checkLoginForm(), "Login form was not opened!");

        page.userLoginInvalidEmail();
        Assert.assertEquals(page.getEmailInput(), TestData.LOGIN_ERROR_OF_EMAIL_MESSAGE_EXPECTED, "Warning message is not as expected!");
    }

    @Test
    public void testInvalidPassword() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        Assert.assertNotNull(page.checkLoginForm(), "Login form was not opened!");

        page.invalidPassword();
        Assert.assertEquals(page.getPasswordInput(), TestData.LOGIN_ERROR_OF_PASSWORD_MESSAGE_EXPECTED, "Warning message is not as expected!");

    }

    @Test
    public void testIncorrectPasswordLogIn() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        Assert.assertNotNull(page.checkLoginForm(), "Login form was not opened!");

        page.incorrectPasswordLogIn();
        Assert.assertEquals(page.getloginFauitDescription(), TestData.SIGNUP_EXISTING_EMAIL_MESSAGE_EXPECTED,
                "Login fault description is not as expected!");
    }


}
