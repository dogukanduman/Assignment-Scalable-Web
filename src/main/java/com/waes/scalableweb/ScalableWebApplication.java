package com.waes.scalableweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application has developed for <strong>WAES software developer</strong> assigment
 * Provide 2 http endpoints that accepts <strong>JSON base64 encoded binaryr</strong> data on both endpoints
 *		 <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
 * The provided data needs to be diff-ed and the results shall be available on a third end point
 *        <host>/v1/diff/<ID>
 *
 * @author ttdduman
 */

@SpringBootApplication
public class ScalableWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalableWebApplication.class, args);
	}
}
