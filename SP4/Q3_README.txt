Steps to run the program:
 1.Create a project and copy all the java programs below to eclipse.
                  a. InsertionSort.java
		  b. MergeSUsingIntArray.java
		  c. Timer.java : provided by Professor
		  d. Driver_Q3.java
			
 2. Give the argument : <number of input> <case number> in Run Configuration->argument field.
e.g. : 200 2

  Here 200 is #of inputs which will be created randomly.
	2 : case 2 for merge sort

3. There are 4 cases for merge sort.
	Case Number options are below:
	1. case 1 : for text book merge sort
	2. case 2 : for merge sort passing temp array
	3. case 3 : Improvement in (2) + use insertion sort for base case when the size of 		    array is below some threshold.
	4. case 4 : Improvements in (2) and (3) + avoid copying to tmp array.

 4. Run the driver program.



SAMPLE OUTPUT:
Size of the input array:30000000
----------------------------------

Case 1 : First 30 elements in the Input unsorted array : 29999999 29999998 29999997 29999996 29999995 29999994 29999993 29999992 29999991 29999990 29999989 29999988 29999987 29999986 29999985 29999984 29999983 29999982 29999981 29999980 29999979 29999978 29999977 29999976 29999975 29999974 29999973 29999972 29999971 29999970 
Time: 5607 msec.
Memory: 443 MB / 963 MB.
Case 1 : First 30 elements in the Output sorted array : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 



Case 2: First 30 elements in the Input unsorted array : 29999999 29999998 29999997 29999996 29999995 29999994 29999993 29999992 29999991 29999990 29999989 29999988 29999987 29999986 29999985 29999984 29999983 29999982 29999981 29999980 29999979 29999978 29999977 29999976 29999975 29999974 29999973 29999972 29999971 29999970 
Time: 2679 msec.
Memory: 230 MB / 352 MB.
Case 2: First 30 elements in the Output sorted array : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 



Case 3: First 30 elements in the Input unsorted array : 29999999 29999998 29999997 29999996 29999995 29999994 29999993 29999992 29999991 29999990 29999989 29999988 29999987 29999986 29999985 29999984 29999983 29999982 29999981 29999980 29999979 29999978 29999977 29999976 29999975 29999974 29999973 29999972 29999971 29999970 
Time: 2390 msec.
Memory: 230 MB / 352 MB.
Case 3: First 30 elements in the Output sorted array : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 


Case 4: First 30 elements in the Input unsorted array : 29999999 29999998 29999997 29999996 29999995 29999994 29999993 29999992 29999991 29999990 29999989 29999988 29999987 29999986 29999985 29999984 29999983 29999982 29999981 29999980 29999979 29999978 29999977 29999976 29999975 29999974 29999973 29999972 29999971 29999970 
Time: 2116 msec.
Memory: 344 MB / 466 MB.
Case 4: First 30 elements in the Output sorted array : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 

