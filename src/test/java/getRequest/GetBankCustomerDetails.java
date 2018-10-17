/**
 * @author Abhishek Singh
 * 
 */
package getRequest;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class GetBankCustomerDetails {

	@Test
	public void testSOAPWSDLService() {
		given().get("http://parabank.parasoft.com/parabank/services/ParaBank?wsdl").then().assertThat()
				.body("wsdl:definitions.wsdl:message", equalTo("initializeDBResponse"));
	}

	@Test
	public void testResponseCodeWitStaticRestImport() {
		System.out.println("now executing method xestResponseCodeWitStaticRestImport");

		Response response = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/");

		int statusCode = response.getStatusCode();
		System.out.println("Response code is" + statusCode);
		Assert.assertEquals(statusCode, 200);
		String wholeResponseBody = response.asString();
		System.out.println("wholeResponseBody is :" + wholeResponseBody);
		System.out.println("Time it took is :" + response.getTime());

	}

	@Test
	public void verifyCustomerName() {
		// Note equalTo() is coming from "import static
		// org.hamcrest.Matchers.*;" if u will remove this from import, will get
		// an error.
		System.out.println("now executing method verifyCustomerName()");
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat()
				.body("customer.firstName", equalTo("John"));
	}

	@Test
	public void verifyCustomernameUsingXpath() {
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat()
				.body(hasXPath("/customer/firstName"), containsString("John"));
	}

	/*
	 * To compare all the response in one go, although it's not recommmended
	 * b'coz for small response it is fine but for big response it may be
	 * confusing
	 * 
	 */

	@Test
	public void verifyWholeResponseTextInOneGo() {
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat()
				.body("customer.text()",
						equalTo("12212JohnSmith1431 Main StBeverly HillsCA90210310-447-4121622-11-9999"))
				.log().all();
	}

	@Test
	public void verifyCustomerFirstnameAndLastname() {
		System.out.println("now executing method verifyCustomerFirstnameAndLastname()");
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat()
				.body("customer.firstName", equalTo("Margo")).and().body("customer.lastName", equalTo("Gru"));

		// or we can write above given() statement in short form as well
		System.out
				.println("now executing method verifyCustomerFirstnameAndLastname() with short form of and condition");
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat()
				.body("customer.firstName", equalTo("Margo"), "customer.lastName", equalTo("Gru"));

	}

	@Test
	public void verifyCustomerStreetAddress() {
		System.out.println("now executing method verifyCustomerStreetAddress()");
		given().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").then().assertThat().body(
				"customer.firstName", equalTo("Margo"), "customer.lastName", equalTo("Gru"), "customer.address.street",
				equalTo("123 Sesame St"), "customer.address.zipCode", equalTo("90210"), "customer.phoneNumber",
				equalTo("0212345678"));

	}
}
