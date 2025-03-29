package testpackages;

import java.io.IOException;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import utillib.BaseTest;
import utillib.UtilLib;

public class SendGetRequest extends BaseTest {

	@Test(priority = 1, description = "Sending get request to fetch the result")
	@Severity(SeverityLevel.NORMAL)
	@Description("Sending get request for fetch the data")
	@Story("Fetching the user details from database")
	public void sendGet() throws IOException {

		UtilLib.sendGetRequest(UtilLib.getProperty("getURL"), "Authorization", UtilLib.getProperty("token"));

		UtilLib.verifyStatusCode(response.status(), 200, "Status code not verified");

		UtilLib.validateHeader();

		//AssertJUnit.assertEquals(UtilLib.validateHeader().get("server"), "cloudflare", "server header is not validated");

		UtilLib.validateJsonBody();

		Integer firstId = responseBody.read("$[0].id");

		System.out.println("First ID = " + firstId);

	}

}
