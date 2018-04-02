// Ver 1.0:  Starter code for bounded size Binary Heap implementation

package cs6301.g45;

/**
* Implementation of Binary Heap and heap sort.
* @author 	Lopamudra
*/
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
    T[] pq;
    Comparator<T> c;
    int size;
    /** Build a priority queue with a given array q, using q[0..n-1].
     *  It is not necessary that n == q.length.
     *  Extra space available can be used to add new elements.
     */
    public BinaryHeap(T[] q, Comparator<T> comp, int n) {
		pq = q;
		c = comp;
    }

    public void insert(T x) {
    	add(x);
    }

    public T deleteMin() {
    	return remove();
    }

    public T min() { 
    	return peek();
    }

    public void add(T x) { /* TO DO. Throw exception if q is full. */
		if(isFull())
			throw new NoSuchElementException("Heap is full.");
		pq[size] = x;
		percolateUp(size);
		size++;
    }

    public T remove() { /* TO DO. Throw exception if q is empty. */
		if(isEmpty())
			throw new NoSuchElementException("Heap is empty.");
		T min = pq[0];
		pq[0] = pq[size - 1];
		percolateDown(0);
		size--;
		return min;
    }

    public T peek() { /* TO DO. Throw exception if q is empty. */
		if(isEmpty())
			throw new NoSuchElementException("Heap is empty.");
		return pq[0];
    }

    public void replace(T x) {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing. 
	   This operation is used in finding largest k elements in a stream.
	 */
    	T temp = pq[0];
    	percolateDown(0);
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { /* to be implemented */
		T temp = pq[i];
		while(i > 0 && (c.compare(temp, pq[(i - 1)/2])) < 0){
			pq[i] = pq[(i - 1)/2];
			i = (i - 1)/2;
		}
		pq[i] = temp;
    		
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { /* to be implemented */
		T temp = pq[i];
		int lChild = 2*i + 1;
		while(lChild <= size - 1) {
			if(lChild < size - 1 && c.compare(pq[lChild], pq[lChild + 1]) > 0) {
				lChild++;
			}
			
			if(c.compare(temp, pq[lChild]) <= 0)
				break;
			pq[i] = pq[lChild];
			i = lChild;
			lChild = 2*lChild + 1;
		}
		pq[i] = temp;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
		for (int i = (pq.length - 2)/2; i >= 0; i--) {
			percolateDown(i);
		}
    		
    }
      
    /** Method to check if heap is empty.*/
    public boolean isEmpty() {
    	return pq.length == 0;
    }
    
    /** Method to check if heap is full.*/
    public boolean isFull() {
		return pq.length == size;
    }
   

    /* sort array A[].
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    public static<T> void heapSort(T[] A, Comparator<T> comp) { /* to be implemented */
		BinaryHeap<T> bHeap = new BinaryHeap<>(A, comp, A.length);
		for(int i = 0; i < A.length; i++) {
			bHeap.insert(A[i]);
		}
		bHeap.buildHeap();
		int size = A.length;
		for(int i = size - 1; i >= 0; i--) {
    		A[i] = bHeap.remove();
		}
    }
}
