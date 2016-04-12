package com.fan.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User>{

	public static final User dao = new User();
	
	public User getUserByUniqueId(String uniqueId){
		User user =  User.dao.findById(uniqueId);
		if(user == null){
			user = User.dao.findFirst("select * from user where email = ?", uniqueId);
		}
		if(user == null){
			user = User.dao.findFirst("select * from user where mobile = ?", uniqueId);
		}
		return user;
	}
}
