package com.nogoon.hadoop.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.server.common.HdfsConstants;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nogoon.hadoop.manager.model.HadoopFile;

public class TestWebHDFSMain {
	public static void main(String[] arg) throws IOException {
		
		System.setProperty("HADOOP_USER_NAME", "epsvc");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//Configuration config = (Configuration) context.getBean("hdfs-conf");
				
		WebHdfsFileSystem webHdfs = (WebHdfsFileSystem) context.getBean("localweb-filesystem");;
		
//		boolean status = hdfs.mkdirs(new Path("/test"));
//		System.out.println(status);
		
		FileStatus[] fileList = webHdfs.listStatus(new Path("/"));
		
		for(FileStatus status : fileList) {
			System.out.println(ToStringBuilder.reflectionToString(status.getPath(), ToStringStyle.MULTI_LINE_STYLE));
			System.out.println(status.getPath().toUri().getPath());
		}
//		fileList = hdfs.listStatus(new Path("/test"));
//		
//		for(FileStatus status : fileList)
//			System.out.println(ToStringBuilder.reflectionToString(status, ToStringStyle.MULTI_LINE_STYLE));
		
		List<HadoopFile> resultList = new ArrayList<HadoopFile>();
		
		
		for(FileStatus file : fileList) {
			resultList.add(new HadoopFile(file));
		}
		
		for(HadoopFile file : resultList) {
			System.out.println(ToStringBuilder.reflectionToString(file, ToStringStyle.MULTI_LINE_STYLE));
		}
	}
}	

	