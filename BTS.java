// Java program to search a given key in a given BST

class Node {
	int key;
	Node left, right;

	public Node(int item) {
		key = item;
		left = right = null;
	}
}

class BinarySearchTree {
	Node root;

	// Constructor
	BinarySearchTree() {
		root = null;
	}

	// A utility function to insert
	// a new node with given key in BST
	Node insert(Node node, int key) {
		// If the tree is empty, return a new node
		if (node == null) {
			node = new Node(key);
			return node;
		}

		// Otherwise, recur down the tree
		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);

		// Return the (unchanged) node pointer
		return node;
	}

	// Utility function to search a key in a BST
	Node search(Node root, int key) {
		// Base Cases: root is null or key is present at root
		if (root == null || root.key == key)
			return root;

		// Key is greater than root's key
		if (root.key < key)
			return search(root.right, key);

		// Key is smaller than root's key
		return search(root.left, key);
	}

	// Driver Code
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

		// Inserting nodes
		tree.root = tree.insert(tree.root, 50);
		tree.insert(tree.root, 30);
		tree.insert(tree.root, 20);
		tree.insert(tree.root, 40);
		tree.insert(tree.root, 70);
		tree.insert(tree.root, 60);
		tree.insert(tree.root, 80);

		// Key to be found
		int key = 6;

		// Searching in a BST
		if (tree.search(tree.root, key) == null)
			System.out.println(key + " not found");
		else
			System.out.println(key + " found");

		key = 60;

		// Searching in a BST
		if (tree.search(tree.root, key) == null)
			System.out.println(key + " not found");
		else
			System.out.println(key + " found");
	}
}

//Output
//6 not found
//60 found


//// Insertion in Binary Search Tree using Recursion:  //////

// Java program to demonstrate
// insert operation in binary
// search tree

import java.io.*;

public class BinarySearchTree {

	// Class containing left
	// and right child of current node
	// and key value
	class Node {
		int key;
		Node left, right;

		public Node(int item)
		{
			key = item;
			left = right = null;
		}
	}

	// Root of BST
	Node root;

	// Constructor
	BinarySearchTree() { root = null; }

	BinarySearchTree(int value) { root = new Node(value); }

	// This method mainly calls insertRec()
	void insert(int key) { root = insertRec(root, key); }

	// A recursive function to
	// insert a new key in BST
	Node insertRec(Node root, int key)
	{
		// If the tree is empty,
		// return a new node
		if (root == null) {
			root = new Node(key);
			return root;
		}

		// Otherwise, recur down the tree
		else if (key < root.key)
			root.left = insertRec(root.left, key);
		else if (key > root.key)
			root.right = insertRec(root.right, key);

		// Return the (unchanged) node pointer
		return root;
	}

	// This method mainly calls InorderRec()
	void inorder() { inorderRec(root); }

	// A utility function to
	// do inorder traversal of BST
	void inorderRec(Node root)
	{
		if (root != null) {
			inorderRec(root.left);
			System.out.print(root.key + " ");
			inorderRec(root.right);
		}
	}

	// Driver Code
	public static void main(String[] args)
	{
		BinarySearchTree tree = new BinarySearchTree();

		/* Let us create following BST
			50
		/	 \
		30	 70
		/ \ / \
	20 40 60 80 */
		tree.insert(50);
		tree.insert(30);
		tree.insert(20);
		tree.insert(40);
		tree.insert(70);
		tree.insert(60);
		tree.insert(80);

		// Print inorder traversal of the BST
		tree.inorder();
	}
}

// This code is contributed by Ankur Narain Verma
//Output
//20 30 40 50 60 70 80 

///////  Implementation of Deletion operation in a BST: /////

// Java program to implement optimized delete in BST.
import java.util.*;

class Node {
	int key;
	Node left, right;

	// A utility function to create a new BST node
	Node(int item) {
		key = item;
		left = right = null;
	}
}

class BST {
	Node root;

	// A utility function to do inorder traversal of BST
	void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.key + " ");
			inorder(root.right);
		}
	}

	/* A utility function to insert a new node with given key in
	* BST */
	Node insert(Node node, int key) {
		/* If the tree is empty, return a new node */
		if (node == null)
			return new Node(key);

		/* Otherwise, recur down the tree */
		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);

		/* return the (unchanged) node pointer */
		return node;
	}

	/* Given a binary search tree and a key, this function
	deletes the key and returns the new root */
	Node deleteNode(Node root, int key) {
		// Base case
		if (root == null)
			return root;

		// Recursive calls for ancestors of
		// node to be deleted
		if (root.key > key) {
			root.left = deleteNode(root.left, key);
			return root;
		} else if (root.key < key) {
			root.right = deleteNode(root.right, key);
			return root;
		}

		// We reach here when root is the node
		// to be deleted.

		// If one of the children is empty
		if (root.left == null) {
			Node temp = root.right;
			return temp;
		} else if (root.right == null) {
			Node temp = root.left;
			return temp;
		}

		// If both children exist
		else {

			Node succParent = root;

			// Find successor
			Node succ = root.right;
			while (succ.left != null) {
				succParent = succ;
				succ = succ.left;
			}

			// Delete successor. Since successor
			// is always left child of its parent
			// we can safely make successor's right
			// right child as left of its parent.
			// If there is no succ, then assign
			// succ.right to succParent.right
			if (succParent != root)
				succParent.left = succ.right;
			else
				succParent.right = succ.right;

			// Copy Successor Data to root
			root.key = succ.key;

			// Delete Successor and return root
			return root;
		}
	}

	// Driver Code
	public static void main(String[] args) {
		BST tree = new BST();

		/* Let us create following BST
				50
			/	 \
			30	 70
			/ \ / \
		20 40 60 80 */
		tree.root = tree.insert(tree.root, 50);
		tree.insert(tree.root, 30);
		tree.insert(tree.root, 20);
		tree.insert(tree.root, 40);
		tree.insert(tree.root, 70);
		tree.insert(tree.root, 60);

		System.out.print("Original BST: ");
		tree.inorder(tree.root);

		System.out.println("\n\nDelete a Leaf Node: 20");
		tree.root = tree.deleteNode(tree.root, 20);
		System.out.print("Modified BST tree after deleting Leaf Node:\n");
		tree.inorder(tree.root);

		System.out.println("\n\nDelete Node with single child: 70");
		tree.root = tree.deleteNode(tree.root, 70);
		System.out.print("Modified BST tree after deleting single child Node:\n");
		tree.inorder(tree.root);

		System.out.println("\n\nDelete Node with both child: 50");
		tree.root = tree.deleteNode(tree.root, 50);
		System.out.print("Modified BST tree after deleting both child Node:\n");
		tree.inorder(tree.root);
	}
}


/*
 * Output: 
        Original BST: 20 30 40 50 60 70 

        Delete a Leaf Node: 20
        Modified BST tree after deleting Leaf Node:
        30 40 50 60 70 

        Delete Node with single child: 70
        Modified BST tree after deleting single child No...
 * 
 * 
 */