package week3;

/*
Convert Infix expression to Reverse Polish Notation (RPN) and Evaluate Reverse polish notation using stack. [5 Marks]
Example
Infix: 4(5+6) (input)
RPN conversion: 456+* (output)
Evaluated value: 44 (output)
*/

import java.util.Stack;

public class InfixToRPN {

	public static int priority(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}// switch case end
		return -1;
	}// priority method end

	public static String convertInfixToRPN(String expression) {

		String result = "";
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			// check if char is operator
			if (priority(c) > 0) {
				while (stack.isEmpty() == false && priority(stack.peek()) >= priority(c)) {
					result += stack.pop();
				}
				stack.push(c);
			} // if condition end
			else if (c == ')') {
				char x = stack.pop();
				while (x != '(') {
					result += x;
					x = stack.pop();
				} // while loop end
			} // if condition end
			else if (c == '(') {
				stack.push(c);
			} // else if condition end
			else {
				// character is neither operator nor (
				result += c;
			} // else condition end
		}// for loop end
		for (int i = 0; i <= stack.size(); i++) {
			result += stack.pop();
		}// for loop end
		return result;
	}// convertInfixToRPN method end

}// InfixToRPN class end