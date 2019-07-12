package com.java4s.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CustomException extends RuntimeException{

	
	private String message = "";
	private String errorCode = "";
	public CustomException(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
		System.out.println();
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	

		
		
	
	
	public static void main(String[] args) throws InterruptedException  {
		
		
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = "d";
		String encodedString = encoder.encodeToString( 
		        normalString.getBytes(StandardCharsets.UTF_8) );
		System.out.println(encodedString);
		
		
		// encodedString = "dXNlcm5hbWU6cGFzc3dvcmQ=";
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedByteArray = decoder.decode(encodedString);
		//Verify the decoded string
		System.out.println(new String(decodedByteArray));
		
		
		 
	}
	
	}

class Run implements Runnable{

	CountDownLatch latch ;
	Random r = new Random();

	int low = 4000;
	int high = 5000;
	int result = r.nextInt(high-low) + low;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"is waiting");
		
		
		latch.countDown();
//		 try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		 System.out.println(Thread.currentThread().getName() +"Started.");
		 int j = 0;
		 for(int i =0;i<10000000;i++) {}
		
		j++;
		System.out.println(Thread.currentThread().getName() + " has finished");	
		
	}
	Run(CountDownLatch latch) {
		 this.latch = latch;
		 System.out.println("constrocot");
		 
	 }		

}
	

