package cs6301.g45;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.EmptyStackException;
import cs6301.g45.OperatorPrecedence.Operators;
import cs6301.g45.Tokenizer.Token;

/**
 * A driver program that evaluates a set of expressions
 * @author Lopamudra 
 */

public class LP1L4 {
	
	/**
	 * Method to evaluate set of expressions
	 * @param base : int base of Num
	 * @param expression : LinkedList<Tokenizer> - contains the infix expression as Tokenizer objects in sequence
	 * @param variable : HashMap <String, Num>
	 * @return Num
	 */
	public static Num shuntYardCall(int base, LinkedList<Tokenizer> expression, ArrayList<String> variable,ArrayList<Num> varValue) {
		LinkedList<Tokenizer> result = new LinkedList<Tokenizer>();
		// if assignment operation return the value
		if(expression.size()==1){
			return new Num(expression.peek().getValue(),base);
		}
		// mapping of values from hashmap to linkedList to pass to shuntYard function
		for (Tokenizer t : expression){
			if (t.getT().equals(Token.OP)){// if operator, pass it as is
				result.add(t);
			}else if(t.getT().equals(Token.VAR)){// if variable, find its value from the hashmap and add it to linked list
				Tokenizer temp = new Tokenizer();
				temp.setT(Token.NUM);
				//temp.setValue(variable.get(t.getValue()).toString());
				temp.setValue(varValue.get(variable.indexOf(t.getValue())).toString());
				result.add(temp);
			}else if(t.getT().equals(Token.NUM)){// if number, pass it to the linked list directly
				Tokenizer temp = new Tokenizer();
				temp.setT(Token.NUM);
				temp.setValue(t.getValue());
				result.add(temp);
			}
		
		}
		
		try {
			return LP1L4.shuntYard(result);
		} catch (IncorrectInputFormatException e) {//to catch incorrect braces in expression
			e.printStackTrace();
		}
		return null;
	}
	/**
     * Method to implement shunt yard algorithm
     * @param expression: LinkedList<Tokenizer> - contains the infix expression as Tokenizer objects in sequence
	 * @return evaluated expression from expressionEval as a Num class object
     */
	public static Num shuntYard(LinkedList<Tokenizer> expression) throws IncorrectInputFormatException {
		Iterator<Tokenizer> it = expression.iterator();
		OperatorPrecedence opP = new OperatorPrecedence();
		Tokenizer tk;
		LinkedList<String> outputQueue = new LinkedList<String>();
		Stack<Operators> operatorStack = new Stack<Operators>();
		while (it.hasNext()) {// loop through the infix expression
			tk = it.next();
			switch (tk.getT()) {
			// if operator, check for the precedence rules and modify stack accordingly
			case OP:
				Operators operator = null;
				try{
				operator = operatorStack.peek();
				}catch(EmptyStackException ese){
					operator = null;
				}
				switch (opP.getOpOP(tk.getValue())) {
				case ADD:
					if(operator!=null){
					while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.ADD))&&(opP.getAssociativity(operator))){
						outputQueue.add(opP.getOpStr(operatorStack.pop()));
						try{
						operator= operatorStack.peek();
						}catch(EmptyStackException ese){
							break;
						}
					}
					}
					operatorStack.push(Operators.ADD);
					break;
				case SUB:
					if(operator!=null){
					while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.SUB))&&(opP.getAssociativity(operator))){
						outputQueue.add(opP.getOpStr(operatorStack.pop()));
						try{
							operator= operatorStack.peek();
							}catch(EmptyStackException ese){
								break;
							}
					}
					}
					operatorStack.push(Operators.SUB);
					break;
				case MUL:
					if(operator!=null){
						
					
					while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.MUL))&&(opP.getAssociativity(operator))){
						outputQueue.add(opP.getOpStr(operatorStack.pop()));
						try{
							operator= operatorStack.peek();
							}catch(EmptyStackException ese){
								break;
							}
					}
					}
						operatorStack.push(Operators.MUL);
					
					break;
				case DIV:
					if(operator!=null){
						while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.DIV))&&(opP.getAssociativity(operator))){
							outputQueue.add(opP.getOpStr(operatorStack.pop()));
							try{
								operator= operatorStack.peek();
								}catch(EmptyStackException ese){
									break;
								}
						}
						}
						operatorStack.push(Operators.DIV);
						break;
				case MOD:
					if(operator!=null){
						while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.MOD))&&(opP.getAssociativity(operator))){
							outputQueue.add(opP.getOpStr(operatorStack.pop()));
							try{
								operator= operatorStack.peek();
								}catch(EmptyStackException ese){
									break;
								}
						}
						}
						operatorStack.push(Operators.MOD);
						break;
				case SQA:
					if(operator!=null){
						while((opP.getPrecedence(operator) >= opP.getPrecedence(Operators.SQA))&&(opP.getAssociativity(operator))){
							outputQueue.add(opP.getOpStr(operatorStack.pop()));
							try{
								operator= operatorStack.peek();
								}catch(EmptyStackException ese){
									break;
								}
						}
						}
						operatorStack.push(Operators.SQA);
						break;
				case POW:
					operatorStack.push(Operators.POW);
					
					break;
				}
				break;
			case NUM:
				outputQueue.add(tk.getValue());
				break;
			case OPEN:
				operatorStack.push(Operators.OPB);
				break;
			case CLOSE:
				Operators o = null;
				try{
					o = operatorStack.pop();
				}catch(EmptyStackException ese){
					throw new IncorrectInputFormatException("Improper Braces");
				}
				// pop all the operators from the stack after a matching close brace occurs 
				while(!o.equals(Operators.OPB)){
					outputQueue.add(opP.getOpStr(o));
					try{
						o = operatorStack.pop();
					}catch(EmptyStackException ese){
						throw new IncorrectInputFormatException("Improper Braces");
					}
				}
				break;

			}
		}
		
		while(!operatorStack.isEmpty()){
			Operators t = operatorStack.peek();
			if (t.equals(Operators.OPB)||t.equals(Operators.CLB)){
				throw new IncorrectInputFormatException("Improper Braces");				
			}
			outputQueue.add(opP.getOpStr(operatorStack.pop()));
		}
		
		return expressionEval(outputQueue);
	}
	/**
     * Method to evaluate postfix expressions given by shunt yard algorithm
     * @param expression: LinkedList<Tokenizer> - contains the postfix expression as String objects in sequence
	 * @return evaluated expression as a Num class object
     */
	public static Num expressionEval(LinkedList<String> expression){
		Num result = null;
		Stack<Num> values = new Stack<Num>();
		Num two = null;
		Num one = null;
		for (String token : expression){
			if (token.replaceAll("[0-9]", "").equals("")){
				values.push(new Num(token));
			}else{
				// apply the arithmetic based on the operators
				switch(token){
				case "+":
					values.push( Num.add(values.pop(),values.pop()));
					break;
				case "-":
					two = values.pop();
					one = values.pop();
					values.push( Num.subtract(one,two));
					break;
				case "*":
					values.push( Num.product(values.pop(),values.pop()));
					break;
				case "/":
					two = values.pop();
					one = values.pop();
					values.push( Num.divide(one,two));
					break;
				case "%":
					two = values.pop();
					one = values.pop();
					values.push( Num.mod(one,two));
					break;
				case "|":
					one = values.pop();
					values.push( Num.squareRoot(one));
					break;
				case "^":
					two = values.pop();
					one = values.pop();
					values.push( Num.power(one, two));
					break;
				}
			}
		}
		
		return values.pop();
	}


	public static void main(String[] args) throws Exception {
		Scanner in;	
		int base = 10;
		if (args.length > 0) {
			base = Integer.parseInt(args[0]);
		}

		in = new Scanner(System.in);
		LP1L4 x = new LP1L4();
		ArrayList<Integer> goToNo=new ArrayList<>();
		ArrayList<Integer> goToNoExp=new ArrayList<>();
		//HashMap<Integer, Integer> goTo = new HashMap<Integer, Integer>();
		LinkedList<LinkedList<Tokenizer>> evaluate = new LinkedList<LinkedList<Tokenizer>>();

		LinkedList<Tokenizer> expression = new LinkedList<Tokenizer>();
		ArrayList<String> variable=new ArrayList<String>();
		ArrayList<Num> varValue=new ArrayList<Num>();
		//HashMap<String, Num> variable = new HashMap<String, Num>();
		Tokenizer var = null;
		
		while (in.hasNext()) {
			String word = in.next();
			Tokenizer t = new Tokenizer();
			t.setValue(word);
			t.setT(Tokenizer.tokenize(word));
			if (word.equals("=")) { // create a variable list
				variable.add(expression.peekLast().value);
				varValue.add(new Num(0,10));
				//variable.put(expression.peekLast().value, new Num(0,10));
				expression.add(t); //doubt
				
			} else if (!word.equals(";")) { 
				// create tokens to become an expression
				expression.add(t);
			} else if (word.equals(";")) { 
				// once end of line is reached,create an expression with all the tokens that are created
				if (expression.isEmpty() && var != null) {
					expression.add(t);	//to printList function
					evaluate.addLast(expression);
					break;
				} else if (expression.isEmpty() && var == null) {
					continue;	//do nothing
				} else {
					var = expression.peekFirst();
					if (var.t == Token.NUM) {
						Integer lineNo = Integer.parseInt(var.value);
						//var = expression.peekFirst();
						var=expression.get(1);
						//expression.addFirst(var); // readding the line number
						 evaluate.add(expression); //adding all expressions
						// here
						// index of expression stored here along with line no.
						 goToNo.add(lineNo);
						 goToNoExp.add(evaluate.indexOf(expression));
						//goTo.put(lineNo, evaluate.indexOf(expression)); 	
						expression = new LinkedList<Tokenizer>();
					} else {
						//expression.addFirst(var);
						evaluate.add(expression); // adding all expressions here
						expression = new LinkedList<Tokenizer>();
					}
				}
			}
		}

		
		Iterator<LinkedList<Tokenizer>> it = evaluate.iterator();
		
		LinkedList<Tokenizer> exp;
		Tokenizer lastVar = null;
		var = null;
		while (it.hasNext()) {
			if (var != null) {
				lastVar = var;
			}
			exp = it.next(); 
			
			if(exp.size()==1){
				var = exp.peekFirst();
				if(var.t==Token.EOL){
					varValue.get(variable.indexOf(lastVar.value)).printList();
					//variable.get(lastVar.value).printList();					
				}else{
					System.out.println(varValue.get(variable.indexOf(var.value)).toString());
					//System.out.println(variable.get(var.value).toString());
				}
				continue;
			}
			int index=0;
			var = exp.peekFirst();
			
			//System.out.println("Outside "+var.value);
			if(var.t==Token.NUM){
				index++;
				var = exp.get(index);
			}
			/*if (var.t == Token.EOL) {
				// printList of latest variable
				variable.get(lastVar.value).printList();
			}else */
			if (exp.get(index+1).t == Token.QS) {
				//if (!variable.get(var.value).toString().equals(new Num(0,base).toString())) {
				if (!varValue.get(variable.indexOf(var.value)).toString().equals(new Num(0,base).toString())) {
					Tokenizer lineNo = exp.get(index+2);
					//lineNo = exp.remove();
					int index1= goToNoExp.get(goToNo.indexOf(Integer.parseInt(lineNo.value)));
					//int index1 = goTo.get(Integer.parseInt(lineNo.value));
					it = evaluate.iterator();
					while (it.hasNext() && index1 != 0) {
						exp = it.next();
						/*if(exp.peek().t==Token.NUM&&exp.peek().value==""+index){
							break;
						}*/
						index1 --;
					}
					continue;
				}else if (varValue.get(variable.indexOf(var.value)).toString().equals(new Num(0,base).toString())) {
					if(exp.size()>index+4){
						Tokenizer t = exp.get(index+4);
						if (t.t == Token.NUM) {
							int index1= goToNoExp.get(goToNo.indexOf(Integer.parseInt(t.value)));
							//int index1 = goTo.get(Integer.parseInt(t.value));
							it = evaluate.iterator();
							while (it.hasNext() && index1 != 0) {
								it.next();
								index1--;
							}
						}
					}
				}
			}else if(exp.get(index+1) .t==Token.EQ){
				LinkedList<Tokenizer> rightExp = new LinkedList<Tokenizer>();
				rightExp.addAll(exp);
				int temp=index+2;
				while(temp!=0){
					rightExp.remove();
					temp--;
				}
				
				Num answer = shuntYardCall(base, rightExp, variable,varValue);
				varValue.set(variable.indexOf(var.value),answer);
				//variable.put(var.value, answer);
				/*if(rightExp.size()==1){
					//System.out.println("Var: "+var.value+" Value: "+answer);
					System.out.println(answer);
				}*/
			}
		}
	}
}
