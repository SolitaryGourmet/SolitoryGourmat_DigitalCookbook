package jTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessLayer.Ingredient;

public class IngredientTest
{
	Ingredient testIngredient1;
	Ingredient testIngredient2;

	@Before
	public void setUp() throws Exception
	{
		testIngredient1 = new Ingredient("testIngredient1", 1, "tablespoon", "test1");
		testIngredient2 = new Ingredient("testIngredient2", 2, "tablespoon", "test2");
	}

	@Test
	public void testToString()
	{
		assertNotNull(testIngredient1.toString());
		assertNotNull(testIngredient2.toString());
	}

}
