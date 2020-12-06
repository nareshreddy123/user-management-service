/**
 * 
 */
package au.com.org.usermgmtservice.dao;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import au.com.org.usermgmtservice.exception.EntityNotAvailableException;
import au.com.org.usermgmtservice.exception.GenericSystemException;
import au.com.org.usermgmtservice.exception.InvalidEmailIDException;
import au.com.org.usermgmtservice.model.RetrieveUserDetailsRequest;
import au.com.org.usermgmtservice.model.UserDetailsBean;
import au.com.org.usermgmtservice.utility.ApplicationProperties;
import au.com.org.usermgmtservice.model.UserDetails;

/**
 * @author sekhar
 *
 */
@Repository
public class UserDetailsDAOImpl implements IUserDetailsDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsDAOImpl.class);

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Override
	public UserDetailsBean retrieveUserDetailsById(String transactionId,
			RetrieveUserDetailsRequest retrieveUserDetailsRequest) {

		EntityManager entityManager = emf.createEntityManager();
		try {

			logger.info(transactionId + "START : UserDetailsDAOImpl.retrieveUserDetails with user ID :"
					+ retrieveUserDetailsRequest.getUserId());

			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			return entityManager.find(UserDetailsBean.class, Long.parseLong(retrieveUserDetailsRequest.getUserId()));
		} finally {
			entityManager.close();
			logger.info(transactionId + "END : UserDetailsDAOImpl.retrieveUserDetails");
		}
	}

	@Override
	public void updateUserDetails(String transactionId, UserDetails userDetails) throws GenericSystemException {
		// TODO Auto-generated method stub

		EntityManager entityManager = emf.createEntityManager();

		try {
			logger.info(transactionId + "START : UserDetailsDAOImpl.updateUserDetails with user ID :"
					+ userDetails.getUserId());

			entityManager.getTransaction().begin();

			UserDetailsBean userDetailsUpdate = entityManager.find(UserDetailsBean.class, userDetails.getUserId());

			if (null != userDetailsUpdate) {
				userDetailsUpdate = mapUserDetailsForNonNullValues(userDetailsUpdate, userDetails);

				entityManager.merge(userDetailsUpdate);

				// Rollback condition implemented after merge. Circuit break condition is
				// invalid emailId supplied to update the data.
				Predicate<String> checkValidEmail = (s) -> {
					return Pattern.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+", s);
				};

				if (!checkValidEmail.test(userDetailsUpdate.getEmailId())) {
					logger.debug(transactionId
							+ "Invalid email address supplied for updation, hence rolling back the changes");
					throw new InvalidEmailIDException(applicationProperties.getUpdateRollbackForInvalidEmail());
				}
				entityManager.getTransaction().commit();

			} else {
				throw new EntityNotAvailableException(applicationProperties.getEntityUnavailableError());

			}
		} catch (EntityNotAvailableException exception) {

			// TODO: handle exception
			logger.debug(transactionId
					+ "EntityNotAvailableException occured in UserDetailsDAOImpl.updateUserDetails with exception message :"
					+ exception.getMessage());
			throw exception;

		} catch (InvalidEmailIDException exception) {

			// ROLL BACK TRANSACTION AND RETRUNING EXCEPTION MESSAGE BACK
			entityManager.getTransaction().rollback();
			throw new GenericSystemException(exception.getMessage());

		} finally {
			entityManager.close();
			logger.info(transactionId + "END : UserDetailsDAOImpl.updateUserDetails");
		}
	}

	private UserDetailsBean mapUserDetailsForNonNullValues(UserDetailsBean userDetailsUpdate, UserDetails userDetails) {

		userDetailsUpdate
				.setTitle(userDetails.getTitle() != null ? userDetails.getTitle() : userDetailsUpdate.getTitle());
		userDetailsUpdate.setFirstName(
				userDetails.getFirstName() != null ? userDetails.getFirstName() : userDetailsUpdate.getFirstName());
		userDetailsUpdate.setLastName(
				userDetails.getLastName() != null ? userDetails.getLastName() : userDetailsUpdate.getLastName());
		userDetailsUpdate.setEmailId(
				userDetails.getEmailId() != null ? userDetails.getEmailId() : userDetailsUpdate.getEmailId());
		userDetailsUpdate
				.setGender(userDetails.getGender() != null ? userDetails.getGender() : userDetailsUpdate.getGender());

		if (userDetails.getAddress() != null) {
			userDetailsUpdate.getAddress()
					.setStreet(userDetails.getAddress().getStreet() != null ? userDetails.getAddress().getStreet()
							: userDetailsUpdate.getAddress().getStreet());
			userDetailsUpdate.getAddress()
					.setCity(userDetails.getAddress().getCity() != null ? userDetails.getAddress().getCity()
							: userDetailsUpdate.getAddress().getCity());
			userDetailsUpdate.getAddress()
					.setState(userDetails.getAddress().getState() != null ? userDetails.getAddress().getState()
							: userDetailsUpdate.getAddress().getState());
			userDetailsUpdate.getAddress()
					.setPostcode(userDetails.getAddress().getPostcode() != null ? userDetails.getAddress().getPostcode()
							: userDetailsUpdate.getAddress().getPostcode());
		}

		userDetailsUpdate.setModifiedDate(new java.util.Date());

		return userDetailsUpdate;

	}

	public UserDetailsDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

}
