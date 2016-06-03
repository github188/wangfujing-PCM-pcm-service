/*
 * @(#)Constants.java 2015-7-10
 * 
 * 王府井集团拥有完全的版权
 * 使用者必须经过许可
 */
package com.wangfj.util;

import java.math.BigDecimal;

/**
 * Constant Definition/定义的常量
 *
 * @Class Name Constants
 * @Author wangsy
 * @Create In 2015年7月10日
 */
public interface Constants {
	/**
	 * *************** CODE STYLE ********************
	 */
	public static final Integer FLAG_YES = 0;
	public static final String ARROW = " -> ";
	public static final String COLON = " : ";
	public static final String STRING_EMPTY_SPACE = " ";
	public static final String COMA = " , ";
	public static final String EXCLAMATION = " ! ";
	public static final String START = "Start ";
	public static final String END = "End ";
	public static final String DATE = " Date: ";
	public static final String EMPTY_LIST_ARRAY = "Empty List/ Array returned";
	public static final String NEW_LINE = "\n";
	public static final String WILDCARD = "%";

	/**
	 * *************** SYSTEM RELATED SECTION - START ********************
	 */
	public static final String SYS_ERR_404 = "SYS_ERR_404";
	public static final String SYS_ERR_404_DES = "Resource not found";
	public static final String SYS_ERR_404_DES_CN = "未找到资源";

	public static final String INV_ERR_001 = "INV_ERR_001";
	public static final String INV_ERR_001_DES = "The resource has been existed";
	public static final String INV_ERR_001_DES_CN = "资源已经存在";

	public static final String SYS_ERR_500 = "SYS_ERR_500";
	public static final String SYS_ERR_500_DES = "Internal System Error";
	public static final String SYS_ERR_500_DES_CN = "系统内部错误";

	public static final String VALIDATION_EXCEPTION = "VALIDATION_EXCEPTION";
	public static final String VALIDATION_EXCEPTION_DES_CN = "Validation Problem";
	public static final String DUPLICATE_ENTITY_EXCEPTION = "DUPLICATE_ENTITY_EXCEPTION";

	/**
	 * 数据格式错误
	 */
	public static final String INV_ERR_003 = "INV_ERR_003";
	public static final String INV_ERR_003_DES = "Wrong data format";
	public static final String INV_ERR_003_DES_CN = "数据格式错误";
	/**
	 * 数据为空
	 */
	public static final String INV_ERR_004 = "INV_ERR_004";
	public static final String INV_ERR_004_DES = "Cannot be null ";
	public static final String INV_ERR_004_DES_CN = "不能为空";
	/**
	 * 没有通过数据校验
	 */
	public static final String INV_ERR_006 = "INV_ERR_006";
	public static final String INV_ERR_006_DES = "Data not passed the validate";
	public static final String INV_ERR_006_DES_CN = "没有通过数据校验";

	public static final String INV_ERR_007 = "INV_ERR_007";
	public static final String INV_ERR_007_DES = "The message is empty";
	public static final String INV_ERR_007_DES_CN = "该消息是空的";

	public static final String INV_ERR_008 = "INV_ERR_008";
	public static final String INV_ERR_008_DES = "The input data have format problems";
	public static final String INV_ERR_008_DES_CN = "输入参数错误";

	public static final String SUCCESS = "true";
	public static final String FAILURE = "false";
	/**
	 * A 添加 *
	 */
	public static final String A = "A";
	/**
	 * U 修改 *
	 */
	public static final String U = "U";
	/**
	 * D 删除 *
	 */
	public static final String D = "D";

	public static final Integer PUBLIC_0 = 0;
	public static final Integer PUBLIC_1 = 1;
	public static final Integer PUBLIC_2 = 2;
	public static final Integer PUBLIC_3 = 3;
	public static final Integer PUBLIC_4 = 4;
	public static final Integer PUBLIC_5 = 5;
	public static final Integer PUBLIC_6 = 6;
	public static final Integer PUBLIC_7 = 7;
	public static final Integer PUBLIC_8 = 8;
	public static final Integer PUBLIC_9 = 9;
	public static final Integer PUBLIC_10 = 10;

	/**
	 * 批量导入标签时查询时的限制的条数
	 */
	public static final Integer SHOPPEPRODUCT_LIMIT = 100;

	/**
	 * Redi服务器挂了时，处理存在数据库里的缓存，数值限制。
	 */
	public static final Integer REDIS_CURRENTPAGE = 1;
	public static final Integer REDIS_PAGESIZE = 100;

	/**
	 * 生成缩略图设置规格常量
	 */
	public static final String PIC_STANSID = "1";
	/**
	 * 商品简称不能大于36字节
	 */
	public static final Integer PUBLIC_36 = 36;

	public static final String Y = "Y";
	public static final String N = "N";

	public static final String YES = "YES";
	public static final String NO = "NO";

	public static final String STATUS_ERROR = "STATUS_ERROR";
	public static final String EXCEPTION_ERROR = "Exception Error";
	public static final String EXCEPTION_ERROR_CN = "错误";
	public static final String EXCEPTION_ERROR_CAUSE = "Exception Error Message";
	public static final String EXCEPTION_ERROR_CAUSE_CN = "错误信息";
	public static final String EXCEPTION_ERROR_MSG = "Exception Error Cause";
	public static final String EXCEPTION_ERROR_MSG_CN = "错误原因";

	public static final String FIELD_COULD_NOT_BE_NULL = "Could not be null";

	public static final String ERROR_PARSE_001 = "ERROR_PARSE_001";
	public static final String ERROR_PARSE_001_DES = "Parse faild";
	public static final String ERROR_CONVERTER_PARSE = "Error while parsing/setting in populator/converter";
	public static final String ERROR_UPDATE_FAILED = "Update failed";

	/**
	 * 条码表
	 */
	// 条码类型
	/**
	 * 0 单条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_LESS = 0;
	/**
	 * 1 多条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_MANY = 1;
	/**
	 * 门店条码 *
	 */
	public static final String PCMBARCODE_CODE_TYPE_SE_STR = "SE";
	/**
	 * 电商国标条码 *
	 */
	public static final String PCMBARCODE_CODE_TYPE_IE_STR = "IE";
	/**
	 * 电商自编条码 *
	 */
	public static final String PCMBARCODE_CODE_TYPE_ZE_STR = "ZE";
	/**
	 * 商品企业内部条码 *
	 */
	public static final String PCMBARCODE_CODE_TYPE_YE_STR = "YE";
	/**
	 * 国标条码 *
	 */
	public static final String PCMBARCODE_CODE_TYPE_GE_STR = "GE";
	/**
	 * 2门店条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_SE = 2;
	/**
	 * 3电商国标条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_IE = 3;
	/**
	 * 4电商自编条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_ZE = 4;
	/**
	 * 5商品企业内部条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_YE = 5;
	/**
	 * 6国标条码 *
	 */
	public static final int PCMBARCODE_CODE_TYPE_GE = 6;
	/**
	 * 多包装默认 *
	 */
	public static final String PCMBARCODE_CODE_MOUNT = "1";
	/**
	 * 门店品牌表
	 */
	// 门店类型
	/**
	 * 0 北京 *
	 */
	public static final int PCMBRAND_SHOP_TYPE_BEIJING = 0;
	/**
	 * 1 外埠 *
	 */
	public static final int PCMBRAND_SHOP_TYPE_OTHER = 1;
	/**
	 * 2 电商 *
	 */
	public static final int PCMBRAND_SHOP_TYPE_EBUSINESS = 2;
	// 品牌类型
	/**
	 * 0 集团品牌 *
	 */
	public static final int PCMBRAND_TYPE_BRANDGROUP = 0;
	/**
	 * 1 门店品牌 *
	 */
	public static final int PCMBRAND_TYPE_BRAND = 1;
	/** 自定义品牌编码开头 **/
	/**
	 * 集团品牌 *
	 */
	public static final String PCMBRANDGROUP_BRANDSID_START = "1";
	/**
	 * 北京门店品牌 *
	 */
	public static final String PCMBRAND_BRANDSID_BEIJING_START = "6";
	/**
	 * 外阜门店品牌 *
	 */
	public static final String PCMBRAND_BRANDSID_OTHER_START = "7";
	/**
	 * 电商门店品牌 *
	 */
	public static final String PCMBRAND_BRANDSID_EBUSINESS_START = "5";
	/**
	 * 自定义品牌编码的位数 *
	 */
	public static final Integer PCMBRAND_BRANDSID_DIGIT = 7;
	/**
	 * 品牌图片的路径前缀 *
	 */
	public static final String PCMBRAND_BRANDPICURL = "/brand/";

	/** 品牌导入终端时门店类型 */
	/**
	 * 3 全局 *
	 */
	public static final String PCMBRANDGROUP_STORE_TYPE_ALL = "3";
	/**
	 * 2 电商 *
	 */
	public static final String PCMBRANDGROUP_STORE_TYPE_EBUSINESS = "2";
	/**
	 * 0 北京 *
	 */
	public static final String PCMBRANDGROUP_STORE_TYPE_BEIJING = "0";
	/**
	 * 1 外埠 *
	 */
	public static final String PCMBRANDGROUP_STORE_TYPE_OTHER = "1";

	/** 移动工作台调用主数据获取品牌信息时门店类型 */
	/**
	 * 0 全局 *
	 */
	public static final String PCMBRAND_PAD_STORE_TYPE_ALL = "0";
	/**
	 * 1 电商 *
	 */
	public static final String PCMBRAND_PAD_STORE_TYPE_EBUSINESS = "1";
	/**
	 * 2 北京 *
	 */
	public static final String PCMBRAND_PAD_STORE_TYPE_BEIJING = "2";
	/**
	 * 3 外埠 *
	 */
	public static final String PCMBRAND_PAD_STORE_TYPE_OTHER = "3";

	/** 门店品牌下发时门店类型 */
	/**
	 * 0 全局 *
	 */
	public static final String PCMBRAND_ERP_STORE_TYPE_ALL = "0";
	/**
	 * 1 电商 *
	 */
	public static final String PCMBRAND_ERP_STORE_TYPE_EBUSINESS = "1";
	/**
	 * 2 北京 *
	 */
	public static final String PCMBRAND_ERP_STORE_TYPE_BEIJING = "2";
	/**
	 * 3 外埠 *
	 */
	public static final String PCMBRAND_ERP_STORE_TYPE_OTHER = "3";

	/**
	 * 门店品牌下发时操作时间的格式
	 */
	public static final String PCMBRAND_ACTION_DATE = "yyyyMMdd.HHmmssZ";

	/**
	 * 集团品牌下发时操作时间的格式
	 */
	public static final String PCMBRANDGROUP_ACTION_DATE = "yyyyMMdd.HHmmssZ";

	/**
	 * 供应商 wangxuan
	 */
	/** 供应商状态 **/
	/**
	 * 供应商状态 正常 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_Y_CODE = "Y";
	public static final String PCMSUPPLYINFO_STATUS_Y_TXT = "正常";
	/**
	 * 供应商状态 未批准 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_T_CODE = "T";
	public static final String PCMSUPPLYINFO_STATUS_T_TXT = "未批准";
	/**
	 * 供应商状态 终止 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_N_CODE = "N";
	public static final String PCMSUPPLYINFO_STATUS_N_TXT = "终止";
	/**
	 * 供应商状态 待审批 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_L_CODE = "L";
	public static final String PCMSUPPLYINFO_STATUS_L_TXT = "待审批";
	/**
	 * 供应商状态 淘汰 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_3_CODE = "3";
	public static final String PCMSUPPLYINFO_STATUS_3_TXT = "淘汰";
	/**
	 * 供应商状态 停货 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_4_CODE = "4";
	public static final String PCMSUPPLYINFO_STATUS_4_TXT = "停货";
	/**
	 * 供应商状态 停款 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_5_CODE = "5";
	public static final String PCMSUPPLYINFO_STATUS_5_TXT = "停款";
	/**
	 * 供应商状态 冻结 *
	 */
	public static final String PCMSUPPLYINFO_STATUS_6_CODE = "6";
	public static final String PCMSUPPLYINFO_STATUS_6_TXT = "冻结";

	/** 供应商类型 **/
	/**
	 * 供应商类型 门店供应商 *
	 */
	public static final Integer PCMSUPPLYINFO_SUPPLYTYPE_SHOP = 0;
	/**
	 * 供应商类型 电商供应商 *
	 */
	public static final Integer PCMSUPPLYINFO_SUPPLYTYPE_EBUSINESS = 1;
	/**
	 * 供应商类型 集团供应商 *
	 */
	public static final Integer PCMSUPPLYINFO_SUPPLYTYPE_GROUP = 2;

	/**
	 * 经营方式(0 经销) *
	 */
	public static final int PCMSUPPLYINFO_BUSINESS_PATTERN_Z1 = 0;
	/**
	 * 经营方式(Z001 经销) *
	 */
	public static final String PCMSUPPLYINFO_BUSINESS_PATTERN_Z1_STR = "Z001";

	/**
	 * 经营方式(1 代销) *
	 */
	public static final int PCMSUPPLYINFO_BUSINESS_PATTERN_Z2 = 1;
	/**
	 * 经营方式(Z002 代销 ) *
	 */
	public static final String PCMSUPPLYINFO_BUSINESS_PATTERN_Z2_STR = "Z002";

	/**
	 * 经营方式(2 联营) *
	 */
	public static final int PCMSUPPLYINFO_BUSINESS_PATTERN_Z3 = 2;
	/**
	 * 经营方式(Z003 联营) *
	 */
	public static final String PCMSUPPLYINFO_BUSINESS_PATTERN_Z3_STR = "Z003";

	/**
	 * 经营方式(3 平台服务) *
	 */
	public static final int PCMSUPPLYINFO_BUSINESS_PATTERN_Z4 = 3;
	/**
	 * 经营方式(Z004 平台服务) *
	 */
	public static final String PCMSUPPLYINFO_BUSINESS_PATTERN_Z4_STR = "Z004";

	/**
	 * 经营方式(4 租赁) *
	 */
	public static final int PCMSUPPLYINFO_BUSINESS_PATTERN_Z5 = 4;
	/**
	 * 经营方式(Z005 租赁) *
	 */
	public static final String PCMSUPPLYINFO_BUSINESS_PATTERN_Z5_STR = "Z005";

	/** 供应商纳税类别 **/
	/**
	 * 供应商纳税类别 1 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_1_CODE = "1";
	/**
	 * 供应商纳税类别 1 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_1_TXT = "增值税一般纳税人";

	/**
	 * 供应商纳税类别 2 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_2_CODE = "2";
	/**
	 * 供应商纳税类别 2 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_2_TXT = "小规模纳税人";

	/**
	 * 供应商纳税类别 3 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_3_CODE = "3";
	/**
	 * 供应商纳税类别 3 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_3_TXT = "交纳营业税";

	/**
	 * 供应商纳税类别 4 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_4_CODE = "4";
	/**
	 * 供应商纳税类别 4 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_4_TXT = "零税率";

	/**
	 * 供应商纳税类别 5 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_5_CODE = "5";
	/**
	 * 供应商纳税类别 5 *
	 */
	public static final String PCMSUPPLYINFO_SUPPLYTYPE_TAXTYPE_5_TXT = "自然人";

	/** 商品导入终端--由主数据获取供应商信息(输出参数DTO) **/
	/**
	 * 供应商注册日期(准入日期 ，日期格式)
	 */
	public static final String PCMSUPPLYINFO_APPROVALDATE_FORMAT = "yyyymmdd";
	/** 供应商级别(1门店；2电商；3集团) */
	/**
	 * 供应商级别 1门店
	 */
	public static final String PCMSUPPLYINFO_SUPPLIERLEVEL_SHOP = "1";
	/**
	 * 供应商级别 2电商
	 */
	public static final String PCMSUPPLYINFO_SUPPLIERLEVEL_EBUSINESS = "2";
	/**
	 * 供应商级别 3集团
	 */
	public static final String PCMSUPPLYINFO_SUPPLIERLEVEL_GROUP = "3";

	/**
	 * 自营供应商WFJ的编码
	 */
	public static final String PCMSUPPLYINFO_WFJ_SUPPLYCODE = "1";

	/**
	 * 供应商信息下发时操作时间的格式 *
	 */
	public static final String PCMSUPPLYINFO_ACTION_DATE = "yyyyMMdd.HHmmssZ";

	/**
	 * 产品表状态
	 */
	/**
	 * 0 未启用 *
	 */
	public static final int PCMPRODUCT_PRO_ACTIVE_BIT_NOENABLE = 0;
	/**
	 * 1 启用 *
	 */
	public static final int PCMPRODUCT_PRO_ACTIVE_BIT_ENABLE = 1;
	/**
	 * 0 未上架 *
	 */
	public static final int PCMPRODUCT_PRO_SELLING_NOSHELVES = 0;
	/**
	 * 1 上架 *
	 */
	public static final int PCMPRODUCT_PRO_SELLING_SHELVES = 1;

	/**
	 * 商品明细表（SKU）/默认0
	 */
	/**
	 * 0 未计划 *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_NOPLAN = 0;
	/**
	 * 1 已计划 *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_PLANED = 1;
	/**
	 * 2 已拍照未上传店内 *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_TWO = 2;
	/**
	 * 3 已上传店内未到IDC *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_THREE = 3;
	/**
	 * 4 已上传至IDC 完成 *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_FOUR = 4;
	/**
	 * 5 表示拍照部已计划导购未确认 *
	 */
	public static final int PCMPRODETAIL_PHOTO_STATUS_FIVE = 5;
	/**
	 * 0 未启用 *
	 */
	public static final int PCMPRODETAIL_ACTIVE_BIT_NOENABLE = 0;
	/**
	 * 1 启用 *
	 */
	public static final int PCMPRODETAIL_ACTIVE_BIT_ENABLE = 1;
	/**
	 * 0 未上架 *
	 */
	public static final int PCMPRODETAIL_PRO_SELLING_NOSHELVES = 0;
	/**
	 * 1 上架 *
	 */
	public static final int PCMPRODETAIL_PRO_SELLING_SHELVES = 1;
	/**
	 * 1 普通商品（实物类）
	 */
	public static final int PCMPRODETAIL_PROTYPE_GENERALGOODS = 1;
	/**
	 * 2 赠品
	 */
	public static final int PCMPRODETAIL_PROTYPE_GIFT = 2;
	/**
	 * 3 礼品
	 */
	public static final int PCMPRODETAIL_PROTYPE_PRESENT = 3;
	/**
	 * 4 虚拟商品（充值卡，购物卡）
	 */
	public static final int PCMPRODETAIL_PROTYPE_VIRTUALGOODS = 4;
	/**
	 * 5 服务类商品（礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	 */
	public static final int PCMPRODETAIL_PROTYPE_SERVICEGOODS = 5;

	/** 组织机构表 **/
	/**
	 * 0 状态可用 *
	 */
	public static final int PCMORGANIZATION_STATUS_AVAILABLE = 0;
	/**
	 * 1 状态禁用 *
	 */
	public static final int PCMORGANIZATION_STATUS_DISABLE = 1;
	/**
	 * 0机构类别-集团 *
	 */
	public static final int PCMORGANIZATION_TYPE_GROUP_INT = 0;
	public static final String PCMORGANIZATION_TYPE_GROUP = "GROUP";
	/**
	 * 1机构类别-大区 *
	 */
	public static final int PCMORGANIZATION_TYPE_REGION_INT = 1;
	public static final String PCMORGANIZATION_TYPE_REGION = "REGION";
	/**
	 * 2机构类别-城市 *
	 */
	public static final int PCMORGANIZATION_TYPE_CITY_INT = 2;
	public static final String PCMORGANIZATION_TYPE_CITY = "CITY";
	/**
	 * 3机构类别-门店 *
	 */
	public static final int PCMORGANIZATION_TYPE_STORE_INT = 3;
	public static final String PCMORGANIZATION_TYPE_STORE = "STORE";
	/** 门店类型 **/
	/**
	 * 2电商 *
	 */
	public static final int PCMORGANIZATION_STORE_TYPE_DS = 2;
	public static final String PCMORGANIZATION_STORE_TYPE_DS_STR = "电商";
	/**
	 * 0北京 (默认) *
	 */
	public static final int PCMORGANIZATION_STORE_TYPE_BJ = 0;
	public static final String PCMORGANIZATION_STORE_TYPE_BJ_STR = "北京";
	/**
	 * 1外埠 *
	 */
	public static final int PCMORGANIZATION_STORE_TYPE_MD = 1;
	public static final String PCMORGANIZATION_STORE_TYPE_MD_STR = "外埠";
	/**
	 * 3集货仓 *
	 */
	public static final int PCMORGANIZATION_STORE_TYPE_JH = 3;
	public static final String PCMORGANIZATION_STORE_TYPE_JH_STR = "集货仓";
	/**
	 * 4门店物流室 *
	 */
	public static final int PCMORGANIZATION_STORE_TYPE_WL = 4;
	public static final String PCMORGANIZATION_STORE_TYPE_WL_STR = "门店物流室";

	/**
	 * 电商门店的编码 *
	 */
	public static final String PCMORGANIZATION_E_STORE_CODE = "D001";

	/** 专柜表 **/
	/** 专柜状态 **/
	/**
	 * 专柜类型 *
	 */
	public static final String PCM_SHOPPE_TYPE_PRO = "01";
	public static final String PCM_SHOPPE_TYPE_ERP = "02";
	/**
	 * 1 正常 *
	 */
	public static final int PCMSHOPPE_SHOPPE_STATUS_1 = 1;
	/**
	 * 2 停用 *
	 */
	public static final int PCMSHOPPE_SHOPPE_STATUS_2 = 2;
	/**
	 * 3撤柜 *
	 */
	public static final int PCMSHOPPE_SHOPPE_STATUS_3 = 3;
	/** 专柜下发时的业态 **/
	/**
	 * 0 百货 *
	 */
	public static final String PCMSHOPPE_SHOPPE_BUSINESSTYPE_0 = "01";
	/**
	 * 1 超市 *
	 */
	public static final String PCMSHOPPE_SHOPPE_BUSINESSTYPE_1 = "02";
	/**
	 * 2 电商 *
	 */
	public static final String PCMSHOPPE_SHOPPE_BUSINESSTYPE_2 = "03";

	/**
	 * 电商专柜编码流水的位数 *
	 */
	public static final Integer PCMSHOPPE_SHOPPE_SHOPPECODE_DIGIT = 5;

	/**
	 * 2 电商虚库专柜的编码开头 *
	 */
	@Deprecated
	public static final String PCMSHOPPE_SHOPPE_SHOPPECODESTART_2 = "2";
	/**
	 * 5 电商先销后采专柜的编码开头 *
	 */
	@Deprecated
	public static final String PCMSHOPPE_SHOPPE_SHOPPECODESTART_5 = "5";
	/** 分类类型 **/
	/**
	 * 工业分类 *
	 */
	public static final int PCM_CATEGORY_TYPE_GY = 0;
	/**
	 * 统计分类 *
	 */
	public static final int PCM_CATEGORY_TYPE_TJ = 1;
	/**
	 * 管理分类 *
	 */
	public static final int PCM_CATEGORY_TYPE_GL = 2;
	/**
	 * 展示分类 *
	 */
	public static final int PCM_CATEGORY_TYPE_ZS = 3;
	/** 库存表 **/
	/**
	 * 库存类型1001销售库 *
	 */
	public static final int PCMSTOCK_TYPE_SALE = 1001;
	/**
	 * 库存类型1002残次品库 *
	 */
	public static final int PCMSTOCK_TYPE_DEFECTIVE = 1002;
	/**
	 * 库存类型1003退货商品库 *
	 */
	public static final int PCMSTOCK_TYPE_RETURN = 1003;
	/**
	 * 库存类型1004锁定库 *
	 */
	public static final int PCMSTOCK_TYPE_LOCK = 1004;
	/**
	 * 库存类型1005团购库 *
	 */
	public static final int PCMSTOCK_TYPE_GROUPON = 1005;
	/**
	 * 库存类型1007测试库 *
	 */
	public static final int PCMSTOCK_TYPE_TEST = 1007;
	/** 变动类型 **/
	/**
	 * 借出 *
	 */
	public static final int PCMSTOCK_TYPE_BORROW = 1010;
	/**
	 * 归还 *
	 */
	public static final int PCMSTOCK_TYPE_REBORROW = 1011;
	/**
	 * 调出 *
	 */
	public static final int PCMSTOCK_OUT_TRANSFER = 1012;
	/**
	 * 调入 *
	 */
	public static final int PCMSTOCK_IN_TRANSFER = 1013;
	/**
	 * 返厂 *
	 */
	public static final int PCMSTOCK_IN_BACKFACTORY = 1014;
	/**
	 * 允许减可售库的操作 *
	 */
	public static final Integer[] PCMSTOCK_SALE_REDUCE = { 1002, 1005, 1010, 1012, 1014 };
	/**
	 * 允许减可售库的操作 *
	 */
	public static final Integer[] PCMSTOCK_SALE_INCREASE = { 1011, 1013 };

	/* 锁定记录表 */
	/**
	 * 已支付减库 *
	 */
	public static final int PCMSTOCK_YES_UNLOCK = 1021;
	/**
	 * 未支付解锁 *
	 */
	public static final int PCMSTOCK_NO_UNLOCK = 1022;
	/**
	 * 未支付锁定 *
	 */
	public static final int PCMSTOCK_NO_LOCK = 1023;
	/**
	 * 已减库 *
	 */
	public static final int PCMSTOCK_REDUCE_STOCK = 1024;
	/**
	 * 全量 *
	 */
	public static final String PCMSTOCK_TYPE_ALL = "ALL";
	/**
	 * 增量 *
	 */
	public static final String PCMSTOCK_TYPE_DELTA = "DELTA";
	/**
	 * 分布式锁 *
	 */
	public static final String PCM_LOCK_ZOOKEEPER = "zookeeper";

	/**
	 * 是否下发数据
	 */
	public static final String PCM_PUBLISH = "publish";

	public static final String PCM_LOCK_REDIS = "redis";

	/** 库存类型 **/
	/**
	 * 锁库 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE0 = 0;
	/**
	 * 减库 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE1 = 1;
	/**
	 * 解锁 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE2 = 2;
	/**
	 * 还库 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE3 = 3;
	/**
	 * 调入、调出、借出、归还 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE4 = 4;
	/**
	 * 库位修改 *
	 */
	public static final int PCMSTOCK_OPERATION_TYPE5 = 5;
	public static final int PCMSTOCK_OPERATION_TYPE6 = 6;
	/**
	 * 操作成功状态 *
	 */
	public static final String PCM_OPERATION_SUCCEED = "1";
	/**
	 * 操作失败状态 *
	 */
	public static final String PCM_OPERATION_FAILED = "0";

	/**
	 * 是否支付减库存(0:否;1:是) *
	 */
	public static final String PCMSTOCK_ISPAY_REDUCESTOCK0 = "0";
	public static final String PCMSTOCK_ISPAY_REDUCESTOCK1 = "1";

	/**
	 * 全渠道库存是否推送给EDI 0:否;1:是 *
	 */
	public static final String PCMSTOCK_ISPUSH_EDI = "1";

	/** 专柜商品表 **/
	/**
	 * 可售状态 0 可售 *
	 */
	public static final int PCMSHOPPEPRODECT_SALE_STATUS_YES = 0;
	/**
	 * 可售状态 1 不可售 *
	 */
	public static final int PCMSHOPPEPRODECT_SALE_STATUS_NO = 1;
	/**
	 * 是否负库存销售0允许 *
	 */
	public static final int PCMSHOPPEPRODECT_NEGATIVE_STOCK_YES = 0;
	/**
	 * 是否负库存销售1不允许 *
	 */
	public static final int PCMSHOPPEPRODECT_NEGATIVE_STOCK_NO = 1;
	/**
	 * 专柜商品类型1普通商品 *
	 */
	public static final int PCMSHOPPEPRODECT_PRO_TYPE_ORDINARY = 1;
	/**
	 * 专柜商品类型2大码商品 *
	 */
	public static final int PCMSHOPPEPRODECT_PRO_TYPE_ERP = 2;
	/**
	 * 是否管库存0管 *
	 */
	public static final int PCMSHOPPEPRODECT_IS_STOCK_YE = 0;
	/**
	 * 是否管库存1否 *
	 */
	public static final int PCMSHOPPEPRODECT_IS_STOCK_NE = 1;
	/**
	 * 库存方式1不管 *
	 */
	public static final int PCMSHOPPEPRODECT_IS_STOCK_BG = 1;
	/**
	 * 库存方式2自库 *
	 */
	public static final int PCMSHOPPEPRODECT_IS_STOCK_ZK = 2;
	/**
	 * 库存方式3虚库 *
	 */
	public static final int PCMSHOPPEPRODECT_IS_STOCK_XK = 3;
	/**
	 * 是否管库存Y管 *
	 */
	public static final String PCMSHOPPEPRODECT_IS_STOCK_Y_STR = "Y";
	/**
	 * 是否管库存N否 *
	 */
	public static final String PCMSHOPPEPRODECT_IS_STOCK_N_STR = "N";
	/**
	 * 库存方式BG不管 *
	 */
	public static final String PCMSHOPPEPRODECT_IS_STOCK_BG_STR = "BG";
	/**
	 * 库存方式ZK自库 *
	 */
	public static final String PCMSHOPPEPRODECT_IS_STOCK_ZK_STR = "ZK";
	/**
	 * 库存方式XK虚库 *
	 */
	public static final String PCMSHOPPEPRODECT_IS_STOCK_XK_STR = "XK";
	/**
	 * 是否管库存0管 *
	 */
	public static final char PCMSHOPPEPRODECT_IS_STOCK_YES = '0';
	/**
	 * 是否管库存1不管 *
	 */
	public static final char PCMSHOPPEPRODECT_IS_STOCK_NO = '1';
	/**
	 * 是否管库存Y管 *
	 */
	public static final char PCMSHOPPEPRODECT_IS_STOCK_Y = 'Y';
	/**
	 * 是否管库存N不管 *
	 */
	public static final char PCMSHOPPEPRODECT_IS_STOCK_N = 'N';
	/**
	 * 是否支持货到付款(0支持) *
	 */
	public static final int PCMSHOPPEPRODECT_IS_COD_YES = 0;
	/**
	 * 是否支持货到付款(1不支持) *
	 */
	public static final int PCMSHOPPEPRODECT_IS_COD_NO = 1;
	/**
	 * 物流属性(0 液体) *
	 */
	public static final int PCMSHOPPEPRODECT_TMS_TYPE_Z1 = 0;
	/**
	 * 物流属性(Z001液体) *
	 */
	public static final String PCMSHOPPEPRODECT_TMS_TYPE_Z1_STR = "Z001";
	/**
	 * 物流属性(1 易碎) *
	 */
	public static final int PCMSHOPPEPRODECT_TMS_TYPE_Z2 = 1;
	/**
	 * 物流属性(Z002 易碎 ) *
	 */
	public static final String PCMSHOPPEPRODECT_TMS_TYPE_Z2_STR = "Z002";
	/**
	 * 物流属性(2 液体和易碎) *
	 */
	public static final int PCMSHOPPEPRODECT_TMS_TYPE_Z3 = 2;
	/**
	 * 物流属性(Z003 液体和易碎) *
	 */
	public static final String PCMSHOPPEPRODECT_TMS_TYPE_Z3_STR = "Z003";
	/**
	 * 物流属性(3 粉末) *
	 */
	public static final int PCMSHOPPEPRODECT_TMS_TYPE_Z4 = 3;
	/**
	 * 物流属性(Z004 粉末) *
	 */
	public static final String PCMSHOPPEPRODECT_TMS_TYPE_Z4_STR = "Z004";
	/** ERP商品表 **/
	/**
	 * 大码类型:0 价格码 *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_PRICE = 0;
	/**
	 * 大码类型(1 长期统码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_LONG = 1;
	/**
	 * 大码类型(2 促销统码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_PROMOTION = 2;
	/**
	 * 大码类型(3 特卖统码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_SPECOFFER = 3;
	/**
	 * 大码类型(4 扣率码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_DISCOUNT = 4;
	/**
	 * 大码类型(5 促销扣率码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_PROMOTIONDISCOUNT = 5;
	/**
	 * 大码类型(6 单品码) *
	 */
	public static final int PCMERPPRODUCT_CODE_TYPE_SKU = 6;
	/**
	 * 经营方式(0 经销) *
	 */
	public static final int PCMERPPRODUCT_PRODUCT_TYPE_Z1 = 0;
	/**
	 * 经营方式(Z001 经销) *
	 */
	public static final String PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR = "Z001";
	/**
	 * 经营方式(1 代销) *
	 */
	public static final int PCMERPPRODUCT_PRODUCT_TYPE_Z2 = 1;
	/**
	 * 经营方式(Z002 代销 ) *
	 */
	public static final String PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR = "Z002";
	/**
	 * 经营方式(2 联营) *
	 */
	public static final int PCMERPPRODUCT_PRODUCT_TYPE_Z3 = 2;
	/**
	 * 经营方式(Z003 联营) *
	 */
	public static final String PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR = "Z003";
	/**
	 * 经营方式(3 平台服务) *
	 */
	public static final int PCMERPPRODUCT_PRODUCT_TYPE_Z4 = 3;
	/**
	 * 经营方式(Z004 平台服务) *
	 */
	public static final String PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR = "Z004";
	/**
	 * 经营方式(4 租赁) *
	 */
	public static final int PCMERPPRODUCT_PRODUCT_TYPE_Z5 = 4;
	/**
	 * 经营方式(Z005 租赁) *
	 */
	public static final String PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR = "Z005";
	/**
	 * 商品状态(Y正常) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_NORMAL_STR = "Y";
	/**
	 * 商品状态(X停售) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR = "X";
	/**
	 * 商品状态(T停货) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_STOP_STR = "T";
	/**
	 * 商品状态(P暂停使用) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_PAUSE_STR = "P";
	/**
	 * 商品状态(N已删除) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_DELETE_STR = "N";
	/**
	 * 商品状态(M淘汰) *
	 */
	public static final String PCMERPPRODUCT_PRO_STATUS_PASS_STR = "M";
	/**
	 * 商品状态(0正常) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_NORMAL = 0;
	/**
	 * 商品状态(1停售) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_STOPSALE = 1;
	/**
	 * 商品状态(2停货) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_STOP = 2;
	/**
	 * 商品状态(3暂停使用) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_PAUSE = 3;
	/**
	 * 商品状态(4已删除) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_DELETE = 4;
	/**
	 * 商品状态(5淘汰) *
	 */
	public static final int PCMERPPRODUCT_PRO_STATUS_PASS = 5;
	/**
	 * 是否允许促销(0 允许) *
	 */
	public static final int PCMERPPRODUCT_IS_PROMOTION_YES = 0;
	/**
	 * 是否允许促销(1 不允许) *
	 */
	public static final int PCMERPPRODUCT_IS_PROMOTION_NO = 1;
	/**
	 * 是否允许调价(0 允许) *
	 */
	public static final int PCMERPPRODUCT_IS_ADJUST_PRICE_YES = 0;
	/**
	 * 是否允许调价(1 不允许) *
	 */
	public static final int PCMERPPRODUCT_IS_ADJUST_PRICE_NO = 1;
	/**
	 * 业态类型（1百货） *
	 */
	public static final int PCMERPPRODUCT_FORMAT_TYPE_DEPARTMENT = 1;
	/**
	 * 业态类型（2超市） *
	 */
	public static final int PCMERPPRODUCT_FORMAT_TYPE_SUPERMARKET = 2;
	/**
	 * 业态类型（3电商） *
	 */
	public static final int PCMERPPRODUCT_FORMAT_TYPE_ONLINE = 3;
	/** 行政机构 **/
	/**
	 * 国家 *
	 */
	public static final String PCMREGION_TYPE_COUNTRY = "COUNTRY";
	/**
	 * 省 *
	 */
	public static final String PCMREGION_TYPE_PROVINCE = "PROVINCE";
	/**
	 * 市 *
	 */
	public static final String PCMREGION_TYPE_CITY = "CITY";
	/**
	 * 区/县 *
	 */
	public static final String PCMREGION_TYPE_DISTRICT = "DISTRICT";

	/**
	 * 省 *
	 */
	public static final Integer PCMREGION_REGIONLEVEL_PROVINCE = 0;
	/**
	 * 市 *
	 */
	public static final Integer PCMREGION_REGIONLEVEL_CITY = 1;
	/**
	 * 区/县 *
	 */
	public static final Integer PCMREGION_REGIONLEVEL_DISTRICT = 3;

	/** 电商下发截取位置 **/
	/**
	 * 电商下发市截取开始位置 *
	 */
	public static final Integer PCMREGION_E_CITY_START = 2;
	/**
	 * 电商下发区截取开始位置 *
	 */
	public static final Integer PCMREGION_E_DISTRICT_START = 7;

	/**
	 * 工业分类 *
	 */
	public static final int INDUSTRYTCATEGORY = 0;
	/**
	 * 管理分类 *
	 */
	public static final int MANAGECATEGORY = 1;
	/**
	 * 统计分类 *
	 */
	public static final int STATISTICSCATEGORY = 2;
	/**
	 * 展示分类 *
	 */
	public static final int SHOWCATEGORY = 3;

	/**
	 * 电商门店编码 D001
	 */
	public static final String STORE_DS = "D001";
	/**
	 * 商品和库存准入导入前的校验数量限制
	 */
	public static final int VALIDPRODUCTDTO_PRODUCTCOUNT = 200;
	/**
	 * 商品下发数量限制
	 */
	public static final int PULISHPROINFO = 100;
	/**
	 * 批量库存 数量限制
	 */
	public static final int STOCK_LINE_COUNT = 200;
	/**
	 * 批量导入库存数量限制
	 */
	public static final int STOCK_IN_COUNT = 100;
	/**
	 * 用于生成品类编码
	 */
	public static final String GY = "GY";

	public static final String TJ = "TJ";

	public static final String GL = "GL";

	public static final String ZS = "ZS";

	/**
	 * 价格类型 1:全渠道;2:电商;3:微信;4:APP;5:线下 *
	 */
	public static final String PRICE_TYPE = "1;2;3;4;5";
	/**
	 * 价格渠道 *
	 */
	public static final String DEFAULT_CHANNEL_SID = "0";
	/**
	 * 价格渠道 *
	 */
	public static final String DEFAULT_PRICE_TYPE = "1";
	/**
	 * 变价操作 *
	 */
	public static final String ACTIONCODEA = "A";
	public static final String ACTIONCODEU = "U";
	public static final String ACTIONCODED = "D";
	/**
	 * 价格删除标识 *
	 */
	public static final Integer PRICE_DEL_FLAG = 1;
	/**
	 * 价格类型 1标识零售价 2 短期价 *
	 */
	public static final Integer PRICE_TYPE_1 = 1;
	public static final Integer PRICE_TYPE_2 = 2;
	/**
	 * 价格零售时间 *
	 */
	public static final String PRICE_RETAIL_DATE = "99991231";
	public static final String PRICE_RETAIL_DATETIME = "99991231.235959";
	public static final int PRICE_LINE_COUNT = 200;
	public static final int PRICE_LINE_COUNT100 = 100;
	/**
	 * 价格阀值 *
	 */
	public static final BigDecimal PRICE_THRESHOLD = new BigDecimal(0.3);

	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHHmmss = "yyyyMMdd.HHmmss";

	/**
	 * 默认变价类型，专柜商品编码变价 *
	 */
	public static final String PRICE_CHANGE_TYPE0 = "0";
	/**
	 * 金额减 *
	 */
	public static final String PRICE_CHANGE_TYPE1 = "1";
	/**
	 * 百分百减 *
	 */
	public static final String PRICE_CHANGE_TYPE2 = "2";

	/** 支付方式 **/
	/**
	 * 一级支付方式上级CODE *
	 */
	public static final String DEFAULT_PARENT_CODE = "0";
	/**
	 * 支付方式删除标识 *
	 */
	public static final Integer PAYMENT_DEL_FLAG = 1;
	public static final String DEFAULT_CHANGE_CODE = "0000001";

	public static final String EFUTUREERP = "EFUTUREERP";// 门店ERP
	public static final String PIS = "PIS";// 导入中端

	/**
	 * 脱机标示（1：脱机 0：联网） *
	 */
	public static final String PCM_ISOFFERLINE0 = "0";
	public static final String PCM_ISOFFERLINE1 = "1";

	public static final String ADDSUCCESS = "添加成功";
	public static final String UPDATESUCCESS = "修改成功";

	/**
	 * SKU库存标识：0有货，1无货 *
	 */
	public static final String SKU_STOCK0 = "0";
	public static final String SKU_STOCK1 = "1";

	/**
	 * 商品编码 *
	 */
	public static final Long PRO_CODE = 20000000l;
	public static final Long SKU_CODE = 1000000000000l;
	public static final Long SPU_CODE = 100000000l;
	public static final String SAPPRO_CODE = "0000001";

	/**
	 * 下发类型 *
	 */
	public static final Integer PUSH_TYPE_A = 0;// 添加
	public static final Integer PUSH_TYPE_U = 1;// 修改
	public static final Integer PUSH_TYPE_D = 2;// 删除

	/**
	 * 默认截至时间 *
	 */
	public static final int expireDate = 2678399;

	/**
	 * 标准品名分隔符 *
	 */

	public static final String SEPARATOR = " ";

	public static final String PROYEINfO = "http:10.6.2.48:8046";
	/**
	 * 供应商条码类型
	 */
	public static final String SUPPLIER_BARCODE_TYPE = "1";
	/**
	 * 经营方式规则
	 */
	public static final String SUPPLIER_OPERATEMODE_TYPE = "[01234]";
	/** 门店ERP **/
	public static final String FJERP = "FJERP";
	public static final String SAPERP = "SAPERP";
}
