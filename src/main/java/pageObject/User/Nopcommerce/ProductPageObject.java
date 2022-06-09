package pageObject.User.Nopcommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.user.Nopcommerece.ProductPageUIs;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sortOrDisplay(String sortOrDisplay, String dropdownListName) {
		waitForElementVisisble(driver, ProductPageUIs.SORT_OR_DISPLAY, sortOrDisplay);
		selectDropdownByText(driver, ProductPageUIs.SORT_OR_DISPLAY, dropdownListName, sortOrDisplay);
	}

	public boolean isProductNameSortAToZ() {
		waitForAllElementVisisble(driver, ProductPageUIs.PRODUCT_ITEM_BY_NAME);
		ArrayList<String> elementNameList = new ArrayList<>();
		for (WebElement element : getElements(driver, ProductPageUIs.PRODUCT_ITEM_BY_NAME)) {
			elementNameList.add(element.getText().trim());

		}
		ArrayList<String> cloneArrayList = new ArrayList<>();
		for (String elementText : elementNameList) {
			cloneArrayList.add(elementText);

		}
		Collections.sort(cloneArrayList);

		return elementNameList.equals(cloneArrayList);
	}

	public boolean isProductNameSortZToA() {
		waitForAllElementVisisble(driver, ProductPageUIs.PRODUCT_ITEM_BY_NAME);

		ArrayList<String> elementNameList = new ArrayList<>();
		for (WebElement element : getElements(driver, ProductPageUIs.PRODUCT_ITEM_BY_NAME)) {
			elementNameList.add(element.getText().trim());
		}
		ArrayList<String> cloneArrayList = new ArrayList<>();
		for (String elementText : elementNameList) {
			cloneArrayList.add(elementText);
		}
		Collections.sort(cloneArrayList);
		Collections.reverse(cloneArrayList);

		return elementNameList.equals(cloneArrayList);
	}

	public boolean isProductPriceSortLowToHigh() {
		waitForAllElementVisisble(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		List<WebElement> elements = getElements(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		ArrayList<Float> elementNameList = new ArrayList<>();
		for (WebElement element : elements) {
			elementNameList.add(Float.parseFloat(element.getText().trim().replaceAll("\\$", "").replaceAll(",", "")));

		}
		ArrayList<Float> cloneArrayList = new ArrayList<>();
		for (Float elementText : elementNameList) {
			cloneArrayList.add(elementText);
			System.out.println("gia add vao clone" + elementText);

		}
		Collections.sort(cloneArrayList);
		for (Float float1 : cloneArrayList) {
			System.out.println("gia sau khi sort" + float1);

		}

		return elementNameList.equals(cloneArrayList);
	}

	public boolean isProductPriceSortHighToLow() {
		waitForAllElementVisisble(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		List<WebElement> elements = getElements(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		ArrayList<Float> elementNameList = new ArrayList<>();
		for (WebElement element : elements) {
			elementNameList.add(Float.parseFloat(element.getText().trim().replaceAll("\\$", "").replaceAll(",", "")));

		}
		ArrayList<Float> cloneArrayList = new ArrayList<>();
		for (Float elementText : elementNameList) {
			cloneArrayList.add(elementText);

		}
		Collections.sort(cloneArrayList);
		Collections.reverse(cloneArrayList);

		return elementNameList.equals(cloneArrayList);

	}

	public boolean isPagingIconDisplayCorrect(String iconText) {
		return isElementDisplayed(driver, ProductPageUIs.PAGING_ICON, iconText);

	}

	public boolean isProductNumberDisplayCorrect(String productNumber) {
		waitForAllElementVisisble(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		List<WebElement> elements = getElements(driver, ProductPageUIs.PRODUCT_ITEM_BY_PRICE);
		return elements.size() <= Integer.parseInt(productNumber);
	}

	public void clickToPagingIcon(String iconText) {
		waitForElementClickable(driver, ProductPageUIs.PAGING_ICON, iconText);
		clickToElement(driver, ProductPageUIs.PAGING_ICON, iconText);
	}

	public boolean isPagingIconUndisplayed(String iconText) {
		return isElementUnDisplayed(driver, ProductPageUIs.PAGING_ICON, iconText);
	}

}
