package com.wangfj.product.category.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.vo.PcmCategoryValuesDictDto;
import com.wangfj.product.category.domain.vo.ShowPropsDictVo;
import com.wangfj.product.category.persistence.PcmCategoryValuesDictMapper;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;

@Service
public class CategoryValuesDictServiceImpl implements ICategoryValuesDictService {

	@Autowired
	private PcmCategoryValuesDictMapper CategoryValuesDictMapper;

	@Transactional
	public int delete(Long sid) {
		return this.CategoryValuesDictMapper.deleteByPrimaryKey(sid);
	}

	@Transactional
	public int save(PcmCategoryValuesDict record) {

		return this.CategoryValuesDictMapper.insertSelective(record);
	}

	public PcmCategoryValuesDict get(Long sid) {
		return this.CategoryValuesDictMapper.selectByPrimaryKey(sid);
	}

	@Transactional
	public int update(PcmCategoryValuesDict record) {
		return this.CategoryValuesDictMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 显示所有的属性值信息
	 */
	public List<PcmCategoryValuesDict> selectList(PcmCategoryValuesDict record) {
		return this.CategoryValuesDictMapper.selectList(record);
	}

	/**
	 * 分页显示所有的属性值信息
	 */
	public List<PcmCategoryValuesDictDto> selectPage(PcmCategoryValuesDict record) {
		return this.CategoryValuesDictMapper.selectPage(record);
	}

	/**
	 * 查询属性值的记录数
	 */
	public int selectPageTotal(PcmCategoryValuesDict record) {
		return this.CategoryValuesDictMapper.selectPageTotal(record);
	}

	/**
	 * 查询当前属性下所有值的最大序列号
	 */
	public Long getMaxSortOrder(Long channelSid, Long propsSid) {
		PcmCategoryValuesDict scpd = new PcmCategoryValuesDict();
		scpd.setChannelSid(channelSid);
		scpd.setPropsSid(propsSid);
		List<PcmCategoryValuesDict> list = this.CategoryValuesDictMapper.selectList(scpd);
		Long max = 0L;
		for (PcmCategoryValuesDict cat : list) {
			if (max < cat.getSortOrder()) {
				max = cat.getSortOrder();
			}
		}
		return max;
	}

	/**
	 * 根据属性值ID与渠道ID查询属性值信息
	 */
	public PcmCategoryValuesDict getByValuesSidAndChannelSid(Long valuesSid, Long channelSid) {
		PcmCategoryValuesDict scvd = new PcmCategoryValuesDict();
		scvd.setValuesSid(valuesSid);
		scvd.setChannelSid(channelSid);
		List<PcmCategoryValuesDict> list = this.CategoryValuesDictMapper.selectList(scvd);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 对当前属性值信息做添加或更新操作
	 */
	@Transactional
	public int saveorupdate(PcmCategoryValuesDict record) {
		PcmCategoryValuesDict scvd = getByValuesSidAndChannelSid(record.getValuesSid(),
				record.getChannelSid());
		if (scvd == null) {
			return this.CategoryValuesDictMapper.insertSelective(record);
		} else {
			scvd.setPropsSid(record.getPropsSid());
			scvd.setValuesName(record.getValuesName());
			scvd.setIsKeyValue(record.getIsKeyValue());
			scvd.setSortOrder(record.getSortOrder());
			scvd.setStatus(record.getStatus());
			return this.CategoryValuesDictMapper.updateByPrimaryKeySelective(scvd);
		}
	}

	/**
	 * 查询当前属性下的所有属性值
	 */
	public List<PcmCategoryValuesDict> getByPropsSidAndChannelSid(Long propsSid, Long channelSid) {
		PcmCategoryValuesDict scvd = new PcmCategoryValuesDict();
		scvd.setPropsSid(propsSid);
		scvd.setChannelSid(channelSid);
		return this.CategoryValuesDictMapper.selectList(scvd);
	}

	/**
	 * 更改属性值状态
	 */
	@Transactional
	public int updateStatus(Long propsSid, Long channelSid) {
		PcmCategoryValuesDict scvd = new PcmCategoryValuesDict();
		scvd.setChannelSid(channelSid);
		scvd.setPropsSid(propsSid);
		return this.CategoryValuesDictMapper.updateStatus(scvd);
	}

	/**
	 * 批量添加或更新属性值信息
	 */
	@Transactional
	public int saveorupdate(List<PcmCategoryValuesDict> list) {
		List<PcmCategoryValuesDict> savelist = new ArrayList<PcmCategoryValuesDict>();
		List<PcmCategoryValuesDict> updatelist = new ArrayList<PcmCategoryValuesDict>();
		for (PcmCategoryValuesDict record : list) {
			PcmCategoryValuesDict scvd = getByValuesSidAndChannelSid(record.getValuesSid(),
					record.getChannelSid());
			if (scvd == null) {
				savelist.add(record);
			} else {
				scvd.setPropsSid(record.getPropsSid());
				scvd.setValuesName(record.getValuesName());
				scvd.setIsKeyValue(record.getIsKeyValue());
				scvd.setSortOrder(record.getSortOrder());
				scvd.setStatus(record.getStatus());
				updatelist.add(scvd);
			}
		}
		int flag = 0;
		if (savelist.size() > 0) {
			flag = this.CategoryValuesDictMapper.insertBatch(savelist);
		}
		if (updatelist.size() > 0) {
			flag = flag + this.CategoryValuesDictMapper.updateBatch(updatelist);
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangfj.product.category.service.intf.ICategoryValuesDictService#
	 * selectListByCategorySid
	 * (com.wangfj.product.category.domain.vo.PcmPropsDictsDto)
	 */
	@Override
	public List<PcmCategoryValuesDict> selectListByCategorySid(PcmCategoryValuesDictDto pros) {

		List<PcmCategoryValuesDict> listcate = CategoryValuesDictMapper
				.selectListByCategorySid(pros);
		ShowPropsDictVo showVo = new ShowPropsDictVo();

		return CategoryValuesDictMapper.selectListByCategorySid(pros);
	}

	/**
	 * 通过属性SID获取属性值信息
	 * 
	 * @Methods Name selectValueDictByPropSid
	 * @Create In 2015年11月24日 By yedong
	 * @param propSid
	 * @return List<PcmCategoryValuesDict>
	 */
	public List<PcmCategoryValuesDict> selectValueDictByPropSid(String propSid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (propSid != null && !propSid.equals("")) {
			paramMap.put("propsSid", Long.parseLong(propSid));
		}
		paramMap.put("status", "1");
		List<PcmCategoryValuesDict> list = CategoryValuesDictMapper.selectListByParam(paramMap);
		return list;
	}

}
