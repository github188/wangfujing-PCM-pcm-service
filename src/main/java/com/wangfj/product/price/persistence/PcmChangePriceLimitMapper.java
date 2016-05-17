package com.wangfj.product.price.persistence;

import java.util.List;

import com.wangfj.product.price.domain.entity.PcmChangePriceLimit;
import com.wangfj.product.price.domain.vo.PcmChangePriceLimitDto;
import com.wangfj.product.price.domain.vo.SelectPcmPriceLimitDto;

public interface PcmChangePriceLimitMapper{
	
	/**
	 * 根据sid删除阀值
	 * @param sid 主健
	 * @return
	 */
    int deleteByPrimaryKey(Long sid);

    /**
     * 插入一条阀值
     * @param record
     * @return
     */    
    Integer insert(PcmChangePriceLimit record);
	
    int insertSelective(PcmChangePriceLimit record);
    
    /**
     * 根据门店号查询阀值
     * @param shopSid
     * @return
     */
    List<PcmChangePriceLimit> selectPriceLimitByShopSid(String shopSid);
    
    /**
     * 根据门店编号查询阀值
     * @param shopSid
     * @return
     */
    List<PcmChangePriceLimit> selectPriceLimitByShopCode(String shopCode);
    
    /**
	 * 分页查询
	 * 
	 * @Methods Name PcmChannel
	 * @Create In 2015年7月31日 By zhangdongliang
	 * @param record
	 * @return List<PcmChangePriceLimit>
	 */
	List<PcmChangePriceLimitDto> selectPageList(SelectPcmPriceLimitDto record);

    /**
     * 根据sid查找一条阀值
     * @param sid
     * @return
     */
    PcmChangePriceLimit selectByPrimaryKey(Long sid);

    /**
     * 修改一条阀值
     * @param record
     * @return
     */   
    int updateByPrimaryKeySelective(PcmChangePriceLimit record);

    /**
     * 查询所有已经添加过阀值的门店号
     * @return
     */
    List<Long> selectAllShopSidFromPriceLimit();
    
    /**
	 * 分页总数查询
	 * 
	 * @Methods Name getPageCountByPara
	 * @Create In 2015-8-21 By wangxuan
	 * @param paramMap
	 * @return Integer
	 */
	Integer getPageCount(SelectPcmPriceLimitDto record);
}