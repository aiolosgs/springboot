package com.codegenius.shop.web.mapper;

import com.codegenius.shop.web.entity.User;

public interface UserDao {

	User getUserById(String id);
	
	User getUserByLoginName(String loginName);
	
	void save(User user);
}
