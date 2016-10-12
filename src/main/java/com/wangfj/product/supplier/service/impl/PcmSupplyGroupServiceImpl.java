package com.wangfj.product.supplier.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.supplier.domain.entity.PcmSupplyGroup;
import com.wangfj.product.supplier.persistence.PcmSupplyGroupMapper;
import com.wangfj.product.supplier.service.intf.IPcmSupplyGroupService;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-10-12 0012.
 * 集团供应商
 */
@Service
public class PcmSupplyGroupServiceImpl implements IPcmSupplyGroupService {

    private static final Logger logger = LoggerFactory.getLogger(PcmSupplyGroupServiceImpl.class);

    @Autowired
    private PcmSupplyGroupMapper supplyGroupMapper;

    /**
     * 集团供应商从供应商平台上传到PCM
     *
     * @param supplyInfo
     * @param dtoMap
     * @return
     * @throws BleException
     */
    @Override
    @Transactional
    public Map<String, Object> uploadPcmSupplyGroupFromSupplierERP(PcmSupplyGroup supplyInfo, Map<String, Object> dtoMap) throws BleException {
        logger.info("start uploadPcmSupplyGroupFromSupplierERP(),param:" + supplyInfo.toString());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        String actionCode = dtoMap.get("actionCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bizCertificateNo", supplyInfo.getBizCertificateNo());
        paramMap.put("taxNumbe", supplyInfo.getTaxNumbe());
        //集团供应商判重：营业执照号+税务登记证
        List<PcmSupplyGroup> supplyInfoList = supplyGroupMapper.selectListByParam(paramMap);
        if (supplyInfoList != null) {
            if (supplyInfoList.size() == 0) {
                if (Constants.A.equals(actionCode)) {
                    supplyInfo.setStatus(Constants.PCMSUPPLYINFO_STATUS_Y_CODE);//添加时集团供应商状态设置为 正常：Y
                    result = supplyGroupMapper.insertSelective(supplyInfo);
                    resultMap.put("actionCode", Constants.A);
                } else if (Constants.U.equals(actionCode) || Constants.D.equals(actionCode)) {//修改、删除操作
                    throw new BleException(ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(), "集团供应商不存在！");
                } else {
                    throw new BleException(ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(), "集团供应商上传时ACTION_CODE不符合A/U/D！");
                }
            }

            if (supplyInfoList.size() == 1) {
                if (Constants.U.equals(actionCode)) {
                    //营业执照号+税务登记证+集团供应商编码不能修改
                    supplyInfo.setSid(supplyInfoList.get(0).getSid());
                    supplyInfo.setBizCertificateNo(null);
                    supplyInfo.setTaxNumbe(null);
                    supplyInfo.setSupplyCode(null);
                    result = supplyGroupMapper.updateByPrimaryKeySelective(supplyInfo);
                    resultMap.put("actionCode", Constants.U);
                } else if (Constants.D.equals(actionCode)) {
                    PcmSupplyGroup dSupplyGroup = new PcmSupplyGroup();
                    dSupplyGroup.setSid(supplyInfoList.get(0).getSid());
                    dSupplyGroup.setStatus(Constants.PCMSUPPLYINFO_STATUS_3_CODE);//删除将集团供应商状态设置为 淘汰：3
                    result = supplyGroupMapper.updateByPrimaryKeySelective(dSupplyGroup);
                    resultMap.put("actionCode", Constants.D);
                } else if (Constants.A.equals(actionCode)) {//添加操作
                    throw new BleException(ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(), "集团供应商已存在！");
                } else {
                    throw new BleException(ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(), "集团供应商上传时ACTION_CODE不符合A/U/D！");
                }
            }

            if (result == 1) {
                resultMap.put("sid", supplyInfo.getSid());
            }
        }
        resultMap.put("result", result);

        logger.info("end uploadPcmSupplyGroupFromSupplierERP(),return:" + resultMap.toString());
        return resultMap;
    }


}
