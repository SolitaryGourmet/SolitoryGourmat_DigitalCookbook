/**
 * Recipe.java
 * providing all necessary attributes and methods for Recipe
 * @author LYF
 */
package businessLayer;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable
{
	private int recipeId;
	private String recipeName;
	private int serveNum;
	private int cookingTime;
	private int preparationTime;
	private Image picture;// use url to read the path??????
	private ArrayList<Ingredient> ingredientList;
	private ArrayList<String> stepList;
	private Category category;
	private String dishType;// missing from pic

	
	//all getters and setters
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

	public int getCookingTime()
	{
		return cookingTime;
	}

	public void setCookingTime(int cookingTime)
	{
		this.cookingTime = cookingTime;
	}

	public int getPreparationTime()
	{
		return preparationTime;
	}

	public void setPreparationTime(int preparationTime)
	{
		this.preparationTime = preparationTime;
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

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	
	
	/**
	 * constructor with input parameters
	 * @param recipeName
	 * @param dishType
	 * @param serveNum
	 */
	public Recipe(String recipeName, String dishType, int serveNum)
	{
		this.recipeName = recipeName;
		this.serveNum = serveNum;
		this.dishType = dishType;
		
		recipeId = 0;
		cookingTime = 0;
		preparationTime = 0;
		picture = null;
		ingredientList = new ArrayList<Ingredient>();
		stepList = new ArrayList<String>();
	}

	/**
	 * default constructor
	 */
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


	public String toString()
	{
		return "recipeId: " + recipeId + "\n" + "recipeName: " + recipeName + "\n" + "dishType: " + dishType + "\n"
				+ "serveNum: " + serveNum + "\n" + "cookTime: " + cookingTime + "\n" + "prepTime: " + preparationTime + "\n"
				+ "category: " + category + "\n" + ingredientList + "\n" + stepList;
	}
}
