/**
 * Java Program to implement Binary Search Tree
 * @author Lopamudra 
 */
package cs6301.g45;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
	
	/*Class to represent node of a BST*/
	static class Entry<T> {
	    T element;       		   //BST node element
	    Entry<T> left, right;       //Represents left and right subtree nodes
	
	    Entry(T x, Entry<T> left, Entry<T> right) {
	        this.element = x;
	        this.left = left;
	        this.right = right;
	    }
    }
   
    Entry<T> root;					//Root of BST
    int size;					    //Size of BST
    Comparable[] arrData;			//Array of elements used for in-order traversal of tree
    Stack<Entry<T>> entryStack = new Stack<Entry<T>>();		//Stack used to design a single-pass algorithm
    
    /*BST constructor used to initialize its root and size*/
    public BST() {
    	root = null;
    	size = 0;
    }
    
    /**
     * Method used to create a new Entry<T> object
     * @param x: T - BST node element
     * @param left: Entry<T> - BST left subtree node
     * @param right: Entry<T> - BST right subtree node
     * @return Entry<T> - new Entry<T> object
     */
    public Entry<T> createEntry(T x, Entry<T> left, Entry<T> right){
     	return (new Entry<T>(x, null, null));
     }
    
    /** Method to check if node x is contained in tree
	 *  @param: x: T - BST node element
	 *  @return: true if x is contained in tree, false otherwise
     */
    public boolean contains(T x) {
	    Entry<T> t = find(x);
	    return (t != null && t.element.compareTo(x) == 0) ? true : false;
    }

    /** Method to check if there is an element that is equal to x in the tree
	 * @param: x: T - BST element
	 * @return: element: T - Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
	    Entry<T> t = find(x);
	    return (t != null && t.element.compareTo(x) == 0) ? t.element : null;
    }
    
    /** Method to find x in tree
	* @param: x: T - BST element that is to be found
	* @return: element: Entry<T> - Returns node where the search ends
	*/
    public Entry<T> find(T x) {
	    entryStack.push(null);
	    Entry<T> element = find(root,x);
	    return element;
    }
    
    /** Helper method to find x in tree 
	 *  @param: x: T - BST element that is to be found
	 *  @param: t: Entry<T> - BST root from where the search starts
	 *  @return: element: Entry<T> - Returns node where the search ends
	 */
    public Entry<T> find(Entry<T> t, T x) {
	    	if(t == null || t.element.compareTo(x) == 0)    
	    		return t;
	    	
	    	while(true) {
	    		if(x.compareTo(t.element) == -1) {
	    			if(t.left == null) {
	    				break;
	    			} 
	    			else {
	    				entryStack.push(t);
	        			t = t.left;
	    			}
	    		} 
	    		else if(x.compareTo(t.element) == 0) {
	    			break;
	    		} 
	    		else if(x.compareTo(t.element) == 1) {
	    			if(t.right == null) {
	    				break;
	    			}
	    			else {
	    				entryStack.push(t);
	        			t = t.right;
	    			}
	    		}
	    	}
    	return t;
    }
    
    /** Method to find the minimum element of BST
	 *  @return: t.element: T - Returns minimum element of BST if it exists, otherwise returns the root
	 */
    public T min() {
	    if(root == null)
	    	return null;
	    Entry<T> t = root;
	    while(t.left != null) {
	    	entryStack.push(t);
	    	t = t.left;
	    }
	    return t.element;
    }
    
    /** Method to find the maximum element of BST
	 *  @return: t.element: T - Returns maximum element of BST if it exists, otherwise returns the root
	 */
    public T max() {
	   	if(root == null)
	   		return null;
	   	Entry<T> t = root;
	   	while(t.right != null) {
	   		entryStack.push(t);
	   		t = t.right;
	   	}
	   	return t.element;
    }

    /** Method to add node x to tree 
     *  @param: x: T - Element to be added to tree if it does not contain a node with same key, otherwise replace element by x
     *  @return Returns true if x is a new element added to tree, false otherwise
     */
    public boolean add(T x) {
	    Entry<T> nodeInserted = add_helper(x);
	    return (nodeInserted != null);
    }
    
    /** Helper method to add node x to tree 
     *  @param: x: T - Element to be added to tree if it does not contain a node with same key, otherwise replace element by x
     *  @return Entry<T> - Returns the element added
     */
    public Entry<T> add_helper(T x){
	   	if(root == null) {
	   		root = createEntry(x, null, null);
	   		size = 1;
	   		return root;
	   	}
		Entry<T> insertPos = find(x);
	   	if(x.compareTo(insertPos.element) == 0) {
	   		insertPos.element = x;		//replace
	   		return insertPos;
	   	}
	   	else if(x.compareTo(insertPos.element) == -1) {
	    	insertPos.left = createEntry(x, null, null);;
	    }
	   	else
	    	insertPos.right = createEntry(x, null, null);;
	    	size++;
	    return insertPos;
    }

    /** Method to remove x from tree
	 *	@param: x: T - Element to be removed
     *  @return: result: T - Returns x if found, null otherwise
     */
    public T remove(T x) {
	    if(root == null) 
	    	return null;
	    Entry<T> rmvNode = remove_helper(x);
	    if(rmvNode != null)
	    	return rmvNode.element;
	    else
	    	return null;	    
    }
    
    /** Helper method to remove node x from the tree 
     *  @param: x: T - Element to be removed
     *  @return Entry<T> - Returns x if found, null otherwise
     */
    public Entry<T> remove_helper(T x){
	    Entry<T> t = find(x);	    	
	    if(t.element.compareTo(x) == -1 || t.element.compareTo(x) == 1)
	    	return null;
	    	
	    if(t.left == null || t.right == null) {
	    	bypass(t);
	    } 
	    else {	//t has 2 children
	    	entryStack.push(t);
	    	Entry<T> minRight = find(t.right, t.element);
	    	t.element = minRight.element;
	    	bypass(minRight);
	    }
	    size--;
	    return t;
    }

    /** 
	 * Method to bypass the child of t when it has at most one child, while removing t
	 * @param: t: Entry<T> - Element whose child is to be skipped  
	 */
    public void bypass(Entry<T> t) {
	    Entry<T> tParent = entryStack.peek();
	    Entry<T> c = (t.left == null) ? t.right : t.left;
	    if(tParent == null) {//t is root
	    	root = c;
	    } 
	    else if(tParent.left == t) {
	    	tParent.left = c;
	    }
	    else {
	    	tParent.right = c;
	    }
    }
    
    /** Method to create an array with the elements using in-order traversal of tree
	 *  @return: arr: Comparable[] - Returns an array containing in-order traversal of tree
	 */
    public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		inOrderTraversal(root, arr, 0);
		return arr;
    }  
    
    /** Helper method to add elements into an array using  in-order traversal of tree
     * @param t: Entry<T> - BST root
     * @param arr: Comparable[] - Array into which elements are to be added
     * @param index: int - Index of array
     * @return index: int - Index of array
     */
    public int inOrderTraversal(Entry<T> t, Comparable[] arr, int index) {
        if(t != null) {
            index = inOrderTraversal(t.left, arr, index);
            arr[index++] = t.element;
            index = inOrderTraversal(t.right, arr, index);
        }
        return index;
    }
    
    /** Method to iterate elements in sorted order of keys
     *  @return: new BSTIterator(): Iterator<T> - Returns a new object of BSTIterator
     */
    public Iterator<T> iterator() {
		return new BSTIterator(root);
	}
	
    /** 
	 * Class to implement BST iterator by in-order traversal using stack
	 */
    public class BSTIterator implements Iterator<T> {
        public Entry<T> currNode;		//Current Node of BST
        public Entry<T> lastNode;		//Last Node of BST

        /**
         * BSTIterator Constructor
         * @param root: Entry<T> - Root of BST
         */
        public BSTIterator(Entry<T> root) {
            if (root == null) 
                return;
            
            this.currNode = root;
            this.lastNode = root;
            
            while (currNode != null && currNode.left != null) {
            	currNode = currNode.left;
            }
            
            while (lastNode != null && lastNode.right != null) {
            	lastNode = lastNode.right;
            }
        }

        /**
         * Method to check if iterator has next element or not
         * @return: Returns true if next element exists, false otherwise
         */
        public boolean hasNext() {
            return currNode != null && (currNode.element.compareTo(lastNode.element) == -1 || currNode.element.compareTo(lastNode.element) == 0);
        }
        
        /**
         * Method to get the next element in iterator
         * @return t: T - Returns the next element in iterator if it exists, null otherwise
         */
        public T next() {
        	Entry<T> t = currNode;
            
            if (currNode.right != null) {		//Current node has right subtree/child
            	currNode = currNode.right;
                while (currNode.left != null) {
                	currNode = currNode.left;
                }
            } else {
                currNode = getLeftSubTree();	//Current node has only left subtree/child
            }
            return t.element;
        }

        /**
         * Method to get the left subtree elements when tree does not have right subtree/child
         * @return t1: Entry<T> - Returns the left subtree/child of current node
         */
        public Entry<T> getLeftSubTree() {
        	Entry<T> t = root;
        	Entry<T> t1 = null;
            if (currNode.element.compareTo(lastNode.element) == 0) {
                return null;
            }
            while (t != null) {
                if (currNode.element.compareTo(t.element) == -1) {
                    t1 = t;
                    t = t.left;
                } else if (currNode.element.compareTo(t.element) == 1) {
                    t = t.right;
                } else 
                    break;
            }
            return t1;
        }
    }

    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNext()) {
            int x = sc.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable<Integer>[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }           
        }
    }

    /** Method to print the tree elements 
	 */
    public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
    }

    /** Helper method to print the tree elements by in-order traversal 
	 */
    void printTree(Entry<T> node) {
    	if(node != null) {
    		printTree(node.left);
    		System.out.print(" " + node.element);
    		printTree(node.right);
		}
    }

}
/*
Sample input:
1 3 5 7 9 3 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

2 1 6 -2 -6 3 4 -3 0

*/
