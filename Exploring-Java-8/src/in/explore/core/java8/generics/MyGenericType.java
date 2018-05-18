package in.explore.core.java8.generics;

public class MyGenericType<T> {

	private T t;

	public T get() {
		return t;
	}

	public void set(T t) {
		System.out.println(t.getClass().getComponentType());
		this.t = t;
	}
	
	
	public static void main(String args[]){
		
		
		MyGenericType<String> stringType  = new MyGenericType<>();
		stringType.set("Prashant");
		System.out.println(stringType);
		
		
	}
}
