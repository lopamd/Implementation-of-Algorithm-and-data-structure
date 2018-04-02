package cs6301.g45;

import java.util.Random;
import java.util.Scanner;
/**
 * Implementation of Insertion Sort algorithm.
 * @author Lopamudra 
 */
public class InsertionSort {
	
	//Insertion sort method
	public static void insertionsort(int arr[], int p, int r)
    {
        int length = r - p;
        
        for (int i= p + 1; i <= r; ++i)
        {
            int key = arr[i];
            int j = i-1;
 
            while (j>=p && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

}
