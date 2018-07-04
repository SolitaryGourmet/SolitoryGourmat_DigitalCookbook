package jTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessLayer.Category;

public class CategoryTest
{
	private Category newCategory1 = null;
	private Category newCategory2 = null;
	
	@Before
	public void setUp() throws Exception
	{
		newCategory1 = new Category("Nanjing", "salty", "100", "pork", false);
		newCategory2 = new Category("Beijing", "salty", "101", "chicken", false);
	}

	@Test
	public void testToString()
	{
		assertNotNull(newCategory1.toString());
		assertNotNull(newCategory2.toString());
	}

}
