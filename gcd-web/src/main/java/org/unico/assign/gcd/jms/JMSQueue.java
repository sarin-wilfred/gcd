package org.unico.assign.gcd.jms;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.unico.assign.gcd.constants.GcdConstants;

/**
 * 
 * @author Sarin
 *
 */

public class JMSQueue {

	@Resource(lookup = GcdConstants.ASSIGN_UNICO_JMS)
	private Queue queue;

	/**
	 * 
	 * @return queue
	 */
	public Queue getQueue() {
		return queue;
	}

	/**
	 * 
	 * @param queue
	 */
	public void setQueue(Queue queue) {
		this.queue = queue;
	}

}
