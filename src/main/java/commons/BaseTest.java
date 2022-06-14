package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected static ThreadLocal<String> emailRd = new ThreadLocal<>();
	protected static ThreadLocal<String> emailEdit = new ThreadLocal<>();
	Random rand = new Random();

	protected final Log log;

	// Constructor

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge_chrominum")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please enter correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge_chrominum")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please enter correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);

		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl, String ipAddress, String portNumber) {
		DesiredCapabilities capability = null;
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
		} else if (browserName.equalsIgnoreCase("chrome")) {
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.merge(capability);
		} else if (browserName.equalsIgnoreCase("edge_chrominum")) {
			WebDriverManager.edgedriver().setup();
			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			capability.setPlatform(Platform.WINDOWS);
			EdgeOptions options = new EdgeOptions();
			options.merge(capability);
		} else {
			throw new RuntimeException("Please enter correct browser name!");
		}
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)),
					capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);

		return driver;
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public static String getRandomEmail() {
		Random rand = new Random();
		return "dungvutuan" + rand.nextInt(99999) + "@gmail.com";
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		System.out.println("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		System.out.println("---------- END delete file in folder ----------");
	}

	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\ReportNGScreenshots";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserAndDriver() {
//		String cmd = "";
//		try {
//			String osName = System.getProperty("os.name").toLowerCase();
//			log.info("OS name = " + osName);
//
//			String driverInstanceName = driver.toString().toLowerCase();
//			log.info("Driver instance name = " + driverInstanceName);
//
//			if (driverInstanceName.contains("chrome")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
//				} else {
//					cmd = "pkill chromedriver";
//				}
//			} else if (driverInstanceName.contains("internetexplorer")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
//				}
//			} else if (driverInstanceName.contains("firefox")) {
//				if (osName.contains("windows")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
//				} else {
//					cmd = "pkill geckodriver";
//				}
//			} else if (driverInstanceName.contains("edge")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
//				} else {
//					cmd = "pkill msedgedriver";
//				}
//			} else if (driverInstanceName.contains("opera")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
//				} else {
//					cmd = "pkill operadriver";
//				}
//			} else if (driverInstanceName.contains("safari")) {
//				if (osName.contains("mac")) {
//					cmd = "pkill safaridriver";
//				}
//			}
//
//			if (driver != null) {
//				driver.manage().deleteAllCookies();
//				driver.quit();
//			}
//		} catch (Exception e) {
//			log.info(e.getMessage());
//		} finally {
//			try {
//				Process process = Runtime.getRuntime().exec(cmd);
//				process.waitFor();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		driver.quit();
	}

}
