package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageObjectGenerator;
import pageUIs.admin.nopCommerce.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	private WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public void clickToExpandPanelByName(String fieldName) {
		waitForElementVisisble(driver, ProductDetailPageUI.EXPAND_BY_NAME, fieldName);
		scrollToElement(driver, ProductDetailPageUI.EXPAND_BY_NAME, fieldName);
		if (getElementAttribute(driver, ProductDetailPageUI.EXPAND_BY_NAME, "class", fieldName)
				.equals("fa toggle-icon fa-plus")) {
			clickToElement(driver, ProductDetailPageUI.EXPAND_BY_NAME, fieldName);
		}

	}

	public void uploadPictureByFileIndex(String indexNumber, String fileName) {
//		waitForElementClickable(driver, ProductDetailPageUI.UPLOAD_BUTTON_BY_INDEX, indexNumber);
		uploadMultipleFilesByName(driver, indexNumber, fileName);
	}

	public boolean isPictureUploadedSuccessByFileName(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisisble(driver, ProductDetailPageUI.UPLOAD_PICTURE_IMAGE_PENDING, fileName);
		return isElementDisplayed(driver, ProductDetailPageUI.UPLOAD_PICTURE_IMAGE_PENDING, fileName);

	}

	public void endterToAltTextbox(String value) {
		waitForElementVisisble(driver, ProductDetailPageUI.ALT_TEXTBOX);
		sendKeyToElement(driver, ProductDetailPageUI.ALT_TEXTBOX, value);
	}

	public void endterToTitleTextbox(String value) {
		waitForElementVisisble(driver, ProductDetailPageUI.TITLE_TEXTBOX);
		sendKeyToElement(driver, ProductDetailPageUI.TITLE_TEXTBOX, value);

	}

	public void clickToOrderIcon(String icon, String times) {
		waitForElementClickable(driver, ProductDetailPageUI.DISPLAY_ORDER_ICON, icon);
		for (int i = 0; i < Integer.parseInt(times); i++) {
			clickToElement(driver, ProductDetailPageUI.DISPLAY_ORDER_ICON, icon);
		}

	}

	public boolean isPictureDisplayed(String order, String alt, String title) {
		waitForElementVisisble(driver, ProductDetailPageUI.SUCCESS_IMAGE_DISPLAYED, order, alt, title);
		return isElementDisplayed(driver, ProductDetailPageUI.SUCCESS_IMAGE_DISPLAYED, order, alt, title);
	}

	public ProductSearchPageObject clickToSaveButton() {
		waitForElementClickable(driver, ProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.SAVE_BUTTON);
		return PageObjectGenerator.getPOG().getProductSearchPage(driver);
	}

	public void clickToDeleteButtonAtPictureName() {
		waitForElementClickable(driver, ProductDetailPageUI.DELETE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON);
		acceptAlert(driver);

	}

	public void clickToAddProductButton() {
		waitForElementVisisble(driver, ProductDetailPageUI.ADD_PRODUCT_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_PRODUCT_BUTTON);

	}

	public boolean isDeletedMessageDisplayed() {
		waitForElementVisisble(driver, ProductDetailPageUI.DELETE_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, ProductDetailPageUI.DELETE_SUCCESS_MESSAGE);
	}
}
