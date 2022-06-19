package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beans.Article;
import com.beans.User;
import com.mappers.ArticleMapper;
import com.mappers.UserMapper;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	public UserMapper userMapper;
	
	@Resource
	public ArticleMapper articleMapper;

	@Override
	public User Login(String userNameOrEmail) {
		return userMapper.login(userNameOrEmail);
	}

	@Override
	public int updateLoginUser(User user) {
		return userMapper.updateLoginUser(user);
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = userMapper.getUserList();
		for(User user : userList) {
			user.setArticleCount(articleMapper.getArticleCountByUser(user.getUserId()));
		}
		return userList;
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public void deleteUser(Integer userId) {
		userMapper.deleteUser(userId);
		List<Article> articleList = articleMapper.getArticleByUser(userId);
		for(Article article : articleList) {
			articleMapper.deleteArticle(article.getArticleId());
		}
		
		
	}


	
}
