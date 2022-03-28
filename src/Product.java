
public class Product {
	String name = "";
	//Category category;
	
	Product(String name){
		this.name = name;
	//	this.category = category;
	}
	
	@Override
	public boolean equals(Object o){
		  if(o instanceof Product){
		   Product other = (Product) o;
		    return this.name.equals(other.name);
		 
		  }	
		  return false;
	}
	 @Override
	    public int hashCode() {
	        
	        return name.hashCode();
	    }
}
