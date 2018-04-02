package cs6350.g45;

/*
 * Class to hold the node information
 * @author Lopamudra 
 */

public class Tree {

	String item;
	double frequency;
	String label;
	Tree left;
	Tree right;
	public Tree(){
		label="";
		item="";
	}
	public Tree(String item, double frequency) {
		label="";
		this.item = item;
		this.frequency = frequency;
		left = null;
		right = null;
	}

	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

}
