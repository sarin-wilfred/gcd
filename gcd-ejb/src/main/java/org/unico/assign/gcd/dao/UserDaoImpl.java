package org.unico.assign.gcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.unico.assign.gcd.domain.User;

/**
 * 
 * @author Sarin
 *
 */

public class UserDaoImpl implements UserDao {

	@PersistenceContext(unitName = "unico.persistenceContext")
	EntityManager entityManager;

	/**
	 * 
	 * @return entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * This method returns the User Object
	 * 
	 * @param userName
	 * @param password
	 * @return User
	 */
	@Override
	public User getUser(String username, String password) {
		User user = null;
		List<User> users = (List<User>) getEntityManager().createNativeQuery(
				"SELECT * FROM USER where userName = '" + username + "' and password = md5('" + password + "')",
				User.class).getResultList();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

}
