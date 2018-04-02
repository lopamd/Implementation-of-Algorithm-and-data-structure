package cs6301.g45;

/**
 * Implementation of Merge Sort algorithm using int array.
 * @author 	Lopamudra
 */

public class MergeSUsingIntArray {
	
	
	/**
	 * CASE-1 MergeSort
	 * @param arr: int[] - unsorted input array to be sorted.
	 * @param temp: int[] - temporary array to be used during the merge.
	 * @param leftIdx: int - left index of input array
	 * @param rightIdx: int - right index of input array
	 */

	public static void mergeSort(int[] arr, int leftIdx, int rightIdx){
		if(leftIdx < rightIdx){
			int midIdx = (leftIdx + rightIdx)/2;
			mergeSort(arr, leftIdx, midIdx);
			mergeSort(arr, midIdx + 1, rightIdx);
			merge(arr,leftIdx,midIdx,rightIdx);
		}
	}
	
	
	/**
	 * Merge function
	 * @param arr : int input array
	 * @param leftIdx : int left index of input array
	 * @param midIdx : int middle index of input array
	 * @param rightIdx : int right index of input array
	 */
	public static void merge(int[] arr, int leftIdx, int midIdx, int rightIdx) {
		
		int size_tempArray1 = midIdx - leftIdx + 1;
		int size_tempArray2 = rightIdx - midIdx;
		
		int[] tempArray1 = new int[size_tempArray1 + 1];
		int[] tempArray2 = new int[size_tempArray2 + 1];
		
		for(int i = 0; i < size_tempArray1; i++){
			tempArray1[i] = arr[leftIdx + i];
		}
		
		for(int j = 0; j < size_tempArray2; j++){
			tempArray2[j] = arr[midIdx + 1 + j];
		}
		
		//Assign sentinel to both temp array
		tempArray1[midIdx - leftIdx + 1] = Integer.MAX_VALUE;
		tempArray2[rightIdx - midIdx ] = Integer.MAX_VALUE;
		
		int i = 0, j = 0;
		for(int k = leftIdx; k <= rightIdx; k++){
			if(tempArray1[i] <= tempArray2[j]){
				arr[k] = tempArray1[i++];
			}else
				arr[k] = tempArray2[j++];
		}
			
		
	}
	
	/**
	 * Case -2 MergeSort
	 * @param arr: int[] - unsorted input array to be sorted.
	 * @param temp: int[] - temporary array to be used during the merge.
	 * @param leftIdx: int - left index of input array
	 * @param rightIdx: int - right index of input array
	 */

	public static void mergeSort(int[] arr, int[] temp, int leftIdx, int rightIdx){
		if(leftIdx < rightIdx){
			int midIdx = (leftIdx + rightIdx)/2;
			mergeSort(arr, temp, leftIdx, midIdx);
			mergeSort(arr, temp, midIdx + 1, rightIdx);
			merge(arr, temp, leftIdx,midIdx,rightIdx);
		}
	}
	
	/**
	 * CASE-2
	 * Method for merge operation.
	 * @param arr: int[] - input array to be merged.
	 * @param temp: int[] - temporary array to be used during the merge.
	 * @param leftIdx: int - left index of input array
	 * @param midIdx: int - middle index of input array
	 * @param rightIdx: int - right index of input array
	 */
		
	public static void merge(int[] arr, int[] temp, int leftIdx, int midIdx, int rightIdx) {
		for(int k = leftIdx; k <= rightIdx; k++)
			temp[k] = arr[k];
		int i = leftIdx, j = midIdx + 1;
		for(int k = leftIdx; k <= rightIdx; k++){
			if(j > rightIdx || (i <= midIdx && temp[i] <= temp[j])){
				arr[k] = temp[i++];
			} else
				arr[k] = temp[j++];
		}
	}
	
	
/**
 * Case 3 merge sort
 * @param arr
 * @param temp: int[] - temporary array to be used during the merge.
 */
	public static void mergeSort(int[] arr, int[] temp){
		mergeSort_case3(arr, temp,  0, arr.length -1);
	}
	public static void mergeSort_case3(int[] arr, int[] temp, int leftIdx, int rightIdx){
		if(rightIdx - leftIdx < 17) {
			InsertionSort.insertionsort(arr, leftIdx, rightIdx);
		} else {
			int midIdx = (leftIdx + rightIdx)/2;
			mergeSort_case3(arr, temp, leftIdx, midIdx);
			mergeSort_case3(arr, temp, midIdx + 1, rightIdx);
			merge(arr, temp, leftIdx, midIdx, rightIdx);
		}
		
	}
	
	/**
	 * Case 4 merge sort
	 * @param A : int[] - input array to be merged
	 * @param B : int[] - temporary array to be used during the merge.
	 * @param leftIdx: int - left index of input array
	 * @param rightIdx: int - right index of input array
	 */
	public static void mergeSort_case4(int[] A, int[] B, int leftIdx, int rightIdx){
		if(rightIdx - leftIdx < 15) { //Threshold
			InsertionSort.insertionsort(A, leftIdx, rightIdx);
		}else {
			int midIdx = (leftIdx + rightIdx)/2;
			mergeSort_case4(B, A, leftIdx, midIdx);
			mergeSort_case4(B, A, midIdx + 1, rightIdx);
			merge_case4(B, A, leftIdx, midIdx, rightIdx);
		}
	}
	

	/**
	 * @param A :int[] - input array to be merged
	 * @param B : int[] - temporary array to be used during the merge.
	 * @param leftIdx: int - left index of input array
	 * @param midIdx : int - middle index of input array
	 * @param rightIdx: int - right index of input array
	 */
	public static void merge_case4(int[] A, int[] B , int leftIdx, int midIdx, int rightIdx) {
		int i = leftIdx, j = midIdx + 1;
		for(int k = leftIdx; k <= rightIdx; k++){
			if(j > rightIdx || (i <= midIdx && A[i] <= A[j])){
				B[k] = A[i++];
			}else
				B[k] = A[j++];
		}
		
	}


	/**
	 * Method to print the array
	 * @param arr input array
	 */
	static void printFirstTen(int[] arr) {
        int n = Math.min(arr.length, 30);
        for(int i = 0; i< n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
     }		

}
