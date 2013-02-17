package com.nogoon.hadoop.manager;

import java.io.IOException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.server.common.HdfsConstants;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] arg) throws IOException {
		
		System.setProperty("HADOOP_USER_NAME", "epsvc");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Configuration config = (Configuration) context.getBean("hdfs-conf");
				
		FileSystem hdfs = FileSystem.get(config);

//		boolean status = hdfs.mkdirs(new Path("/test"));
//		System.out.println(status);
		
//		FileStatus[] fileList = hdfs.listStatus(new Path("/"));
//		
//		for(FileStatus status : fileList)
//			System.out.println(ToStringBuilder.reflectionToString(status, ToStringStyle.MULTI_LINE_STYLE));
//		
//		fileList = hdfs.listStatus(new Path("/test"));
//		
//		for(FileStatus status : fileList)
//			System.out.println(ToStringBuilder.reflectionToString(status, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println("#####################################################");
		FSDataInputStream is = hdfs.open(new Path("/test/CHANGES.txt"));
		
		StringBuffer out = new StringBuffer();
	     byte[] b = new byte[4096];
	     for (int n; (n = is.read(b)) != -1;) {
	         out.append(new String(b, 0, n));
	     }
	     	     
	     System.out.println(out.toString());
	}
}	
