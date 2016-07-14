package com.wangfj.product.category.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.utils.FormatUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.brand.persistence.PcmBrandCategoryMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.domain.vo.CategoryVO;
import com.wangfj.product.category.domain.vo.ClassDTO;
import com.wangfj.product.category.domain.vo.CriteriaVO;
import com.wangfj.product.category.domain.vo.JsonCate;
import com.wangfj.product.category.domain.vo.NavigationVO;
import com.wangfj.product.category.domain.vo.ProductCateVO;
import com.wangfj.product.category.domain.vo.ProductQueryVO;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.service.intf.ISCategoryService;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.maindata.persistence.PcmProductMapper;

/**
 * 维护品类信息的接口
 * 
 * @author xuxf
 */

@Service
public class SCategoryServiceImpl implements ISCategoryService {

	@Autowired
	private PcmCategoryMapper categoryMapper;
	@Autowired
	private PcmBrandCategoryMapper brandCategoryMapper;
	@Autowired
	private PcmProductMapper ssdProductMapper;

	@Autowired
	private PcmProductCategoryMapper productCategoryMapper;

	@Autowired
	private RedisUtil redisUtil;

	// 查询所有品类
	public List<PcmCategory> getAll() {
		return this.categoryMapper.getAll();
	}

	/**
	 * 从数据库删除一条品类信息
	 */
	@Transactional
	public int delete(Long sid) {
		return this.categoryMapper.deleteByPrimaryKey(sid);
	}

	/**
	 * 向数据库插入一条品类信息
	 */
	@Transactional
	public int insert(PcmCategory record) {
		return this.categoryMapper.insertSelective(record);
	}

	/**
	 * 根据品类ID获取品类信息
	 */
	public PcmCategory get(Long sid) {
		return this.categoryMapper.selectByPrimaryKey(sid);
	}

	/**
	 * 根据品类ID更新品类信息
	 */
	@Transactional
	public int update(PcmCategory record) {
		return this.categoryMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 遍历所有品类信息
	 */
	public List<PcmCategory> selectList(PcmCategory record) {
		return this.categoryMapper.selectPageListByParam(record);
	}

	/**
	 * 查找父类下的所有子类
	 */
	public List<PcmCategory> getByParentSid(String parentSid) {
		PcmCategory category = new PcmCategory();
		category.setParentSid(parentSid);
		return this.categoryMapper.selectPageListByParam(category);
	}

	/**
	 * 根据指定的ID找出相应的品类
	 */
	public List<PcmCategory> selectListIn(List<Long> cateIds) {
		return this.categoryMapper.selectListIn(cateIds);
	}

	/**
	 * 用于生成查询条件
	 * 
	 * @param queryVO
	 *            商品查询对象
	 * @param orderBy
	 *            是否排序
	 * @return 条件列表
	 */
	private CriteriaVO getQuery(ProductQueryVO queryVO, boolean orderBy) {
		CriteriaVO criteriaVO = new CriteriaVO();
		criteriaVO.setPresentFlg(1L);
		criteriaVO.setActivityFlg(1L);
		criteriaVO.setProType(1L);
		criteriaVO.setProSelling(1L);
		if (queryVO == null) {
			return criteriaVO;
		}
		// 品牌
		if (queryVO.getProBrand() != null && !"".equals(queryVO.getProBrand().trim())) {
			criteriaVO.setBrandSid(Long.valueOf(queryVO.getProBrand().trim()));
		}
		// 类别
		if (queryVO.getProCate() != null && !"".equals(queryVO.getProCate())) {
			Integer[] cateIds = queryVO.getProCates();
			if (cateIds != null && cateIds.length != 0) {
				List<Long> cateList = new ArrayList<Long>();
				for (Integer cateId : cateIds) {
					cateList.add(Long.valueOf(cateId));
				}
				List<PcmCategory> cats = this.categoryMapper.selectListIn(cateList);
				if (cats != null && !cats.isEmpty()) {
					StringBuffer sb = new StringBuffer();
					for (PcmCategory cat : cats) {
						if (cat.getSid() == null || cat.getIsParent() == null
								|| cat.getLevel() == null) {
							continue;
						}
						String sid = cat.getCategorySid();
						long isParent = cat.getIsParent();
						long level = cat.getLevel();
						if (isParent == 0) {
							if (sb.length() == 0) {
								sb.append("sc.category_sid=" + sid);
							} else {
								sb.append(" or sc.category_sid=" + sid);
							}
						} else if (level == 1) {
							if (sb.length() == 0) {
								sb.append("sc.root_sid=" + sid);
							} else {
								sb.append(" or sc.root_sid=" + sid);
							}
						} else if (level == 2) {
							if (sb.length() == 0) {
								sb.append("sc.parent_sid=" + sid);
							} else {
								sb.append(" or sc.parent_sid=" + sid);
							}
							List<PcmCategory> cs = this.getByParentSid(sid);
							for (PcmCategory c : cs) {
								if (c.getIsParent() == 1) {
									if (sb.length() == 0) {
										sb.append("sc.parent_sid=" + c.getSid());
									} else {
										sb.append(" or sc.parent_sid=" + c.getSid());
									}
								}
							}
						} else {
							if (sb.length() == 0) {
								sb.append("sc.parent_sid=" + sid);
							} else {
								sb.append(" or sc.parent_sid=" + sid);
							}
						}
					}
					String queryCate = sb.toString();
					criteriaVO.setQueryCate(queryCate);
				}
			}
		}

		// 折扣
		if (queryVO.getSoffprice() != null && queryVO.getEoffprice() != null
				&& !"".equals(queryVO.getSoffprice().trim())
				&& !"".equals(queryVO.getEoffprice().trim())) {
			StringBuffer sb = new StringBuffer();
			sb.append("sp.off_value>" + Double.valueOf(queryVO.getSoffprice().trim())
					+ " and sp.off_value<=" + Double.valueOf(queryVO.getEoffprice().trim()));
			String offValue = sb.toString();
			criteriaVO.setOffprice(offValue);
		}

		// 价格
		if (queryVO.getSprice() != null && queryVO.getEprice() != null
				&& !"".equals(queryVO.getSprice().trim()) && !"".equals(queryVO.getEprice().trim())) {
			StringBuffer sb = new StringBuffer();
			sb.append("sp.promotion_price>" + Double.valueOf(queryVO.getSprice().trim())
					+ " and sp.promotion_price<=" + Double.valueOf(queryVO.getEprice().trim()));
			String price = sb.toString();
			criteriaVO.setPrice(price);
		}

		// 上架天数
		if (queryVO.getRecent() != null && !"".equals(queryVO.getRecent().trim())) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt("-" + queryVO.getRecent().trim()));
			criteriaVO.setRecent(cal.getTime());
		}

		// 款式
		if (queryVO.getStyle() != null && !"".equals(queryVO.getStyle().trim())) {
			criteriaVO.setSexId(Long.valueOf(queryVO.getStyle().trim()));
		}

		// 排序方式
		if (orderBy) {
			if (queryVO.getOrderByPrice() != null && !"".equals(queryVO.getOrderByPrice().trim())) {
				int orderByPrice = Integer.valueOf(queryVO.getOrderByPrice());
				if (orderByPrice == 0) {
					criteriaVO.setOrderByPrice("asc");
				} else {
					criteriaVO.setOrderByPrice("desc");
				}
			}
			if (queryVO.getOrderByTime() != null && !"".equals(queryVO.getOrderByTime().trim())) {
				int orderByTime = Integer.valueOf(queryVO.getOrderByTime().trim());
				if (orderByTime == 0) {
					criteriaVO.setOrderByTime("asc");
				} else {
					criteriaVO.setOrderByTime("desc");
				}
			}
		}
		return criteriaVO;
	}

	/**
	 * 由叶子节点构造目录树
	 * 
	 * @param map
	 *            类目
	 * @return 目录树
	 */
	private ProductCateVO getCateTree(Map<String, ProductCateVO> map) {
		Iterator<ProductCateVO> iterator = map.values().iterator();
		TreeMap<String, ProductCateVO> newmap = new TreeMap<String, ProductCateVO>();
		while (iterator.hasNext()) {
			ProductCateVO catVO = iterator.next();
			PcmCategory cat = this.get(Long.valueOf(catVO.getSid()));
			catVO.setNodeName(cat.getName());
			catVO.setNodeLevel(cat.getLevel() + "");
			catVO.setNodeSeq(cat.getSortOrder() + "");
			String sortOrder = FormatUtil.formatBinary(cat.getSortOrder().toString(), 2);
			String level = FormatUtil.formatBinary(cat.getLevel() + "", 2);
			String cateSid = FormatUtil.formatBinary(cat.getSid().toString(), 4);
			String catekey = level + sortOrder + cateSid;

			// g感觉这有问题
			PcmCategory parentCate = this.get(Long.parseLong(cat.getParentSid()));
			ProductCateVO parentVO = new ProductCateVO();
			if (parentCate != null) {
				String parentLevel = FormatUtil.formatBinary(parentCate.getLevel() + "", 2);
				String parentOrder = FormatUtil.formatBinary(parentCate.getSortOrder().toString(),
						2);
				String parentSid = FormatUtil.formatBinary(parentCate.getSid().toString(), 4);
				String parentkey = parentLevel + parentOrder + parentSid;
				if (newmap.containsKey(parentkey)) {
					parentVO = newmap.get(parentkey);
				}
				Map<String, ProductCateVO> list = parentVO.getChildCatesMap();
				if (list == null) {
					list = new HashMap<String, ProductCateVO>();
				}
				if (list.containsKey(catekey)) {
					ProductCateVO categoryVO = list.get(catekey);
					categoryVO.getChildCatesMap().putAll(catVO.getChildCatesMap());
					categoryVO.setProductCount(categoryVO.getProductCount()
							+ catVO.getProductCount());
				} else {
					list.put(catekey, catVO);
				}
				parentVO.setSid(parentCate.getSid() + "");
				parentVO.setNodeName(parentCate.getName());
				parentVO.setProductCount(parentVO.getProductCount() + catVO.getProductCount());
				parentVO.setChildCatesMap(list);
				newmap.put(parentkey, parentVO);
			} else {
				if (newmap.containsKey(catekey)) {
					parentVO = newmap.get(catekey);
					String parentLevel = FormatUtil.formatBinary(parentVO.getNodeLevel() + "", 2);
					String parentOrder = FormatUtil.formatBinary(parentVO.getNodeSeq().toString(),
							2);
					String parentSid = FormatUtil.formatBinary(parentVO.getSid().toString(), 4);
					String parentkey = parentLevel + parentOrder + parentSid;
					Map<String, ProductCateVO> list = parentVO.getChildCatesMap();
					if (list == null) {
						list = new HashMap<String, ProductCateVO>();
					}
					if (list.containsKey(catekey)) {
						ProductCateVO categoryVO = list.get(catekey);
						categoryVO.getChildCatesMap().putAll(catVO.getChildCatesMap());
						categoryVO.setProductCount(categoryVO.getProductCount()
								+ catVO.getProductCount());
					} else {
						list.put(catekey, catVO);
					}
					parentVO.setProductCount(parentVO.getProductCount() + catVO.getProductCount());
					parentVO.setChildCatesMap(list);
					newmap.put(parentkey, parentVO);
				} else {
					newmap.put(catekey, catVO);
				}
			}
		}
		if (newmap.size() == 1 && newmap.containsKey("00000001")) {
			return newmap.get("00000001");
		} else {
			return getCateTree(newmap);
		}
	}

	/**
	 * 当前查询条件下的分类, 需要统计每个叶子类别下的商品数量.
	 * 
	 * @param queryVO
	 *            条件列表
	 * @return 返回带有商品数量的类目树
	 */
	public ProductCateVO findCurrentCateList(ProductQueryVO queryVO) {
		CriteriaVO criteriaVO = getQuery(queryVO, false);
		List<ProductCateVO> list = this.categoryMapper.selectCateVOList(criteriaVO);
		if (list == null || list.isEmpty()) {
			return new ProductCateVO();
		}
		Map<String, ProductCateVO> map = new TreeMap<String, ProductCateVO>();// 叶子
		for (ProductCateVO pc : list) {
			if (pc == null)
				continue;
			ProductCateVO vo = new ProductCateVO();
			vo.setProductCount(pc.getProductCount());
			String seq = pc.getNodeSeq() + "";
			String level = pc.getNodeLevel() + "";
			vo.setSid(pc.getSid());
			String sid = pc.getSid() + "";
			map.put(level + seq + sid, vo);
		}
		return getCateTree(map);
	}

	/**
	 * 返回同一父类下的子类最大的序列号
	 */
	public Integer getMaxSortOrder(String parentSid, Long channelSid) {
		List<PcmCategory> list = getByParentSidAndChannelSid(parentSid, channelSid, null, null);
		Integer max = 0;
		if (list.size() > 0) {
			for (PcmCategory cat : list) {
				if (max < cat.getSortOrder()) {
					max = cat.getSortOrder();
				}
			}
		}
		return max;
	}

	/**
	 * 用于返回当前品类下的商品数量
	 */
	// public int getProCount(Long categorySid, Long channelSid) {
	// PcmCategory category = getByCategorySidAndChannelSid(categorySid,
	// channelSid);
	// if (category.getIsParent() == 0) {
	// PcmCategory record = new PcmCategory();
	// record.setCategorySid(categorySid);
	// record.setChannelSid(channelSid);
	// String obj = this.categoryMapper.selectProCount(record);
	// int num = 0;
	// if (obj != null) {
	// num = Integer.valueOf(obj);
	// }
	// return num;
	// } else {
	// List<PcmCategory> cates = this.getByParentSidAndChannelSid(categorySid,
	// channelSid);
	// int sum = 0;
	// for (PcmCategory cate : cates) {
	// sum += getProCount(cate.getCategorySid(), cate.getChannelSid());
	// }
	// return sum;
	// }
	// return this.ssdProductMapper.selectProCount(categorySid);
	// }

	/**
	 * 根据商品ID返回所属的品类ID及其父节点ID,根节点ID,以","分开
	 */
	public String getCateString(Long productSid) {
		PcmProductCategory spc = new PcmProductCategory();
		spc.setProductSid(Long.valueOf(productSid.toString()));
		List<PcmProductCategory> list = this.productCategoryMapper.selectList(spc);
		Set<Long> cateIds = new HashSet<Long>();
		for (PcmProductCategory pc : list) {
			cateIds.add(pc.getCategorySid());
			Long categorySid = pc.getCategorySid();
			while (true) {
				PcmCategory cate = this.get(categorySid);
				categorySid = Long.parseLong(cate.getParentSid());
				if (categorySid == 1) {
					break;
				}
				cateIds.add(categorySid);
			}
		}
		StringBuffer sb = new StringBuffer();
		Iterator<Long> it = cateIds.iterator();
		while (it.hasNext()) {
			sb.append(it.next() + ",");
		}
		if (sb.length() == 0) {
			return null;
		} else {
			return sb.toString();
		}
	}

	public Map<Long, JsonCate> getCateJSON(Long productSid) {
		PcmProductCategory spc = new PcmProductCategory();
		Map<Long, JsonCate> map = new HashMap<Long, JsonCate>();
		spc.setProductSid(Long.valueOf(productSid.toString()));
		Set<JsonCate> set = new HashSet<JsonCate>();
		List<PcmProductCategory> list = this.productCategoryMapper.selectList(spc);
		for (PcmProductCategory pc : list) {
			Long categorySid = pc.getCategorySid();
			Long channelSid = pc.getChannelSid();
			JsonCate obj = new JsonCate();
			PcmCategory sc = getByCategorySidAndChannelSid(categorySid.toString(), channelSid);
			String parentSid = sc.getParentSid();
			while (true) {
				List<PcmCategory> scs = getByParentSidAndChannelSid(parentSid, channelSid, null,
						null);
				Set<JsonCate> jcs = new HashSet<JsonCate>();
				if (scs.size() > 0) {
					for (PcmCategory ss : scs) {
						Iterator<JsonCate> it = set.iterator();
						boolean flag = false;
						while (it.hasNext()) {
							JsonCate tm = it.next();
							if (tm.getId().equals(ss.getSid())) {
								obj = tm;
								flag = true;
								break;
							}
						}
						if (flag) {
							jcs.add(obj);
						} else {
							JsonCate jc = new JsonCate();
							jc.setId(ss.getSid());
							jc.setText(ss.getName());
							if (ss.getIsParent() == 1) {
								jc.setState("closed");
							} else {
								jc.setState("open");
							}
							jcs.add(jc);
						}
					}
				}
				PcmCategory cate = this.getByCategorySidAndChannelSid(parentSid, channelSid);
				JsonCate pjcate = new JsonCate();
				pjcate.setId(cate.getSid());
				pjcate.setText(cate.getName());
				pjcate.setState("closed");
				pjcate.setChildren(jcs);
				obj = pjcate;
				set.add(pjcate);
				parentSid = cate.getParentSid();
				if (parentSid.equals(0)) {
					break;
				}
			}
			if (map.containsKey(channelSid)) {
				map.remove(channelSid);
				map.put(channelSid, obj);
			} else {
				map.put(channelSid, obj);
			}
		}
		List<PcmCategory> ssd = getByParentSid("0");
		for (PcmCategory ss : ssd) {
			if (!map.containsKey(ss.getChannelSid())) {
				JsonCate obj = new JsonCate();
				obj.setId(ss.getSid());
				obj.setState("closed");
				obj.setText(ss.getName());
				map.put(ss.getChannelSid(), obj);
			}
		}
		return map;
	}

	/**
	 * 根据品类ID与渠道ID查询品类信息
	 */
	public PcmCategory getByCategorySidAndChannelSid(String categorySid, Long channelSid) {
		PcmCategory sc = new PcmCategory();
		sc.setCategorySid(categorySid);
		sc.setChannelSid(channelSid);
		List<PcmCategory> list = this.categoryMapper.selectPageListByParam(sc);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据商品ID查询该商品所属的品类信息
	 */
	public List<PcmCategory> selectByProductSid(long productSid) {

		return this.categoryMapper.selectByProductSid(productSid);
	}

	/**
	 * 根据品类ID与渠道ID查询当前品类下的所有子品类信息
	 */
	public List<PcmCategory> getByParentSidAndChannelSid(String parentSid, Long channelSid,
			String categoryType, String shopSid) {
		CategoryVO sc = new CategoryVO();
		sc.setParentSid(parentSid);
		sc.setChannelSid(channelSid);
		sc.setShopSid(shopSid);
		sc.setIsDisplay(1);
		List<String> listS = new ArrayList<String>();
		if (categoryType != null) {
			String[] s = categoryType.split(",");
			for (String string : s) {
				listS.add(string);
			}
			sc.setCategoryType(listS);
		} else {
			sc.setCategoryType(null);
		}
		List<PcmCategory> list = this.categoryMapper.selectPageListByCategoryVO(sc);
		return list;
	}

	/**
	 * 根据品类ID与渠道ID查询当前品类下的所有子品类信息(缓存)
	 */
	@Override
	// @RedisCacheGet(redisName = DomainName.selectCateGory, isList = true,
	// returnObj = "com.wangfj.product.category.domain.entity.PcmCategory")
	public List<PcmCategory> getByParentSidAndChannelSidCache(String parentSid, Long channelSid,
			String categoryType, String shopSid) {
		List<PcmCategory> list = null;
		String json = redisUtil.get(DomainName.selectCateGory + parentSid, "00000");
		if (!"00000".equals(json)) {
			list = JsonUtil.getListDTO(json, PcmCategory.class);
		} else {
			CategoryVO sc = new CategoryVO();
			sc.setParentSid(parentSid);
			sc.setChannelSid(channelSid);
			sc.setShopSid(shopSid);
			sc.setIsDisplay(1);
			List<String> listS = new ArrayList<String>();
			if (categoryType != null) {
				String[] s = categoryType.split(",");
				for (String string : s) {
					listS.add(string);
				}
				sc.setCategoryType(listS);
			} else {
				sc.setCategoryType(null);
			}
			list = this.categoryMapper.selectPageListByCategoryVO(sc);
			if (list != null && list.size() != 0) {
				redisUtil.set(DomainName.selectCateGory + parentSid, JsonUtil.getJSONString(list));
			}
		}
		return list;
	}

	/**
	 * 对当前品类的信息做添加或更新操作
	 */
	@Transactional
	public int saveorupdate(PcmCategory record) {
		PcmCategory sc = this.getByCategorySidAndChannelSid(record.getCategorySid(),
				record.getChannelSid());
		if (sc == null) {
			return this.categoryMapper.insertSelective(record);
		} else {
			sc.setIsParent(record.getIsParent());
			sc.setIsSelfBuilt(record.getIsSelfBuilt());
			sc.setName(record.getName());
			sc.setParentSid(record.getParentSid());
			sc.setSortOrder(record.getSortOrder());
			sc.setStatus(record.getStatus());
			return this.categoryMapper.updateByPrimaryKeySelective(sc);
		}
	}

	/**
	 * 修改当前品类的信息
	 */
	public int modify(PcmCategory record) {
		PcmCategory cate = new PcmCategory();
		cate.setChannelSid(record.getChannelSid());
		cate.setCategorySid(record.getCategorySid());
		List<PcmCategory> list = this.categoryMapper.selectPageListByParam(cate);
		if (list != null && list.size() > 0) {
			record.setSid(list.get(0).getSid());
			return this.categoryMapper.updateByPrimaryKeySelective(record);
		}
		return 1;
	}

	// @MethodCache(expire = 1800)
	public List<ClassDTO> selectCategory(PcmCategory record) {
		return this.categoryMapper.selectCategory(record);
	}

	private List<PcmCategory> getByParentSidAndChanSid(String parentSid, Long channelSid) {
		PcmCategory sc = new PcmCategory();
		sc.setParentSid(parentSid);
		sc.setIsDisplay(1);
		sc.setChannelSid(channelSid);
		return this.categoryMapper.selectPageListByParam(sc);
	}

	/**
	 * 根据一级品类ID遍历其下所有子品类及其下的子品类
	 */
	public NavigationVO getSubCateList(String sid, String categorySid, String channelSid) {
		NavigationVO nv = new NavigationVO();
		nv.setSid(Integer.valueOf(sid));
		String[] cateIds = categorySid.split(",");
		List<NavigationVO> seNVList = new ArrayList<NavigationVO>();
		StringBuffer sb = new StringBuffer();
		for (String cateId : cateIds) {
			PcmCategory sc = getByCategorySidAndChannelSid(cateId, Long.valueOf(channelSid));
			sb.append(sc.getName() + "、");

			List<PcmCategory> seCateList = getByParentSidAndChanSid(cateId,
					Long.valueOf(channelSid));
			for (PcmCategory seCate : seCateList) {
				NavigationVO se = new NavigationVO();
				se.setName(seCate.getName());
				se.setCates(seCate.getSearchPath());
				List<NavigationVO> thNVList = new ArrayList<NavigationVO>();
				List<PcmCategory> thCateList = getByParentSidAndChanSid(seCate.getCategorySid(),
						Long.valueOf(channelSid));
				for (PcmCategory thCate : thCateList) {
					NavigationVO th = new NavigationVO();
					th.setName(thCate.getName());
					th.setCates(thCate.getSearchPath());
					thNVList.add(th);
				}
				se.setSubList(thNVList);
				seNVList.add(se);
			}
		}
		sb = sb.deleteCharAt(sb.lastIndexOf("、"));
		nv.setName(sb.toString());
		nv.setSubList(seNVList);
		return nv;
	}

	/**
	 * 修改品类名称
	 */
	public int updateCate(PcmCategory record) {
		return this.categoryMapper.updateCate(record);
	}

	@Transactional
	public int save(List<PcmCategory> list) {
		int flag = 0;
		Integer min = list.get(0).getLevel();
		String categorySid = list.get(0).getCategorySid();
		Long channelSid = list.get(0).getChannelSid();
		for (PcmCategory sc : list) {
			channelSid = sc.getChannelSid();
			if (min > sc.getLevel()) {
				min = sc.getLevel();
				categorySid = sc.getCategorySid();
			}
		}
		deleteRecursive(categorySid, channelSid);
		flag = this.categoryMapper.insertBatch(list);
		return flag;
	}

	@Transactional
	private void deleteRecursive(String categorySid, Long channelSid) {
		List<PcmCategory> list = getByParentSidAndChannelSid(categorySid, channelSid, null, null);
		if (list == null || list.size() == 0) {
			this.categoryMapper.deleteCate(categorySid, channelSid);
			return;
		} else {
			for (PcmCategory sc : list) {
				deleteRecursive(sc.getCategorySid(), channelSid);
			}
			this.categoryMapper.deleteCate(categorySid, channelSid);
		}
	}

	@Transactional
	public int deleteCate(PcmCategory sc) {
		return this.categoryMapper.deleteCate(sc.getCategorySid(), sc.getChannelSid());
	}

	@Transactional
	public int insertBatch(List<PcmCategory> list) {
		return this.categoryMapper.insertBatch(list);
	}

	/**
	 * 根据品类ID与渠道ID查询当前品类下的所有子品类信息
	 */
	// @MethodCache(expire = 1800)
	public List<PcmCategory> getByParentSidChannelSid(PcmCategory record) {
		return this.categoryMapper.selectPageListByParam(record);
	}

	/**
	 * 根据品类ID与渠道ID查询品类信息
	 */
	// @MethodCache(expire = 1800)
	public PcmCategory getByCategorySidChannelSid(PcmCategory record) {
		List<PcmCategory> list = this.categoryMapper.selectPageListByParam(record);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// @MethodCache(expire = 1800)
	public List<PcmCategory> selectCategoryList(Long channelSid) {
		PcmCategory sc = new PcmCategory();
		sc.setChannelSid(channelSid);
		return this.categoryMapper.selectPageListByParam(sc);
	}

	/**
	 * 门店系统,pad系统专用接口 返回当前节点的父节点或子节点
	 * 
	 * @param categorySid
	 *            当前节点的ID
	 * @param channelSid
	 * @param flag
	 *            0返回父节点 1返回子节点
	 * @return
	 */
	public List<PcmCategory> getSubOrPaNode(String categorySid, String channelSid, String flag) {
		if ("0".equals(flag)) {
			PcmCategory sc = new PcmCategory();
			sc.setCategorySid(categorySid);
			sc.setChannelSid(Long.valueOf(channelSid));
			List<PcmCategory> ts = this.categoryMapper.selectPageListByParam(sc);
			if (ts != null && ts.size() > 0) {
				PcmCategory sctemp = ts.get(0);
				if (sctemp.getLevel().longValue() >= 2) {
					PcmCategory param = new PcmCategory();
					param.setCategorySid(sctemp.getParentSid());
					param.setChannelSid(Long.valueOf(channelSid));
					return this.categoryMapper.selectPageListByParam(param);
				}
				return null;
			} else {
				return null;
			}
		} else if ("1".equals(flag)) {
			PcmCategory sc = new PcmCategory();
			sc.setParentSid(categorySid);
			sc.setChannelSid(Long.valueOf(channelSid));
			return this.categoryMapper.selectPageListByParam(sc);
		} else {
			return null;
		}
	}

	// @Override
	// public Map<Long, JsonCate> getCateJSON2(Long brandSid) {
	// PcmBrandCategory sbc = new PcmBrandCategory();
	// Map<Long, JsonCate> map = new HashMap<Long, JsonCate>();
	// sbc.setBrandSid(brandSid);
	// Set<JsonCate> set = new HashSet<JsonCate>();
	// // 根据品牌id查询中间 表，再根据品类ID和渠道ID查询品类表，得到parentSid
	// List<PcmBrandCategory> list =
	// this.brandCategoryMapper.selectPageListByParam(sbc);
	// for (PcmBrandCategory pc : list) {
	// Long categorySid = pc.getCategorySid();
	// Long channelSid = pc.getChannelSid();
	// JsonCate obj = new JsonCate();
	// PcmCategory sc = getByCategorySidAndChannelSid(categorySid.toString(),
	// channelSid);
	// String parentSid = sc.getParentSid();
	// // 根据parentSid和channelSid查询对应parentSid的子品类
	// while (true) {
	// List<PcmCategory> scs = getByParentSidAndChannelSid(parentSid,
	// channelSid, null,
	// null);
	// Set<JsonCate> jcs = new HashSet<JsonCate>();
	// if (scs.size() > 0) {
	// // 遍历子品类
	// for (PcmCategory ss : scs) {
	// Iterator<JsonCate> it = set.iterator();
	// boolean flag = false;
	// while (it.hasNext()) {
	// JsonCate tm = it.next();
	// if (tm.getId().equals(ss.getSid())) {
	// obj = tm;
	// flag = true;
	// break;
	// }
	// }
	// if (flag) {
	// jcs.add(obj);
	// } else {
	// JsonCate jc = new JsonCate();
	// for (PcmBrandCategory bb : list) {
	// if (ss.getCategorySid().equals(bb.getCategorySid())) {
	// jc.setChecked(true);
	// break;
	// } else {
	// jc.setChecked(false);
	// }
	// }
	// jc.setId(ss.getSid());
	// jc.setText(ss.getName());
	// if (ss.getIsParent() == 1) {
	// jc.setState("closed");
	// } else {
	//
	// jc.setState("open");
	// }
	// jcs.add(jc);
	// }
	// }
	// }
	// // 查询父节点品类，将父节点sid,name,子节点存入JSONCate
	// PcmCategory cate = this.getByCategorySidAndChannelSid(parentSid,
	// channelSid);
	// JsonCate pjcate = new JsonCate();
	//
	// pjcate.setId(cate.getSid());
	// pjcate.setText(cate.getName());
	// pjcate.setState("closed");
	// pjcate.setChildren(jcs);
	// obj = pjcate;
	// set.add(pjcate);
	// parentSid = cate.getParentSid();
	// if (parentSid.equals(0)) {
	// break;
	// }
	// }
	// if (map.containsKey(channelSid)) {
	// map.remove(channelSid);
	// map.put(channelSid, obj);
	// } else {
	// map.put(channelSid, obj);
	// }
	// }
	// // 查询顶级节点的子品类，将顶级品类的sid,name
	// List<PcmCategory> ssd = getByParentSid("0");
	// for (PcmCategory ss : ssd) {
	// System.out.println(ss.getChannelSid());
	// if (!map.containsKey(ss.getChannelSid())) {
	// JsonCate obj = new JsonCate();
	// obj.setId(ss.getSid());
	// obj.setState("closed");
	// obj.setText(ss.getName());
	// map.put(ss.getChannelSid(), obj);
	// }
	// }
	// return map;
	// }

	// 此方法用于查询三级节点，生成规格表数据
	public List<PcmCategory> selectByRootSid(PcmCategory record) {

		return this.categoryMapper.selectByRootSid(record);
	}

	@Override
	public List<Map<String, Object>> getProductStanCategorys(Map map) {
		return this.categoryMapper.getProductStanCategorys(map);
	}

}
