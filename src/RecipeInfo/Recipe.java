package RecipeInfo;
import java.util.ArrayList;
import java.io.Serializable;

public class Recipe implements Serializable{
	
	private int recipeID;
	private String recipeName;
	private int serveNum;
	private int cookTime;
	private int prepTime;
	//picture?
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();;
	private ArrayList<String> stepList = new ArrayList<String>();
	private String dishType;
	private Category category;
	
	public Recipe(String recipeName, String dishType, int serveNum) {
		this.recipeName = recipeName;
		this.dishType = dishType;
		this.serveNum = serveNum;
	}
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredientList.add(ingredient);
	}
	
	public void addPreparationStep(String stepDescription) {
		this.stepList.add(stepDescription + "\n");
	}
	
	public void setPreparationTime(int prepTime) {
		this.prepTime = prepTime;
	}
	
	public void setCookingTime(int cookTime) {
		this.cookTime = cookTime;
	}
	
	public void addCategory(Category category) {
		this.category = category;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	public int getServeNum() {
		return serveNum;
	}

	public void setServeNum(int serveNum) {
		this.serveNum = serveNum;
	}
	

	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	

	public ArrayList getStepList() {
		return stepList;
	}

	public void setStepList(ArrayList stepList) {
		this.stepList = stepList;
	}
	

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public String getDishType() {
		return dishType;
	}

	public void setDishType(String dishType) {
		this.dishType = dishType;
	}

	public String toString() {
		String temp1 = "Recipe name: " + recipeName + "\n";
		String temp2 = "serve number: " + serveNum + "\n";
		String temp3 = "cooking time: " + cookTime + "\n";
		String temp4 = "preparation time: " + prepTime + "\n";
		String temp5 = "dish type: " + dishType + "\n";
		String temp6 = "category: " + category.toString() + "\n";		
		String temp7 = "ingrediants: " + "\n" + ingredientList + "\n";
		String temp8 = "steps: " + "\n" + stepList;
		
		return temp1 + temp2 + temp3 + temp4 + temp5 + temp6 + temp7 + temp8;
	}
	
}
