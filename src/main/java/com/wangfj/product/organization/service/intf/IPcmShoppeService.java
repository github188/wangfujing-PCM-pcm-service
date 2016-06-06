package com.wangfj.product.organization.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PcmGetShoppeDto;
import com.wangfj.product.organization.domain.vo.PcmPadShoppeQueryDto;
import com.wangfj.product.organization.domain.vo.PcmPadShoppeResultDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeAUDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeDataDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeErpDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmShoppeDto;

/**
 * 专柜管理 Service
 *
 * @Class Name IPcmShoppeService
 * @Author yedong
 * @Create In 2015年7月14日
 */
public interface IPcmShoppeService {
    /**
     * MDERP专柜主数据分发
     *
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PushCounterDto>
     * @Methods Name publishShoppeFromPCM
     * @Create In 2015年7月14日 By yedong
     */
    public List<Map<String, Object>> findShoppeByParamFromPcm(Map<String, Object> map);

    public Integer getCountByParam(Map<String, Object> paramMap);

    public PcmGetShoppeDto selectShoppeSid(Long sid);

    /**
     * 分页条件查询专柜信息
     *
     * @param dto
     * @param pageorg
     * @return Page<HashMap<String,Object>>
     * @Create In 2015年7月30日 By wuxiong
     */
    public Page<HashMap<String, Object>> selectPageShoppe(PcmGetShoppeDto dto,
                                                          Page<PcmGetShoppeDto> pageorg);

    /**
     * 专柜状态变更
     *
     * @param dto
     * @return
     */
    int updateShoppeStatus(PcmGetShoppeDto dto);

    /**
     * 分页查询
     *
     * @param selectShoppeDto
     * @return Page<PcmShoppeResultDto>
     * @Methods Name findPageShoppe
     * @Create In 2015-8-24 By niuzhifan
     */
    Page<PcmShoppeResultDto> findPageShoppe(SelectPcmShoppeDto selectShoppeDto);

    /**
     * 查询专柜
     *
     * @param selectShoppeDto
     * @return List<PcmShoppeResultDto>
     * @Methods Name findListShoppeData
     * @Create In 2015-8-25 By niuzhifan
     */
    List<PcmShoppeResultDto> findListShoppeData(SelectPcmShoppeDto selectShoppeDto);

    /**
     * 专柜导入终端--由主数据获取专柜信息
     *
     * @param paramMap
     * @return List<PcmShoppeDataDto>
     * @Methods Name getShoppeData
     * @Create In 2015-8-25 By niuzhifan
     */
    List<PcmShoppeDataDto> getShoppeData(Map<String, Object> paramMap);

    /**
     * 移动工作台调用主数据获取专柜信息
     *
     * @param dto
     * @return
     * @Create 2015-8-30 niuzhifan
     */
    List<PcmShoppeErpDto> getShoppeInfo(PcmGetShoppeDto dto);

    // int updateShoppeManagetype(Map)

    /**
     * 查询专柜List
     *
     * @param dto
     * @return List<PcmShoppeResultDto>
     * @Methods Name findShoppeList
     * @Create In 2015-10-16 By wangxuan
     */
    List<PcmShoppeResultDto> findShoppeList(SelectPcmShoppeDto dto);

    List<Map<String, Object>> pushShoppeData(Map<String, Object> para);

    String generateShoppeCode(Map<String, Object> paramMap);

    Map<String, Object> addShoppe(PcmShoppeAUDto dto);

    Integer modifyShoppe(PcmShoppeAUDto dto);

    List<Map<String, Object>> pushShoppeToEBusiness(Map<String, Object> para);

    /**
     * PAD
     *
     * @param dto
     * @return List<PcmPadShoppeResultDto>
     * @Methods Name findShopAndShoppeByParam
     * @Create In 2015-12-2 By wangxuan
     */
    List<PcmPadShoppeResultDto> findShopAndShoppeByParam(PcmPadShoppeQueryDto dto);

    /**
     * 根据门店和供应商查询专柜（页面添加专柜商品）
     *
     * @param dto
     * @return List<PcmShoppeResultDto>
     * @Methods Name findListShoppeForAddShoppeProduct
     * @Create In 2015-12-21 By wangxuan
     */
    public List<PcmShoppeResultDto> findListShoppeForAddShoppeProduct(SelectPcmShoppeDto dto);

    /**
     * 根据门店和供应商查询专柜（SAPERP导入商品时查询）
     *
     * @param dto
     * @return
     */
    PcmShoppeResultDto findShoppeForSAPERPImport(SelectPcmShoppeDto dto);

    /**
     * 电商专柜编码生成
     *
     * @param paramMap
     * @return String
     * @Methods Name generateEShoppeCode
     * @Create In 2015-12-22 By wangxuan
     */
    @Deprecated
    String generateEShoppeCode(Map<String, Object> paramMap);

    /**
     * 电商专柜编码生成
     *
     * @param paramMap
     * @return String
     * @Methods Name generateEBusinessShoppeCode
     * @Create In 2015-12-25 By wangxuan
     */
    String generateEBusinessShoppeCode(Map<String, Object> paramMap);
}
