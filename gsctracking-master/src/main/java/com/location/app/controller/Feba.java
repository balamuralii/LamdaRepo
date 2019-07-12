package com.location.app.controller;

import java.io.UnsupportedEncodingException;
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

import org.json.simple.JSONObject;

import com.infosys.feba.tools.shoppingmallencryption.ShoppingMallBase64EncoderDecoder;
import com.infosys.feba.tools.shoppingmallencryption.ShoppingMallStringByteConverter;
import com.infosys.feba.tools.shoppingmallencryption.ShoppingMallSymmetricCipherHelper;

public class Feba {
	  private static final ConcurrentMap<String, Charset> charsetsByAlias = new ConcurrentHashMap();
	  private static final String DEFAULT_CHARSET = Charset.defaultCharset().displayName();
	 private static final String mHexKey = "29304E8758327892";
	  private static final String Algo_DES = "DES";
	  private static final String Algo_AES = "AES";
	  private static final String Algo_DESede = "DESede";
	 // private static CipherInstanceThreadLocal cipherThreadLocal = new CipherInstanceThreadLocal(null);



		public static void main(String[] args) throws Exception {
			//To encrypt the data
			
			
	
						
			String Key="POC1234";
			String encryptedVal=null;
			
			JSONObject obj = new JSONObject();
			obj.put("userid", "testuser");
			obj.put("password", "password");
			String js = obj.toString();
			encryptedVal=encrypt(js, Key, "AES");
			//encryptedVal= URLEncoder.encode(encryptedVal,"UTF-8");

			System.out.println("encryptedVal--->"+ encryptedVal);
			
			
			
		}
		

		  public static String encrypt(String paramString1, String paramString2, String paramString3) throws Exception {
		    byte[] arrayOfByte1 = ShoppingMallStringByteConverter.convertStringtoByteArray(paramString1);
		    
		    byte[] arrayOfByte2 = null;
		    
		    
		    byte[] arrayOfByte3 = getKey(paramString2, paramString3);
		    SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, paramString3);
		    HashMap hashMap = getCipherMap();
		    Cipher cipher = (Cipher)hashMap.get(paramString3);
		    cipher.init(1, secretKeySpec);
		    arrayOfByte2 = cipher.doFinal(arrayOfByte1);
		    
		    return ShoppingMallBase64EncoderDecoder.encode(arrayOfByte2);
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
		  public static byte[] convertStringtoByteArray(String paramString) throws UnsupportedEncodingException { return convertStringtoByteArray(paramString, DEFAULT_CHARSET); }

		  public static byte[] convertStringtoByteArray(String paramString1, String paramString2) throws UnsupportedEncodingException {
		    Charset charset = lookup(paramString2);

		    
		    ByteBuffer byteBuffer = charset.encode(paramString1);

		    
		    byte[] arrayOfByte1 = byteBuffer.array();

		    
		    int i = byteBuffer.limit();
		    
		    byte[] arrayOfByte2 = new byte[i];


		    
		    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
		    
		    return arrayOfByte2;
		  }
		  private static Charset lookup(String paramString) throws UnsupportedCharsetException {
			    Charset charset = (Charset)charsetsByAlias.get(paramString);
			    if (charset == null) {
			      charset = Charset.forName(paramString);
			      charsetsByAlias.putIfAbsent(paramString, charset);
			    } 
			    return charset;
			  }

		  private static int getKeyLength(String paramString) throws Exception {
		    if (paramString.equalsIgnoreCase("DES"))
		      return 64; 
		    if (paramString.equalsIgnoreCase("AES"))
		      return 128; 
		    if (paramString.equalsIgnoreCase("DESede")) {
		      return 192;
		    }
		    
		    throw new Exception("SymmetricCipher.getKeyLength() error - Symmetric cipher algorithm name " + paramString + " is not recognized. Thus can not determine key length.");
		  }


		  private static HashMap<String, Cipher> getCipherMap() { HashMap hashMap = new HashMap();
	      
	      try { hashMap.put("DES", Cipher.getInstance("DES"));
	        hashMap.put("AES", Cipher.getInstance("AES"));
	        hashMap.put("DESede", Cipher.getInstance("DES")); }
	      catch (NoSuchAlgorithmException noSuchAlgorithmException) {  }
	      catch (NoSuchPaddingException noSuchPaddingException) {}

	      
	      return hashMap;
	    } 

	
		
			
			  
}
			


		


