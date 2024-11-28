package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comp;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		comp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 * @param comparator specific comparator to be used, created outside constructor.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		comp = comparator;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param e element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E e) {
		//If current node null then create new node with param element. Return true.
		if(root == null){
			root = new BinaryNode<E>(e);
			size++;
			return true;
		}
		return add(e, root);
	}

	/**
	 * Recursive helper function to insert specific element into tree.
	 * @param e element to be inserted
	 * @param node current node
	 * @return true if element was inserted, false if element already exists or couldnt be inserted
	 */
	private boolean add(E e, BinaryNode<E> node){
		//If element e is smaller than node element, go left.
		if(comp.compare(e, node.element) < 0){
			if(node.left == null){
				node.left = new BinaryNode<E>(e);
				size++;
				return true;
			}
			else
				return add(e, node.left);
		}

		//If element e is bigger than node element, go right.
		else if(comp.compare(e, node.element) > 0){
			if(node.right == null){
				node.right = new BinaryNode<E>(e);
				size++;
				return true;
			}
			else
				return add(e, node.right);
		}

		//If element e is same as node element, duplicate is found. Return false.
		return false;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		//Height of tree = maximum depth.
		return height(root);
	}

	/**
	 * Recursive helper method to compute height of tree.
	 * @param node current node used as reference
	 * @return maximum depth for tree
	 */
	private int height(BinaryNode<E> node){
		//Baseline - once end of branch is found.
		if(node == null){
			return 0;
		}
		else{
			//Recursive step branching out left and right from node
			int left = height(node.left);
			int right = height(node.right);
			return 1 + java.lang.Math.max(left, right);
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Removes all of the elements from this tree.
	 */
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Return a String containing all elements from tree inorder.
	 * @return String inorder of all elements
	 */
	public String toString(){
		if(root == null){
			return "No elements in tree";
		}
		else{
			StringBuilder sb = new StringBuilder();
			//Recursive step
			return toString(root, sb);
		}	
	}

	/**
	 * Recursive helper method to compose String of all elements from tree inorder.
	 * @param node current node used as reference
	 * @param sb StringBuilder to compose String
	 * @return String with processed nodes/elements
	 */
	private String toString(BinaryNode<E> node, StringBuilder sb){
		if(node != null){
			return "";
		}
		else{
			sb.append(toString(node.left, sb));
			sb.append(node.element.toString());
			return toString(node.right, sb);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sortedArray = new ArrayList<E>();
		toArray(root, sortedArray);
		root = buildTree(sortedArray, 0, sortedArray.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n != null){
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last){
			return null;
		}
		else{
			int mid = first + (last - first)/2;
			BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
			node.left = buildTree(sorted, first, mid - 1);
			node.right = buildTree(sorted, mid + 1, last);
			return node;
		}
	}

	static class BinaryNode<E>{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BSTVisualizer bstVisualizer = new BSTVisualizer("BinaryTree", 300, 300);
		BSTVisualizer bstVisualizer2 = new BSTVisualizer("BinaryTree", 300, 300);
		BinarySearchTree bst1 = new BinarySearchTree<>();
		BinarySearchTree bst2 = new BinarySearchTree<>();
		bst1.add(200);
		bst1.add(100);
		bst1.add(150);
		bst1.add(300);
		bst1.add(400);
		bst1.add(500);
		bst1.add(600);
		bst1.add(550);
		bst1.add(50);

		/* 
		bst2.add(100);
		bst2.add(200);
		bst2.add(300);
		bst2.add(400);
		bst2.add(500);
		bst2.add(600);
		*/
		

		bstVisualizer.drawTree(bst1);
		bst1.rebuild();
		bstVisualizer2.drawTree(bst1);
	}
	
}
