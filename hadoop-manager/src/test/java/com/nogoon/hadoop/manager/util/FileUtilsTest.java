package com.nogoon.hadoop.manager.util;

import junit.framework.Assert;

import org.junit.Test;

public class FileUtilsTest {
	
	@Test
	public void makeFile() {
		String dirName = "/Volumes/data/Temp";
		String fileName = "test.txt";
		String contents = "abc\nabc";
		
		String result = FileUtils.makeFile(contents, dirName, fileName);
		
		Assert.assertEquals(dirName + "/" + fileName, result);
		
	}
}
