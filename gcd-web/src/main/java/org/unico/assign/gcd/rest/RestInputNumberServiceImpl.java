package org.unico.assign.gcd.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unico.assign.gcd.constants.GcdConstants;
import org.unico.assign.gcd.dao.InputNumberDao;
import org.unico.assign.gcd.dao.UserDao;
import org.unico.assign.gcd.domain.InputNumber;
import org.unico.assign.gcd.jms.JMSMessageProcessor;
import org.unico.assign.gcd.jms.JMSQueue;

/**
 * 
 * @author Sarin
 *
 */

@Path("/gcd")
public class RestInputNumberServiceImpl implements RestInputNumberService {

	@Resource(lookup = GcdConstants.DEFAULT_JMS_CONNECTION_FACTORY)
	ConnectionFactory jmsConnectionFactory;

	@Inject
	JMSQueue jmsQueue;

	@Inject
	JMSMessageProcessor jmsMessageProcessor;

	@Inject
	InputNumberDao inputNumberDao;

	@Inject
	UserDao userDao;

	Logger logger = LogManager.getLogger(RestInputNumberServiceImpl.class);

	public RestInputNumberServiceImpl() {
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> list() {
		logger.info("list-STARTS");
		List<Integer> inputNumbers = new ArrayList<>();
		Collection<InputNumber> inputNumberCol = inputNumberDao.list();
		for (InputNumber params : inputNumberCol) {
			inputNumbers.add(params.getNum1());
			inputNumbers.add(params.getNum2());
		}
		logger.info("inputNumbers: " + inputNumbers);
		logger.info("list-ENDS");
		return inputNumbers;
	}

	@GET
	@Path("/push/{num1}/{num2}")
	@Produces("text/plain")
	public String push(@PathParam("num1") int num1, @PathParam("num2") int num2) {
		logger.info("push-STARTS");
		logger.debug("num1 = " + num1 + " & num2 = " + num2);
		try {
			jmsMessageProcessor.send(jmsConnectionFactory, jmsQueue.getQueue(), String.valueOf(num1));
			jmsMessageProcessor.send(jmsConnectionFactory, jmsQueue.getQueue(), String.valueOf(num2));
			inputNumberDao.push(num1, num2);
		} catch (Exception exception) {
			exception.printStackTrace();
			return "Not able to send input to JMS queue: " + exception.getMessage();
		}
		logger.info("push-ENDS");
		logger.info("Num1 = " + num1 + " ; Num2 = " + num2);
		return "Recieved parameters :" + num1 + " and " + num2;
	}

}
