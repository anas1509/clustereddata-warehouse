package com.FxDeals.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class keepAlive {

	/*
	 * This is a test controller that could be used to monitor the application availability.
	 * In practice we could use Spring actuator, that will generate other monitoring end points.
	 */
	@RequestMapping(value = "/keep-alive", method = RequestMethod.POST)
	public String health() {
		return "good";
	}

}
