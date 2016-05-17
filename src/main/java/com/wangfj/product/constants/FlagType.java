package com.wangfj.product.constants;

public class FlagType {
	public static Integer zookeeper_lock = 0;
	public static Integer redis_falg = 0;
	public static Integer publish_info = 0;

	public static Integer getPublish_info() {
		return publish_info;
	}

	public static void setPublish_info(Integer publish_info) {
		FlagType.publish_info = publish_info;
	}

	public static Integer getZookeeper_lock() {
		return zookeeper_lock;
	}

	public static void setZookeeper_lock(Integer zookeeper_lock) {
		FlagType.zookeeper_lock = zookeeper_lock;
	}

	public static Integer getRedis_falg() {
		return redis_falg;
	}

	public static void setRedis_falg(Integer redis_falg) {
		FlagType.redis_falg = redis_falg;
	}

}
