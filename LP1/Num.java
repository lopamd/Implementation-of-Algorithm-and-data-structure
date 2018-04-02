package cs6301.g45;

/**
 * Java program to perform level 1 and level 2 operations
 * @author Lopamudra 
 */

import java.util.LinkedList;
import java.util.Iterator;

public class Num implements Comparable<Num> {

	static long defaultBase = 10; // This can be changed to what you want it to be.
	long base = 10; // Change as needed
	boolean sign; // true - negative, false - positive.
	LinkedList<Long> number;

	public long getBase() {
		return base;
	}

	public void setBase(long base) {
		this.base = base;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}
	public LinkedList<Long> getNumber() {
		return number;
	}

	public void setNumber(LinkedList<Long> number) {
		this.number = number;
	}

	Num() {
		number = new LinkedList<Long>();
	}

	Num(LinkedList<Long> num) {
		number = num;
	}
	
	Num(LinkedList<Long> num, long b) {
		number = num;
		this.base = b;
	}
	
	/* Start of Level 1 */
	Num(String s) {
		if (s.contains("-")) {
			this.sign = true;
			s = s.replaceAll("-", "");
		}
		int size = s.length();
		Num result = new Num();
		for(int i = 0; i < size ; i++){
			//System.out.println("result = "+ result.getNumber());
			Num product1 = product(result, new Num(10L));
			Num add1 = new Num(Long.parseLong(s.substring(i, i + 1)));
			result = add(product1 , add1);
		}
		this.number = result.getNumber();
		//System.out.println("after conversion from base 10 to req base :"+ number);
	}
	Num(String s, long base){
		this.base=base;
		if (s.contains("-")) {
			this.sign = true;
			s = s.replaceAll("-", "");
		}
		int size = s.length();
		Num result = new Num();
		for(int i = 0; i < size ; i++){
			//System.out.println("result = "+ result.getNumber());
			Num product1 = product(result, new Num(10L));
			Num add1 = new Num(Long.parseLong(s.substring(i, i + 1)));
			result = add(product1 , add1);
		}
		this.number = result.getNumber();
	}
	Num(long x, int b){
		this.base = b;
		if (x < 0) {
			sign = true;
			x = x * -1;
		}
		number = new LinkedList<Long>();
		if(x == 0){
			number.add(x);
			return;
		}
		while (x != 0) {
			number.add(x % b);
			x = x / b;
		}
	}


	
	Num(long x) {
		if (x < 0) {
			sign = true;
			x = x * -1;
		}
		number = new LinkedList<Long>();
		if(x == 0){
			number.add(x);
			return;
		}
		while (x != 0) {
			number.add(x % base);
			x = x / base;
		}
	}

	/**
	 * Construction for creating Num of given base
	 * @param x : Num
	 * @param b : long base
	 */
	Num(long x, long b) {
		base = b;
		number = new LinkedList<Long>();
		if (x < 0) {
			sign = true;
			x = x * -1;
		} else if(x == 0) {
			number.add(x);
		}
		
		while (x != 0) {
			number.add(x % base);
			x = x / base;
		}
	}
	
	Num(int b10) {
		number = new LinkedList<Long>();
		this.base = b10;
	}
	

	/**
	 * Method to implement add function
	 * @param a : Num
	 * @param b : Num
	 * @return Num : result of add function
	 */
	static Num add(Num a, Num b) {
		if ((a.sign == true && b.sign == true) || (a.sign == false && b.sign == false)) {
			Iterator<Long> itNumA = a.getNumber().iterator();
			Iterator<Long> itNumB = b.getNumber().iterator();
			Num result = new Num((int)a.base);
			long statBase = a.getBase();
			long carry = 0;
			long sum = 0;
			// addition continues till both the numbers are traversed completely
			// or the carry is 0
			while (itNumA.hasNext() || itNumB.hasNext() || carry > 0) {
				sum = failSafeNext(itNumA) + failSafeNext(itNumB) + carry;
				carry = sum / statBase;
				sum = sum % statBase;
				result.getNumber().add(sum);
			}
			result.sign = a.sign;
			return result;
		} else {
			if (a.sign) {
				Num num = new Num(a.getNumber(), a.base);
				num.sign = false;
				return subtract(b, num);
			} else {
				Num num = new Num(b.getNumber(), b.base);
				num.sign = false;
				return subtract(a, num);
			}
		}
	}

	static long failSafeNext(Iterator it) {
		if (!it.hasNext()) {
			return 0;
		} else
			return (long) it.next();
	}

	/**
	 * Method to implement subtract function 
	 * @param a : Num 
	 * @param b : Num
	 * @return Num : result of subtraction
	 */
	static Num subtract(Num a, Num b) {

		if ((a.sign == true && b.sign == true) || (a.sign == false && b.sign == false)) {
			Iterator<Long> itNumA;
			Iterator<Long> itNumB;
			Num result = new Num((int)a.base);
			//if (Long.parseLong(b.toString()) > Long.parseLong(a.toString()))
			if(b.compareTo(a) == 1){ //have to change.. if num is too big, long can't parse
				itNumA = b.getNumber().iterator();
				itNumB = a.getNumber().iterator();
				result.sign = true;
			} else {
				itNumA = a.getNumber().iterator();
				itNumB = b.getNumber().iterator();
				result.sign = false;
			}

			long statBase = a.getBase();
			long borrow = 0;

			while (itNumA.hasNext() || itNumB.hasNext() || borrow > 0) {
				long num1 = failSafeNext(itNumA);
				long num2 = failSafeNext(itNumB);
				/*if (borrow > 0 && (num1 == 0 && num2 == 0)) {
					num1--;//result.getNumber().add((long) -1);
				} else */
				if (borrow > 0) {
					num1--;
				}
				if (num1 >= num2) {
					result.getNumber().add(num1 - num2);
					borrow = 0;
				} else {
					result.getNumber().add(num1 + statBase - num2);
					borrow = 1;
				}
			}
			result = removeTrailingZero(result);
			return result;
		} else {
			Num num = null;
			num = new Num(b.getNumber(), b.base);
			num.sign = !b.sign;
			return add(a, num);
		}
	}

	public static Num removeTrailingZero(Num a) {
		Iterator<Long> revIt = a.getNumber().descendingIterator();
		boolean trailingZero = true;
		while (revIt.hasNext()) {
			String temp = revIt.next().toString();
			if (!(temp.equals("0") && trailingZero)) {
				trailingZero = false;
			} else {
				revIt.remove();
			}
		}
		if (trailingZero == true) {
			a.getNumber().add((long) 0);
		}
		return a;
	}

	public static LinkedList<Long> convertToList(long x, long base, LinkedList<Long> number) {
		while (x != 0) {
			number.add(x % base);
			x = x / base;
		}
		return number;
	}

	
	/**
	 * Method to implement product of two Nums
	 * @param a : Num
	 * @param b : Num
	 * @return Num : result from helper function product_(Num a, Num b)
	 */
	static Num product(Num a, Num b){
		//System.out.println("Inside product . base of a :"+ a.base);
		boolean sign = false;
		if(a.sign != b.sign)
			sign = true;
		Num result = product_(a, b);
		removeTrailingZero(result).setSign(sign);
		//result.;
		
		return result;
	}
	
	// Implement Karatsuba algorithm for excellence credit
	/**
	 * Helper function of product
	 * @param a Num
	 * @param b Num
	 * @return Num : product of a and b.
	 */
	static Num product_(Num a, Num b) { // base conditions not implemented yet
		int shiftSize = 0;
		int aSize = a.getNumber().size();
		int bSize = b.getNumber().size();
		if((aSize == 1 && a.getNumber().getFirst().equals(0)) || 
				(bSize == 1 && b.getNumber().getFirst().equals(0)) ||
				aSize == 0 || bSize == 0)
			return new Num((int)a.base);
		if (aSize >= bSize) {
			shiftSize = bSize/2;
		} else {
			shiftSize = aSize/2;
		}

		LinkedList<Long> aHalfLsd = new LinkedList<Long>();
		LinkedList<Long> bHalfLsd = new LinkedList<Long>();

		LinkedList<Long> aHalfMsd = new LinkedList<Long>();
		LinkedList<Long> bHalfMsd = new LinkedList<Long>();

		Iterator<Long> itA = a.getNumber().iterator();
		Iterator<Long> itB = b.getNumber().iterator();

		Num prod1 = null,prod2 = null,prod3 = null;
		LinkedList<Long> resultList= new LinkedList<Long>();
		long carry=0;
		if(shiftSize < 1){
			//direct multiplication
			if(aSize > bSize){
				while(itA.hasNext() || carry!=0){
					long prod=0;
					if(itA.hasNext())
						prod=itA.next() * b.getNumber().peekFirst();
					long temp=(prod+carry)%b.base;
					resultList.add(temp);
					carry=(prod+carry)/b.base;
				}
				return new Num(resultList, b.base);
			}else{
				while(itB.hasNext() || carry!=0){
					long prod = 0;
					if(itB.hasNext())
						prod = itB.next() * a.getNumber().peekFirst();
					long temp = (prod+carry) % a.base;
					resultList.add(temp);
					carry=(prod + carry) / a.base;
				}
				return new Num(resultList, a.base);
			}
		}else{
			//recursive calls
			int i = 1;
			while (itA.hasNext()) {
				if (i <= shiftSize)
					aHalfLsd.add(itA.next());
				else
					aHalfMsd.add(itA.next());
				i++;
			}

			i = 1;
			while (itB.hasNext()) {
				if (i <= shiftSize)
					bHalfLsd.add(itB.next());
				else
					bHalfMsd.add(itB.next());
				i++;
			}
			prod1=product(new Num(aHalfMsd, a.base),new Num(bHalfMsd, a.base));
			prod3 = product(new Num(aHalfLsd, a.base), new Num(bHalfLsd, a.base));
			Num aSum=add(new Num(aHalfMsd, a.base), new Num(aHalfLsd, a.base));
			Num bSum=add(new Num(bHalfMsd, a.base), new Num(bHalfLsd, a.base));
			prod2=subtract(product(aSum,bSum),add(prod1,prod3));
			
			prod1=shiftOp(prod1, 2*shiftSize);
			prod2=shiftOp(prod2, shiftSize);
			
			return add(add(prod1, prod2), prod3);
		}
	}
	public long listVals(){
		Iterator<Long> revIt = this.number.descendingIterator();
		Long number = 0L;
		double counter = 0;
		while (revIt.hasNext()) {
			number = (long) (number * 10 + revIt.next());
		}
		return number;
	}
	public static Num shiftOp(Num num, long d) {
		
		for (int i = 1; i <= d; i++) {
			num.getNumber().addFirst((long) 0);
		}
		return num;
	}

	// Use divide and conquer
	/**
	 * Method to implement power of a and n
	 * @param a : Num
	 * @param n : long
	 * @return Num : is the result of power of a and n
	 */
	static Num power(Num a, long n) {
		//System.out.println("Inside power . base of a :"+ a.base);
		if(n == 0)
			return new Num((long)1 , a.base);
		if(n == 1)
			return a;
		return (n % 2 == 0)? power(product(a,a), n/2) : product(a, power(product(a,a), n/2));
		
	}
	/* End of Level 1 */

	/* Start of Level 2 */
	/**
	 * Method to implement divide of 2 num
	 * @param a : Num
	 * @param b : Num
	 * @return Num : quotient of a and b
	 */
	static Num divide(Num a, Num b) {
		
		Num dividend = new Num(a.getNumber(), a.base);
		Num divisor = new Num(b.getNumber(), b.base);
		Num quotient = new Num(0 , a.base);
		Num zero = new Num(0, a.base);

		boolean aZero = false;
		boolean bZero = false;
		
		if(a.compareTo(zero) == 0) {
			aZero = true;
		}
		
		if(b.compareTo(zero) == 0) {
			bZero = true;
		}
		
		try {
			ArithmeticException ae = new ArithmeticException("Divide by 0 is not allowed!");
			if(a.compareTo(b) == -1 && !a.sign && !aZero) {
				quotient = new Num(0, a.base);
			}
			else if(!aZero && !bZero && a.compareTo(b) == 0) {
				quotient = new Num(1, a.base);
			}
			else if (aZero) {
				if(!bZero) {
					quotient = new Num(0, a.base);
				} else {
					quotient = null;
					throw ae;
				}
			}
			else if(bZero) {
				quotient = null;
				throw ae;
			}
			else if(b.compareTo(new Num(1, b.base)) == 0 && a.compareTo(b)!= 0) {
				quotient = a;
			}
			else {
				
				Num low = divisor;
				Num high = dividend;
				
				while((high.compareTo(low) == 1) || (high.compareTo(low) == 0)) {
					Num temp = low;
					Num multiple = new Num(1, a.base);
					
					while((high.compareTo(product(temp,new Num(2, a.base))) == 1) || high.compareTo(product(temp,new Num(2, a.base))) == 0)	{
						temp = product(temp,new Num(2, a.base));
						multiple = product(multiple,new Num(2, a.base));
					}
					high = subtract(high, temp);
					quotient = add(quotient,multiple);
				}
				
				if((!b.sign && a.sign) || (b.sign && !a.sign)) {
					quotient = subtract(new Num(0, a.base), quotient);
				} 
			}
		} catch(ArithmeticException ex) {
			System.out.println("Divide Exception: "+ex);
		}
		return quotient;
	}

	/**
	 * Method to implement mod
	 * @param a : Num
	 * @param b : Num
	 * @return return Num which is mod of a and b.
	 */
	static Num mod(Num a, Num b) {

		Num quotient, mul, remainder = new Num(0, a.base);
		Num zero = new Num(0, a.base);

		boolean aZero = false;
		boolean bZero = false;
		
		try {
			IllegalArgumentException iae = new IllegalArgumentException("Mod is allowed only if a is nonnegative and b > 0");
			if(a.compareTo(zero) == 0) {
				aZero = true;
			}
			
			if(b.compareTo(zero) == 0) {
				bZero = true;
			}
			if(aZero) {
				if(!bZero) {
					remainder = new Num(0, a.base);
				} 
				else {
					remainder = null;
					throw iae;
				}
			}
			else if(a.compareTo(b) == 0) {
				remainder = new Num(0, a.base);
			}
			else if(!bZero  && b.compareTo(new Num(1)) == 0 && !b.sign) {
				remainder = new Num(0, a.base);
			}
			else if(!a.sign && a.compareTo(b) == -1) {
				remainder = a;
			} 
			else if(b.compareTo(new Num(0)) == -1) {
				remainder = null;
				throw iae;
			}
			else if(a.sign) {
				remainder = null;
				throw iae;
			}
			else {
				if(bZero) { 
					remainder = null;
					throw iae;
				} else {
					quotient = divide(a, b);
					mul = product(quotient, b);
					remainder = subtract(a, mul);
				}
			} 
		} 
		catch(IllegalArgumentException ex) {
			System.out.println("Mod Exception: "+ex);
		}
		return remainder;
	}

	// Use divide and conquer
	/**
	 * Method to implement power(Num n) of a number(Num a)
	 * @param a Num
	 * @param n Num
	 * @return Num : power of given input
	 */
	static Num power(Num a, Num n) {
		Num result;
		if(n.getNumber().size() == 1){
			result = power(a, n.getNumber().get(0));
			return result;
		}else{
			long a0 = n.getNumber().get(0);
			n.getNumber().removeFirst();
			Num s0 = power(a, n);
			result = product(power(s0 , a.getBase()), power(a, a0));
		}
		return result;
	}

	/**
	 * @param a : Num input squareroot num
	 * @return Num : square root of a
	 */
	static Num squareRoot(Num a) {
		try{
			if(a.sign)
				throw new IllegalArgumentException("Square root of negative number not allowed.");
		}
		catch(IllegalArgumentException e) {
			System.out.println("Exception: "+e);
		}
		int size = a.getNumber().size();
		if(size == 1 && (a.compareTo(new Num(0,a.base)) == 0 || a.compareTo(new Num(1,a.base)) == 0))
			return a;
		Num low = new Num(0L, a.base);
		Num high = divide(a, new Num(2, a.base));
		while(low.compareTo(high) == -1){
			Num mid = divide(add(low, high), new Num(2L, a.base));
			Num sMid = product(mid, mid);
			if(sMid.compareTo(a) == 0)
				return mid;
			else if(sMid.compareTo(a) == -1)
				low = add(mid,new Num(1L, a.base));
			else
				high = subtract(mid, new Num(1L, a.base));
		}
		return low;
	}
	/* End of Level 2 */

	// Utility functions
	// compare "this" to "other": return +1 if this is greater, 0 if equal, -1
	// otherwise
	public int compareTo(Num other) {
		int aSize = this.getNumber().size();
		int bSize = other.getNumber().size();
		
		boolean aSign = this.sign;
		boolean bSign = other.sign;
		
		if(aSign != bSign)
			return (aSign == true)? -1 : 1;
		else if(aSize > bSize)
				return 1;
		else if(aSize < bSize)
				return -1;
		else {
			while(aSize != 0){
				if(this.getNumber().get(aSize - 1) > other.getNumber().get(aSize - 1))
					return 1;
				else if(this.getNumber().get(aSize - 1) < other.getNumber().get(aSize - 1))
					return -1;
				else {
					aSize--;
				}
			}
			return 0;
		}
	}

	// Output using the format "base: elements of list ..."
	// For example, if base=100, and the number stored corresponds to 10965,
	// then the output is "100: 65 9 1"
	void printList() {
		System.out.print(this.base + ":");
		Iterator<Long> revIt = this.number.iterator();
		Long number = 0L;
		double counter = 0;
		while (revIt.hasNext()) {
			System.out.print(" " + revIt.next());
		}
		System.out.println();
	}

	// Return number to a string in base 10
	@Override
	public String toString() {
		long count = 0;
		Num basePow = new Num(10);
		Num result = new Num(10);
		Iterator<Long> revIt = this.number.iterator();
		StringBuilder sb = new StringBuilder();
		if (sign){
			sb.append("-");
		}
		while (revIt.hasNext()) {
			basePow = power(new Num(base , 10), count++);
			Num product1 = product(new Num(revIt.next(), 10), basePow);
			result = add(result, product1 ); 
		}
		Iterator<Long> itr = result.getNumber().descendingIterator();
		while(itr.hasNext())
			sb.append(itr.next());
		return sb.toString();
	}
	
	public long base() {
		return base;
	}
}
