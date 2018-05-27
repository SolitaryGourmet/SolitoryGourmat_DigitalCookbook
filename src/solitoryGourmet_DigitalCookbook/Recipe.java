package solitoryGourmet_DigitalCookbook;

import java.awt.Image;
import java.util.ArrayList;

public class Recipe
{
	private int recipeId;
	private String recipeName;
	private int serveNum;
	private int cookTime;
	private int prepTime;
	private Image picture;// use url to read the path??????
	private ArrayList<Ingredient> ingredientList;
	private ArrayList<String> stepList;
	private String dishType;// missing from pic

	public int getRecipeId()
	{
		return recipeId;
	}

	public void setRecipeId(int recipeId)
	{
		this.recipeId = recipeId;
	}

	public String getRecipeName()
	{
		return recipeName;
	}

	public void setRecipeName(String recipeName)
	{
		this.recipeName = recipeName;
	}

	public int getServeNum()
	{
		return serveNum;
	}

	public void setServeNum(int serveNum)
	{
		this.serveNum = serveNum;
	}

	public int getCookTime()
	{
		return cookTime;
	}

	public void setCookTime(int cookTime)
	{
		this.cookTime = cookTime;
	}

	public int getPrepTime()
	{
		return prepTime;
	}

	public void setPrepTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	public Image getPicture()
	{
		return picture;
	}

	public void setPicture(Image picture)
	{
		this.picture = picture;
	}

	public ArrayList<Ingredient> getIngredientList()
	{
		return ingredientList;
	}

	public void setIngredientList(ArrayList<Ingredient> ingredientList)
	{
		this.ingredientList = ingredientList;
	}

	public ArrayList<String> getStepList()
	{
		return stepList;
	}

	public void setStepList(ArrayList<String> stepList)
	{
		this.stepList = stepList;
	}

	public String getDishType()
	{
		return dishType;
	}

	public void setDishType(String dishType)
	{
		this.dishType = dishType;
	}

	public Recipe(String recipeName, String dishType, int serveNum)
	{
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.dishType = dishType;

		recipeId = 0;
		cookTime = 0;
		prepTime = 0;
		picture = null;
		ingredientList = new ArrayList<Ingredient>();
		stepList = new ArrayList<String>();

	}

	public Recipe()
	{

	}

	public void addIngredient(Ingredient ingredient)
	{
		this.ingredientList.add(ingredient);
	}

	public void addPreparationStep(String step)
	{
		this.stepList.add("\n" + step);
	}

	public void setPreparationTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	public void setCookingTime(int cookTime)
	{
		this.cookTime = cookTime;
	}

	public String toString()
	{
		return "recipeId: " + recipeId + "\n" + "recipeName: " + recipeName + "\n" + "dishType: " + dishType + "\n"
				+ "serveNum: " + serveNum + "\n" + "cookTime: " + cookTime + "\n" + "prepTime: " + prepTime + "\n"
				+ ingredientList + "\n" + stepList;
	}
}
