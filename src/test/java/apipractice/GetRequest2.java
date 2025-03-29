package apipractice;

import org.testng.annotations.Test;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import utillib.UtilLib;

public class GetRequest2 extends Orders {

	@Test()
	public void getMethodTest3() {

		APIResponse responseValue = Playwright.create().request().newContext().get(UtilLib.getProperty("getURL"));

		System.out.println("Method Three result = " + responseValue.text());
	}

	@Test()
	public void getMethodTest4() {

		APIResponse responseValue = Playwright.create().request().newContext().get(UtilLib.getProperty("getURL"));

		System.out.println("Method Four result = " + responseValue.text());
	}

}
