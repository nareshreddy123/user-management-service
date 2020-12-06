/**
 * 
 */
package au.com.org.usermgmtservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.utility.ObjectCreatorUtility;

/**
 * @author sekhar
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagementAPITest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void retrieveUserDetailsBadRequestForNull() throws RestClientException, MalformedURLException {

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/retrieve").toString(), null,
				Object.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void retrieveUserDetailsInternalErrorForNotWellFormedAuthKey()
			throws RestClientException, MalformedURLException {

		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new ObjectCreatorUtility()
				.CreateRetrieveUserDetailsRequest();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("TestingNotWellFormedKey");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/retrieve").toString(),
				new HttpEntity<>(retrieveUserDetailsRequest, httpHeaders), Object.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

	@Test
	public void retrieveUserDetailsValidateNumeric() throws RestClientException, MalformedURLException {

		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new ObjectCreatorUtility()
				.CreateRetrieveUserDetailsRequest();
		retrieveUserDetailsRequest.setUserId("AB1234");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/retrieve").toString(),
				new HttpEntity<>(retrieveUserDetailsRequest, httpHeaders), Object.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("{errorField=userId, errorMessage=User Id can only be numeric}",
				response.getBody().toString());
	}

	@Test
	public void retrieveUserDetailsValidResponse() throws RestClientException, MalformedURLException {

		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new ObjectCreatorUtility()
				.CreateRetrieveUserDetailsRequest();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/retrieve").toString(),
				new HttpEntity<>(retrieveUserDetailsRequest, httpHeaders), Object.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(
				"{userId=345627, title=Mrs, firstName=Mary, lastName=Parker, emailId=Mary.Parker@org.com.au, gender=Female, address={street=123 Rowling Rd, city=Sydney, state=NSW, postcode=2000}}",
				response.getBody().toString());

	}

	@Test
	public void updateUserDetailsBadRequestForNull() throws RestClientException, MalformedURLException {

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/update").toString(), null,
				Object.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void updateUserDetailsInternalErrorForNotWellFormedAuthKey()
			throws RestClientException, MalformedURLException {

		UserDetails userDetails = new ObjectCreatorUtility().createUserDetails();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("TestingNotWellFormedKey");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/update").toString(),
				new HttpEntity<>(userDetails, httpHeaders), Object.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

	}

	@Test
	public void updateUserDetailsUserIdInvalid() throws RestClientException, MalformedURLException {

		UserDetails userDetails = new ObjectCreatorUtility().createUserDetails();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/update").toString(),
				new HttpEntity<>(userDetails, httpHeaders), Object.class);

		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		assertEquals("{message=UserId doesn't exist, Please check and try again}", response.getBody().toString());

	}

	@Test
	public void updateUserDetailsValidResponse() throws RestClientException, MalformedURLException {

		UserDetails userDetails = new ObjectCreatorUtility().createUserDetails();
		userDetails.setUserId(345627L);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setBasicAuth("dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0");

		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/api/usermgmt/userdetails/update").toString(),
				new HttpEntity<>(userDetails, httpHeaders), Object.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

}
