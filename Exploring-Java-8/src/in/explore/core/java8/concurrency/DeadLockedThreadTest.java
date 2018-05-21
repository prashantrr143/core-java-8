package in.explore.core.java8.concurrency;

import java.lang.management.ThreadInfo;

public class DeadLockedThreadTest {

	public static void main(String args[]) {

		ThreadWarningSystem tws = new ThreadWarningSystem();
		tws.addListener(new ThreadWarningSystem.Listener() {

			@Override
			public void thresholdExceeded(ThreadInfo[] allThreads) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deadlockDetected(ThreadInfo deadLockedThreadInfo) {
				System.out.println(" ------- DeadLock Detected-------");
				System.out.println("***** DeadLocked Thread*****");
				System.out.println(deadLockedThreadInfo);
				for (StackTraceElement ste : deadLockedThreadInfo.getStackTrace()) {
					System.out.println("\t" + ste);
				}

			}
		});
		
		// deadlock with three locks
	    Object lock1 = new String("lock1");
	    Object lock2 = new String("lock2");
	    Object lock3 = new String("lock3");

	   /* new DeadLockingThread("t1", lock1, lock2);
	    new DeadLockingThread("t2", lock2, lock3);
	    new DeadLockingThread("t3", lock3, lock1);*/

	    // deadlock with two locks
	    Object lock4 = new String("lock4");
	    Object lock5 = new String("lock5");

	    new DeadLockingThread("t4", lock4, lock5);
	    new DeadLockingThread("t5", lock5, lock4);

	}
	
		
	
	private static class DeadLockingThread extends Thread{
		
		private final Object lock1;
		private final Object lock2;
		
		public DeadLockingThread(String name, Object lock1, Object lock2){
			super(name);
			this.lock1 = lock1;
			this.lock2 = lock2;
			start();
		}
		
		
		public void run(){
			while(true){
				f();
			}
		}
		
		private void f(){
			synchronized (lock1) {
				g();
			}
		}
		
		private void g(){
			synchronized (lock2) {
				// do some work
			}
		}
	}
	

}
