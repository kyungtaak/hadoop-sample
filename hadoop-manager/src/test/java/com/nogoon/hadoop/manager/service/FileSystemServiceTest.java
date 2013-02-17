package com.nogoon.hadoop.manager.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.nogoon.hadoop.manager.config.HadoopServiceTestConfig;
import com.nogoon.hadoop.manager.model.HadoopFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HadoopServiceTestConfig.class, loader=AnnotationConfigContextLoader.class)
public class FileSystemServiceTest {
	
	@Autowired
	FileSystemService fileSystemService;
	
	@Test
	public final void whenSpringContextIsInstantiated_thenNoExceptions() {
	}

	@Test
	public void getFileList() {
		List<HadoopFile> fileList = fileSystemService.getFileList("/");
		//assertThat(fileList.size(), is(2));
		printFileList(fileList);
		
		
	}
	
	public void printFileList(List<HadoopFile> fileList) {
		for(HadoopFile file : fileList) {
			System.out.println(file);
		}
	}
}
