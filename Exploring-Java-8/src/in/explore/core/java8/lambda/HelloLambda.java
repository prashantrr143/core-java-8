package in.explore.core.java8.lambda;

import java.util.function.Predicate;

/**
 * Introduction to Lambda Expressions introduced in Java 8
 * 
 * @author prashantsingh
 *
 */
public class HelloLambda {

	public void printLambdaExpression(String str) {
		Predicate<String> predicate = s -> s.length() > 10;
		Predicate<String> predicate2 = s -> s.length() < 10;
		System.out.println("Is length greater than 10 " + predicate.test(str));
		System.out.println("Is length less than 10 " + predicate2.test(str));
		System.out.println("Calculation Done by Lambda Expression " + predicate + "\n"  + predicate2);

	}

	public static void main(String args[]) {

		HelloLambda helloLambda = new HelloLambda();
		helloLambda.printLambdaExpression("Prashant Kumar Singh");

	}

}
