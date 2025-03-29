package testpackages;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pagelib.UserPojo;
import utillib.BaseTest;
import utillib.UtilLib;

public class SendPostRequest extends BaseTest {

	String userId;

	UserPojo user;

	@Test(priority = 1, description = "Sending post request ")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending post request for generating the data")
	@Story("creating user in the database")
	public void sendPost() throws IOException {

		user = new UserPojo();
		user.setName(UtilLib.getRandomName());
		user.setEmail(UtilLib.getRandomMail());
		user.setGender("male");
		user.setStatus("active");

		UtilLib.sendPostRequest(UtilLib.getProperty("getURL"), user, "Content-Type", "application/json",
				"Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(responsebodyValue.status(), 201, "Status code not verified");

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonResponse = objectMapper.readTree(responsebodyValue.body());

		String jsonPrettyRespose = jsonResponse.toPrettyString();

		System.out.println(jsonPrettyRespose);

		userId = jsonResponse.get("id").asText();

		System.out.println(userId);

	}

	@Test(priority = 2, description = "Sending get request to see the result")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending get request for fetching the data")
	@Story("Fetch the data from the database")
	public void sendGetRequest() throws IOException {

		UtilLib.sendGetRequest(UtilLib.getProperty("URL") + userId, "Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(response.status(), 200, "Status code not verified");

		System.out.println(response.text());

		AssertJUnit.assertTrue(response.text().contains(userId));

	}

	@Test(priority = 3, description = "Sending Put request to update the result")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending put request for update the data")
	@Story("Update user in the database")
	public void sendPutRequest() throws IOException {

		user.setStatus("Inactive");

		user.setName("Playwright Automation");

		UtilLib.sendPutRequest(UtilLib.getProperty("URL") + userId, user, "Content-Type", "application/json",
				"Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(responseBodyData.status(), 200, "Status code not verified");

		String putResponseText = responseBodyData.text();
		System.out.println(putResponseText);

		System.out.println(responseBodyData.status() + " : " + responseBodyData.statusText());
		AssertJUnit.assertEquals(responseBodyData.status(), 200);

	}

	@Test(priority = 4, description = "Sending get request to fetch the result")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending get request for fetch the data")
	@Story("Fetching user from the database")
	public void sendGetRequest1() throws IOException {

		UtilLib.sendGetRequest(UtilLib.getProperty("URL") + userId, "Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(response.status(), 200, "Status code not verified");

		System.out.println(response.text());

		AssertJUnit.assertTrue(response.text().contains(userId));

	}

	@Test(priority = 5, description = "Sending delete request to delete the data")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending delete request for delete the data")
	@Story("Deleteing user from the database")
	public void sendDelete() throws IOException {

		UtilLib.sendDeleteRequest(UtilLib.getProperty("URL") + userId, "Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(responseDelete.status(), 204, "Status code not verified");

		System.out.println("User Id deleted = " + responseDelete.text());

	}

	@Test(priority = 6, description = "Sending get request to fetch the result")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending get request for fetch the data")
	@Story("Fetching the user details from database")
	public void sendGetRequest2() throws IOException {

		UtilLib.sendGetRequest(UtilLib.getProperty("URL") + userId, "Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(response.status(), 404, "Status code not verified");

		System.out.println(response.text());

	}

}
