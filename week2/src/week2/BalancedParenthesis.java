package week2;

import java.util.*;

/*
  	a)Using stack check for balanced parenthesis within a string. [2 Marks]
	Input "{()}{}"
	Output true
 */

public class BalancedParenthesis {

	public static void main(String[] args) {

		String expression = "{()}{}";

		// calling checkParenthesis method and storing its value in stack variable of
		// Stack data type
		Stack<Character> stack = checkParenthesis(expression);

		// checking if stack is empty or not
		if (stack.empty()) {

			System.out.println(true);
			System.out.println(expression + " is Balanced");

		} // if condition end

		else {
			System.out.println(false);
			System.out.println(expression + " is not Balanced");

		} // else condition end

	} // main method end

	static Stack<Character> checkParenthesis(String expression) {

		// Creating a Stack of character type
		Stack<Character> stack = new Stack<Character>();

		// Traversing the given expression of String type
		for (int i = 0; i < expression.length(); i++) {

			// If the current character is opening bracket, then pushing it into a stack

			if (expression.charAt(i) == '{' || expression.charAt(i) == '[' || expression.charAt(i) == '(') {

				stack.push(expression.charAt(i));

			} // if condition end

			// Else, if the stack is not empty, And current character of expression is a
			// closing bracket and top character of a stack is matching open bracket then
			// pop it.

			else if (!stack.empty() && ((expression.charAt(i) == ']' && stack.peek() == '[')
					|| (expression.charAt(i) == '}' && stack.peek() == '{')
					|| (expression.charAt(i) == ')' && stack.peek() == '('))) {

				stack.pop();

			} // else if condition end

			else {

				stack.push(expression.charAt(i));

			} // else condition end

		} // for loop end

		return stack;

	} // checkParenthesis method end

} // BalancedParenthesis class end