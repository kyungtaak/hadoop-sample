package com.nogoon.hadoop.manager.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.hadoop.fs.FileStatus;
import org.joda.time.DateTime;

import com.nogoon.hadoop.manager.constant.FileSystemType;
import com.nogoon.hadoop.manager.util.PathUtils;

@AllArgsConstructor
@ToString
public class HadoopFile {
	
	@Getter
	int level;
	@Getter
	String parentDir;
	@Getter
	String name;
	@Getter
	String path;
	@Getter
	boolean isDir;
	@Getter
	long length;
	@Getter
	String owner;
	@Getter
	String group;
	@Getter
	String permission;
	@Getter
	DateTime modTime;
	@Getter @Setter
	transient List<HadoopFile> children = new ArrayList<HadoopFile>();
	
	public HadoopFile() {
	}
	
	public HadoopFile(FileStatus status) {
		this.level = status.getPath().depth();
		this.parentDir = status.getPath().getParent().getName();
		this.name = status.getPath().getName();
		this.path = status.getPath().toUri().getPath();
		this.isDir = status.isDir();
		this.length = status.getLen();
		this.owner = status.getGroup();
		this.group = status.getGroup();
		this.permission = status.getPermission().toString();
		this.modTime = new DateTime(status.getModificationTime());
	}

	public HadoopFile(int level, String parentDir, String name, String path,
			boolean isDir, long length, String owner, DateTime modTime) {
		this.level = level;
		this.parentDir = parentDir;
		this.name = name;
		this.path = path;
		this.isDir = isDir;
		this.length = length;
		this.owner = owner;
		this.modTime = modTime;
	}
	
	public void addChild(HadoopFile file) {
		children.add(file);
	}
	
	
}
