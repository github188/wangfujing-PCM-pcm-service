package com.wangfj.product.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.category.domain.entity.PcmCategoryRelation;
import com.wangfj.product.category.persistence.PcmCategoryRelationMapper;
import com.wangfj.product.category.service.intf.ICategoryRelationService;

@Service
public class CategoryRelationServiceImpl implements ICategoryRelationService {

	@Autowired
	private PcmCategoryRelationMapper ssdCategoryRelationMapper;

	public int delete(Long sid) {
		return this.ssdCategoryRelationMapper.deleteByPrimaryKey(sid);
	}

	public int save(PcmCategoryRelation record) {
		return this.ssdCategoryRelationMapper.insertSelective(record);
	}

	public PcmCategoryRelation get(Long sid) {
		return this.ssdCategoryRelationMapper.selectByPrimaryKey(sid);
	}

	public int update(PcmCategoryRelation record) {
		return this.ssdCategoryRelationMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据gyCategorySid查询 返回品类GyCategorySid
	 */
	public String getCateIds(String gyCategorySid) {
		PcmCategoryRelation scr = new PcmCategoryRelation();
		scr.setGyCategorySid(Long.valueOf(gyCategorySid));
		List<PcmCategoryRelation> list = this.ssdCategoryRelationMapper.selectList(scr);
		if (list.size() > 0) {
			return list.get(0).getGyCategorySid() + "";
		} else {
			return null;
		}
	}

}
