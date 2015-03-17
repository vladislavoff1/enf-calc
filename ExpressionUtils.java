import java.util.function.Function;

class ExpressionUtils {
	
	public static <T> T evaluate(Node<T, Operation<T>> expression) {
		return expression.process((l) -> l, (f, l, r) -> f.evaluate.apply(l, r));
	}

	public static <T> String toString(Node<T, Operation<T>> expression) {
		return expression.process(

			(l) -> Character.MAX_VALUE + l.toString(), // numbers have maximal priority

			(f, l, r) -> (char) f.priority + f.visual
				.replaceAll("%l", (int) l.charAt(0) > f.priority ? "%l" : "(%l)")
				.replaceAll("%l", l.substring(1))
				.replaceAll("%r", r.charAt(0) > f.priority ? "%r" : "(%r)")
				.replaceAll("%r", r.substring(1))

		).substring(1);
	}

	public static <T> Node<T, Operation<T>> changeLeafs(Node<T, Operation<T>> expression, Function<T, T> change) {
		return expression.process(
			
			l -> new Leaf<>(change.apply(l)), 

			(Operation<T> f, Node<T, Operation<T>> l, Node<T, Operation<T>> r) -> new BiNode<>(new Operation<>(f), l, r) // How can I minimize this line?
		
		);
	}

}