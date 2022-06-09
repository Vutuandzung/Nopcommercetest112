package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.admin.nopCommerce.ProductDetailPageUI;
import pageUIs.user.Nopcommerece.BasePageUIs;
import pageUIs.user.Nopcommerece.MyAccountPageUIs;

public class BasePage {
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTittle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Alert waitForAlertPresense(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresense(driver);
		alert.accept();

	}

	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresense(driver);
		alert.dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresense(driver);
		alert.sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		alert = waitForAlertPresense(driver);
		return alert.getText();
	}

	public boolean isAlertPresent(WebDriver driver) {
		overideGlobalTimeOut(driver, alertTimeout);
		explicitWait = new WebDriverWait(driver, alertTimeout);
		try {
			explicitWait.until(ExpectedConditions.alertIsPresent());
			overideGlobalTimeOut(driver, longtTimeout);
			return true;
		} catch (Exception e) {
			overideGlobalTimeOut(driver, longtTimeout);
			return false;
		}

	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String windowID : allWindowID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(castDynamicLocator(locator, params)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, castDynamicLocator(locator, params)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value, String... params) {
		getElement(driver, castDynamicLocator(locator, params)).clear();
		getElement(driver, castDynamicLocator(locator, params)).sendKeys(value);
	}

	public int getElementSize(WebDriver driver, String locator, String value) {
		return getElements(driver, locator).size();
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		select = new Select(getElement(driver, castDynamicLocator(locator, params)));
		select.selectByVisibleText(itemText);
	}

	public String getSelectedItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemDropdown(WebDriver driver, String locator, String... params) {
		select = new Select(getElement(driver, castDynamicLocator(locator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		return select.isMultiple();

	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longtTimeout);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params) {
		return getElement(driver, castDynamicLocator(locator, params)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText().trim();

	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		if (!isElementSelected(driver, castDynamicLocator(locator, params))) {
			getElement(driver, castDynamicLocator(locator, params)).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overideGlobalTimeOut(driver, longtTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator, String...params) {
		overideGlobalTimeOut(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overideGlobalTimeOut(driver, longtTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overideGlobalTimeOut(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		return getElement(driver, castDynamicLocator(locator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver swicthToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();

	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void hoverToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, castDynamicLocator(locator, params))).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourcecLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourcecLocator), getElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();

	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, castDynamicLocator(locator, params)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				getElement(driver, castDynamicLocator(locator, params)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisisble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));

	}

	public void waitForElementVisisble(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(castDynamicLocator(locator, params))));

	}

	public void waitForAllElementVisisble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(castDynamicLocator(locator, params))));
	}

	public void waitForElementInisisble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementInisisble(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longtTimeout);
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(getByXpath(castDynamicLocator(locator, params))));
	}

	public String castDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void addCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
//

	// Nopcommerce admin
	public void uploadMultipleFilesByName(WebDriver driver, String buttonIndex, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER;
		String fullName = "";
		for (String file : fileNames) {
			fullName = fullName + filePath + file + "\n";
		}
		fullName = fullName.trim();
		getElement(driver, ProductDetailPageUI.UPLOAD_BUTTON_BY_INDEX, buttonIndex).sendKeys(fullName);
	}

	// Nopcommerce user
	public void clickToHeaderLinkByClassAttribute(WebDriver driver, String value) {
		waitForElementClickable(driver, BasePageUIs.HEADER_UPPER_LINK_BY_CLASS, value);
		clickToElement(driver, BasePageUIs.HEADER_UPPER_LINK_BY_CLASS, value);

	}

	public void clickToHeaderLinkByClassAttributeJS(WebDriver driver, String value) {

		clickToElementByJS(driver, BasePageUIs.HEADER_UPPER_LINK_BY_CLASS, value);
	}

	public void clickToButtonByText(WebDriver driver, String value) {
		waitForElementClickable(driver, BasePageUIs.BUTTON_BY_TEXT, value);
		clickToElement(driver, BasePageUIs.BUTTON_BY_TEXT, value);

	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String message, String id) {
		return isElementDisplayed(driver, BasePageUIs.ERROR_MESSAGE_BY_ID, id, message);
	}

	public void sendKeyToTextBoxByName(WebDriver driver, String value, String textboxName) {
		waitForElementVisisble(driver, BasePageUIs.TEXTBOX_BY_NAME, textboxName);
		sendKeyToElement(driver, BasePageUIs.TEXTBOX_BY_NAME, value, textboxName);
		System.out.println(value);

	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String value) {
		return isElementDisplayed(driver, BasePageUIs.ERROR_MESSAGE_BY_STRING, value);
	}

	public boolean isHeaderLinkButtonDisplayed(WebDriver driver, String className) {
		return isElementDisplayed(driver, BasePageUIs.HEADER_UPPER_LINK_BY_CLASS, className);
	}

	public String getTextboxAttribute(WebDriver driver, String texboxName, String attributeName) {
		waitForElementVisisble(driver, BasePageUIs.TEXTBOX_BY_NAME, texboxName);
		return getElementAttribute(driver, BasePageUIs.TEXTBOX_BY_NAME, attributeName, texboxName);

	}

	public void clickToProductByText(WebDriver driver, String value) {
		waitForElementClickable(driver, BasePageUIs.PRODUCT_ITEM_BY_TEXT, value);
		clickToElement(driver, BasePageUIs.PRODUCT_ITEM_BY_TEXT, value);

	}

	public String getDropdownfirstText(WebDriver driver, String nameDropdown) {
		waitForElementVisisble(driver, MyAccountPageUIs.DATE_OF_BIRTH, nameDropdown);
		return getSelectedItemDropdown(driver, MyAccountPageUIs.DATE_OF_BIRTH, nameDropdown);

	}

	public void refreshCurrentPageNopcom(WebDriver driver) {
		driver.navigate().refresh();
		if (driver.toString().toLowerCase().contains("firefox") && isAlertPresent(driver)) {
			acceptAlert(driver);
		}
	}

	public void clickToHeaderSubmenu(WebDriver driver, String menuName, String subMenuName) {
		waitForElementClickable(driver, BasePageUIs.HEADER_SUBMENU, menuName, subMenuName);
		clickToElement(driver, BasePageUIs.HEADER_SUBMENU, menuName, subMenuName);
	}

	public void hoverToHeaderMenu(WebDriver driver, String menuName) {
		waitForElementVisisble(driver, BasePageUIs.HEADER_MENU, menuName);
		hoverToElement(driver, BasePageUIs.HEADER_MENU, menuName);

	}

	

	private Alert alert;
	private Select select;
	private Actions action;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longtTimeout = GlobalConstants.LONG_TIMEOUT;
	private long alertTimeout = GlobalConstants.ALERT_TIMEOUT;
	JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
}