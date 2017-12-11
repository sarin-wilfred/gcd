package org.unico.assign.gcd.dao;

import org.unico.assign.gcd.domain.User;

/**
 * 
 * @author Sarin
 *
 */

public interface UserDao {
	
	/**
	 * This method returns the User Object
	 * 
	 * @param userName
	 * @param password
	 * @return User
	 */
	public User getUser(String userName, String password);
}
