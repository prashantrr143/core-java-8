package in.explore.core.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapVsFlatMap {
	
	
	

	public static void main(String args[]) {

		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5);

		Stream<Stream<int[]>> mapArray = list1.stream().map(i -> list2.stream().map(j -> new int[] { i, j }));

		Stream<int[]> flatMapArray = list1.stream().flatMap(i -> list2.stream().map(j -> new int[] { i, j }));

	}

}
