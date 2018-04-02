package cs6301.g45;

import java.math.BigInteger;
import java.util.Scanner;

public class FibSeries {

	static BigInteger[][] product(BigInteger left[][], BigInteger right[][]) {
		BigInteger[][] result = { { BigInteger.ZERO, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ZERO } };
		// simple matrix multiplication
		result[0][0] = left[0][0].multiply(right[0][0]).add(left[0][1].multiply(right[1][0]));
		result[0][1] = left[0][0].multiply(right[0][1]).add(left[0][1].multiply(right[1][1]));
		result[1][0] = left[1][0].multiply(right[0][0]).add(left[1][1].multiply(right[1][0]));
		result[1][1] = left[1][0].multiply(right[0][1]).add(left[1][1].multiply(right[1][1]));
		return result;

	}

	static BigInteger logFib(int n) {
		BigInteger[][] left = { { BigInteger.ONE, BigInteger.ONE },
				{ BigInteger.ONE, BigInteger.ZERO } };
		// the constant matrix raised to power of the input num-1
		BigInteger[][] result = power(left, n - 1);
		return result[0][0];
	}
	
	static BigInteger[][] power(BigInteger a[][], int n) {
		if (n == 1)
			return a;
		return (n % 2 == 0) ? power(product(a, a), n / 2) : product(a, power(product(a, a), n / 2));
	}
	
	static BigInteger linearFib(int n) {
		BigInteger fib[] = new BigInteger[n + 1];
		fib[0] = BigInteger.ZERO;
		fib[1] = BigInteger.ONE;

		for (int i = 2; i <= n; i++) {
			// adds the previous two numbers in series
			fib[i] = fib[i - 1].add(fib[i - 2]); 
		}

		return fib[n];
	}


	public static void main(String[] args) {
		
		System.out.println("Enter the num to calculate fibonacci: ");
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		Timer linear = new Timer();
		Timer log = new Timer();

		linear.start();
		System.out.println(FibSeries.linearFib(input));
		System.out.println(linear.end());

		log.start();
		System.out.println(FibSeries.logFib(input));
		System.out.println(log.end());

	}

}
