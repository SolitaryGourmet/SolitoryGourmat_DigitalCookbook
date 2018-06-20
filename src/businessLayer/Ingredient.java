/**
 * Ingredient.java
 * providing all necessary attributes for Ingredient class
 * @author LYF
 */
package businessLayer;

import java.io.Serializable;

public class Ingredient implements Serializable
{
	private String ingredientName;
	private double ingredientAmount;
	private String unit;
	private String description = "";

	public String getIngredientName()
	{
		return ingredientName;
	}

	public void setIngredientName(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}

	public double getIngredientAmount()
	{
		return ingredientAmount;
	}

	public void setIngredientAmount(double ingredientAmount)
	{
		this.ingredientAmount = ingredientAmount;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Ingredient(String ingredientName, double ingredientAmount, String unit, String description)
	{
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
		this.description = description;
	}

	public Ingredient(String ingredientName, double ingredientAmount, String unit)
	{
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.unit = unit;
	}

	public Ingredient()
	{

	}

	public String toString()
	{
		return ingredientName + ", " + ingredientAmount + " " + unit + " " + description + "\n";
	}
}
