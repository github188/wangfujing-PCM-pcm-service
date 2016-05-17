package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.vo.LabelSkuPageQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmShoppeProductQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSkuQueryDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;

public interface IPcmProductTagService {

	/**
	 * 按条件 分页 查询专柜商品基础信息
	 * 
	 * @Methods Name selectBaseProPageByPara
	 * @Create In 2015年8月4日 By dongliang
	 * @param pageDto
	 * @return Page<ProductPageDto>
	 * @throws Exception
	 */
	public Page<ProductPageDto> selectBaseProPageByPara(ProductPageDto pageDto, String isAddTag,
			String tagSid) throws BleException;

	/**
	 * 批量导入专柜商品与促销标签的关系
	 *
	 * @param dto
	 * @return
	 */
	boolean addShoppeProductTagList(PcmShoppeProductQueryDto dto);

	/**
	 * 批量导入商品(SKU)与关键字的关系
	 *
	 * @param dto
	 * @return
	 */
	boolean addSkuTagList(PcmSkuQueryDto dto);

	/**
	 * 添加关系
	 */
	public boolean save(List<PcmProductTag> proTags);

	/**
	 * 删除关系
	 */
	public boolean delete(List<PcmProductTag> proTags);

	public Page<SkuPageDto> selectSkuPage(LabelSkuPageQueryDto pageDto);

}
