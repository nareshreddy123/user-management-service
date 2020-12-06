/**
 * 
 */
package au.com.org.usermgmtservice.model;

import java.io.Serializable;

/**
 * @author sekhar
 *
 */
public class RetrieveUserDetailsRequest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7302442933950206643L;
	
	private String userId; 
	
	//This request can be enhanced in future to include any search criteria.

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RetrieveUserDetailsRequest(String userId) {
		super();
		this.userId = userId;
	}

	public RetrieveUserDetailsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof RetrieveUserDetailsRequest)) {
            return false;
        }

        RetrieveUserDetailsRequest req = (RetrieveUserDetailsRequest) o;

        return req.userId.equals(userId);
    }
	
	@Override
    public int hashCode() {
        int result = 23;
        result = 31 * result + userId.hashCode();
        return result;
    }
	
}
