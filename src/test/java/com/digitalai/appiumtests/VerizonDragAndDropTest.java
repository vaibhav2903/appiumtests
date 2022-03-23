package com.digitalai.appiumtests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org. junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VerizonDragAndDropTest {

	private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo0NiwieHAucCI6MSwieHAubSI6MTYxODMxOTY4MTkwNywiZXhwIjoxOTMzNjc5NjgyLCJpc3MiOiJjb20uZXhwZXJpdGVzdCJ9.dQn-RQn-uTvynZAxHtXwDgLFyvGZbp0ASfdO62U3Tr4";
	protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

	@Before
	public void setUp() throws MalformedURLException {
		dc.setCapability("testName", "Quick Start iOS Native Demo");
		dc.setCapability("accessKey", accessKey);
		dc.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
		// dc.setCapability("appiumVersion", "2.0.beta.cv");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		dc.setCapability("appiumVersion", "1.22.2");
		dc.setBrowserName("Chrome");
		dc.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		driver = new AndroidDriver<>(new URL("https://lisbon.experitest.com/wd/hub"), dc);
	//	driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	}


	@Test
	public void testingneo() throws InterruptedException {
		driver.navigate().to("https://verizon.com/5g/home/");
		Set<String> s = driver.getContextHandles();
		for (String ss : s) {
			if (ss.contains("NATIVE")) {
				driver.context(ss);
				break;
			}
		}

		Thread.sleep(3000);

		WebElement signInButton = driver.findElement(By.xpath("//*[@text='Check availability']"));
		signInButton.click();

		Thread.sleep(3000);

		for (String ss : s) {
			if ((ss.contains("CHROMIUM")) || (ss.contains("WEB"))) {
				driver.context(ss);
				break;
			}
		}

		WebElement address = driver.findElement(By
				.xpath("(//*[contains(@id,'streetLocusAddress') or contains(@aria-label,'Street Address')])[last()]"));
//		String X = address.getAttribute("x");
//		int x = Integer.parseInt(X);
//		String Y = address.getAttribute("y");
//		int y = Integer.parseInt(Y);
//		String bounds = pin.getAttribute("bounds");
		address.click();
		address.sendKeys("2660 N Haskell");

		Thread.sleep(8000);

		WebElement addresssel = driver.findElement(By.xpath("//*[@id='selectedOption']"));
		addresssel.click();

		Thread.sleep(3000);

		WebElement aptnum = driver.findElement(By.xpath("//*[@id='apartmentNumber']"));
		aptnum.click();
		aptnum.sendKeys("UNIT 3137");

		Thread.sleep(8000);

		WebElement highloghted = driver.findElement(By.xpath("//*[@class='item-highlighted']"));
		highloghted.click();

		Thread.sleep(3000);

		WebElement cont = driver.findElement(By.xpath("//*[@id='continue-cta']"));
		cont.click();

		Thread.sleep(8000);

		WebElement flr = driver.findElement(By.xpath("//*[@id='selectFloor']"));
		flr.click();
		flr.sendKeys("1");

		Thread.sleep(8000);

		WebElement cont2 = driver
				.findElement(By.xpath("//*[@aria-label='Continue' and @role='button' and @aria-disabled='false']"));
		cont2.click();

		Thread.sleep(8000);

		// MAP PAGE
		// NEED TO DRAG AND DROP THE PIN
		WebElement pin = driver.findElement(
				By.xpath("//*[@src='//www.verizonwireless.com/content/dam/vzwcom/5g/images/Orangepin.png']"));
		
		//pin.click();
		TouchAction action = new TouchAction(driver);
		String X = pin.getAttribute("x");
		//pin.attribute
		System.out.println("Rectangle coordinates");
		System.out.println(pin.getRect().getX());
		System.out.println(pin.getRect().getY());
		System.out.println("Rectangle coordinates Ends here");
		System.out.println(pin.getSize().getHeight());
		System.out.println(pin.getSize().getWidth());
		int x = Integer.parseInt(X);
		String Y = pin.getAttribute("y");
		int y = Integer.parseInt(Y);
		String bounds = pin.getAttribute("bounds");
		//pin.get
		System.out.println("X,Y cooridinates: "+x+", "+y);
		WebElement el = pin;
//		System.out.println(el.getLocation().getX()+", " +el.getLocation().getY()); // first [x,y]
//			System.out.println(el.getLocation().getX() +el.getSize().getWidth()+", " +(el.getLocation().getY()+el.getSize().getHeight()));
		//action.
//		pin.get
		 int pinX = pin.getLocation().getX();
		 int pinY = pin.getLocation().getY();
		 System.out.println(pinX + ", "+ pinY);
		 System.out.println(((AndroidElement)(pin)).getCenter());
		 //action.tap(new PointOption<>().withCoordinates(pinX, pinY));
		
		int leftX = x;
		int rightX = leftX+ pin.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		int upperY = y;
		int lowerY = upperY + pin.getSize().getHeight();
		int middleY = (upperY + lowerY) / 2;
		System.out.println(middleX + " " +middleY);
		 System.out.println(bounds);
		 
		//action.longPress(pin).dragAndDropBy(pin, pinX+100, pinY).perform();
//		action.longPress(new PointOption().withCoordinates(564, 1384))	
//				.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(3)))
//				.moveTo(new PointOption<>().withCoordinates(591, 1434))
//				.release()
//				.perform();
		// action.tap(new PointOption<>().withCoordinates(830, 1740));
//		 action.tap(element((driver.findElement(
//					By.xpath("//*[@src='//www.verizonwireless.com/content/dam/vzwcom/5g/images/Orangepin.png']"))))).perform();
//		 action.press(element((pin))).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(5))).moveTo(new PointOption<>().withCoordinates(830, 1740)).release()
//			.perform();	;
//		action.press(new PointOption<>().withCoordinates(716, 1709))
//		.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(5)))
//		.moveTo(new PointOption<>().withCoordinates(830, 1740))
//		.release()
//		.perform();	
		
		
		action.press(new PointOption<>().withCoordinates(545, 1406))
		.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(5)))
		.moveTo(new PointOption<>().withCoordinates(655, 1471))
		.release()
		.perform();	
		// The above is for cloud appiumOSS - Pixel 5
		
		
		
		// action.press({})
		 
//		 (new TouchAction(driver))
//		  .press({x: 544, y: 1384})
//		  .moveTo({x: 628: y: 1462})
//		  .release()
//		  .perform()
		 
		 

		Thread.sleep(3000);
	}
	
	@After
    public void tearDown() {
        System.out.println("Report URL: "+ driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }
	
	

}
