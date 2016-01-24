package com.abandikatla.ratelimiter.webapp;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
public class Server {

	 protected Server() throws Exception {
	        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	        sf.setResourceClasses(APITest.class);
	        sf.setResourceProvider(APITest.class, 
	            new SingletonResourceProvider(new APITest()));
	        sf.setAddress("http://localhost:9010/");
	        sf.create();
	 }
	 public static void main(String args[]) throws Exception {
	        new Server();
	        System.out.println("Server ready...");
	      /*  Thread.sleep(5 * 6000 * 1000);
	        System.out.println("Server exiting");
	        System.exit(0);*/
	    }

}
