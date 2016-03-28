package com.fan.jfinal.controller;

import java.io.IOException;

import com.fan.jfinal.base.BaseController;
import com.jfinal.upload.UploadFile;

public class PublishController extends BaseController {
	
	public void index() {
		render("publish.html");
	}
	public void create() {
		UploadFile panoFile = getFile();
		
		System.out.println(panoFile.getContentType());
		
		System.out.println(panoFile.getFileName());
		
		System.out.println(panoFile.getOriginalFileName());
		
		System.out.println(panoFile.getParameterName());
		
		System.out.println(panoFile.getUploadPath());
		
		render("publishSuccess.html");
	}
	
}
