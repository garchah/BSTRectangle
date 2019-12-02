//Deletes
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

public class TestBST {

	 public static void main(String[] args) {
		 
		 //Get 10 integers from the user
		    Integer[] numbers = new Integer[10];
		    System.out.println("Please enter 10 integers: ");
		    Scanner input = new Scanner(System.in);
		    
		    for (int i=0; i<numbers.length;i++)
		    {
		    	numbers[i] = input.nextInt();
		    }
		    
		    //Add them to the tree
		    BST<Integer> tree = new BST<Integer>();
		    for(Integer n: numbers) tree.insert(n);
		    
		    //Delete first integer
		    tree.delete(numbers[0]);
		     
		    //Display paths
		    for(int i=0; i<numbers.length; i++) {
		    	if(tree.isLeaf(numbers[i])) {
		    		System.out.println(tree.getPath(numbers[i]));
		    	}
		    }
		    BST<Rectangle> rectangle = new BST<Rectangle>();
		    Integer[] area = new Integer[10];
		    
		    for(int i=1; i<=10;i++) {
		    System.out.println("Enter width of Rectangle " + i +": ");
		    int width = input.nextInt();
		    System.out.println("Enter height of Rectangle " + i +": ");
		    int height = input.nextInt();
	
		    area[i] = width*height;
		    }
		    for(Integer x: area) rectangle.insert(x);
	 
		    for(int i=0; i<area.length; i++) {
		    	if(rectangle.isLeaf(area[i])) {
		    		System.out.println(rectangle.getPath(area[i]));
		    	}
		    }
	 }
}
		    
		    

