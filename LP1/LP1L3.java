package cs6301.g45;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import cs6301.g45.Num;

/**
 * A driver program that illustrates the methods, based on the following input/output specification
 * @author Lopamudra 
 */

public class LP1L3 {

	ArrayList<String> expression = new ArrayList<>();	//list to store the expressions
	
    public static void main(String[] args) {
    	LP1L3 x = new LP1L3();
    	Scanner sc = new Scanner(System.in);
    	String exp = "";
    	System.out.println("Enter your expressions:");

    	//Takes expressions as input
    	while (!(exp = sc.nextLine()).isEmpty()) {
			x.expression.add(exp);
		}
    	sc.close();
    	x.inputOutputSpec();
    }
    
    /**
     *  Method to illustrate the input/output specification
     */
    private void inputOutputSpec() {
		Stack<String> stack = new Stack<>();	//Stack to evaluate postfix expression
		Num var1 = new Num();					//Num variable on which operations are performed
		Num var2 = new Num();					//Num variable on which operations are performed
		Num printLast = new Num();				//To keep track of the last variable which was assigned a value
		
		for (int i = 0; i < expression.size(); i++) {
			String str = expression.get(i);		//Holds the expression one by one
			Num result = null;					//Holds the result of postfix expression operation

			//If the expression is not only ';'
			if (str.contains("=")) {
				
				String[] split = str.split("=");
				String operator = getOperator(split[1]);	//To keep track of the operator that the expression contains
				String var = split[0].trim();				//To keep track of the operand of the left side of = in the expression
				String varl[] = split[1].trim().split(";");
				boolean flag = varl[0].trim().matches("-?\\d+");	//To check if the expression contains digits or operand/operator
				
				if (flag == true) {		//If expression contains only a variable then just output the value of the variable
					if (operator.isEmpty()) {
						Num val = new Num(varl[0].trim());
						System.out.println(val); 
						if(i+1 == expression.size()-1) {	
							printLast = val;
						}
					}
					continue;					
				} 
				else if (flag == false) {	//If it is a postfix expression then its value is evaluated and printed
					if (!operator.isEmpty()) {
						for (int j = 0; j < split[1].length(); j++) {
							
							char ch = split[1].charAt(j);
							if (Character.isLetter(ch)) {
								stack.push(ch + "");		//If the character is an operand then it is pushed into the stack
							} 
							else if (!Character.isSpace(ch) && ch != ';') {	//If the character is an operator then pop two elements and apply the operator  

								String val1 = stack.pop();	
								String val2 = stack.pop();
								
								var2 = isContains(val1, expression);	//Gets the value of the operand popped from stack
								var1 = isContains(val2, expression);	//Gets the value of the operand popped from stack
								
								switch (operator) {	//Apply operation according to the operator found in the expression
								case "+":
									result = Num.add(var1, var2);
									break;
								case "-":
									result = Num.subtract(var1, var2);
									break;
								case "*":
									result = Num.product(var1, var2);
									break;
								case "/":
									result = Num.divide(var1, var2);
									break;
								case "%":
									result = Num.mod(var1, var2);
									break;
								case "^":
									result = Num.power(var1, var2);
									break;
								case "|":
									result = Num.squareRoot(var1);
									break;
								}

								System.out.println(result);			//Prints the value of the evaluated postfix expression
								if(i+1 == expression.size()-1) {	//Keeps track of the last variable that was assigned a value
									printLast = result;
								}
							}
						}
						expression.remove(i);						
						String element = var+" = "+result+" ;";
						expression.add(i, element);					//Stores the postfix expression value to the list
					}
					
				}
			}	
			else if (str.contains(";") && str.length() == 1) {  //If the expression is only ';' then  it prints the last variable that was assigned a value
				if (i != 0) {
					printLast.printList();
				}
				else {
					System.out.println("No expressions stored yet. Re-run the program");
				}
				break;
			} 
			else {
				String[] sp = str.split(" ");
				Num n = isContains(sp[0], expression);
				System.out.println(n);
			}
		}
	}

    /**
     * Method to check if the expression contains a variable and to return its value if it exists
     * @param var: String - To check if this var is contained in the expression
     * @param list: ArrayList - To check in the list which stores the expression
     * @return res: Num - returns the value of var
     */
    public static Num isContains(String var, ArrayList<String> list) {
    	Num res = new Num();
    	for (String item : list) {
	        if (item.startsWith(var) && item.contains("=")) {
	            String[] spl = item.split("=");
	            spl = spl[1].split(";");
	            res = new Num(spl[0].trim());
	        } 
	    }
    	return res;
    }
	
    /**
     * Method to get the postfix expression operator 
     * @param str: String - string expression that contains the operator
     * @return s: String - operator
     */
	public static String getOperator(String str) {
		String[] op = { "+", "-", "*", "/", "%", "^", "|" };
		for (String s : op) {
			if (str.contains(s)) {
				return s;
			}
		}
		return "";
	}

}
