import java.util.ArrayList;

public class Recipe {
	int recipeId;
	private String recipeName;
	private int serveNum;
	private int cookTime;
	private int prepTime;
	private String pictureUrl;//use url to read the path??????
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<Step> stepList = new ArrayList<Step>();
	private ArrayList<Category> category = new ArrayList<Category>();
	private String dishType;//missing from pic
	
	public Recipe(String recipeName, String dishType, int serveNum) {
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.dishType = dishType;
	}

	public Recipe(){
	}
	
	public void addIngredient(Ingredient input){
		this.ingredientList.add(input);
	}
	
	public void addPreparationStep(Step input){
		this.stepList.add(input);
	}
	
	public void setPreparationTime(int prepTime){
		this.prepTime = prepTime;
	}
	
	public void setCookingTime(int cookingTime){
		this.cookTime = cookingTime;
		}
	
	public void addCategory(Category input){
		this.category.add(input);
	}
	
}