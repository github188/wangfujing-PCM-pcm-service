package com.wangfj.product.maindata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmBarcodeHis;
import com.wangfj.product.maindata.domain.vo.SupplierBarCodeFromEfutureDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeHisMapper;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.service.intf.IPcmBarcodeService;
import com.wangfj.util.Constants;

/**
 * 条码管理 Service
 *
 * @Class Name PcmBarcodeServiceImpl
 * @Author yedong
 * @Create In 2015年7月13日
 */
@Service
public class PcmBarcodeServiceImpl implements IPcmBarcodeService {

    @Autowired
    public PcmBarcodeMapper pcmBarcodeMapper;

    @Autowired
    private PcmBarcodeHisMapper barcodeHisMapper;

    private static final Logger logger = LoggerFactory.getLogger(PcmBarcodeServiceImpl.class);

    /**
     * 下发条码查询
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<PcmBarcode> pushBarcodeToSAPERP(Map<String, Object> paramMap) {
        logger.info("start pushBarcodeToSAPERP(),param:");
        List<PcmBarcode> barcodeList = pcmBarcodeMapper.pushBarcode(paramMap);
        logger.info("end pushBarcodeToSAPERP(),return:" + barcodeList);
        return barcodeList;
    }

    /**
     * MDERP条码从门店ERP和电商ERP上传到主数据ERP
     *
     * @param pushSupplierlist
     * @Methods Name getSupplierBarCodeFromEfuture
     * @Create In 2015年7月13日 By yedong
     */
    @Override
    @Transactional
    public void importSupplierBarCodeFromEfuture(SupplierBarCodeFromEfutureDto pushSupplierDto)
            throws BleException {
        logger.info("start importSupplierBarCodeFromEfuture(),param:" + pushSupplierDto.toString());

        String actionCode = pushSupplierDto.getActionCode();
        if (StringUtils.isNotEmpty(actionCode)) {
            if (Constants.A.equals(actionCode)) {
                getSupplierBarCodeFromEfuture(pushSupplierDto);
            }

            if (Constants.D.equals(actionCode)) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("barcode", pushSupplierDto.getSbarcode());// 条码
                paramMap.put("storeCode", pushSupplierDto.getStoreCode());// 门店
                paramMap.put("shoppeProSid", pushSupplierDto.getMatnr()); // 专柜商品编码
                List<PcmBarcode> barcodeList = pcmBarcodeMapper.selectListByParam(paramMap);
                if (barcodeList != null && barcodeList.size() == 1) {
                    PcmBarcode barcode = barcodeList.get(0);
                    PcmBarcodeHis barcodeHis = new PcmBarcodeHis();
                    barcodeHis.setSid(barcode.getSid());
                    barcodeHis.setBarcode(barcode.getBarcode());
                    barcodeHis.setBarcodeName(barcode.getBarcodeName());
                    barcodeHis.setBarcodeUnit(barcode.getBarcodeUnit());
                    barcodeHis.setBarcodeRate(barcode.getBarcodeRate());
                    barcodeHis.setCodeType(barcode.getCodeType());
                    barcodeHis.setShoppeProSid(barcode.getShoppeProSid());
                    barcodeHis.setStoreCode(barcode.getStoreCode());
                    barcodeHis.setProductCode(barcode.getProductCode());
                    barcodeHis.setShoppeCode(barcode.getShoppeCode());
                    barcodeHis.setSupplyCode(barcode.getSupplyCode());
                    barcodeHis.setSaleMount(barcode.getSaleMount());
                    barcodeHis.setSalePrice(barcode.getSalePrice());
                    barcodeHis.setOriginLand(barcode.getOriginLand());

                    int count = barcodeHisMapper.insertSelective(barcodeHis);// 迁移到历史记录表
                    if (count == 1) {
                        Map<String, Object> delMap = new HashMap<String, Object>();
                        delMap.put("sid", barcode.getSid());
                        delMap.put("storeCode", barcode.getStoreCode());
                        pcmBarcodeMapper.deleteByPrimaryKeyAndStoreCode(delMap);// 删除条码
                    } else {
                        throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
                    }
                } else if (barcodeList.size() == 0) {
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.BARCODE_IS_NO_EXIST.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.BARCODE_IS_NO_EXIST.getMemo());
                } else {
                    throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
                }
            }

            if (Constants.U.equals(actionCode)) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("barcode", pushSupplierDto.getSbarcode());// 条码
                paramMap.put("storeCode", pushSupplierDto.getStoreCode());// 门店
                paramMap.put("shoppeProSid", pushSupplierDto.getMatnr()); // 专柜商品编码
                List<PcmBarcode> barcodeList = pcmBarcodeMapper.selectListByParam(paramMap);
                if (barcodeList != null && barcodeList.size() == 1) {
                    PcmBarcode barcode = barcodeList.get(0);
                    PcmBarcode record = new PcmBarcode();
                    record.setSid(barcode.getSid());
                    //可以被修改的值
                    record.setBarcodeName(pushSupplierDto.getSbarcodeName());
                    record.setBarcodeUnit(pushSupplierDto.getSaleUnit());
                    String saleAmount = pushSupplierDto.getSaleAmount();
                    if (StringUtils.isNotEmpty(saleAmount)) {
                        record.setSaleMount(new BigDecimal(saleAmount));
                    }
                    BigDecimal salePrice = pushSupplierDto.getSalePrice();
                    if (salePrice != null) {
                        record.setSalePrice(salePrice);
                    }
                    record.setOriginLand(barcode.getOriginLand());
                    //添加修改条件(where)门店号，分库路由使用
                    record.setStoreCode(barcode.getStoreCode());
                    int count = pcmBarcodeMapper.updateByPrimaryKeySelective(record);// 修改
                    if (count != 1) {
                        throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
                    }
                } else if (barcodeList.size() == 0) {
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.BARCODE_IS_NO_EXIST.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.BARCODE_IS_NO_EXIST.getMemo());
                } else {
                    throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
                }
            }

        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.ACTIONCODE_IS_EMPTY.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.ACTIONCODE_IS_EMPTY.getMemo());
        }

        logger.info("end importSupplierBarCodeFromEfuture()");
    }

    /**
     * MDERP条码从门店ERP和电商ERP上传到主数据ERP
     *
     * @param pushSupplierlist
     * @Methods Name getSupplierBarCodeFromEfuture
     * @Create In 2015年7月13日 By yedong
     */
    @Transactional
    public void getSupplierBarCodeFromEfuture(SupplierBarCodeFromEfutureDto pushSupplierDto)
            throws BleException {
        logger.info("start getSupplierBarCodeFromEfuture(),param:" + pushSupplierDto.toString());
        // if (pushSupplierDto.getActionCode().equals(Constants.A)) {
        // Map<String, Object> paramMap = new HashMap<String, Object>();
        // if(Constants.PCMBARCODE_CODE_TYPE_ZE_STR.equals(pushSupplierDto.getSbarcodeType())){
        // paramMap.put("codeType", 1);
        // }else
        // if(Constants.PCMBARCODE_CODE_TYPE_IE_STR.equals(pushSupplierDto.getSbarcodeType())){
        // paramMap.put("codeType", 0);
        // }
        // paramMap.put("barcode", pushSupplierDto.getSbarcode());
        // paramMap.put("productCode", pushSupplierDto.getMatnr());
        // Integer sbarcode = pcmBarcodeMapper.getCountByParam(paramMap);
        // if (sbarcode == Constants.PUBLIC_0) {
        // PcmBarcode record = new PcmBarcode();
        // record.setStoreCode(pushSupplierDto.getStoreCode());
        // record.setProductCode(pushSupplierDto.getMatnr());
        // record.setShoppeProSid(pushSupplierDto.getMatnr());
        // record.setSupplyCode(pushSupplierDto.getLifnr());
        // record.setShoppeCode(pushSupplierDto.getCounterCode());
        // record.setBarcode(pushSupplierDto.getSbarcode());
        // if(Constants.PCMBARCODE_CODE_TYPE_ZE_STR.equals(pushSupplierDto.getSbarcodeType())){
        // record.setCodeType(1);
        // }else
        // if(Constants.PCMBARCODE_CODE_TYPE_IE_STR.equals(pushSupplierDto.getSbarcodeType())){
        // record.setCodeType(0);
        // }
        // record.setBarcodeName(pushSupplierDto.getSbarcode());
        // record.setBarcodeUnit(pushSupplierDto.getSaleUnit());
        // if (pushSupplierDto.getSaleAmount() == null) {
        // record.setSaleMount(new BigDecimal("1"));
        // } else {
        // record.setSaleMount(new BigDecimal(pushSupplierDto.getSaleAmount()));
        // }
        // record.setSalePrice(pushSupplierDto.getSalePrice());
        // pcmBarcodeMapper.insertSelective(record);
        // } else {
        // throw new BleException(
        // ComErrorCodeConstants.ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
        // ComErrorCodeConstants.ErrorCode.UPDATE_HAVE_ERROR.getMemo());
        // }
        // } else if (pushSupplierDto.getActionCode().equals(Constants.U)) {
        // Map<String, Object> paramMap = new HashMap<String, Object>();
        // paramMap.put("codeType", 0);
        // paramMap.put("barcode", pushSupplierDto.getSbarcode());
        // paramMap.put("productCode", pushSupplierDto.getMatnr());
        // List<PcmBarcode> sbarcode =
        // pcmBarcodeMapper.selectListByParam(paramMap);
        // if (sbarcode.size() == Constants.PUBLIC_1) {
        // PcmBarcode record = new PcmBarcode();
        // record.setSid(sbarcode.get(0).getSid());
        // record.setBarcode(pushSupplierDto.getSbarcode());
        // record.setBarcodeName(pushSupplierDto.getSbarcodeName());
        // pcmBarcodeMapper.updateByPrimaryKeySelective(record);
        // } else {
        // throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
        // }
        // }

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
        } else if (Constants.PCMBARCODE_CODE_TYPE_SE_STR.equals(pushSupplierDto.getSbarcodeType())) {
            record.setCodeType(1);
        }
        record.setBarcodeName(pushSupplierDto.getSbarcode());
        record.setBarcodeUnit(pushSupplierDto.getSaleUnit());
        if (pushSupplierDto.getSaleAmount() == null) {
            record.setSaleMount(new BigDecimal("1"));
        } else {
            record.setSaleMount(new BigDecimal(pushSupplierDto.getSaleAmount()));
        }
        record.setSalePrice(pushSupplierDto.getSalePrice());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("barcode", pushSupplierDto.getSbarcode());// 条码
        paramMap.put("storeCode", pushSupplierDto.getStoreCode());// 门店
        Integer sbarcode = pcmBarcodeMapper.getCountByParam(paramMap);
        if (sbarcode == Constants.PUBLIC_0) { // 根据paramMap查询 结果为0则添加条码
            pcmBarcodeMapper.insertSelective(record);
        } else { // 结果不为0加入专柜商品编码继续查询
            paramMap.put("shoppeProSid", pushSupplierDto.getMatnr()); // 专柜商品编码
            Integer count = pcmBarcodeMapper.getCountByParam(paramMap);
            if (count == Constants.PUBLIC_1) {
                List<PcmBarcode> sbarcodes = pcmBarcodeMapper.selectListByParam(paramMap);// 查询得到已有条码SID
                record.setSid(sbarcodes.get(0).getSid());// 将已有SID赋值到接收到的参数实体
                pcmBarcodeMapper.updateByPrimaryKeySelective(record);// 修改已有条码
            } else if (count == Constants.PUBLIC_0) { // 查询结果为0 返回已有该数据
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.BARCODE_IS_EXIST.getMemo());
            } else {
                throw new BleException(ErrorCode.DATA_OPER_ERROR.getMemo());
            }
        }
        logger.info("end getSupplierBarCodeFromEfuture()");
    }
}
