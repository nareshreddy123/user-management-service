/**
 * 
 */
package au.com.org.usermgmtservice.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.ValidationErrorResponse;
import au.com.org.usermgmtservice.utility.ApplicationProperties;

/**
 * @author sekhar
 *
 */
@Component
public class UserDetailsValidator {


	@Autowired
	ApplicationProperties applicationProperties;

	public ValidationErrorResponse validateUserRequestForUserId(RetrieveUserDetailsRequest retrieveUserDetailsRequest) {

		if (null == retrieveUserDetailsRequest || null == retrieveUserDetailsRequest.getUserId()) {
			return new ValidationErrorResponse("userId", applicationProperties.getUserIdNull());
		} else if (!StringUtils.isNumeric(retrieveUserDetailsRequest.getUserId())) {
			return new ValidationErrorResponse("userId", applicationProperties.getUserIdNonNumeric());
		}
		return null;
	}
}
