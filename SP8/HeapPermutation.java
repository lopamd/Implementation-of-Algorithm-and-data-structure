/**
* Implementation of Permutations and Combinations tree.

*/

package cs6301.g45;

public class HeapPermutation {
	/*
	 * Function to generate permutations using heaps algorithm
	 * @param 
	 * obj : T[] : Input set
	 */
	public static<T> void findHeapsPerm( T[] obj) {
		int[] c = new int[obj.length];
		for (int i =0;i<obj.length;i++){
			c[i]=0;
		}
		printNum(obj);// output initial set as it is
		int i = 0;
		while (i<obj.length){
			if (c[i]<i){
				if(i%2==0){
					T temp = obj[i];
					obj[i] = obj[0];
					obj[0] = temp;
				}else{
					T temp = obj[c[i]];
					obj[c[i]] = obj[i];
					obj[i] = temp;
				}
				printNum(obj);// output intermediate permutations
				c[i]++;
				i =0;
					
			}else{
				c[i]=0;
				i++;
			}
		}
	}
	
	/*
	 * For printing the permutation
	 * @param 
	 * arr : T[] : input an array to print
	 */
	static <T> void printNum(T[] arr){
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] arr = {"man","dog","cat","girl"};
		findHeapsPerm(arr);
	}

}

/*
 * Sample output:
 man dog cat girl 
dog man cat girl 
cat man dog girl 
man cat dog girl 
dog cat man girl 
cat dog man girl 
girl dog man cat 
dog girl man cat 
man girl dog cat 
girl man dog cat 
dog man girl cat 
man dog girl cat 
man cat girl dog 
cat man girl dog 
girl man cat dog 
man girl cat dog 
cat girl man dog 
girl cat man dog 
girl cat dog man 
cat girl dog man 
dog girl cat man 
girl dog cat man 
cat dog girl man 
dog cat girl man 

 */
