package com.deepsouthsoftware.uatshootout;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFindKioskFlow {	
	private WebDriver driver;
			
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/fluffbox-rwx");
	}

	@Test
	public void testFindKioskFlow() throws Exception {
		driver.findElement(By.xpath("//a[contains(@href, '/fluffbox-rwx/kiosk/find')]"))
					.click();
		driver.findElement(By.id("searchCriteria"))
					.sendKeys("Ft. Lauderdale");
		driver.findElement(By.id("searchButton")).click();
		
		WebElement findSpeakersHereLink = null;
		
		for (int second = 0;; second++) {
			if (second >= 60) 
				fail("timeout");
			try { 
				 findSpeakersHereLink = driver.findElement(By.linkText("Find Speakers Here"));
				 break; 
			} catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		findSpeakersHereLink.click();
		
		driver.findElement(By.linkText("Aleksandar Seovic")).click();
		driver.findElement(By.linkText("RENT NOW!")).click();
		driver.findElement(By.linkText("Continue")).click();
		
		driver.findElement(By.id("username")).sendKeys("joeuser");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("loginButton")).click();
		
		assertTrue(driver.getPageSource().indexOf("This concludes your fake rental experience!") != -1);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
