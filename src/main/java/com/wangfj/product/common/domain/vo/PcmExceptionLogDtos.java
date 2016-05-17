package com.wangfj.product.common.domain.vo;

import java.util.Date;
/**
 * 
 * @Comment
 * @Class Name PcmExceptionLogDtos
 * @Author chenhu
 * @Create In 2015-9-9
 */

public class PcmExceptionLogDtos {
	private Long sid;

	private String interfaceName;

	private String exceptionType;

	private Date createTimes;
	private String createTimeStr;

	private String resolveby;

	private Integer processStatus;

	private Date updateTimes;
	private String updateTimeStr;

	private String errorMessage;

	private String dataContent;

	private String uuid;
	
	private int currentPage=1;
	private int limit;
	private int start;
	private int pageSize=10;
	private Date startTime;
	private Date endTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

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

	public Date getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(Date createTimes) {
		this.createTimes = createTimes;
	}

	public String getResolveby() {
		return resolveby;
	}

	public void setResolveby(String resolveby) {
		this.resolveby = resolveby;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public Date getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Date updateTimes) {
		this.updateTimes = updateTimes;
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
		return "PcmExceptionLogDtos [sid=" + sid + ", interfaceName=" + interfaceName
				+ ", exceptionType=" + exceptionType + ", createTimes=" + createTimes
				+ ", resolveby=" + resolveby + ", processStatus=" + processStatus
				+ ", updateTimes=" + updateTimes + ", errorMessage=" + errorMessage
				+ ", dataContent=" + dataContent + ", uuid=" + uuid + "]";
	}
	
}