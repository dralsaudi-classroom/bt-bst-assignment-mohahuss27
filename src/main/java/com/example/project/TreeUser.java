package com.example.project;

public class TreeUser {
	public static <T> int countLeaves(BT<T> bt) {
		// Write the method countLeafs that should return the number of leaf nodes in
		// the tree. A leaf node is a node that has no children.
		// assume the following method exists in the ADT: isLeaf (boolean flag):
		// requires: Binary tree is not empty.
		// input: None. results: if the current node of the binary tree is a leaf then
		// flag is set to true otherwise it is set to false. output: flag.
		// you can't call countLeaves from BT.java

		if (bt.empty())
			return 0;

		bt.find(Relative.Root);

		return countLeavesHelper(bt);
	}

	private static <T> int countLeavesHelper(BT<T> bt) {
		if (bt.isLeaf())
			return 1;

		int count = 0;
		if (bt.find(Relative.LeftChild)) {
			count += countLeavesHelper(bt);
			bt.find(Relative.Parent);
		}
		if (bt.find(Relative.RightChild)) {
			count += countLeavesHelper(bt);
			bt.find(Relative.Parent);
		}
		return count;
	}
}