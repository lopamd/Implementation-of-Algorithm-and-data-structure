package cs6301.g45;

import java.util.Iterator;

public class BSTMapDriver {
	public static void main(String args[]){
		BSTMap<Integer, String> bst=new BSTMap<Integer,String>();
		//Adding the key value pairs
		bst.put(10, "Hello10");
		bst.put(5, "Hello5");
		bst.put(9, "Hello9");
		bst.put(15, "Hello15");
		bst.put(6, "Hello6");
		bst.put(17, "Hello17");
		bst.put(8, "Hello8");
		//Remove an element with key 10
		//bst.bst.remove(new Node<Integer,String>(10));
		System.out.println("Printing the tree in in-order traversal:");
		bst.bst.printTree();
		System.out.print("Retrieve value of key 6: ");
		System.out.println(bst.get(6));
		Iterator<Integer> it=bst.iterator();
		System.out.print("Using iterator printing the keys: ");
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
		
	}
	
	
}


/*
 * Input tree provided: [10,"Hello10"][5,"Hello5"][9, "Hello9"][15, "Hello15"][6, "Hello6"][17, "Hello17"][8, "Hello8"]
 * Get value of key 6 and print the tree using the iterator.
 * 
 * Output printed:
 * 
 *  Printing the tree in in-order traversal:
 *	[7] [5, Hello5] [6, Hello6] [8, Hello8] [9, Hello9] [10, Hello10] [15, Hello15] [17, Hello17]
 *	Retrieve value of key 6: Hello6
 *	Using iterator printing the keys: 5 6 8 9 10 15 17
 */