package com.fan.jfinal.controller;

import java.util.List;

import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.Pano;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class TourController extends BaseController {
	
	public void index() {
		
		String sql = "select a.id,a.name,b.uid from pano a, user b where a.uid=b.id and a.id= ?";
		Record pano = Db.findFirst(sql, getPara(0));
		setAttr("pano", pano);
		
		render("vtour.html");
	}
	public void getVt() {
		Page<Record> panoPage = Db.paginate(getParaToInt(), 12, "select a.*,b.nickName","from pano a ,user b where a.uid=b.uid order by id desc");
		setAttr("data", panoPage);
		renderMsg();
	}
	
	public void getVtByUid() {
		Page<Record> panoPage = Db.paginate(getParaToInt(), 12, "select * ","from pano where uid= ?",getPara("uid"));
		setAttr("data", panoPage);
		renderMsg();
	}
	
	public void getVtRank(){
		
		List<Pano> panoList = Pano.dao.find("select * from pano limit ?", getParaToInt());
		setAttr("data", panoList);
		renderMsg();
		
	}
}
