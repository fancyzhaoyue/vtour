package com.fan.jfinal.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) {
		
		try {
			String commands = "ls -l";
			String cmd="/Users/fanzhaoyue/Desktop/krpano-1.19-pr3/krpanotools makepano -config=/Users/fanzhaoyue/Desktop/krpano-1.19-pr3/templates/vtour-multires.config /Users/fanzhaoyue/Desktop/panos/20160327.jpg";
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
