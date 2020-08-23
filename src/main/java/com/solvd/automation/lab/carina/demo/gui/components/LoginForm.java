package com.solvd.automation.lab.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends AbstractUIObject {

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


    @FindBy(xpath = "//h3[text()='Login successful.']")
    private ExtendedWebElement loginSuccessMessage;

    public LoginForm(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isEmailInputPresent() {
        return emailInput.isPresent();
    }

    public String getEmailInput() {
        return emailInput.getAttribute("validationMessage");
    }

    public LoginForm login(final String username, final String password) {
        emailInput.type(username);
        passwordInput.type(password);
        submitButton.click();
        return this;
    }

    public String getPasswordInputMessage() {
        return passwordInput.getAttribute("validationMessage");
    }

    public String getLoginSuccessMessage() {
        assertElementPresent(loginSuccessMessage);
        return loginSuccessMessage.getText();
    }


    public String getLoginFauitMessage() {
        assertElementPresent(wrongPasswordReasonLink);
        return wrongPasswordReasonLink.getText();
    }

}
