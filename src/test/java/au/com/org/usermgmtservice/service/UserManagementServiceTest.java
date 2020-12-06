/**
 * 
 */
package au.com.org.usermgmtservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import au.com.org.usermgmtservice.dao.IUserDetailsDAO;
import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.mapper.UserDetailsMapper;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetails;
import au.com.org.usermgmtservice.model.UserDetailsBean;
import au.com.org.usermgmtservice.utility.ApplicationProperties;
import au.com.org.usermgmtservice.utility.ObjectCreatorUtility;

/**
 * @author sekhar
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagementServiceTest {
	
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService; 	
	
	@Mock
	private UserDetailsMapper UserDetailsMapper;
	
	@Mock
	private IUserDetailsDAO userDetailsDAO;
	
	@Mock
	private ApplicationProperties ApplicationProperties;
	
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void retrieveUserDetailsTest() {
		
		RetrieveUserDetailsRequest retrieveUserDetailsRequest = new ObjectCreatorUtility().CreateRetrieveUserDetailsRequest();
		UserDetails userDetails = new ObjectCreatorUtility().createUserDetails();
		UserDetailsBean userDetailsBean = new ObjectCreatorUtility().createUserDetailsBean();
				
		when(userDetailsDAO.retrieveUserDetailsById("retrieveUserDetails - 1095319072-", retrieveUserDetailsRequest)).thenReturn(userDetailsBean);
		when(UserDetailsMapper.convertUserDetailsEntitytoDTO(userDetailsBean)).thenReturn(userDetails);
				
		UserDetails retUserDetails = userDetailsService.retrieveUserDetails("retrieveUserDetails - 1095319072-", retrieveUserDetailsRequest);
		
		assertEquals(userDetails, retUserDetails);
		verify(userDetailsDAO, times(1)).retrieveUserDetailsById("retrieveUserDetails - 1095319072-", retrieveUserDetailsRequest);
	}
	
	@Test
	public void retrieveUserDetailsTestNull() {
		
		
		when(UserDetailsMapper.convertUserDetailsEntitytoDTO(userDetailsDAO.retrieveUserDetailsById("retrieveUserDetails - 1095319072-", null))).thenThrow(NullPointerException.class);
		
		try {
			UserDetails retUserDetails= userDetailsService.retrieveUserDetails("retrieveUserDetails - 1095319072-", null);
			} catch (NullPointerException e) {
				System.out.println("**********NullPointerException Caught***********");
			}
		
	}
	
	@Test
	public void updateUserDetailsTest() throws GenericSystemException {
		
		UserDetails userDetails = new ObjectCreatorUtility().createUserDetails();
				
		doNothing().when(userDetailsDAO).updateUserDetails("updateUserDetails - 1670461597-", userDetails);
		userDetailsService.updateUserDetails("updateUserDetails - 1670461597-", userDetails);
		
	}
	
	@Test
	public void updateUserDetailsTestNull() throws GenericSystemException {
		
		doThrow(new NullPointerException()).when(userDetailsDAO).updateUserDetails("updateUserDetails - 1670461597-", null);
				
		try {
			userDetailsService.updateUserDetails("updateUserDetails - 1670461597-", null);
		} catch (NullPointerException e) {
			System.out.println("**********NullPointerException Caught***********");
		}
		
	}
	
}
