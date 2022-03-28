
public class Category {
	String name = "";
	Category(String name){
		this.name = name;
	}
	@Override
	public boolean equals(Object o){
		  if(o instanceof Category){
		    Category other = (Category) o;
		    return this.name.equals(other.name);
		 
		  }	
		  return false;
	}
	 @Override
	    public int hashCode() {
	        
	        return name.hashCode();
	    }
}
