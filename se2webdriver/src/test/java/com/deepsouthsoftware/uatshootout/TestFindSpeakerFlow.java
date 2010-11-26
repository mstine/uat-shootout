package com.deepsouthsoftware.uatshootout;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFindSpeakerFlow {	
	private WebDriver driver;
			
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/fluffbox-rwx");
	}

	@Test
	public void testFindSpeakerFlow() throws Exception {
		driver.findElement(By.xpath("//a[contains(@href, '/fluffbox-rwx/speaker/find')]"))
				  .click();
		driver.findElement(By.linkText("Alex Miller")).click();
		driver.findElement(By.linkText("Find this Speaker")).click();
		driver.findElement(By.linkText("RENT NOW")).click();
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
