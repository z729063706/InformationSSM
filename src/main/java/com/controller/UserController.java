package com.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.beans.User;
import com.mappers.UserMapper;
import com.service.ArticleService;
import com.service.CommentService;
import com.service.UserService;

@Controller
public class UserController {
	@Resource
	UserService userService;
	
	@Resource
	ArticleService articleService;
	
	@Resource
	CommentService commentService;
	
	@RequestMapping(value="/login")
	public String Login_get() {
		return "forward:/view/login.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String Login(HttpServletRequest request,ModelMap m) {
		String userNameOrEmail=request.getParameter("username");
		String userPass=request.getParameter("userpass");
		User user=userService.Login(userNameOrEmail);
		if(user==null) {
			request.setAttribute("msg", "”√ªß√˚¥ÌŒÛ");
			return "forward:/view/login.jsp";
		}
		else if(!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "√‹¬Î¥ÌŒÛ");
			return "forward:/view/login.jsp";
		}
		else {
			request.getSession().setAttribute("session_user", user);
			user.setUserLastLoginTime(new Date());
			user.setUserLastLoginIp(request.getRemoteAddr());
			user.setUserStatus(1);
			userService.updateLoginUser(user);
			return  "forward:/index";
		}
	}
	
	@RequestMapping("/index")
	public String index(HttpSession session,ModelMap m) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("showArticles",articleService.getShowArticle());
		m.put("showComments",commentService.getShowComment());
		return  "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "forward:/login";
		
	}
	
	@RequestMapping("/User/userlist")
	public String userLIst(ModelMap m,HttpSession session) {
		m.put("loginuser",session.getAttribute("session_user"));
		m.put("userList", userService.getUserList());
		return "/user/user-list";
		
	}
	
	@RequestMapping(value="/User/add", method=RequestMethod.GET)
	public String addUser_get(HttpSession session,ModelMap m){
		m.put("loginuser",session.getAttribute("session_user"));
		return "/user/user-add";
	}
	
	@RequestMapping(value="/User/add", method=RequestMethod.POST)
	public String addUser_post(User user, MultipartFile photo) throws IOException {	
		user.setUserRegisterTime(new Date());
		user.setUserStatus(1);
		user.setUserPhoto(photo.getBytes());
		
		userService.addUser(user);	
		return "forward:/User/userlist";
	}
	
	@RequestMapping("/User/photo/{userId}")
	public void showPhoto(@PathVariable("userId") Integer userId,HttpServletResponse response) throws IOException {
		User user = userService.getUserById(userId);
		response.setContentType("image/jpg");
		ServletOutputStream out =response.getOutputStream();
		out.write(user.getUserPhoto());
		out.flush();
	}
	
	@RequestMapping("/User/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
		return "forward:/User/userlist";
	}
	
}
