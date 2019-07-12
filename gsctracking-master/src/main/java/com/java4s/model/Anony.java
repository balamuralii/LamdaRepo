package com.java4s.model;

public class Anony implements Runnable{
	int j = 0;
	@Override
	public void run() {

			//synchronized (this){} 
			for(int i =0;i<1000000;i++) {
				
				j++;
			}

			
			
			
		}
	
	public void pri() {
		System.out.println(j);
	}
		// TODO Auto-generated method stub
		
	}