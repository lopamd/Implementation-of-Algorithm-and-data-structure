package cs6301.g45;

import java.util.Random;
import java.util.Scanner;


/**
 * Driver program to run the merge sort
 * @author Lopamudra 
 */

public class Driver_Q3 {
	
	Timer timer = new Timer(); //Timer used for running time of merge sort
	
	public int[] createRandArray(int length){
		int[] arr = new int[length]; //input array to be sorted
		
		Random rand = new Random(); //Generate random numbers used for input array
		for(int i=0; i< length; i++) {
		   arr[i] = rand.nextInt(length);
		   //arr[i] = length - 1 - i;
		}
		return arr;
	}
	
	
	/*For merge sort using int array*/
	public void mSort(int length, int sortType){
		int[] temp = new int[length]; //temporary array to be used during the merge operation.
		int[] arr = createRandArray(length);
		//int arr[] = {2, 4,1, 5, 3, Integer.MAX_VALUE}; 
		System.out.println("");
		
		//CASE- 1
		switch (sortType) {
		case 1:
			System.out.print("Case 1 : First 30 elements in the Input unsorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			timer.start();
		    MergeSUsingIntArray.mergeSort(arr, 0, length - 1);
			System.out.println(timer.end());
			System.out.print("Case 1 : First 30 elements in the Output sorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			System.out.println("");
			break;
		case 2:
			System.out.print("Case 2: First 30 elements in the Input unsorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			timer.start();
		    MergeSUsingIntArray.mergeSort(arr,temp,0,length - 1);
		    System.out.println(timer.end());
		    System.out.print("Case 2: First 30 elements in the Output sorted array : ");
		    MergeSUsingIntArray.printFirstTen(arr);
		    System.out.println("");
		    break;
		case 3:
			System.out.print("Case 3: First 30 elements in the Input unsorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			timer.start();
			MergeSUsingIntArray.mergeSort(arr, temp);
			System.out.println(timer.end());
			System.out.print("Case 3: First 30 elements in the Output sorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			break;
		case 4:
			System.out.print("Case 4: First 30 elements in the Input unsorted array : ");
			MergeSUsingIntArray.printFirstTen(arr);
			timer.start();
			int[] aux = new int[length];
			for(int i = 0; i < length; i++)
				aux[i] = arr[i];
			 MergeSUsingIntArray.mergeSort_case4(arr, aux, 0, length - 1);
			 System.out.println(timer.end());
			 System.out.print("Case 4: First 30 elements in the Output sorted array : ");
			 MergeSUsingIntArray.printFirstTen(arr);
			 break;
		default:
			break;
		}

	}
	
	public static void main(String[] args) {
		int length;
		Driver_Q3 d = new Driver_Q3();
		if (args.length == 2){
			length = Integer.parseInt(args[0]);
			System.out.println("Size of the input array:"+ length);
			System.out.println("----------------------------------");
			d.mSort(length, Integer.parseInt(args[1]));
			
		} else {
			System.out.println("Invalid Argument. java <program> <arraysize> <sorttype>");
		}
	}

}
