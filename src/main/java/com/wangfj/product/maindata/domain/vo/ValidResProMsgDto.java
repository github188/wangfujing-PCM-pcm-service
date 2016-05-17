package com.wangfj.product.maindata.domain.vo;

/**
 * resList中详细商品验证信息
 * 
 * @Class Name ValidResProMsgVo
 * @Author wangsy
 * @Create In 2015年7月14日
 */
public class ValidResProMsgDto {

	/**
	 * 款号（吊牌或者外包装上的款号）
	 */
	private String productNum;
	/**
	 * 色码（吊牌或者外包装上的色码对应的字典编码）
	 */
	private String colorCode;
	/**
	 * 尺码/规格(吊牌或者外包装上的尺码/规格在尺码字典中的编码)。如果manageType为1表示为大码，sizeCode字段可以为空，否则不可为空。
	 */
	private String sizeCode;
	/**
	 * 商品编码（中台编码）
	 */
	private String productCode;
	/**
	 * 专柜商品编码（中台编码）
	 */
	private String supplierProductCode;
	/**
	 * 库存方式 ZK 自库 XK 虚库 BG 不管库
	 */
	private String stockTypeLib;
	/**
	 * 先销后采(Y/N)。仅对于电商ERP的商品有这个值。门店ERP和导入终端上传的商品没有这个字段。
	 */
	private String isSelllPurchase;
	/**
	 * 大码类型
	 */
	private String erpSkuType;
	/**
	 * 本行商品对应的校验结果
	 * <p>
	 * 1存在商品和对应的专柜商品；
	 * <p>
	 * 2存在商品但是不存在对应的专柜商品，需要挂大码；
	 * <p>
	 * 3商品不存在，需要准入；
	 * <p>
	 * 4其它。接口字段传的是编码（例如1）。
	 */
	private String resCode;

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public String getStockTypeLib() {
		return stockTypeLib;
	}

	public void setStockTypeLib(String stockTypeLib) {
		this.stockTypeLib = stockTypeLib;
	}

	public String getErpSkuType() {
		return erpSkuType;
	}

	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getIsSelllPurchase() {
		return isSelllPurchase;
	}

	public void setIsSelllPurchase(String isSelllPurchase) {
		this.isSelllPurchase = isSelllPurchase;
	}

	@Override
	public String toString() {
		return "ValidResProMsgDto [productNum=" + productNum + ", colorCode=" + colorCode
				+ ", sizeCode=" + sizeCode + ", productCode=" + productCode
				+ ", supplierProductCode=" + supplierProductCode + ", stockTypeLib=" + stockTypeLib
				+ ", isSelllPurchase=" + isSelllPurchase + ", erpSkuType=" + erpSkuType
				+ ", resCode=" + resCode + "]";
	}

}
