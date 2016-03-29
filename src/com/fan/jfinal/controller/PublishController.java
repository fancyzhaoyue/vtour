package com.fan.jfinal.controller;


import java.io.File;

import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.Pano;
import com.fan.jfinal.model.User;
import com.fan.util.KrpanoUtil;
import com.jfinal.upload.UploadFile;

public class PublishController extends BaseController {
	
	public void index() {
		render("publish.html");
	}
	
	public void add(){
		
	}
	
	public void create() {
		
		User user = (User)getSessionAttr(Constants.SESSION_USER);
		String uid = user.getStr("uid");
		
		//将上传文件保存在用户独立目录下
		UploadFile panoFile = getFile("panoFile", uid);
		
		//新增全景图记录
		Pano pano = getModel(Pano.class);
		pano.set("name", getPara("name"));
		pano.set("uid", user.get("id"));
		pano.save();
		
		//文件重命名并通过调用cmd处理全景图
		String path = panoFile.getUploadPath();
		String filename = pano.get("id") + getFileExt(panoFile.getFileName());
		File dest = new File(path, filename); 
		panoFile.getFile().renameTo(dest);
		KrpanoUtil.makepano(dest.getAbsolutePath());
		
		render("publishSuccess.html");
	}
	
	public String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }
}
