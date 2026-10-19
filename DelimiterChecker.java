DelimiterChecker.java
package delimiterchecker;
/*
 * Description: Checks if delimiter sequences are properly matched using a Stack data structure
 * Programmed by: Lawrence Rain P. Castillo
 * BSIT-2 
 * Last Modified: October 19 2026
 * Version: 1.0
 * Acknowledgements: Java standard documentation (java.util.Stack)
 */
import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {

    public static void checkDelimiters(String input) {
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        
        System.out.println("Processing Input: " + input);
        System.out.println("----------------------------------------");

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                System.out.printf("Read: %c | Operation: PUSH | Stack: %s\n", ch, stack);
            } 
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.printf("Read: %c | Operation: ERROR | Reason: Closing delimiter '%c' has no matching opening delimiter.\n", ch, ch);
                    isValid = false;
                    break;
                }

                char top = stack.peek();
                System.out.printf("Read: %c | Operation: PEEK -> %c\n", ch, top);

                if (isMatchingPair(top, ch)) {
                    stack.pop();
                    System.out.printf("        | Operation: POP  | Stack: %s\n", stack);
                } else {
                    System.out.printf("        | Operation: ERROR | Reason: Mismatched delimiters ('%c' does not match '%c').\n", top, ch);
                    isValid = false;
                    break;
                }
            }
        }

       
        if (isValid) {
            if (!stack.isEmpty()) {
                System.out.printf("Result: INVALID | Reason: Unmatched opening delimiter(s) remain on stack: %s\n", stack);
            } else {
                System.out.println("Result: VALID | All delimiters are properly matched.");
            }
        } else {
            System.out.println("Result: INVALID");
        }
        System.out.println("----------------------------------------\n");
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a delimiter sequence: ");
        String userInput = sc.nextLine();
        checkDelimiters(userInput);
        sc.close();
    }
}
    }
}
