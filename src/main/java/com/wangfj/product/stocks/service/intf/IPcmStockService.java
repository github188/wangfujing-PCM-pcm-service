package com.wangfj.product.stocks.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.stocks.domain.vo.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 库存管理
 *
 * @Class Name IPcmStockService
 * @Author yedong
 * @Create In 2015年7月23日
 */
public interface IPcmStockService {
    /**
     * 库存添加一条数据或修改（全量）
     *
     * @param record
     * @return Integer
     * @Methods Name saveOrUpdate
     * @Create In 2015年7月23日 By yedong
     */
    public Integer saveOrUpdate(PcmStockDto record);

    /**
     * 查询可售库存数（总库存-已锁定库存）
     *
     * @param record
     * @return Integer
     * @Methods Name findStockCountFromPcm
     * @Create In 2015年7月23日 By yedong
     */
    public int findStockCountFromPcm(String key, PcmStockDto record);

    /**
     * 查询总库存数
     *
     * @param record
     * @return Integer
     * @Methods Name selectStockCountFromPcm
     * @Create In 2015年7月28日 By yedong
     */
    public int selectStockCountFromPcm(PcmStockDto record);

    /**
     * 根据专柜商品编码和渠道查询库位信息
     *
     * @param record
     * @return List<PcmStock>
     * @Methods Name selectShoppeProStockInfo
     * @Create In 2015年9月16日 By kongqf
     */
    public List<PcmStockInfoDto> selectShoppeProStockInfo(PcmStockDto record);

    /**
     * 库存导入
     *
     * @param paraList
     * @return List<PcmStockDto>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @Methods Name findStockImportFromPcm
     * @Create In 2015年7月27日 By yedong
     */
    public PcmStockDto findStockImportFromPcm(PcmStockDto dto);

    /**
     * 库存锁定
     *
     * @param dto
     * @return StockResultDto
     * @Methods Name findStockLockFromPcm
     * @Create In 2015年7月28日 By yedong
     */
    public String findStockLockFromPcm(StockProCountListDto dto);

    /**
     * 减库
     *
     * @param dto
     * @return StockResultDto
     * @Methods Name findStockReduceFromPcm
     * @Create In 2015年7月28日 By yedong
     */
    public String findStockReduceFromPcm(StockProCountListDto dto);

    /**
     * 解锁
     *
     * @param dto
     * @return StockResultDto
     * @Methods Name findStockUnLockFromPcm
     * @Create In 2015年7月28日 By yedong
     */
    public String findStockUnLockFromPcm(StockProCountListDto dto);

    /**
     * 还库
     *
     * @param dto
     * @return StockResultDto
     * @Methods Name findStockRefundFromPcm
     * @Create In 2015年7月21日 By yedong
     */
    public String findStockRefundFromPcm(StockProCountListDto dto);

    /**
     * 调入、调出、借出、归还
     *
     * @param dto
     * @return StockResultDto
     * @throws Exception
     * @Methods Name findStockTransferFromPcm
     * @Create In 2015年7月30日 By yedong
     */
    public String findStockTransferFromPcm(StockProCountListDto dto);

    /**
     * 类型转换
     *
     * @param paramList
     * @return StockResultDto
     * @throws Exception
     * @Methods Name findStockTypeUpdateFromPcm
     * @Create In 2015年7月30日 By yedong
     */
    public String findStockTypeUpdateFromPcm(List<Map<String, Object>> paramList) throws Exception;

    public String findStockTypeUpdateFromPcmV2(List<PcmStockChangeDto> pcmStockChangeDtos);

    /**
     * 批量查询库存
     *
     * @param listDto
     * @return List<PcmStockDto>
     * @Methods Name findStockBigCountFromPcm
     * @Create In 2015年8月6日 By yedong
     */
    public List<PcmStockDto> findStockBigCountFromPcm(List<PcmStockDto> listDto);

    /**
     * 库存清零
     *
     * @param shoppeProSid void
     * @Methods Name stockEmpty
     * @Create In 2015年8月11日 By yedong
     */
    public StockResultDto stockEmpty(String shoppeProSid);

    /**
     * 加减库操作
     *
     * @param dto
     * @return StockResultDto
     * @Methods Name findInsertAndReduceFromPcm
     * @Create In 2015年8月14日 By yedong
     */
    // public StockResultDto findInsertAndReduceFromPcm(StockProCountListDto
    // dto);
    public StockResultDto findInsertAndReduceFromPcmV2(StockProCountListDto dto);

    public StockResultDto findInsertAndReduceFromPcmByOffLine(StockProCountListDto dto);

    /**
     * 专柜商品库存信息查询
     *
     * @param queryProductStockInfoDto
     * @return Page<SelectProductStockInfoDto>
     * @Methods Name queryProductStockInfo
     * @Create In 2015年9月28日 By kongqf
     */
    public Page<SelectProductStockInfoDto> queryProductStockInfo(
            QueryProductStockInfoDto queryProductStockInfoDto);

    /**
     * 专柜商品库存信息查询
     *
     * @param queryProductStockInfoDto
     * @return Page<SelectProductStockInfoDto>
     * @Methods Name queryProductStockInfoOptimization
     * @Create In 2016年3月26日 By kongqf
     */
    public Page<SelectProductStockInfoDto> queryProductStockInfoOptimization(QueryProductStockInfoDto queryProductStockInfoDto);

    /**
     * 根据一组专柜商品编码查询库存
     *
     * @param dto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name findStockInfoByProductPara
     * @Create In 2016年4月13日 By wangxuan
     */
    List<SelectProductStockInfoDto> findStockInfoByProductPara(QueryProductStockInfoDto dto);

    /**
     * 专柜商品库存信息查询导出Excel 总数量
     *
     * @param queryProductStockInfoDto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name queryProductStockInfoExcelCount
     * @Create In 2016年04月05日 By wangxuan
     */
    public Page<SelectProductStockInfoDto> queryProductStockInfoExcelCount(QueryProductStockInfoDto queryProductStockInfoDto);

    /**
     * 专柜商品库存信息查询导出Excel
     *
     * @param queryProductStockInfoDto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name queryProductStockInfoExcel
     * @Create In 2015年10月8日 By kongqf
     */
    public List<SelectProductStockInfoDto> queryProductStockInfoExcel(
            QueryProductStockInfoDto queryProductStockInfoDto);

    /**
     * 查询sku下库存数
     *
     * @param dto
     * @return PcmProductStockInfoDto
     * @Methods Name SelecSkuStockSumBySku
     * @Create In 2015年11月25日 By kongqf
     */
    public PcmProductStockInfoDto SelectSkuStockSumBySku(PcmProductStockInfoDto dto);

    /**
     * 库存变动修改SKU库存状态
     *
     * @Methods Name updateSKUStatus
     * @Create In 2015年11月30日 By kongqf void
     */
    public void updateSKUStatus(PcmProductStockInfoDto dto);

    /**
     * 导入库存更新缓存
     *
     * @param shoppeProSid
     * @param channelSid   void
     * @Methods Name updateImportStockCache
     * @Create In 2016年3月10日 By kongqf
     */
    public void updateImportStockCache(String shoppeProSid, String channelSid);
    
    /**
     * 根据参数查询下发给wcs的库存数据信息
     * @param param
     * @return
     */
    public List<PcmStockWCSDto> selectProStockPushByParam(Map<String, Object> param);
}
