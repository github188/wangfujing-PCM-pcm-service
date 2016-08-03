package com.wangfj.product.stocks.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmStock;
import com.wangfj.product.stocks.domain.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Class Name PcmStockMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmStockMapper extends BaseMapper<PcmStock> {
    /**
     * 删除
     *
     * @param sid
     * @return int
     * @Methods Name deleteByPrimaryKey
     * @Create In 2015年7月3日 By yedong
     */
    int deleteByPrimaryKey(Long sid);

    /**
     * 添加
     *
     * @param record
     * @return int
     * @Methods Name insertSelective
     * @Create In 2015年7月3日 By yedong
     */
    int insertSelective(PcmStock record);

    /**
     * 修改
     *
     * @param record
     * @return int
     * @Methods Name updateByPrimaryKeySelective
     * @Create In 2015年7月3日 By yedong
     */
    int updateByPrimaryKeySelective(PcmStock record);

    /**
     * 根据专柜商品SID，查询库存数量
     *
     * @param sid
     * @return int
     * @Methods Name selectProSumByPrimaryKey
     * @Create In 2015年7月7日 By yedong
     */
    Integer selectProSumByPrimaryKey(PcmStockDto record);

    /**
     * 添加or修改（全量导入）
     *
     * @param record
     * @return int
     * @Methods Name saveOrUpdate
     * @Create In 2015年7月7日 By yedong
     */
    int saveOrUpdate(PcmStockDto record);

    /**
     * 增加OR减少
     *
     * @param record
     * @return int
     * @Methods Name addOrReduce
     * @Create In 2015年7月20日 By yedong
     */
    int addOrReduce(PcmStockDto record);

    /**
     * 增量导入
     *
     * @param record
     * @return int
     * @Methods Name stockImport
     * @Create In 2015年7月27日 By yedong
     */
    int stockImport(PcmStockDto record);

    /**
     * 根据专柜商品+库存类型查SID
     *
     * @param ShoppeProSid
     * @return Integer
     * @Methods Name selectSidByShoppeProSid
     * @Create In 2015年7月21日 By yedong
     */
    Integer selectSidByShoppeProSid(PcmStockDto record);

    /**
     * 修改数量
     *
     * @param record
     * @return Integer
     * @Methods Name proSumUpdateSelective
     * @Create In 2015年7月30日 By yedong
     */
    Integer proSumUpdateSelective(PcmStockDto record);

    /**
     * 修改库存
     *
     * @param record
     * @return Integer
     * @Methods Name updateByParamSelective
     * @Create In 2015-8-3 By liuhp
     */
    Integer updateByParamSelective(PcmStock record);

    /**
     * 查询可售数量
     *
     * @return List<Map<String, Object>>
     * @Methods Name selectSaleCount
     * @Create In 2015年8月4日 By yedong
     */
    PcmStock selectSaleCount(PcmStockDto record);

    /**
     * 查询库存总数
     *
     * @param record
     * @return PcmStock
     * @Methods Name selectStockCount
     * @Create In 2015年8月27日 By kongqf
     */
    PcmStock selectStockCount(PcmStockDto record);

    /**
     * 根据专柜商品编码和渠道查询库位信息
     *
     * @param record
     * @return List<PcmStock>
     * @Methods Name selectShoppeProStockInfo
     * @Create In 2015年9月16日 By kongqf
     */
    List<PcmStockInfoDto> selectShoppeProStockInfo(PcmStockDto record);

    /**
     * 商品库存信息查询
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name SelecProductStockInfoByPara
     * @Create In 2015年9月28日 By kongqf
     */
    List<SelectProductStockInfoDto> SelecProductStockInfoByPara(QueryProductStockInfoDto dto);

    /**
     * 商品库存查询导出
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name SelecProductStockInfoExcelByPara
     * @Create In 2015年10月8日 By kongqf
     */
    List<SelectProductStockInfoDto> SelecProductStockInfoExcelByPara(QueryProductStockInfoDto dto);

    /**
     * 商品库存信息总数条目数
     *
     * @param dto
     * @return Integer
     * @Methods Name SelecProductStockInfoCountByPara
     * @Create In 2015年9月28日 By kongqf
     */
    Integer SelecProductStockInfoCountByPara(QueryProductStockInfoDto dto);

    /**
     * 查询 sku下的库存数
     *
     * @param dto
     * @return PcmProductStockInfoDto
     * @Methods Name SelecSkuStockSumBySku
     * @Create In 2015年11月25日 By kongqf
     */
    PcmProductStockInfoDto SelectSkuStockSumBySku(PcmProductStockInfoDto dto);

    /**
     * 商品库存信息总数条目数
     *
     * @param dto
     * @return Integer
     * @Methods Name findProductStockInfoCountByPara
     * @Create In 2016年3月26日 By kongqf
     */
    Integer findProductStockInfoCountByPara(QueryProductStockInfoDto dto);

    /**
     * 商品信息查询 拆分优化
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name findProductInfoByPara
     * @Create In 2016年04月11日 By wangxuan
     */
    List<SelectProductStockInfoDto> findProductInfoByPara(QueryProductStockInfoDto dto);

    /**
     * 商品库存信息查询 拆分优化
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name findProductInfoByPara
     * @Create In 2016年04月11日 By wangxuan
     */
    List<SelectProductStockInfoDto> findStockInfoByProductPara(QueryProductStockInfoDto dto);

    /**
     * 商品库存信息查询
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name findProductStockInfoByPara
     * @Create In 2016年3月26日 By wangxuan
     */
    List<SelectProductStockInfoDto> findProductStockInfoByPara(QueryProductStockInfoDto dto);
    
    /**
     * 根据参数查询下发给wcs的库存数据信息
     * @param param
     * @return
     */
    List<PcmStockWCSDto> selectProStockPushByParam(Map<String, Object> param);
    
    /**
     * 根据参数查询edi关联商品可售库存（分页）
     */
    List<PcmStock> selectProStockByParam(Map<String, Object> param);

    /**
     * 根据商品编码和渠道批量查询下发给wcs的库存数据信息
     * @param param
     * @return
     */
	List<PcmStockWCSDto> selectProStockPushByPros(Map<String, Object> param);

}