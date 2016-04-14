package com.fan.common.util;

import java.io.IOException;

import com.fan.common.StreamGobbler;
import com.jfinal.kit.PropKit;

public class KrpanoUtil {
	
	public static void makepano(String file) {
		
		String cmd = PropKit.get("krpanotools.makepano") + file;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");              
		    errorGobbler.start();  
		    StreamGobbler outGobbler = new StreamGobbler(process.getInputStream(), "STDOUT");  
		    outGobbler.start();   
			process.waitFor(); 
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
