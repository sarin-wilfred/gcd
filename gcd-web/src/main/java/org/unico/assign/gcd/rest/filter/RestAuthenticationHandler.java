package org.unico.assign.gcd.rest.filter;

import java.security.GeneralSecurityException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.unico.assign.gcd.dao.UserDao;
import org.unico.assign.gcd.utils.AuthenticationUtil;

/**
 * 
 * @author Sarin
 *
 */
@PreMatching
@Priority(value = 3)
@Provider
public class RestAuthenticationHandler implements ContainerRequestFilter {

	@Inject
	UserDao userDao;

	Logger logger = LogManager.getLogger(RestAuthenticationHandler.class);

	public RestAuthenticationHandler() {
		logger.debug("ServerAuthenticationRequestFilter initialization");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) {
		ResponseBuilder responseBuilder = null;
		logger.debug("filter-STARTS");
		try {
			AuthenticationUtil.authenticate(userDao, requestContext.getHeaders());
			logger.debug("Authentication -PASSED");
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("Authentication - FAILED");
			responseBuilder = Response.serverError();
			Response response = responseBuilder.status(Status.UNAUTHORIZED).build();
			requestContext.abortWith(response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("Authentication - FAILED");
			responseBuilder = Response.serverError();
			Response response = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).build();
			requestContext.abortWith(response);
		}
		logger.debug("filter-ENDS");
	}
}
