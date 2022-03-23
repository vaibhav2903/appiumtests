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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumImageRecognitionFindElementByImageTest {
	
	 private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo1MTg2NjM1LCJ4cC5wIjoyLCJ4cC5tIjoxNjMzMDE4MzcyMjYyLCJleHAiOjE5NDgzNzgzNzIsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.oj1mv7xZSdmETWX3E3g19Nh0WLZMjYEPfkRnyjlTcgg";
	    protected IOSDriver<IOSElement> driver = null;
	    DesiredCapabilities dc = new DesiredCapabilities();

	    @Before
	    public void setUp() throws MalformedURLException {
	        dc.setCapability("testName", "Quick Start iOS Native Demo");
	        dc.setCapability("accessKey", accessKey);
	        dc.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
	        dc.setCapability("appiumVersion", "1.22.2.opencv");
	        //dc.setCapability("appiumVersion", "2.0.beta.cv");
	        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
	        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
	        driver = new IOSDriver<>(new URL("https://mastercloud.experitest.com/wd/hub"), dc);
	    }

	    @Test
	    public void quickStartiOSNativeDemo() {
	        driver.rotate(ScreenOrientation.PORTRAIT);
	        
	        byte[] fileContent;
			try {
				fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\VaibhavSavala\\Desktop\\login_button_ios.png"));
				String encodedString = Base64.getEncoder().encodeToString(fileContent);
				//driver.findElement(MobileBy.image(encodedString)).click();
				By image = MobileBy.image(encodedString);
				new WebDriverWait(driver, 10)
				  .until(ExpectedConditions.presenceOfElementLocated(image)).click();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
//	        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
//	        driver.hideKeyboard();
//	        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
//	        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
//	        driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
//	        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("0541234567");
//	        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Jon Snow");
//	        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("50");
//	        driver.findElement(By.xpath("//*[@id='countryButton']")).click();
//	        driver.findElement(By.xpath("//*[@id='Switzerland']")).click();
//	        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
//	        driver.findElement(By.xpath("//*[@id='Yes']")).click();
	    }

	    @After
	    public void tearDown() {
	        System.out.println("Report URL: "+ driver.getCapabilities().getCapability("reportUrl"));
	        driver.quit();
	    }
	
	

}
