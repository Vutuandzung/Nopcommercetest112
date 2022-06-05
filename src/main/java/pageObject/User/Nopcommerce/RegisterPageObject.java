package pageObject.User.Nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.Nopcommerece.RegisterPageUIs;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isErrorEmailExsistDisplayed() {
		return isElementDisplayed(driver, RegisterPageUIs.EMAIL_EXIST_ERROR_MESSAGE);
	}

	public boolean isSuccessMessageDisplayed() {
		return isElementDisplayed(driver, RegisterPageUIs.SUCCESS_REGISTER_MESSAGE);
	}

}
