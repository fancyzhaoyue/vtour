package test;

import java.io.File;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		JSONObject json = JSONObject.parseObject("access_token=D2EE7FB49278828CA409C8A77A2417D8&expires_in=7776000&refresh_token=8776541BBA1A629E3FD4E58895A8693A");
		System.out.println(json.toString());
	}
}
