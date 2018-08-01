Feature: Student
	@RegressionTest
	Scenario Outline: Make Post Call to student api
		Given the request body contains name<name>, age<age>, major<major>, houseNumber<houseNumber>, street<street>, city<city>, state<state>, zipCode<zipCode>
		When send http POST request
		Then get response and verify httpCode<httpCode> responseMessage<responseMessage>
		
		Examples:
			| name| age| major| houseNumber| street| city| state| zipCode| httpCode| responseMessage|
			| john| 12| physics| 12| 123 street| salem| alabama| 12345| 201| record added to database|