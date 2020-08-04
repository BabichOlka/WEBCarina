package com.solvd.automation.lab.carina.demo.locators;
import org.openqa.selenium.By;

public class PhonePageLocators {

    public static class PhoneElements {
        public static final By MODEL = By.className("specs-phone-name-title");
        public static final By PHOTO = By.xpath("//div[@class='specs-photo-main']//img");
        public static final By SPEC_LIST = By.id("specs-list");
    }
}
