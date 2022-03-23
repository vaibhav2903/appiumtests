package com.digitalai.appiumtests.opencv;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class ImageRecognitionAndroid {

	private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo1MTg2NjM1LCJ4cC5wIjoyLCJ4cC5tIjoxNjMzMDE4MzcyMjYyLCJleHAiOjE5NDgzNzgzNzIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.oj1mv7xZSdmETWX3E3g19Nh0WLZMjYEPfkRnyjlTcgg";
	protected AndroidDriver<AndroidElement> driver = null;
	DesiredCapabilities dc = new DesiredCapabilities();

	@Before
	public void setUp() throws MalformedURLException {
		dc.setCapability("testName", "Quick Start Android Native Demo");
		dc.setCapability("accessKey", accessKey);
		//dc.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
		 dc.setCapability("appiumVersion", "2.0.beta.cv");
//		dc.setCapability("appiumVersion", "1.22.2.opencv");
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability("automationName", "UIAutomator2");
		driver = new AndroidDriver<>(new URL("https://mastercloud.experitest.com/wd/hub"), dc);
	}

	@Test
	public void quickStartAmdroidNativeDemo() {
		driver.rotate(ScreenOrientation.PORTRAIT);

		byte[] fileContent;
		try {
			fileContent = FileUtils
					.readFileToByteArray(new File("C:\\Users\\VaibhavSavala\\Desktop\\android_login_button.png"));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			// driver.findElement(MobileBy.image(encodedString)).click();
			By image = MobileBy.image(encodedString);
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(image)).click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
	}

}
