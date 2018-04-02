package CS6301.g45.SP2.Q2;

/**
 * @author Lopamudra
 *
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {
	
	/**
	 * @param otherList : The second sorted list to merge
	 */
	void merge(SortableList<T> otherList) 
	{  
		//Base cases
    	if(head == null){
    		head = otherList.head;
    		return;
    	}
    	if(otherList.head == null){
    		return;
    	}
    	/*Entry tCursor stores reference for the head of parent list.*/
    	Entry<T> tCursor = head.next;
    	
    	/*Entry oCursor stores reference for the head of other List */
    	Entry<T> oCursor = otherList.head.next;
    	
    	/*Entry dummyTail stores reference for head of the sorted merged List  */
    	Entry<T> dummyTail = head;
    	
    	while(tCursor != null && oCursor != null)
    	{
    		/* Select next element of the merged List by adding smaller element to the result list.*/
	    	if(tCursor.element.compareTo(oCursor.element) <= 0){
	    		dummyTail.next = tCursor;
	    		dummyTail = tCursor;
	    		tCursor = tCursor.next;
	    	}else{
	    		dummyTail.next = oCursor;
	    		dummyTail = oCursor;
	    		oCursor = oCursor.next;
	    	}
    	}
    	/*If any list is empty then the dummyTail will reference to the remaining element of the other list*/
    	if(tCursor == null)
    	{
    		dummyTail.next = oCursor;
    		tail = otherList.tail;
    	}
    	else
    		dummyTail.next = tCursor;
	}
	
	
    /**
     * Split the nodes of the given list into two halves recursively,
     * and do the merge operation on both the list. 
     */
    void mergeSort() { 
    	/* Base cases */
    	if(head == null || head.next == null)
    		return;
    	Entry<T> startCursor = this.head.next;
    	Entry<T> endCursor = this.tail;

    	if(startCursor != endCursor)
    	{
    		/* Get the middle of the List. */
	    	Entry<T> middle = getMiddle(startCursor);
	    	Entry<T> middleNext = middle.next; // head for the second half List
	    	
	    	/* Apply merge sort to the left half of the List. */
	    	tail = middle;
	    	tail.next = null;
	    	mergeSort();
	    	
	    	/* Apply merge sort to the right half of the List. */
	    	SortableList<T> list2 = new SortableList<>();
	    	list2.head.next = middleNext;
	    	list2.tail = endCursor;
	    	list2.mergeSort();
	    	
	    	//Merge the two lists
	    	merge(list2);
    	}
    	
    }
    /**
     * @param list : unsorted List for merge sort.
     */
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
    	list.printList();
    	list.mergeSort();
    	list.printList();
    }
	 
   /**
    * Function to find the middle element of the LinkedList
    * Moving fastptr by two and slowptr by one so that 
    * slowptr will point to middle node when the fastptr reach the end of the list
    * 
	* @param head : reference to the head of the LinkedList whose middle element is needed.
	* @return middle element of the Linked List
	*/
public Entry<T> getMiddle(Entry<T> head){
	   if(head == null)
		   return head;
	   Entry<T> slowptr = head; 
	   Entry<T> fastptr = head;
	   while(fastptr.next!= null && fastptr.next.next != null)
	   {
		   slowptr = slowptr.next;
		   fastptr = fastptr.next.next;
	   }
	   //System.out.println("slowptr : "+ slowptr.element);
	   return slowptr;
   }

}
