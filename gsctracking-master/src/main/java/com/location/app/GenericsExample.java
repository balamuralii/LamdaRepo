package com.location.app;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample {
	
	public static void main(String[] args) {
		 
		
		List list1 = new ArrayList<String>();    
		list1.add("hello"); 
		
		String s = (String) list1.get(0); 
	
	}
}

