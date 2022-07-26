package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenshot {

	String screenShotPath = ".\\WEB_Feature_Flow_Test_Cases_001\\Screenshots\\";

	SysPropertyDriver setSysPropertyDriver;

	public TakeScreenshot(SysPropertyDriver setSysPropertyDriver) {
		this.setSysPropertyDriver = setSysPropertyDriver;
	}
	
	public void takeScreenShot(String fileName) {
//		setSPAD = new Store_System_Property_And_Driver();
		File screenShot = ((TakesScreenshot) setSysPropertyDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + ".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}