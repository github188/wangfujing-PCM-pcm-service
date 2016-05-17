package com.wangfj.product.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.product.category.domain.entity.PcmProductCategory;
import com.wangfj.product.category.persistence.PcmProductCategoryMapper;
import com.wangfj.product.category.service.intf.IProductCategoryService;

/**
 * 维护品类-商品对应关系的接口
 * 
 * @author xuxf
 */
@Component("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	private PcmProductCategoryMapper productCategoryMapper;

	// @Autowired
	// private ProductWithClassMapper productWithClassMapper;

	/**
	 * 删除品类-商品对应关系
	 */
	public int delete(Long sid) {
		return this.productCategoryMapper.deleteByPrimaryKey(sid);
	}

	/**
	 * 添加品类-商品对应关系
	 */
	public int insert(PcmProductCategory record) {
		return this.productCategoryMapper.insertSelective(record);
	}

	/**
	 * 查找品类-商品对应关系
	 */
	public PcmProductCategory get(Long sid) {
		return this.productCategoryMapper.selectByPrimaryKey(sid);
	}

	/**
	 * 更新品类-商品对应关系
	 */
	public int update(PcmProductCategory record) {
		return this.productCategoryMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 用于保持数据源之间的同步
	 */
	// public boolean saveorupdate(Long productSid){
	// boolean flag = true;
	// SsdProductCategory sp = new SsdProductCategory();
	// sp.setProductSid(productSid);
	// List<SsdProductCategory> list1 =
	// this.productCategoryMapper.selectList(sp);
	// List<ProductWithClass> list =
	// this.productWithClassMapper.selectByProductSid(productSid);
	// if (list.size() == 0 || list == null) {
	// if (list1.size() > 0) {
	// for (SsdProductCategory spc : list1) {
	// int v = this.productCategoryMapper.deleteByPrimaryKey(spc.getSid());
	// flag = flag && v==1;
	// }
	// return flag;
	// }
	// return flag;
	// } else {
	// if (list1.size() > 0) {
	// for (SsdProductCategory spc : list1) {
	// int v = this.productCategoryMapper.deleteByPrimaryKey(spc.getSid());
	// flag = flag && v==1;
	// }
	// }
	// for (ProductWithClass pwc : list) {
	// SsdProductCategory pc = new SsdProductCategory();
	// pc.setProductSid(pwc.getProductListSid().longValue());
	// pc.setCategorySid(pwc.getProductClassSid().longValue());
	// int num = this.productCategoryMapper.insertSelective(pc);
	// flag = flag && num==1;
	// }
	// }
	// return flag;
	// }

	public int save(PcmProductCategory record) {
		PcmProductCategory spc = new PcmProductCategory();
		spc.setCategorySid(record.getCategorySid());
		spc.setChannelSid(record.getChannelSid());
		List<PcmProductCategory> list = this.productCategoryMapper.selectList(spc);
		boolean isHave = false;
		if (list.size() > 0) {
			for (PcmProductCategory sp : list) {
				if (sp.getCategorySid().equals(record.getCategorySid())) {
					isHave = true;
					break;
				}
			}
		}
		if (!isHave) {
			return this.productCategoryMapper.insertSelective(record);
		} else {
			return 1;
		}

	}

	public List<PcmProductCategory> selectList(PcmProductCategory record) {
		return this.productCategoryMapper.selectList(record);
	}

	public int deleteByRecord(PcmProductCategory record) {
		return this.productCategoryMapper.deleteByRecord(record);
	}

	/**
	 * 根据record 查询如果为空insert，否则update
	 */
	public int saveorupdate(PcmProductCategory record) {
		PcmProductCategory spc = new PcmProductCategory();
		spc.setCategorySid(record.getCategorySid());
		spc.setChannelSid(record.getChannelSid());
		spc.setProductSid(record.getProductSid());
		List<PcmProductCategory> list = this.productCategoryMapper.selectList(spc);
		if (list == null || list.size() == 0) {
			return this.productCategoryMapper.insertSelective(record);
		} else {
			Long sid = list.get(0).getSid();
			record.setSid(sid);
			return this.productCategoryMapper.updateByPrimaryKeySelective(record);
		}
	}

}
