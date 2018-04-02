package cs6301.g45;
import java.util.*;
/**
* Implementation of AVL tree.
* @author 	Lopamudra
*/

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
	
     /**
     * @param <T> Entry Node for AVL tree
     * height : int : Height of each Entry node
     */
    static class Entry<T> extends BST.Entry<T>{
        int height;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            this.height = 1;
        }
    
	 /**
	 * Setter method for height of Node 
	 * @param height: int : Height of each Entry node
	 */
	public void setHeight(int height) {
		   this.height = height;
	   }
    }
     
    AVLTree() {
    		super();
    }
   
	 /**
	 * Rotation type during insertion
	 */
	private enum ROTATIONS {
	        LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
	};

    /* 
     * It will create node AVL Entry Node corresponding to BST Entry Node.
     */
    public BST.Entry<T> createEntry(T x, BST.Entry<T> left, BST.Entry<T> right){
     	return (new AVLTree.Entry<T>(x, null, null));
     }
    
    /**
     * @param x : BST.Entry<T> node for which height is calculated
     * @return int : height of the the Node
     */
    public int getHeight(BST.Entry<T> x){
        if(x == null) return 0;
        		return 1 + Math.max(getHeight(x.left), getHeight(x.right));
    }
    
    /**
     * Update the height value for t Node
     * @param t : Entry<T> Node
     */
    protected void updateHeight(Entry<T> t) {
        int lesserHeight = 0;
        int greaterHeight = 0;
        if (t.left != null) {
        		Entry<T> lesserAVLNode = (Entry<T>) t.left ;
            lesserHeight = lesserAVLNode.height;
        }
        if (t.right != null) {
        		Entry<T> greaterAVLNode = (Entry<T>) t.right;
            greaterHeight = greaterAVLNode.height;
        }
        t.height = (lesserHeight > greaterHeight) ? lesserHeight + 1 : greaterHeight + 1;
      
    }
    
    /**
     * Calculate the height difference between left and right node of "Entry<T> node"
     * @param node : Entry<T> : Node of AVL Tree
     * @return : int : height difference 
     */
    protected int getHeightDiff(Entry<T> node) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null) {
        		Entry<T> leftAVLNode = (Entry<T>) node.left;
        		leftHeight = leftAVLNode.height;
        }
        if (node.right != null) {
        		Entry<T> rightAVLNode = (Entry<T>) node.right;
            rightHeight = rightAVLNode.height;
        }
        return rightHeight - leftHeight;
    }
      
    /**
     * Balance the AVL tree after the insertion
     * @param node: Entry<T> : Node of AVL Tree
     * @param key : inserted node value
     */
    private void balanceAfterInsert(Entry<T> node, T key) {
    		int heightDiff = getHeightDiff(node);
    		
    		//System.out.println("Inside balanceafterinsert, node : "+ node.element + "x : "+ key);

	    	if (heightDiff > 1 || heightDiff < -1) {
	    		Entry<T> parent = null;
	    		Entry<T> child = null;
	    		ROTATIONS rotation = null;
	    	   
	    		if (heightDiff < 0) {//left lengthy
	    			parent = (Entry<T>)node.left;
	    			if(key.compareTo(parent.element) < 0){
	    				rotation = ROTATIONS.LEFT_LEFT;
	    				child = (Entry<T>)parent.left;
	    			}else {
	    				rotation = ROTATIONS.LEFT_RIGHT;
	    				child = (Entry<T>)parent.right;
	    			}
	    		} else {
	    			parent = (Entry<T>)node.right;
	    			if(key.compareTo(parent.element) > 0){
	    				rotation = ROTATIONS.RIGHT_RIGHT;
	    				child = (Entry<T>)parent.right;
	    			}else {
	    				rotation = ROTATIONS.RIGHT_LEFT;
	    				child = (Entry<T>)parent.left;
	    			}
	    		}
    	 
	    	  if (rotation == ROTATIONS.LEFT_RIGHT) { //Left rotation, right rotation
	    		  leftRotate(node.left);
	    		  rightRotate(node);
	    	  } else if (rotation == ROTATIONS.RIGHT_LEFT) { //Right rotation, left rotation
	    		  rightRotate(node.right);
	    		  leftRotate(node);
	    	  } else if (rotation == ROTATIONS.LEFT_LEFT) { //Right rotation
	    		  rightRotate(node);
	    	  } else {				//Left rotation
	    		  leftRotate(node);
	    	  }
	    	 
	    	  updateHeight(node); // New node
	    	  updateHeight(child); // New child node
	    	  updateHeight(parent); // New Parent node
	    	}
    	 }

    
    /**
     * Balance the AVL tree after the deletion
     * @param node: Entry<T> : Node of AVL Tree
     * @param key : inserted node value
     */
    private void balanceAfterDelete(Entry<T> node, T key) {
		int heightDiff = getHeightDiff(node);
		if(heightDiff > 1 || heightDiff < -1) {
			if(heightDiff < -1) {
				Entry<T> ll = (Entry<T>) node.left.left;
				int lHeight = (ll != null) ? ll.height : 0;
				Entry<T> lr = (Entry<T>) node.left.right;
				int rHeight = (lr != null) ? lr.height : 0;
				if(lHeight >= rHeight) { //RIGHT ROTATE
					BST.Entry<T> pNode = rightRotate(node);
					updateHeight(node);
					if(pNode != null)
						updateHeight((Entry<T>) pNode);
				}else { //LEFT RIGHT ROTATE
					leftRotate(node.left);
					BST.Entry<T> pNode = rightRotate(node);
					if(pNode.left != null)
						updateHeight((Entry<T>)pNode.left);
					if(pNode.right != null)
						updateHeight((Entry<T>)pNode.right);
					updateHeight((Entry<T>)pNode);
					
				}
			}else {
				Entry<T> rr = (Entry<T>) node.right.right;
				int rHeight = (rr != null) ? rr.height : 0;
				Entry<T> rl = (Entry<T>) node.right.left;
				int lHeight = (rl != null) ? rl.height : 0;
				if(rHeight >= lHeight) {  //LEFT ROTATE
					BST.Entry<T> pNode = leftRotate(node); 
					updateHeight(node);
					if(pNode != null)
						updateHeight((Entry<T>) pNode);
				}else {  //RIGHT LEFT ROTATE
					rightRotate(node.right);
					BST.Entry<T> pNode = leftRotate(node);
					if(pNode.left != null)
						updateHeight((Entry<T>)pNode.left);
					if(pNode.right != null)
						updateHeight((Entry<T>)pNode.right);
					updateHeight((Entry<T>)pNode);
				}
				
			}
		}
    }
    /**
     * Function to rotate right at a given node "t"
     * @param t : BST.Entry<T>:  Right rotation at node "t"
     * @return parent after the rotation of that subtree
     */
    public BST.Entry<T> rightRotate(BST.Entry<T> t){
    		BST.Entry<T> t_p = null;
    		if(entryStack.peek() != null)
			t_p = (Entry<T>) entryStack.pop();
		BST.Entry<T> t_c = t.left;
		BST.Entry<T> t_cRight = null;
		if(t_c.right != null) {
			t_cRight = t_c.right;	
		}
		t_c.right = t;
		t.left = t_cRight;
		if (t_p != null && t_p.left == t)
		    t_p.left = t_c;
		else if(t_p != null && t_p.right == t)
			t_p.right = t_c;
		if(t_p == null)
    			root = t_c;
		return t_c;
    }
    
    /**
     * Function to rotate left at a given node "t"
     * @param t : BST.Entry<T>:  Left rotation at node "t"
     * @return parent after the rotation in that subtree
     */
    BST.Entry<T> leftRotate(BST.Entry<T> t){
    		BST.Entry<T> t_c = null;
    		BST.Entry<T> t_p = null;
    		if(entryStack.peek() != null)
    			t_p = (Entry<T>) entryStack.pop();
	    	if(t.right != null)
	    		t_c = t.right;
	    	BST.Entry<T> t_cLeft = t_c.left;

        t_c.left = t;
        t.right = t_cLeft;
        
        if (t_p != null && t_p.left == t)
        		t_p.left = t_c;
        else if(t_p != null && t_p.right == t)
        		t_p.right = t_c;
        if(t_p == null)
        		root = t_c;
        return t_c;
    }
    
    /** Method to add node x to tree 
     *  @param: x: T - Element to be added to tree if it does not contain a node with same key, otherwise replace element by x
     *  @return Returns true if x is a new element added to tree, false otherwise
     */
    public boolean add(T x) {
    		BST.Entry<T> t = super.add_helper(x);
    		Entry<T> nodeInserted = (Entry<T>) t;
    		 while (nodeInserted != null) {
    			 updateHeight(nodeInserted);
    			 //System.out.println("nodeInserted : "+ nodeInserted.element + "x : "+ x);
    			 balanceAfterInsert(nodeInserted, x);
    			 if(!entryStack.isEmpty() && entryStack.peek() != null)
    				 nodeInserted = (Entry<T>) entryStack.pop();
    			 else
    				 nodeInserted = null;
    		 }
    		 return (t != null);
    }

    /** Method to remove x from tree
	 *	@param: x: T - Element to be removed
     *  @return: result: T - Returns x if found, null otherwise
     */
    public T remove(T x) {
    		BST.Entry<T> deletedNode = super.remove_helper(x);
    		Entry<T> nodeReplaced = (Entry<T>)deletedNode;
    		if(nodeReplaced == null)
    			return null;
    		while(nodeReplaced != null) {
    			updateHeight(nodeReplaced);
    			balanceAfterDelete(nodeReplaced, x);
    			if(!entryStack.isEmpty() && entryStack.peek() != null)
    				nodeReplaced = (Entry<T>) entryStack.pop();
   			 else
   				nodeReplaced = null;
    		}
    		return deletedNode.element;
    }

    
    /**
     * Driver method for AVL Tree
     */
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        
        Scanner sc = new Scanner(System.in);
        try {
	        while(sc.hasNext()) {
	            int x = sc.nextInt();
	            if( x > 0) {
	                System.out.print("Add " + x + " : ");
	                avlTree.add(x);
	                avlTree.printTree();
	            } 
	            else if (x < 0) {
	            	System.out.print("Remove " + x + " : ");
	            		avlTree.remove(-x);
	            		avlTree.printTree();
	            } 
	            else {
	            		Comparable<Integer>[] arr = avlTree.toArray();
	                System.out.print("Final: ");
	                for(int i=0; i < avlTree.size; i++) {
	                    System.out.print(arr[i] + " ");
	                }
	                System.out.println();
	                return;
	            }
	        } 
        }finally {
	        		sc.close();
	    }
    }

}

/*Sample Input
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
1 3 5 7 9 2 4 6 8 10 6 0
1 3 5 2 0
2 1 6 -2 -6

*/

