package businessLayer;

import java.util.ArrayList;

import databaseLayer.DataBaseControl;

public class RecipeControl
{
	public static void showEditRecipeForm(Recipe recipe)
	{
		
	}
	
	public static void showSearchResultForm(ArrayList<Recipe> recipeList)
	{
		
	}
	
	public static void displayRecipe(Recipe recipe)
	{
		
	}
	
	public static void addRecipeToDataBase(Recipe recipe)
	{
		DataBaseControl.insertRecipe(recipe);
	}
	
	public static void editRecipeFromDataBase(Recipe recipe)
	{
		
	}
	
	public static void deleteRecipeFromDataBase(Recipe recipe)
	{
		DataBaseControl.deleteRecipe(recipe);
	}
}
