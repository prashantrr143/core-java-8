package in.explore.core.java8.streams.mapping;

import java.util.List;

import in.explore.core.java8.streams.domain.Dish;
import in.explore.core.java8.streams.domain.MenuFactory;

public class MenuOperations {
	
	
	public void listMenuItems(List<Dish> menu){
		
		menu.stream().forEach(System.out::println);
		
	}
	
	
	

	public static void main(String args[]) {
		List<Dish> menu =  MenuFactory.createMenu();
		MenuOperations operations = new MenuOperations();
		operations.listMenuItems(menu);
		

	}

}
