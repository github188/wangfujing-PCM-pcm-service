package com.wangfj.util.mq;

import java.util.List;

public class MqMessage {
	
	private List data;
	
	private Header header;

	

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	
}
