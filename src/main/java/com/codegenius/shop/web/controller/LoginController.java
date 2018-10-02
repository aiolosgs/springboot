package com.codegenius.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegenius.shop.core.configuration.SystemConfiguration;
import com.codegenius.shop.web.entity.User;
import com.codegenius.shop.web.mapper.UserDao;

@Controller
public class LoginController{

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
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
    public String index() {
		System.out.println("go to index");
		return "index";
    }
	
	@RequestMapping("login")
	public String login(){
		User user = userDao.getUserById("1");
		System.out.println(user.getLoginName());
		System.out.println(user.getDateOfBirth());
		System.out.println("go to login");
		return "login";
	}
}
