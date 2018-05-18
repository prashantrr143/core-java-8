package in.explore.core.java8.streams;

import java.util.stream.LongStream;

public class SerialVsParallelStream {

	public static Long serialSum(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}

	public static Long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}

	public static void main(String args[]) {

		System.out.println("Serial Sum Done in "
				+ MeasurePerformance.measurePerf(SerialVsParallelStream::serialSum, 1_000_000) + " msecs");

	System.out.println("Parallel Sum Done in "
				+ MeasurePerformance.measurePerf(SerialVsParallelStream::parallelRangedSum, 1_000_000) + " msecs");

	}
}
