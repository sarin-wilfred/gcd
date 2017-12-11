package org.unico.assign.gcd.soap;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unico.assign.gcd.constants.GcdConstants;
import org.unico.assign.gcd.dao.GcdDao;
import org.unico.assign.gcd.dao.UserDao;
import org.unico.assign.gcd.dao.exception.GcdServiceException;
import org.unico.assign.gcd.domain.Gcd;
import org.unico.assign.gcd.jms.JMSMessageProcessor;
import org.unico.assign.gcd.jms.JMSQueue;
import org.unico.assign.gcd.utils.GCDCalcuationUtil;

/**
 * 
 * @author Sarin
 *
 */

@WebService(name = "GcdService", serviceName = "GcdService", portName = "GcdServicePort", targetNamespace = "http://soap.services.unico.com/")
@HandlerChain(file = "/soap-handlers.xml")
public class SoapGcdServiceImpl implements SoapGcdService {

	@Resource(lookup = GcdConstants.DEFAULT_JMS_CONNECTION_FACTORY)
	ConnectionFactory jmsConnectionFactory;

	@Inject
	JMSQueue queues;

	@Inject
	private JMSMessageProcessor jmsMessageProcessor;

	@Resource
	WebServiceContext webServiceContext;

	@Inject
	GcdDao gcdDao;

	@Inject
	UserDao userDao;

	Logger logger = LogManager.getLogger(SoapGcdServiceImpl.class);

	/**
	 * No arguments Constructor
	 */
	public SoapGcdServiceImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.unico.gcd.soap.GCDSoapService#gcd()
	 */
	@WebMethod
	public synchronized Integer gcd() throws GeneralSecurityException, JMSException, GcdServiceException {
		logger.info("gcd-STARTS");
		Integer num1 = jmsMessageProcessor.receive(jmsConnectionFactory, queues.getQueue());
		Integer num2 = jmsMessageProcessor.receive(jmsConnectionFactory, queues.getQueue());
		logger.debug("GCD for num1:" + num1 + " num2:" + num2);
		Integer gcdValue = GCDCalcuationUtil.getGcd(num1, num2);
		
		gcdDao.saveGcd(gcdValue);
		logger.info("gcd-STARTS");
		return gcdValue;
	}

	/**
	 * This method returns the sum of all gcd's
	 * 
	 * @return int
	 * @throws GeneralSecurityException
	 */
	@WebMethod
	public synchronized int gcdSum() throws GeneralSecurityException {
		return gcdDao.gcdSum();
	}
	
	/**
	 * This method return all gcd's from DB
	 * 
	 * @return List<Integer>
	 * @throws GeneralSecurityException
	 */
	@WebMethod
	public synchronized List<Integer> gcdList() throws GeneralSecurityException {
		List<Integer> gcds = new ArrayList<>();
		Collection<Gcd> gcdList = gcdDao.gcdList();
		for (Gcd gcd : gcdList) {
			gcds.add(gcd.getCalculatedGcd());
		}
		return gcds;
	}

}
