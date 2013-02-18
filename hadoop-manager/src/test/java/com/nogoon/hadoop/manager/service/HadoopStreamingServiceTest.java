package com.nogoon.hadoop.manager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.nogoon.hadoop.manager.config.HadoopServiceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HadoopServiceTestConfig.class, loader=AnnotationConfigContextLoader.class)
public class HadoopStreamingServiceTest {
	
	@Autowired
	HadoopStreamingService hsService;
	
	@Test
	public final void whenSpringContextIsInstantiated_thenNoExceptions() {
	}
	
	@Test
	public void invokeStreamingJob() {
		String mapperPath = "/Volumes/data/Temp/map.py";
		String reducerPath = "/Volumes/data/Temp/reducer.py";
		String inputPath = "/user/kyungtaak/zbp07detail_min.txt";
		String outputPath = "/user/kyungtaak/out3";
		
		int status = hsService.invokeStreamingJob(mapperPath, reducerPath, inputPath, outputPath);
		
		System.out.println("STATUS :: " + status);
		
	}
}
