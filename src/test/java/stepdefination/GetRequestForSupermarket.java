package stepdefination;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testngtestcases.GetRequestSupermarket;

public class GetRequestForSupermarket {

	GetRequestSupermarket superMarket = new GetRequestSupermarket();

	@Before
	public void before() {
		System.out.println("Before all methods");
	}

	@Given("Send a GET request to the search endpoint")
	public void send_a_get_request_to_the_search_endpoint() {

		superMarket.sendSuperMarketGetRequest();

	}

	@When("I get a response from the server")
	public void i_get_a_response_from_the_server() {

		superMarket.validateStatusCode();
	}

	@Then("The Response should contain the status code")
	public void the_response_should_contain_the_status_code() {

		superMarket.validateStatusCode();
	}

	@Then("Then Validate the status body")
	public void then_validate_the_status_body() {

		superMarket.validateStatusCode();
	}

	@After
	public void after() {
		System.out.println("After all methods");
	}

}
