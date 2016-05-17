package com.wangfj.util;

import java.util.List;

public class CheckVO {
	private String caller;
    private String username;
    private Object messageBody;
    private String stamp;
    private String signature;
    
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Object getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(Object messageBody) {
		this.messageBody = messageBody;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
    
}
