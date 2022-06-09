package pageObject.User.Nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.Nopcommerece.MyAccountPageUIs;

public class MyAccountPageObject extends BasePage {
	WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSubMenuByString(String value) {
		waitForElementClickable(driver, MyAccountPageUIs.MY_ACCOUNT_SUBMENU, value);
		clickToElement(driver, MyAccountPageUIs.MY_ACCOUNT_SUBMENU, value);

	}

	public void clickToGenderRaidoButtonByValue(String value) {
		waitForElementClickable(driver, MyAccountPageUIs.GENDER_RADIO_BUTTON, value);
		checkToCheckboxOrRadio(driver, MyAccountPageUIs.GENDER_RADIO_BUTTON, value);

	}

	public void selectDropDownByNameAndText(String drodownName, String value) {
		waitForElementVisisble(driver, MyAccountPageUIs.DATE_OF_BIRTH, drodownName);
		selectDropdownByText(driver, MyAccountPageUIs.DATE_OF_BIRTH, value, drodownName);

	}

	public boolean isEditFieldDisplayed(String classAttributeName, String value) {
		return isElementDisplayed(driver, MyAccountPageUIs.EDIT_FIELD, classAttributeName, value);
	}

	public void closeChangeSuccessMessage() {
		waitForElementClickable(driver,  MyAccountPageUIs.SUCCESS_CHANGED_PASSWORD_MESSAGE);
		clickToElement(driver,  MyAccountPageUIs.SUCCESS_CHANGED_PASSWORD_MESSAGE);
		
	}
	
	public void clickToReviewRadioByLabel(String value) {
		waitForElementVisisble(driver, MyAccountPageUIs.REVIEW_RADIO, value);
		checkToCheckboxOrRadio(driver, MyAccountPageUIs.REVIEW_RADIO, value);
	}

	public void clickToAddYourReivewLink() {
		waitForElementVisisble(driver, MyAccountPageUIs.ADD_REVIEW_LINK);
		clickToElement(driver, MyAccountPageUIs.ADD_REVIEW_LINK);
		
	}

	public void sendKeyToReviewText(String value) {
		waitForElementVisisble(driver, MyAccountPageUIs.REVIEW_TEXT);
		sendKeyToElement(driver, MyAccountPageUIs.REVIEW_TEXT, value);
		
	}

	public boolean isReviewDisplay() {
		waitForElementVisisble(driver, MyAccountPageUIs.REVIEW_DISPLAYED);
		return isElementDisplayed(driver, MyAccountPageUIs.REVIEW_DISPLAYED);
	}

}
