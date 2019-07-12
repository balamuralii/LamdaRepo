package com.location.app;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class OBCEncDec {

	private static final ConcurrentMap<String, Charset> charsetsByAlias = new ConcurrentHashMap();
	private static final String CHARACTER_SET = System.getProperty("CHARACTER_SET", "UTF-8");
	private static final String DEFAULT_CHARSET = Charset.defaultCharset().displayName();
	private static final String mHexKey = "29304E8758327892";
	private static final String Algo_DES = "DES";
	private static final String Algo_AES = "AES";
	private static final String Algo_DESede = "DESede";
	private static CipherInstanceThreadLocal cipherThreadLocal = new CipherInstanceThreadLocal();

	static {
		charsetsByAlias.put(CHARACTER_SET, Charset.forName(CHARACTER_SET));
		charsetsByAlias.put(DEFAULT_CHARSET, Charset.defaultCharset());
	}

	public static void main(String[] args) {

		OBCEncDec enc = new OBCEncDec();
		String object = "Test Argument java";
		String Key = "POC1234";
		String encryptedVal = null;
		try {

			encryptedVal = enc.encrypt(object, Key, "AES");

			System.out.println("Before encryptedVal--->" + encryptedVal);
			encryptedVal = URLEncoder.encode(encryptedVal, "UTF-8");

			System.out.println("After encryptedVal--->" + encryptedVal);
			// encryptedVal = "%2BesJ1kGY4hhfLvL0qhE1KOCG%2BEXXWSigNskdE5I2Y4w%3D";
			encryptedVal = URLDecoder.decode(encryptedVal, "UTF-8");

			encryptedVal = enc.decrypt(encryptedVal, Key, "AES");
			System.out.println("decryptedVal--->" + encryptedVal);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String paramString1, String paramString2, String paramString3) throws Exception {
		byte[] arrayOfByte1 = convertStringtoByteArray(paramString1);

		byte[] arrayOfByte2 = null;

		byte[] arrayOfByte3 = getKey(paramString2, paramString3);
		SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, paramString3);
		HashMap hashMap = getCipherMap();
		Cipher cipher = (Cipher) hashMap.get(paramString3);
		cipher.init(1, secretKeySpec);
		arrayOfByte2 = cipher.doFinal(arrayOfByte1);
		return new BASE64Encoder().encode(arrayOfByte2);
	}

	private static byte[] getKey(String paramString1, String paramString2) throws Exception {
		int i = getKeyLength(paramString2) / 8;
		int j = paramString1.length();

		String str = paramString1;
		if (j < i) {
			int k = i / j;
			for (byte b = 0; b < k; b++) {
				str = str + paramString1;
			}
		}

		return convertStringtoByteArray(str.substring(0, i));
	}

	private static int getKeyLength(String paramString) throws Exception {
		if (paramString.equalsIgnoreCase("DES"))
			return 64;
		if (paramString.equalsIgnoreCase("AES"))
			return 128;
		if (paramString.equalsIgnoreCase("DESede")) {
			return 192;
		}

		throw new Exception("SymmetricCipher.getKeyLength() error - Symmetric cipher algorithm name " + paramString
				+ " is not recognized. Thus can not determine key length.");
	}

	public static byte[] convertStringtoByteArray(String paramString1) throws UnsupportedEncodingException {

		Charset charset = lookup(DEFAULT_CHARSET);
		ByteBuffer byteBuffer = charset.encode(paramString1);
		byte[] arrayOfByte1 = byteBuffer.array();
		int i = byteBuffer.limit();
		byte[] arrayOfByte2 = new byte[i];
		System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
		return arrayOfByte2;
	}

	public static String convertByteArrayToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2,
			String paramString) throws UnsupportedEncodingException {
		Charset charset = lookup(paramString);

		ByteBuffer byteBuffer = ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2);

		return charset.decode(byteBuffer).toString();
	}

	private static Charset lookup(String paramString) throws UnsupportedCharsetException {
		Charset charset = (Charset) charsetsByAlias.get(paramString);
		if (charset == null) {
			charset = Charset.forName(paramString);
			charsetsByAlias.putIfAbsent(paramString, charset);
		}
		return charset;
	}

	private static class CipherInstanceThreadLocal extends ThreadLocal<HashMap<String, Cipher>> {
		private CipherInstanceThreadLocal() {
		}

		protected HashMap<String, Cipher> initialValue() {
			HashMap hashMap = new HashMap();

			try {
				hashMap.put("DES", Cipher.getInstance("DES"));
				hashMap.put("AES", Cipher.getInstance("AES"));
				hashMap.put("DESede", Cipher.getInstance("DES"));
			} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			} catch (NoSuchPaddingException noSuchPaddingException) {
			}
			return hashMap;
		}
	}

	public static String decrypt(String paramString1, String paramString2, String paramString3) throws Exception {
		byte[] arrayOfByte1 = new BASE64Decoder().decodeBuffer(paramString1);
		byte[] arrayOfByte2 = null;

		byte[] arrayOfByte3 = getKey(paramString2, paramString3);
		SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, paramString3);

		HashMap hashMap = getCipherMap();
		Cipher cipher = (Cipher) hashMap.get(paramString3);
		cipher.init(2, secretKeySpec);
		arrayOfByte2 = cipher.doFinal(arrayOfByte1);

		return convertByteArrayToString(arrayOfByte2);
	}

	public static String convertByteArrayToString(byte[] paramArrayOfByte) throws UnsupportedEncodingException {
		return convertByteArrayToString(paramArrayOfByte, DEFAULT_CHARSET);
	}

	public static String convertByteArrayToString(byte[] paramArrayOfByte, String paramString)
			throws UnsupportedEncodingException {
		Charset charset = lookup(paramString);

		return charset.decode(ByteBuffer.wrap(paramArrayOfByte)).toString();
	}

	private static HashMap<String, Cipher> getCipherMap() {
		return (HashMap) cipherThreadLocal.get();
	}

}
