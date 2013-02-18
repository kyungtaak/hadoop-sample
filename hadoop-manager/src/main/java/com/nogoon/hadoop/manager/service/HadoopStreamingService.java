package com.nogoon.hadoop.manager.service;

public interface HadoopStreamingService {
	int invokeStreamingJob(String mapperPath, String reducerPath, String inputPath, String outputPath);
}
