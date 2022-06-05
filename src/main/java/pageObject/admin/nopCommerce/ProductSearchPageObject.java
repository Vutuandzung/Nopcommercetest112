package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageObjectGenerator;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage{
 private WebDriver driver;

public ProductSearchPageObject(WebDriver driver) {

	this.driver = driver;
}

public void enterToTextboxByName(String value, String textboxName) {
	waitForElementVisisble(driver, ProductSearchPageUI.TEXTBOX_BY_NAME, textboxName);
	sendKeyToElement(driver, ProductSearchPageUI.TEXTBOX_BY_NAME, value, textboxName);
	
}

public void clickToSearchButton() {
	waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
	clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
	
}

public ProductDetailPageObject clickToEditButton() {
//	waitForElementClickable(driver, ProductSearchPageUI.EDI_BUTTON);
	clickToElement(driver, ProductSearchPageUI.EDI_BUTTON);
	return PageObjectGenerator.getPOG().getProductDetailPage(driver);
}

public boolean isSuccessUploadFileTextDisplay() {
	waitForElementVisisble(driver, ProductSearchPageUI.SUCCESS_UPLOAD_MESSAGE);
	return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_UPLOAD_MESSAGE);
}

public boolean isPictureUploaded(String imageState, String productName) {
	waitForElementVisisble(driver, ProductSearchPageUI.SUCCESS_UPLOAD_IMAGE, imageState, productName);
	return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_UPLOAD_IMAGE, imageState, productName);
}
 
}
