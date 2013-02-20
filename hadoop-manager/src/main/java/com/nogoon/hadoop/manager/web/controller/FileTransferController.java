package com.nogoon.hadoop.manager.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nogoon.hadoop.manager.model.UplaodFileMeta;

@Controller
@RequestMapping("/file")
public class FileTransferController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@RequestMapping(value="/message", method=RequestMethod.POST)
//	public @ResponseBody StatusResponse message(@RequestBody Message message) {
//		// Do custom steps here
//		// i.e. Persist the message to the database
//		log.debug("Service processing...done");
//		
//		return new StatusResponse(true, "Message received");
//	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody List<UplaodFileMeta> upload(
			@RequestParam("file") MultipartFile file) {
		// Do custom steps here
		// i.e. Save the file to a temporary location or database
		log.debug("Writing file to disk...done");
		
		List<UplaodFileMeta> uploadedFiles = new ArrayList<UplaodFileMeta>();
		UplaodFileMeta u = new UplaodFileMeta(file.getOriginalFilename(),
				Long.valueOf(file.getSize()).intValue(),
				"http://localhost:8080/spring-fileupload-tutorial/resources/"+file.getOriginalFilename());
 
		uploadedFiles.add(u);
		return uploadedFiles;
	}

}
