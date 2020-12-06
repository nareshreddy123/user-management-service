/**
 * 
 */
package au.com.org.usermgmtservice.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author sekhar
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	@Value("${user.id.null}")
	private String userIdNull;

	@Value("${user.id.nonnumeric}")
	private String userIdNonNumeric;

	@Value("${entity.unavailable.error}")
	private String entityUnavailableError;

	@Value("${invalid.email.update.rollback}")
	private String updateRollbackForInvalidEmail;

	@Value("${non.admin.user.username}")
	private String nonAdminUsername;

	@Value("${non.admin.user.password}")
	private String nonAdminPassword;

	@Value("${admin.user.username}")
	private String adminUsername;

	@Value("${admin.user.password}")
	private String adminPassword;

	@Value("${access.read}")
	private String readAccess;

	@Value("${access.admin}")
	private String adminAccess;

	@Value("${authentication.error}")
	private String authenticationError;

	@Value("${authorization.insufficient.priv}")
	private String authorizationError;

	public String getUserIdNull() {
		return userIdNull;
	}

	public void setUserIdNull(String userIdNull) {
		this.userIdNull = userIdNull;
	}

	public String getUserIdNonNumeric() {
		return userIdNonNumeric;
	}

	public void setUserIdNonNumeric(String userIdNonNumeric) {
		this.userIdNonNumeric = userIdNonNumeric;
	}

	public String getEntityUnavailableError() {
		return entityUnavailableError;
	}

	public void setEntityUnavailableError(String entityUnavailableError) {
		this.entityUnavailableError = entityUnavailableError;
	}

	public String getUpdateRollbackForInvalidEmail() {
		return updateRollbackForInvalidEmail;
	}

	public void setUpdateRollbackForInvalidEmail(String updateRollbackForInvalidEmail) {
		this.updateRollbackForInvalidEmail = updateRollbackForInvalidEmail;
	}

	public String getNonAdminUsername() {
		return nonAdminUsername;
	}

	public void setNonAdminUsername(String nonAdminUsername) {
		this.nonAdminUsername = nonAdminUsername;
	}

	public String getNonAdminPassword() {
		return nonAdminPassword;
	}

	public void setNonAdminPassword(String nonAdminPassword) {
		this.nonAdminPassword = nonAdminPassword;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getReadAccess() {
		return readAccess;
	}

	public void setReadAccess(String readAccess) {
		this.readAccess = readAccess;
	}

	public String getAdminAccess() {
		return adminAccess;
	}

	public void setAdminAccess(String adminAccess) {
		this.adminAccess = adminAccess;
	}

	public String getAuthenticationError() {
		return authenticationError;
	}

	public void setAuthenticationError(String authenticationError) {
		this.authenticationError = authenticationError;
	}

	public String getAuthorizationError() {
		return authorizationError;
	}

	public void setAuthorizationError(String authorizationError) {
		this.authorizationError = authorizationError;
	}

}
