package com.digitalai.appiumtests.opencv;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumOpenCVSimilarityTest {

	private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo1MTg2NjM1LCJ4cC5wIjoyLCJ4cC5tIjoxNjMzMDE4MzcyMjYyLCJleHAiOjE5NDgzNzgzNzIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.oj1mv7xZSdmETWX3E3g19Nh0WLZMjYEPfkRnyjlTcgg";
	protected AndroidDriver<AndroidElement> driver = null;
	DesiredCapabilities dc = new DesiredCapabilities();

	@Before
	public void setUp() throws MalformedURLException {
		dc.setCapability("testName", "Quick Start iOS Native Demo");
		dc.setCapability("accessKey", accessKey);
		dc.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
		//dc.setCapability("appiumVersion", "1.22.2.opencv");
		 dc.setCapability("appiumVersion", "2.0.beta.cv");
		//dc.setCapability("udid", "00008110-000271422252801E");
		//dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		driver = new AndroidDriver<>(new URL("https://mastercloud.experitest.com/wd/hub"), dc);
	}

	@Test
	public void test() throws IOException {
		byte[] fileContent;
		fileContent = FileUtils
				.readFileToByteArray(new File("C:\\Users\\VaibhavSavala\\Desktop\\android_similarity_img.png"));
		byte[] screenshot1 = Base64.getEncoder().encode(fileContent);
		byte[] screenshot2 = Base64.getEncoder().encode(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver.getImagesSimilarity(screenshot1, screenshot2,
				new SimilarityMatchingOptions().withEnabledVisualization());
		assertTrue(result.getVisualization().length > 0);
		assertTrue(result.getScore() > 0);
	}

	@After
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
	}

}
