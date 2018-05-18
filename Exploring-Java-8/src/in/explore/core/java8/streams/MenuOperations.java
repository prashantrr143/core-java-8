package in.explore.core.java8.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

import in.explore.core.java8.streams.domain.CaloricLevel;
import in.explore.core.java8.streams.domain.Dish;
import in.explore.core.java8.streams.domain.DishType;
import in.explore.core.java8.streams.domain.MenuFactory;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class MenuOperations {

	public void groupDishesByType(List<Dish> menu) {

		Map<DishType, List<Dish>> map = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println("Dishes Grouped by their types \n " + map);
	}

	public void getMaxCaloricDishGroupedByType(List<Dish> menu) {
		Map<DishType, Optional<Dish>> map = menu.stream()
				.collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

		for (Map.Entry<DishType, Optional<Dish>> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().get());
		}
	}

	public void getMaxCaloricDishGroupedByTypeWithOutOptional(List<Dish> menu) {
		Map<DishType, Dish> map = menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

	}

	// Multi Level Grouping

	public void groupDishesByTypeAndCaloricLevel(List<Dish> menu) {

		Map<DishType, Map<CaloricLevel, List<Dish>>> map =

				menu.stream().collect(groupingBy(Dish::getType, groupingBy(d -> {

					if (d.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (d.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;

				})));

		System.out.println("Dishes Grouped by their types and then grouped by caloric level \n " + map);

	}

	public void getDishesCountByDishType(List<Dish> menu) {
		Map<DishType, Long> map = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println("Dishes Grouped by their types and their count \n " + map);
	}

	public static void main(String args[]) {

		MenuOperations operations = new MenuOperations();

		/*
		 * operations.groupDishesByType(MenuFactory.createMenu());
		 * operations.getDishesCountByDishType(MenuFactory.createMenu());
		 * operations.groupDishesByTypeAndCaloricLevel(MenuFactory.createMenu())
		 * ;
		 */

		operations.getMaxCaloricDishGroupedByType(MenuFactory.createMenu());
	}

}
