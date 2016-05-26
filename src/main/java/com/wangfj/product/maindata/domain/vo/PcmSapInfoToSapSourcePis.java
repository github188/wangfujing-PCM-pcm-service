/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voPcmSapInfoToSapSourcePis.java
 * @Create By wangc
 * @Create In 2016年5月24日 上午11:05:33
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

/**
 * @Class Name PcmSapInfoToSapSourcePis
 * @Author wangc
 * @Create In 2016年5月24日
 */
public class PcmSapInfoToSapSourcePis {
	private String STORECODE;  //门店编码
	private String PRODUCTCODE;  //专柜商品编码
	private String ZGID; //专柜编码
	private String BUSINESSTYPELIB;  //业态
	private String BUSINESSPRACTICELIB;  //经营方式
	private String STOCKTYPELIB;   //库存方式
	private String BASEPRODUCTCODE;  //spu编码
	private String ISGIFT;  //赠品范围
	private String PCM_SKU;  //sku编码
	private String ACTIONCODE;  //操作类型
	private String ACTIONDATE;  //操作日期
	private String ACTIONPERSON;  //操作人
	private String MTART;//商品类型
	private String MATNR;//商品编码
	private String MAKTX;//商品描述
	private String MATKL;//工业分类
	private String ZZTJFL;//统计分类
	private String LIFNR;//供应商编码
	private String GLJG;//管理分类
	private String ZBRAND_ID;//品牌
	private String ZZSTYLENO;//款号
	private String ZZDKNO;//货号
	private String ZZE_EAN;//供应商条码/国标码
	private String BISMT;//供应商商品编码
	private String MEINS;//基本计量单位
	private String ZZCOLORID;//色系
	private String ZCOLOR;//色码
	private String ZSIZE;//尺码
	private String MWSKZ;//进项税
	private String TAXKM1;//销项税
	private String TAXKM2;//消费税
	private String ZZGJ_TAX;//进价
	private String ZZP01_TAX;//售价
	private String ZZPRICE;//市场价
	private String WHERL;//原产国
	private String WHERR;//货源地
	private String ZZSSDATE;//上市日期
	private String EXTWG;//季节
	private String ZZGENDER;//适用人群
	private String ZZCOD;//是否COD
	private String ZZYCPACK;//是否原厂包装
	private String MHDHB;//总货架寿命
	private String MHDRZ;//剩余货架寿命
	private String MAGRV;//包装组(物流类型)
	public String getSTORECODE() {
		return STORECODE;
	}
	public void setSTORECODE(String sTORECODE) {
		STORECODE = sTORECODE;
	}
	public String getPRODUCTCODE() {
		return PRODUCTCODE;
	}
	public void setPRODUCTCODE(String pRODUCTCODE) {
		PRODUCTCODE = pRODUCTCODE;
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
	public String getPCM_SKU() {
		return PCM_SKU;
	}
	public void setPCM_SKU(String pCM_SKU) {
		PCM_SKU = pCM_SKU;
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
	public String getMTART() {
		return MTART;
	}
	public void setMTART(String mTART) {
		MTART = mTART;
	}
	public String getMATNR() {
		return MATNR;
	}
	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}
	public String getMAKTX() {
		return MAKTX;
	}
	public void setMAKTX(String mAKTX) {
		MAKTX = mAKTX;
	}
	public String getMATKL() {
		return MATKL;
	}
	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}
	public String getZZTJFL() {
		return ZZTJFL;
	}
	public void setZZTJFL(String zZTJFL) {
		ZZTJFL = zZTJFL;
	}
	public String getLIFNR() {
		return LIFNR;
	}
	public void setLIFNR(String lIFNR) {
		LIFNR = lIFNR;
	}
	public String getGLJG() {
		return GLJG;
	}
	public void setGLJG(String gLJG) {
		GLJG = gLJG;
	}
	public String getZBRAND_ID() {
		return ZBRAND_ID;
	}
	public void setZBRAND_ID(String zBRAND_ID) {
		ZBRAND_ID = zBRAND_ID;
	}
	public String getZZSTYLENO() {
		return ZZSTYLENO;
	}
	public void setZZSTYLENO(String zZSTYLENO) {
		ZZSTYLENO = zZSTYLENO;
	}
	public String getZZDKNO() {
		return ZZDKNO;
	}
	public void setZZDKNO(String zZDKNO) {
		ZZDKNO = zZDKNO;
	}
	public String getZZE_EAN() {
		return ZZE_EAN;
	}
	public void setZZE_EAN(String zZE_EAN) {
		ZZE_EAN = zZE_EAN;
	}
	public String getBISMT() {
		return BISMT;
	}
	public void setBISMT(String bISMT) {
		BISMT = bISMT;
	}
	public String getMEINS() {
		return MEINS;
	}
	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}
	public String getZZCOLORID() {
		return ZZCOLORID;
	}
	public void setZZCOLORID(String zZCOLORID) {
		ZZCOLORID = zZCOLORID;
	}
	public String getZCOLOR() {
		return ZCOLOR;
	}
	public void setZCOLOR(String zCOLOR) {
		ZCOLOR = zCOLOR;
	}
	public String getZSIZE() {
		return ZSIZE;
	}
	public void setZSIZE(String zSIZE) {
		ZSIZE = zSIZE;
	}
	public String getMWSKZ() {
		return MWSKZ;
	}
	public void setMWSKZ(String mWSKZ) {
		MWSKZ = mWSKZ;
	}
	public String getTAXKM1() {
		return TAXKM1;
	}
	public void setTAXKM1(String tAXKM1) {
		TAXKM1 = tAXKM1;
	}
	public String getTAXKM2() {
		return TAXKM2;
	}
	public void setTAXKM2(String tAXKM2) {
		TAXKM2 = tAXKM2;
	}
	public String getZZGJ_TAX() {
		return ZZGJ_TAX;
	}
	public void setZZGJ_TAX(String zZGJ_TAX) {
		ZZGJ_TAX = zZGJ_TAX;
	}
	public String getZZP01_TAX() {
		return ZZP01_TAX;
	}
	public void setZZP01_TAX(String zZP01_TAX) {
		ZZP01_TAX = zZP01_TAX;
	}
	public String getZZPRICE() {
		return ZZPRICE;
	}
	public void setZZPRICE(String zZPRICE) {
		ZZPRICE = zZPRICE;
	}
	public String getWHERL() {
		return WHERL;
	}
	public void setWHERL(String wHERL) {
		WHERL = wHERL;
	}
	public String getWHERR() {
		return WHERR;
	}
	public void setWHERR(String wHERR) {
		WHERR = wHERR;
	}
	public String getZZSSDATE() {
		return ZZSSDATE;
	}
	public void setZZSSDATE(String zZSSDATE) {
		ZZSSDATE = zZSSDATE;
	}
	public String getEXTWG() {
		return EXTWG;
	}
	public void setEXTWG(String eXTWG) {
		EXTWG = eXTWG;
	}
	public String getZZGENDER() {
		return ZZGENDER;
	}
	public void setZZGENDER(String zZGENDER) {
		ZZGENDER = zZGENDER;
	}
	public String getZZCOD() {
		return ZZCOD;
	}
	public void setZZCOD(String zZCOD) {
		ZZCOD = zZCOD;
	}
	public String getZZYCPACK() {
		return ZZYCPACK;
	}
	public void setZZYCPACK(String zZYCPACK) {
		ZZYCPACK = zZYCPACK;
	}
	public String getMHDHB() {
		return MHDHB;
	}
	public void setMHDHB(String mHDHB) {
		MHDHB = mHDHB;
	}
	public String getMHDRZ() {
		return MHDRZ;
	}
	public void setMHDRZ(String mHDRZ) {
		MHDRZ = mHDRZ;
	}
	public String getMAGRV() {
		return MAGRV;
	}
	public void setMAGRV(String mAGRV) {
		MAGRV = mAGRV;
	}
	@Override
	public String toString() {
		return "PcmSapInfoToSapSourcePis [STORECODE=" + STORECODE + ", PRODUCTCODE=" + PRODUCTCODE + ", ZGID=" + ZGID
				+ ", BUSINESSTYPELIB=" + BUSINESSTYPELIB + ", BUSINESSPRACTICELIB=" + BUSINESSPRACTICELIB
				+ ", STOCKTYPELIB=" + STOCKTYPELIB + ", BASEPRODUCTCODE=" + BASEPRODUCTCODE + ", ISGIFT=" + ISGIFT
				+ ", PCM_SKU=" + PCM_SKU + ", ACTIONCODE=" + ACTIONCODE + ", ACTIONDATE=" + ACTIONDATE
				+ ", ACTIONPERSON=" + ACTIONPERSON + ", MTART=" + MTART + ", MATNR=" + MATNR + ", MAKTX=" + MAKTX
				+ ", MATKL=" + MATKL + ", ZZTJFL=" + ZZTJFL + ", LIFNR=" + LIFNR + ", GLJG=" + GLJG + ", ZBRAND_ID="
				+ ZBRAND_ID + ", ZZSTYLENO=" + ZZSTYLENO + ", ZZDKNO=" + ZZDKNO + ", ZZE_EAN=" + ZZE_EAN + ", BISMT="
				+ BISMT + ", MEINS=" + MEINS + ", ZZCOLORID=" + ZZCOLORID + ", ZCOLOR=" + ZCOLOR + ", ZSIZE=" + ZSIZE
				+ ", MWSKZ=" + MWSKZ + ", TAXKM1=" + TAXKM1 + ", TAXKM2=" + TAXKM2 + ", ZZGJ_TAX=" + ZZGJ_TAX
				+ ", ZZP01_TAX=" + ZZP01_TAX + ", ZZPRICE=" + ZZPRICE + ", WHERL=" + WHERL + ", WHERR=" + WHERR
				+ ", ZZSSDATE=" + ZZSSDATE + ", EXTWG=" + EXTWG + ", ZZGENDER=" + ZZGENDER + ", ZZCOD=" + ZZCOD
				+ ", ZZYCPACK=" + ZZYCPACK + ", MHDHB=" + MHDHB + ", MHDRZ=" + MHDRZ + ", MAGRV=" + MAGRV + "]";
	}
	
	
	
}
