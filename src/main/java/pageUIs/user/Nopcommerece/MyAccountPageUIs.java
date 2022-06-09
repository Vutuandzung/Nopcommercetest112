package pageUIs.user.Nopcommerece;

public class MyAccountPageUIs {
	public static final String GENDER_RADIO_BUTTON = "//div[@class='gender']//input[@value='%s']";
	public static final String MY_ACCOUNT_SUBMENU = "//div[@class='listbox']//a[contains(string(),'%s')]";
	public static final String DATE_OF_BIRTH = "//select[@name='%s']";
	public static final String EDIT_FIELD = "//li[@class='%s' and contains(string(),'%s')]";
	public static final String SUCCESS_CHANGED_PASSWORD_MESSAGE = "//span[@class='close']";
	public static final String ADD_REVIEW_LINK = "//a[text()='Add your review']";
	public static final String REVIEW_RADIO = "//input[@name='AddProductReview.Rating' and @aria-label='%s']";
	public static final String REVIEW_TEXT = "//textarea[@name='AddProductReview.ReviewText']";
	public static final String REVIEW_DISPLAYED = "//div[@class='review-title']//strong[text()='Perfect Product']";
}
