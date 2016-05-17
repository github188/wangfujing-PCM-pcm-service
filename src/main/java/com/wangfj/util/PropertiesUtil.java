/**
 * @Probject Name: oms-service
 * @Path: com.wangfj.utilPropertiesUtil.java
 * @Create By kong
 * @Create In 2015年7月13日 下午4:23:57
 * TODO
 */
package com.wangfj.util;

import com.wangfj.core.utils.PropertyConfigurer;

/**
 * @Comment
 * @Class Name PropertiesUtil
 * @Author kong
 * @Create In 2015年7月13日
 */
public class PropertiesUtil {
	public static String getProperties(String key){
		return PropertyConfigurer.getContextProperty(key);
	}
}
