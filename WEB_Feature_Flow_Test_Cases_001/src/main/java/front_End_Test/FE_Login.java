package front_End_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import utility.ReportGenerate;
import utility.SysPropertyDriver;
import utility.TakeScreenshot;

public class FE_Login {

	ReportGenerate runReportGenerate;
	SysPropertyDriver setSysPropertyDriver;
	TakeScreenshot takeScreenshot;

	@BeforeSuite
	public void runReportGenerator() {
		runReportGenerate = new ReportGenerate();
		runReportGenerate.generateReport("Login test");
	}

	@BeforeClass
	public void startWEBInstance() {
		runReportGenerate.createTest("START OF FEATURE TEST");
		setSysPropertyDriver = new SysPropertyDriver();
		setSysPropertyDriver.setDriver();
	}

	@Test(priority = 0)
	public void openURL() {
		runReportGenerate.createTest("Go to WL003017");
		setSysPropertyDriver.getDriver().get("https://wl003.the777888.com/");
		String URL = setSysPropertyDriver.getDriver().getCurrentUrl();
		if(URL.equals("https://wl003.the777888.com/")) {
			runReportGenerate.setExtentTest().info("Current site >>>>> " + URL);
		}
	}

//	testing to make the method skip =========================
//	@Test(priority = 2)
//	public void Skip_Test_Sample() throws InterruptedException {
//		runRG.createNode("Test skipped");
//		throw new SkipException("Skipping");
//	}

	@Test(priority = 1)
	public void closeAnnouncement() {
		runReportGenerate.createTest("Close Announcement");
		WebElement 今日不再显示 = setSysPropertyDriver.getDriver().findElement(By.xpath("//span[contains(text(),'今日不再显示')]"));
		String doNotShowAgainToday = 今日不再显示.getText();
		if (doNotShowAgainToday.equals("今日不再显示")) {
			今日不再显示.click();
			runReportGenerate.setExtentTest().info("Clicked >>>>> " + doNotShowAgainToday);
			WebElement closeAnnouncement = setSysPropertyDriver.getDriver().findElement(By.xpath("//div[@id='web_normal_announcement_popout']//button[@aria-label='Close']"));
			if (closeAnnouncement.isDisplayed()) {
				closeAnnouncement.click();
				runReportGenerate.setExtentTest().info("Clicked >>>>> Close announcement button");
			} 
		}
//		WebElement closeAnnouncement = setSysPropertyDriver.getDriver().findElement(By.xpath("//div[@id='web_normal_announcement_popout']//button[@aria-label='Close']"));
//		if (closeAnnouncement.isDisplayed()) {
//			closeAnnouncement.click();
//			runReportGenerate.setExtentTest().info("Clicked >>>>> Close announcement button");
//		}
		else {
				throw new SkipException("Skipping");
			}
		}
	
	@AfterClass
	public void stopProgram() throws InterruptedException {
		Thread.sleep(1000);
		setSysPropertyDriver.getDriver().quit();
		runReportGenerate.setExtentReport().flush();
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		String resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			runReportGenerate.setExtentTest().pass("<<<<< " + resultOfCaseStatus + " is passed! >>>>>");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			runReportGenerate.setExtentTest().skip("<<<<< " + resultOfCaseStatus + " is skipped! >>>>>");
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenshot = new TakeScreenshot(setSysPropertyDriver);
			takeScreenshot.takeScreenShot(resultOfCaseStatus);
			runReportGenerate.setExtentTest().fail("<<<<< " + resultOfCaseStatus + " is failed! >>>>>");
		}
	}
}
