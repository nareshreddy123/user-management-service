package au.com.org.usermgmtservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author sekhar
 *
 */
@Entity
@Table(name = "user_details")
public class UserDetailsBean implements Serializable{


	private static final long serialVersionUID = 2925525145584529070L;
	
	@Id
	@Column(name = "user_id")
	private Long userId;

	@Column( name = "title")
	private String title;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_Id")
	private String emailId;
	
	@Column(name = "gender")
	private String gender;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private AddressBean address;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	
	
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
	public AddressBean getAddress() {
		return address;
	}
	public void setAddress(AddressBean address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	public UserDetailsBean(Long userId, String title, String firstName, String lastName, String emailId, String gender,
			AddressBean address) {
		super();
		this.userId = userId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.address = address;
	}
	
	
	
	
	public UserDetailsBean(Long userId, String title, String firstName, String lastName, String emailId, String gender,
			AddressBean address, Date createdDate, Date modifiedDate) {
		super();
		this.userId = userId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.address = address;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	
	public UserDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDetailsBean [userId=" + userId + ", title=" + title + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", gender=" + gender + ", address=" + address + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
		
}


