import java.util.ArrayList;

public class recipe {
	int recipeId;
	private String recipeName;
	private int serveNum;
	private int cookTime;
	private int prepTime;
	private String picture;//use url to read the path??????
	private ArrayList ingredientList;
	private ArrayList stepList;
	private String dishType;//missing from pic
	
	public recipe(String recipeName, int serveNum, String dishType) {
		super();
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.dishType = dishType;
	}

	public recipe(){
		
	}
	
	public addIngredient(Ingredient ingredient){
		
	}
	
	public addPreparationStep(Step step){
		
	}
	
	public setPreparationTime(int prepTime){
		this.prepTime = prepTime;
		
	}
	
	public
	

}
