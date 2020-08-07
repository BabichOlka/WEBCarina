package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.DriverHelper;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
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


public class GSMArenaPhotoTest extends AbstractTest {

    private WebDriver driver = getDriver();
    private static DriverHelper driverHelper;

    private static final Logger LOGGER = Logger.getLogger(GSMArenaPhotoTest.class);

    public static final List<By> PHONE_PAGE_ELEMENTS = Arrays.asList(
            PhonePageLocators.PhoneElements.MODEL,
            PhonePageLocators.PhoneElements.PHOTO,
            PhonePageLocators.PhoneElements.SPEC_LIST,
            PhonePageLocators.PhoneElements.COMMENTS_CONTAINER,
            PhonePageLocators.PhoneElements.OPTIONS
            );


    @BeforeTest
    public void initializeDriverHelper() {
        LOGGER.info("Will initialize driver helper.");
        driverHelper = new DriverHelper(driver);
        LOGGER.info("Driver helper was initialized.");

    }

    @BeforeMethod
    public void openHomePage() {
        getDriver().get(Configuration.get(Configuration.Parameter.URL));
        ExtendedWebElement logo = driverHelper.findExtendedWebElement(Header.LOGO);
        Assert.assertTrue(logo.isElementPresent(), "Home page was not opened!");
    }

    @Test
    public void testProductDescription() {
        List<ExtendedWebElement> links = driverHelper.findExtendedWebElements(HomePageLocators.PhoneModels.PHONE_LINK);
        links.get(3).click();


        SoftAssert softAssert = new SoftAssert();
        PHONE_PAGE_ELEMENTS.forEach(locator ->
                softAssert.assertNotNull(
                        driverHelper.findExtendedWebElement(locator),
                        String.format("%s is not found on the page.", locator.toString()
                        )
                )
        );
        softAssert.assertAll();
    }
}
