package com.nogoon.hadoop.manager.model;

import lombok.Getter;

public class HadoopStreamingStatus {
	@Getter
	int status;
	
	public HadoopStreamingStatus() {
	}
	
	public HadoopStreamingStatus(int status) {
		this.status = status;
	}

	
	
}
