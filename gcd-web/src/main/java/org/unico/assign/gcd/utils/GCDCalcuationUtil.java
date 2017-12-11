package org.unico.assign.gcd.utils;

/**
 * 
 * @author Sarin
 *
 */

public class GCDCalcuationUtil {

	/**
	 * This method calculates the GCD of two numbers
	 * 
	 * @param num1
	 * @param num2
	 * @return result
	 */
	public static int getGcd(int num1, int num2) {
		if (num1 == 0) {
			return num2;
		}

		while (num2 != 0) {
			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num1;
	}
}
