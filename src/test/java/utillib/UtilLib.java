package utillib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.microsoft.playwright.options.RequestOptions;

public class UtilLib extends BaseTest {

	public static String getProperty(String key) {

		Properties properties = new Properties();

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
			properties = new Properties();
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	public static String getRandomMail() {
		String emailId = "Automation" + System.currentTimeMillis() + "@gmail.com";
		return emailId;
	}

	public static String getRandomName() {
		String name = "TestAutomation" + System.currentTimeMillis();
		return name;
	}

	public static void sendGetRequest(String url, String auth, String token) {

		response = context.get(url, RequestOptions.create().setHeader(auth, token));

	}

	public static void sendPostRequest(String applicationUrl, Object body, String headerType, String headerValue,
			String auth, String token) {

		responsebodyValue = context.post(applicationUrl,
				RequestOptions.create().setData(body).setHeader(headerType, headerValue).setHeader(auth, token));
	}

	public static void sendPutRequest(String applicationUrl, Object body, String headerType, String headerValue,
			String auth, String token) {

		responseBodyData = context.put(applicationUrl,
				RequestOptions.create().setData(body).setHeader(headerType, headerValue).setHeader(auth, token));
	}

	public static void sendDeleteRequest(String applicationUrl, String auth, String token) {

		responseDelete = context.delete(applicationUrl, RequestOptions.create().setHeader(auth, token));
	}

	public static void verifyStatusCode(Object actualStatusCode, Object expectedStatusCode, String StatusCodeMessage) {

		Assert.assertEquals(actualStatusCode, expectedStatusCode, StatusCodeMessage);
	}

	public static Map<String, String> validateHeader() {

		Map<String, String> headerData = response.headers();

		System.out.println("Header Size = " + headerData.size());

		for (Entry<String, String> headervalue : headerData.entrySet()) {
			System.out.println(headervalue.getKey() + " = " + headervalue.getValue());
		}

		return headerData;

	}

	public static ReadContext validateJsonBody() throws IOException {

		objectMapper = new ObjectMapper();

		jsonResponse = objectMapper.readTree(response.body());

		jsonPrettyRespose = jsonResponse.toPrettyString();

		System.out.println("String = " + jsonPrettyRespose);

		responseBody = JsonPath.parse(jsonPrettyRespose);

		return responseBody;

	}

}
