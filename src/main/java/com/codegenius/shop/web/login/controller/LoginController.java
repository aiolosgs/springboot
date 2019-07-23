package com.codegenius.shop.web.login.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegenius.shop.core.configuration.SystemConfiguration;
import com.codegenius.shop.web.entity.User;
import com.codegenius.shop.web.login.vo.LoginVo;
import com.codegenius.shop.web.mapper.UserDao;
import com.codegenius.shop.web.utils.RSAUtils;

@RestController
@RequestMapping("login")
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
	
	@PostMapping("getRSAKey")
	public String getRSAKey(HttpServletRequest req){
		HttpSession session = req.getSession();
		String publicKey = (String)session.getAttribute(RSAUtils.RSA_PUBLIC_KEY);
		if(StringUtils.isNotEmpty(publicKey)){
			return publicKey;
		}
		Map<String,String> keyPair = RSAUtils.generateKey();
		if(keyPair != null){
			session.setAttribute(RSAUtils.RSA_PUBLIC_KEY, keyPair.get(RSAUtils.RSA_PUBLIC_KEY));
			session.setAttribute(RSAUtils.RSA_PRIVATE_KEY, keyPair.get(RSAUtils.RSA_PRIVATE_KEY));
			return keyPair.get(RSAUtils.RSA_PUBLIC_KEY);
		}
		return "";
	}
	
	@RequestMapping("/")
    public String index() {
		System.out.println("go to index");
		return "index";
    }
	
	@RequestMapping("doLogin")
	public String login(@RequestBody LoginVo loginVo,HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String privateKey = (String)session.getAttribute(RSAUtils.RSA_PRIVATE_KEY);
		try {
			String username = RSAUtils.decrypt(loginVo.getUsername(), privateKey);
			String password = RSAUtils.decrypt(loginVo.getPassword(), privateKey);
			if(StringUtils.isNotEmpty(username) && username.equals("admin")){
				return "success";
			}else{
				return "fail";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	
	@RequestMapping("vueServer")
	public Object test(){
		User user = new User();
		user.setId("1");
		user.setName("黄思宇");
		user.setLoginName("huangsiyu");
		user.setDateOfBirth(new Date());
		return user;
	}
}
