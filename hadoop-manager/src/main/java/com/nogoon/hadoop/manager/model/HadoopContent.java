package com.nogoon.hadoop.manager.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public class HadoopContent {
	
	@Getter
	String contents;
	

	public HadoopContent() {
	}

	public HadoopContent(String contents) {
		this.contents = contents;
	}
	
}
