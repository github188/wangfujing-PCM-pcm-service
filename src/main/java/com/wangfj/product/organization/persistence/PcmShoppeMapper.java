package com.wangfj.product.organization.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.vo.PcmContractLogDto;
import com.wangfj.product.maindata.domain.vo.ShoppeErpDto;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.domain.vo.PcmGetShoppeDto;
import com.wangfj.product.organization.domain.vo.PcmInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmInfoResultDto;
import com.wangfj.product.organization.domain.vo.PcmPadShoppeQueryDto;
import com.wangfj.product.organization.domain.vo.PcmPadShoppeResultDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeDataDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeErpDto;
import com.wangfj.product.organization.domain.vo.PcmShoppeResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmShoppeDto;

/**
 * @Class Name PcmShoppeMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmShoppeMapper extends BaseMapper<PcmShoppe> {
    /**
     * 删除
     *
     * @param sid
     * @return int
     * @Methods Name deleteByPrimaryKey
     * @Create In 2015年7月3日 By yedong
     */
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmShoppe record);

    /**
     * 添加
     *
     * @param record
     * @return int
     * @Methods Name insertSelective
     * @Create In 2015年7月3日 By yedong
     */
    int insertSelective(PcmShoppe record);

    /**
     * 修改
     *
     * @param record
     * @return int
     * @Methods Name updateByPrimaryKeySelective
     * @Create In 2015年7月3日 By yedong
     */
    int updateByPrimaryKeySelective(PcmShoppe record);

    int updateByPrimaryKey(PcmShoppe record);

    /**
     * 专柜主数据条件查询
     *
     * @return List<PushCounterDto>
     * @Methods Name pushCounter
     * @Create In 2015年7月8日 By yedong
     */
    List<Map<String, Object>> findShoppeByParamFromPcm(Map<String, Object> map);

    List<Map<String, Object>> pushShoppeData(Map<String, Object> paramMap);

    List<Map<String, Object>> pushShoppeToEBusiness(Map<String, Object> paramMap);

    /**
     * 根据sid查询专柜信息
     *
     * @param sid
     * @return PcmGetShoppeDto
     * @Methods Name getShoppeSid
     * @Create In 2015年7月29日 By wuxiong
     */
    PcmGetShoppeDto getShoppeSid(Long sid);

    PcmGetShoppeDto getPcmGetShoppeDtoByShoppeSid(Long sid);

    /**
     * 按条件分页查询专柜
     *
     * @param map
     * @return List<HashMap<String,Object>>
     * @Methods Name findShoppeByParam
     * @Create In 2015年7月30日 By wuxiong
     */
    List<HashMap<String, Object>> findShoppeByParam(Map<String, Object> map);

    Integer getCountShoppes(Map<String, Object> map);

    // 判重条件
    Integer getShoppeCount(Map<String, Object> map);

    Integer getShoppesCount(Map<String, Object> map);

    PcmShoppe selectByPrimaryKey(Long sid);

    /**
     * 判重
     *
     * @param shoppe
     * @return
     */
    Integer getCountByShoppe(PcmShoppe shoppe);

    /**
     * 根据专柜sid查找门店编码（关联组织机构表）
     */
    String getShopCodeByShoppeSid(PcmShoppe shoppe);

    /**
     * 根据门店编码查找该门店下专柜的最大sid
     */
    String getShoppeSidByShopCode(Map<String, Object> map);

    /**
     * 根据门店sid查找该门店下专柜的最大sid
     */
    String getMaxShoppeSidByShop(Map<String, Object> paramMap);

    /**
     * 根据电商门店编码及专柜编码规则查询某种类型下的电商专柜的最大编码
     *
     * @param paramMap
     * @return Map<String,Object>
     * @Methods Name getMaxEShoppeCode
     * @Create In 2015-12-22 By wangxuan
     */
    @Deprecated
    Map<String, Object> getMaxEShoppeCode(Map<String, Object> paramMap);

    /**
     * 根据电商门店编码查询电商专柜的最大编码
     *
     * @param paramMap
     * @return Map<String,Object>
     * @Methods Name getMaxEBusinessShoppeCode
     * @Create In 2015-12-25 By wangxuan
     */
    Map<String, Object> getMaxEBusinessShoppeCode(Map<String, Object> paramMap);

    /**
     * 分页查询总数
     *
     * @param selectShoppeDto
     * @return Integer
     * @Methods Name getPageCountByPara
     * @Create In 2015-8-24 By niuzhifan
     */
    Integer getPageCountByPara(SelectPcmShoppeDto selectShoppeDto);

    /**
     * 分页查询
     *
     * @param selectShoppeDto
     * @return List<PcmShoppeResultDto>
     * @Methods Name selectPageByPara
     * @Create In 2015-8-24 By niuzhifan
     */
    List<PcmShoppeResultDto> selectPageByPara(SelectPcmShoppeDto selectShoppeDto);

    /**
     * 分页查询总数关联供应商
     *
     * @param selectShoppeDto
     * @return
     */
    Integer findPageCountByPara(SelectPcmShoppeDto selectShoppeDto);

    /**
     * 分页查询关联供应商
     *
     * @param selectShoppeDto
     * @return
     */
    List<PcmShoppeResultDto> findPageByPara(SelectPcmShoppeDto selectShoppeDto);

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
     * @param shoppe
     * @return
     */
    List<PcmShoppeErpDto> getShoppeInfo(PcmShoppe shoppe);

    /**
     * 根据专柜更改渠道可售状态
     *
     * @param map
     * @Create IN 2015-9-14 NZF
     */
    int getStatusByShoppe(Map<String, Object> map);

    /**
     * 查询专柜List
     *
     * @param dto
     * @return List<PcmShoppeResultDto>
     * @Methods Name selectShoppeListByParam
     * @Create In 2015-10-16 By wangxuan
     */
    List<PcmShoppeResultDto> selectShoppeListByParam(SelectPcmShoppeDto dto);

    List<ShoppeErpDto> selectShoppeListForPis(PcmContractLogDto dto);

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
    List<PcmShoppeResultDto> findListShoppeForAddShoppeProduct(SelectPcmShoppeDto dto);

    /**
     * 根据门店和供应商查询专柜（SAPERP导入商品时查询）
     *
     * @param dto
     * @return
     */
    List<PcmShoppeResultDto> findShoppeForSAPERPImport(SelectPcmShoppeDto dto);

    /**
     * CMS 门店下对应的专柜
     *
     * @param dto
     * @return List<PcmInfoResultDto>
     * @Methods Name findShoppeByShopParamToCMS
     * @Create In 2015-12-28 By wangxuan
     */
    List<PcmInfoResultDto> findShoppeByShopParamToCMS(PcmInfoQueryDto dto);

}