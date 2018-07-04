package jTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import businessLayer.Category;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import databaseLayer.DataBaseControl;

public class DataBaseAccessTest
{
	static Recipe testRecipe;

	@Before
	public void setUp() throws Exception
	{
		testRecipe = new Recipe("Gong Bao Jiding", "Sichuan Dish", 4);
		testRecipe.addIngredient(new Ingredient("cornstarch", 1.0, "tablespoon"));
		testRecipe.addIngredient(new Ingredient("soy sauce", 4.0, "tablespoon"));
		testRecipe.addPreparationStep("Mix together cornstarch and 1 tbsp. of the soy sauce in a medium bowl.");
		testRecipe.addPreparationStep("Add chicken, toss well, and set aside to marinate for 30 minutes.");
		testRecipe.setCategory(new Category("Sichuan", "salty ", "lunch dinner ", "chicken ", false));
		testRecipe.setPhotoRoute("E:\\College\\Courseware\\software engineering II\\Digital cook book - Solitary Gourmet\\food-icons-1.jpg");
		testRecipe.setPreparationTime(30);
		testRecipe.setCookingTime(10);
	}

	@Test
	public void testGetConnection()
	{
		assertTrue(DataBaseControl.getConnection());
	}

	@Test
	public void testCreateTable()
	{
		DataBaseControl.getConnection();
		assertTrue(DataBaseControl.createTable());
		DataBaseControl.closeConnection();
	}
	
	@Test
	public void testInsertRecipe()
	{
		DataBaseControl.getConnection();
		assertTrue(DataBaseControl.insertRecipe(testRecipe));
		DataBaseControl.closeConnection();
	}
	
	@Test
	public void testSearchByCategory()
	{
		DataBaseControl.getConnection();
		assertNotSame(0,DataBaseControl.searchByCategory(testRecipe.getCategory()).size());
		DataBaseControl.closeConnection();
	}

	@Test
	public void testSearchByName()
	{
		DataBaseControl.getConnection();
		assertNotSame(0,DataBaseControl.searchByName(testRecipe.getRecipeName()).size());
		DataBaseControl.closeConnection();
	}

	@Test
	public void testEditRecipe()
	{
		DataBaseControl.getConnection();
		assertTrue(DataBaseControl.editRecipe(testRecipe));
		DataBaseControl.closeConnection();
	}

	@Test
	public void testDeleteRecipe()
	{
		DataBaseControl.getConnection();
		assertTrue(DataBaseControl.deleteRecipe(testRecipe));
		DataBaseControl.closeConnection();
	}

	@Test
	public void testCloseConnection()
	{
		DataBaseControl.getConnection();
		assertTrue(DataBaseControl.closeConnection());
	}
}
