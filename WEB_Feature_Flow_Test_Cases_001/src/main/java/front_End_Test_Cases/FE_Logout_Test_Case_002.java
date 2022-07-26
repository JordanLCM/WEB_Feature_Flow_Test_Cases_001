package front_End_Test_Cases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class FE_Logout_Test_Case_002 {

	ExtentSparkReporter sparkReport;
	ExtentReports extentReport;
	ExtentTest extentTest;
	ExtentTest extentNode;
	FE_Logout_Test_Case_002 ProgramTakeScreenshot;
	static WebDriver driver;
	static String PathOfReport = ".\\WEB_Feature_Flow_Test_Cases_001\\Reports\\";
	static String NameOfReport = "FE_Logout";
	static String chromeDriver = "webdriver.chrome.driver";
	static String chromeDriverPath = ".\\src\\main\\resources\\chromedriver.exe";
	static String url = "https://wl003.the777888.com/";
	static String siteLink;
	static String screenShotPath = "\\Eclipse WorkSpace\\WEB_Feature_Flow_Test_Cases_001\\Screenshots\\";

	@BeforeSuite
	public void Start_And_Set_Reports_Data() {
//		runs extent reports
		extentReport = new ExtentReports();
//		creates a report in the path set plus the name of the report in .html format
		sparkReport = new ExtentSparkReporter(PathOfReport + NameOfReport + ".html");
//		attaches the extent report created to the spark report
		extentReport.attachReporter(sparkReport);
//		sets system settings listed by user when report is generated
		extentReport.setSystemInfo("Platform", "Windows");
		extentReport.setSystemInfo("Browser", "Chrome");
		extentReport.setSystemInfo("Java version", "JDK 17");
		extentReport.setSystemInfo("User", "Jordan");
	}

	@BeforeClass
	public void Start_WEB_Browser_Instance() {
		System.setProperty(chromeDriver, chromeDriverPath);
//		 starts a chrome driver instance
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		 maximises the browser page
		driver.manage().window().maximize();
//		creates a main test case
		extentTest = extentReport.createTest("WEB test");
	}

	@Test(priority = 0)
	public void Go_To_Site() throws InterruptedException {
//		creates a node in the main test
		extentNode = extentTest.createNode("Go_To_Site");
//		sets the url
		driver.get(url);
		Thread.sleep(500);
//		gets the current url
		siteLink = driver.getCurrentUrl();
//		if current url is same as user set url, then assertion is true & correct
		Assert.assertEquals(siteLink, url);
		extentNode.info("Clicked " + url);
	}

//	testing to make the method fail =========================
//	@Test(priority = 1)
//	public void Fail_Test_Sample() throws InterruptedException {
//		extentNode = extentTest.createNode("Fail_Test_Sample");
//		siteLink = driver.getCurrentUrl();
//		forced fail of this method
//		Assert.assertEquals(siteLink, "Fail");
//	}

//	testing to make the method skip =========================
//	@Test(priority = 2)
//	public void Skip_Test_Sample() throws InterruptedException {
//		extentTest = extentReport.createTest("Skip_Test_Sample");
//		throw new SkipException("Skipping");
//	}

	@Test(priority = 1)
	public void Close_Announcement() {
		extentNode = extentTest.createNode("Close_Announcement");
		WebElement 今日不再显示 = driver.findElement(By.xpath("//span[contains(text(),'今日不再显示')]"));
		String doNotShowAgainToday = 今日不再显示.getText();
		if (doNotShowAgainToday.equals("今日不再显示")) {
			今日不再显示.click();
			extentNode.info("Clicked " + doNotShowAgainToday);

			WebElement closeAnnouncement = driver
					.findElement(By.xpath("//div[@id='web_normal_announcement_popout']//button[@aria-label='Close']"));
			if (closeAnnouncement.isDisplayed()) {
				closeAnnouncement.click();
				extentNode.info("Clicked close announcement button");

			} else {
				throw new SkipException("Skipping");
			}
		}
	}

	@Test(priority = 2)
	public void Click_Login_Option() {
		extentNode = extentTest.createNode("Click_Login_Option");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement 登录 = driver.findElement(By.id("header_login"));
		String loginOption = 登录.getText();
		if (loginOption.equals("登录")) {
			登录.click();
			extentNode.info("Clicked " + loginOption);
		}
	}

	@Test(priority = 3)
	public void Enter_Data_Into_Login_Fields() throws IOException, InterruptedException {
		extentNode = extentTest.createNode("Enter_Data_Into_Login_Fields");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement 请输入帐号 = driver.findElement(By.id("username"));
		String userIDField = 请输入帐号.getAttribute("placeholder");
		String userID = "qctester01010101";
		if (userIDField.equals("请输入帐号")) {
			请输入帐号.clear();
			请输入帐号.sendKeys(userID);
			extentNode.info("UserID = " + userID);

			WebElement 请输入密码 = driver.findElement(By.id("password"));
			String passwordField = 请输入密码.getAttribute("placeholder");
			String password = "test123";
			if (passwordField.equals("请输入密码")) {
				请输入密码.clear();
				请输入密码.sendKeys(password);
				extentNode.info("Password = " + password);

				WebElement eyeIcon = driver.findElement(By.xpath("//div[@class='ico ico-eye_close']"));
				if (eyeIcon.isEnabled()) {
					eyeIcon.click();
					ProgramTakeScreenshot = new FE_Logout_Test_Case_002();
					ProgramTakeScreenshot.takeScreenShot("Eye icon");
					extentNode.info("Clicked eye icon").addScreenCaptureFromPath(screenShotPath + "Eye icon" + ".png");
					Thread.sleep(500);
					eyeIcon.click();

					WebElement 请输入验证码 = driver.findElement(By.id("captcha_code"));
					String captchaField = 请输入验证码.getAttribute("placeholder");
					String captcha = "123456";
					if (captchaField.equals("请输入验证码")) {
						请输入验证码.clear();
						请输入验证码.sendKeys(captcha);
						extentNode.info("Captcha = " + captcha);

						WebElement 登录button = driver.findElement(By.id("login_popup_btn"));
						String loginButton = 登录button.getText();
						if (登录button.isEnabled()) {
							登录button.click();
							extentNode.info("Clicked = " + loginButton);

						}
					}
				}
			}
		}
	}

	@Test(priority = 4)
	public void After_Login_Verify_User() throws InterruptedException {
		extentNode = extentTest.createNode("After_Login_Verify_User");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement userID_AfterLogin = driver.findElement(By.xpath(
				"//body/header/div[@class='header_top_outside']/div[@class='header_top']/div[@class='header_bottom_right']/div/div[@class='header_bottom_after_login']/div[2]/a[1]"));
		String userID = "qctester01010101";
		String userID_AL = userID_AfterLogin.getText();
		if (userID_AL.equals(userID)) {
			extentNode.info("Logged in with = " + userID_AL);
			Actions action = new Actions(driver);
			action.moveToElement(userID_AfterLogin).perform();
		}
	}

	@Test(priority = 5)
	public void Logout() throws InterruptedException {
		extentNode = extentTest.createNode("Logout");
		Thread.sleep(1000);
		WebElement 登出button = driver.findElement(By.xpath("//button[contains(text(),'登出')]"));
		String logoutButton = 登出button.getText();
		String logoutTxt = "登出";
		if (logoutButton.equals(logoutTxt)) {
			登出button.click();

			WebElement 登录 = driver.findElement(By.id("header_login"));
			if (登录.isDisplayed()) {
				extentNode.info("*** Log out success ***");
			}
		}
	}

	@AfterClass
	public void Stop_And_Close_WEB_Browser() throws InterruptedException {
//		forces the system to wait for 2 seconds
		Thread.sleep(1000);
//		stops the driver fully
		driver.quit();
//		generates the report
		extentReport.flush();
	}

	@AfterMethod
	public void Log_Test_Cases_Status(ITestResult result) throws IOException {
//		gets the name of the result
		String resultOfTestCases = result.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			ProgramTakeScreenshot = new FE_Logout_Test_Case_002();
//			system will take a screenshot of the page that the error occurred
			ProgramTakeScreenshot.takeScreenShot(resultOfTestCases);
//			a log of the error will be added into the generated report with screenshot
			extentNode.fail("*** " + resultOfTestCases + " is failed! ***" + " -----> " + resultOfTestCases + ".png")
					.addScreenCaptureFromPath(screenShotPath + resultOfTestCases + ".png");
		}
		if (result.getStatus() == ITestResult.SKIP) {
//			a log of the test being skipped will be added into the generated report
			extentNode.skip("*** " + resultOfTestCases + " is skipped! ***");
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
//			a log of the test being passed will be added into the generated report
			extentNode.pass("*** " + resultOfTestCases + " is passed! ***");
		}
	}

	public void takeScreenShot(String fileName) throws IOException {
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
//			screenshot is generated in the set path with its screenshot name in a .png format
			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
