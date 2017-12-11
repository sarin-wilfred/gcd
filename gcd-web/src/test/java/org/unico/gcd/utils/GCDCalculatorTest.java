package org.unico.gcd.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.unico.assign.gcd.utils.GCDCalcuationUtil;

/**
 * 
 * @author Sarin
 *
 */

public class GCDCalculatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetGcd() {
		assertEquals(GCDCalcuationUtil.getGcd(5, 10), 5);
		assertEquals(GCDCalcuationUtil.getGcd(20, 16), 4);
		assertEquals(GCDCalcuationUtil.getGcd(10, 20), 10);
		assertEquals(GCDCalcuationUtil.getGcd(200, 100), 100);
		assertEquals(GCDCalcuationUtil.getGcd(10, 8), 2);
		assertEquals(GCDCalcuationUtil.getGcd(100, 80), 20);
		assertEquals(GCDCalcuationUtil.getGcd(25, 50), 25);
		assertEquals(GCDCalcuationUtil.getGcd(25, 75), 25);
		
	}

}
