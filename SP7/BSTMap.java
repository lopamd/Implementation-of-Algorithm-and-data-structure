package cs6301.g45;
/**
* Implementation of BSTMap.
* @author 	Lopamudra
*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import cs6301.g45_1.BST.BSTIterator;

/*
 * Node class that contains Key and Value information
 * @param K,V - Key and Value
 */
class Node<K extends Comparable<? super K>,V> implements Comparable<Node<K,V>>{
	K key;
	V value;
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param Node<K,V> 
	 * for comparison between two Node class objects
	 */
	@Override
	public int compareTo(Node<K,V> o) {
		return this.key.compareTo(o.key);
	}
	/*
	 * Constructors
	 */
	public Node(){
		
	}
	/*
	 * Key-Value based Constructor
	 */
	public Node(K key, V value){
		this.key=key;
		this.value=value;
	}
	/*
	 * Key based constructor
	 */
	public Node(K key){
		this.key=key;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Used while printing Key Value pairs of the object 
	 */
	public String toString(){
		return "["+key+", "+value+"]";
	}
}

public class BSTMap<K extends Comparable<? super K>,V> implements Iterable<K> {
	/*
	 * Root of the BSTMap variable
	 */
	BST<Node<K,V>> bst;
	/*
	 * Constructor
	 */
	public BSTMap(){
		super();
		bst=new BST<Node<K,V>>();
	}
	/*
	 * Get Value provided Key
	 */
	public V get(K key){
		BST.Entry<Node<K,V>> n=this.bst.find(new Node<K,V>(key));
		return n.element.value;
	}
	/*
	 * Adding key value pair to the BSTMap
	 * @param K key,V value
	 */
	public boolean put(K key, V value){
		return this.bst.add(new Node<K,V>(key,value));
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * Returns the key Iterator of the BSTMap 
	 */
	@Override
	public Iterator<K> iterator() {
		ArrayList<K> cache = new ArrayList<K>();
		Iterator<Node<K,V>> it=this.bst.iterator();
		while(it.hasNext()){
			Node<K,V> n=it.next();
			cache.add(n.key);
		}
		return cache.iterator();
	}
	
}

