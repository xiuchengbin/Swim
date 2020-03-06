package com.swim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping({"/test","/test.html"})
	public String a(){
		return "test";
	}
	
}
