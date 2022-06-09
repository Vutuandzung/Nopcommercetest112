package commons;

import org.openqa.selenium.WebDriver;

import pageObject.User.Nopcommerce.HomePageObject;
import pageObject.User.Nopcommerce.LoginUserPageObject;
import pageObject.User.Nopcommerce.MyAccountPageObject;
import pageObject.User.Nopcommerce.ProductPageObject;
import pageObject.User.Nopcommerce.RegisterPageObject;
import pageObject.admin.nopCommerce.LoginPageObject;
import pageObject.admin.nopCommerce.MyDasboardPageObject;
import pageObject.admin.nopCommerce.ProductDetailPageObject;
import pageObject.admin.nopCommerce.ProductSearchPageObject;

public class PageObjectGenerator {

	private PageObjectGenerator() {

	}

	public static PageObjectGenerator getPOG() {

		return new PageObjectGenerator();
	}

	public LoginPageObject getLoginPageAdmin(WebDriver driver) {

		return new LoginPageObject(driver);
	}

	public LoginUserPageObject getLoginPageUser(WebDriver driver) {
		return new LoginUserPageObject(driver);
	}

	public ProductDetailPageObject getProductDetailPage(WebDriver driver) {

		return new ProductDetailPageObject(driver);
	}

	public ProductSearchPageObject getProductSearchPage(WebDriver driver) {

		return new ProductSearchPageObject(driver);
	}

	public MyDasboardPageObject getMyDashboardPage(WebDriver driver) {

		return new MyDasboardPageObject(driver);
	}

	public HomePageObject getHomePage(WebDriver driver) {

		return new HomePageObject(driver);
	}

	public RegisterPageObject getRegisterPage(WebDriver driver) {

		return new RegisterPageObject(driver);
	}

	public MyAccountPageObject getAccountPage(WebDriver driver) {

		return new MyAccountPageObject(driver);
	}

	public ProductPageObject getProductPage(WebDriver driver) {

		return new ProductPageObject(driver);
	}
}