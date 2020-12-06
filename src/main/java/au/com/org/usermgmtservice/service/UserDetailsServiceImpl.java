/**
 * 
 */
package au.com.org.usermgmtservice.service;

import javax.transaction.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.com.org.usermgmtservice.dao.IUserDetailsDAO;
import au.com.org.usermgmtservice.exception.AuthorizationException;
import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.mapper.UserDetailsMapper;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.utility.ApplicationProperties;

/**
 * @author sekhar
 *
 */
@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDetailsMapper UserDetailsMapper;

	@Autowired
	private IUserDetailsDAO userDetailsDAO;

	@Autowired
	private ApplicationProperties ApplicationProperties;

	@Override
	@Transactional
	public UserDetails retrieveUserDetails(String transactionId,
			RetrieveUserDetailsRequest retrieveUserDetailsRequest) {
		// TODO Auto-generated method stub

		try {
			logger.info(transactionId + "START: UserDetailsServiceImpl.retrieveUserDetails");
			return UserDetailsMapper.convertUserDetailsEntitytoDTO(
					userDetailsDAO.retrieveUserDetailsById(transactionId, retrieveUserDetailsRequest));
		} finally {
			logger.info(transactionId + "END: UserDetailsServiceImpl.retrieveUserDetails");
		}
	}

	@Override
	@Transactional
	public void updateUserDetails(String transactionId, UserDetails userDetails) throws GenericSystemException {
		// TODO Auto-generated method stub

		try {

			logger.info(transactionId + "START: UserDetailsServiceImpl.updateUserDetails");
			userDetailsDAO.updateUserDetails(transactionId, userDetails);

		} finally {
			logger.info(transactionId + "END: UserDetailsServiceImpl.updateUserDetails");
		}
	}

	public String verifyAuthorization(String transactionId, String encoded) throws GenericSystemException {
		try {
			logger.info(transactionId + "START:  UserDetailsServiceImpl.verifyAuthorization");

			String[] userDetails = new String(Base64.decodeBase64(encoded.getBytes())).split(":", 2);

			if (ApplicationProperties.getNonAdminUsername().equals(userDetails[0])
					&& ApplicationProperties.getNonAdminPassword().equals(userDetails[1]))
				return ApplicationProperties.getReadAccess();
			else if (ApplicationProperties.getAdminUsername().equals(userDetails[0])
					&& ApplicationProperties.getAdminPassword().equals(userDetails[1]))
				return ApplicationProperties.getAdminAccess();
			else
				throw new AuthorizationException(ApplicationProperties.getAuthenticationError());

		} catch (AuthorizationException excep) {

			logger.debug(transactionId + "AuthorizationException occured while validating the encoded credentials");
			throw excep;

		} catch (Exception exception) {

			logger.debug(transactionId + "Exception occured while validating the encoded credentials");
			throw new GenericSystemException("Exception occured while validating the encoded credentials with message :"
					+ exception.getMessage());

		} finally {

			logger.info(transactionId + "END:  UserDetailsServiceImpl.verifyAuthorization");

		}
	}

	// Random transaction Id created to track the logs in different layers. It'll be
	// helpful in log tracing in tools like Splunk etc to trace down a transaction.
	public String createTransactionId(String transaction) {
		return transaction + (int) (Math.random() * Integer.MAX_VALUE) + "-";
	}
}
