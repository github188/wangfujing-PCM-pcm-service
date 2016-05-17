package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 只做返回验证结果使用
 * 
 * @Class Name ValidResultVo
 * @Author wangsy
 * @Create In 2015年7月14日
 */
public class ValidResultDto {

	/**
	 * 1 成功(详细结果见返回的对象列表); 0专柜编码在中台不存在;
	 */
	private String resCode;
	/**
	 * resCode对应的详细文本信息
	 */
	private String resMessage;
	/**
	 * resList中包含的记录数(大于或等于0)
	 */
	private String resCount;
	/**
	 * 输入的productList对应的校验结果
	 */
	private List<ValidResProMsgDto> resList;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public String getResCount() {
		return resCount;
	}

	public void setResCount(String resCount) {
		this.resCount = resCount;
	}

	public List<ValidResProMsgDto> getResList() {
		return resList;
	}

	public void setResList(List<ValidResProMsgDto> resList) {
		this.resList = resList;
	}

	@Override
	public String toString() {
		return "ValidResultVo [resCode=" + resCode + ", resMessage=" + resMessage + ", resCount="
				+ resCount + ", resList=" + resList + "]";
	}

}
