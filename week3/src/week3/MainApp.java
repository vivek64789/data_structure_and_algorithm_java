package week3;

public class MainApp {

	public static void main(String[] args) {
		
		String expression = "4*(5+6)";
		System.out.println("Infix Expression(Input): " + expression);
		System.out.println("RPN Conversion(Output): " + InfixToRPN.convertInfixToRPN(expression));
		String conversion = InfixToRPN.convertInfixToRPN(expression);

		String str[] = new String[conversion.length()];

		for (int i = 0; i < conversion.length(); i++) {
			char c = conversion.charAt(i);
			str[i] = String.valueOf(c);
		}// for loop end

		EvaluateRPN evalObj = new EvaluateRPN();
		int evaluatedValue= evalObj.calculation(str);
		System.out.println("Evaluated Value: "+evaluatedValue);
	}// main method end

}// MainApp class end
