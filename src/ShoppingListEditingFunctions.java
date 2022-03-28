
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

// you can implement a null space deleter function at the end.

public class ShoppingListEditingFunctions {
	
	// this function takes the current shopping list as a parameter which is a hashmap, category of the product and the name of the product as parameters 
	// then simply finds the associated key according to the category, adds the product to that category.
	// it also checks if the item was already in the list, then it simply returns the original hashmap.
	static Map<String, ArrayList<String>> addNewElementToShoppingList(String productCategory, String productName, Map<String,ArrayList<String>> shoppingList){
		
	
		ArrayList<String> productCategoryList = shoppingList.get(productCategory);
		
			
			if(!productCategoryList.contains(productName)) {
				
				productCategoryList.add(productName);
			}
		
		
		return shoppingList;
	}
	
	// If the shopping list doesn't already contain the category, add the new category with an empty arraylist of product strings.
	static Map<String, ArrayList<String>> addNewCategoryToShoppingList(String productCategory, Map<String,ArrayList<String>> shoppingList){
		
		ArrayList<String> emptyProductCategoryList = new ArrayList<String>();
					
			if(!shoppingList.containsKey(productCategory)) {
				shoppingList.put(productCategory, emptyProductCategoryList);
			}
		
		
		return shoppingList;
	}
	
	static Map<String, ArrayList<String>> deleteProductFromShoppingList(String productCategory, String productName, Map<String,ArrayList<String>> shoppingList){
		
		ArrayList<String> ProductCategoryList = new ArrayList<String>(); // not sure if this works fine
					
			if(shoppingList.containsKey(productCategory)) {
				
				shoppingList.get(productCategory).remove(productName);
				
			}
	
		
		return shoppingList;
	}
	
	//simply deleting the category from the dictionary

	static Map<String, ArrayList<String>> deleteCategoryFromShoppingList(String productCategory, Map<String,ArrayList<String>> shoppingList){
		
	
		shoppingList.remove(productCategory);
		
		
		
		return shoppingList;
	}
	
	// this function converts the given txt file to a hashmap (dictionary)
	static Map<String, ArrayList<String>> convertTxtToHashMap(File file){
		
		Map<String,ArrayList<String>> shoppingList = new HashMap<String,ArrayList<String>>();
		String currentCategoryRead;
		String[] currentLineSplitToStringArray;
		String[] currentValuesReadList;
		try {
			Scanner scanner = new Scanner(file);
			int categoryIndex = 0;
			int valueListIndex = 1;
			int arrayLengthOfEmptyCategory = 1;
			
			
			while(scanner.hasNextLine() ) { // while there is something to read

				
				currentLineSplitToStringArray = scanner.nextLine().split(":"); // split the key (category) and the values(products)
				currentCategoryRead= currentLineSplitToStringArray[categoryIndex];
				
				if(currentLineSplitToStringArray.length == arrayLengthOfEmptyCategory) { // deleting the empty categories.
					shoppingList =	deleteCategoryFromShoppingList(currentCategoryRead, shoppingList);
					continue;
				}
				currentValuesReadList = currentLineSplitToStringArray[valueListIndex].split(","); // line creates a problem only if line is empty
				
				
				
				
				
				ArrayList<String> valuesAsStringArrayList = new ArrayList<String>(Arrays.asList(currentValuesReadList));
				
				// if shoppinglist doesnt already contain the category, add it.
				if(!shoppingList.containsKey(currentCategoryRead)) {
					
					shoppingList.put(currentCategoryRead, valuesAsStringArrayList);
				}
				// else, add the new elements to the existing category.
				else {
					for(int i = 0 ; i < currentValuesReadList.length; i++) {
						shoppingList = addNewElementToShoppingList(currentCategoryRead, valuesAsStringArrayList.get(i) , shoppingList);
					}
					
				}
				
				
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}	
		
		
		return shoppingList;
	}
	
	static void writeToTxtFile( Map<String,ArrayList<String>> shoppingList) throws IOException {
		String directoryString = "C:\\Users\\kocak\\Desktop\\ShoppingList.txt";
		File file = new File(directoryString);
		FileWriter fWriter = new FileWriter(file);
		PrintWriter pWriter = new PrintWriter(fWriter);
	
		for (String category: shoppingList.keySet()) {
			String products = "";
			for(String product : shoppingList.get(category)) {
				
			    products +=	product + "," ;
				
			}
			pWriter.println(category + ":" + products);
		
		   
		}
		pWriter.close();
	}
	// Hotdogs: a,B,C,
	// Maybe: D,E

	/*
	 * if(currentLineSplitToStringArray.length == arrayLengthOfEmptyCategory) { // deleting the empty categories.
					
					
					deleteCategoryFromShoppingList(currentCategory);
					continue;
				}
	 */
	
}
