package com.umbertoloria.utils;

import com.umbertoloria.Main;

import java.io.IOException;
import java.io.InputStream;

public class FileLoader {

	public static String getContent(String filepath) {
		InputStream is = Main.class.getResourceAsStream("programs/" + filepath);
		StringBuilder content = new StringBuilder();
		int c;
		try {
			while ((c = is.read()) >= 0) {
				content.append((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
