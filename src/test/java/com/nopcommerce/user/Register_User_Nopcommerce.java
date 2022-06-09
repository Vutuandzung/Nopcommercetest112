package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageObjectGenerator;
import pageObject.User.Nopcommerce.HomePageObject;
import pageObject.User.Nopcommerce.LoginUserPageObject;
import pageObject.User.Nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Register_User_Nopcommerce extends BaseTest {
	WebDriver driver;
	String firstName, lastName, registerURL;

	@Parameters({ "browser", "appUrl", "ipAddress", "port" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl, String ipAddress, String port) {
		driver = getBrowserDriver(browserName, appUrl, ipAddress, port);
		log.info("Open Home Page");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);
		firstName = "John";
		lastName = "Wick";

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		log.info("Step 1: Click to 'Register' link on header menu");
		homePage.clickToHeaderLinkByClassAttribute(driver, "ico-register");
		registerPage = PageObjectGenerator.getPOG().getRegisterPage(driver);

		log.info("Step 2: Click to 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 3: Verify error 'First Name' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "First name is required.", "FirstName-error"));

		log.info("Step 4: Verify error 'Last Name' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Last name is required.", "LastName-error"));

		log.info("Step 5: Verify error 'Email' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Email is required.", "Email-error"));

		log.info("Step 6: Verify error 'Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Password is required.", "Password-error"));

		log.info("Step 7: Verify error 'Confirm Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Password is required.", "ConfirmPassword-error"));

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		log.info("Step 8: Refesh 'Register' ");
		registerPage.refreshCurrentPageNopcom(driver);
		registerURL = registerPage.getPageUrl(driver);

		log.info("Step 9: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, firstName, "FirstName");

		log.info("Step 10: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, lastName, "LastName");

		log.info("Step 11: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123", "Email");

		log.info("Step 12: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "Password");

		log.info("Step 13: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "ConfirmPassword");

		log.info("Step 14: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 15: Verify error 'Email' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Wrong email", "Email-error"));

	}

	@Test
	public void TC_03_Register_Valid_Information() {
		log.info("Step 16: Refesh 'Register' ");
		registerPage.refreshCurrentPageNopcom(driver);

		log.info("Step 17: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, firstName, "FirstName");

		log.info("Step 18: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, lastName, "LastName");

		log.info("Step 19: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, EMAIL_RANDOM, "Email");

		log.info("Step 20: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "Password");

		log.info("Step 21: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "ConfirmPassword");

		log.info("Step 22: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 23: Verify Success Message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Your registration completed"));

		log.info("Step 24: Click to 'Log out' button");
		registerPage.clickToHeaderLinkByClassAttribute(driver, "ico-logout");

	}

	@Test
	public void TC_04_Register_Short_Password() {
		log.info("Step 25: Refesh 'Register' ");
		registerPage.refreshCurrentPageNopcom(driver);
		registerPage.clickToHeaderLinkByClassAttribute(driver, "ico-register");

		log.info("Step 26: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, firstName, "FirstName");

		log.info("Step 27: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, lastName, "LastName");

		log.info("Step 28: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, getRandomEmail(), "Email");

		log.info("Step 29: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "12", "Password");

		log.info("Step 30: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "12", "ConfirmPassword");

		log.info("Step 31: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 32: Verify error 'Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Password must meet the following rules: ",
				"Password-error"));
	}

	@Test
	public void TC_05_Register_Exist_Email() {

		log.info("Step 33: Click to 'Register' link on header menu");
		registerPage.refreshCurrentPageNopcom(driver);

		log.info("Step 34: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, firstName + "hc", "FirstName");

		log.info("Step 35: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, lastName + "hc", "LastName");

		log.info("Step 36: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, EMAIL_RANDOM, "Email");

		log.info("Step 37: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "Password");

		log.info("Step 38: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "123456", "ConfirmPassword");

		log.info("Step 39: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 40: Verify Success Message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "The specified email already exists"));

	}

	

	@AfterClass(alwaysRun = true)
	public void afterclass() {
		closeBrowserAndDriver();
	}

	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginUserPageObject loginPage;

}
