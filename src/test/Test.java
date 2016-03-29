package test;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		File file = new File("D:\\home\\237572.jpg");
		file.renameTo(new File("D:\\home\\237572-1.jpg"));
	}
}
