package week3;

import java.util.*; 
  

class EvaluateRPN { 
    public int calculation(String[] calcExp){ 
    	
        Stack<String> stack = new Stack<String>(); 
        int x, y; 
        String result = ""; 
        String choice; 
        int value = 0; 
        String p = ""; 
  
        for (int i = 0; i < calcExp.length; i++) { 
        	// if character is operand then push it to the stack
            if (!calcExp[i].equals("+") && !calcExp[i].equals("-")
                && !calcExp[i].equals("*") && !calcExp[i].equals("/")) { 
                stack.push(calcExp[i]); 
                continue; 
            }// if condition end 
            else { 
                choice = calcExp[i]; 
            }// else condition end 

            switch (choice) {
            case "+":
 
            	/*
            	 * if encountered + operator then pop two value from stack
            	 * and perform the addition calculation and again push it to the stack
            	 * */
  
                x = Integer.parseInt(stack.pop()); 
                y = Integer.parseInt(stack.pop()); 
                value = x + y; 
                result = p + value; 
                stack.push(result); 
                break; 
  
            case "-": 
            	/*
            	 * if encountered - operator then pop two value from stack
            	 * and perform the subtraction calculation and again push it to the stack
            	 * */
                x = Integer.parseInt(stack.pop()); 
                y = Integer.parseInt(stack.pop()); 
                value = x - y; 
                result = p + value; 
                stack.push(result); 
                break; 
  
            case "*": 
            	/*
            	 * if encountered multiplication operator(*) operator then pop two value from stack
            	 * and perform the multiplication calculation and again push it to the stack
            	 * */
                x = Integer.parseInt(stack.pop()); 
                y = Integer.parseInt(stack.pop()); 
                value = x * y; 
                result = p + value; 
                stack.push(result); 
                break; 
  
            case "/": 
            	/*
            	 * if encountered division (/) operator then pop two value from stack
            	 * and perform the division calculation and again push it to the stack
            	 * */
                x = Integer.parseInt(stack.pop()); 
                y = Integer.parseInt(stack.pop()); 
                value = y / x; 
                result = p + value; 
                stack.push(result); 
                break; 
  
            default: 
                continue; 
            }// switch case end
        }// for loop end
  
        // converting String to integer and returning it
        return Integer.parseInt(stack.pop()); 
    }// calculation method end
}// EvaluateRPN class end