package in.explore.core.java8.concurrency.questions.prodcons;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 
 * Another way of dealing with Producer and Consumer
 * 
 * 
 * Here, instead of using any java provided concurrent construct, same
 * functionality can be achieved by using low level methods like wait() ,
 * notify() and notifyAll()
 * 
 * @author prashantsingh
 *
 */
public class WaitNotifyProducerConsumer {

	/**
	 * Field to declare maximum capacity of shared queue.
	 */
	public static final int QUEUE_CAPACITY = 4;

	/**
	 * Runnable implementation of Producer
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class Producer implements Runnable {

		/**
		 * Field to hold reference of Shared Queue between producer and consumer
		 */
		private Queue<String> sharedQueue;

		Producer(Queue<String> sharedQueue) {
			this.sharedQueue = sharedQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					// Need to access sharedQueue is synchronized context as multiple threads
					// producer/consumer
					// will be accessing it.
					synchronized (sharedQueue) {
						// Checking for queue capacity
						// if queue size is equal to maximum capacity, then producer threads should be
						// put on hold
						// as there is no space to add any new values in queue
						while (sharedQueue.size() >= QUEUE_CAPACITY) {
							System.out
									.println("Queue is full --> " + Thread.currentThread().getName() + "  put to wait");
							// putting the current thread in waiting state.
							// we need to invoke wait() on sharedQueue instance as we are using that as
							// monitor object here
							sharedQueue.wait();
						}
						// If the queue is not full, push the message on to the queue and notify all
						// others threads waiting
						// to acquire lock on this queue.
						String message = getMesageForCurrentThread(Thread.currentThread().getName());
						System.out.println("Producer " + Thread.currentThread().getName() + " Pushed " + message);
						sharedQueue.offer(message);
						sharedQueue.notifyAll();
					}
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					System.out.println(" Exception reported  in Producer THREAD : " + Thread.currentThread().getName());
					ie.printStackTrace();
				}
			}

		}
	}

	/**
	 * Runnable implementation of Consumer
	 * 
	 * @author prashantsingh
	 *
	 */
	private static class Consumer implements Runnable {

		/**
		 * Field to hold reference of Shared Queue between producer and consumer
		 */
		private Queue<String> sharedQueue;

		Consumer(Queue<String> sharedQueue) {
			this.sharedQueue = sharedQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					// Need to access sharedQueue is synchronized context as multiple threads
					// producer/consumer
					// will be accessing it.
					synchronized (sharedQueue) {
						// Checking for queue capacity
						// if queue is empty, then consumer threads should be
						// put on hold
						// as there are no values in queue to be consumed
						while (sharedQueue.isEmpty()) {
							System.out.println(
									"Queue is Empty --> " + Thread.currentThread().getName() + "  put to wait");
							sharedQueue.wait(2000);
						}

						// If the queue is not empty, consumer the message from the queue and notify all
						// others threads waiting to acquire lock on this queue.
						String message = sharedQueue.poll();
						System.out.println(Thread.currentThread().getName()
								+ "    Polled Message from queue : Message => " + message);
						sharedQueue.notifyAll();

					}
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					System.out.println("Exception reported in Consumer THREAD " + Thread.currentThread().getName());
					ie.printStackTrace();
				}
			}

		}

	}

	public static String getMesageForCurrentThread(String currentThreadName) {
		return "Message From Producer Thread " + Thread.currentThread().getName() + "  " + System.currentTimeMillis();
	}

	public static void main(String args[]) {

		Queue<String> sharedQueue = new ArrayDeque<>();

		Thread p1 = new Thread(new Producer(sharedQueue));
		Thread p2 = new Thread(new Producer(sharedQueue));
		Thread p3 = new Thread(new Producer(sharedQueue));
		Thread p4 = new Thread(new Producer(sharedQueue));

		p1.setName("Producer-Thread**1");
		p2.setName("Producer-Thread**2");
		p3.setName("Producer-Thread**3");
		p4.setName("Producer-Thread**4");

		Thread c1 = new Thread(new Consumer(sharedQueue));
		Thread c2 = new Thread(new Consumer(sharedQueue));
		Thread c3 = new Thread(new Consumer(sharedQueue));
		Thread c4 = new Thread(new Consumer(sharedQueue));

		c1.setName("Consumer-Thread**1");
		c2.setName("Consumer-Thread**2");
		c3.setName("Consumer-Thread**3");
		c4.setName("Consumer-Thread**4");

		p1.start();
		p2.start();
		p3.start();
		p4.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();

	}
}
