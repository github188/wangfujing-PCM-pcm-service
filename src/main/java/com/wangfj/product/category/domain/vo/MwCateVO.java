package com.wangfj.product.category.domain.vo;

import java.io.Serializable;

/**
 * 说明：
 * @author WangWB
 * @date 2012-8-30
 * @time 下午03:33:03
 * @email wenbao163.good@163.com
 *
 */
public class MwCateVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
    private String serialNumber;  //分类编码
    private String nodeName;      //节点名称
    private int productCount;   //品类商品数量
	

	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	
	
	
}
