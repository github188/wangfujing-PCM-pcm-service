package com.wangfj.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractJsonResponse<T> {
	private String resCode;

	@JsonProperty("msg")
	private String message;

	@JsonProperty("obj")
	private T restObject;

	/**
	 * @return the resCode
	 */
	public String getResCode() {
		return resCode;
	}

	/**
	 * @param resCode
	 *            the resCode to set
	 */
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the restObject
	 */
	public T getRestObject() {
		return restObject;
	}

	/**
	 * @param restObject
	 *            the restObject to set
	 */
	public void setRestObject(T restObject) {
		this.restObject = restObject;
	}
}
