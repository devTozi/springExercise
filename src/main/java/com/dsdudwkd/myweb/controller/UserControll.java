package com.dsdudwkd.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControll {
	
	@RequestMapping("/login")
	public String user() {
		
		return "user/login";
	}
	
	@RequestMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	
}
