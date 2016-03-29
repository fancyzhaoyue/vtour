package com.fan.jfinal.controller;

import com.fan.jfinal.base.BaseController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TourController extends BaseController {
	
	public void index() {
		
		String sql = "select a.id,a.name,b.uid from pano a, user b where a.uid=b.id and a.id= ?";
		Record pano = Db.findFirst(sql, getPara(0));
		setAttr("pano", pano);
		
		render("vtour.html");
	}
	
}
