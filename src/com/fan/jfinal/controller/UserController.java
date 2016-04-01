package com.fan.jfinal.controller;

import java.util.List;

import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.Pano;
import com.fan.jfinal.model.User;

public class UserController extends BaseController {
	
	public void index() {
		
		User user = User.dao.findById(getPara());
		setAttr("user", user);
		
		List<Pano> panoList = Pano.dao.find("select * from pano where uid= ?", user.get("id"));
		setAttr("panoList", panoList);
		
		render("user.html");
	}
	
}
