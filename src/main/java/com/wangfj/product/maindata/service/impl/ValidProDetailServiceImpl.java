package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;

@Service
public class ValidProDetailServiceImpl implements IValidProDrtailService {
	@Autowired
	private PcmProductMapper pcmProductMapper;
	@Autowired
	private PcmBarcodeMapper barcodeMapper;
	@Autowired
	private PcmProDetailMapper pcmProDetailMapper;
	@Autowired
	private PcmSupplyInfoMapper pcmSupplyInfoMapper;
	@Autowired
	private PcmShoppeMapper pcmShoppeMapper;
	@Autowired
	private PcmShoppeProductMapper pcmShoppeProductMapper;

	/**
	 * SPU判重
	 * 
	 * @Methods Name validSpuBh
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return PcmProduct
	 */
	public PcmProduct validSpuBh(ValidProDetailDto dto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brandSid", dto.getBrandSid());
		if (dto.getProductSku() != null && !dto.getProductSku().equals("")) {
			paramMap.put("productSku", dto.getProductSku());
		}
		if (dto.getPrimaryAttr() != null && !dto.getPrimaryAttr().equals("")) {
			paramMap.put("primaryAttr", dto.getPrimaryAttr());
		}
		List<PcmProduct> count = pcmProductMapper.selectListByParam(paramMap);
		if (count.size() > 0 && count != null) {
			return count.get(0);
		}
		return null;
	}

	/**
	 * 超市SKU判重(0没重复、1sku已存在，可添加专柜商品、2、3无法使用的sku)
	 * 
	 * @Methods Name ValidSkuCs
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 */
	public List<PcmProDetail> validSku(ValidProDetailDto dto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productSid", dto.getProductSid());
		paramMap.put("proStanSid", dto.getProStanSid());
		List<PcmProDetail> count = new ArrayList<PcmProDetail>();
		if (dto.getFeatures() != null && !dto.getFeatures().equals("")) {// 超市商品判重
			paramMap.put("features", dto.getFeatures()); /* 特性 */
			count = pcmProDetailMapper.validSku(paramMap);
		} else {// 百货商品判重
			paramMap.put("proColorSid", dto.getProColorSid()); /* 色系 */
			count = pcmProDetailMapper.validSku(paramMap);
			if (count.size() == 1) {
				if (!count.get(0).getProColorName().equals(dto.getProColorName())) {
					count.add(count.get(0));
					count.add(count.get(0));
				}
			} else if (count.size() == 0) {
				paramMap.clear();
				paramMap.put("proColorSid", dto.getProColorSid()); /* 色系 */
				paramMap.put("productSid", dto.getProductSid());
				count = pcmProDetailMapper.validSku(paramMap);
				if (count.size() != 0) {
					if (!count.get(0).getProColorName().equals(dto.getProColorName())) {
						count.add(count.get(0));
						count.add(count.get(0));
					} else {
						count.clear();
					}
				}
			}
		}
		return count;
	}

	/**
	 * 专柜商品判重
	 * 
	 * @Methods Name ValidShoppeProBh
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 */
	public PcmShoppeProduct validShoppeProBh(ValidShoppeProDto dto) throws BleException {
		// 条码校验 同一门店下条码不能重复
		if (StringUtils.isNotBlank(dto.getBarcode()) && StringUtils.isNotBlank(dto.getStoreCode())) {
			PcmBarcode barcodeEntity = new PcmBarcode();
			barcodeEntity.setBarcode(dto.getBarcode());// 条码
			barcodeEntity.setStoreCode(dto.getStoreCode());// 门店编码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo());
			}
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (dto.getShoppeSid() != null) {
			paramMap.put("shoppeSid", dto.getShoppeSid());
		} else {
			paramMap.put("shoppeCode", dto.getShoppeCode());
			List<PcmShoppe> mapList2 = pcmShoppeMapper.selectListByParam(paramMap);
			paramMap.clear();
			paramMap.put("shoppeSid", mapList2.get(0).getSid());
		}
		if (dto.getSaleUnitCode() != null && !dto.getSaleUnitCode().equals("")) {
			paramMap.put("saleUnitCode", dto.getSaleUnitCode());
		}
		paramMap.put("supplySid", dto.getSupplySid());
		paramMap.put("productDetailSid", dto.getProductDetailSid());
		List<PcmShoppeProduct> count = pcmShoppeProductMapper.selectListByParam(paramMap);
		if (count.size() > 0 && count != null) {
			return count.get(0);
		}
		return null;
	}

	/**
	 * 专柜商品判重(非联营)
	 * 
	 * @Methods Name ValidShoppePro
	 * @Create In 2015年8月17日 By yedong
	 * @param dto
	 * @return boolean
	 */
	public PcmShoppeProduct validShoppePro(ValidShoppeProDto dto) throws BleException {
		// 条码校验 同一门店下条码不能重复
		if (StringUtils.isNotBlank(dto.getBarcode()) && StringUtils.isNotBlank(dto.getStoreCode())) {
			PcmBarcode barcodeEntity = new PcmBarcode();
			barcodeEntity.setBarcode(dto.getBarcode());// 条码
			barcodeEntity.setStoreCode(dto.getStoreCode());// 门店编码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo());
			}
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (dto.getShoppeSid() != null) {
			paramMap.put("shoppeSid", dto.getShoppeSid());
		} else {
			paramMap.put("shoppeCode", dto.getShoppeCode());
			List<PcmShoppe> mapList2 = pcmShoppeMapper.selectListByParam(paramMap);
			paramMap.clear();
			paramMap.put("shoppeSid", mapList2.get(0).getSid());
		}
		if (dto.getSaleUnitCode() != null && !dto.getSaleUnitCode().equals("")) {
			paramMap.put("saleUnitCode", dto.getSaleUnitCode());
		}
		// paramMap.put("supplySid", dto.getSupplySid());// 自营商品供应商不加入盼重,走一品多供
		paramMap.put("productDetailSid", dto.getProductDetailSid());
		List<PcmShoppeProduct> count = pcmShoppeProductMapper.selectListByParam(paramMap);
		if (count != null && count.size() > 0) {
			return count.get(0);
		}
		return null;
	}
}
