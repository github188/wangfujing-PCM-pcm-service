package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class ContractInfoDto {
	private String supplierName;
	private List<String> contractList;
	private List<ShoppeErpDto> shoppeList;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<String> getContractList() {
		return contractList;
	}

	public void setContractList(List<String> contractList) {
		this.contractList = contractList;
	}

	public List<ShoppeErpDto> getShoppeList() {
		return shoppeList;
	}

	public void setShoppeList(List<ShoppeErpDto> shoppeList) {
		this.shoppeList = shoppeList;
	}

	@Override
	public String toString() {
		return "ContractInfoDto [supplierName=" + supplierName + ", contractList=" + contractList
				+ ", shoppeList=" + shoppeList + "]";
	}

}
