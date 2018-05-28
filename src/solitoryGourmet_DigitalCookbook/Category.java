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
	private String city = "";
	private String taste = "";
	private String mealtime = "";
	private String meat = "";
	private boolean vegetarian;

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

	public String toString()
	{
		return city + "," + taste + "," + mealtime + "," + meat + "," + vegetarian;
	}

}