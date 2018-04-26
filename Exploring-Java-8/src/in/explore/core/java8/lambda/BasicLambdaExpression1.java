package in.explore.core.java8.lambda;



/**
 * 
 * Class to display basic usage of Lambda Expression
 * 
 * @author prashantsingh
 *
 */
public class BasicLambdaExpression1 {

	public static void main(String args[]) {

		
		// Pre Java-8 Style of implementing runnable
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// simulates a long running task
				System.out.println("Welcome to Pre-Lambda Development Style");
			}
		});

		// Post Java 8 Style of implementing runnable
		Thread t2 = new Thread(() -> System.out.println("Welcome to Lambda Style  of Development"));

		Thread t3 = new Thread(() -> {
			// Simulates a long running task
			System.out.println("Welcome to Lambda Style  of Development");
		});

		t1.start();
		t2.start();
		t3.start();

	}

}
