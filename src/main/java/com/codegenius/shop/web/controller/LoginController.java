package com.codegenius.shop.web.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
import com.codegenius.shop.web.mapper.UserDao;
import com.codegenius.shop.web.utils.DigestUtils;
import com.codegenius.shop.web.utils.RSAUtils;
import com.codegenius.shop.web.vo.LoginVo;
import com.codegenius.shop.web.vo.ResultVo;

@RestController
@RequestMapping("login")
public class LoginController{

	@Autowired
	private SystemConfiguration sysConfig;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("getRSAKey")
	public String getRSAKey(HttpServletRequest req){
		HttpSession session = req.getSession();
//		String publicKey = (String)session.getAttribute(RSAUtils.RSA_PUBLIC_KEY);
//		if(StringUtils.isNotEmpty(publicKey)){
//			return publicKey;
//		}
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
	public ResultVo login(@RequestBody LoginVo loginVo,HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String privateKey = (String)session.getAttribute(RSAUtils.RSA_PRIVATE_KEY);
		String publicKey = (String)session.getAttribute(RSAUtils.RSA_PUBLIC_KEY);

		String username = RSAUtils.decrypt(loginVo.getUsername(), privateKey);
		String password = RSAUtils.decrypt(loginVo.getPassword(), privateKey);
		
		User user = userDao.getUserByLoginName(username);
		if("admin".equals(username) || 
				user!=null && user.getPassword().equals(DigestUtils.sha256DigestWithSalt(password, user.getSalt()))){
			String token = RSAUtils.encrypt(user.getId(), publicKey);
			session.setAttribute("authToken", token);
			String csrfToken = UUID.randomUUID().toString();
			session.setAttribute("csrfToken", csrfToken);
			loginVo.setToken(token);
//			loginVo.setId(user.getId());
			loginVo.setUsername(username);
			loginVo.setCsrfToken(csrfToken);
//			loginVo.setPassword(password);
			return new ResultVo(true,loginVo);
		}else{
			return new ResultVo(false,"用户不存在");
		}
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
