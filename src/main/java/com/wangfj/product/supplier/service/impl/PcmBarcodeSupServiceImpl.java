/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.service.implPcmBarcodeServiceImpl.java
 * @Create By wangc
 * @Create In 2016-3-2 下午2:11:29
 * TODO
 */
package com.wangfj.product.supplier.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.demo.service.impl.UserServiceImpl;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.supplier.domain.vo.SupplierBarCodeFromSupDto;
import com.wangfj.product.supplier.service.intf.IPcmBarcodeSupService;
import com.wangfj.util.Constants;

/**
 * 供应商上传条码
 * @Class Name PcmBarcodeServiceImpl
 * @Author wangc
 * @Create In 2016-3-2
 */
@Service
@Transactional
public class PcmBarcodeSupServiceImpl implements IPcmBarcodeSupService {

	@Autowired
	public PcmBarcodeMapper pcmBarcodeMapper;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public void getSupplierBarCodeFromSup(SupplierBarCodeFromSupDto pushSupplierDto)
			throws BleException {
		logger.info("start getSupplierBarCodeFromSup(),param:" + pushSupplierDto.toString());
		// 封装接收到的参数
		PcmBarcode record = new PcmBarcode();
		record.setStoreCode(pushSupplierDto.getStoreCode());
		record.setProductCode(pushSupplierDto.getMatnr());
		record.setShoppeProSid(pushSupplierDto.getMatnr());
		record.setSupplyCode(pushSupplierDto.getLifnr());
		record.setShoppeCode(pushSupplierDto.getCounterCode());
		record.setBarcode(pushSupplierDto.getSbarcode());
		if (Constants.PCMBARCODE_CODE_TYPE_ZE_STR.equals(pushSupplierDto.getSbarcodeType())) {
			record.setCodeType(1);
		} else if (Constants.PCMBARCODE_CODE_TYPE_IE_STR.equals(pushSupplierDto.getSbarcodeType())) {
			record.setCodeType(0);
		}
		record.setBarcodeName(pushSupplierDto.getSbarcode());
		record.setBarcodeUnit(pushSupplierDto.getSaleUnit());
		if (StringUtils.isBlank(pushSupplierDto.getSaleAmount())) {
			record.setSaleMount(new BigDecimal("1"));
		} else {
			record.setSaleMount(new BigDecimal(pushSupplierDto.getSaleAmount()));
		}
		record.setSalePrice(pushSupplierDto.getSalePrice());
		record.setOriginLand(pushSupplierDto.getOriginLand());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();//参数
		if("A".equals(pushSupplierDto.getActionCode())){//新增操作
			paramMap.put("barcode", pushSupplierDto.getSbarcode());// 条码
			paramMap.put("storeCode", pushSupplierDto.getStoreCode());// 门店
			Integer sbarcode = pcmBarcodeMapper.getCountByParam(paramMap);
			if(sbarcode == Constants.PUBLIC_0){
				pcmBarcodeMapper.insertSelective(record);
			}else{//不为0时，条码在该门店下已存在
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.BARCODE_IS_EXIST.getMemo()+"(添加)");
			}
		}
		if("D".equals(pushSupplierDto.getActionCode())){//删除操作
			paramMap.put("barcode", pushSupplierDto.getSbarcode());// 条码
			paramMap.put("storeCode", pushSupplierDto.getStoreCode());// 门店
			Integer sbarcode = pcmBarcodeMapper.getCountByParam(paramMap);
			if(sbarcode == 0){
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.PRO_SUP_BARCODE_ISNULL.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.PRO_SUP_BARCODE_ISNULL.getMemo()+"(更新)");
			}else{
				paramMap.put("shoppeProSid", pushSupplierDto.getMatnr()); // 专柜商品编码
				List<PcmBarcode> sbarcodes = pcmBarcodeMapper.selectListByParam(paramMap);// 查询得到已有条码SID
				if(sbarcodes.size()==0){
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.PRO_BARCODE_PROCODE_ERROR.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.PRO_BARCODE_PROCODE_ERROR.getMemo()+"(更新)");
				}else if(sbarcodes.size()==1){
					record.setSid(sbarcodes.get(0).getSid());// 将已有SID赋值到接收到的参数实体
					pcmBarcodeMapper.deleteByPrimaryKey(record.getSid());// 删除已有条码
				}
				else{
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			}
		}
		logger.info("end getSupplierBarCodeFromSup()");

	}

}
