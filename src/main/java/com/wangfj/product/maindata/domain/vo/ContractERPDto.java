package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;

public class ContractERPDto {
	private List<PcmContractLog> contractList;
	private List<PcmErpProduct> erpList;

	public List<PcmContractLog> getContractList() {
		return contractList;
	}

	public void setContractList(List<PcmContractLog> contractList) {
		this.contractList = contractList;
	}

	public List<PcmErpProduct> getErpList() {
		return erpList;
	}

	public void setErpList(List<PcmErpProduct> erpList) {
		this.erpList = erpList;
	}

	@Override
	public String toString() {
		return "ContractERPDto [contractList=" + contractList + ", erpList=" + erpList + "]";
	}

}