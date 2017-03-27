package com.test.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
	private static final String token="test";
	private static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b0 = md[i];
				buf[k++] = hexDigits[b0 >>> 4 & 0xf];
				buf[k++] = hexDigits[b0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{token,timestamp,nonce};
		//ÅÅÐò
		Arrays.sort(arr);
		//Éú³É×Ö·û´®
		StringBuffer content =new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		//SHA1¼ÓÃÜ
		String sha1=getSha1(content.toString());
		return sha1.equals(signature);
	}
}
