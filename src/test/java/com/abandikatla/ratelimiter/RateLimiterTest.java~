package com.abandikatla.ratelimiter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.junit.Test;

public class RateLimiterTest {
	
	@Test
	public void runTest(){
		Integer startTimeMillis = 0;
		System.out.println("Testing the api which uses rate limiter ");
		for(int i=0; i<10; i++){
			URL url;
			try {
				//Invoke the api
				url = new URL("http://localhost:9010/webapp/firstapi");
				InputStream in = url.openStream();
				if(startTimeMillis == 0){
					startTimeMillis = (int) (System.currentTimeMillis()/1000);
				}
				Integer diff = (int)(System.currentTimeMillis()/1000) - startTimeMillis;
				String response  = getStringFromInputStream(in);
				System.out.println("Time from start in Seconds : " + diff  + ", Response : " + response );
				
				//In these two cases, there are already 5 successful calls (true response) in the last 20 secs.
				if(i== 5 || i==6){
					assertEquals("false",response);
				}else{
					assertEquals("true", response);
				}
				
				//Sleep for i seconds
				Thread.sleep(i*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	 private static String getStringFromInputStream(InputStream in) throws Exception {
	        CachedOutputStream bos = new CachedOutputStream();
	        IOUtils.copy(in, bos);
	        in.close();
	        bos.close();
	        return bos.getOut().toString();
	    }

}
