package pageObject.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.MyDashboardPageUI;

public class MyDasboardPageObject extends BasePage {
	private WebDriver driver;

	public MyDasboardPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public void clickToSubMenuByName(String menuName, String subMenuName) {
		waitForElementClickable(driver, MyDashboardPageUI.SUBMENU_BY_NAME, menuName, subMenuName);
		clickToElement(driver, MyDashboardPageUI.SUBMENU_BY_NAME, menuName, subMenuName);
	}

	public void clickToMenuByName(String menuName) {
		waitForElementClickable(driver, MyDashboardPageUI.MENU_BY_NAME, menuName);
		clickToElement(driver, MyDashboardPageUI.MENU_BY_NAME, menuName);

	}

}
