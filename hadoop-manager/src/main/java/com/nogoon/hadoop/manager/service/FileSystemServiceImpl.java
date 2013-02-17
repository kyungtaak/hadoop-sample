package com.nogoon.hadoop.manager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nogoon.hadoop.manager.model.HadoopContent;
import com.nogoon.hadoop.manager.model.HadoopFile;

@Service
public class FileSystemServiceImpl implements FileSystemService {

	@Autowired
	@Qualifier("localweb-filesystem")
	FileSystem fileSystem;

	@Override
	public List<HadoopFile> getFileList(String path) {

		List<HadoopFile> resultList = new ArrayList<HadoopFile>();

		FileStatus[] rawFiles = null;
		try {
			rawFiles = fileSystem.listStatus(new Path(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rawFiles == null) {
			return resultList;
		}

		for (FileStatus file : rawFiles) {
			resultList.add(new HadoopFile(file));
		}

		return resultList;
	}

	@Override
	public HadoopContent getfileContents(String path, long offset) {

		String resultContents = null;

		try {
			FSDataInputStream fis = fileSystem.open(new Path(path));
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = fis.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			
			resultContents = out.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new HadoopContent(resultContents);
	}

}
