package org.unico.gcd;

import javax.naming.NamingException;

import org.junit.runners.model.InitializationError;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unico.assign.gcd.constants.GcdConstants;

/**
 * 
 * @author Sarin
 *
 */

public class SpringWithJNDIRunner extends SpringJUnit4ClassRunner {

	public static final String WEB_SERVICE_CONTEXT = "webServiceContext";
	public static final String JMS_CONNECTION_FACTORY = "jmsConnectionFactory";
	public static final String TEST_CONFIG = "test-config.xml";

	public static boolean isJndiActive;

	public SpringWithJNDIRunner(Class<?> k) throws InitializationError, IllegalStateException, NamingException {
		super(k);
		synchronized (SpringWithJNDIRunner.class) {
			if (!isJndiActive) {
				ApplicationContext applicationContext = new ClassPathXmlApplicationContext(TEST_CONFIG);
				SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
				builder.bind(GcdConstants.DEFAULT_JMS_CONNECTION_FACTORY,
						applicationContext.getBean(JMS_CONNECTION_FACTORY));
				builder.bind(GcdConstants.ASSIGN_UNICO_JMS, applicationContext.getBean("queue"));
				builder.bind(WEB_SERVICE_CONTEXT, applicationContext.getBean(WEB_SERVICE_CONTEXT));
				builder.activate();
				isJndiActive = true;
			}
		}
	}
}