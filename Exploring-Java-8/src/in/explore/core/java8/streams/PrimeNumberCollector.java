package in.explore.core.java8.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumberCollector<T> implements Collector<T, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {
			{
				put(true, new ArrayList<>());
				put(true, new ArrayList<>());
			}
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, T> accumulator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		// because accumulator will already have all the data stored in it , no
		// need to transform otherwise
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return null;
	}

}
