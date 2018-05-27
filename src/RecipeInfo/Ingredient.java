package RecipeInfo;
import java.io.Serializable;

public class Ingredient implements Serializable{
	
	private String ingredientName;
	private double ingredientAmount;
	private String unit;
	private String description;
	
	public Ingredient(String ingredientName, double ingredientAmount, String unit) {
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
		this.description = " ";
	}
	
	public Ingredient(String ingredientName, double ingredientAmount, String unit, String description) {
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
		this.description = description;
	}

	public double getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(double ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}
	public String toString(){
		return  ingredientName + " " + ingredientAmount + " " + unit + " " + description + "\n";
		
	}
	
}
