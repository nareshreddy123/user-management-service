package au.com.org.usermgmtservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sekhar
 *
 */
@Entity
@Table(name = "address_details")
public class AddressBean implements Serializable{
	
	private static final long serialVersionUID = -6924816032041003236L;
	
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "postcode")
	private Long postcode;
		
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getPostcode() {
		return postcode;
	}
	public void setPostcode(Long postcode) {
		this.postcode = postcode;
	}
	
	public AddressBean(String street, String city, String state, Long postcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
	}
	
	
	public AddressBean(int addressId, String street, String city, String state, Long postcode) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
	}
	
	public AddressBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AddressBean [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", postcode=" + postcode + "]";
	}	
		
}
