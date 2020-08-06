package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GSMPhonePage extends AbstractPage {

    @FindBy(id = "specs-list")
    private ExtendedWebElement specList;
    @FindBy(className = "specs-phone-name-title")
    private ExtendedWebElement model;
    @FindBy(xpath="//div[@class='specs-photo-main']//img")
    private ExtendedWebElement photo;
    @FindBy(id = "user-comments")
    private ExtendedWebElement comments;
    @FindBy(className = "article-info-meta")
    private ExtendedWebElement options;


    public GSMPhonePage(WebDriver driver) {
        super(driver);
    }

    public boolean validateBaseElements() {

        return specList.isElementPresent() &&
                model.isElementPresent() &&
                photo.isElementPresent() &&
                comments.isElementPresent()&&
                options.isElementPresent() ;
    }




}
