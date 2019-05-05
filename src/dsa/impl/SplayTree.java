package dsa.impl;

import dsa.iface.INode;
import dsa.impl.AbstractBinaryTree.BTNode;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	
	public SplayTree() {
		root = newNode(null, null);
		BTNode n1 = newNode(null, root);
		BTNode n2 = newNode(null, root);
		root.right = n2;
		root.left = n1;
	}

   public void insert( T value ) {
      // TODO: Implement the insert(...) method.
	  if(root.element() == null) {
		  replace(root, value);
	  }
	  else {
		  BTNode node = (BTNode) findNode(root, value);
		  if(isExternal(node)) {
			  INode<T> nParent = parent(node);
			  node = newNode(value, (BTNode)nParent);
			  BTNode n1 = newNode(null, node);
			  BTNode n2 = newNode(null, node);
			  node.right = n2;
			  node.left = n1;
			  splay(node);
		  }
		  else {
			  System.out.println("This value has already exist");
		  }
	  }
   }

   public boolean contains( T value ) {
      // TODO: Implement the contains(...) method.
	  BTNode node = (BTNode) findNode(root, value);
	  if(isExternal(node)) {
		  splay(parent(node));
		  return false;
	  }else {
		  splay(node);
		  return true;
	  }
   }

   public void remove( T value ) {
      // TODO: Implement the remove(...) method.
	   BTNode node = (BTNode) findNode(root, value);
	   if(isExternal(node)) {
		   splay(parent(node));
	   }
	   else if(node.element()!=root.element()){
		   BTNode nRight = node.right;
		   BTNode nLeft = node.left;
		   BTNode nParent = node.parent;
		   
		   //Suppose the node we want to remove isn't root
		   if(isExternal(nRight) && isExternal(nLeft)) {
			   BTNode n1 = newNode(null, nParent);
			   if(node.element().compareTo(nParent.element()) > 0) {
				   nParent.right = n1;
			   }
			   else {
				   nParent.left = n1;
			   }
			   splay(nParent);
		   }
		   else if(!isExternal(nRight) && isExternal(nLeft)) {
			   if(node.element().compareTo(nParent.element()) > 0) {
				   nParent.right = nRight;
				   nRight.parent = nParent;
			   }else {
				   nParent.left = nRight;
				   nRight.parent = nParent;
			   }
			   splay(nParent);
		   }
		   else if(isExternal(nRight) && !isExternal(nLeft)) {
			   if(node.element().compareTo(nParent.element()) > 0) {
				   nParent.right = nLeft;
				   nLeft.parent = nParent;
			   }else {
				   nParent.left = nLeft;
				   nLeft.parent = nParent;
			   }
			   splay(nParent);
		   }
		   //两个子都是Internal
		   else {
			   BTNode x = nRight;
			   while(!isExternal(x.left)) {
				   x = x.left;
			   }
			   replace(node, x.element());
			   if(x == nRight) {
				   node.right = newNode(null, node);
				   splay(node);
			   }
			   else {
				   BTNode xParent = x.parent;
				   xParent.left = newNode(null, xParent);
				   splay(xParent);
			   }
		   }
	   }
	   //Suppose the node we want to remove is root
	   else {
		   BTNode nRight = node.right;
		   BTNode nLeft = node.left;
		   if(isExternal(nRight) && isExternal(nLeft)) {
			   root = null;
		   }
		   else if(!isExternal(nRight) && isExternal(nLeft)) {
			   nRight.parent = null;
			   root = nRight;
		   }else if(isExternal(nRight) && !isExternal(nLeft)) {
			   nLeft.parent = null;
			   root = nLeft;
		   }else {
			   BTNode x = nRight;
			   while(!isExternal(x.left)) {
				   x = x.left;
			   }
			   
			   replace(node, x.element());
			   
			   if(x == nRight) {
				   node.right = newNode(null, node);
				   splay(node);
			   }
			   else {
				   BTNode xParent = x.parent;
				   xParent.left = newNode(null, xParent);
				   splay(xParent);
			   }
		   }
	   }
   }

   private void splay( INode<T> n ) {
      // TODO: Implement the splay(...) method.
	  if(isRoot(n)) {
		  return;
	  }else if(isRoot(parent(n))) {
		  zig(n);
	  }
	  else {
		  INode<T> nParent = parent(n);
		  INode<T> nGrandParent = parent(nParent);
		  if((n.element().compareTo(nParent.element()) > 0) == (nParent.element().compareTo(nGrandParent.element())>0)) {
			  zigZig(n);
		  }
		  else {
			  zigZag(n);
		  }
		  splay(n);
	  }
   }
   
   private void zig(INode<T> n ) {
	   BTNode node = (BTNode)n;
	   BTNode nParent = node.parent;
	   node.parent = null;
	   root = node;
	   if(nParent.element().compareTo(node.element()) > 0) {
		   BTNode nRight = node.right;
		   nParent.left = nRight;
		   nRight.parent = nParent;
		   node.right = nParent;
		   nParent.parent = node;
	   }
	   else {
		  BTNode nLeft = node.left;
		  nParent.right = nLeft;
		  nLeft.parent = nParent;
		  node.left = nParent;
		  nParent.parent = node;
	   }
   }
   
   private void zigZig(INode<T> n) {
	   BTNode node = (BTNode)n;
	   BTNode nParent = node.parent;
	   BTNode nGrandParent = nParent.parent;
	   //Do operation on node's grand-grand-parent
	   if(isRoot(nGrandParent)) {
		   root = node;
		   node.parent = null;
	   }else{
		   BTNode nGrandGrandParent = nGrandParent.parent;
		   if(nGrandGrandParent.element().compareTo(nGrandParent.element()) < 0) {
			   nGrandGrandParent.right = node;
			   node.parent = nGrandGrandParent;
		   }else {
			   nGrandGrandParent.left = node;
			   node.parent = nGrandGrandParent; 
		   }
	   }
	   
	   //left-left or right-right
	   if(nParent.element().compareTo(node.element())>0) {
		   BTNode nRight = node.right;
		   BTNode nParentRight = nParent.right;
		   node.right = nParent;
		   nParent.parent = node;
		   nParent.right = nGrandParent;
		   nGrandParent.parent = nParent;
		   nParent.left = nRight;
		   nRight.parent = nParent;
		   nGrandParent.left = nParentRight;
		   nParentRight.parent = nGrandParent;
	   }else {
		   BTNode nLeft = node.left;
		   BTNode nParentLeft = nParent.left;
		   node.left = nParent;
		   nParent.parent = node;
		   nParent.left = nGrandParent;
		   nGrandParent.parent = nParent;
		   nParent.right = nLeft;
		   nLeft.parent = nParent;
		   nGrandParent.right = nParentLeft;
		   nParentLeft.parent = nGrandParent;
	   }
   }
   
   private void zigZag(INode<T> n) {
	   BTNode node = (BTNode)n;
	   BTNode nParent = node.parent;
	   BTNode nGrandParent = nParent.parent;
	   //Do operation on node's grand-grand-parent
	   if(isRoot(nGrandParent)) {
		   root = node;
		   node.parent = null;
	   }else{
		   BTNode nGrandGrandParent = nGrandParent.parent;
		   if(nGrandGrandParent.element().compareTo(nGrandParent.element()) < 0) {
			   nGrandGrandParent.right = node;
			   node.parent = nGrandGrandParent;
		   }else {
			   nGrandGrandParent.left = node;
			   node.parent = nGrandGrandParent; 
		   }
	   }
	   
	   //Do zig-zag
	   BTNode nLeft = node.left;
	   BTNode nRight = node.right;
	   if(nParent.element().compareTo(node.element())>0) {
		   node.right = nParent;
		   nParent.parent = node;
		   node.left = nGrandParent;
		   nGrandParent.parent = node;
		   nParent.left = nRight;
		   nRight.parent = nParent;
		   nGrandParent.right = nLeft;
		   nLeft.parent = nGrandParent;
	   }else {
		   node.left = nParent;
		   nParent.parent = node;
		   node.right = nGrandParent;
		   nGrandParent.parent = node;
		   nParent.right = nLeft;
		   nLeft.parent = nParent;
		   nGrandParent.left = nRight;
		   nRight.parent = nGrandParent;
	   }
   }
   
   private INode<T> findNode(INode<T> n,T e){
	   if(n.element() == e) {
		   return n;
	   }
	   if(isExternal(n)) {
		   System.out.println("is an external node");
		   return n;
	   }
	   else {
		   if(e.compareTo(n.element()) > 0) {
			   return findNode(right(n), e);
		   }
		   else {
			   return findNode(left(n), e);
		   }
	   }
   }
}
