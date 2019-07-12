package com.location.app;

import java.net.URLEncoder;

import com.infosys.feba.tools.shoppingmallencryption.ShoppingMallSymmetricCipherHelper;

public class ExternalPaymentURLEncryptionUtility {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//To encrypt the data
		
		
		try{
					
		String Key="POC1234";
		String encryptedVal=null;
		
		encryptedVal=ShoppingMallSymmetricCipherHelper.encrypt("Test Argument java", Key, "AES");
		System.out.println(encryptedVal);
		encryptedVal= URLEncoder.encode(encryptedVal,"UTF-8");

		System.out.println("encryptedVal--->"+ encryptedVal);
		
		}
		catch (Exception e )
		{
			e.printStackTrace();	
		}


	}

}

