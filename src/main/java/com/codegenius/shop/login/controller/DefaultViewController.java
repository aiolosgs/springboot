package com.codegenius.shop.login.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultViewController{

	@Value("${test.config}")
	private String config;
	
	@Value("${security.config}")
	private String security;
	
	@Value("${system.config}")
	private String system;
	
	@RequestMapping("/")
    public String index() {
		System.out.println("go to index");
		return "index";
    }
	
	@RequestMapping("login")
	public String login(){
		System.out.println(security);
		System.out.println(system);
		System.out.println(config);
		System.out.println("go to login");
		return "login";
	}
}
