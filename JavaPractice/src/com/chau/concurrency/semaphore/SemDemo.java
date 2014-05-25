package com.chau.concurrency.semaphore;

import java.util.concurrent.*;

public class SemDemo {
	
	public static void main(String[] args){
		
		Semaphore sem = new Semaphore(1);
		IncTask task1 = new IncTask(sem, "A");
		DecTask task2 = new DecTask(sem, "B");
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(task1);
		service.submit(task2);
		
		service.shutdown();
		
	}
	

}

// A shared resource
class Shared {
	static int count = 0;
}

class IncTask implements Runnable {
	private String name;
	private Semaphore sem;
	
	public IncTask(Semaphore sem, String name){
		this.name = name;
		this.sem  = sem;
	}
	
	@Override
	public void run() {
		
		System.out.printf("IncThread: Starting %s\n", name);
		
		try {
			//First, get a permit
			System.out.printf("IncThread: %s is waiting for a permit.\n", name);
			sem.acquire();
			System.out.printf("IncThread: %s gets a permit.\n", name);
			
			// Now, access shared resource
			for(int i=0; i<5; i++){
				Shared.count++;
				System.out.printf("IncThread: %s: %s\n", name, Shared.count);
				
				// Now, allow a context switch -- if possible.
				TimeUnit.MILLISECONDS.sleep(100);
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("IncThread: %s releases the permit.\n", name);
		sem.release();
		
	}
	
}


class DecTask implements Runnable {
	private String name;
	private Semaphore sem;
	
	public DecTask(Semaphore sem, String name){
		this.name = name;
		this.sem  = sem;
	}
	
	@Override
	public void run() {
		
		System.out.printf("DecThread: Starting %s\n", name);
		
		try {
			//First, get a permit
			System.out.printf("DecThread: %s is waiting for a permit.\n", name);
			sem.acquire();
			System.out.printf("DecThread: %s gets a permit.\n", name);
			
			// Now, access shared resource
			for(int i=0; i<5; i++){
				Shared.count--;
				System.out.printf("DecThread: %s: %s\n", name, Shared.count);
				
				// Now, allow a context switch -- if possible.
				TimeUnit.MILLISECONDS.sleep(100);
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("DecThread: %s releases the permit.\n", name);
		sem.release();
		
	}
	
}

