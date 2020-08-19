package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaSignUpPage;
import com.solvd.automation.lab.carina.demo.gui.TestData;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GSMArenaSignUpPageTest extends AbstractTest {

    private static final Logger LOGGER = Logger.getLogger(GSMArenaSignUpPageTest.class);
    GSMArenaHomePage homePage = new GSMArenaHomePage(getDriver());

    @Test
    public void isPageOpened() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();

        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

    }

    @Test
    public void validateBaseElementsOnPageHeader() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();
        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

        Assert.assertTrue(signUpPage.validateBaseElements(),
                "HomePageBaseElement page is not opened");
    }

    @Test
    public void testCorrectData() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();
        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

        signUpPage.signUp(TestData.CORRECT_NICKNAME,TestData.CORRECT_LOGIN,TestData.CORRECT_PASSWORD);
        signUpPage.clickIAgree1CheckBox();
        signUpPage.clickIAgree2CheckBox();
        signUpPage.clickSubmitButton();
        Assert.assertEquals(signUpPage.getCorrectDataMessage(), TestData.SIGNUP_SUCCESS_MESSAGE_EXPECTED,
                "Message is not as expected!");

    }

    @Test
    public void testExistingNickName() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();
        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

        signUpPage.signUp(TestData.EXISTING_NICKNAME,null,null);
        Assert.assertEquals(signUpPage.getExistingNickNameMessage(), TestData.SIGNUP_EXISTING_NICKNAME_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }

    @Test
    public void testShortNickName() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();
        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

        signUpPage.signUp(TestData.SHORT_NICKNAME,null,null);
        Assert.assertEquals(signUpPage.getShortNickNameMessage(), TestData.SIGNUP_SHORT_NICKNAME_MESSAGE_EXPECTED,
                "Message is not as expected!");
    }

    @Test
    public void testExistingEmail() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMArenaSignUpPage signUpPage = homePage.openGSMArenaSignUpPage();
        Assert.assertTrue(signUpPage.isPageOpened(), "GSMPhone page is not opened");

        signUpPage.signUp(TestData.CORRECT_NICKNAME,TestData.EXISTING_LOGIN,
                TestData.CORRECT_PASSWORD);
        signUpPage.clickIAgree1CheckBox();
        signUpPage.clickIAgree2CheckBox();
        signUpPage.clickSubmitButton();
        Assert.assertEquals(signUpPage.getExistingEmailMessage(), TestData.SIGNUP_EXISTING_EMAIL_MESSAGE_EXPECTED,
                "Message is not as expected!");

    }

}
