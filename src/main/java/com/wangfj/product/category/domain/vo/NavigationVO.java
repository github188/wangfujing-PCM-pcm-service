/**
 * 说明:
 *     网站三版左侧导航vo,可扩展
 * @Probject Name: business
 * @Path: com.wangfj.view.NavitationVO.java
 * @Create By chengsj
 * @Create In 2013-7-19 下午1:58:12
 *
 */
package com.wangfj.product.category.domain.vo;

import java.io.Serializable;
import java.util.List;



/**
 * @Class Name NavitationVO
 * @Author chengsj
 * @Create In 2013-7-19
 */
public class NavigationVO implements Serializable{
	
	private Integer sid;//导航主键标识位
	private String  name;//导航名称
	private String  link;//导航链接(支持运营人员编辑)
	private String  icon;//前台显示样式(包含图片,下划线,字体颜色)(后期可扩展)\
	private String brandLink;//
	private String seq;//导航展示的顺序-升序
	private String show;//是否显示标志位
	private List<NavigationVO> subList;//一级导航弹出扩展层的二级导航列表(与secNavList显示位置不同,entity中flag标识不同)
//	private List<PromotionVO> promotions;//导航下的促销活动列表
	private List<BrandVO> brandList;//导航下的品牌列表
	
	private String cates;
	
	private String keyWord;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public List<NavigationVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NavigationVO> subList) {
		this.subList = subList;
	}
//	public List<PromotionVO> getPromotions() {
//		return promotions;
//	}
//	public void setPromotions(List<PromotionVO> promotions) {
//		this.promotions = promotions;
//	}
	public List<BrandVO> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<BrandVO> brandList) {
		this.brandList = brandList;
	}
	public String getBrandLink() {
		return brandLink;
	}
	public void setBrandLink(String brandLink) {
		this.brandLink = brandLink;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getCates() {
		return cates;
	}
	public void setCates(String cates) {
		this.cates = cates;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	
	

}
