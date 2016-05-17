package com.wangfj.product.common.service.intf;

import java.util.List;
import java.util.Map;

public interface IJcoSAPUtil {
	public List<Map<String, String>> functionExecute(String functionName, String requestTableName,
			String resultTableName, List<Map<String, Object>> listMap);

	public void functionExecute(String functionName, String requestTableName,
			List<Map<String, Object>> listMap);
}
