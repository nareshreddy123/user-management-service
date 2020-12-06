/**
 * 
 */
package au.com.org.usermgmtservice.model;

import java.io.Serializable;

/**
 * @author sekhar
 *
 */
public class ValidationErrorResponse implements Serializable{
	
	
	
	private static final long serialVersionUID = 4193471870385583149L;
	
	private String errorField;
	private String errorMessage;
	
	
	public ValidationErrorResponse(String errorField, String errorMessage) {
		super();
		this.errorField = errorField;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorField() {
		return errorField;
	}
	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

