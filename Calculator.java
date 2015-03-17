import java.util.function.Function;

public class Calculator {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No expression. Enter something like: \njava Calculator \"(1 + 2) * 3\"");
			return;
		}

		for (int i = 0; i < args.length; i++) {
			// Parse expression
			Node<Integer, Operation<Integer>> expression = new ExpressionParser().parse(args[i]);

			// Calculate result
			Integer res = ExpressionUtils.evaluate(expression);

			// To string
			String str = ExpressionUtils.toString(expression);

			// Inversion
			Node<Integer, Operation<Integer>> invExpression = ExpressionUtils.changeLeafs(expression, l -> -l);
			String invStr = ExpressionUtils.toString(invExpression);

			System.out.println();
			System.out.println(str + " = " + res);
			System.out.println("Inverted: " + invStr);
		}
		
		System.out.println();
	}
}