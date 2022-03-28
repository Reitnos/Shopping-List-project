import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;

public class ShoppingList {




	Map<Category,ArrayList<Product>> shoppingList = new HashMap<Category,ArrayList<Product>>();

	
	// this function takes the current shopping list as a parameter which is a hashmap, category of the product and the name of the product as parameters 
	// then simply finds the associated key according to the category, adds the product to that category.
	// it also checks if the item was already in the list, then it simply returns the original hashmap.
	void addNewElementToShoppingList(Product product, Category category){
		
	
		ArrayList<Product> productCategoryList = shoppingList.get(category);
		
			
			if(!productCategoryList.contains(product)) {
				
				productCategoryList.add(product);
			}
		
		
		
	}
	
	// If the shopping list doesn't already contain the category, add the new category with an empty arraylist of product strings.
	void addNewCategoryToShoppingList(Category category){
		
		ArrayList<Product> emptyProductList = new ArrayList<Product>();
					
			if(!shoppingList.containsKey(category)) {
				shoppingList.put(category, emptyProductList);
			}
		
		
		
	}
	
	 void deleteProductFromShoppingList(Product product){
		
		 for(Category category : shoppingList.keySet()) {
			 if(shoppingList.get(category).contains(product)) {
				 shoppingList.get(category).remove(product);
			 }
		 }
			
		/* 
			if(shoppingList.containsKey(product.category)) {
				
				shoppingList.get(product.category).remove(product);
				
			}
			
			*/
	
		
		
	}
	
	//simply deleting the category from the dictionary

	void deleteCategoryFromShoppingList(Category category){
		
	
		shoppingList.remove(category);
	
		
		
		
	}
	
	// this function converts the given txt file to a hashmap (dictionary)
	 void convertTxtToHashMap(File file){
		
		
		String currentCategoryStringRead;
		String[] currentLineSplitToStringArray;
		String[] currentValuesReadList;
		try {
			Scanner scanner = new Scanner(file);
			int categoryIndex = 0;
			int valueListIndex = 1;
			int arrayLengthOfEmptyCategory = 1;
			
			
			while(scanner.hasNextLine() ) { // while there is something to read

				
				currentLineSplitToStringArray = scanner.nextLine().split(":"); // split the key (category) and the values(products)
				
				currentCategoryStringRead= currentLineSplitToStringArray[categoryIndex];
				
				
				Category currentCategory = new Category(currentCategoryStringRead);
				
				
				currentValuesReadList = currentLineSplitToStringArray[valueListIndex].split(","); // line creates a problem only if line is empty
				
				
				ArrayList<Product> valuesAsProductArrayList = new ArrayList<Product>();
				
				createCurrentReadProductsList(currentValuesReadList, currentCategory, valuesAsProductArrayList);
				
				
				checkIfCategoryExistsAndAddProductsToListAccordingly(currentCategory, valuesAsProductArrayList);
				
				
			
				
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}	
		
		
		
	}

	private void createCurrentReadProductsList(String[] currentValuesReadList, Category currentCategory,
			ArrayList<Product> valuesAsProductArrayList) {
		for(String productName : currentValuesReadList) {
			Product productOnList = new Product(productName);
			valuesAsProductArrayList.add(productOnList);
		}
	}

	private void checkIfCategoryExistsAndAddProductsToListAccordingly(Category currentCategory,
			ArrayList<Product> valuesAsProductArrayList) {
		//	ArrayList<String> valuesAsProductArrayList = new ArrayList<String>(Arrays.asList(currentValuesReadList));
		
		// if shoppinglist doesnt already contain the category, add it.
		if(!shoppingList.containsKey(currentCategory)) {
			
			shoppingList.put(currentCategory, valuesAsProductArrayList);
		}
		// else, add the new elements to the existing category.
		else {
			for(Product product : valuesAsProductArrayList)
			{
				
				addNewElementToShoppingList(product,currentCategory);
			}
			
		}
	}
	
	void writeToTxtFile() throws IOException {
		String directoryString = "C:\\Users\\kocak\\Desktop\\ShoppingList.txt";
		File file = new File(directoryString);
		FileWriter fWriter = new FileWriter(file);
		PrintWriter pWriter = new PrintWriter(fWriter);
	
		for (Category category: shoppingList.keySet()) {
			String productsString = "";
			for(Product product : shoppingList.get(category)) {
				
			    productsString +=	product.name + "," ;
				
			}
			pWriter.println(category.name + ":" + productsString);
		
		   
		}
		pWriter.close();
	}
	

	void printInitialStateOfList(File file) {
		String contentString = "";
		
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) { 
				contentString = contentString.concat(scanner.nextLine() + "\n");
				
			}
			
			System.out.println("Initial txt:\n" + contentString);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	/* 
	Mycats: Miyavhav
Hotdogs: aha,ehe,ihi
Mydogs: Hirr,himm
	 */


}
