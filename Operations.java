import java.util.function.BiFunction;

public class Operation<R> {

	String visual;
	int priority;
	BiFunction<R, R, R> evaluate;

	public Operation(String visual, int priority, BiFunction<R, R, R> evaluate) {
		this.visual = visual;
		this.priority = priority;
		this.evaluate = evaluate;
	}

	
}