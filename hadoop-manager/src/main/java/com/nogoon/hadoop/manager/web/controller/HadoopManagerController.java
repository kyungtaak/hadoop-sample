package com.nogoon.hadoop.manager.web.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nogoon.hadoop.manager.model.HadoopFile;
import com.nogoon.hadoop.manager.service.FileSystemService;

@Controller
@RequestMapping("/home")
public class HadoopManagerController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileSystemService fileSystemService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView viewManagerHome() {
		
		List<HadoopFile> hadoopFileList = fileSystemService.getFileList("/");
		
		String jsonResult = null;
		try {
			jsonResult = new ObjectMapper().writeValueAsString(hadoopFileList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("JSON : \n" + jsonResult);
		
		ModelAndView mav = new ModelAndView("thymeleaf/viewManagerHome");
		mav.addObject("fileList", hadoopFileList);
		mav.addObject("fileInfoString", jsonResult);
		
		return mav;
	}
	
}
