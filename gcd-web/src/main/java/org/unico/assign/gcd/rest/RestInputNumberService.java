package org.unico.assign.gcd.rest;

import java.util.List;

/**
 * 
 * @author Sarin
 *
 */

public interface RestInputNumberService {

	/**
	 * This method returns the list of all elements
	 * 
	 * @return List<Integer>
	 */
	public List<Integer> list();
	
	/**
	 * This method takes two parameters that are added to the JMS queue and
	 * returns the status
	 * 
	 * @param num1
	 * @param num2
	 * @return String
	 */
	public String push(int num1, int num2);

}