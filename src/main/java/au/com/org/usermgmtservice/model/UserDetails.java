package au.com.org.usermgmtservice.model;

import java.io.Serializable;
/**
 * @author sekhar
 *
 */
public class UserDetails implements Serializable{


	private static final long serialVersionUID = 2925525145584529070L;
	
	private Long userId;

	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String gender;
	
	private Address address;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public UserDetails(Long userId, String title, String firstName, String lastName, String emailId, String gender,
			Address address) {
		super();
		this.userId = userId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "UserDetailsDTO [userId=" + userId + ", title=" + title + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", gender=" + gender + ", address=" + address + "]";
	}
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}


