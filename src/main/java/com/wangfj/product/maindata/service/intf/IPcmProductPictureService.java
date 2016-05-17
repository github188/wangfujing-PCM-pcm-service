package com.wangfj.product.maindata.service.intf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmPictureUrl;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProductMemo;
import com.wangfj.product.maindata.domain.vo.PcmPictureDto;
import com.wangfj.product.maindata.domain.vo.PcmPictureInfoDto;
import com.wangfj.product.maindata.domain.vo.PcmPictureUrlDto;
import com.wangfj.product.maindata.domain.vo.PhotoStatusDto;
import com.wangfj.product.maindata.domain.vo.ProCateChannelDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.stocks.domain.vo.PcmSapWsDto;
import com.wangfj.product.stocks.domain.vo.PcmSpuSkuProInfoDto;

/**
 * 商品描述
 * 
 * @Class Name IPcmProductMemo
 * @Author kongqf
 * @Create In 2015年9月18日
 */
public interface IPcmProductPictureService {
	public List<Map<String, Object>> picOldToNew(Map<String, Object> paramMap);

	/**
	 * 
	 * @Methods Name redisOmsDelInfo
	 * @Create In 2016年4月8日 By yedong
	 * @param spuCode
	 *            void
	 */
	public void redisOmsDelInfo(String spuCode);

	/**
	 * SAP获取图片
	 * 
	 * @Methods Name getUrlBySpuCodeAndColor
	 * @Create In 2016年3月16日 By yedong
	 * @param spuSkuProPara
	 * @return List<PcmSapResUrlDto>
	 */
	public PcmSapWsDto getUrlBySpuCodeAndColor(PcmSpuSkuProInfoDto spuSkuProPara);

	public List<ProCateChannelDto> getCatePropBySpuCode(PhotoStatusDto para);

	/**
	 * sku基础查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年3月3日 By yedong
	 * @param record
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectListByParam(PcmProDetail record);

	/**
	 * 删除单品页缓存
	 * 
	 * @Methods Name redisSpuCMSSHopperInfo
	 * @Create In 2015年12月23日 By yedong
	 * @param spuCode
	 *            void
	 */
	public void redisSpuCMSSHopperInfo(String spuCode);

	/**
	 * 根据SKU编码查询文描信息
	 * 
	 * @Methods Name selectProductMemoBySKUCode
	 * @Create In 2015-12-18 By wangc
	 * @param skuCode
	 * @return PcmProductMemo
	 */
	public HashMap<String, Object> selectProductMemoBySKUCode(String skuCode);

	/**
	 * 商品描述添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年9月18日 By kongqf
	 * @param record
	 * @return boolean
	 */
	public boolean insertSelective(PcmProductMemo record);

	/**
	 * 商品描述修改
	 * 
	 * @Methods Name updateByPrimaryKey
	 * @Create In 2015年9月18日 By kongqf
	 * @param record
	 * @return int
	 */
	public boolean updateByPrimaryKey(PcmProductMemo record);

	/**
	 * 保存商品图片信息
	 * 
	 * @Methods Name SaveProductPictureInfo
	 * @Create In 2015年9月21日 By kongqf
	 * @param dto
	 * @return boolean
	 */
	public List<Map<String, Object>> SaveProductPictureInfo(PcmPictureInfoDto dto);

	/**
	 * 根据产品编码,色系查询商品图片
	 * 
	 * @Methods Name queryPrctureInfoByPara
	 * @Create In 2015年10月14日 By zhangxy
	 * @param spuCode
	 * @param colorSid
	 * @return List<PcmPictureUrl>
	 */
	public List<PcmPictureUrl> queryPrctureInfoByPara(PcmPictureDto dto);

	public List<Map<String, Object>> queryColorBySpu(String spuCode);

	public Integer getSortByOara(PcmPictureDto dto);

	/**
	 * 根据SID,修改商品图片信息
	 * 
	 * @Methods Name updatePrctureInfoBySid
	 * @Create In 2015年10月15日 By yedong
	 * @param dto
	 * @return Boolean
	 */
	public ProSkuSpuPublishDto updatePrctureInfoBySid(List<PcmPictureUrlDto> dtoList);

	/**
	 * 根据产品编码,色系修改商品拍照状态
	 * 
	 * @Methods Name updatePhotoStatus
	 * @Create In 2015年10月21日 By zhangxy void
	 */
	public void updatePhotoStatus(List<PhotoStatusDto> list) throws BleException;
}
