import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ningh4_assignment3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Take the input as a string
        String inputs;
        inputs = scanner.nextLine();
        //Call doCalculation function to calculate

        //Check if the input is valid
        //If the input contains characters except 0-9, (, ), @, &
        if (! inputs.matches("^[0-9()@&]+$")) {
            //Print invalid char error
            System.out.println("INVALID CHARACTERS");
            return; //Terminate
        //If input is empty bracket or has non-matching brackets, print invalid expression error
        } else if (emptyBracket(inputs) || (! checkExpression(inputs))) {
            System.out.println("INVALID EXPRESSION");
            return; //Terminate
        } else {
            try { //Do the calculation first, if exceptions, print invalid expression error
                int result = doCalculation(inputs);
                System.out.println(result);
            }
            catch (Throwable e) {
                System.out.println("INVALID EXPRESSION");
                return; //Terminate
            }
        }
    }

    //Check if input is an empty bracket
    private static boolean emptyBracket(String inputs) {
        for (int k = 0; k < inputs.length() - 1; k++) {
            if (inputs.toCharArray()[k]==('(') && inputs.toCharArray()[k+1]==(')')) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkExpression(String inputs) {
        //Check for balanced brackets using stack
        Stack<Character> stack = new Stack<>();
        char element;
        for (int i = 0; i < inputs.length(); i++) {
            element = inputs.charAt(i);
            //If opening bracket, push into stack
            if (element == '(') {
                stack.push(element);
            } else if (element == ')') {
                //If closing bracket and stack is empty, return false
                if (stack.isEmpty()) {
                    return false;
                    //If closing bracket and the first element of stack is '(', then pop from stack
                } else if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static int doCalculation(String inputs) {
        //Add brackets for the whole input to make sure every operator is executed
        inputs = '(' + inputs + ')';

        //Use stack algorithm to deal with brackets
        Stack<String> stack = new Stack<>();
        char[] theArray = inputs.toCharArray();

        StringBuilder sub_inputs = new StringBuilder();

        for (int i = 0; i < theArray.length; i++) {
            if (theArray[i] >= '0' && theArray[i] <= '9') {
                sub_inputs.append(theArray[i]);
            } else {
                if (sub_inputs.length() > 0) {
                    stack.push(sub_inputs.toString());
                    sub_inputs = new StringBuilder();
                }
                //If the current char is a opening bracket, push it into stack
                if (theArray[i] != ')') {
                    stack.push(new String(new char[]{theArray[i]}));
                //If the current char is a closing bracket, pop from stack and calculate
                } else {
                    ArrayList<String> calPart = new ArrayList<>();
                    while (! stack.isEmpty()) {
                        String u = stack.pop();
                        if (u.equals("(")) {
                            break;
                        } else {
                            calPart.add(0, u);
                        }
                    }
                    //Calculation
                    int t_result = 0;
                    //If there is only one integer in bracket, the result is this integer itself
                    if (calPart.size() == 1) {
                        t_result = Integer.parseInt(calPart.get(0));
                    } else {
                        for (int j = (calPart.size() - 1); j > 0; j = j - 2) {
                            //If the operator is @, take the minimum of two integers
                            if (calPart.get(j - 1).equals("@")) {
                                t_result = Math.min(Integer.parseInt(calPart.get(j - 2)), Integer.parseInt(calPart.get(j)));
                            //If the operator is &, take the maximum of two integers
                            } else {
                                t_result = Math.max(Integer.parseInt(calPart.get(j - 2)), Integer.parseInt(calPart.get(j)));
                            }
                        }
                    }
                    stack.push(String.valueOf(t_result));
                }
            }
        }
        //When stack is not empty, add top of the stack into resList and return as result
        ArrayList<String> resList = new ArrayList<>();
        while (! stack.isEmpty()) {
            String item = stack.pop();
            resList.add(0, item);
        }

        return Integer.parseInt(resList.get(0));

    }
}





