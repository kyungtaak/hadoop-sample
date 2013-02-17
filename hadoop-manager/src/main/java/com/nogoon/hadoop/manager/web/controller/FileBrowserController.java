package com.nogoon.hadoop.manager.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nogoon.hadoop.manager.model.HadoopContent;
import com.nogoon.hadoop.manager.model.HadoopFile;
import com.nogoon.hadoop.manager.service.FileSystemService;

@Controller
@RequestMapping("/filesystem")
public class FileBrowserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileSystemService fileSystemService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public List<HadoopFile> getfileList(@RequestParam(value="path") String path) {
		log.info("call getFileList : " + path);
		List<HadoopFile> fileList = fileSystemService.getFileList(path);
		
		return fileList;
	}
	
	@RequestMapping(value="/contents", method = RequestMethod.GET)
	@ResponseBody
	public HadoopContent getfileContents(@RequestParam(value="path") String path) {
		log.info("call getfileContents : " + path);
		
		HadoopContent contents = fileSystemService.getfileContents(path, 0L);
		
		return contents;
	}
	
}
