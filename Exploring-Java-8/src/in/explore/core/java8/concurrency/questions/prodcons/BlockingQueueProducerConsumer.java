package in.explore.core.java8.concurrency.questions.prodcons;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue Based implementation of Producer and Consumer problem
 * 
 * @author prashantsingh
 * @since 26/06/2018
 *
 *
 *        Producer is a client application which is producing some results and
 *        Consumer is consuming the generated/produced values from the producer.
 * 
 *        Producer puts the values in a shared queue ( accessible to both
 *        producer and consumer). The idea is that, if the queue is full, then
 *        producer should wait till there is some space in queue. Similarly, the
 *        consumer should wait when the queue is empty.
 * 
 *        This problem is also called Bounder Buffer problem.
 *
 */
public class BlockingQueueProducerConsumer {

	/**
	 * Private static class representing a Producer, to be used with in the
	 * context of this class only
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class Producer implements Runnable {

		private BlockingQueue<Integer> workQueue;

		Producer(BlockingQueue<Integer> workQueue) {
			this.workQueue = workQueue;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 20; i++) {
					Thread.sleep(1000);
					int value = i;
					System.out.println(Thread.currentThread().getName() + " trying to put value " + value);
					// put() method is blocking in nature. This method will
					// block
					// if the queue is full
					workQueue.put(value);

				}
			} catch (InterruptedException ie) {
				System.out.println("Exception occurrred in Thread  : " + Thread.currentThread().getName());
				ie.printStackTrace();
			}

		}

	}

	/**
	 * Private static class representing a consumer, to be used with in the
	 * context of this class only
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class Consumer implements Runnable {

		private BlockingQueue<Integer> workQueue;

		Consumer(BlockingQueue<Integer> workQueue) {
			this.workQueue = workQueue;
		}

		public void run() {
			try {
				for (int i = 0; i < 20; i++) {
					Thread.sleep(1000);
					// take() method is blocking in nature. If the value is
					// found in queue
					// value is returned else this method blocks till a value is
					// provided in the queue.
					int value = workQueue.take();
					System.out.println(Thread.currentThread().getName() + " removed  " + value);
				}
			} catch (InterruptedException ie) {
				System.out.println("Exception occurrred in Thread  : " + Thread.currentThread().getName());
				ie.printStackTrace();
			}

		}
	}

	public static void main(String args[]) {

		// Shared work queue , used by all Producer and Consumer Threads\
		BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(4);

		// Creating 4 Producer Thread and same instance of BlokcingQueue is
		// shared among them
		Thread p1 = new Thread(new Producer(sharedQueue));
		Thread p2 = new Thread(new Producer(sharedQueue));
		Thread p3 = new Thread(new Producer(sharedQueue));
		Thread p4 = new Thread(new Producer(sharedQueue));
		p1.setName("Producer-Thread**1");
		p2.setName("Producer-Thread**2");
		p3.setName("Producer-Thread**3");
		p4.setName("Producer-Thread**4");

		// Creating 4 Consumer Thread and same instance of BlokcingQueue is
		// shared among them
		Thread c1 = new Thread(new Consumer(sharedQueue));
		Thread c2 = new Thread(new Consumer(sharedQueue));
		Thread c3 = new Thread(new Consumer(sharedQueue));
		Thread c4 = new Thread(new Consumer(sharedQueue));
		c1.setName("Consumer-Thread**1");
		c2.setName("Consumer-Thread**2");
		c3.setName("Consumer-Thread**3");
		c4.setName("Consumer-Thread**4");

		// Starting all Producers and Consumer Threads
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();

		// Joining all tasks, so that main thread waits till all the threads are
		// done processing.
		try {
			p1.join();
			p2.join();
			p3.join();
			p4.join();
			c1.join();
			c2.join();
			c3.join();
			c4.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		// Marking End of the processing from main thread
		System.out.println(Thread.currentThread().getName() + "   Marks End of the Program");

	}
}
