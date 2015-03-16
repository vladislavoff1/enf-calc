public class Calculator {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No expression. Enter something like: \njava Calculator \"(1 + 2) * 3\"");
			return;
		}

		// Parse expression
		Node<Integer, Operation<Integer>> expression = new ExpressionParser().parse(args[0]);

		// Calculate result
		Integer res = expression.process((l) -> l, (f, l, r) -> f.evaluate.apply(l, r));

		// To string
		String str = expression.process(

			(l) -> l.toString(), 

			(f, l, r) -> f.visual
				.replaceAll("%l", l.toString())
				.replaceAll("%r", r.toString())
		);

		System.out.println(str + " = " + res);
	}	
}