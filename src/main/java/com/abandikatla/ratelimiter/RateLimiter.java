package com.abandikatla.ratelimiter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

/**
 * @author Amulya Bandikatla
 *
 */
public class RateLimiter {
	private final Integer frequency;
	private final Integer duration;
	private Map<String, List<Integer>> clientApiCalls;

	/**
	 * @param frequency
	 * @param duration
	 */
	public RateLimiter(Integer frequency, Integer duration) {
		this.frequency = frequency;
		this.duration = duration;
		clientApiCalls = new HashMap<String, List<Integer>>();
	}
	
	/**
	 * @return true, if in the past duration, there < frequency success calls. 
	 * Else, it returns false.
	 */

	public boolean check() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
	    HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		// get the client IP.
		String clientIP = Util.getClientIP(request);
		System.out.println("clientIP " + clientIP);
		// Get the current time.
		Integer currentTime = Util.getCurrentTimeSecs();

		if (clientApiCalls.containsKey(clientIP)) {
			List<Integer> calls = clientApiCalls.get(clientIP);
			System.out.println("Freq " + frequency + " Sofar... " + calls.size());
			if (calls.size() < frequency) {
				calls.add(currentTime);
			} else {
				Integer oldestCallTime = calls.get(0);
				System.out.println("oldestCallTime "+ oldestCallTime + "currentTime " + currentTime + " duration " + duration + " diff " + (currentTime - oldestCallTime) );
				// Check the number of calls in the past duration
				if (currentTime - oldestCallTime <= duration) {
					System.out.println("false");
					return false;
				} else {
					calls.remove(0);
					calls.add(currentTime);
				}
			}
		} else if (frequency > 0) {
			List<Integer> calls = new ArrayList<Integer>();
			calls.add(currentTime);
			clientApiCalls.put(clientIP, calls);
		} else {
			System.out.println("false");
			return false;
		}
		System.out.println("true");
		return true;
	}

}
