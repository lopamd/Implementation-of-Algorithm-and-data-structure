package cs6301.g45;

/**
 * A helper class to aid the expression evaluation
 * @author Lopamudra 
 */

public class OperatorPrecedence{
	public enum Operators {
		ADD,SUB,MUL,DIV,MOD,SQA,POW,OPB,CLB
	}
	// fucntion to return the precedence levels of the operator passed
	public int getPrecedence(Operators op){
		switch (op){
		case ADD:
			return 1;
		case SUB:
			return 1;
		case MUL:
			return 2;
		case DIV:
			return 2;
		case MOD:
			return 2;
		case POW:
			return 3;
		case SQA:
			return 4;
		case OPB:
			return 5;
		case CLB:
			return 5;
		}
		return 0;
	}
	// function to give the string format of hte operator passed
	public String getOpStr(Operators op){
		switch (op){
		case ADD:
			return "+";
		case SUB:
			return "-";
		case MUL:
			return "*";
		case DIV:
			return "/";
		case MOD:
			return "%";
		case POW:
			return "^";
		case SQA:
			return "|";
		case OPB:
			return "(";
		case CLB:
			return ")";
		}
		return null;
	}
	// function to get the Operator version of the string format passed
	public Operators getOpOP(String op){
		switch (op){
		case "+":
			return Operators.ADD;
		case "-":
			return Operators.SUB;
		case "*":
			return Operators.MUL;
		case "/":
			return Operators.DIV;
		case "%":
			return Operators.MOD;
		case "^":
			return Operators.POW;
		case "|":
			return Operators.SQA;
		case "(":
			return Operators.OPB;
		case ")":
			return Operators.CLB;
		}
		return null;
	}
	
	public boolean getAssociativity(Operators op){
		switch (op){
		case ADD:
			return true;
		case SUB:
			return true;
		case MUL:
			return true;
		case DIV:
			return true;
		case MOD:
			return true;
		case POW:
			return false;
		case SQA:
			return false;
		case OPB:
			return false;
		case CLB:
			return false;
		}
		return false;
	}
}
