/**
 * 
 */
package au.com.org.usermgmtservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.org.usermgmtservice.exception.AuthorizationException;
import au.com.org.usermgmtservice.exception.EntityNotAvailableException;
import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.model.UserManagementErrorRespone;
import au.com.org.usermgmtservice.model.ValidationErrorResponse;
import au.com.org.usermgmtservice.service.IUserDetailsService;
import au.com.org.usermgmtservice.utility.ApplicationProperties;
import au.com.org.usermgmtservice.validator.UserDetailsValidator;

/**
 * @author sekhar
 *
 */

@RestController
@RequestMapping("/api/usermgmt/userdetails/")
public class UserManagementAPI {

	private static final Logger logger = LoggerFactory.getLogger(UserManagementAPI.class);

	@Autowired
	private IUserDetailsService userDetailsService;

	@Autowired
	private UserDetailsValidator userDetailsValidator;

	@Autowired
	private ApplicationProperties ApplicationProperties;

	@RequestMapping(method = RequestMethod.POST, value = "/retrieve")
	public ResponseEntity<Object> retrieveUserDetails(@RequestHeader("Authorization") String authorizationToken,
			@RequestBody RetrieveUserDetailsRequest retrieveUserDetailsRequest) {

		String transactionId = userDetailsService.createTransactionId("retrieveUserDetails - ");

		try {
			logger.info(transactionId + "START: UserManagementAPI.retrieveUserDetails");

			userDetailsService.verifyAuthorization(transactionId,
					authorizationToken.substring("Basic".length()).trim());

			ValidationErrorResponse validationErrorResponse = userDetailsValidator
					.validateUserRequestForUserId(retrieveUserDetailsRequest);

			if (null != validationErrorResponse) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
			}

			return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
					.body(userDetailsService.retrieveUserDetails(transactionId, retrieveUserDetailsRequest));

		} catch (AuthorizationException exception) {
			logger.debug(transactionId + "AuthorizationException occurred with exception message :"
					+ exception.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new UserManagementErrorRespone(exception.getMessage()));
		} catch (Exception excep) {
			logger.debug(transactionId + "Exception occurred with exception message :" + excep.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserManagementErrorRespone(excep.getMessage()));
		} finally {
			logger.info(transactionId + "END: UserManagementAPI.retrieveUserDetails");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ResponseEntity<Object> updateUserDetails(@RequestHeader("Authorization") String authorizationToken,
			@RequestBody UserDetails userDetails) {

		String transactionId = userDetailsService.createTransactionId("updateUserDetails - ");

		String privilege;

		try {

			logger.info(transactionId + "START: UserManagementAPI.updateUserDetails");

			privilege = userDetailsService.verifyAuthorization(transactionId,
					authorizationToken.substring("Basic".length()).trim());

			if (privilege.equalsIgnoreCase(ApplicationProperties.getAdminAccess())) {
				userDetailsService.updateUserDetails(transactionId, userDetails);
				return ResponseEntity.status(HttpStatus.OK).body("");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new UserManagementErrorRespone(ApplicationProperties.getAuthorizationError()));
			}
		} catch (AuthorizationException exception) {
			logger.debug(transactionId + "AuthorizationException occurred with exception message :"
					+ exception.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new UserManagementErrorRespone(exception.getMessage()));
		} catch (EntityNotAvailableException exception) {
			logger.debug(transactionId + "Generic System Exception occurred with exception message :"
					+ exception.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new UserManagementErrorRespone(exception.getMessage()));
		} catch (GenericSystemException exception) {
			logger.debug(transactionId + "Generic System Exception occurred with exception message :"
					+ exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserManagementErrorRespone(exception.getMessage()));
		} catch (Exception exception) {
			logger.debug(transactionId + "Exception occurred with exception message :" + exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserManagementErrorRespone(exception.getMessage()));
		} finally {
			logger.info(transactionId + "END: UserManagementAPI.updateUserDetails");
		}
	}

}
