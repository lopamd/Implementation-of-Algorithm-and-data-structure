package cs6301.g45.Q5;

import java.util.Scanner;

/**
 * Java Program to implement array-based bounded sized queue
 * @author Lopamudra 
 */

public class BoundedQueueOperations<T> {

	int front;	 //Index of the front element of the queue
	int rear;    //Index of the element to be added into the queue
	int minSize; //Represents the minimum size of queue
	int arrSize; //Represents the total number of elements in the array
	T[] arr;     //Array used to implement bounded-sized queue
	T[] arr1;	 //Array used to copy elements from arr
	
	/**
	 * Constructor for BoundedQueueOperations 
	 */
	public BoundedQueueOperations() {
		minSize = 16;	//Minimum size of the queue should be 16 at all times
		arrSize = 0;	
		front = -1;
		rear = -1;
		arr =  (T[]) new Object[minSize];
	}
	
	/**
	 * Method used to check if the queue is mostly empty (less then 25% occupied)
	 * @return true if the queue is mostly empty, false otherwise
	 */
	 public boolean isEmpty()
	 {
		 int queueSize = (int) (0.25*minSize);
		 if(arrSize == queueSize) {
			return true;
		 }
		 else {
			 return false;
		 }
	 }

	/**
	 * Method used to check if the queue is mostly full (over 90% occupied)
	 * @return true if the queue is mostly full, false otherwise
	 */
	 public boolean IsFull()
	 {
		 int queueSize = (int) (0.9*minSize);
		 if(arrSize == queueSize)
		 {
			return true;
		 }
		 else {
			 return false;
		 }
	}

	/**
     * Inserts the specified element into the queue if it is possible to do so immediately without violating capacity restrictions.
	 * @param element: T - Element to be added into the queue
	 * @return true if the element was added to the queue, false otherwise
	 */
	public boolean offer(T element) 
	{	
		if (rear == -1) { 	//If the queue is empty then add the element to the front of the queue
			front = 0;
			rear = 0; 
			arr[rear] = element;
			arrSize++;
			System.out.println("Element added at front:" + element);
			return true;
		}
		else if((rear + 1 < minSize) && !IsFull()) { 	//Keep adding element until the queue is full
			 rear = (rear+1) % minSize;
			 arr[rear] = element;
			 arrSize++;
			 System.out.println("Element added:" +element);
			 return true;
		}
		else if(IsFull()) {		//If the queue is 90% full then resize it and then add the element
		 	resize(minSize);			
			arr[++rear] = element;	
			arrSize++;
			System.out.println("Element added after resize:" + element);
			return true;
		}
		else {
			return false;
		}
	 }
	
	/**
	 * Retrieves but does not remove the head of the queue or returns null if the queue is empty
	 * @return head: T - Head of the queue
	 */
	public T peek() {
		if(front == -1 || arrSize == 0) { 	//Return null if the queue is empty
			System.out.println("Cannot peek into empty Queue! Perform offer!");
			return null;
		}
		else {
			front = (front) % minSize;
			T head = (T) arr[front];
			System.out.println("Head of the queue:" + head);
			return  head;
		}
	}
	
	/**
	 * Retrieves and removes the head of the queue or returns null if the queue is empty
	 * @return head: T - Head of the queue
	 */
	public T poll() {
		 if(isEmpty()) {	//If the queue is mostly empty then halve it and poll the head
			 resize(minSize);
			 front = (front) % minSize;
			 T head = (T) arr[front];
			 arr[front] = null;
			 System.out.println("Element polled:"+head);
			 front++;
			 arrSize--;
			 return  head;
		 }
		 else if(front == rear || arrSize == 0) {	//If the queue is empty then return null
			 rear = front = -1;
			 System.out.println("Cannot poll from empty queue! Perform offer!");
			 return null;	
		}
		else {	//Poll the head of the queue
			 front = (front) % minSize;
			 T head = (T) arr[front];
			 arr[front] = null;
			 front++;
			 System.out.println("Element polled:" + head);
			 arrSize--;
			 return  head;
		} 
	}
	
	/**
	 * Doubles the queue if it is mostly full or halves it if it is mostly empty
	 * @param size: int - queue size which is to be doubled or halved
	 */
	 public void resize(int size)
	 {
		 if(IsFull()) {
			 int s = size;
			 size = size * 2;
			 arr1 = (T[]) new Object[size];		//Array of double size
			 for (int j = 0; j <= rear; j++) { 	//Copy elements from arr to arr1
		       	arr1[j] = arr[j];
		     }
		     arr = arr1;
		     minSize = size;	
		     arr1=null;	
		     System.out.println("Queue size doubled from "+s+" to "+minSize);
		 }
		 else {
			 if(isEmpty()) {
				 int s = size;
				 size = minSize/2;
				 
				 //Cannot halve the queue if its size is 16 to maintain the minimum size of queue
				 if(size <= 16)
					 size = 16;
				 else 
					 size=minSize/2;
					
				if(minSize == size)
					return;
				
				arr1 = (T[]) new Object[size];		//Array of half size
				int index=0;
				for (int j=front; j<=rear; j++) {	//Copy elements from arr to arr1
				   	if(arr[j] != null) {
				     	arr1[index] = arr[j];
				    }
				    index++;
				}
				front = 0;
				rear = index;
				arr = arr1;
				minSize=size;	
				arr1=null;
				System.out.println("Queue size halved from "+s+" to "+minSize);
			 }
		 }
	 }
	  
	 public static void main(String[] args) {	     
		 BoundedQueueOperations<Integer> queue = new BoundedQueueOperations<>();
	     Scanner sc = new Scanner(System.in);
	     System.out.println("Do you want to perform any operation- yes/no?");
	     String ans = sc.next();
	     
	     do{
		     System.out.println("\nWhich operation you want to perform: offer/poll/peek/print?");
		     String op = sc.next();
		     
		     if(op.equalsIgnoreCase("offer")) {
				 System.out.println("How many elements you want to offer/add?");
				 int num = sc.nextInt();
				 System.out.println("Enter the elements:");
				 for(int i=0; i<num; i++) {
				   	 queue.offer(sc.nextInt());
				 }
				 
			 } else if(op.equalsIgnoreCase("poll")) {
				 System.out.println("How many elements you want to poll/remove?");
				 int num = sc.nextInt();
				 for(int i=0; i<num; i++) {
				   	 queue.poll();
				 }
			 } else if(op.equalsIgnoreCase("peek")) {
				 queue.peek();
			 } else if(op.equalsIgnoreCase("print")) {
				 for(Object a: queue.arr) {
					 System.out.print(a+" ");
				 }
			 } else {
				 System.out.println("There is no such operation!");
			 }
		     
	   	}while(ans.equalsIgnoreCase("yes"));
	 }
	
}
