package in.explore.core.java8.concurrency;

/**
 * Basics of creating and starting a Thread in java
 * 
 * 1. We can directly instantiate Thread 2. Or, we can provide implementation of
 * Runnable Interface and override run() method.
 * 
 * @author prashantsingh
 *
 */
public class BasicThread {

	public static void main(String args[]) throws InterruptedException {

		// Creating a new Thread here
		Thread myThread = new Thread(new MyRunnable());
		// setting user defined name
		myThread.setName("MyThread-1");
		// running a new thread
		myThread.start();
		
		
		Thread myExtendedThread = new Thread(new MyExtednedThread());
		myExtendedThread.setName("MyExtendedThread1Approach");
		myExtendedThread.start();
		
		MyExtednedThread myExtendedThread2 = new MyExtednedThread();
		myExtendedThread2.setName("MyExtendedThread2Approach");
		myExtendedThread2.start();
		
		System.out.println(Thread.currentThread().getName() + "  ==> Main thread name");
		/**
		 * Joining the main thread with the myThread . the main thread will wait for its
		 * turn for processing , till myThread is completed.
		 */
		myThread.join();
		
		myExtendedThread.join();
		myExtendedThread2.join();
		
		
		System.out
				.println("Again Printed by " + Thread.currentThread().getName() + " here. After MyThead is completed ");

	}

	/**
	 * Directly extending Thread class. Not to be used if the intention is to use
	 * only for run() implementation.
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class MyExtednedThread extends Thread {

		@Override
		public void run() {

			try {
				for (int i = 1; i <= 10; i++) {
					System.out.println(" Printed By :" + Thread.currentThread().getName() + " ==>" + i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}

	}

	/**
	 * Implementation of Runnable : To be used as a concurrent task
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class MyRunnable implements Runnable {
		public void run() {
			try {
				for (int i = 1; i <= 10; i++) {
					System.out.println(" Printed By :" + Thread.currentThread().getName() + " ==>" + i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

}
