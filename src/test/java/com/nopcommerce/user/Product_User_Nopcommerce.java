package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageObjectGenerator;
import pageObject.User.Nopcommerce.HomePageObject;
import pageObject.User.Nopcommerce.LoginUserPageObject;
import pageObject.User.Nopcommerce.MyAccountPageObject;
import pageObject.User.Nopcommerce.ProductPageObject;
import pageObject.User.Nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Product_User_Nopcommerce extends BaseTest {
	WebDriver driver;

	@Parameters({ "browser", "appUrl", "ipAddress", "port" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl, String ipAddress, String port) {
		driver = getBrowserDriver(browserName, appUrl, ipAddress, port);
		driver.manage().window().maximize();
		log.info("Open Home Page");
		homePage = PageObjectGenerator.getPOG().getHomePage(driver);

		log.info("Step 1: Hover to header menu");
		homePage.hoverToHeaderMenu(driver, "Computers");

		log.info("Step 1: Open 'Note Book' submenu");
		homePage.clickToHeaderSubmenu(driver, "Computers", "Notebooks ");
		productPage = PageObjectGenerator.getPOG().getProductPage(driver);

	}

	@Test
	public void TC_01_Sort_Name_A_To_Z() {
		log.info("Step 1: Click to Sort A to Z");
		productPage.sortOrDisplay("products-orderby", "Name: A to Z");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Step 2: Click to Sort A to Z");
		verifyTrue(productPage.isProductNameSortAToZ());
	}

	@Test
	public void TC_02_Sort_Name_Z_To_A() {
		log.info("Step 1: Click to Sort Z to A");
		productPage.sortOrDisplay("products-orderby", "Name: Z to A");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Step 2: Click to Sort Z to A");
		verifyTrue(productPage.isProductNameSortZToA());

	}

	@Test
	public void TC_03_Sort_Price_Low_To_High() {
		log.info("Step 1: Click to Sort Low to High");
		productPage.sortOrDisplay("products-orderby", "Price: Low to High");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Step 2: Click to Sort Low to High");
		verifyTrue(productPage.isProductPriceSortLowToHigh());

	}

	@Test
	public void TC_04_Sort_Price_High_To_Low() {
		log.info("Step 1: Click to Sort High to Low");
		productPage.sortOrDisplay("products-orderby", "Price: High to Low");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isProductPriceSortHighToLow());

	}

	@Test
	public void TC_05_Product_Display_03_Product() {
		log.info("Step 1: Click to Sort A to Z");
		productPage.sortOrDisplay("products-pagesize", "3");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isProductNumberDisplayCorrect("3"));

		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isPagingIconDisplayCorrect("Next"));

		log.info("Step 2: Click to Sort High to High");
		productPage.clickToPagingIcon("Next");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isPagingIconDisplayCorrect("Previous"));
		
	}

	@Test
	public void TC_06_Product_Display_06_Product() {
		log.info("Step 2: Click to Sort High to High");
		productPage.clickToPagingIcon("Previous");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Step 1: Click to Sort A to Z");
		productPage.sortOrDisplay("products-pagesize", "6");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isProductNumberDisplayCorrect("6"));
		
		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isPagingIconUndisplayed("Next")||productPage.isPagingIconUndisplayed("Next"));

	}

	@Test
	public void TC_07_Product_Display_09_Product() {
		log.info("Step 1: Click to Sort A to Z");
		productPage.sortOrDisplay("products-pagesize", "9");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isProductNumberDisplayCorrect("9"));
		
		log.info("Step 2: Click to Sort High to High");
		verifyTrue(productPage.isPagingIconUndisplayed("Next")||productPage.isPagingIconUndisplayed("Next"));
	}

	@AfterClass(alwaysRun = true)
	public void afterclass() {
		closeBrowserAndDriver();
	}

	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginUserPageObject loginPage;
	MyAccountPageObject accountPage;
	ProductPageObject productPage;

}
