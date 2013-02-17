package com.nogoon.hadoop.manager.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.nogoon.hadoop.manager.model.HadoopContent;
import com.nogoon.hadoop.manager.model.HadoopFile;

public class MockFileSystemService implements FileSystemService {
	
	
	HadoopFile fileSystem;
	
	@PostConstruct
	public void init() {
		fileSystem = new HadoopFile(0, null, "root", "/", true, 0, "epsvc", DateTime.now());
		
		HadoopFile l1file1 = new HadoopFile(1, "root", "test1", "/test1", true, 0, "epsvc", DateTime.now());
		HadoopFile l2file1 = new HadoopFile(2, "test1", "a.txt", "/test1/a.txt", false, 3224L, "epsvc", DateTime.now());
		HadoopFile l2file2 = new HadoopFile(2, "test1", "b.txt", "/test1/b.txt", false, 2345L, "epsvc", DateTime.now());
		l1file1.addChild(l2file1);
		l1file1.addChild(l2file2);
		
		fileSystem.addChild(l1file1);
				
		HadoopFile l1file2 = new HadoopFile(1, "root", "test2", "/test2", true, 0, "epsvc", DateTime.now());
		
		HadoopFile l2file3 = new HadoopFile(2, "test2", "aabb", "/test2/aabb", true, 0, "epsvc", DateTime.now());
		HadoopFile l3file1 = new HadoopFile(3, "aabb", "c.txt", "/test2/aabb/c.txt", false, 2345L, "epsvc", DateTime.now());
		l2file3.addChild(l3file1);
		
		l1file2.addChild(l2file3);
		
		HadoopFile l2file4 = new HadoopFile(2, "test2", "bbcc", "/test2/bbcc", true, 0, "epsvc", DateTime.now());
		HadoopFile l3file2 = new HadoopFile(3, "bbcc", "d.txt", "/test2/bbcc/d.txt", false, 342L, "epsvc", DateTime.now());
		HadoopFile l3file3 = new HadoopFile(3, "bbcc", "f.txt", "/test2/bbcc/d.txt", false, 2345L, "epsvc", DateTime.now());
		l2file4.addChild(l3file2);
		l2file4.addChild(l3file3);
		
		l1file2.addChild(l2file4);
		
		HadoopFile l2file5 = new HadoopFile(2, "test2", "vvaa", "/test2/vvaa", true, 0, "epsvc", DateTime.now());
		l1file2.addChild(l2file5);
		HadoopFile l2file6 = new HadoopFile(2, "test2", "aaa.txt", "/test2/aaa.txt", false, 0, "epsvc", DateTime.now());
		l1file2.addChild(l2file6);
		HadoopFile l2file7 = new HadoopFile(2, "test2", "bbb.txt", "/test2/bbb.txt", false, 0, "epsvc", DateTime.now());
		l1file2.addChild(l2file7);
		
		fileSystem.addChild(l1file2);	
		
	}

	@Override
	public List<HadoopFile> getFileList(String path) {
		
		List<HadoopFile> fileList = getFilePath(fileSystem, path); 
		
		
		return fileList;
	}
	
	public List<HadoopFile> getFilePath(HadoopFile file, String path) {
		if(path.equals(file.getPath())) {
			return file.getChildren();
		}
		
		List<HadoopFile> resultList = null;
		for(HadoopFile subFile : file.getChildren()) {
			resultList = getFilePath(subFile, path);
			if(resultList != null) {
				break;
			}
		}
		
		return resultList;
	}

	@Override
	public HadoopContent getfileContents(String path, long offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
