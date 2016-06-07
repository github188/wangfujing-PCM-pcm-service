package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

public class SapContractDto {
	private String GLFL;
	
	private String CONTRACTCODE; // 要约号

	private String STORECODE;// 门店编码

	private String SUPPLIERCODE;// 供应商编码

	private String MANAGETYPE;// 经营方式(经销9,代销2，联营0，租赁3)

	private String BUYTYPE;// 采购类别（总部集采“1”、城市集采“2”、门店经营“3”）

	private String OPERMODE;// 管理方式（单品“1” 金饰“5”服务费“6”租赁“7”）

	private String BUSINESSTYPE;// 业态类型（百货:E 超市:C）

	private String INPUTTAX;// 进项税率（0.17,0.13,0.05.......）

	private String OUTPUTTAX; // 销项税率(0.17,0.13,0.05....)

	private String COMMISSIONRATE;// 要约扣率

	private String ACTION_CODE;// 本条记录对应的操作 (A添加；U更新；D删除)

	private String ACTION_DATE;// 操作时间（格式为yyyyMMdd.HHmmssZ，结果形如”
								// 20141008.101603+0800”）

	private String ACTION_PERSON;// 操作人
	private List<SapProListDto> PRODTOLIST = new ArrayList<SapProListDto>();//商品列表

	public List<SapProListDto> getPRODTOLIST() {
		return PRODTOLIST;
	}

	public void setPRODTOLIST(List<SapProListDto> pRODTOLIST) {
		PRODTOLIST = pRODTOLIST;
	}

	public String getCONTRACTCODE() {
		return CONTRACTCODE;
	}

	public String getGLFL() {
		return GLFL;
	}

	public void setGLFL(String gLFL) {
		GLFL = gLFL;
	}

	public void setCONTRACTCODE(String cONTRACTCODE) {
		CONTRACTCODE = cONTRACTCODE;
	}

	public String getSTORECODE() {
		return STORECODE;
	}

	public void setSTORECODE(String sTORECODE) {
		STORECODE = sTORECODE;
	}

	public String getSUPPLIERCODE() {
		return SUPPLIERCODE;
	}

	public void setSUPPLIERCODE(String sUPPLIERCODE) {
		SUPPLIERCODE = sUPPLIERCODE;
	}

	public String getMANAGETYPE() {
		return MANAGETYPE;
	}

	public void setMANAGETYPE(String mANAGETYPE) {
		MANAGETYPE = mANAGETYPE;
	}

	public String getBUYTYPE() {
		return BUYTYPE;
	}

	public void setBUYTYPE(String bUYTYPE) {
		BUYTYPE = bUYTYPE;
	}

	public String getOPERMODE() {
		return OPERMODE;
	}

	public void setOPERMODE(String oPERMODE) {
		OPERMODE = oPERMODE;
	}

	public String getBUSINESSTYPE() {
		return BUSINESSTYPE;
	}

	public void setBUSINESSTYPE(String bUSINESSTYPE) {
		BUSINESSTYPE = bUSINESSTYPE;
	}

	public String getINPUTTAX() {
		return INPUTTAX;
	}

	public void setINPUTTAX(String iNPUTTAX) {
		INPUTTAX = iNPUTTAX;
	}

	public String getOUTPUTTAX() {
		return OUTPUTTAX;
	}

	public void setOUTPUTTAX(String oUTPUTTAX) {
		OUTPUTTAX = oUTPUTTAX;
	}

	public String getCOMMISSIONRATE() {
		return COMMISSIONRATE;
	}

	public void setCOMMISSIONRATE(String cOMMISSIONRATE) {
		COMMISSIONRATE = cOMMISSIONRATE;
	}

	public String getACTION_CODE() {
		return ACTION_CODE;
	}

	public void setACTION_CODE(String aCTION_CODE) {
		ACTION_CODE = aCTION_CODE;
	}

	public String getACTION_DATE() {
		return ACTION_DATE;
	}

	public void setACTION_DATE(String aCTION_DATE) {
		ACTION_DATE = aCTION_DATE;
	}

	public String getACTION_PERSON() {
		return ACTION_PERSON;
	}

	public void setACTION_PERSON(String aCTION_PERSON) {
		ACTION_PERSON = aCTION_PERSON;
	}

	@Override
	public String toString() {
		return "SaveContractParaSAPERP [CONTRACTCODE=" + CONTRACTCODE + ", STORECODE=" + STORECODE
				+ ", SUPPLIERCODE=" + SUPPLIERCODE + ", MANAGETYPE=" + MANAGETYPE + ", BUYTYPE="
				+ BUYTYPE + ", OPERMODE=" + OPERMODE + ", BUSINESSTYPE=" + BUSINESSTYPE
				+ ", INPUTTAX=" + INPUTTAX + ", OUTPUTTAX=" + OUTPUTTAX + ", COMMISSIONRATE="
				+ COMMISSIONRATE + ", ACTION_CODE=" + ACTION_CODE + ", ACTION_DATE=" + ACTION_DATE
				+ ", ACTION_PERSON=" + ACTION_PERSON + "]";
	}

}
