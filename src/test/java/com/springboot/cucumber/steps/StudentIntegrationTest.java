package com.springboot.cucumber.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springboot.SpringBootPostgresqlApplication;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringBootPostgresqlApplication.class, loader = SpringBootContextLoader.class)
public class StudentIntegrationTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentIntegrationTest.class);

	@LocalServerPort
	private int serverPort;

	public String getCompleteLocalUrl(String path) {
		return "http://localhost:" + serverPort + path;
	}

	private String name;
	private String age;
	private String major;
	private String houseNumber;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private ObjectMapper objectMapper = new ObjectMapper();
	private HttpHeaders httpHeaders;
	private ResponseEntity<String> responseEntity;
	private static final String STUDENT_API_URL = "/student";
	private ObjectNode studentResponse;
	private RestTemplate restTemplate = new RestTemplate();

	@Given("^the request body contains name(.*), age(.*), major(.*), houseNumber(.*), street(.*), city(.*), state(.*), zipCode(.*)$")
	public void buildStudentObject(String name, String age, String major, String houseNumber, String street,
			String city, String state, String zipCode) {
		this.name = name;
		this.age = age;
		this.major = major;
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	@When("^send http POST request$")
	public void executePost() throws Exception {
		responseEntity = null;
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("test_header", "test_header");

		ObjectNode student = objectMapper.createObjectNode();
		ObjectNode address = objectMapper.createObjectNode();
		address.put("houseNumber", houseNumber);
		address.put("street", street);
		address.put("city", city);
		address.put("state", state);
		address.put("zipCode", zipCode);
		student.put("name", name);
		student.put("age", age);
		student.put("major", major);
		student.set("address", address);

		HttpEntity<String> httpEntity = new HttpEntity<>(student.toString(), httpHeaders);
		try {
			responseEntity = restTemplate.exchange(getCompleteLocalUrl(STUDENT_API_URL), HttpMethod.POST, httpEntity,
					String.class);
			if (responseEntity != null && responseEntity.getBody() != null)
				studentResponse = (ObjectNode) objectMapper.readTree(responseEntity.getBody());
			else
				LOG.info("RESPONSE IS NULL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^get response and verify httpCode(.*) responseMessage(.*)")
	public void get_response(String httpCode, String responseMessage) throws Exception {
		String aHttpCode, aResponseMessage, aResponseCode;
		if (studentResponse.findPath("errors") instanceof MissingNode) {
			aHttpCode = String.valueOf(responseEntity.getStatusCodeValue());
			aResponseMessage = studentResponse.get("response").get("responseMsg").textValue();
			aResponseCode = studentResponse.get("response").get("responseCode").textValue();
			assertThat(aHttpCode, is(httpCode));
			assertThat(aResponseMessage, is(responseMessage));
		}
	}
}
