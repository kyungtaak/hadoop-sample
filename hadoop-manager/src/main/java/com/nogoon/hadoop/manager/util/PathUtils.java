package com.nogoon.hadoop.manager.util;

import com.nogoon.hadoop.manager.constant.FileSystemType;
import com.nogoon.hadoop.manager.constant.Protocals;

public class PathUtils {
	public static String getFileName(String fullPath) {		
		return fullPath.substring(fullPath.lastIndexOf("/") + 1, fullPath.length());
	}
	
	public static String getPath(FileSystemType type, String fullPath) {
		return fullPath.substring(fullPath.indexOf("/", Protocals.WEBHDFS_PREFIX.length()), fullPath.length());
	}
}
