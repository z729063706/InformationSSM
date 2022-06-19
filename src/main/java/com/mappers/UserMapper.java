package com.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beans.User;
@Component
public interface UserMapper {

	void addUser(User user);

	@Select("select * from user")
	List<User> getUserList();
	
	@Delete("delete from user where user_id= #{userId}")
	int deleteUser(int userId);
	
	@Update("update user set userPass=#{userPass}, userNickname=#{userNickname}, userEmail=#{userEmail} where userName=#{userName}")
	int updateUser(User user);

	@Update("update user set user_last_login_time=#{userLastLoginTime}, user_status=#{userStatus}, user_last_login_ip=#{userLastLoginIp} where user_name=#{userName}")
	int updateLoginUser(User user);
	
	@Select("select * from user where user_name=#{userName} or user_email=#{userName}")
	User login(@Param("userName") String userNameOrEmail);
	
	@Select("select count(*) from user")
	int userCount();
	
	@Select("select * from user where user_id=#{userId}")
	User getUserById(Integer userId);
	
	
}
