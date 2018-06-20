/**
 * CookBook.java
 * create a object of digital cookbook
 * @author LYF
 */

package solitoryGourmet_DigitalCookbook;

import java.io.Serializable;
import java.util.ArrayList;

import businessLayer.Ingredient;
import businessLayer.Recipe;
import businessLayer.RecipeControl;

public class CookBook implements Serializable
{
	private String cookBookName;
	private ArrayList<Recipe> recipeList;

	public CookBook(String cookBookName)
	{
		this.cookBookName = cookBookName;
		recipeList = new ArrayList<Recipe>();
	}

	public void add(Recipe recipe)
	{
		RecipeControl.addRecipeToDataBase(recipe);
	}

	public Recipe getRecipe(String recipeName)
	{
		Recipe recipe = null;
		for (int i = 0; i < recipeList.size(); i++)
		{
			if (recipeName == this.recipeList.get(i).getRecipeName())
			{
				recipe = this.recipeList.get(i);
			}
		}
		return recipe;
	}

	
	/**
	 * changeServeNum
	 * directly change the ingredient amount via new serve number
	 * @param recipeName
	 * @param newServeNum
	 */
	public void changeServeNum(String recipeName, int newServeNum)
	{
		for (int i = 0; i < recipeList.size(); i++)
		{
			if (recipeName == this.recipeList.get(i).getRecipeName())
			{
				int oldServeNum = this.recipeList.get(i).getServeNum();
				this.recipeList.get(i).setServeNum(newServeNum);
				for (int j = 0; j < this.recipeList.get(i).getIngredientList().size(); j++)
				{
					double oldIngredientAmount = this.recipeList.get(i).getIngredientList().get(j)
							.getIngredientAmount();
					this.recipeList.get(i).getIngredientList().get(j)
							.setIngredientAmount(oldIngredientAmount * newServeNum / oldServeNum);
				}
			}
		}
	}

	/**
	 * changeServeNum
	 * create a new Recipe object which represents the changed recipe
	 * @param recipe
	 * @param serveNum
	 * @return Recipe
	 * @author Liu Yanran
	 */
	public Recipe changeServeNum(Recipe recipe, int serveNum)
	{
		int n = recipe.getServeNum();
		recipe.setServeNum(serveNum);
		ArrayList<Ingredient> ingredientList = recipe.getIngredientList();
		int i;
		for (i = 0; i < ingredientList.size(); i++)
		{
			double ingredientAmount = ingredientList.get(i).getIngredientAmount();
			double ingredientAmountUnit = ingredientAmount / n;
			double ingredientAmountNew = ingredientAmountUnit * serveNum;
			ingredientList.get(i).setIngredientAmount(ingredientAmountNew);
		}
		recipe.setIngredientList(ingredientList);
		return recipe;
	}

}
