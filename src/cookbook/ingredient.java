
public class ingredient {
	private String ingredientName;
	private double ingredientAmount;
	private String unit;
	private String description;
	public ingredient(String ingredientName, float ingredientAmount, String unit, String description) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
		this.description = description;
	}
	
	public ingredient(String ingredientName, float ingredientAmount, String unit) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
	}

	public ingredient(){
		
	}

}
