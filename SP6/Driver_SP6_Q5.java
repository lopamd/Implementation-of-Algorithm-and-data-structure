package cs6301.g45;

import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
* Driver program for Binary Heap and heap sort.
* @author 	Lopamudra
*/
public class Driver_SP6_Q5 {
	
	public static void main(String[] args) {
		int length = 1000;
		Integer arr[] = createRandArray(length);
		//Integer[] arr = {12, 11, 13, 5, 6, 19, 7, 16, 19}; // Change the input here
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1: for Descending order" + "\n" + "Enter 2: for Ascending order" );
		int heapType = Integer.parseInt(sc.next());
		switch (heapType) {
		case 1: // Descending Order
			Comparator<Integer> compMax = new Comparator<Integer>() {
				@Override
		        public int compare(Integer o1, Integer o2) {
					return o1-o2;
	        	}
			};
			System.out.println("Before sort : " );
			printArray(arr);
			BinaryHeap.heapSort(arr, compMax);
			System.out.println("After max Heap sort first 20 elements : " );
			printFirstTwenty(arr);
			break;
			
		case 2: // Ascending Order
			Comparator<Integer> compMin = new Comparator<Integer>() {
				@Override
		        public int compare(Integer o1, Integer o2) {
					return o2-o1;
	        	}
			};
			System.out.println("Before sort : " );
			printArray(arr);
			BinaryHeap.heapSort(arr, compMin);
			System.out.println("After min Heap sort first 20 elements : " );
			printFirstTwenty(arr);
			break;
		
		/*case 3: 
			Comparator<Integer> comp = new Comparator<Integer>() {
				@Override
		        public int compare(Integer o1, Integer o2) {
					return o2-o1;
	        	}
			};
			BinaryHeap<Integer> bp = new BinaryHeap<>(arr, comp, arr.length);
			bp.buildHeap();
			int temp = arr[0];
			if(comp.compare(temp, bp.peek()) > 0)
				bp.replace(temp);
			for(int k = 0; k < 3; k++)
				System.out.println(arr[k]);
			break;*/

		default:
			break;
		}
		sc.close();
		
	}
	
	/* A utility function to print array of size n */
    /**
     * @param arr : input array
     */
    static void printArray(Integer arr[])
    {
        for(Integer i: arr)
            System.out.print(i +" ");
        System.out.println();
    }
    
    /**
	 * Method to create random array of given length
	 * @param length : int : length of input randrom array
	 * @return : int[] array
	 */
	public static Integer[] createRandArray(int length){
		Integer[] arr = new Integer[length]; //input array to be sorted
		Random rand = new Random(); //Generate random numbers used for input array
		for(int i=0; i< length; i++) {
		   arr[i] = rand.nextInt(length); // use for random number
		   //arr[i] = length - 1 - i; // use for creating descending input array
		}
		return arr;
	}
	
	/**
	 * Method to print the array
	 * @param arr input array
	 */
	static void printFirstTwenty(Integer[] arr) {
        int n = Math.min(arr.length, 20);
        for(int i = 0; i< n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
     }	
}
