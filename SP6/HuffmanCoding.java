package cs6350.g45;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Java Program to implement Huffman coding
 * @author Lopamudra 
 */

public class HuffmanCoding {

	PriorityQueue<Tree> huffCode;
	String code;
	Tree[] nodes;
	
	// constructor
	public HuffmanCoding(ArrayList<String> codes) {
		code="";
		huffCode = new PriorityQueue<Tree>(new Comparator<Tree>() {
			// overridden comparator to consider the frequency values of the tree for sorting
			@Override
			public int compare(Tree o1, Tree o2) {
				if (o1.frequency <= o2.frequency)
					return -1;
				return 1;
			}

		});
		int i = 0;
		nodes = new Tree[codes.size()];
		for (String sym : codes) {
			String[] parsed = sym.split(" ");
			nodes[i] = new Tree(parsed[0], Double.parseDouble(parsed[1]));
			huffCode.add(nodes[i]);
		}
	}

	/*
	 * method to build the huffman tree
	 */
	public void buildHuffTree() {
		if(huffCode.size() == 1){
			huffCode.peek().label = "0";
		}
		while (huffCode.size() != 1) {
			// extract the first 2 minimum trees
			Tree min1 = huffCode.poll();
			Tree min2 = huffCode.poll();
			//if (min2 != null) {
				// add the frequencies
				Tree merged = new Tree("", min1.frequency + min2.frequency);
				merged.left = min1;
				min1.label = "0"; // left label is 0
				merged.right = min2;
				min2.label = "1"; // right label is 1
				huffCode.add(merged);
			//}

		}
	}
	/*
	 * @param Tree : To recurse over the nodes of the huffman tree
	 */
	public void readTree(Tree t) {

		String temp = code;
		code = code + t.label;
		// if leaf node output he symbol and code
		if (t.getLeft() == null && t.getRight() == null) {
			System.out.println(t.item + " " + code);
		} 
		//if not leaf then recursion
		else {
			readTree(t.left);
			readTree(t.right);
		}
		code = temp;

	}

}
