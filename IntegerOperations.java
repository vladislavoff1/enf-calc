import java.util.function.BiFunction;

public class IntegerOperations {

	static Operation<Integer> ADD = new Operation<>(
		"(%l) + (%r)",
		(a, b) -> a + b
	);

	static Operation<Integer> SUBTRACT = new Operation<>(
		"(%l) - (%r)",
		(a, b) -> a - b
	);

	static Operation<Integer> MULTIPLY = new Operation<>(
		"(%l) * (%r)",
		(a, b) -> a * b
	);

	static Operation<Integer> DIVIDE = new Operation<>(
		"(%l) / (%r)",
		(a, b) -> a / b
	);
}