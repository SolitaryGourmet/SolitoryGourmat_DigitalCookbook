package solitoryGourmet_DigitalCookbook;

import java.util.ArrayList;

public class CookBook 
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
		recipe.setRecipeId(recipeList.size() + 1);
		this.recipeList.add(recipe);
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
					double oldIngredientAmount = this.recipeList.get(i).getIngredientList().get(j).getIngredientAmount();
					this.recipeList.get(i).getIngredientList().get(j).setIngredientAmount(oldIngredientAmount * newServeNum / oldServeNum);
				}
			}
		}
	}
}
