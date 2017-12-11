package org.unico.assign.gcd.dao;

import java.util.Collection;

import org.unico.assign.gcd.dao.exception.GcdServiceException;
import org.unico.assign.gcd.domain.Gcd;

/**
 * @author Sarin
 * 
 */

public interface GcdDao {

	/**
	 * This method will get the sum of gcd's saved
	 * 
	 * @return Integer
	 */
	public Integer gcdSum();
	
	/**
	 * This method will add gcd's to DB
	 * 
	 * @param gcdValue
	 * @throws GcdServiceException
	 */
	public void saveGcd(Integer gcdValue) throws GcdServiceException;

	/**
	 * This method will list the gcd's from DB
	 * 
	 * @return Collection<Gcd>
	 */
	public Collection<Gcd> gcdList();

}
