/**
 * 
 */
package org.unico.gcd.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.unico.assign.gcd.dao.InputNumberDao;
import org.unico.assign.gcd.domain.InputNumber;
import org.unico.assign.gcd.rest.RestInputNumberService;
import org.unico.gcd.SpringWithJNDIRunner;

/**
 * @author Sarin
 *
 */

@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class RestInputNumberServiceImplTest {

	@Autowired
	InputNumberDao inputNumberDao;
	
	@Autowired
	RestInputNumberService restInputNumberService;

	@Before
	public void setUpBeforeClass() throws Exception {
		Collection<InputNumber> inputNumberList = new ArrayList<InputNumber>();
		inputNumberList.add(new InputNumber(10, 20));
		inputNumberList.add(new InputNumber(30, 40));
		inputNumberList.add(new InputNumber(50, 60));
		inputNumberList.add(new InputNumber(70, 80));
		inputNumberList.add(new InputNumber(90, 100));
		doReturn(inputNumberList).when(inputNumberDao).list();
	}

	@Test
	public void testGetList() {
		List<Integer> inputNumberList = restInputNumberService.list();
		assertEquals(new Integer(10), inputNumberList.get(0));
		assertEquals(new Integer(20), inputNumberList.get(1));
		assertEquals(new Integer(30), inputNumberList.get(2));
		assertEquals(new Integer(40), inputNumberList.get(3));
		assertEquals(new Integer(50), inputNumberList.get(4));
		assertEquals(new Integer(60), inputNumberList.get(5));
		assertEquals(new Integer(70), inputNumberList.get(6));
		assertEquals(new Integer(80), inputNumberList.get(7));
		assertEquals(new Integer(90), inputNumberList.get(8));
		assertEquals(new Integer(100), inputNumberList.get(9));

	}
	
	@Test
	public void testPush() {
		assertEquals("Recieved parameters :20 and 16", restInputNumberService.push(20, 16));
	}

}
