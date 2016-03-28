package com.fan.jfinal.controller;


import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
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
		// 1将上传的文件存放到指定位置(与用户关联) /uid/pid/imgs/
		// 2操作数据库生成pano记录
		// 3调用makepano命令生成vtour(将处理后的图片拷贝到指定目录 /uid/pid/imgs/) 确认makepano命令如何定义输出文件的位置
		
		UploadFile panoFile = getFile();
		
		
		User user = (User)getSessionAttr(Constants.SESSION_USER);
		String uid = user.getStr("uid");
		
		
		KrpanoUtil.makepano(panoFile.getFile().getAbsolutePath());
		
		render("publishSuccess.html");
	}
	
}
