package com.codegenius.shop.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultViewController{

	@RequestMapping("/")
    public String index() {
		return "index";
    }
}
