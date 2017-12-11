package org.unico.assign.gcd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Sarin
 *
 */

@Entity
public class Gcd  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int calculatedGcd;

	/**
	 * 
	 */
	public Gcd() {
		
	}
	
	/**
	 * 
	 * @param calculatedGcd
	 */
	public Gcd(int calculatedGcd) {
		this.calculatedGcd = calculatedGcd;
	}
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return calculatedGcd
	 */
	public int getCalculatedGcd() {
		return calculatedGcd;
	}

	/**
	 * 
	 * @param calculatedGcd
	 */
	public void setCalculatedGcd(int calculatedGcd) {
		this.calculatedGcd = calculatedGcd;
	}
}