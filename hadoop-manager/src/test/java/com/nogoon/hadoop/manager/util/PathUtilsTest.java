package com.nogoon.hadoop.manager.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.nogoon.hadoop.manager.constant.FileSystemType;
import com.nogoon.hadoop.manager.constant.Protocals;

public class PathUtilsTest {
	
	@Test
	public void getFileName() {
		
		String rawPath = "webhdfs://203.254.214.153:8010/user/abc.txt";
		
		String name = PathUtils.getFileName(rawPath);
				
		assertThat("abc.txt", is(name));
	}
	
	@Test
	public void getPath() {
		String rawPath = "webhdfs://203.254.214.153:8010/user/abc.txt";
	
		
		String path = PathUtils.getPath(FileSystemType.WEBHDFS, rawPath);
		
		assertThat("/user/abc.txt", is(path));
	}	

}
