package in.explore.core.java8.streams;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class TestCustomCollector {

	
	
	
	public static void main(String args[]){
		
		
		System.out.println(ForkJoinPool.commonPool());
		System.out.println("********************\n\n");
		
		List<Integer> list= IntStream.rangeClosed(1,10).boxed().collect(new ToListCollector<>());
		System.out.println("Transformed List  "+ list);
		
		
		
	}
}
