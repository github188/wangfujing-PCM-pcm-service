package com.wangfj.product.price.persistence;

import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.domain.vo.*;

import java.util.List;
import java.util.Map;

public interface PcmPriceMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PcmPrice record);

    int insertSelective(PcmPrice record);

    PcmPrice selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmPrice record);

    int updateByPrimaryKey(PcmPrice record);

    /**
     * 根据专柜商品编码查询
     *
     * @param pcmPrice
     * @return Map<String,Object>
     * @Methods Name queryPriceInfoByShoppeProCode
     * @Create In 2015年7月29日 By kongqf
     */
    Map<String, Object> queryPriceInfoByShoppeProCode(PcmPrice pcmPrice);

    /**
     * 根据专柜商品编码查询变价日志
     *
     * @param map
     * @return HashMap<String,Object>
     * @Methods Name selectPriceChangeLogByShoppeProCode
     * @Create In 2015年8月3日 By kongqf
     */
    List<Map<String, Object>> selectPriceChangeLogByShoppeProCode(Map<String, Object> map);

    /**
     * 查询需要变价的价格信息
     *
     * @param queryPriceInfoDto
     * @return List<PcmPrice>
     * @Methods Name selectActivePriceInfo
     * @Create In 2015年8月12日 By kongqf
     */
    PcmPrice selectActivePriceInfo(QueryPriceInfoDto queryPriceInfoDto);

    /**
     * 查询需要变价的区间价格信息
     *
     * @param queryPriceInfoDto
     * @return List<PcmPrice>
     * @Methods Name selectMiddleActivePriceInfo
     * @Create In 2015年8月15日 By kongqf
     */
    List<PcmPrice> selectMiddleActivePriceInfo(QueryPriceInfoDto queryPriceInfoDto);

    /**
     * 删除变价信息
     *
     * @param pcmPriceChangeDto
     * @return int
     * @Methods Name deletePriceInfoByPara
     * @Create In 2015年8月15日 By kongqf
     */
    int deletePriceInfoByPara(PcmPriceChangeDto pcmPriceChangeDto);

    /**
     * 查询单个专柜商品编码价格
     *
     * @param priceDto
     * @return Map<String,Object>
     * @Methods Name queryPriceInfoByPara
     * @Create In 2015年7月28日 By kongqf
     */
    SelectPriceDto queryPriceInfoByPara(QueryPriceDto queryPriceDto);

    /**
     * 查询无效价格信息
     *
     * @return List<PcmPrice>
     * @Methods Name queryExpirePriceInfoList
     * @Create In 2015年8月10日 By kongqf
     */
    List<PcmPrice> queryExpirePriceInfoList();

    /**
     * 根据管理分类、供应商、专柜查询专柜商品数量
     *
     * @param pcmPricePISDto
     * @return Integer
     * @Methods Name SelectShoppeProSidCountByPara
     * @Create In 2015年8月19日 By kongqf
     */
    Integer SelectShoppeProSidCountByPara(PcmPricePISDto pcmPricePISDto);

    /**
     * 根据管理分类、供应商、专柜查询专柜商品编码
     *
     * @param pcmPricePISDto
     * @return List<QueryShoppeProSidDto>
     * @Methods Name SelectShoppeProSidInfoByPara
     * @Create In 2015年8月19日 By kongqf
     */
    List<QueryShoppeProSidDto> SelectShoppeProSidInfoByPara(PcmPricePISDto pcmPricePISDto);

    /**
     * 根据变价号查询变价信息总数
     *
     * @param selectPcmPriceToERPPDto
     * @return List<PcmPriceToERPPDto>
     * @Methods Name SelecPricePushCountBychangeCode
     * @Create In 2015年8月21日 By kongqf
     */
    Integer SelecPricePushCountBychangeCode(SelectPcmPriceToERPPDto selectPcmPriceToERPPDto);

    /**
     * 根据变价号查询变价信息
     *
     * @param selectPcmPriceToERPPDto
     * @return List<PcmPriceToERPPDto>
     * @Methods Name SelecPricePushInfoBychangeCode
     * @Create In 2015年8月21日 By kongqf
     */
    List<PcmPriceToERPPDto> SelecPricePushInfoBychangeCode(
            SelectPcmPriceToERPPDto selectPcmPriceToERPPDto);

    /**
     * 商品价格信息(分页)
     *
     * @param queryProductPriceInfoDto
     * @return List<QueryProductPriceInfoDto>
     * @Methods Name SelecProductPriceInfoByPara
     * @Create In 2015年9月7日 By kongqf
     */
    List<SelectProductPriceInfoDto> SelecProductPriceInfoByPara(
            QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 查询商品价格信息数量(优化)
     *
     * @param queryProductPriceInfoDto
     * @return Integer
     * @Methods Name findProductPriceInfoCountByPara
     * @Create In 2016年03月26日 By kongqf
     */
    Integer findProductPriceInfoCountByPara(QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 商品价格信息(分页)(优化)
     *
     * @param queryProductPriceInfoDto
     * @return List<SelectProductPriceInfoDto>
     * @Methods Name findProductPriceInfoCountByPara
     * @Create In 2016年03月26日 By kongqf
     */
    List<SelectProductPriceInfoDto> findProductPriceInfoByPara(QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 根据一组专柜商品编码查询价格
     *
     * @param queryProductPriceInfoDto
     * @return List<SelectProductPriceInfoDto>
     * @Methods Name findPriceInfoByParaForShoppeProduct
     * @Create In 2016年04月12日 By wangxuan
     */
    List<SelectProductPriceInfoDto> findPriceInfoByParaForShoppeProduct(QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 查询商品价格信息数量
     *
     * @param queryProductPriceInfoDto
     * @return Integer
     * @Methods Name SelecProductPriceInfoCountByPara
     * @Create In 2015年9月7日 By kongqf
     */
    Integer SelecProductPriceInfoCountByPara(QueryProductPriceInfoDto queryProductPriceInfoDto);


    /**
     * 根据专柜商品查询价格信息
     *
     * @param q
     * @return List<SelectShoppeProPriceInfoDto>
     * @Methods Name queryShoppeProPriceInfoByShoppeProSid
     * @Create In 2015年9月11日 By kongqf
     */
    List<SelectShoppeProPriceInfoDto> queryShoppeProPriceInfoByShoppeProSid(
            QueryPriceDto queryPriceDto);

    /**
     * 查询当前商品价格信息（分页）
     *
     * @param pcmPricePISDto
     * @return List<SelectPriceDto>
     * @Methods Name queryCurrPriceInfo
     * @Create In 2015年9月17日 By kongqf
     */
    List<SelectPriceDto> queryCurrPriceInfo(PcmPricePISDto pcmPricePISDto);

    /**
     * 查询当前商品价格总数
     *
     * @param pcmPricePISDto
     * @return Integer
     * @Methods Name queryCurrPriceInfoCount
     * @Create In 2015年9月17日 By kongqf
     */
    Integer queryCurrPriceInfoCount(PcmPricePISDto pcmPricePISDto);

    /**
     * 商品价格信息Excel
     *
     * @param queryProductPriceInfoDto
     * @return List<QueryProductPriceInfoDto>
     * @Methods Name SelecProductPriceInfoByPara
     * @Create In 2015年9月7日 By kongqf
     */
    List<SelectProductPriceInfoDto> SelecProductPriceInfoExcel(
            QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 根据商品编号、专柜编号、门店校验商品是否有效
     *
     * @param paramMap
     * @return Map<String,Object>
     * @Methods Name validShoppeProInfo
     * @Create In 2015年12月21日 By kongqf
     */
    Map<String, Object> validShoppeProInfo(Map<String, Object> paramMap);

    /**
     * 根据开始时间结束时间，获取商品价格信息
     *
     * @param map
     * @return List<Map<String,Object>>
     * @Methods Name selectPriceByTimeFrame
     * @Create In 2016-1-6 By wangc
     */
    List<Map<String, Object>> selectPriceByTimeFrame(Map<String, Object> map);

    /**
     * 获取时间段内商品价格数量
     *
     * @param map
     * @return Integer
     * @Methods Name getCountByTimeFrame
     * @Create In 2016-1-6 By wangc
     */
    Integer getCountByTimeFrame(Map<String, Object> map);

    List<Map<String, Object>> selectProSidListByShopProCode(Map<String, Object> paramMap);
}
