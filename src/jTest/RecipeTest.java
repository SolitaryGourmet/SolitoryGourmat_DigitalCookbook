package jTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessLayer.Category;
import businessLayer.Recipe;

public class RecipeTest
{
	private static Recipe newRecipe1 = null;
	private static Recipe newRecipe2 = null;

	@Before
	public void setUp() throws Exception
	{
		newRecipe1 = new Recipe();
		newRecipe1.setRecipeName("testrecipe1");
		newRecipe1.setCookingTime(60);
		newRecipe1.setPreparationTime(30);
		newRecipe1.setPhotoRoute("E:\\College\\Courseware\\software engineering II\\Digital cook book - Solitary Gourmet\\food-icons-1.jpg");
		newRecipe1.setServeNum(2);
		newRecipe1.setCategory(new Category("Shanghai", "salty", "500", "pork", false));
		newRecipe1.setDishType("DishType1");

		newRecipe2 = new Recipe();
		newRecipe2.setRecipeName("testrecipe2");
		newRecipe2.setCookingTime(50);
		newRecipe2.setPreparationTime(25);
		newRecipe2.setPhotoRoute("E:\\College\\Courseware\\software engineering II\\Digital cook book - Solitary Gourmet\\food-icons-1.jpg");
		newRecipe2.setServeNum(4);
		newRecipe2.setCategory(new Category("Hangzhou", "sweet", "600", "chicken", false));
		newRecipe2.setDishType("DishType2");
	}

	@Test
	public void testToString()
	{
		assertNotNull(newRecipe1.toString());
		assertNotNull(newRecipe2.toString());
	}

}
