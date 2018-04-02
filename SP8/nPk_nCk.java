/**
* Implementation of Permutations and Combinations tree.

*/

package cs6301.g45;
import java.util.Scanner;

/**
 * @param <T> Class declaration for Permutation and Combination
 * A : T[] : Values of the array
 * n : int : length of the array
 * k : int : k value to find either nPk or nCk
 * pCount: int : count of permutations
 * cCount: int : count of combinations
 */
public class nPk_nCk<T> {
	T[] A;
	int n,k;
	long pCount;
	long cCount;
	//Constructors
	public nPk_nCk(){
		pCount=0;
		cCount=0;
	}
	public nPk_nCk(T[] A,int n, int k){
		this.A=A;
		this.n=n;
		this.k=k;
		pCount=0;
		cCount=0;
	}
	/**
	 * Permutation function 
	 * @param c: int
	 * VERBOSE : int : 1 - to print the permutations visited, 0 - will not print the permutations visited
	 */
	public void permute(int c, int VERBOSE){
		if(c==0){
			if(VERBOSE==1){
				for(int i=0;i<k;i++){
					System.out.print(A[i]);
				}
				System.out.println();
			}
			pCount++;
		}
		else{
			int d=k-c;
			permute(c-1,VERBOSE);
			for(int i=d+1;i<n;i++){
				T temp=A[d];
				A[d]=A[i];
				A[i]=temp;
				permute(c-1,VERBOSE);
				A[i]=A[d];
				A[d]=temp;
			}
		}
	}
	/**
	 * Permutation function 
	 * @param i: int
	 * c: int
	 * chosen : T[] : chosen combinations that needs to be considered
	 * VERBOSE : int : 1 - to print the combinations visited, 0 - will not print the combinations visited
	 */
	public void combination(int i, int c,T[] chosen,int VERBOSE){
		if(c==0){
			if(VERBOSE==1){
				for(int index=0;index<k;index++){
					System.out.print(chosen[index]);
				}
				System.out.println();
			}
			cCount++;
		}else{
			chosen[k-c]=A[i];
			combination(i+1,c-1,chosen,VERBOSE);
			if(n-i>c){
				combination(i+1,c,chosen,VERBOSE);
			}
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n,k,VERBOSE;
		String A[],chosen[];
		System.out.println("Enter n value: ");
		n=sc.nextInt();
		System.out.println("Enter values of the array A: ");
		A= new String[n];
		for(int i=0;i<n;i++){
			A[i]=sc.next();
		}
		System.out.println("Enter value of k for permutation and combination: ");
		k=sc.nextInt();
		chosen= new String[k];
		System.out.println("Do you want to print the paths? (Enter 1 for YES/0 for NO)");
		VERBOSE=sc.nextInt();
		//Initiating class object with A,n and k values
		nPk_nCk<String> test=new nPk_nCk<String>(A,n,k);
		System.out.println("Visiting permutations for "+n+"P"+k);
		//Intial Permutation Call
		test.permute(k,VERBOSE);
		//To print the number of ways we can traverse
		System.out.println("Count of permutations : "+test.pCount);
		System.out.println("Visiting combinations for "+n+"C"+k);
		//Initial Combination call
		test.combination(0,k,chosen,VERBOSE);
		//To print the number of ways we can traverse
		System.out.println("Count of combinations : "+test.cCount);
	}
}


/*
 * Sample example output
 * 
    Enter n value: 
	3
	Enter values of the array A: 
	a b c
	Enter value of k for permutation and combination: 
	2
	Do you want to print the paths? (Enter 1 for YES/0 for NO)
	1
	Visiting permutations for 3P2
	ab
	ac
	ba
	bc
	cb
	ca
	Count of permutations : 6
	Visiting combinations for 3C2
	ab
	ac
	bc
	Count of combinations : 3
*/
