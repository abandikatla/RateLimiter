package com.abandikatla.ratelimiter.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.junit.Test;

import com.abandikatla.ratelimiter.RateLimiter;

@Path("/webapp/")
@Produces("text/json")
public class APITest {
	
	private RateLimiter rateLimiter;
	
	public APITest() {
		rateLimiter = new RateLimiter(5,20); //5 calls in 10 secs
	}
	
	@GET
    @Path("/firstapi")
	public Boolean runTest1(){
		boolean isCallAllowed = rateLimiter.check();
		if(isCallAllowed){
			//TODO : Business logic here
		}
		return isCallAllowed;
	}

}
