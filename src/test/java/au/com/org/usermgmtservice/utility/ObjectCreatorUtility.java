/**
 * 
 */
package au.com.org.usermgmtservice.utility;


import au.com.org.usermgmtservice.model.Address;
import au.com.org.usermgmtservice.model.AddressBean;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.model.UserDetailsBean;

/**
 * @author sekhar
 *
 */
public class ObjectCreatorUtility {
	
	
	public RetrieveUserDetailsRequest CreateRetrieveUserDetailsRequest() {
		
		return new RetrieveUserDetailsRequest("345627");
		
	}

	
	public UserDetails createUserDetails() {
	
		return new UserDetails(1234L,"Mr","John","Louis","John.Louis@org.com.au","Male",new Address("123 Station St", "Sydney", "NSW", 2000L));		
	}
	
	
	public UserDetailsBean createUserDetailsBean() {
		
		return new UserDetailsBean(1234L,"Mr","John","Louis","John.Louis@org.com.au","Male",new AddressBean(2,"123 Station St", "Sydney", "NSW", 2000L));		
	}
	
	
	public UserDetails createUserDetailsPact() {
		
		return new UserDetails(345627L,"Mrs","Mary","Parker","Mary.Parker@org.com.au","Female",new Address("123 Rowling Rd", "Sydney", "NSW", 2000L));		
	}
	
	
	
}

