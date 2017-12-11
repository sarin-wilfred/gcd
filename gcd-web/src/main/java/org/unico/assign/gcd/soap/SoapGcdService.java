package org.unico.assign.gcd.soap;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

import org.unico.assign.gcd.dao.exception.GcdServiceException;

/**
 * 
 * @author Sarin
 *
 */

@WebService
public interface SoapGcdService {

	/**
	 * This method calculates the gcd of two numbers that are retrieved from the
	 * JMS queue. If the queue does not have two number it will return null
	 * 
	 * @return Integer
	 * @throws GeneralSecurityException
	 * @throws JMSException
	 * @throws GcdServiceException
	 */
	public Integer gcd() throws GeneralSecurityException, JMSException, GcdServiceException;

	/**
	 * This method returns the sum of all gcd's
	 * 
	 * @return int
	 * @throws GeneralSecurityException
	 */
	public int gcdSum() throws GeneralSecurityException;

	/**
	 * This method return all gcd's from DB
	 * 
	 * @return List<Integer>
	 * @throws GeneralSecurityException
	 */
	public List<Integer> gcdList() throws GeneralSecurityException;

}