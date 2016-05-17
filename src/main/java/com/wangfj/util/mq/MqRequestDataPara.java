package com.wangfj.util.mq;

public class MqRequestDataPara {

	public String data;
	public RequestHeader header;

	public String getData() {
		return data;
	}

	public void setData(String data) {
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
		return "MqRequestDataPara [data=" + data + ", header=" + header + "]";
	}

}
