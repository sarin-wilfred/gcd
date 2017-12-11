package org.unico.gcd.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.unico.assign.gcd.dao.GcdDao;
import org.unico.assign.gcd.dao.UserDao;
import org.unico.assign.gcd.domain.Gcd;
import org.unico.assign.gcd.domain.User;
import org.unico.assign.gcd.jms.JMSMessageProcessor;
import org.unico.assign.gcd.soap.SoapGcdService;
import org.unico.gcd.SpringWithJNDIRunner;

/**
 * 
 * @author Sarin
 *
 */

@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class SoapGcdServiceImplTest {

	@Autowired
	JMSMessageProcessor jmsMessageProcessor;

	@Autowired
	ConnectionFactory jmsConnectionFactory;

	@Autowired
	Queue queue;

	@Autowired
	SoapGcdService soapGcdService;

	@Autowired
	GcdDao gcdDao;

	@Autowired
	UserDao userDao;

	@Before
	public void init() throws JMSException {

		doReturn(new User()).when(userDao).getUser(any(String.class), any(String.class));

		Collection<Gcd> gcdCollection = new ArrayList<Gcd>();
		gcdCollection.add(new Gcd(4));
		gcdCollection.add(new Gcd(8));
		gcdCollection.add(new Gcd(16));
		gcdCollection.add(new Gcd(20));

		doReturn(gcdCollection).when(gcdDao).gcdList();
		doAnswer(new Answer<Integer>() {
			private int count = 16;

			public Integer answer(InvocationOnMock invocation) {
				int temp = count;
				count = count + 4;
				return temp;
			}
		}).when(jmsMessageProcessor).receive(any(ConnectionFactory.class), any(Queue.class));
		doReturn(new Integer(100)).when(gcdDao).gcdSum();
	}


	@Test
	public void testGcdList() {
		List<Integer> list;
		try {
			list = soapGcdService.gcdList();
			assertEquals(new Integer(4), list.get(0));
			assertEquals(new Integer(8), list.get(1));
			assertEquals(new Integer(16), list.get(2));
			assertEquals(new Integer(20), list.get(3));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testGcdSum() {
		try {
			assertEquals(100, soapGcdService.gcdSum());
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGCD() {
		try {
			Integer gcd = soapGcdService.gcd();
			assertEquals(new Integer(4), gcd);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}


}
