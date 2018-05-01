package in.explore.core.java8.lambda;

/**
 * 
 * Class to demonstrate usage of a custom functional interface
 * 
 * @author prashantsingh
 * @since 05/01/2018
 *
 */
public class BasicLambdaExpression2 {

	public static void main(String args[]) {

		/**
		 * To use Adder interface , we need to provide an implementation of the abstract
		 * methods defined by it .
		 */

		// Approach #1 : Creating an Anonymous class to provide implementation of add
		// method
		Adder add = new Adder() {
			// anonymous class
			@Override
			public int add(int a, int b) {
				return a + b;
			}

		};

		// Approach #2 : Creating a lambda expression to implement add method

		// Here we are also holding reference of lambda expression , which can
		// again be used somewhere else if required.

		Adder lambdaAdd = (a, b) -> (a + b);

		System.out.println(" Addition Result Anonymous class ==>" + add.add(10, 20));
		System.out.println(" Addition Result Lambda Function ==>" + lambdaAdd.add(10, 20));

	}

}
