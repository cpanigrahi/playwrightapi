package utillib;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.ReadContext;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class BaseTest {

	protected Playwright playwright;

	protected APIRequest request;

	protected static APIRequestContext context;

	protected static APIResponse response;

	protected static APIResponse responsebodyValue;

	protected static ObjectMapper objectMapper;

	protected static JsonNode jsonResponse;

	protected static String jsonPrettyRespose;

	protected static ReadContext responseBody;

	protected static APIResponse responseBodyData;

	protected static APIResponse responseDelete;

	protected ExtentTest test;
	protected ExtentReports extent;
	protected ExtentSparkReporter spark;

	@BeforeTest
	public void beforTest() {

		extent = new ExtentReports();

		spark = new ExtentSparkReporter("Playwright6.html");

		extent.attachReporter(spark);

		spark.config().setReportName("Playwright Report");

		spark.config().setTheme(Theme.STANDARD);

		test = extent.createTest("Playwright automation test");

		test.info("Test executed");

		test.pass("test cases executed");

		playwright = Playwright.create();

		request = playwright.request();

		context = request.newContext();

	}

	@AfterTest
	public void afterTest() {

		context.dispose();

		playwright.close();

		extent.flush();

	}

}
