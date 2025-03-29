package testngtestcases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import utillib.UtilLib;

public class GetRequestSupermarket {

	Playwright playwright;

	APIRequest request;

	APIRequestContext context;

	APIResponse response;

	ObjectMapper objectMapper;

	JsonNode jsonResponse;

	String jsonPrettyRespose;

	Logger log = LogManager.getLogger(GetRequestSupermarket.class);

	public void sendSuperMarketGetRequest() {

		try {

			playwright = Playwright.create();

			request = playwright.request();

			context = request.newContext();

			response = context.get(UtilLib.getProperty("baseURl"));

			objectMapper = new ObjectMapper();

			jsonResponse = objectMapper.readTree(response.body());

			jsonPrettyRespose = jsonResponse.toPrettyString();

			System.out.println(jsonPrettyRespose);

			log.info("Chandan");

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	public void validateStatusCode() {

		int statuscode = response.status();

		Assert.assertEquals(200, statuscode, "Status Code not matched");

		// System.out.println("Status code validated");

		log.info("Chandan Panigrahi padampur");

	}

}
