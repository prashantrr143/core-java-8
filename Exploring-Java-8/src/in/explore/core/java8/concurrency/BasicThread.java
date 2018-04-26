package in.explore.core.java8.concurrency;

public class BasicThread {

	public static void main(String args[]) throws InterruptedException {

		Thread myThread = new Thread(new MyRunnable());
		myThread.setName("MyThread-1");
		myThread.start();
		System.out.println(Thread.currentThread().getName() +"  ==> Main thread name");
		myThread.join();
		System.out.println("Again Printed by " + Thread.currentThread().getName() + " here. After MyThead is completed ");
		

	}

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
