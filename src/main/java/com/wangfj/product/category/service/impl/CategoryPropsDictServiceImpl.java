package com.wangfj.product.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.vo.CategoryPropsDictVO;
import com.wangfj.product.category.domain.vo.PcmCategoryPropsDictDto;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.persistence.PcmCategoryPropsDictMapper;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;

/**
 * 分类属性service
 * 
 * @Class Name CategoryPropsDictServiceImpl
 * @Author duanzhaole
 * @Create In 2015年7月30日
 */
@Service
public class CategoryPropsDictServiceImpl implements ICategoryPropsDictService {

	@Autowired
	private PcmCategoryPropsDictMapper CategoryPropsDictMapper;

	@Transactional
	public int delete(Long sid) {
		return this.CategoryPropsDictMapper.deleteByPrimaryKey(sid);
	}

	@Transactional
	public int save(PcmCategoryPropsDict record) {

		int result = CategoryPropsDictMapper.insertSelective(record);

		return result;
	}

	public PcmCategoryPropsDict get(Long sid) {
		return this.CategoryPropsDictMapper.selectByPrimaryKey(sid);
	}

	public PcmCategoryPropsDict getByPropSidAndChannelSid(Long propsSid, Long channelSid) {
		PcmCategoryPropsDict scpd = new PcmCategoryPropsDict();
		scpd.setPropsSid(propsSid);
		scpd.setChannelSid(channelSid);
		List<PcmCategoryPropsDict> list = this.CategoryPropsDictMapper.selectList(scpd);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public int update(PcmCategoryPropsDict record) {
		return this.CategoryPropsDictMapper.updateByPrimaryKeySelective(record);
	}

	public List<PcmCategoryPropsDict> selectList(PcmCategoryPropsDict record) {
		return this.CategoryPropsDictMapper.selectList(record);
	}

	/**
	 * 
	 */
	public List<PcmCategoryPropsDict> selectListInChannelSid(CategoryPropsDictVO record) {
		return this.CategoryPropsDictMapper.selectListInChannelSid(record);
	}

	public List<PcmCategoryPropsDictDto> selectPage(PcmCategoryPropsDict record) {
		return this.CategoryPropsDictMapper.selectPage(record);
	}

	public int selectPageTotal(PcmCategoryPropsDict record) {
		return this.CategoryPropsDictMapper.selectPageTotal(record);
	}
	public int selectPageLikeTotal(PcmCategoryPropsDict record) {
		return this.CategoryPropsDictMapper.selectPageLikeTotal(record);
	}
	/**
	 * 返回同一父类下的子类最大的序列号
	 */
	public Long getMaxSortOrder(Long channelSid) {
		PcmCategoryPropsDict scpd = new PcmCategoryPropsDict();
		scpd.setChannelSid(channelSid);
		List<PcmCategoryPropsDict> list = this.CategoryPropsDictMapper.selectList(scpd);
		Long max = 0L;
		for (PcmCategoryPropsDict cat : list) {
			if (max < cat.getSortOrder()) {
				max = cat.getSortOrder();
			}
		}
		return max;
	}

	/**
	 * 维护属性字典信息
	 */
	@Transactional
	public int saveorupdate(PcmCategoryPropsDict record) {
		PcmCategoryPropsDict scpd = getByPropSidAndChannelSid(record.getPropsSid(),
				record.getChannelSid());
		if (scpd == null) {
			return this.CategoryPropsDictMapper.insertSelective(record);
		} else {
			scpd.setStatus(record.getStatus());
			scpd.setIsKeyProp(record.getIsKeyProp());
			scpd.setPropsName(record.getPropsName());
			scpd.setIsEnumProp(record.getIsEnumProp());
			scpd.setSortOrder(record.getSortOrder());
			return this.CategoryPropsDictMapper.updateByPrimaryKeySelective(scpd);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryPropsDictService#
	 * selectPropsDictByParentSid()
	 */
	@Override
	public Page<PcmCategoryPropsDict> selectPropsDictByParentSid(PcmPropsDictsDto propdDto,
			Page<PcmPropsDictsDto> pagedto) {
		List<PcmCategoryPropsDict> proplist = new ArrayList<PcmCategoryPropsDict>();
		Page<PcmCategoryPropsDict> pagelist = new Page<PcmCategoryPropsDict>();
		int count = CategoryPropsDictMapper.selectCountPropsDictByParentSid(propdDto);
		pagelist.setCount(count);
		propdDto.setStart(pagedto.getStart());
		propdDto.setLimit(pagedto.getLimit());
		proplist = CategoryPropsDictMapper.selectPropsDictByParentSid(propdDto);
		if (!proplist.isEmpty()) {
			pagelist.setCount(count);
			pagelist.setList(proplist);
			pagelist.setPageSize(propdDto.getPageSize());
			pagelist.setCurrentPage(propdDto.getCurrenPage());

		} else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		return pagelist;
	}

	

	

}
