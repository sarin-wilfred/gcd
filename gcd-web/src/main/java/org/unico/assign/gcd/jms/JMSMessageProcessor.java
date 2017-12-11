/**
 * 
 */
package org.unico.assign.gcd.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sarin
 *
 */
public class JMSMessageProcessor {

	Logger logger = LogManager.getLogger(JMSMessageProcessor.class);

	/**
	 * This method sends params to JMS Queue
	 * 
	 * @param connectionFactory
	 * @param queue
	 * @return Integer
	 * @throws JMSException
	 */
	public Integer receive(ConnectionFactory connectionFactory, Queue queue) throws JMSException {
		JMSContext jmsContext = null;
		try {
			jmsContext = connectionFactory.createContext();
			jmsContext.start();
			String param = jmsContext.createConsumer(queue).receiveBody(String.class, 1000);
			logger.debug("Params received from Queue = " + param);
			if (param != null) {
				return Integer.valueOf(param);
			}
			throw new JMSException("Queue is empty");
		} finally {
			if (jmsContext != null) {
				jmsContext.close();
			}
		}
	}

	/**
	 * This method sends params to JMS Queue
	 * 
	 * @param connectionFactory
	 * @param queue
	 * @param param
	 */
	public void send(ConnectionFactory connectionFactory, Queue queue, String param) {
		JMSContext jmsContext = null;
		try {
			jmsContext = connectionFactory.createContext();
			jmsContext.start();
			jmsContext.createProducer().send(queue, param);
		} finally {
			if (jmsContext != null) {
				jmsContext.close();
			}
		}
	}

}
