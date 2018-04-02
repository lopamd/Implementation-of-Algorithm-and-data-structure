/**
 * Sparse Polynomial class that make use of Polynomial class that implement add, multiply and evaluate
 * @author Lopamudra 
 */
package cs6301.g45.q9;

import java.util.Iterator;
import java.util.LinkedList;
/*Polynomial class that has value and exponent fields*/
class Polynomial {
	double value;
	double exponent;
	/*
	 * Constructor that takes value and exponent values and set the polynomial term
	*/
	public Polynomial(double value, double exponent) {
		this.value = value;
		this.exponent = exponent;
	}
}
/*
 * Sparse Polynomial class that makes use of Polynomial class as a LinkedList of Polynomials
 * along with the functionalities of add, multiply and evaluate a polynomial.
*/
public class SparsePolynomials {
	LinkedList<Polynomial> sp;
	/*
	 * Constructor initializing the LinkedList of Polynomials
	*/
	public SparsePolynomials() {
		sp = new LinkedList<Polynomial>();
	}
	/*
	 * adding a polynomial to the existing sparse polynomial.
	 * Example:
	 * Polynomial - 4x^3
	 * Sparse Polynomial - 2x+3x^4
	 * Sparse Polynomial output once we execute this function - 2x+4x^3+3x^4
	 * Adding the polynomials by making use of insertion sort.
	 * @param value: value of the polynomial
	 * @param exponent: exponent of the polynomial.
	*/
	public void createPolynomial(double value, double exponent) {
		LinkedList<Polynomial> spTemp = (LinkedList<Polynomial>) sp.clone();
		Iterator<Polynomial> it = spTemp.iterator();
		int i = 0;
		boolean set = false;
		while (it.hasNext()) {
			Polynomial p = it.next();
			//System.out.println(this.sp.get(i).value + " "+i);
			if (p.exponent > exponent) {
				sp.add(i, new Polynomial(value, exponent));
				set = true;
				break;
			}else if(p.exponent==exponent){
				//System.out.println("Entered "+(p.value+value)+" "+i+" "+sp.get(i).value);
				sp.remove(i);
				//print();
				double sum=(p.value+value);
				sp.add(i, new Polynomial(sum,exponent));
				//System.out.println("Entered "+(p.value+value)+" "+i+" "+sp.get(i).value);
				set=true;
			}
			i++;
		}
		if (set == false)
			sp.add(new Polynomial(value, exponent));
		
	}
	/*
	 * Display of the sparse polynomial function
	*/
	public void print() {
		Iterator<Polynomial> it = sp.iterator();
		while (it.hasNext()) {
			Polynomial p = it.next();
			System.out.println(p.value + " " + p.exponent);
		}
	}
	/*
	 * Adding multiple sparse polynomials to the current sparse polynomial which makes use of createPolynomial funciton
	 * @param polynomials: multiple sparse polynomial objects
	 * can be passed and added to the current sparse polynomial and result is returned
	*/
	public SparsePolynomials add(SparsePolynomials... polynomials) {
		SparsePolynomials result = new SparsePolynomials();
		for (SparsePolynomials spTemp : polynomials) {
			Iterator<Polynomial> a = this.sp.iterator();
			Iterator<Polynomial> b = spTemp.sp.iterator();
			if (!(a.hasNext() || b.hasNext())) {
				return result;
			}
			Polynomial x = a.next();
			Polynomial y = b.next();
			while (x!=null&&y!=null) {
				if (x.exponent == y.exponent) {
					result.createPolynomial(x.value + y.value, x.exponent);
					x = a.hasNext()?a.next():null;
					y = b.hasNext()?b.next():null;
				} else if (x.exponent > y.exponent) {
					result.createPolynomial(y.value, y.exponent);
					y = b.hasNext()?b.next():null;
				} else {
					result.createPolynomial(x.value, x.exponent);
					x = a.hasNext()?a.next():null;
				}
			}
			while(x!=null){
				result.createPolynomial(x.value, x.exponent);
				x = a.hasNext()?a.next():null;
			}
			while(y!=null){
				result.createPolynomial(y.value, y.exponent);
				y = b.hasNext()?b.next():null;
			}
		}
		return result;
	}
	/*
	 * Multiplication of 2 sparse polynomials.
	 * Multiplication is done by multiplying each polynomial term of the first sparse polynomial with the sum of each polynomial term of the second sparse polynomial
	 * @param sp: sparse polynomial that needs to be multiplied with
	*/
	public SparsePolynomials multiply(SparsePolynomials sp){
		SparsePolynomials result=new SparsePolynomials();
		LinkedList<Polynomial> poly=(LinkedList<Polynomial>) sp.sp.clone();
		for(int i=0;i<poly.size();i++){
			Polynomial p1=poly.get(i);
			for(int j=0;j<this.sp.size();j++){
				Polynomial p2=this.sp.get(j);
				result.createPolynomial(p1.value*p2.value,p1.exponent+p2.exponent);
			}
		}
		return result;
	}
	/*
	 * Evaluating the sparse polynomial with the provided value.
	 * @param x: input value to evaluate the expression.
	*/
	public double evaluate(double x){
		double result=0;
		for(int i=0;i<sp.size();i++){
			Polynomial p=sp.get(i);
			result=p.value*Math.pow(x,p.exponent)+result;
		}
		return result;
	}
}
