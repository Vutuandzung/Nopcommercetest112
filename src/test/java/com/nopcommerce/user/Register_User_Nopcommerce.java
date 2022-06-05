package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageObjectGenerator;
import pageObject.User.Nopcommerce.HomePageObject;
import pageObject.User.Nopcommerce.LoginPageObject;
import pageObject.User.Nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Register_User_Nopcommerce extends BaseTest {
	WebDriver driver;
	String firstName, lastName, emailRandom, registerURL;

	@Parameters({ "browser", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		log.info("Open Home Page");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);
		firstName = "John";
		lastName = "Wick";

		emailRandom = getRandomEmail();
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		log.info("Step 1: Click to 'Register' link on header menu");
		homePage.clickToHeaderLinkByClassAttribute(driver, "ico-register");
		registerPage = PageObjectGenerator.getPOG().getRegisterPage(driver);

		log.info("Step 2: Click to 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 3: Verify error 'First Name' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "FirstName-error", "First name is required."));

		log.info("Step 4: Verify error 'Last Name' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "LastName-error", "Last name is required."));

		log.info("Step 5: Verify error 'Email' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Email-error", "Email is required."));

		log.info("Step 6: Verify error 'Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Password-error", "Password is required."));

		log.info("Step 7: Verify error 'Confirm Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "ConfirmPassword-error", "Password is required."));

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		log.info("Step 8: Refesh 'Register' ");
		registerPage.refreshCurrentPage(driver);
		registerURL = registerPage.getPageUrl(driver);

		log.info("Step 9: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "FirstName", firstName);

		log.info("Step 10: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "LastName", lastName);

		log.info("Step 11: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Email", "123");

		log.info("Step 12: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Password", "123456");

		log.info("Step 13: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "ConfirmPassword", "123456");

		log.info("Step 14: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 15: Verify error 'Email' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Email-error", "Wrong email"));

	}

	@Test
	public void TC_03_Register_Valid_Information() {
		log.info("Step 16: Refesh 'Register' ");
		registerPage.refreshCurrentPage(driver);

		log.info("Step 9: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "FirstName", firstName);

		log.info("Step 10: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "LastName", lastName);

		log.info("Step 11: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Email", emailRandom);

		log.info("Step 12: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Password", "123456");

		log.info("Step 13: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "ConfirmPassword", "123456");

		log.info("Step 14: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 15: Verify Success Message");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Step 16: Click to 'Log out' button");
		registerPage.clickToHeaderLinkByClassAttribute(driver, "ico-logout");

	}

	@Test
	public void TC_04_Register_Exist_Email() {

		log.info("Step 17: Click to 'Register' link on header menu");
		registerPage.navigateToUrlByJS(driver, registerURL);

		log.info("Step 9: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "FirstName", firstName);

		log.info("Step 10: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "LastName", lastName);

		log.info("Step 11: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Email", emailRandom);

		log.info("Step 12: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Password", "123456");

		log.info("Step 13: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "ConfirmPassword", "123456");

		log.info("Step 14: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");
		

		log.info("Step 15: Verify Success Message");
		verifyTrue(registerPage.isErrorEmailExsistDisplayed());

	}

	@Test
	public void TC_05_Register_Short_Password() {
		log.info("Step 16: Refesh 'Register' ");
		registerPage.refreshCurrentPage(driver);
		registerPage.sleepInSecond(5);

		log.info("Step 3: Enter to 'First Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "FirstName", firstName);

		log.info("Step 3: Enter to 'Last Name' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "LastName", lastName);

		log.info("Step 11: Enter to 'Email' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Email", getRandomEmail());

		log.info("Step 12: Enter to 'Passowrd' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "Password", "12");

		log.info("Step 13: Enter to 'Confirm Password' textbox");
		registerPage.sendKeyToTextBoxByName(driver, "ConfirmPassword", "12");

		log.info("Step 14: Click to 'Register' button ");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Step 15: Verify error 'Password' message");
		verifyTrue(registerPage.isErrorMessageDisplayed(driver, "Password-error",
				"Password must meet the following rules: "));
	}

//	@AfterClass(alwaysRun = true)
//	public void afterclass() {
//		closeBrowserAndDriver();
//	}

	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;

}
