/**
 * 
 */
package au.com.org.usermgmtservice.service;


import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;

/**
 * @author sekhar
 *
 */
public interface IUserDetailsService {
	
	UserDetails retrieveUserDetails(String transactionId, RetrieveUserDetailsRequest retrieveUserDetailsRequest);
	
	void updateUserDetails(String transactionId, UserDetails userDetailsDTO) throws GenericSystemException;
	
	String verifyAuthorization(String transactionId, String encoded) throws GenericSystemException;
	
	String createTransactionId(String transaction);
	
}
