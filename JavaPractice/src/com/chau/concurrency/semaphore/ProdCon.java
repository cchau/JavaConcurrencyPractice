package com.chau.concurrency.semaphore;

import java.util.concurrent.*;

class SemQueue{
	int n;
	
	// Start with consumer semaphore unavailable.
	static Semaphore semCon = new Semaphore(0);
	static Semaphore semProd = new Semaphore(1);
	
	public void get() {
		try {
			semCon.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.printf("Got: %d%n", n);
		semProd.release();
		
	}
	
	public void put(int n){
		
		try {
			semProd.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.n = n;
		System.out.printf("Put: %d%n", n);
		semCon.release();
		
	}
	
}

class Producer implements Runnable {
	SemQueue q;
	
	public Producer(SemQueue q){
		this.q = q;
	}

	@Override
	public void run() {
		for(int i=0; i<20; i++) q.put(i);
		
	}
	
}

class Consumer implements Runnable {
	SemQueue q;
	
	public Consumer(SemQueue q){
		this.q = q;
	}

	@Override
	public void run() {
		for(int i=0; i<20; i++) q.get();
	}
}

public class ProdCon {
	
	public static void main(String[] args){
		SemQueue q = new SemQueue();
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		service.submit(new Consumer(q));
		service.submit(new Producer(q));
		
		service.shutdown();
		
	}

	
}
