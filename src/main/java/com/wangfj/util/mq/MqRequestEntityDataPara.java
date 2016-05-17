package com.wangfj.util.mq;

/**
 * mq 请求 data是单一数据
 * 
 * @Class Name MqRequestEntityDataPara
 * @Author kongqf
 * @Create In 2016年2月1日
 * @param <T>
 */
public class MqRequestEntityDataPara<T> {

	public T data;
	public RequestHeader header;

	public T getData() {
		return data;
	}

	public void setData(T data) {
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
		return "MqRequestEntityDataPara [data=" + data + ", header=" + header + "]";
	}

}
