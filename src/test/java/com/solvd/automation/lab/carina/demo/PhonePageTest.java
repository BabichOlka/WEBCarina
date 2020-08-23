package com.solvd.automation.lab.carina.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMArenaHomePage;
import com.solvd.automation.lab.carina.demo.gui.pages.GSMPhonePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhonePageTest extends AbstractTest {

    private static final Logger LOGGER = Logger.getLogger(GSMArenaHomePageTest.class);

    GSMArenaHomePage homePage = new GSMArenaHomePage(getDriver());
    GSMPhonePage phonePage;

    @Test
    public void isPageOpened() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMPhonePage phonePage = homePage.openPhonePageFromGrid(3) ;

        Assert.assertTrue(phonePage.isPageOpened(), "GSMPhone page is not opened");


    }

    @Test
    public void validateBaseElements() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        GSMPhonePage phonePage = homePage.openPhonePageFromGrid(3);

        Assert.assertTrue(phonePage.validateBaseElements(), "GSMPhoneBaseElements page was not validate!");
    }
}
