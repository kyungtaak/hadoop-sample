package com.nogoon.hadoop.manager.service;

import java.util.List;

import com.nogoon.hadoop.manager.model.HadoopFile;

public interface FileSystemService {
	List<HadoopFile> getFileList(String path);
}
