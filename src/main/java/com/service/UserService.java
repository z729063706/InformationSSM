package com.service;

import java.util.List;

import com.beans.User;

public interface UserService {
	User Login(String userNameOrEmail);
	
	int updateLoginUser(User user);
	
	List<User> getUserList();

	void addUser(User user);
	
	User getUserById(Integer userId);
	
	void deleteUser(Integer userId);
	
}
