package com.wangfj.product.common.domain.vo;

public class PcmExceptionLogDto {
	/**
	 * 接口名称
	 */
	private String interfaceName;

	/**
	 * 接口类型
	 */
	private String exceptionType;

	/**
	 * 异常信息
	 */
	private String errorMessage;

	/**
	 * 接口请求数据
	 */
	private String dataContent;

	private String uuid;

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	/**
	 * @Return the String uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @Param String uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "PcmExceptionLogDto [interfaceName=" + interfaceName + ", exceptionType="
				+ exceptionType + ", errorMessage=" + errorMessage + ", dataContent=" + dataContent
				+ ", uuid=" + uuid + "]";
	}

}
