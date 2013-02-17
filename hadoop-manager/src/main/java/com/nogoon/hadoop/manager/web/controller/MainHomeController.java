package com.nogoon.hadoop.manager.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainHomeController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
		log.debug("TEST!");
		return "test";
	}
	
	@RequestMapping(value="/test/thymeleaf", method = RequestMethod.GET)
	public String thymeleafView() {
//		ModelAndView mav = new ModelAndView("mysingle-home");				
//		return mav;
		return "thymeleaf/test-thymeleaf";
	}
	
}
