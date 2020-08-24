package com.solvd.automation.lab.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.automation.lab.carina.demo.gui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class GSMArenaReviewsPage extends AbstractPage {

    @FindBy(linkText = "Popular tags")
    private ExtendedWebElement popularTags;

    @FindBy(xpath = "//h1[@class=\"article-info-name\"]")
    private ExtendedWebElement reviewsTitle;


    @FindBy(xpath = "//*[@class=\"searchFor\"]")
    private ExtendedWebElement searchForForm;

    @FindBy(xpath = "//*[@class=\"review-item clearfix\"]")
    private List <ExtendedWebElement> reviewItem;

    @FindBy(xpath = "//*[@class=\"submit button button-small\"]")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//li[@class=\"popular-tags-list-item\"]/a")
    private List <ExtendedWebElement> popularTagsListItem;



    public GSMArenaReviewsPage(WebDriver driver) {
        super(driver);
        setPageURL("/reviews.php3");
    }

    private List<ExtendedWebElement> formElements =
            Arrays.asList(popularTags, reviewsTitle,searchForForm,submitButton);


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
    public GSMArenaReviewsPage clickSubmitButton() {
        submitButton.click();
        return this;
    }
    public boolean searchResult(String searchInfo) {

        searchForForm.type(searchInfo);
        submitButton.click();
        SoftAssert softAssert = new SoftAssert();
        reviewItem.forEach(item ->
                softAssert.assertTrue(
                        item.getText().toLowerCase().contains(searchInfo.toLowerCase()),
                        String.format("%s is not found on the page.",item.toString()
                        )
                ));
        softAssert.assertAll();
        return true;
    }
    public boolean searchItemFromPopularTagsList(int i) {
        popularTagsListItem.get(i).click();
       // searchForForm.type(searchInfo);
       // submitButton.click();
        SoftAssert softAssert = new SoftAssert();
       reviewItem.forEach(item ->
                softAssert.assertTrue(
                        item.getText().toLowerCase().contains( popularTagsListItem.get(i).getText().toLowerCase()),
                        String.format("%s is not found on the page.",item.toString()
                        )
                ));
        softAssert.assertAll();
        return true;
    }

}
