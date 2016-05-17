package com.wangfj.product.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.vo.CategorySearchDto;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.service.intf.ICategorySearchService;
import com.wangfj.util.mq.PublishDTO;

@Service
public class CategorySearchServiceImpl implements ICategorySearchService {
	@Autowired
	private PcmCategoryMapper cateMapper;

	/**
	 * 根据展示分类编码查询展示分类信息
	 * 
	 * @Methods Name getCateByCode
	 * @Create In 2015年11月11日 By zhangxy
	 * @param categorySid
	 * @return PcmCategory
	 */
	@Override
	public CategorySearchDto getCateByCode(String code) {
		PcmCategory category = new PcmCategory();
		category.setCategorySid(code);
		category.setCategoryType(3);
		category.setIsDisplay(1);
		category.setStatus("Y");
		List<PcmCategory> list = cateMapper.selectListByParam(category);
		CategorySearchDto resDto = null;
		if (list != null && list.size() != 0) {
			resDto = new CategorySearchDto();
			PcmCategory cate = list.get(0);
			resDto.setCategoryId(cate.getCategorySid());
			resDto.setCategoryName(cate.getName());
			resDto.setLeafLevel(cate.getIsLeaf().equals("Y") ? true : false);
			resDto.setLevel(cate.getLevel());
			resDto.setOrder(cate.getSortOrder());
			resDto.setParentCategoryId(cate.getParentSid().equals("0") ? null : cate.getParentSid());
			resDto.setRootCategoryId(String.valueOf(cate.getRootSid() == 0 ? cate.getCategorySid()
					: cate.getRootSid()));
			resDto.setSelfBuilt(cate.getIsSelfBuilt() == 1 ? true : false);
			resDto.setChannel(String.valueOf(cate.getChannelSid()));
		}
		return resDto;
	}

	/**
	 * 根据展示分类编码查询子节点
	 * 
	 * @Methods Name getByParentSid
	 * @Create In 2015年11月11日 By zhangxy
	 * @param parentSid
	 * @return List<PcmCategory>
	 */
	@Override
	public List<String> getByParentSid(String parentSid) {
		PcmCategory category = new PcmCategory();
		category.setParentSid(parentSid);
		category.setCategoryType(3);
		category.setIsDisplay(1);
		category.setStatus("Y");
		List<PcmCategory> list = cateMapper.selectListByParam(category);
		List<String> resList = new ArrayList<String>();
		for (PcmCategory entity : list) {
			resList.add(entity.getCategorySid());
		}
		return resList;
	}

	/**
	 * 根据展示分类Sid查询展示分类信息下发
	 * 
	 * @Methods Name selectCateBySidList
	 * @Create In 2015年11月11日 By zhangxy
	 * @param pushList
	 * @return List<CategorySearchDto>
	 */
	@Override
	public List<CategorySearchDto> selectCateBySidList(List<PublishDTO> pushList) {
		// PcmCategory category = new PcmCategory();
		// category.setCategorySid(code);
		// category.setCategoryType(3);
		// category.setIsDisplay(1);
		// category.setStatus("Y");
		List<PcmCategory> list = cateMapper.selectCateBySidList(pushList);
		List<CategorySearchDto> resList = new ArrayList<CategorySearchDto>();
		if (list != null && list.size() != 0) {
			for (PcmCategory cate : list) {
				CategorySearchDto resDto = new CategorySearchDto();
				resDto.setChannel(cate.getChannelSid().toString());
				resDto.setCategoryId(cate.getCategorySid());
				resDto.setCategoryName(cate.getName());
				resDto.setLeafLevel(cate.getIsLeaf().equals("Y") ? true : false);
				resDto.setLevel(cate.getLevel());
				resDto.setOrder(cate.getSortOrder());
				resDto.setParentCategoryId(cate.getParentSid().equals("0") ? null : cate
						.getParentSid());
				resDto.setRootCategoryId(String.valueOf(cate.getRootSid() == 0 ? cate
						.getCategorySid() : cate.getRootSid()));
				resDto.setSelfBuilt(cate.getIsSelfBuilt() == 1 ? true : false);
				resList.add(resDto);
			}
		}
		return resList;
	}
}
