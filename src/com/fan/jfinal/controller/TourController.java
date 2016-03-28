package com.fan.jfinal.controller;

import java.util.HashMap;
import java.util.Map;

import com.fan.jfinal.base.BaseController;
import com.jfinal.upload.UploadFile;

public class TourController extends BaseController {
	
	@SuppressWarnings("rawtypes")
	public void index() {
		String pid = getPara(0);
		
		Map pano = new HashMap();
		pano.put("name", "test");
		pano.put("pid", "123");
		setAttr("pano", pano);
		
		render("show.html");
	}
	public void create() {
		UploadFile panoFile = getFile();
		
		System.out.println(panoFile);
		
		render("publishSuccess.html");
	}
	
}
