package com.wangfj.product.category.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.cache.RedisCacheGet;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.ValuesVO;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmCategoryPropValuesMapper;
import com.wangfj.product.category.service.intf.ICategoryPropValuesService;
import com.wangfj.product.constants.DomainName;

/**
 * 品类属性关联service
 * 
 * @Class Name CategoryPropValuesServiceImpl
 * @Author duanzhaole
 * @Create In 2015年8月3日
 */
@Service
public class CategoryPropValuesServiceImpl implements ICategoryPropValuesService {

	@Autowired
	private PcmCategoryPropValuesMapper ssdCategoryPropValuesMapper;

	@Autowired
	private PcmCategoryMapper cateMapper;

	// 子节点列表-yedong
	List<PcmCategory> sub_list = new ArrayList<PcmCategory>();

	@Transactional
	public int delete(Long sid) {
		return this.ssdCategoryPropValuesMapper.deleteByPrimaryKey(sid);
	}

	@Transactional
	public int save(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.insertSelective(record);
	}

	public PcmCategoryPropValues get(Long sid) {
		return this.ssdCategoryPropValuesMapper.selectByPrimaryKey(sid);
	}

	@Transactional
	public int update(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 显示所有的品类-属性-属性值信息
	 */
	public List<PcmCategoryPropValues> selectList(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectList(record);
	}

	/**
	 * 分页显示所有的品类-属性-属性值信息
	 */
	public List<PcmCategoryPropValues> selectPage(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectPage(record);
	}

	/**
	 * 查询品类-属性-属性值的记录数
	 */
	public int selectPageTotal(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectPageTotal(record);
	}

	/**
	 * 根据品类id,属性id删除品类-属性-属性值信息
	 */
	@Transactional
	public int deleteByProperties(String categorySid, Long propSid, Long channelSid) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(categorySid);
		scpv.setPropsSid(propSid);
		scpv.setChannelSid(channelSid);
		return this.ssdCategoryPropValuesMapper.deleteList(scpv);
	}

	/**
	 * 根据品类id删除品类-属性-属性值信息
	 */
	@Transactional
	public int deleteByProperties(String categorySid, Long channelSid) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(categorySid);
		scpv.setChannelSid(channelSid);
		return this.ssdCategoryPropValuesMapper.deleteList(scpv);
	}

	/**
	 * 根据属性id删除品类-属性-属性值信息
	 */
	@Transactional
	public int deleteByPropAndChan(Long propsSid, Long channelSid) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setPropsSid(propsSid);
		scpv.setChannelSid(channelSid);
		return this.ssdCategoryPropValuesMapper.deleteList(scpv);
	}

	/**
	 * 根据属性值id删除品类-属性-属性值信息
	 */
	@Transactional
	public int deleteByValueAndChan(Long valueSid, Long channelSid) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setValuesSid(valueSid);
		scpv.setChannelSid(channelSid);
		return this.ssdCategoryPropValuesMapper.deleteList(scpv);
	}

	/**
	 * 从品类-属性-属性值关系中查询所需要的属性(photo)
	 */
	public List<PcmCategoryPropValues> selectPropsVOList(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectPropsVOList(record);
	}

	/**
	 * 从品类-属性-属性值关系中查询所需要的属性(SSD)
	 */
	public List<PcmCategoryPropValues> selectPropsVO(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectPropsVO(record);
	}

	/**
	 * 从品类-属性-属性值关系中查询所需要的属性记录数(SSD)
	 */
	public int selectPropsVOTotal(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectPropsVOTotal(record);
	}

	/**
	 * 查询品类-属性-属性值
	 */
	public List<PcmCategoryPropValues> selectCateVO(PcmCategoryPropValues record) {
		return this.ssdCategoryPropValuesMapper.selectCateVO(record);
	}

	/**
	 * 对品类-属性-属性值做添加更新操作 若没有信息,做添加操作 否则,做更新操作
	 */
	@Transactional
	public int saveorupdate(PcmCategoryPropValues record) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(record.getCategorySid());
		scpv.setPropsSid(record.getPropsSid());
		scpv.setValuesSid(record.getValuesSid());
		scpv.setChannelSid(record.getChannelSid());
		scpv.setNotNull(record.getNotNull());
		List<PcmCategoryPropValues> list = this.ssdCategoryPropValuesMapper.selectList(scpv);
		scpv.setCategoryName(record.getCategoryName());
		scpv.setPropsName(record.getPropsName());
		scpv.setValuesName(record.getValuesName());
		if (list == null || list.size() == 0) {
			return this.ssdCategoryPropValuesMapper.insertSelective(scpv);
		} else {
			scpv.setSid(list.get(0).getSid());
			return this.ssdCategoryPropValuesMapper.updateByPrimaryKeySelective(scpv);
		}
	}

	/**
	 * 删除符合条件的品类-属性-属性值
	 */
	@Transactional
	public int deleteorupdate(PcmCategoryPropValues record) {
		PcmCategoryPropValues scpv = new PcmCategoryPropValues();
		scpv.setCategorySid(record.getCategorySid());
		scpv.setPropsSid(record.getPropsSid());
		scpv.setValuesSid(record.getValuesSid());
		scpv.setChannelSid(record.getChannelSid());
		return this.ssdCategoryPropValuesMapper.deleteList(scpv);
	}

	/**
	 * 对品类-属性-属性值做批量添加或批量更新
	 */
	@Transactional
	public int saveorupdate(List<PcmCategoryPropValues> list) {
		List<PcmCategoryPropValues> savelist = new ArrayList<PcmCategoryPropValues>();
		List<PcmCategoryPropValues> updatelist = new ArrayList<PcmCategoryPropValues>();
		for (PcmCategoryPropValues record : list) {
			PcmCategoryPropValues scpv = new PcmCategoryPropValues();
			scpv.setCategorySid(record.getCategorySid());
			scpv.setPropsSid(record.getPropsSid());
			scpv.setValuesSid(record.getValuesSid());
			scpv.setChannelSid(record.getChannelSid());
			List<PcmCategoryPropValues> lis = this.ssdCategoryPropValuesMapper.selectList(scpv);
			if (lis.size() == 0) {
				savelist.add(record);
			} else {
				record.setSid(lis.get(0).getSid());
				updatelist.add(record);
			}
		}
		int flag = 0;
		if (savelist.size() > 0) {
			flag = this.ssdCategoryPropValuesMapper.insertBatch(savelist);
		}
		if (updatelist.size() > 0) {
			flag = flag + this.ssdCategoryPropValuesMapper.updateBatch(updatelist);
		}
		return flag;
	}

	/**
	 * 对品类-属性-属性值做批量添加
	 */
	@Transactional
	public int save(List<PcmCategoryPropValues> list) {
		return this.ssdCategoryPropValuesMapper.insertBatch(list);
	}

	/**
	 * 从品类-属性-属性值关系中查询所需要的属性(photo)
	 */
	// @MethodCache(expire=1800)
	public List<PcmCategoryPropValues> selectPropsValueList(String categorySid, Long channelSid) {
		PcmCategoryPropValues scp = new PcmCategoryPropValues();
		scp.setCategorySid(categorySid);
		scp.setChannelSid(channelSid);
		return this.ssdCategoryPropValuesMapper.selectPropsVOList(scp);

	}

	@Override
	public List<ValuesVO> getAllCategoryValuesVOs(Map map) {
		return this.ssdCategoryPropValuesMapper.getAllCategoryValuesVOs(map);
	}

	public PcmCategoryPropValues selectByPropsSid(PcmPropsDictsDto propsSid) {
		return this.ssdCategoryPropValuesMapper.selectByPropSid(propsSid);
	}

	/**
	 * 按分类SID查询分类属性信息
	 * 
	 * @Methods Name propSelect
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 * @return List<PcmCategoryPropValues>
	 */
	public List<PcmCategoryPropValues> propSelect(String cateSid) {
		PcmCategoryPropValues entity = new PcmCategoryPropValues();
		entity.setCategorySid(cateSid);
		List<PcmCategoryPropValues> catePropValList = ssdCategoryPropValuesMapper
				.selectListByParam(entity);
		return catePropValList;
	}

	/**
	 * 属性继承
	 * 
	 * @Methods Name propInheritance
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 * @param list
	 * @throws Exception
	 *             void
	 */
	public void propInheritance(String cateSid, List<PcmCategoryPropValues> list) throws Exception {
		/* list:添加前的分类属性关系实体LIST */
		PcmCategoryPropValues entity = new PcmCategoryPropValues();
		entity.setCategorySid(cateSid);
		/* catePropValList:添加后的分类属性关系实体LIST */
		List<PcmCategoryPropValues> catePropValList = ssdCategoryPropValuesMapper
				.selectListByParam(entity);
		/* cateSid下的查询所有子类 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		selectAllSubNodeByCateSid(cateSid);
		List<PcmCategory> subNodeList = new ArrayList<PcmCategory>();
		subNodeList = this.sub_list;
		/* 如果 list！=null 删除所有子类相对应的数据 */
		if (subNodeList != null && subNodeList.size() > 0) {
			if (list != null && list.size() > 0) {
				/* 查询应删除的SID */
				List<String> propsList = new ArrayList<String>();
				for (PcmCategoryPropValues prop : list) {
					Long propsSid = prop.getPropsSid();
					propsList.add(propsSid.toString());
				}
				List<String> cateList = new ArrayList<String>();
				for (PcmCategory cate : subNodeList) {
					Long cateSids = cate.getSid();
					cateList.add(cateSids.toString());
				}
				paramMap.put("cateList", cateList);
				paramMap.put("propsList", propsList);
				List<String> sidListParams = ssdCategoryPropValuesMapper.getSidListParams(paramMap);
				/* 删除 */
				if (sidListParams != null && sidListParams.size() > 0) {
					paramMap.clear();
					paramMap.put("subNodeList", sidListParams);
					ssdCategoryPropValuesMapper.deleteSidListParams(paramMap);
				}
			}
			/* 给所有子类添加相对应的数据 */
			List<PcmCategoryPropValues> new_catePropValList = new ArrayList<PcmCategoryPropValues>();
			for (PcmCategoryPropValues catePropVal : catePropValList) {
				for (PcmCategory subNode : subNodeList) {
					PcmCategoryPropValues new_catePropVal = new PcmCategoryPropValues();
					new_catePropVal.setSid(null);
					new_catePropVal.setChannelSid(catePropVal.getChannelSid());
					new_catePropVal.setPropsSid(catePropVal.getPropsSid());
					new_catePropVal.setPropsName(catePropVal.getPropsName());
					new_catePropVal.setValuesSid(catePropVal.getValuesSid());
					new_catePropVal.setValuesName(catePropVal.getValuesName());
					new_catePropVal.setOptUser(catePropVal.getOptUser());
					new_catePropVal.setNotNull(catePropVal.getNotNull());
					new_catePropVal.setIsErpSyn(catePropVal.getIsErpSyn());
					new_catePropVal.setCategorySid(subNode.getCategorySid());
					new_catePropVal.setCategoryName(subNode.getName());
					new_catePropValList.add(new_catePropVal);
				}
			}
			paramMap.clear();
			paramMap.put("batchList", new_catePropValList);
			ssdCategoryPropValuesMapper.insertCategoryPropValBatch(paramMap);
			this.sub_list.clear();
		}
	}

	/**
	 * 查询所有子节点(不包括本身)
	 * 
	 * @Methods Name selectAllSubNodeByCateSid
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 *            void
	 */
	public List<PcmCategory> selectAllSubNodeByCateSid(String cateSid) {
		PcmCategory entity = new PcmCategory();
		entity.setParentSid(cateSid);
		entity.setIsDisplay(1);
		List<PcmCategory> selectListByParam = cateMapper.selectListByParam(entity);
		if (selectListByParam != null && selectListByParam.size() > 0) {
			for (PcmCategory pcmCategory : selectListByParam) {
				selectAllSubNodeByCateSid(pcmCategory.getSid().toString());
				this.sub_list.add(pcmCategory);
			}
		}
		return this.sub_list;
	}

	@Override
	@RedisCacheGet(redisName = DomainName.getCMSCategory, isList = true, returnObj = "com.wangfj.product.category.domain.entity.PcmCategory")
	public List<PcmCategory> selectAllSupNodeBySpuCode(String key) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spuCode", key);
		List<PcmCategory> cateList = cateMapper.getCateInfoBySpuCode(paramMap);
		for (PcmCategory pcmCategory : cateList) {
			PcmCategory entity = new PcmCategory();
			entity.setSid(pcmCategory.getSid());
			List<PcmCategory> selectListByParam = cateMapper.selectListByParam(entity);
			if (selectListByParam != null && selectListByParam.size() > 0) {
				selectAllSupNodeByCateSid(selectListByParam.get(0).getSid().toString());
			}
		}
		return this.sub_list;
	}

	/**
	 * 查询所有父节点(包括本身)
	 * 
	 * @Methods Name selectAllSupNodeByCateSid
	 * @Create In 2015年11月20日 By yedong
	 * @param cateSid
	 *            void
	 */
	@Override
	public List<PcmCategory> selectAllSupNodeByCateSid(String cateSid) {
		PcmCategory entity = new PcmCategory();
		entity.setSid(Long.parseLong(cateSid));
		List<PcmCategory> selectListByParam = cateMapper.selectListByParam(entity);
		if (selectListByParam != null && selectListByParam.size() > 0) {
			this.sub_list.add(selectListByParam.get(0));
			selectAllSupNodeByCateSid(selectListByParam.get(0).getParentSid().toString());
		}
		return this.sub_list;
	}

	/**
	 * 删除所有子节点关联关系
	 * 
	 * @Methods Name propInheritanceDelete
	 * @Create In 2015年11月4日 By yedong
	 * @param cateSid
	 *            void
	 */
	public void propInheritanceDelete(String cateSid, List<PcmCategoryPropValues> list) {
		/* list:原分类属性关系实体LIST */
		PcmCategoryPropValues entity = new PcmCategoryPropValues();
		entity.setCategorySid(cateSid);
		/* cateSid下的查询所有子类 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		selectAllSubNodeByCateSid(cateSid);
		List<PcmCategory> subNodeList = new ArrayList<PcmCategory>();
		subNodeList = sub_list;
		/* 如果 list！=null 删除所有子类相对应的数据 */
		if (subNodeList != null && subNodeList.size() > 0) {
			if (list != null && list.size() > 0) {
				/* 查询应删除的SID */
				List<String> propsList = new ArrayList<String>();
				for (PcmCategoryPropValues prop : list) {
					Long propsSid = prop.getPropsSid();
					propsList.add(propsSid.toString());
				}
				List<String> cateList = new ArrayList<String>();
				for (PcmCategory cate : subNodeList) {
					Long cateSids = cate.getSid();
					cateList.add(cateSids.toString());
				}
				paramMap.put("cateList", cateList);
				paramMap.put("propsList", propsList);
				List<String> sidListParams = ssdCategoryPropValuesMapper.getSidListParams(paramMap);
				/* 删除 */
				if (sidListParams != null && sidListParams.size() > 0) {
					paramMap.clear();
					paramMap.put("subNodeList", sidListParams);
					ssdCategoryPropValuesMapper.deleteSidListParams(paramMap);
				}
			}
		}
		this.sub_list.clear();
	}

	/**
	 * 拖拽继承属性
	 * 
	 * @Methods Name DragInheritance
	 * @Create In 2015年11月5日 By yedong
	 * @param parentSid
	 * @param cateSid
	 *            void
	 */
	public void dragInheritance(String parentSid, String cateSid, String oldCateSid) {
		/* 查询原父类的属性 */
		PcmCategoryPropValues entity_old = new PcmCategoryPropValues();
		entity_old.setCategorySid(oldCateSid);
		List<PcmCategoryPropValues> parentPropList_old = ssdCategoryPropValuesMapper
				.selectListByParam(entity_old);
		/* 查询父类属性 */
		PcmCategoryPropValues entity = new PcmCategoryPropValues();
		entity.setCategorySid(parentSid);
		List<PcmCategoryPropValues> parentPropList = ssdCategoryPropValuesMapper
				.selectListByParam(entity);
		/* 查询子节点信息 */
		PcmCategory pcmCategory = cateMapper.get(Long.parseLong(cateSid));
		/* 查询所有子节点 */
		selectAllSubNodeByCateSid(cateSid);
		this.sub_list.add(pcmCategory);
		List<PcmCategory> subNodeList = new ArrayList<PcmCategory>();
		subNodeList = this.sub_list;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		/* 如果有子节点且有继承的属性，则删除原继承的属性 */
		if (parentPropList_old != null && parentPropList_old.size() > 0) {
			if (subNodeList != null && subNodeList.size() > 0) {
				Map<String, Object> paramMaps = new HashMap<String, Object>();
				List<String> cateSidList = new ArrayList<String>();
				List<String> propSidList = new ArrayList<String>();
				for (PcmCategoryPropValues catePropVal : parentPropList_old) {
					propSidList.add(catePropVal.getPropsSid().toString());
				}
				for (PcmCategory subNode : subNodeList) {
					cateSidList.add(subNode.getSid().toString());
				}
				paramMaps.put("cateSidList", cateSidList);
				paramMaps.put("propSidList", propSidList);
				ssdCategoryPropValuesMapper.deleteDragInheritance(paramMaps);
			}
		}
		/* 如果有子节点则添加相对应的数据 */
		if (parentPropList != null && parentPropList.size() > 0) {
			if (subNodeList != null && subNodeList.size() > 0) {
				/* 给所有子类添加相对应的数据 */
				List<PcmCategoryPropValues> new_catePropValList = new ArrayList<PcmCategoryPropValues>();
				for (PcmCategoryPropValues catePropVal : parentPropList) {
					for (PcmCategory subNode : subNodeList) {
						PcmCategoryPropValues new_catePropVal = new PcmCategoryPropValues();
						new_catePropVal.setSid(null);
						new_catePropVal.setChannelSid(catePropVal.getChannelSid());
						new_catePropVal.setPropsSid(catePropVal.getPropsSid());
						new_catePropVal.setPropsName(catePropVal.getPropsName());
						new_catePropVal.setValuesSid(catePropVal.getValuesSid());
						new_catePropVal.setValuesName(catePropVal.getValuesName());
						new_catePropVal.setOptUser(catePropVal.getOptUser());
						new_catePropVal.setNotNull(catePropVal.getNotNull());
						new_catePropVal.setIsErpSyn(catePropVal.getIsErpSyn());
						new_catePropVal.setCategorySid(subNode.getCategorySid());
						new_catePropVal.setCategoryName(subNode.getName());
						new_catePropValList.add(new_catePropVal);
					}
				}
				paramMap.put("batchList", new_catePropValList);
				ssdCategoryPropValuesMapper.insertCategoryPropValBatch(paramMap);
			}
		}
		this.sub_list.clear();
	}

	public void selectList_1() {
		selectAllSubNodeByCateSid("419");
		List<PcmCategory> subNodeList = new ArrayList<PcmCategory>();
		subNodeList = this.sub_list;
		System.out.println(this.sub_list);
		this.sub_list.clear();
		System.out.println(this.sub_list);
		System.out.println(subNodeList);
	}

	@Override
	public void clearSubList() {
		this.sub_list.clear();
	}
}
