package com.fan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jfinal.kit.PropKit;

public class KrpanoUtil {
	
	public static void makepano(String file) {
		
		String cmd = PropKit.get("krpanotools.makepano") + file;
		System.out.println(cmd);
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
