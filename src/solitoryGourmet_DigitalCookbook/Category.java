/**
 * Category.java
 * additional task for digital cookbook: the category of recipe
 * @author Liu Yanran
 */

package solitoryGourmet_DigitalCookbook;

import java.io.Serializable;

public class Category implements Serializable
{

	private int categoryID;
	private String city = new String();
	private String taste = new String();
	private String mealtime = new String();
	private String meat = new String();
	private boolean vegetarian = false;
	
	public int getCategoryID()
	{
		return categoryID;
	}

	public void setCategoryID(int categoryID)
	{
		this.categoryID = categoryID;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getTaste()
	{
		return taste;
	}

	public void setTaste(String taste)
	{
		this.taste = taste;
	}

	public String getMealtime()
	{
		return mealtime;
	}

	public void setMealtime(String mealtime)
	{
		this.mealtime = mealtime;
	}

	public String getMeat()
	{
		return meat;
	}

	public void setMeat(String meat)
	{
		this.meat = meat;
	}

	public boolean isVegetarian()
	{
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian)
	{
		this.vegetarian = vegetarian;
	}

	

	/**
	 * constructor of Category if a specific attribute has no input, then it will be "" 
	 * @param city
	 * @param taste
	 * @param mealtime
	 * @param meat
	 * @param vegetarian
	 */
	public Category(String city, String taste, String mealtime, String meat, boolean vegetarian)
	{
		this.city = city;
		this.taste = taste;
		this.mealtime = mealtime;
		this.meat = meat;
		this.vegetarian = vegetarian;
	}

	public Category()
	{
		
	}
	
	public String toString()
	{
		return city + "," + taste + "," + mealtime + "," + meat + "," + vegetarian;
	}

}