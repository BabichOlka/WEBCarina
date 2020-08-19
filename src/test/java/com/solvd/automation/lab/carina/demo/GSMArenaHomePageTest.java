package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
import com.solvd.automation.lab.carina.demo.gui.TestData;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GSMArenaHomePageTest extends AbstractTest {


    private static final Logger LOGGER = Logger.getLogger(GSMArenaHomePageTest.class);

    GSMArenaHomePage homePage = new GSMArenaHomePage(getDriver());


    @Test
    public void testValidateBaseElementsOnPageHeader() {
        homePage.open();
        homePage.getHeader();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getHeader().validateBaseElementsOnPageHeader(),
                "HomePageBaseElement page is not opened");
    }


    @Test
    public void testUserLoginSuccess() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();

        page.getHeader().getLoginForm().login(TestData.EXISTING_LOGIN, TestData.CORRECT_PASSWORD);
        Assert.assertEquals(page.getHeader().getLoginForm().getLoginSuccessMessage(),
                TestData.LOGIN_SUCCESS_MESSAGE_EXPECTED, "Login success message is not as expected!");
    }

    @Test
    public void testUserLoginInvalidEmail() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();

        page.getHeader().getLoginForm().login(TestData.BAD_LOGIN, TestData.CORRECT_PASSWORD);
        Assert.assertEquals(page.getHeader().getLoginForm().getEmailInput(),
                TestData.LOGIN_ERROR_OF_EMAIL_MESSAGE_EXPECTED,
                "Warning message is not as expected!");
    }

    @Test
    public void testInvalidPassword() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();

        page.getHeader().getLoginForm().login(TestData.CORRECT_LOGIN, TestData.BAD_PASSWORD);
        Assert.assertEquals(page.getHeader().getLoginForm().getPasswordInputMessage(),
                TestData.LOGIN_ERROR_OF_PASSWORD_MESSAGE_EXPECTED,
                "Warning message is not as expected!");

    }

    @Test
    public void testIncorrectPasswordLogIn() {
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();

        page.getHeader().getLoginForm().login(TestData.CORRECT_LOGIN, TestData.INCORRECT_PASSWORD);
        Assert.assertEquals(page.getHeader().getLoginForm().getLoginFauitMessage(),
                TestData.SIGNUP_EXISTING_EMAIL_MESSAGE_EXPECTED,
                "Login fault description is not as expected!");
    }
    @Test
    public void testValidateBaseElementsOnSearchBar(){
    GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
    page.open();
    Assert.assertTrue( page.openGSMArenaSearchBar().validateBaseElementsOnPageHeader(),
            "SearchBarBaseElement page is not opened");
}
    @Test
    public void testSearchIncorrectInfo(){
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();
        page.openGSMArenaSearchBar().search(TestData.SEARCHBAR_INCORRECT_DATA);
        Assert.assertEquals( page.openGSMArenaSearchBar().getNotFoundResultMessage(),
                TestData.SEARCHBAR_NOT_FOUND_RESULT_MESSAGE_EXPECTED);
    }
    @Test
    public void testSearchResult(){
        GSMArenaHomePage page = new GSMArenaHomePage(getDriver());
        page.open();

        Assert.assertTrue(page.openGSMArenaSearchBar().searchResult(TestData.SEARCHBAR_INPUT_DATA),
                "SearchResult is not right");
    }

}
