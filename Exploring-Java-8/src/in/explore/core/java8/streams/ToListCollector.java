package in.explore.core.java8.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 
 * @author prashantsingh
 *
 * @param <T>
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	@Override
	public Supplier<List<T>> supplier() {
		System.out.println(
				"Supplied Called by " + Thread.currentThread().getName() + " : " + this.getClass().getCanonicalName());
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		System.out.println("Accumualtor Called by " + Thread.currentThread().getName() + " : "
				+ this.getClass().getCanonicalName());

		return List::add;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		System.out.println(
				"Combiner Called by " + Thread.currentThread().getName() + " : " + this.getClass().getCanonicalName());

		return (list1, list2) -> {
			System.out.println("List 1 contents = " + list1 + " : By  : " +Thread.currentThread().getName());
			System.out.println("List 2 contents = " + list2 + " : By  : " +Thread.currentThread().getName());;
			list1.addAll(list2);
			System.out.println("Combined List 1 " + list1 + " : By  : " +Thread.currentThread().getName());
			return list1;
		};

	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		System.out.println(
				"Finishier Called by " + Thread.currentThread().getName() + " : " + this.getClass().getCanonicalName());

		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		System.out.println("characteristics Called by " + Thread.currentThread().getName() + " : "
				+ this.getClass().getCanonicalName());

		return Collections.unmodifiableSet(
				EnumSet.of(Collector.Characteristics.IDENTITY_FINISH, Collector.Characteristics.CONCURRENT));
	}

}
