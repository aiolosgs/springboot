package com.codegenius.shop.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegenius.shop.core.configuration.SystemConfiguration;

@Controller
public class DefaultViewController{

	@Autowired
	private SystemConfiguration sysConfig;
//	@Value("${test.config}")
//	private String config;
//	
//	@Value("${security.config}")
//	private String security;
//	
//	@Value("${system.config}")
//	private String system;
	
	@RequestMapping("/")
    public String index() {
		System.out.println("go to index");
		return "index";
    }
	
	@RequestMapping("login")
	public String login(){
//		System.out.println(security);
//		System.out.println(system);
//		System.out.println(config);
		System.out.println(sysConfig.getName());
		System.out.println(sysConfig.getValue());
		System.out.println("go to login");
		return "login";
	}
}
