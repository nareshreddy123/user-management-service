/**
 * 
 */
package au.com.org.usermgmtservice.model;

import java.io.Serializable;

/**
 * @author sekhar
 *
 */
public class UserManagementErrorRespone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4573979584854673529L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserManagementErrorRespone(String message) {
		super();
		this.message = message;
	}

}
