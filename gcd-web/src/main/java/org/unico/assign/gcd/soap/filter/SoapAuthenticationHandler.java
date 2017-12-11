package org.unico.assign.gcd.soap.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unico.assign.gcd.dao.UserDao;
import org.unico.assign.gcd.utils.AuthenticationUtil;

/**
 * 
 * @author Sarin
 *
 */
public class SoapAuthenticationHandler implements SOAPHandler<SOAPMessageContext> {

	Logger logger = LogManager.getLogger(SoapAuthenticationHandler.class);

	@Inject
	UserDao userDao;

	/**
	 * 
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		logger.debug("handleMessage- STARTS");
		Boolean outgoingMessage = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!outgoingMessage) {
			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();
				try {
					AuthenticationUtil.authenticate(userDao,
							(Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS));
				} catch (Exception e) {
					e.printStackTrace();
					generateSOAPErrorMessage(soapMsg, e.getMessage() + " - handler");
				}
				soapMsg.writeTo(System.out);
				logger.debug("handleMessage- ENDS");
			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}

		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
		logger.debug("close");
	}

	@Override
	public Set<QName> getHeaders() {
		logger.debug("getHeaders");
		return null;
	}

	/**
	 * This method generates the soap error message
	 * 
	 * @param msg
	 * @param reason
	 */
	private void generateSOAPErrorMessage(SOAPMessage msg, String reason) {
		try {
			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
		}
	}

}