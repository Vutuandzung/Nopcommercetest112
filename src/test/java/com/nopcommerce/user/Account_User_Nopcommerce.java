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

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Account_User_Nopcommerce extends BaseTest {
	WebDriver driver;
	String firstName, lastName, comName, address1, address2, city, zipcode, fax, phone, oldPassword, newPassword;


	@Parameters({ "browser", "appUrl", "ipAddress", "port" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl, String ipAddress, String port) {
		driver = getBrowserDriver(browserName, appUrl, ipAddress, port);
		driver.manage().window().maximize();
		log.info("Open Home Page");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);
		

		log.info("Step 1: Click to 'Login' link on header menu");
		homePage.clickToHeaderLinkByClassAttribute(driver, "ico-login");
		loginPage = PageObjectGenerator.getPOG().getLoginPageUser(driver);
		
		log.info("Step 4: Enter  email to email textbox");
		loginPage.sendKeyToTextBoxByName(driver, emailRd.get(), "Email");
		
		log.info("Step 5: Enter password to 'Password' textbox");
		loginPage.sendKeyToTextBoxByName(driver, "123456", "Password");
		
		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");
		
		accountPage = PageObjectGenerator.getPOG().getAccountPage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		comName = "Automation FC";
		address1 = "103/04 Le Lai";
		address2 = "234/05 Hai Phong";
		city = "Da Nang";
		zipcode = "550000";
		fax = "0987654321";
		phone = "0123456789";
		oldPassword = "123456";
		newPassword = "789789";

	}

	@Test
	public void TC_01_Account_Customer_Information() {
		log.info("Step 3: Click to 'My Account' header menu");
		accountPage.clickToHeaderLinkByClassAttribute(driver, "ico-account");

		log.info("Step 3: Click to 'Customer Info' sub menu");
		accountPage.clickToSubMenuByString("Customer info");

		log.info("Step 3: Click to 'Gender' radio button");
		accountPage.clickToGenderRaidoButtonByValue("F");

		log.info("Step 3: Enter to 'First Name' textbox");
		accountPage.sendKeyToTextBoxByName(driver, firstName, "FirstName");

		log.info("Step 3: Enter to 'Last Name' textbox");
		accountPage.sendKeyToTextBoxByName(driver, lastName, "LastName");

		log.info("Step 3: Sellect 'Day' dropdown");
		accountPage.selectDropDownByNameAndText("DateOfBirthDay", "1");

		log.info("Step 3: Sellect 'Month' dropdown");
		accountPage.selectDropDownByNameAndText("DateOfBirthMonth", "January");

		log.info("Step 3: Sellect 'Year' dropdown");
		accountPage.selectDropDownByNameAndText("DateOfBirthYear", "1999");

		log.info("Step 3: Enter to 'Email' textbox");
		accountPage.sendKeyToTextBoxByName(driver, emailEdit.get(), "Email");

		log.info("Step 3: Enter to 'Company Name' textbox");
		accountPage.sendKeyToTextBoxByName(driver, comName, "Company");

		log.info("Step 3: Click to 'Save' button");
		accountPage.clickToButtonByText(driver, "Save");

		log.info("Step 3: Verify 'First Name' textbox");
		verifyEquals(accountPage.getTextboxAttribute(driver, "FirstName", "value"), firstName);

		log.info("Step 3: Verify 'Last Name' textbox");
		verifyEquals(accountPage.getTextboxAttribute(driver, "LastName", "value"), lastName);

		log.info("Step 3: Verify 'Day' dropdown");
		verifyEquals(accountPage.getDropdownfirstText(driver, "DateOfBirthDay"), "1");

		log.info("Step 3: Verify 'Month' dropdown");
		verifyEquals(accountPage.getDropdownfirstText(driver, "DateOfBirthMonth"), "January");

		log.info("Step 3: Verify 'Year' dropdown");
		verifyEquals(accountPage.getDropdownfirstText(driver, "DateOfBirthYear"), "1999");

		log.info("Step 3: Verify 'Email' textbox");
		verifyEquals(accountPage.getTextboxAttribute(driver, "Email", "value"), emailEdit.get());

		log.info("Step 3: Verify 'Company' textbox");
		verifyEquals(accountPage.getTextboxAttribute(driver, "Company", "value"), comName);

	}

	@Test
	public void TC_02_Account_Address() {
		log.info("Step 3: Click to 'Address' sub menu");
		accountPage.clickToSubMenuByString("Addresses");

		log.info("Step 3: Click to 'Add New' button");
		accountPage.clickToButtonByText(driver, "Add new");

		log.info("Step 3: Enter to 'First Name' textbox");
		accountPage.sendKeyToTextBoxByName(driver, firstName, "Address.FirstName");

		log.info("Step 3: Enter to 'Last Name' textbox");
		accountPage.sendKeyToTextBoxByName(driver, lastName, "Address.LastName");

		log.info("Step 3: Enter to 'Email' textbox");
		accountPage.sendKeyToTextBoxByName(driver, emailEdit.get(), "Address.Email");

		log.info("Step 3: Enter to 'Company' textbox");
		accountPage.sendKeyToTextBoxByName(driver, comName, "Address.Company");

		log.info("Step 3: Select 'Country' dropdown");
		accountPage.selectDropDownByNameAndText("Address.CountryId", "Viet Nam");

		log.info("Step 3: Select 'State' dropdown");
		accountPage.selectDropDownByNameAndText("Address.StateProvinceId", "Other");

		log.info("Step 3: Enter to 'City' textbox");
		accountPage.sendKeyToTextBoxByName(driver, city, "Address.City");

		log.info("Step 3: Enter to 'Address 1' textbox");
		accountPage.sendKeyToTextBoxByName(driver, address1, "Address.Address1");

		log.info("Step 3: Enter to 'Address 2' textbox");
		accountPage.sendKeyToTextBoxByName(driver, address2, "Address.Address2");

		log.info("Step 3: Enter to 'Zip / postal code' textbox");
		accountPage.sendKeyToTextBoxByName(driver, zipcode, "Address.ZipPostalCode");

		log.info("Step 3: Enter to 'Phone number' textbox");
		accountPage.sendKeyToTextBoxByName(driver, phone, "Address.PhoneNumber");

		log.info("Step 3: Enter to 'Fax number' textbox");
		accountPage.sendKeyToTextBoxByName(driver, fax, "Address.FaxNumber");

		log.info("Step 3: Click to 'Save' button");
		accountPage.clickToButtonByText(driver, "Save");

		log.info("Step 3: Verify name");
		verifyTrue(accountPage.isEditFieldDisplayed("name", firstName + " " + lastName));

		log.info("Step 3: Verify 'Email' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("email", emailEdit.get()));

		log.info("Step 3: Verify 'Company' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("company", comName));

		log.info("Step 3: Verify 'Country' dropdown");
		verifyTrue(accountPage.isEditFieldDisplayed("country", "Viet Nam"));

		log.info("Step 3: Verify 'Address 1' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("address1", address1));

		log.info("Step 3: Verify 'Address 2' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("address2", address2));

		log.info("Step 3: Verify 'Zip / postal code' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("city-state-zip", city + ", " + zipcode));

		log.info("Step 3: Verify 'Phone number' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("phone", phone));

		log.info("Step 3: Verify 'Fax number' textbox");
		verifyTrue(accountPage.isEditFieldDisplayed("fax", fax));
	}

	@Test
	public void TC_03_Account_Change_Password() {
		log.info("Step 3: Click to 'Change password' sub menu");
		accountPage.clickToSubMenuByString("Change password");

		log.info("Step 3: Enter to 'Old Password' textbox");
		accountPage.sendKeyToTextBoxByName(driver, oldPassword, "OldPassword");

		log.info("Step 3: Enter to 'New Passowrd' textbox");
		accountPage.sendKeyToTextBoxByName(driver, newPassword, "NewPassword");

		log.info("Step 3: Enter to 'Confirm Password' textbox");
		accountPage.sendKeyToTextBoxByName(driver, newPassword, "ConfirmNewPassword");

		log.info("Step 3: Click to 'Change password' button");
		accountPage.clickToButtonByText(driver, "Change password");
		
		log.info("Step 3: Close 'Password was changed'");
		accountPage.closeChangeSuccessMessage();
		
		log.info("Step 3: Click to 'Log out' header menu");
		accountPage.clickToHeaderLinkByClassAttributeJS(driver, "ico-logout");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);
		
		log.info("Step 1: Click to 'Login' link on header menu");
		homePage.clickToHeaderLinkByClassAttribute(driver, "ico-login");
		loginPage = PageObjectGenerator.getPOG().getLoginPageUser(driver);

		log.info("Step 4: Enter  email to 'Email' textbox");
		loginPage.sendKeyToTextBoxByName(driver, emailEdit.get(), "Email");
		
		log.info("Step 5: Enter old password to 'Password' textbox");
		loginPage.sendKeyToTextBoxByName(driver, oldPassword, "Password");
		
		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");
		
		log.info("Step 7: Verify error message ");
		verifyTrue(loginPage.isErrorMessageDisplayed(driver, "The credentials provided are incorrect"));
		
		log.info("Step 3: Refesh login page");
		loginPage.refreshCurrentPageNopcom(driver);
	
		log.info("Step 4: Enter  email to 'Email' textbox");
		loginPage.sendKeyToTextBoxByName(driver, emailEdit.get(), "Email");
		
		log.info("Step 5: Enter new password to 'Password' textbox");
		loginPage.sendKeyToTextBoxByName(driver, newPassword, "Password");
		
		log.info("Step 6: Click to 'Login' button ");
		loginPage.clickToButtonByText(driver, "Log in");
		accountPage = PageObjectGenerator.getPOG().getAccountPage(driver);
		
		log.info("Step 7: Verify 'Log out' header link button display  ");
		verifyTrue(accountPage.isHeaderLinkButtonDisplayed(driver, "ico-logout"));
		

		

	}

	@Test
	public void TC_04_Account_My_Product_Review() {
		log.info("Step 3: Click to product by text");
		accountPage.clickToProductByText(driver, "Build your own computer");
		
		log.info("Step 3: Click to 'Add Your Review' link");
		accountPage.clickToAddYourReivewLink();
		
		log.info("Step 3: Enter to 'Review title' textbox");
		accountPage.sendKeyToTextBoxByName(driver, "Perfect Product", "AddProductReview.Title");
		
		log.info("Step 3: Enter to 'Review title' textbox");
		accountPage.sendKeyToReviewText("Love it");
		
		log.info("Step 3: Click to 'Review' radio");
		accountPage.clickToReviewRadioByLabel("Excellent");
		
		log.info("Step 6: Click to 'Submit' button ");
		accountPage.clickToButtonByText(driver, "Submit review");
		
		log.info("Step 3: Click to 'My Account' header menu");
		accountPage.clickToHeaderLinkByClassAttribute(driver, "ico-account");
		
		log.info("Step 3: Click to 'My product reviews' sub menu");
		accountPage.clickToSubMenuByString("My product reviews");
		
		log.info("Step 7: Verify review display  ");
		verifyTrue(accountPage.isReviewDisplay());
		

	}


	@AfterClass(alwaysRun = true)
	public void afterclass() {
		closeBrowserAndDriver();
	}

	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginUserPageObject loginPage;
	MyAccountPageObject accountPage;

}
