public class Category {
	public int categoryId;
	private String country;
	private String taste;
	private String mealTime;
	private String meat;
	private boolean vegetarian;

	public void category(int categoryId, String country, String taste, String mealTime, String meat, boolean vegetarian) {
		
		this.categoryId = categoryId;
		this.country = country;
		this.taste = taste;
		this.mealTime = mealTime;
		this.meat = meat;
		this.vegetarian = vegetarian;
	}
	public void category(){
		
	}

}
