package cs6301.g45;

/**
 * A driver program to run level 1 and level 2 operations
 * @author Lopamudra 
 */

public class LP1L1_driver {
 public static void main(String[] args) {
		Num x = new Num("100000");
		Num y = new Num("0");
		System.out.println("x: "+x);
		System.out.println("y: "+y);
			
		Num z = Num.add(x, y);
		System.out.println("Addition: "+ z);
		
		Num z1 = Num.subtract(x, y);
		System.out.println("Subtract: "+ z1);
		
		Num z2 = Num.product(x, y);
		System.out.println("Product: "+ z2);
		
		Num a = Num.power(x, y);
		System.out.println("Level-1 Power: "+ a);
		
		Num s1 = Num.divide(x, y);
		if(s1 == null) {
			System.out.println("Take nonzero divisor");
		} else {
			System.out.println("Divide: " +s1 );
		}
		
		Num s2 = Num.mod(x, y);
		if(s2 == null) {
			System.out.println("Take a to be nonnegative and b > 0");
		} else {
			System.out.println("Mod: " + s2 );
		}
		
		Num s3 = Num.squareRoot(x);
		System.out.println("Square root : " +s3);
		
		Num p = Num.power(x, y);
		System.out.println("Level-2 Power : "+ p );

	}
}
