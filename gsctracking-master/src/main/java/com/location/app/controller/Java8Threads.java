package com.location.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Java8Threads {
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(5000);
		
		Products p1	=new Products(1,"HP ",25000f,latch);  
		Products p2=new Products(2,"Dell Laptop",30000f,latch);  
		Products p3=new Products(3,"Lenevo Laptop",28000f,latch);  
		Products p4 =new Products(4,"Sony Laptop",28000f,latch);  
		Products p5 =new Products(5,"Apple Laptop",90000f,latch);
        		
        ArrayList<Products> arr = new ArrayList<>();
        arr.add(p5);arr.add(p4);arr.add(p3);arr.add(p2);arr.add(p1);
       
        
        Runnable runn = ()-> {
	for(int i = 0;i<1000;i++) {System.out.println(i);}		

		};
		
		Thread t1 = new Thread(runn);
		t1.start();
		
	//	String s = "hello";
	
		
		new Thread(()->{
			for(int i =1;i<100;i++) {
				System.out.println("hi");
				}
			}).start(); 
		
		//new Thread(() -> arr.forEach(e->System.out.println(e.id+" "+e.name+" "+e.price))).start();
		
		
		
		//new Thread(p1).start();
		
		//ExecutorService exc = Executors.newFixedThreadPool(100000);		
		for(int i =0;i<=10000000;i++) {
			
			//exc.submit(p1);
			latch.countDown();
//			exc.submit(p2);
//			latch.countDown();
//			exc.submit(p3);
//			latch.countDown();
//			exc.submit(p4);	
//			latch.countDown();
//			exc.submit(p5);	 
//			latch.countDown();
		}
		
		
		
	}
}

class Products implements Runnable{
	
	int id;  
    String name;  
    float price;  
    private CountDownLatch latch;
    public Products(int id, String name, float price, CountDownLatch latch) {  
        this.id = id;  
        this.name = name;  
        this.price = price; 
        this.latch = latch;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Thread "+Thread.currentThread().getName()+" is waiting");
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" finished execution");
		
	} 
	
	
	public static void main(String[] args) {
		Drawable d = s->{
			//System.out.println(" draewing"+s); 
			return s;
		};
		
	String b =	d.draw("hello");
	System.out.println(b);
		
	}
}

@FunctionalInterface
interface Drawable{  
    public String draw(String s); 
    
   // public void draw2(String s);
} 
 