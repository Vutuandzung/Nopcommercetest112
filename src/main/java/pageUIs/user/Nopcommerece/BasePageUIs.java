package pageUIs.user.Nopcommerece;

public class BasePageUIs {
	public static final String HEADER_UPPER_LINK_BY_CLASS = "//div[@class='header-upper']//*[@class='%s']";
	public static final String BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String ERROR_MESSAGE_BY_ID = "//span[@id='%s' and contains(string(),'%s')]";
	public static final String TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String ERROR_MESSAGE_BY_STRING = "//div[contains(string(),'%s')]";
	public static final String PRODUCT_ITEM_BY_TEXT = "//div[@class='item-grid']//a[text()='%s']";
	public static final String HEADER_MENU = "//div[@class='header-menu']//ul[@class='top-menu notmobile']//a[contains(string(),'%s')]";
	public static final String HEADER_SUBMENU = "//div[@class='header-menu']//ul[@class='top-menu notmobile']//a[contains(string(),'%s')]/following-sibling::ul//a[contains(string(),'%s')]";
}
