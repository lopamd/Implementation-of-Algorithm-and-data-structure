package cs6301.g45;

/**
 * A helper class to enable expression parsing using tokens
 * @author Lopamudra 
 */
 
public class Tokenizer {
	
	public Token t;
	public String value;
	
	/**
	 * @return the t
	 */
	public Token getT() {
		return t;
	}
	/**
	 * @param t the t to set
	 */
	public void setT(Token t) {
		this.t = t;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public enum Token {
		VAR, NUM, OP, EQ, OPEN, CLOSE, EOL, QS, COL
	}
	public static Token tokenize(String s) throws Exception {
		if (s.matches("\\d+")) { // one or more digits
			return Token.NUM;
		} else if (s.matches("[a-z]")) { // letter
			int index = s.charAt(0) - 'a'; // Convert var to index: a-z maps to
											// 0-25
			return Token.VAR;
		} else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%") || s.equals("^")
				|| s.equals("|")) {
			return Token.OP;
		} else if (s.equals("=")) {
			return Token.EQ;
		} else if (s.equals("(")) {
			return Token.OPEN;
		} else if (s.equals(")")) {
			return Token.CLOSE;
		} else if (s.equals(";")) {
			return Token.EOL;
		}else if(s.equals("?")){
			return Token.QS;
		}else if(s.equals(":")){
			return Token.COL;
		}else{
			throw new Exception("Unknown token: " + s);
		}
	}
}

