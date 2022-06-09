package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageObjectGenerator;
import pageObject.User.Nopcommerce.HomePageObject;
import pageObject.User.Nopcommerce.LoginUserPageObject;
import pageObject.User.Nopcommerce.MyAccountPageObject;
import pageObject.User.Nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Login_User_Nopcommerce extends BaseTest {
	WebDriver driver;

	@Parameters({ "browser", "appUrl", "ipAddress", "port" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl, String ipAddress, String port) {
		driver = getBrowserDriver(browserName, appUrl, ipAddress, port);
		log.info("Open Home Page");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);
		log.info("Step 1: Click to 'Login' link on header menu");
		homePage.clickToHeaderLinkByClassAttribute(driver, "ico-login");
		loginPage = PageObjectGenerator.getPOG().getLoginPageUser(driver);

	}

	@Test
	public void TC_01_Login_Empty_Data() {
		log.info("Step 2: Click to 'Login' button with empty data");
		loginPage.clickToButtonByText(driver, "Log in");
		log.info("Step 2: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "Please enter your email", "Email-error"));

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);
		log.info("Step 4: Enter ivalid email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123", "Email");
		log.info("Step 5: Enter password to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123456", "Password");
		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");
		log.info("Step 7: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "Wrong email", "Email-error"));
	}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);

		log.info("Step 4: Enter ivalid email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, getRandomEmail(), "Email");

		log.info("Step 5: Enter password to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123456", "Password");

		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Step 7: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "No customer account found"));

	}

	@Test
	public void TC_04_Login_Empty_Password() {
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);

		log.info("Step 4: Enter invalid email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, EMAIL_RANDOM, "Email");

		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Step 7: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "The credentials provided are incorrect"));
	}

	@Test
	public void TC_05_Login_Wrong_Password() {
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);


		log.info("Step 4: Enter invalid email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, EMAIL_RANDOM, "Email");

		log.info("Step 5: Enter password to 'Password' textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123123", "Password");

		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Step 7: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "The credentials provided are incorrect"));

	}

	@Test
	public void TC_06_Login_Success() {
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);


		log.info("Step 4: Enter invalid email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, EMAIL_RANDOM, "Email");

		log.info("Step 5: Enter password to 'Password' textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123456", "Password");

		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");
		accountPage = PageObjectGenerator.getPOG().getAccountPage(driver);

		log.info("Step 7: Verify 'Log out' header link button display  ");
		verifyTrue(accountPage.isHeaderLinkButtonDisplayed(driver, "ico-logout"));
		allCookies = accountPage.getCookies(driver);

	}

	@AfterClass(alwaysRun = true)
	public void afterclass() {
		closeBrowserAndDriver();
	}

	public static Set<Cookie> allCookies;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginUserPageObject loginPage;
	MyAccountPageObject accountPage;

}
