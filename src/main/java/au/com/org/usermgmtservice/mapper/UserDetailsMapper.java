/**
 * 
 */
package au.com.org.usermgmtservice.mapper;

import org.springframework.stereotype.Component;

import au.com.org.usermgmtservice.model.Address;
import au.com.org.usermgmtservice.model.UserDetailsBean;
import au.com.org.usermgmtservice.model.UserDetails;

/**
 * @author sekhar
 * 
 *         This class is used to map DTO to Enity and Vice versa
 *
 */

@Component
public class UserDetailsMapper {

	// This can be enhanced to do DTO to Entity mappings

	public UserDetails convertUserDetailsEntitytoDTO(UserDetailsBean userDetailsBean) {

		Address address = null;

		if (null != userDetailsBean.getAddress())
			address = new Address(userDetailsBean.getAddress().getStreet(), userDetailsBean.getAddress().getCity(),
					userDetailsBean.getAddress().getState(), userDetailsBean.getAddress().getPostcode());
		
		return new UserDetails(userDetailsBean.getUserId(), userDetailsBean.getTitle(), userDetailsBean.getFirstName(),
				userDetailsBean.getLastName(), userDetailsBean.getEmailId(), userDetailsBean.getGender(), address);

	}

}
