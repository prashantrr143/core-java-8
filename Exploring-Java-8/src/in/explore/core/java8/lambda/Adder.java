package in.explore.core.java8.lambda;

/**
 * Custom Functional Interface, annotated with @FunctionalInterface and has only
 * one abstract method which can be referenced/implemented using Lambda
 * Expressions
 * 
 * @author prashantsingh
 * @since 05/01/2018
 */

@FunctionalInterface
public interface Adder {

	/**
	 * This method adds two numbers passed as parameters
	 * 
	 * @param a
	 *            int param
	 * @param b
	 *            int param
	 * @return sum of parameters
	 * @author prashantsingh
	 */
	public int add(int a, int b);

	/**
	 * Since Java 8 , an interface can define/ hold static methods as well.
	 * 
	 * @param a
	 *            int param
	 * @param b
	 *            int param
	 * @param c
	 *            int param
	 * @return sum of parameters
	 * @author prashantsingh
	 */
	public static int addThreeNumbers(int a, int b, int c) {
		return a + b + c;
	}

}
