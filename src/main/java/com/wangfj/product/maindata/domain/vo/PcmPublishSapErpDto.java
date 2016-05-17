package com.wangfj.product.maindata.domain.vo;

public class PcmPublishSapErpDto {
	private String STORECODE;
	private String SUPPLIERPRODUCTCODE;
	private String PRODUCTCODE;
	private String ERPCODE;
	private String SUPPLIERCODE;
	private String ZGID;
	private String BUSINESSTYPELIB;
	private String BUSINESSPRACTICELIB;
	private String STOCKTYPELIB;
	private String BASEPRODUCTCODE;
	private String ISGIFT;
	private String PCM_SKU;
	private String ACTIONCODE;
	private String ACTIONDATE;
	private String ACTIONPERSON;

	public String getPCM_SKU() {
		return PCM_SKU;
	}

	public void setPCM_SKU(String pCM_SKU) {
		PCM_SKU = pCM_SKU;
	}

	public String getSTORECODE() {
		return STORECODE;
	}

	public void setSTORECODE(String sTORECODE) {
		STORECODE = sTORECODE;
	}

	public String getSUPPLIERPRODUCTCODE() {
		return SUPPLIERPRODUCTCODE;
	}

	public void setSUPPLIERPRODUCTCODE(String sUPPLIERPRODUCTCODE) {
		SUPPLIERPRODUCTCODE = sUPPLIERPRODUCTCODE;
	}

	public String getPRODUCTCODE() {
		return PRODUCTCODE;
	}

	public void setPRODUCTCODE(String pRODUCTCODE) {
		PRODUCTCODE = pRODUCTCODE;
	}

	public String getERPCODE() {
		return ERPCODE;
	}

	public void setERPCODE(String eRPCODE) {
		ERPCODE = eRPCODE;
	}

	public String getSUPPLIERCODE() {
		return SUPPLIERCODE;
	}

	public void setSUPPLIERCODE(String sUPPLIERCODE) {
		SUPPLIERCODE = sUPPLIERCODE;
	}

	public String getZGID() {
		return ZGID;
	}

	public void setZGID(String zGID) {
		ZGID = zGID;
	}

	public String getBUSINESSTYPELIB() {
		return BUSINESSTYPELIB;
	}

	public void setBUSINESSTYPELIB(String bUSINESSTYPELIB) {
		BUSINESSTYPELIB = bUSINESSTYPELIB;
	}

	public String getBUSINESSPRACTICELIB() {
		return BUSINESSPRACTICELIB;
	}

	public void setBUSINESSPRACTICELIB(String bUSINESSPRACTICELIB) {
		BUSINESSPRACTICELIB = bUSINESSPRACTICELIB;
	}

	public String getSTOCKTYPELIB() {
		return STOCKTYPELIB;
	}

	public void setSTOCKTYPELIB(String sTOCKTYPELIB) {
		STOCKTYPELIB = sTOCKTYPELIB;
	}

	public String getBASEPRODUCTCODE() {
		return BASEPRODUCTCODE;
	}

	public void setBASEPRODUCTCODE(String bASEPRODUCTCODE) {
		BASEPRODUCTCODE = bASEPRODUCTCODE;
	}

	public String getISGIFT() {
		return ISGIFT;
	}

	public void setISGIFT(String iSGIFT) {
		ISGIFT = iSGIFT;
	}

	public String getACTIONCODE() {
		return ACTIONCODE;
	}

	public void setACTIONCODE(String aCTIONCODE) {
		ACTIONCODE = aCTIONCODE;
	}

	public String getACTIONDATE() {
		return ACTIONDATE;
	}

	public void setACTIONDATE(String aCTIONDATE) {
		ACTIONDATE = aCTIONDATE;
	}

	public String getACTIONPERSON() {
		return ACTIONPERSON;
	}

	public void setACTIONPERSON(String aCTIONPERSON) {
		ACTIONPERSON = aCTIONPERSON;
	}

	@Override
	public String toString() {
		return "PcmPublishSapErpDto [STORECODE=" + STORECODE + ", SUPPLIERPRODUCTCODE="
				+ SUPPLIERPRODUCTCODE + ", PRODUCTCODE=" + PRODUCTCODE + ", ERPCODE=" + ERPCODE
				+ ", SUPPLIERCODE=" + SUPPLIERCODE + ", ZGID=" + ZGID + ", BUSINESSTYPELIB="
				+ BUSINESSTYPELIB + ", BUSINESSPRACTICELIB=" + BUSINESSPRACTICELIB
				+ ", STOCKTYPELIB=" + STOCKTYPELIB + ", BASEPRODUCTCODE=" + BASEPRODUCTCODE
				+ ", ISGIFT=" + ISGIFT + ", PCM_SKU=" + PCM_SKU + ", ACTIONCODE=" + ACTIONCODE
				+ ", ACTIONDATE=" + ACTIONDATE + ", ACTIONPERSON=" + ACTIONPERSON + "]";
	}

}
