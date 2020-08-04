package com.solvd.automation.lab.carina.demo.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static class LoginFaultScreen {
        public static final By LOGIN_FAULT_MESSAGE = By.xpath("//h3[text()='Login failed.']");
    }
}
