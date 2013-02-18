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
import com.nogoon.hadoop.manager.model.HadoopStreamingStatus;
import com.nogoon.hadoop.manager.service.FileSystemService;
import com.nogoon.hadoop.manager.service.HadoopStreamingService;
import com.nogoon.hadoop.manager.util.FileUtils;

@Controller
@RequestMapping("/streaming")
public class HadoopStreamingController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final String TEMP_DIR = "/Volumes/data/Temp";
	
	@Autowired
	HadoopStreamingService hsService;
	
	@RequestMapping(value="/script", method = RequestMethod.GET)
	@ResponseBody
	public HadoopStreamingStatus runStreamingScript(@RequestParam(value="mapperScript") String mapperScript, 
											@RequestParam(value="reducerScript") String reducerScript,
											@RequestParam(value="inputPath") String inputPath,
											@RequestParam(value="outputPath") String outputPath ) {
		
		log.info("call runStreamingScript : ");
		
		//System.out.println(FileUtils.makeFile(mapperScript, TEMP_DIR, "mapper.py"));
		//System.out.println(FileUtils.makeFile(reducerScript, TEMP_DIR, "reducer.py"));
		
		int status = hsService.invokeStreamingJob(FileUtils.makeFile(mapperScript, TEMP_DIR, "mapper.py"), 
											FileUtils.makeFile(reducerScript, TEMP_DIR, "reducer.py"), inputPath, outputPath);
		
		return new HadoopStreamingStatus(status);
	}
	
	
}
