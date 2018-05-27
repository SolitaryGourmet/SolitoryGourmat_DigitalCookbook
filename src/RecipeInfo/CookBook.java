package RecipeInfo;
import java.util.ArrayList;
import java.io.Serializable;

public class CookBook implements Serializable{
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	Recipe recipe;
	public CookBook(String cookBookName) {
		
	}
	
	public void add(Recipe recipe) {
		this.recipeList.add(recipe);
	}
	
	/**
	 * search for a recipe in the cook book when you already have the title of the recipe
	 * @param recipeName
	 * @return the recipe with the searching name
	 */
	public Recipe getRecipe(String recipeName) {
		int i;
		for(i=0; i<recipeList.size(); i++) {
			if(recipeList.get(i).getRecipeName().equals(recipeName)) {
				recipe = recipeList.get(i);
			}
		}
		return recipe;
	}
	
	/**
	 * recalculates the servings
	 * @param recipe
	 * @param serveNum
	 * @return the recipe with the new serve number
	 */
	public Recipe recalculateServeNum(Recipe recipe, int serveNum) {
		int n = recipe.getServeNum();
		recipe.setServeNum(serveNum);
		ArrayList<Ingredient> ingredientList = recipe.getIngredientList();
		int i;
		for(i=0; i<ingredientList.size(); i++) {
			double ingredientAmount = ingredientList.get(i).getIngredientAmount();
			double ingredientAmountUnit =ingredientAmount / n;
			double ingredientAmountNew = ingredientAmountUnit * serveNum;
			ingredientList.get(i).setIngredientAmount(ingredientAmountNew);
		}
		recipe.setIngredientList(ingredientList);
		return recipe;
	}
}
