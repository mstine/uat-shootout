package com.deepsouthsoftware.uatshootout;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFindSpeakerFlow {
	private WebClient webClient;
				
	@Before
	public void setUp() throws Exception {
		webClient = new WebClient();
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}

	@Test
	public void testFindSpeakerFlow() throws Exception {
		HtmlPage fluffboxHomePage = webClient.getPage("http://localhost:8080/fluffbox-rwx");
		HtmlPage firstSpeakerListPage = ((HtmlAnchor) fluffboxHomePage.getFirstByXPath("//a[contains(@href, '/fluffbox-rwx/speaker/find')]")).click();
		HtmlPage alexMillerDetailPage = firstSpeakerListPage.getAnchorByText("Alex Miller").click();
		HtmlPage kioskPage = alexMillerDetailPage.getAnchorByText("Find this Speaker").click();
		HtmlPage rentalPage = kioskPage.getAnchorByText("RENT NOW").click();
		HtmlPage loginPage = rentalPage.getAnchorByText("Continue").click();
		
		HtmlForm loginForm = loginPage.getFormByName("loginForm");
		loginForm.getInputByName("j_username").setValueAttribute("joeuser");
		loginForm.getInputByName("j_password").setValueAttribute("password");

		HtmlPage lastPage = loginPage.getAnchorByText("Log in").click();
		assertTrue(lastPage.asText().contains("This concludes your fake rental experience!"));
	}

	@After
	public void tearDown() throws Exception {
		webClient.closeAllWindows();
	}
}
