import java.util.function.BiFunction;

public class Operation<R> {

	String visual;
	BiFunction<R, R, R> evaluate;

	public Operation(String visual, BiFunction<R, R, R> evaluate) {
		this.visual = visual;
		this.evaluate = evaluate;
	}

}