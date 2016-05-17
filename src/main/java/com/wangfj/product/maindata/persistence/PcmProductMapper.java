package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.vo.PcmProductDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchSpuDto;
import com.wangfj.product.maindata.domain.vo.ProCateChannelDto;
import com.wangfj.product.maindata.domain.vo.PublishCacheDto;
import com.wangfj.product.maindata.domain.vo.SearchSpuDto;
import com.wangfj.product.maindata.domain.vo.SpuPageDto;

public interface PcmProductMapper extends BaseMapper<PcmProduct> {
	List<Map<String, Object>> judgeproductRepeat(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProduct record);

	PcmProduct selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProduct record);

	int updateByPrimaryKey(PcmProduct record);

	List<ProCateChannelDto> getCatePropBySpuCode(Map<String, Object> paramMap);

	List<PcmProduct> selectListByParam(Map<String, Object> paramMap);

	/**
	 * 根据SpuCode查询SkuCode
	 * 
	 * @Methods Name selectSkuCodeBySpuCode
	 * @Create In 2016年3月3日 By yedong
	 * @param spuCode
	 * @return List<String>
	 */
	List<String> selectSkuCodeBySpuCode(String spuCode);

	/**
	 * 商品换各种属性后 下发
	 * 
	 * @Methods Name pubLishPro
	 * @Create In 2015年9月14日 By yedong
	 * @param paramMap
	 * @return List<PublishDTO>
	 */
	List<PublishCacheDto> pubLishPro(Map<String, Object> paramMap);

	/**
	 * 验证SPU是否存在
	 * <p>
	 * 根据<b>productSku款号</b>和<b>brandSid编码</b>
	 * 
	 * @Methods Name selectProductByProSkuAndBrandSidIsExists
	 * @Create In 2015年7月14日 By wangsy
	 * @param map
	 * @return PcmProduct
	 */
	@SuppressWarnings("rawtypes")
	PcmProduct selectProductByProSkuAndBrandSidIsExists(Map map);

	List<Map<String, Object>> selectPage1(PcmProductDto record);

	int selectCount(PcmProductDto record);

	/**
	 * 按条件查询SPU计数
	 * 
	 * @Methods Name getProductCountByPara
	 * @Create In 2015年8月11日 By zhangxy
	 * @param dto
	 * @return Integer
	 */
	Integer getProductCountByPara(SpuPageDto dto);

	/**
	 * 按条件分页查询SPU
	 * 
	 * @Methods Name selectProductPageByPara
	 * @Create In 2015年8月11日 By zhangxy
	 * @param dto
	 * @return List<SpuPageDto>
	 */
	List<SpuPageDto> selectProductPageByPara(SpuPageDto dto);

	/**
	 * SPU下发
	 * 
	 * @Methods Name selectProductPageByPara2
	 * @Create In 2016年3月14日 By kongqf
	 * @param dto
	 * @return List<SpuPageDto>
	 */
	List<SpuPageDto> selectProductPageByPara2(SpuPageDto dto);

	List<SpuPageDto> supPublishFj(Map<String, Object> paramMap);

	/**
	 * 根据品类叶子节点查询spu信息
	 * 
	 * @Methods Name selectSpuByIsLeaf
	 * @Create In 2015年8月11日 By duanzhaole
	 * @param cateParam
	 * @return PcmProduct
	 */
	List<PcmProduct> selectSpuByIsLeaf(SelectCategoryParamDto cateParam);

	/**
	 * 根据品类叶子节点查询spu信息count
	 * 
	 * @Methods Name selectSpuCountByIsLeaf
	 * @Create In 2015年8月11日 By duanzhaole
	 * @param cateParam
	 * @return int
	 */
	int selectSpuCountByIsLeaf(SelectCategoryParamDto cateParam);

	List<Map<String, Object>> searchSpu(PcmSearchSpuDto dto);

	List<SearchSpuDto> selectSpuByCodeForSearch(Map<String, Object> paramMap);

	List<Map<String, Object>> selectSpuByCateAndBrand(Map<String, Object> paramMap);
}