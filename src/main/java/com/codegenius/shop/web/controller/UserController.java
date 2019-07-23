package com.codegenius.shop.web.controller;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegenius.shop.web.entity.User;
import com.codegenius.shop.web.mapper.UserDao;
import com.codegenius.shop.web.utils.DigestUtils;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDao dao;
	
	@RequestMapping("save")
	public String save(@RequestBody User user){
		if(StringUtils.isNotEmpty(user.getId())){
			//update
		}else{
			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			String salt = UUID.randomUUID().toString();
			String encryptedPassword = DigestUtils.sha256DigestWithSalt(user.getPassword(),salt);
			user.setPassword(encryptedPassword);
			user.setSalt(salt);
			dao.save(user);
		}
		return "";
	}
}
