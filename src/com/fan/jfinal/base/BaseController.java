package com.fan.jfinal.base;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;

public class BaseController extends Controller {
	
	public void renderMsg() {
		renderMsg(0, "");
	}
	public void renderMsg(String message) {
		renderMsg(0, message);
	}
	public void renderMsg(int code, String message) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",    getAttr("data"));
		map.put("message", message);
		map.put("status",  code);
		renderJson(map);
	}
	
	public void renderError(int code, String message) {
		setAttr("status",  code);
		setAttr("message", message);
		renderJson();
	}
}
