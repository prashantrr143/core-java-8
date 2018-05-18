package in.explore.core.java8.streams.domain;

/**
 * Creating a domain specific entity. This class represents a dish from a menu.
 * 
 * 
 * @author prashantsingh
 *
 */
public class Dish {

	/**
	 * Name of Dish
	 */
	private final String name;
	/**
	 * Flag to store its vegetarian status
	 */
	private final boolean vegetarian;
	/**
	 * Field to store calories of this dish
	 */
	private final int calories;
	/**
	 * Field to store type of dish
	 */
	private DishType type;

	public Dish(String name, boolean vegetarian, int calories, DishType type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public DishType getType() {
		return type;
	}

	public void setType(DishType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", vegetarian=" + vegetarian + ", calories=" + calories + ", type=" + type + "]";
	}
	
	

}
