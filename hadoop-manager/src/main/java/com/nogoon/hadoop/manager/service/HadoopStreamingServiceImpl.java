package com.nogoon.hadoop.manager.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.streaming.StreamJob;
import org.apache.hadoop.util.ToolRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nogoon.hadoop.manager.util.PathUtils;

@Service
public class HadoopStreamingServiceImpl implements HadoopStreamingService {
	
	@Autowired
	@Qualifier("local-hdfs-conf")
	Configuration hadoopConfig;

	@Override
	public int invokeStreamingJob(String mapperPath, String reducerPath,
			String inputPath, String outputPath) {
		int status = -1;
		StreamJob sj = new StreamJob();
		
		try {			 
		    status = ToolRunner.run(hadoopConfig, sj, new String[] {
		    		    "-file", mapperPath,
		                "-mapper", PathUtils.getFileName(mapperPath),
		                "-file", reducerPath,
		                "-reducer", PathUtils.getFileName(reducerPath), 
		                "-input", inputPath, 
		                "-output", outputPath });
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return status;

	}

}
