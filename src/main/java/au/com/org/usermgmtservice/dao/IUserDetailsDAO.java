/**
 * 
 */
package au.com.org.usermgmtservice.dao;

import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetailsBean;
import au.com.org.usermgmtservice.model.UserDetails;

/**
 * @author sekhar
 *
 */
public interface IUserDetailsDAO {

	UserDetailsBean retrieveUserDetailsById(String transactionId, RetrieveUserDetailsRequest retrieveUserDetailsRequest);
	
	void updateUserDetails(String transactionId, UserDetails userDetailsDTO) throws GenericSystemException;

	
}
