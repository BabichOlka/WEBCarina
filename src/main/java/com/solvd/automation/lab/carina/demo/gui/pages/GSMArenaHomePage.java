package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automation.lab.carina.demo.constant.TimeConstant;
import com.solvd.automation.lab.carina.demo.data.TestData;
import com.solvd.automation.lab.carina.demo.gui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class GSMArenaHomePage extends AbstractPage {
    @FindBy()
    List<ExtendedWebElement> phoneLink;

    @FindBy(xpath = "//*[contains(text(), %s)]")
    private ExtendedWebElement elementByText;

    private ExtendedWebElement loginIconOfRegisteredUser;
    @FindBy(id = "login-popup2")
    private ExtendedWebElement loginFormParent;
        @FindBy(xpath = "//h3[text()='Login failed.']")
    private ExtendedWebElement wrongPasswordLink;
    @FindBy(xpath = "//p[text()='Reason: Wrong password.']")
    private ExtendedWebElement wrongPasswordReasonLink;
    @FindBy(xpath = "//h3[text()='Login successful.']")
    private ExtendedWebElement loginSuccessMessage;

    @FindBy(xpath = "//div[contains(@class, 'top-bar')]")
    private HeaderComponent header;

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

    public boolean isPageOpened(final long timeout) {
        return header.isUIObjectPresent(timeout);
    }

    public GSMPhonePage openPhonePageFromGrid(int i) {
        phoneLink.get(i).click();
        return new GSMPhonePage(driver);
    }



    public String getLoginFaultMessage() {
        return wrongPasswordLink.getText();
    }


    public String getLoginSuccessMessage() {
        assertElementPresent(loginSuccessMessage);
        return loginSuccessMessage.getText();
    }

    public String getloginFauitDescription() {
        return wrongPasswordReasonLink.getText();
    }
}
