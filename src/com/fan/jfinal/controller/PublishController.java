package com.fan.jfinal.controller;


import java.io.File;
import java.util.Date;

import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.interceptor.LoginInterceptor;
import com.fan.jfinal.model.Pano;
import com.fan.jfinal.model.User;
import com.fan.util.KrpanoUtil;
import com.jfinal.aop.Before;
import com.jfinal.upload.UploadFile;
@Before(LoginInterceptor.class)
public class PublishController extends BaseController {
	
	
	public void index() {
		render("publish.html");
	}
	
	public void create() {
		
		User user = (User)getSessionAttr(Constants.SESSION_USER);
		String uid = user.getStr("uid");
		
		//将上传文件保存在用户独立目录下
		UploadFile panoFile = getFile("panoFile", uid);
		
		//新增全景图记录
		Pano pano = getModel(Pano.class);
		pano.set("uid",      user.get("id"));
		pano.set("name",     getPara("name"));
		pano.set("desc",     getPara("desc"));
		pano.set("thumb",    getPara("thumb"));
		pano.set("creative", getPara("creative"));
		pano.set("actor",    getPara("actor"));
		pano.set("original", getPara("original"));
		pano.set("video",    getPara("video"));
		pano.set("createDate", new Date());
		pano.save();
		
		//文件重命名并通过调用cmd处理全景图
		String path = panoFile.getUploadPath();
		String filename = pano.get("id") + getFileExt(panoFile.getFileName());
		File dest = new File(path, filename); 
		panoFile.getFile().renameTo(dest);
		KrpanoUtil.makepano(dest.getAbsolutePath());
		
		pano.set("thumb",    "/upload/"+ uid + "/" + pano.get("id") + "/imgs/thumb.jpg");
		pano.update();
		
		redirect("/publish/add");
		
	}
	
	public void add() {
		render("publishSuccess.html");
	}
	
	public String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }
}
