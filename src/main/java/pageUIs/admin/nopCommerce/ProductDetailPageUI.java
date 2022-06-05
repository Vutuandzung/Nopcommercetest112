package pageUIs.admin.nopCommerce;

public class ProductDetailPageUI {
	public static final String EXPAND_BY_NAME = "//i[@class='%s']/parent::div//following-sibling::div[@class='card-tools float-right']//i[contains(@class,'fa toggle-icon fa')]";
	public static final String UPLOAD_BUTTON_BY_INDEX = "(//input[@title='file input'])[%s]";
	public static final String UPLOAD_PICTURE_IMAGE_PENDING = "//img[contains(@src,'%s')]";
	public static final String ALT_TEXTBOX = "//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String TITLE_TEXTBOX = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DISPLAY_ORDER_ICON = "//div[@class='card card-default']//span[@class='k-icon k-i-arrow-60-%s']";
	public static final String ADD_PRODUCT_BUTTON = "//button[@id='addProductPicture']";
	public static final String SUCCESS_IMAGE_DISPLAYED = "//img[contains(@src,'adobe-photoshop-cs4')]/ancestor::td/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
	public static final String SAVE_BUTTON = "//button[@name='save']";
	public static final String DELETE_BUTTON = "//a[normalize-space()='Delete']";
	public static final String DELETE_SUCCESS_MESSAGE = "//table[@id='productpictures-grid']//td[text()='No data available in table']";
	public static final String PRODUCT_IMAGE_DEFAULT = "//td[text()='Adobe Photoshop CS4']/preceding-sibling::td//img[contains(@src,'default-image')]";
}
