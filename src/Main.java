import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import dsa.iface.IBinarySearchTree;
import dsa.iface.IIterator;
import dsa.iface.INode;
import dsa.impl.AVLTree;
import dsa.impl.BinarySearchTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
   
   public static void main(String[] args ) {
      // Replace this with your code to test your implementations.
      // This just an example of one simple test for a Splay Tree.
      IBinarySearchTree<Integer> st = new SplayTree<Integer>();
      BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
      
      //2.Complex Test
      bst.insert(10);
      st.insert( 10 );
      
      splayCompareStructure((SplayTree<Integer>) st, bst);
      
      st.insert( 20 );
      bst.insert(20);
      
      st.insert( 40 );
      bst.insert(40);
      
      
      st.remove( 20 );
      bst.remove(20);
      
      st.contains( 10 );
      st.contains( 30 );
      
      st.insert( 30 );
      bst.insert(30);
      
      st.insert( 80 );
      bst.insert(80);
      
      st.insert( 20 );
      bst.insert(20);
      
      splayCompareStructure((SplayTree<Integer>) st, bst);

      //3.More complex test
	   splayTreeTest("test2.txt");
	   splayTreeTest("test3.txt");
   }
   
   //1.Every column start with an operation char and then the number
   //2.If a column start with '%', it means this column is a comment
   //This is for splay tree
   public static void splayTreeTest(String fileName) {
	   IBinarySearchTree<Integer> st = new SplayTree<Integer>();
	   try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
		   System.out.println("------------------------------");
		   System.out.println(fileName);
		   System.out.println("------------------------------");
		   String line = br.readLine();
		   while(line != null) {
			   char operation = line.charAt(0);
			
			   if(operation == '%') {
				   line = br.readLine();
			   }else {
				   StringBuilder strBuilder = new StringBuilder(line);
				   strBuilder.delete(0, 1);
				   String str = strBuilder.toString();
				   int number = Integer.parseInt(str);
				   
				   if(operation == 'I') {
					   st.insert(number);
				   }else if(operation == 'R') {
					   st.remove(number);
				   }else if(operation == 'C') {
					   st.contains(number);
				   }
				   TreePrinter.printTree( st );
				   System.out.println("------------------------------");
				   line = br.readLine();
			   }
		   }
		   
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
   }
   
   //1.Every column start with an operation char and then the number
   //2.If a column start with '%', it means this column is a comment
   //This is for AVL tree
   public static void AVLTreeTest(String fileName) {
	   IBinarySearchTree<Integer> at = new AVLTree<Integer>();
	   try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
		   System.out.println("------------------------------");
		   System.out.println(fileName);
		   System.out.println("------------------------------");
		   String line = br.readLine();
		   while(line != null) {
			   char operation = line.charAt(0);
			
			   if(operation == '%') {
				   line = br.readLine();
			   }else {
				   StringBuilder strBuilder = new StringBuilder(line);
				   strBuilder.delete(0, 1);
				   String str = strBuilder.toString();
				   int number = Integer.parseInt(str);
				   
				   if(operation == 'I') {
					   at.insert(number);
				   }else if(operation == 'R') {
					   at.remove(number);
				   }else if(operation == 'C') {
					   at.contains(number);
				   }
				   TreePrinter.printTree( at );
				   System.out.println("------------------------------");
				   line = br.readLine();
			   }
		   }
		   
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
   }
   
   //Judge if two BST are same structure
   public static boolean areSameBST(IBinarySearchTree<Integer> tree1, IBinarySearchTree<Integer> tree2) {
	   IIterator<INode<Integer>> it1 = tree1.nodes();
	   IIterator<INode<Integer>> it2 = tree2.nodes();
	   
	   while(it1.hasNext() && it2.hasNext()) {
		   INode<Integer> i1 = it1.next();
		   INode<Integer> i2 = it2.next();
		   if(i1.element() != i2.element()) {
			   return false;
		   }
	   }
	   if(it1.hasNext() || it2.hasNext()) {
		   return false;
	   }
	   return true;
   }
   
   //Comare Splay tree with BST
   public static void splayCompareStructure(SplayTree<Integer> st, BinarySearchTree<Integer> bst) {
	   System.out.println("------------------------------");
	   
	   System.out.println("splayTree");
	   TreePrinter.printTree(st);
	   System.out.println("BST");
	   TreePrinter.printTree(bst);
	 
	   if(areSameBST(st, bst)){
		   System.out.println("These two trees have same Structure!");
	   }
	   else {
		   System.out.println("These two trees have different Structure!");
	   }
   }
   
   //Compare AVL tree with BST
   public static void AVLCompareStructure(AVLTree<Integer> at, BinarySearchTree<Integer> bst) {
	   System.out.println("------------------------------");
	   
	   System.out.println("AVLTree");
	   TreePrinter.printTree(at);
	   System.out.println("BST");
	   TreePrinter.printTree(bst);
	 
	   if(areSameBST(at, bst)){
		   System.out.println("These two trees have same Structure!");
	   }
	   else {
		   System.out.println("These two trees have different Structure!");
	   }
   }
   
}
