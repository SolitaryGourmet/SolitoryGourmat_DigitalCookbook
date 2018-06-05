package solitoryGourmet_DigitalCookbook;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseControl
{
	/*
	 * public static Recipe selectbyRecipeName(String recipeName) { Connection con =
	 * getConnection(); Recipe recipe = new Recipe(); try { PreparedStatement stmt =
	 * con.prepareStatement("SELECT * FROM recipe where recipeName = recipeName");
	 * ResultSet rset = stmt.executeQuery(); while (rset.next()) {
	 * recipe.setRecipeId(rset.getInt("recipeID"));
	 * recipe.setCookingTime(rset.getInt("cookingTime"));
	 * recipe.setPreparationTime(rset.getInt("preparationTime")); } } catch
	 * (SQLException e) { e.printStackTrace(); } return recipe; }
	 */

	public static Recipe searchByCategory(Category category)
	{
		try
		{
			Recipe recipe = new Recipe();
			String cityt = category.getCity();
			String mealtimet = category.getMealtime();
			String meatt = category.getMeat();
			String tastet= category.getTaste();
			boolean vegetariant = category.isVegetarian();

			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT recipeID FROM recipeCategory where city ="+ "\"" + cityt + "\""+ "and mealtime ="+ "\"" + mealtimet + "\""+ "and meat ="+ "\"" + meatt + "\""+" and taste ="+ "\"" + tastet + "\""+" and vegetarian ="+ "\"" + vegetariant + "\"");

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getInt("recipeId"));
				recipe = searchById(rs.getInt("recipeId"));

			}
			return recipe;

		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static Recipe searchByName(String recipeName)
	{
		int recipeId = 0;
		Recipe recipe = new Recipe();
		try
		{
			Connection con = getConnection();
			PreparedStatement pst = con
					.prepareStatement("select recipeId from recipe where recipeName" + "=" + "\"" + recipeName + "\"");
			ResultSet rs = pst.executeQuery();

			while (rs.next())
			{
				recipeId = rs.getInt("recipeId");
				recipe = searchById(recipeId);

			}
			return recipe;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static Recipe searchById(int recipeId)
	{
		try
		{
			Recipe recipe = new Recipe();
			Connection con = getConnection();

			PreparedStatement pst1 = con.prepareStatement("select * from recipe where recipeId=" + recipeId);
			ResultSet rs1 = pst1.executeQuery();
			while (rs1.next())
			{
				recipe.setRecipeId(rs1.getInt("recipeID"));
				recipe.setRecipeName(rs1.getString("recipeName"));
				recipe.setServeNum(rs1.getInt("serveNum"));
				recipe.setCookingTime(rs1.getInt("cookingTime"));
				recipe.setPreparationTime(rs1.getInt("preparationTime"));
				// no pic
			}
			PreparedStatement pst2 = con.prepareStatement("select * from recipecategory where recipeId =" + recipeId);
			ResultSet rs2 = pst2.executeQuery();

			Category cateTemp = new Category();

			while (rs2.next())
			{

				cateTemp.setCity(rs2.getString("city"));
				cateTemp.setTaste(rs2.getString("taste"));
				cateTemp.setMealtime(rs2.getString("mealtime"));
				cateTemp.setMeat(rs2.getString("meat"));
				cateTemp.setVegetarian(rs2.getBoolean("vegetarian"));

			}

			recipe.setCategory(cateTemp);

			PreparedStatement pst3 = con.prepareStatement("select * from recipeingredient where recipeId =" + recipeId);
			ResultSet rs3 = pst3.executeQuery();

			ArrayList<Ingredient> ingredTemp = new ArrayList<Ingredient>();
			while (rs3.next())
			{
				Ingredient ing = new Ingredient();
				ing.setIngredientName(rs3.getString("ingredientName"));
				ing.setUnit(rs3.getString("unit"));
				ing.setIngredientAmount(rs3.getInt("ingredientAmount"));
				ing.setDescription(rs3.getString("description"));
				ingredTemp.add(ing);
			}
			recipe.setIngredientList(ingredTemp);

			PreparedStatement pst4 = con.prepareStatement("select * from step where recipeId =" + recipeId);
			ResultSet rs4 = pst4.executeQuery();

			ArrayList<String> stepListTemp = new ArrayList<String>();
			while (rs4.next())
			{

				stepListTemp.add(rs4.getString("stepDescription"));

			}
			recipe.setStepList(stepListTemp);

			return recipe;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static void insertCategory(Recipe recipe)
	{

		try
		{
			Connection con = getConnection();
			PreparedStatement insertCategory = con.prepareStatement(
					"insert into RecipeCategory(RecipeID,city,taste,mealtime,meat,vegetarian) value(?,?,?,?,?,?)");

			insertCategory.setInt(1, recipe.getRecipeId());
			insertCategory.setString(2, recipe.getCategory().getCity());
			insertCategory.setString(3, recipe.getCategory().getTaste());
			insertCategory.setString(4, recipe.getCategory().getMealtime());
			insertCategory.setString(5, recipe.getCategory().getMeat());
			insertCategory.setBoolean(6, recipe.getCategory().isVegetarian());
			insertCategory.executeUpdate();

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

	public static void insertIngreident(Recipe recipe)
	{
		try
		{
			Connection conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(
					"insert into RecipeIngredient(recipeID,ingredientName,ingredientAmount,unit,description) values(?,?,?,?,?)");
			ArrayList<Ingredient> ingredientList = recipe.getIngredientList();
			for (int i = 0; i < ingredientList.size(); i++)
			{
				pst.setInt(1, recipe.getRecipeId());
				pst.setString(2, ingredientList.get(i).getIngredientName());
				pst.setDouble(3, ingredientList.get(i).getIngredientAmount());
				pst.setString(4, ingredientList.get(i).getUnit());
				pst.setString(5, ingredientList.get(i).getDescription());
				pst.executeUpdate();
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} /*
			 * finally { if (pst != null) try { pst.close(); } catch (SQLException e) {
			 * e.printStackTrace(); } if (conn != null) { try { conn.close(); } catch
			 * (SQLException e) { e.printStackTrace(); } } }
			 */
	}

	public static void insertStep(Recipe recipe) throws SQLException
	{
		Connection con = getConnection();
		PreparedStatement insertStep = con
				.prepareStatement("insert into Step(recipeID,stepOrder, stepDescription) value(?,?,?)");
		for (int i = 0; i < recipe.getStepList().size(); i++)
		{
			insertStep.setInt(1, recipe.getRecipeId());
			insertStep.setInt(2, i + 1);
			insertStep.setString(3, recipe.getStepList().get(i));
			insertStep.executeUpdate();
		}
	}

	public static void insertRecipe(Recipe recipe)
	{
		try
		{
			Connection con = getConnection();
			PreparedStatement insertRecipe = con.prepareStatement(
					"insert into Recipe(recipeName, serveNum, cookingTime, preparationTime) value(?,?,?,?)");
			insertRecipe.setString(1, recipe.getRecipeName());
			insertRecipe.setInt(2, recipe.getServeNum());
			insertRecipe.setInt(3, recipe.getCookingTime());
			insertRecipe.setInt(4, recipe.getPreparationTime());
			insertRecipe.executeUpdate();

			insertStep(recipe);
			insertIngreident(recipe);
			insertCategory(recipe);
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
					"CREATE TABLE IF NOT EXISTS Step(recipeID int not null, stepOrder int not null, stepDescription LONGTEXT, PRIMARY KEY(recipeID,stepOrder))");
			PreparedStatement createRecipe = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Recipe(recipeID int not null auto_increment, recipeName LONGTEXT, serveNum int, cookingTime int, preparationTime int, picture LONGTEXT, PRIMARY KEY(recipeID))");
			PreparedStatement createRecipeIngredient = con
					.prepareStatement("CREATE TABLE IF NOT EXISTS RecipeIngredient"
							+ "(recipeID int NOT NULL, ingredientName varchar(255) NOT NULL, "
							+ "unit varchar(255), ingredientAmount double, description varchar(255), "
							+ "PRIMARY KEY(recipeID, ingredientName))");
			PreparedStatement createRecipeCategory = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS RecipeCategory(recipeID int NOT NULL, city varchar(255), taste varchar(255), mealtime varchar(255), meat varchar(255), vegetarian boolean, PRIMARY KEY(recipeID))");

			createRecipeCategory.executeUpdate();
			createRecipeIngredient.executeUpdate();
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
			e.printStackTrace();
		}
		return null;
	}

}
