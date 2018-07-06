package in.explore.core.java8.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private final long[] numbers;
	private int start;
	private int end;

	public static final long THRESHOLD = 100;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.start = start;
		this.end = end;
		this.numbers = numbers;
	}

	private long computeSequentially(long[] numbers) {
		long sum = Arrays.stream(numbers).sum();
		return sum;
	}

	@Override
	public Long compute() {
		System.out.println(" Current Thread of Execution : " + Thread.currentThread().getName() + " \t  start = " + start
				+ " end  = " + end);
		int length = end - start;
		if (length < THRESHOLD) {
			return computeSequentially(numbers);
		}
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
		leftTask.fork();
		long rightSum = rightTask.compute();

		long leftSum = leftTask.join();
		return leftSum + rightSum;
	}
	
	
	public static void main(String args[]){
		
		long[] numbers = LongStream.rangeClosed(1, 1_00_0).toArray();
		ForkJoinSumCalculator sumCalculator = new ForkJoinSumCalculator(numbers);
		System.out.println(" ForkJoin Pool Configuration \n");
		ForkJoinPool pool = new ForkJoinPool();
		System.out.println(pool);
		System.out.println(" \n Final Sum = " +  pool.invoke(sumCalculator));
		
		
		
	}
	
	
}




