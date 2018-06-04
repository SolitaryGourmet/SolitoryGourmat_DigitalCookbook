package solitoryGourmet_DigitalCookbook;

import java.sql.*;

public class DataBaseControl
{

	public static Recipe selectbyRecipeName(String recipeName)
	{
		Connection con = getConnection();
		Recipe recipe = new Recipe();
		try
		{
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM recipe where recipeName = recipeName");
			ResultSet rset =  stmt.executeQuery(); 
			while(rset.next())
			{
				recipe.setRecipeId(rset.getInt("recipeID"));
				recipe.setCookingTime(rset.getInt("cookingTime"));
				recipe.setPreparationTime(rset.getInt("preparationTime"));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe; 
	}
	
	
	public static void insert(Recipe recipe)
	{

		try
		{
			
			
			Connection con = getConnection();
			PreparedStatement insertRecipe = con.prepareStatement("insert into Recipe(recipeName, serveNum, cookingTime, preparationTime) value(?,?,?,?)");
			insertRecipe.setString(1, recipe.getRecipeName());
			insertRecipe.setInt(2, recipe.getServeNum());
			insertRecipe.setInt(3, recipe.getCookingTime());
			insertRecipe.setInt(4, recipe.getPreparationTime());
			insertRecipe.executeUpdate();

			
			int recipeID = selectbyRecipeName(recipe.getRecipeName()).getRecipeId();
			
			PreparedStatement insertStep = con.prepareStatement("insert into Step(recipeID,stepOrder, stepDescription) value(?,?,?)");
			for(int i = 0;i<recipe.getStepList().size();i++)
			{
				insertStep.setInt(1, recipeID);
				insertStep.setInt(2, i+1);
				insertStep.setString(3, recipe.getStepList().get(i));
				insertStep.executeUpdate();
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("data inserted");
		}
	}

	public static void createTable()
	{

		try
		{
			Connection con = getConnection();
			PreparedStatement createStep = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Step(recipeID int not null auto_increment, stepOrder int not null, stepDescription LONGTEXT, PRIMARY KEY(recipeID,stepOrder))");
			PreparedStatement createRecipe = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Recipe(recipeID int not null auto_increment, recipeName LONGTEXT, serveNum int, cookingTime int, preparationTime int, picture LONGTEXT, PRIMARY KEY(recipeID))");

			createStep.executeUpdate();
			createRecipe.executeUpdate();
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Table created");
		}
	}

	public static Connection getConnection()
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?&useSSL=false", "root",
					"961211");

			System.out.println("Connected");
			return con;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
