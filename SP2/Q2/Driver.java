package CS6301.g45.SP2.Q2;

import java.util.Random;

/**
 * Driver program for running the merge sort on SortableList.
 * @author Lopamudra	
 */

public class Driver {
	
	/**
	 * Function to create unsorted linkedList having random element values
	 * @param length : length of unsorted Linked List
	 * @return : LinkedList of given length
	 */
	public static SortableList<Integer> createRandArray(int length){
		SortableList<Integer> linklist = new SortableList<Integer>(); //input LinkedList to be sorted
		Random rand = new Random(); //Generate random numbers used for input
		for(int i=0; i<length; i++) {
			linklist.add(rand.nextInt(length));
		}
		return linklist;
	}

	public static void main(String[] args) {
	   int length = 100; // Give the length of input LinkedList
	   SortableList<Integer> linklist = createRandArray(length); 
	   SortableList.mergeSort(linklist);
	}
}
