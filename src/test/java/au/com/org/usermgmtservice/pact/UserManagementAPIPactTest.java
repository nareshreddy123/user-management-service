/**
 * 
 */
package au.com.org.usermgmtservice.pact;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.service.UserDetailsServiceImpl;
import au.com.org.usermgmtservice.utility.ApplicationProperties;
import au.com.org.usermgmtservice.utility.ObjectCreatorUtility;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8080")
@Provider("userservice")
@PactFolder("src/test/resources")
public class UserManagementAPIPactTest {

	@MockBean
	private UserDetailsServiceImpl userDetailsService;

	@MockBean
	private ApplicationProperties ApplicationProperties;

	@BeforeEach
	void setupTestTarget(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void pactVerificationTestTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}

	@State({ "retrieves the user" })
	public void retrieveUserDetails() throws GenericSystemException {

		UserDetails userDetails = new ObjectCreatorUtility().createUserDetailsPact();

		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new ObjectCreatorUtility()
				.CreateRetrieveUserDetailsRequest();

		when(userDetailsService.createTransactionId("retrieveUserDetails - "))
				.thenReturn("retrieveUserDetails - 1670461597-");
		when(userDetailsService.verifyAuthorization("retrieveUserDetails - 1670461597-",
				"dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0")).thenReturn("admin");

		when(userDetailsService.retrieveUserDetails("retrieveUserDetails - 1670461597-", retrieveUserDetailsRequest))
				.thenReturn(userDetails);

	}

	@State({ "update the user" })
	public void updateUserDetails() throws GenericSystemException {

		UserDetails userDetails = new ObjectCreatorUtility().createUserDetailsPact();
		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new RetrieveUserDetailsRequest();
		retrieveUserDetailsRequest.setUserId("345627");

		when(userDetailsService.createTransactionId("updateUserDetails - "))
				.thenReturn("updateUserDetails - 1670461597-");
		when(ApplicationProperties.getAdminAccess()).thenReturn("admin");
		when(userDetailsService.verifyAuthorization("updateUserDetails - 1670461597-",
				"dXNlcm1nbXQtYWRtaW46YWRtaW4xMjM0")).thenReturn("admin");

		doNothing().when(userDetailsService).updateUserDetails("updateUserDetails - 1670461597-", userDetails);

	}

}
