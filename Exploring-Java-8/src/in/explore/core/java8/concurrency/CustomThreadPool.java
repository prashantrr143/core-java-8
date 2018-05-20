package in.explore.core.java8.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * Creating a custom implementation of Thread pool.
 * 
 * <p>
 * Building Blocks of this Pool
 * <ul>
 * <li>Custom Task Type : Task Type executed by the pool</li>
 * <li>Custom Blocking Queue : Custom Blocking queue simulating a queue with
 * methods to add and get task from the queue.</li>
 * <li>Worker Thread : A special type of thread, employed by Custom Thread pool
 * to process the tasks submitted to the pool.</li>
 * <li>Custom Thread Factory :A Factory for creating Threads. Here we are only
 * using it to set some defaults.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author prashantsingh
 * @since 05/20/2018
 *
 */
public class CustomThreadPool {

	/**
	 * Queue to hold Runnable tasks
	 */
	private CustomBlockingQueue<Runnable> queue;
	/**
	 * Custom Thread factory
	 */
	private CustomThreadFactory threadFactory = new CustomThreadFactory();

	public CustomThreadPool(int queueSize, int nThread) {
		queue = new CustomBlockingQueue<>(queueSize);
		System.out.println(" Initializing Custom Thread Pool with  \n Queue of Size :" + queueSize
				+ "  and worker Thread pool size :" + nThread);
		WorkerThread task = null;
		for (int count = 0; count < nThread; count++) {
			task = new WorkerThread(queue);
			Thread thread = threadFactory.newThread(task);
			thread.start();
		}
	}

	/**
	 * Method to add a task to the queue.
	 * 
	 * @param task
	 * @throws InterruptedException
	 */
	public void submitTask(Runnable task) throws InterruptedException {
		queue.addToQueue(task);
	}

	/**
	 * 
	 * Creating a Custom Thread factory to create threads with custom Defaults.
	 * Using here only to set daemon status , priority and a custom name to
	 * distinguish from system and application threads.
	 * 
	 * @author prashantsingh
	 * @since 05/20/2018
	 *
	 */
	static class CustomThreadFactory implements ThreadFactory {

		private static AtomicInteger counter = new AtomicInteger(1);

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setName(" Custom Thread [" + counter.getAndIncrement() + "] ");
			// thread.setDaemon(true);
			thread.setPriority(Thread.NORM_PRIORITY);
			System.out.println(" From Thread Factory  : Created  " + thread.getName());
			return thread;
		}
	}

	/**
	 * A Custom Blocking Queue implementation. Keeping it simple. This class
	 * does not extends any Queue interface.
	 * 
	 * @author prashantsingh
	 *
	 * @param <T>
	 */
	static class CustomBlockingQueue<T> {

		/**
		 * Queue to hold tasks
		 */
		Queue<T> taskQueue = new LinkedList<>();
		private static final int EMPTY = 0;
		private final int MAX_TASK_IN_QUEUE;

		public CustomBlockingQueue(int size) {
			this.MAX_TASK_IN_QUEUE = size;
		}

		private synchronized void addToQueue(T task) throws InterruptedException {
			while (this.taskQueue.size() == this.MAX_TASK_IN_QUEUE) {
				System.out.println(" Queue is Full   : " + Thread.currentThread().getName() + " is put to wait");
				wait();
			}
			if (this.taskQueue.size() == EMPTY) {
				notifyAll();
			}
			this.taskQueue.offer(task);

		}

		private synchronized T getFromQueue() throws InterruptedException {
			while (this.taskQueue.size() == EMPTY) {
				System.out.println(" Queue is Empty  : " + Thread.currentThread().getName() + " is put to wait");
				wait();
			}
			if (this.taskQueue.size() == this.MAX_TASK_IN_QUEUE) {
				notifyAll();
			}
			return this.taskQueue.poll();
		}

	}

	/**
	 * Worker Threads are responsible for picking up tasks from the taskQueue
	 * and process them.
	 * 
	 * @author prashantsingh
	 *
	 */
	static class WorkerThread implements Runnable {

		CustomBlockingQueue<Runnable> workQueue;

		public WorkerThread(CustomBlockingQueue<Runnable> queue) {
			this.workQueue = queue;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String name = Thread.currentThread().getName();
					Runnable task = workQueue.getFromQueue();

					System.out.println(
							"Task [" + ((CustomTask) task).getTaskNumber() + " ] =>Started by Thread :" + name);
					task.run();
					System.out.println(
							"Task   [" + ((CustomTask) task).getTaskNumber() + " ] => Finished by Thread :" + name);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This class represents the type of task our thread pool will execute.
	 * 
	 * @author prashantsingh
	 *
	 */
	static class CustomTask implements Runnable {

		private int number;

		public CustomTask(int number) {
			this.number = number;
		}

		public int getTaskNumber() {
			return this.number;
		}

		public void run() {

			System.out
					.println("Start executing of task number :" + number + "   By " + Thread.currentThread().getName());
			try {
				// Simulating processing time perform tasks
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("End executing of task number :" + number + "   By " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		CustomThreadPool threadPool = new CustomThreadPool(4, 5);

		// Created 10 Tasks and submit to pool
		for (int i = 1; i <= 10; i++) {
			CustomTask task = new CustomTask(i);
			threadPool.submitTask(task);
		}

	}

}
