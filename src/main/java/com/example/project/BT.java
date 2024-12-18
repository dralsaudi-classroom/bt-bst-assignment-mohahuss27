package com.example.project;

public class BT<T> {
    BTNode<T> root, current;
	
	/** Creates a new instance of BT */
	public BT() {
		root = current = null;
	}
	public boolean empty(){
		return root == null;
	}
    public T retrieve() {
		return current.data;
	}
	public void update(T val) {
		current.data = val;
	}
    public boolean insert(Relative rel, T val) {
		switch(rel) {
		   case Root:
			if(!empty())
                return false;
            current = root = new BTNode<T>(val);
			return true;
		   case Parent:	// This is an impossible case.
			return false;
		   case LeftChild:
			if(current.left != null)
                return false;
			current.left = new BTNode<T>(val);
			current = current.left;
			return true;
		   case RightChild:
			if(current.right != null)
                return false;
			current.right = new BTNode<T> (val);
			current = current.right;
			return true;
		   default:
			return false;
		}
	}
    public void deleteSubtree(){
		if(current == root){
			current = root = null;
		}
		else {
			BTNode<T> p = current;
			find(Relative.Parent);
			if(current.left == p)
				current.left = null;
			else 
				current.right = null;
			current = root;
		}
	}
    public boolean find(Relative rel){
		switch (rel) {
		   case Root:	// Easy case
			current = root;
			return true;
		   case Parent:
			if(current == root)
                return false;
			current = findparent(current, root);
			return true;
		   case LeftChild:
			if(current.left == null)
                return false;
			current = current.left;
			return true;
		   case RightChild:
			if(current.right == null)
                return false;
			current = current.right;
			return true;
		   default:
			return false;
		}
	}
    private BTNode<T> findparent(BTNode<T> p, BTNode<T> t) {
		if(t == null)
			return null;	// empty tree
		
		if(t.right == null && t.left == null)
			return null;
		else if(t.right == p || t.left == p)
			return t;	// parent is t
		else {
			BTNode<T> q = findparent(p, t.left);
			if (q != null)
				return q;
			else
				return findparent(p, t.right);
		}
	}
	public boolean isLeaf() {
		return current.left == null && current.right == null;
	}
	public int countLeaves() {
		// Write the method countLeafs that should return the number of leaf nodes in the tree. A leaf node is a node that has no children.
		return countLeavesHelper(root);
	}
	
	private int countLeavesHelper(BTNode<T> p) {
		if (p == null)
			return 0;
		
		if (p.left != null || p.right != null)
			return countLeavesHelper(p.left) + countLeavesHelper(p.right);
		
		return 1;
	}
}
