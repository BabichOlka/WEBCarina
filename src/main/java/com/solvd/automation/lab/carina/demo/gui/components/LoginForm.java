package com.solvd.automation.lab.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
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

    public LoginForm(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isEmailInputPresent() {
        return emailInput.isPresent();
    }

    public String getEmailInput() {
        return emailInput.getAttribute("validationMessage");
    }

    public GSMArenaHomePage login(final String username, final String password) {
        emailInput.type(username);
        passwordInput.type(password);
        submitButton.click();
        return this;
    }

    public String getPasswordInput() {
        return passwordInput.getAttribute("validationMessage");
    }


}
