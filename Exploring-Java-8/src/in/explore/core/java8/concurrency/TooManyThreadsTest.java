package in.explore.core.java8.concurrency;

import java.lang.management.ThreadInfo;

public class TooManyThreadsTest {

	public static void main(String args[]) throws InterruptedException {

		ThreadWarningSystem tws = new ThreadWarningSystem(1000);

		tws.addListener(new ThreadWarningSystem.Listener() {

			@Override
			public void thresholdExceeded(ThreadInfo[] allThreads) {
				System.out.println("Threshold Exceeded ");
				System.out.println("Threads Length " + allThreads.length);

			}

			@Override
			public void deadlockDetected(ThreadInfo deadLockedThreadInfo) {
				// TODO Auto-generated method stub

			}
		});
		
		createBatchOfThreads();
		Thread.sleep(10000);
		System.out.println("We should have  dipped below this threshold");
		createBatchOfThreads();

	}

	private static void createBatchOfThreads() {
		for (int i = 0; i < 1000; i++) {
			new Thread() {
				{
					start();
				}

				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
				}
			};
		}
	}

}
