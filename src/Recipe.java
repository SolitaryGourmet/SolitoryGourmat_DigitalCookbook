import java.util.ArrayList;
import java.util.List;

public class Recipe {
	int recipeId;
	private String recipeName;
	private int serveNum;
	private int cookTime;
	private int prepTime;
	private String picture;//use url to read the path??????
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList stepList;
	private String dishType;//missing from pic
	private int ingredientCount = 0;
	private int stepCount = 0;
//	private int ingredientCount = 0;
	
	public Recipe(String recipeName, int serveNum, String dishType) {
		super();
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.dishType = dishType;
	}

	public Recipe(){
		
	}
	
	public void addIngredient(Ingredient input){
//		Ingredient ingredientList(ingredientCount) = new Ingredient();
		this.ingredientList.add(input);
		
		ingredientCount++;
		}
	
	public void addPreparationStep(Step step(stepCount)){
		stepCount++;
	}
	
	public setPreparationTime(int prepTime){
		this.prepTime = prepTime;
		
	}
	
	public
	

}