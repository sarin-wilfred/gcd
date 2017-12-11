package org.unico.assign.gcd.dao;

import java.util.Collection;

import org.unico.assign.gcd.dao.exception.GcdServiceException;
import org.unico.assign.gcd.domain.InputNumber;

/**
 * @author Sarin
 * 
 */

public interface InputNumberDao {

	/**
	 * This method will will be add 2 parameters to a the JMS queue.
	 * 
	 * @param num1
	 * @param num2
	 * @throws GcdServiceException
	 */
	public void push(Integer num1, Integer num2) throws GcdServiceException;

	/**
	 * This method will return a list of all the elements ever added to the
	 * queue from a database
	 * 
	 * @return Collection<InputNumber>
	 */
	public Collection<InputNumber> list();

}
