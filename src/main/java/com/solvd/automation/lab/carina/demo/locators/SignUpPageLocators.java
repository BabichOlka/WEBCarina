package com.solvd.automation.lab.carina.demo.locators;

import org.openqa.selenium.By;

public class SignUpPageLocators {
    public static final By TITLE = By.xpath("//h1[@class='article-info-name']");
    public static final By SIGN_UP_WHY_TITLE = By.xpath("//div[@class='normal-text']/h3");
    public static final By SIGN_UP_FORM = By.id("user-submit");

    public static class SignUpForm {
        public static final By SIGN_UP_FORM_PARENT = By.id("user-submit");
        public static final By NICKNAME_INPUT = By.xpath("//input[@id='uname']");
        public static final By EMAIL_INPUT = By.xpath("//div[@id='user-submit']//input[@id='email']");
        public static final By PASSWORD_INPUT = By.xpath("//div[@id='user-submit']//input[@id='upass']");
        public static final By SUBMIT_BUTTON = By.xpath("//div[@id='ucsubmit-f']/input[@type='submit' and @id='nick-submit']");
        public static final By I_AGREE_1_CHECKBOX = By.xpath("//input[@id='iagree1' and @class='round-checkbox']");
        public static final By I_AGREE_2_CHECKBOX = By.xpath("//input[@id='iagree2' and @class='round-checkbox']");
        public static final By SIGNUP_SUCCESS_LINK = By.xpath("//*[@id='body']/div/div[2]/h3");
        public static final By SIGNUP_EXISTING_EMAIL_ERROR_LINK = By.xpath("//*[@id='body']/div/div[2]/p");
        public static final By SIGNUP_INVALID_EMAIL_ERROR_LINK = By.xpath("//*[@id='body']/div/div[2]/p");

    }


}
