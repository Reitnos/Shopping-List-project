import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;


public class ShoppingListSimulator {

	public static void main(String[] args){
			
		
			
			ShoppingList shoppingList = new ShoppingList();
			
			String directoryString = "C:\\Users\\kocak\\Desktop\\ShoppingList.txt";
			File file = new File(directoryString);

			shoppingList.printInitialStateOfList(file);
			
				
			shoppingList.convertTxtToHashMap(file);;
				
				
			Category category1 = new Category("Hotdogs");
			Product product1 = new Product("Apple");
 			shoppingList.addNewElementToShoppingList(product1,category1);
			
	
 			Category category2 = new Category("Mycats");
 			Product product2 = new Product("havhav");
 			Product product3 = new Product("miyavhav");
 				
 			shoppingList.addNewCategoryToShoppingList(category2);
 			shoppingList.addNewElementToShoppingList(product2,category2);
 			shoppingList.addNewElementToShoppingList(product3,category2);
	
 				
 			shoppingList.deleteCategoryFromShoppingList(category2);
 		
 			shoppingList.deleteProductFromShoppingList(product1);
 				
 			Category mydogCategory = new Category("Mydogs");
 			Product productHirr = new Product(" Hirr");
 			shoppingList.deleteProductFromShoppingList(productHirr);
			  
			try {
				shoppingList.writeToTxtFile();
				
			}
			  catch(IOException e){
				  
			 }
		  
	}

}
