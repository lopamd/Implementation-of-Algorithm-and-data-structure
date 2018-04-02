package cs6301.g45.q9;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		//Creation of first sparse polynomial
		Scanner sc = new Scanner(System.in);
		SparsePolynomials sp=new SparsePolynomials();
		System.out.println("Enter the number of polynomial terms in first sparse polynomial");
		int size1 = sc.nextInt();
		System.out.println("Enter the values and exponents now");
		for(int i=0;i<size1;i++){
			sp.createPolynomial(sc.nextDouble(), sc.nextDouble());
		}
		
		System.out.println("First Polynomial list");
		sp.print();
		System.out.println();
		SparsePolynomials sp2=new SparsePolynomials();
		System.out.println("Enter the number of polynomial terms in second sparse polynomial");
		int size2 = sc.nextInt();
		System.out.println("Enter the values and exponents now");
		for(int i=0;i<size2;i++){
			sp2.createPolynomial(sc.nextDouble(), sc.nextDouble());
		}
		System.out.println("Second Polynomial list");
		sp2.print();
		System.out.println();
		System.out.println("Addition of the 2 sparse polynomials");
		SparsePolynomials result=sp.add(sp2);
		result.print();
		System.out.println("Multiplication of the 2 sparse polynomials");
		result=sp.multiply(sp2);
		result.print();
		System.out.println("Enter a value to evaluate the first polynomial :");
		System.out.println("Answer "+sp.evaluate(sc.nextDouble()));
		
		System.out.println("Enter a value to evaluate the second polynomial :");
		System.out.println("Answer "+sp2.evaluate(sc.nextDouble()));
	}
}
