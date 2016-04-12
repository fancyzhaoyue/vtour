package com.fan.access;


import com.fan.jfinal.model.User;
import com.jfinal.core.Controller;

public interface UserManager {
	
	public abstract User getUser(Controller c) throws Exception;

	public abstract boolean authenticate(User paramUser, Controller c) throws Exception;

	public abstract void initUser(User paramUser, Controller c) throws Exception;

}
