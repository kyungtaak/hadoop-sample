package com.nogoon.hadoop.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class UplaodFileMeta {
	
	@Getter
	private String name;
	
	@Getter
	private Integer size;
	
	@Getter
	private String url;
	
	@Getter
	private String thumbnail_url;
	
	@Getter
	private String delete_url;
	
	@Getter
	private String delete_type;

	public UplaodFileMeta() {
	}
	
	public UplaodFileMeta(String name, Integer size, String url) {
		this.name = name;
		this.size = size;
		this.url = url;
	}
		
	
}
