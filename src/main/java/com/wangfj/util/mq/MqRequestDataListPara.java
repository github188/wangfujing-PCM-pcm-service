package com.wangfj.util.mq;

import java.util.List;

public class MqRequestDataListPara<T> {

	public List<T> data;
	public RequestHeader header;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public RequestHeader getHeader() {
		return header;
	}

	public void setHeader(RequestHeader header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "MqRequestDataListPara [data=" + data + ", header=" + header + "]";
	}

}
