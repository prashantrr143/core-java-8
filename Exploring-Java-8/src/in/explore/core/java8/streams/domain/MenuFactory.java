package in.explore.core.java8.streams.domain;

import java.util.Arrays;
import java.util.List;

/**
 * A Helper class which helps via creating a list of Dishes aka menu.
 * @author prashantsingh
 *
 */
public class MenuFactory {

	public static List<Dish> createMenu() {
		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, DishType.MEAT),
				new Dish("beef", false, 700, DishType.MEAT), new Dish("chicken", false, 400, DishType.MEAT),
				new Dish("french fries", true, 530, DishType.OTHER), new Dish("rice", true, 350, DishType.OTHER),
				new Dish("season fruit", true, 120, DishType.OTHER), new Dish("pizza", true, 550, DishType.OTHER),
				new Dish("prawns", false, 300, DishType.FISH), new Dish("salmon", false, 450, DishType.FISH));
		return menu;

	}

}
