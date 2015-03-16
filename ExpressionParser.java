public class ExpressionParser {
    // recursive descent parse

    private class ParseError extends Error {

        public ParseError(int pos) {
            super("Parse error at " + pos);
        }

        public ParseError(String s) {
            super(s);
        }

    }

    private String statement;
    private int position = 0;

    public Node<Integer, Operation<Integer>> parse(String s) {

        statement = removeSpaces(s);

        Node<Integer, Operation<Integer>> ex;

        try {
            ex = expression();
        } catch (ParseError e) {
            e.printStackTrace(System.out); // For easy debugging
            return null;
        }

        return ex;

    }

    // parsing 'expression + term', 'expression - term', 'term'
    private Node<Integer, Operation<Integer>> expression() throws ParseError {
        Node<Integer, Operation<Integer>> result = term();

        while (!endOfStatement()) {
            switch (currentChar()) {
                case '+':
                    position++;
                    result = new BiNode<>(IntegerOperations.ADD, result, term());
                    break;
                case '-':
                    position++;
                    result = new BiNode<>(IntegerOperations.SUBTRACT, result, term());
                    break;
                default: 
                    return result;
            }
        }

        return result;
    }

    // parsing 'term * primary', 'term / primary', 'primary'
    private Node<Integer, Operation<Integer>> term() throws ParseError {
        Node<Integer, Operation<Integer>> result = primary();

    while_label:
        while (!endOfStatement()) {
            switch (currentChar()) {
                case '*':
                    position++;
                    result = new BiNode<>(IntegerOperations.MULTIPLY, result, term());
                    break;
                // case '/':
                //     position++;
                //     result = new BiNode<>(IntegerOperations.DIVIDE, result, term());
                //     break;
                default:
                    break while_label;
            }
        }

        return result;
    }

    // parsing '+number', '-number', '(expression)', 'number'
    private Node<Integer, Operation<Integer>> primary() throws ParseError {
        Node<Integer, Operation<Integer>> result;
        if (!endOfStatement()) {
            switch (currentChar()) {
                case '+':
                    position++; // skip unary plus
                    result = primary();
                    break;
                case '-':
                    position++;
                    result = new BiNode<>(IntegerOperations.SUBTRACT, new Leaf<>(0), primary());
                    break;
                case '(':
                    position++;
                    result = expression();
                    if (endOfStatement() || currentChar() != ')') {
                        throw new ParseError("')' expected");
                    }
                    position++;
                    break;
                default:
                    result = number();
                    break;
            }
        } else {
            throw new ParseError("unexpected end");
        }

        return result;
    }

    // parsing int numbers
    private Node<Integer, Operation<Integer>> number() throws ParseError {

        int start = position;
        while (!endOfStatement() && Character.isDigit(currentChar())) {
            position++;
        }

        if (start == position) {
            throw new ParseError(position);
        }

        int num = Integer.parseInt(statement.substring(start, position));

        // position++;

        return new Leaf<>(num);
    }

    private boolean endOfStatement() {
        return position >= statement.length();
    }

    private char currentChar() {
        // if (endOfStatement()) {
        //     error();
        // }
        return statement.charAt(position);
    }

    private String removeSpaces(String s) {
        return s.replaceAll("\\s", "");
    }
}