package au.com.org.usermgmtservice.model;

import java.io.Serializable;

/**
 * @author sekhar
 *
 */
public class Address implements Serializable{
	
	private static final long serialVersionUID = -6924816032041003236L;
	

	private String street;
	
	private String city;
	
	private String state;
	
	private Long postcode;

	
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
	
	public Address(String street, String city, String state, Long postcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", postcode=" + postcode + "]";
	}
	
}
