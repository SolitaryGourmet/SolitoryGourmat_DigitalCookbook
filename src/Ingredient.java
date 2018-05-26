public class Ingredient {
	private String ingredientName;
	private double ingredientAmount;
	private String unit;
	private String description;
	public Ingredient(String ingredientName, float ingredientAmount, String unit, String description) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
		this.description = description;
	}
	
	public Ingredient(String ingredientName, double ingredientAmount, String unit) {
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
	}

	public Ingredient(){
		
	}
	
}