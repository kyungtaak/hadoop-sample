package com.nogoon.hadoop.manager.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	public static String makeFile(String contents, String dirName, String fileName) {
		
		String filePath = dirName + "/" + fileName;
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			out.write(contents);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		
		return filePath;
	}
}
