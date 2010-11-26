package com.deepsouthsoftware.uatshootout;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFindKioskFlow {
	private WebClient webClient;
				
	@Before
	public void setUp() throws Exception {
		webClient = new WebClient();
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}

	@Test
	public void testFindSpeakerFlow() throws Exception {
		HtmlPage fluffboxHomePage = webClient.getPage("http://localhost:8080/fluffbox-rwx");
		HtmlPage kioskListPage = ((HtmlAnchor) fluffboxHomePage.getFirstByXPath("//a[contains(@href, '/fluffbox-rwx/kiosk/find')]")).click();
		
		HtmlForm searchKiosksForm = kioskListPage.getFormByName("searchKiosksForm");
		searchKiosksForm.getInputByName("searchCriteria").setValueAttribute("Ft. Lauderdale");
		searchKiosksForm.getInputByName("searchButton").click();
		
		HtmlPage speakersHerePage = kioskListPage.getAnchorByText("Find Speakers Here").click();
		HtmlPage alexMillerDetailPage = speakersHerePage.getAnchorByText("Alex Miller").click();
		HtmlPage rentalPage = alexMillerDetailPage.getAnchorByText("RENT NOW!").click();
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
