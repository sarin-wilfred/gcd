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
public class InputNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int num1;

	private int num2;

	/**
	 * 
	 */
	public InputNumber() {

	}

	/**
	 * 
	 * @param num1
	 * @param num2
	 */
	public InputNumber(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
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
	 * @return num1
	 */
	public int getNum1() {
		return num1;
	}

	/**
	 * 
	 * @param num1
	 */
	public void setNum1(int num1) {
		this.num1 = num1;
	}

	/**
	 * 
	 * @return num2
	 */
	public int getNum2() {
		return num2;
	}

	/**
	 * 
	 * @param num2
	 */
	public void setNum2(int num2) {
		this.num2 = num2;
	}

}