package apipractice;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import utillib.UtilLib;

public class GetRequest extends Orders {

	@Test()
	public void getMethodTest1() throws IOException {

		APIResponse responseValue = Playwright.create().request().newContext().get(UtilLib.getProperty("getURL"));

		System.out.println("Method one result = " + responseValue.text());
		
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode postJsonResponse = objectMapper.readTree(responseValue.body());

		String value = postJsonResponse.toPrettyString();

		ReadContext readcontext = JsonPath.parse(value);

		Object userId = readcontext.read("$[2].id");

		System.out.println("user id : " + userId);
	}

	//@Test()
	public void getMethodTest2() {

		APIResponse responseValue = Playwright.create().request().newContext().get(UtilLib.getProperty("getURL"));

		System.out.println("Method two result = " + responseValue.text());
	}

}
