package com.location.app;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class OBCEncryptionDec {


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
		byte[] arrayOfByte1 = paramString1.getBytes();

		byte[] arrayOfByte2 = null;

		byte[] arrayOfByte3 = paramString2.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, paramString3);
	
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(cipher.ENCRYPT_MODE, secretKeySpec);
		arrayOfByte2 = cipher.doFinal(arrayOfByte1);
		return new BASE64Encoder().encode(arrayOfByte2);
	}


	public static String decrypt(String paramString1, String paramString2, String paramString3) throws Exception {
		byte[] arrayOfByte1 = new BASE64Decoder().decodeBuffer(paramString1);
		byte[] arrayOfByte2 = null;

		byte[] arrayOfByte3 =paramString2.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, paramString3);

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(cipher.DECRYPT_MODE, secretKeySpec);
		arrayOfByte2 = cipher.doFinal(arrayOfByte1);

		return new String(arrayOfByte2);
	}



	


}
