package com.wangfj.util;

import java.util.Random;

import com.wangfj.core.utils.PropertyUtil;
import com.wfj.platform.util.signature.handler.PrivateSignatureHandler;

import net.sf.json.JSON;

public class CommonUtil {
	/**
	 
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r',
				't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x',
				'c', 'v', 'b', 'n', 'm' };
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
		}
		return stringBuffer.toString();
	}

	/**
	 * 搜索下发签名
	 * 
	 * @Methods Name getSearchSignJson
	 * @Create In 2015年10月9日 By kongqf
	 * @param json
	 * @return
	 * @throws Exception
	 *             String
	 */
	public static String getSearchSignJson(JSON json) throws Exception {
		String privateKeyString = PropertyUtil.getSystemUrl("search.privateKey");

		PrivateSignatureHandler handler = new PrivateSignatureHandler();
		handler.setCaller(PropertyUtil.getSystemUrl("search.Caller"));
		// handler.setUsername(PropertyUtil.getSystemUrl("search.Username"));
		handler.setPrivateKeyString(privateKeyString);

		String signatureResult = handler.sign(json);

		return signatureResult;
	}

}
