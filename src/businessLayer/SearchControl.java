package businessLayer;

import java.util.ArrayList;

import databaseLayer.DataBaseControl;

public class SearchControl
{
	public static ArrayList<Recipe> searchName(String recipeName)
	{		
		return DataBaseControl.searchByName(recipeName);
	}
	
	public static ArrayList<Recipe> searchCategory(Category category)
	{
		return DataBaseControl.searchByCategory(category);
	}
}
