package com.underarmour.phoenix.qa.util;

import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class JSUtil {
	private final static Logger LOGGER = Logger.getLogger(JSUtil.class);

	public static void hoverWithJS(WebDriver driver, ExtendedWebElement el2hover) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, el2hover.getElement());
		LOGGER.info("Hover with JS was successfully completed");
	}

    public static void scroll2element(WebDriver driver, ExtendedWebElement el2scroll) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true); return window.scrollY;", el2scroll.getElement()).toString();
        LOGGER.info("Scrolling with JS was successfully completed");
    }
	
    public static void scroll2TheBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("scroll(0,document.body.scrollHeight);");
        LOGGER.info("Scrolling to the bottom with JS was successfully completed");
    }

	public static String getBottomNoteText(WebDriver driver) {
		String script = "return document.evaluate(\"//div[@class='note_bottom']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.textContent;";
		LOGGER.info("INFO: Trying to get 'Bottom Note' element with JS...");
		try {
			String noteText = (String) ((JavascriptExecutor) driver).executeScript(script);
			LOGGER.info("PASS: 'Bottom Note' has been retrieved successfully.");
			return noteText.trim();
		} catch (WebDriverException e) {
			LOGGER.info("INFO: 'Bottom Note' is missing");
		}
		return null;
	}

	public static void click2Element(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		LOGGER.info("Click with JS was successfully completed");

	}

	public static void scroll2TheTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
	}

	public static void clickHiddenElement(WebDriver driver, ExtendedWebElement element) {
		try {
			WebElement elem = findElement(driver, element, 30);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			LOGGER.info(element.getName() + " was clicked.");
		} catch (Exception e) {
			LOGGER.info(element.getName() + " was NOT clicked.");
			LOGGER.error(e.getMessage());
		}
	}

	private static WebElement findElement(WebDriver driver, ExtendedWebElement element, long timeout) {
		WebElement el = element.getElement();
		if (el != null) {
			return el;
		}

		final WebElement[] els = {null};
		LOGGER.debug("There is null WebElement object. Trying to find dynamic element using By: " + element.getBy().toString());
		final WebDriver drv = driver;
		WebDriverWait wait = new WebDriverWait(drv, timeout, 50);
		try {
			wait.until((Function<WebDriver, Object>) dr -> {
				if (!drv.findElements(element.getBy()).isEmpty()) {
					LOGGER.debug("Dynamic element was found using By: " + element.getBy().toString());
					els[0] = drv.findElement(element.getBy());
					return true;
				}
				return false;
			});
		} catch (TimeoutException e) {
			//do nothing
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		el = drv.findElement(element.getBy());

		if (el == null) {
			throw new RuntimeException("Unable to find dynamic element using By: " + element.getBy().toString());
		}

		return el;
	}

	public static void acceptAuthAlert(WebDriver driver, final String username, final String password) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(String.format("%s\t%s", username, password));
		alert.accept();
		driver.switchTo().defaultContent();
	}

}