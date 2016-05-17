package com.wangfj.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {

	private static String ivValue = "";
	public static String encrypt(String seed, String cleartext) {
		String iv="1234567890123456";
		try {
			ivValue = iv;
			byte[] rawKey = null;

			if (seed.length() != 16) {
				rawKey = getRawKey(seed.getBytes());
			} else {
				rawKey = seed.getBytes();
			}

			byte[] result = encrypt(rawKey, cleartext.getBytes());
			String ivAToHex = toHex(iv.getBytes());
			String resultAToHex = toHex(result);
			return ivAToHex + resultAToHex;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		byte[] afterSeed = new byte[2 * seed.length];
		for (int i = 0; i < seed.length; i++) {
			afterSeed[i] = seed[i];
		}
		for (int i = seed.length; i < afterSeed.length; i++) {
			afterSeed[i] = 0;
		}
		return afterSeed;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		int blockSize = cipher.getBlockSize();

		byte[] dataBytes = clear;
		int plaintextLength = dataBytes.length;
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength
					+ (blockSize - (plaintextLength % blockSize));
		}
		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

		IvParameterSpec iv = new IvParameterSpec(ivValue.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(plaintext);
		return encrypted;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private final static String HEX = "0123456789ABCDEF";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	/**
	 * 
	 * @param sSrc
	 * @param seed
	 * @return
	 * @throws Exception
	 */

	public static String encrypt_new(String seed, String sSrc) throws Exception {

		String ivKey = "1234567890123456";
		if (seed == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (seed.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = seed.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");// "算法/模式/补码方式"
		int blockSize = cipher.getBlockSize();
		byte[] dataBytes = sSrc.getBytes();
		int plaintextLength = dataBytes.length;
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength
					+ (blockSize - (plaintextLength % blockSize));
		}
		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

		IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(plaintext);
		String ivHex = toHex(ivKey.getBytes());
		String encryptedHex = toHex(encrypted);

		return ivHex + encryptedHex;
	}


}