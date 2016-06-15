package finalReview;

public interface InterfaceA {
	void aMethod();
	default void method(){
		System.out.println("interA");
	}
}
