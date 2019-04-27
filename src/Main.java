import java.io.BufferedReader;
import java.io.FileReader;

import dsa.iface.IBinarySearchTree;
import dsa.impl.SplayTree;
import dsa.util.TreePrinter;

public class Main {
   
   public static void main(String[] args ) {
      // Replace this with your code to test your implementations.
      // This just an example of one simple test for a Splay Tree.
//      IBinarySearchTree<Integer> st = new SplayTree<Integer>();
//      st.insert( 10 );
//      st.insert( 20 );
//      st.insert( 40 );
//      st.remove( 20 );
//      st.contains( 10 );
//      st.contains( 30 );w
//      st.insert( 30 );
//      st.insert( 80 );
//      st.insert( 20 );
//      
//      TreePrinter.printTree( st );
	   splayTreeTest("test2.txt");
	   splayTreeTest("test3.txt");
   }
   
   //1.Every column start with an operation char and then the number
   //2.If a column start with '%', it means this column is a comment
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
}
