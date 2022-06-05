package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageObjectGenerator;
import pageObject.admin.nopCommerce.LoginPageObject;
import pageObject.admin.nopCommerce.MyDasboardPageObject;
import pageObject.admin.nopCommerce.ProductDetailPageObject;
import pageObject.admin.nopCommerce.ProductSearchPageObject;

//@Listeners(MethodListener.class)
public class Upload_File_Admin_Nopcommerce extends BaseTest {
	WebDriver driver;
	String fileName, disPlayOrder, alt, title, clickToOrderIconTimes;

	@Parameters({ "browser", "appUrl" })
	@BeforeClass

	public void initBrowser(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		fileName = "Lion.jpg";
		disPlayOrder = "2";
		clickToOrderIconTimes = "10";
		alt = "ALT";
		title = "TITLE";
		driver.manage().window().maximize();
		loginPage = PageObjectGenerator.getPOG().getLoginPage(driver);
		loginPage.sendKeyToEmailTextbox("admin@yourstore.com");
		loginPage.sendKeyToPasswordTextbox("admin");
		myDasboardPage = loginPage.clickToLoginButton();
		myDasboardPage.clickToMenuByName("book");
		myDasboardPage.clickToSubMenuByName("book", "Products");
		productSearchPage = PageObjectGenerator.getPOG().getProductSearchPage(driver);
		productSearchPage.enterToTextboxByName("Adobe Photoshop CS4", "SearchProductName");
		productSearchPage.clickToSearchButton();
		productDetailPage = productSearchPage.clickToEditButton();

	}

	@Test
	public void Admin_01_Upload_File() {
		log.info("Step 1 - Test");
		productDetailPage.clickToExpandPanelByName("far fa-images");
		log.info("Step 1 - Test");
		productDetailPage.uploadPictureByFileIndex("1", fileName);
		log.info("Step 1 - Test");
		verifyTrue(productDetailPage.isPictureUploadedSuccessByFileName(fileName));
		productDetailPage.endterToAltTextbox(alt);
		productDetailPage.endterToTitleTextbox(title);
		productDetailPage.clickToOrderIcon("up", clickToOrderIconTimes);
		productDetailPage.clickToAddProductButton();
		productDetailPage.sleepInSecond(5);
		verifyTrue(productDetailPage.isPictureDisplayed(clickToOrderIconTimes, alt, title));
		productSearchPage = productDetailPage.clickToSaveButton();
		verifyTrue(productSearchPage.isSuccessUploadFileTextDisplay());
		productSearchPage.enterToTextboxByName("Adobe Photoshop CS4", "SearchProductName");
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureUploaded("adobe-photoshop-cs4", "Adobe Photoshop CS4"));
		productDetailPage = productSearchPage.clickToEditButton();
		productDetailPage.clickToExpandPanelByName("far fa-images");
		productDetailPage.clickToDeleteButtonAtPictureName();
		verifyTrue(productDetailPage.isDeletedMessageDisplayed());
		productSearchPage = productDetailPage.clickToSaveButton();
		productSearchPage.enterToTextboxByName("Adobe Photoshop CS4", "SearchProductName");
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureUploaded("default-image", "Adobe Photoshop CS4"));
	}

	@Test
	public void Login_02_Login_To_System() {

	}

	@Test
	public void Login_02_Switch_Page() {

	}

	@AfterClass

	public void cleanBrowser() {
		driver.quit();
	}

	LoginPageObject loginPage;
	MyDasboardPageObject myDasboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;

}
