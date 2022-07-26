package utility;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGenerate {

	String pathOfReport = ".\\WEB_Feature_Flow_Test_Cases_001\\Reports\\";

	ExtentSparkReporter sparkReport;
	ExtentReports extentReport;
	ExtentTest extentTest;
	ExtentTest extentNode;

	public void generateReport(String nameOfReport) {
		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(pathOfReport + nameOfReport + ".html");
		extentReport.attachReporter(sparkReport);

		extentReport.setSystemInfo("Platform", "Windows");
		extentReport.setSystemInfo("Browser", "Chrome");
		extentReport.setSystemInfo("Java version", "JDK 17");
		extentReport.setSystemInfo("User", "Jordan");
	}

	public void createTest(String testName) {
		extentTest = extentReport.createTest(testName);
	}
	
	public void createNode(String nodeName) {
		extentNode = extentTest.createNode(nodeName);
	}

	public ExtentSparkReporter setSparkReport() {
		return sparkReport;
	}

	public ExtentReports setExtentReport() {
		return extentReport;
	}

	public ExtentTest setExtentTest() {
		return extentTest;
	}

	public ExtentTest setExtentNode() {
		return extentNode;
	}
}