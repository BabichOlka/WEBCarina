package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automation.lab.carina.demo.data.TestData;
import com.solvd.automation.lab.carina.demo.gui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class GSMArenaSignUpPage extends AbstractPage {

    @FindBy(xpath = "//h1[@class='article-info-name']")
    private ExtendedWebElement title;
    @FindBy(xpath = "//h1[@class='article-info-name']")
    private ExtendedWebElement signUpWhyTitle;
    @FindBy(id = "user-submit")
    private ExtendedWebElement signUpFrom;
    @FindBy(id = "user-submit")
    private ExtendedWebElement signUpFromParent;

    @FindBy(xpath = "//input[@id='uname']")
    private ExtendedWebElement nickNameInput;
    @FindBy(xpath = "//div[@id='user-submit']//input[@id='email']")
    private ExtendedWebElement emailInput;
    @FindBy(xpath = "//div[@id='user-submit']//input[@id='upass']")
    private ExtendedWebElement passwordInput;
    @FindBy(xpath = "//div[@id='ucsubmit-f']/input[@type='submit' and @id='nick-submit']")
    private ExtendedWebElement submitButton;
    @FindBy(xpath = "//input[@id='iagree1' and @class='round-checkbox']")
    private ExtendedWebElement iAgree1CheckBox;
    @FindBy(xpath = "//input[@id='iagree2' and @class='round-checkbox']")
    private ExtendedWebElement iAgree2CheckBox;
    @FindBy(xpath = "//*[@id='body']/div/div[2]/p")
    private ExtendedWebElement signUpEmailErrorLink;
    @FindBy(xpath = "//*[@id='body']/div/div[2]/p")
    private ExtendedWebElement signUpInvalidEmailErrorLink;
    @FindBy(xpath = "//*[@id='body']/div/div[2]/p")
    private ExtendedWebElement signSuccessLink;

    @FindBy(xpath = "//div[contains(@class, 'top-bar')]")
    private HeaderComponent header;


    public GSMArenaSignUpPage(WebDriver driver) {
        super(driver);
        setPageURL("/register.php3");
    }

    private List<ExtendedWebElement> formElements =
            Arrays.asList(title, signUpWhyTitle, signUpFrom);


    public boolean validateBaseElements() {
        SoftAssert softAssert = new SoftAssert();
        formElements.forEach(locator ->
                softAssert.assertNotNull(
                        locator.isPresent(),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
        return true;
    }


    public GSMArenaSignUpPage testCorrectData() {

        nickNameInput.type(TestData.CORRECT_NICKNAME);
        emailInput.type(TestData.CORRECT_LOGIN);
        passwordInput.type(TestData.CORRECT_PASSWORD);
        iAgree1CheckBox.click();
        iAgree2CheckBox.click();
        submitButton.click();
        return this;
    }

    public String getCorrectDataMessage() {
        assertElementPresent(signSuccessLink);
        return signSuccessLink.getText();
    }


    public GSMArenaSignUpPage testExistingNickName() {
        nickNameInput.type(TestData.EXISTING_NICKNAME);
        return this;
    }

    public String getExistingNickNameMessage() {
        assertElementPresent(nickNameInput);
        return nickNameInput.getText();
    }


    public GSMArenaSignUpPage testShortNickName() {

        nickNameInput.type(TestData.SHORT_NICKNAME);
        return this;
    }

    public String getShortNickNameMessage() {
        assertElementPresent(nickNameInput);
        return nickNameInput.getText();
    }

    @Test
    public void testExistingEmail() {

        nickNameInput.type(TestData.CORRECT_NICKNAME);
        emailInput.type(TestData.EXISTING_LOGIN);
        passwordInput.type(TestData.CORRECT_PASSWORD);
        iAgree1CheckBox.click();
        iAgree2CheckBox.click();
        submitButton.click();
        SoftAssert softAssert = new SoftAssert();


    }

    public String getExistingEmailMessage() {
        assertElementPresent(signUpInvalidEmailErrorLink);
        return signUpInvalidEmailErrorLink.getText();
    }


}
