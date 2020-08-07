package com.solvd.automation.lab.carina.demo.util;

import com.google.common.base.Function;
import com.qaprosoft.carina.core.foundation.commons.HTML;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtil {
    public static final int WAIT_TIMEOUT_SEC = 30;
    public static final int WAIT_POLL_INTERVAL_MS = 1000;

    public static synchronized void waitForElementsTextContains(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                                final ExtendedWebElement element, final String key, final boolean refreshPage) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> {
                        if (refreshPage)
                            browser.navigate().refresh();
                        return (element.getText().toLowerCase().contains(key.toLowerCase()));
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Element's text '%s' doesn't contains key %s after %d sec waiting", element.getText(),
                    key, WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsContainsAnyText(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                                   final ExtendedWebElement element, final boolean refreshPage) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until(input -> {
                        if (refreshPage)
                            browser.navigate().refresh();
                        return (element.getText().length() > 0);
                    }
                    );
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Element's text '%s' doesn't contains any text after %d sec waiting", element.getText(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsTextContains(final WebDriver browser, final ExtendedWebElement element, final String key,
                                                                final boolean refreshPage) {
        waitForElementsTextContains(browser, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS, element, key, refreshPage);
    }

    public static synchronized void waitForElementTextNotEmpty(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                               final ExtendedWebElement element) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> (element.isElementPresent(1) && !"".equals(element.getText())));
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Element's '%s' text is still empty after %d sec waiting", element.getName(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsClassContains(final WebDriver browser, final ExtendedWebElement element,
                                                                 final String key) {
        try {
            new WebDriverWait(browser, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> (element.getAttribute(HTML.CLASS).contains(key)));
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Element's class '%s' doesn't contains key '%s' after %d sec waiting",
                    element.getAttribute(HTML.CLASS), key, WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementPresent(final WebDriver driver, final ExtendedWebElement element) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> (element.getElement().isDisplayed() && element.getElement().isEnabled()));
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Can't find Element '%s' on page after %d sec waiting", element.getName(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementNotPresent(final WebDriver driver, final ExtendedWebElement element) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> {
                        List<WebElement> elements = driver.findElements(element.getBy());
                        return elements.isEmpty() || !elements.get(0).isDisplayed() || !elements.get(0).isEnabled();
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Element '%s' still presents on page after %d sec waiting", element.getName(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsListNotEmpty(final WebDriver driver, final List<? extends ExtendedWebElement> elements) {
        waitForElementsListNotEmpty(driver, elements, WAIT_TIMEOUT_SEC);
    }

    public static synchronized void waitForElementsListNotEmpty(final WebDriver driver, final List<? extends ExtendedWebElement> elements, int waitTimeoutSec) {
        try {
            new WebDriverWait(driver, waitTimeoutSec, WAIT_POLL_INTERVAL_MS).ignoring(WebDriverException.class)
                    .until((Function<WebDriver, Boolean>) input -> elements.size() > 0);
        } catch (TimeoutException e) {
            throw new RuntimeException("Elements list doesn't contain any visible elements");
        }
    }

    public static synchronized void waitForUIObjectsListNotEmpty(final WebDriver driver, final List<? extends AbstractUIObject> uiObjects) {
        waitForUIObjectsListNotEmpty(driver, uiObjects, WAIT_TIMEOUT_SEC);
    }

    public static synchronized void waitForUIObjectsListNotEmpty(final WebDriver driver, final List<? extends AbstractUIObject> uiObjects, int waitTimeoutSec) {
        try {
            new WebDriverWait(driver, waitTimeoutSec, WAIT_POLL_INTERVAL_MS).ignoring(WebDriverException.class)
                    .until((Function<WebDriver, Boolean>) input -> uiObjects.size() > 0);
        } catch (TimeoutException e) {
            throw new RuntimeException("UI objects list doesn't contain any visible elements");
        }
    }

    public static synchronized void waitForURLContain(final WebDriver browser, final String key) {
        try {
            new WebDriverWait(browser, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).until(
                    (Function<WebDriver, Boolean>) input -> (browser.getCurrentUrl().contains(key)));
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Browser URL '%s' doesn't contain key %s after %d sec waiting",
                    browser.getCurrentUrl(), key, WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForURLNotContain(final WebDriver browser, final String key) {
        try {
            new WebDriverWait(browser, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).until(
                    (Function<WebDriver, Boolean>) input -> (!browser.getCurrentUrl().contains(key)));
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Browser URL '%s' still contains key %s after %d sec waiting", browser.getCurrentUrl(),
                    key, WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementIsClickable(final WebDriver driver, final ExtendedWebElement element, final int waitTimeout) {
        try {
            new WebDriverWait(driver, waitTimeout, WAIT_POLL_INTERVAL_MS).ignoring(StaleElementReferenceException.class)
                    .until((Function<WebDriver, Boolean>) input -> element.isVisible() && element.isClickable());
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Element '%s' is not clickable on page after %d sec waiting", element.getName(), WAIT_TIMEOUT_SEC));
        }
    }
}
