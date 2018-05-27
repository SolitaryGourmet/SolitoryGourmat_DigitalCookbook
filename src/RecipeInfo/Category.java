package RecipeInfo;
import java.io.Serializable;

public class Category implements Serializable{
	
	private int categoryID;
	private String city = " ";
	private String taste = " ";
	private String mealtime = " ";
	private String meat = " ";
	private boolean vegetarian;
	
	public Category(String city, String taste, String mealtime, String meat, boolean vegetarian) {
		this.city = city;
		this.taste = taste;
		this.mealtime = mealtime;
		this.meat = meat;
		this.vegetarian = vegetarian;
	}
	public String toString() {
		return city + "," + taste + "," + mealtime + "," + meat + "," + vegetarian;
	}
	
}
