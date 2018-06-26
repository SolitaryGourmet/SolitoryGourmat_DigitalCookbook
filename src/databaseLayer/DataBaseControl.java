package databaseLayer;

import java.sql.*;
import java.util.ArrayList;

import businessLayer.Category;
import businessLayer.Ingredient;
import businessLayer.Recipe;

public class DataBaseControl
{
	private static Connection con;

	/**
	 * with the input value Category, return a list of recipes
	 * 
	 * @param category
	 * @return
	 */
	public static ArrayList<Recipe> searchByCategory(Category category)
	{
		try
		{
			ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
			String city = category.getCity();
			String mealtime = category.getMealtime();
			String meat = category.getMeat();
			String taste = category.getTaste();
			boolean vegetarian = category.isVegetarian();
			int vege = 0;

			if (city == null)
			{
				city = "";
			}
			else
			{
				city = "city =" + "\"" + city + "\"" + " and ";
			}

			if (mealtime == null)
			{
				mealtime = "";
			}
			else
			{
				mealtime = "mealtime like " + "\"%" + mealtime + "%\"" + " and ";
			}

			if (meat == null)
			{
				meat = "";
			}
			else
			{
				meat = "meat like " + "\"%" + meat + "%\"" + " and ";
			}

			if (taste == null)
			{
				taste = "";
			}
			else
			{
				taste = "taste like " + "\"%" + taste + "%\"" + " and ";
			}
			if (vegetarian == true)
			{
				vege = 1;
			}
			else
			{
				vege = 0;
			}

			String sql = "SELECT recipeID FROM recipeCategory where " + city + mealtime + meat + taste + " vegetarian ="
					+ "\"" + vege + "\"";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getInt("recipeId"));
				recipeList.add(searchById(rs.getInt("recipeId")));

			}
			return recipeList;

		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * with the input value recipeName, return a list of all suitable recipes
	 * 
	 * @param recipeName
	 * @return
	 */
	public static ArrayList<Recipe> searchByName(String recipeName)
	{
		int recipeId = 0;
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		try
		{
			PreparedStatement pst = con.prepareStatement(
					"select recipeId from recipe where recipeName like " + "\"%" + recipeName + "%\"");
			ResultSet rs = pst.executeQuery();

			while (rs.next())
			{
				recipeId = rs.getInt("recipeId");
				recipeList.add(searchById(recipeId));

			}
			return recipeList;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * search a specific recipe with recipeID used by another two method:
	 * searchByName(String recipeName), searchByCategory(Category category)
	 * 
	 * @param recipeId
	 * @return
	 */
	public static Recipe searchById(int recipeId)
	{
		try
		{
			Recipe recipe = new Recipe();

			/**
			 * select recipeID, recipeName, serveNum, cookingTime, preparationTime, picture
			 * route from recipe Table
			 */
			PreparedStatement pst1 = con.prepareStatement("select * from recipe where recipeId=" + recipeId);
			ResultSet rs1 = pst1.executeQuery();
			while (rs1.next())
			{
				recipe.setRecipeId(rs1.getInt("recipeID"));
				recipe.setRecipeName(rs1.getString("recipeName"));
				recipe.setServeNum(rs1.getInt("serveNum"));
				recipe.setCookingTime(rs1.getInt("cookingTime"));
				recipe.setPreparationTime(rs1.getInt("preparationTime"));
				recipe.setPhotoRoute(rs1.getString("picture"));
			}

			/**
			 * select category from recipeCategory Table
			 */
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

			/**
			 * select ingredient from recipeIngredient Table
			 */
			PreparedStatement pst3 = con.prepareStatement("select * from recipeingredient where recipeId =" + recipeId);
			ResultSet rs3 = pst3.executeQuery();
			ArrayList<Ingredient> ingredTemp = new ArrayList<Ingredient>();
			while (rs3.next())
			{
				Ingredient ing = new Ingredient();
				ing.setIngredientName(rs3.getString("ingredientName"));
				ing.setUnit(rs3.getString("unit"));
				ing.setIngredientAmount(rs3.getDouble("ingredientAmount"));
				ing.setDescription(rs3.getString("description"));
				ingredTemp.add(ing);
			}
			recipe.setIngredientList(ingredTemp);

			/**
			 * select step from step Table
			 */
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

	public static int getRecipeID(String recipeName)
	{
		int recipeID = 0;
		try
		{
			PreparedStatement stmt = con
					.prepareStatement("select recipeId from recipe where recipeName = " + "\"" + recipeName + "\"");
			ResultSet rset = stmt.executeQuery();
			while (rset.next())
			{
				recipeID = rset.getInt("recipeID");
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipeID;
	}

	public static void insertCategory(Recipe recipe)
	{

		try
		{
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
			PreparedStatement pst = con.prepareStatement(
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
		}
	}

	public static void insertStep(Recipe recipe) throws SQLException
	{
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

	/**
	 * insert the basic attributes of a recipe into the database and also use the
	 * static method of insertIngredient, insertCategory, insertStep
	 * 
	 * @param recipe
	 */
	public static void insertRecipe(Recipe recipe)
	{
		try
		{
			PreparedStatement insertRecipe = con.prepareStatement(
					"insert into Recipe(recipeName, serveNum, cookingTime, preparationTime,picture) value(?,?,?,?,?)");
			insertRecipe.setString(1, recipe.getRecipeName());
			insertRecipe.setInt(2, recipe.getServeNum());
			insertRecipe.setInt(3, recipe.getCookingTime());
			insertRecipe.setInt(4, recipe.getPreparationTime());
			insertRecipe.setString(5, recipe.getPhotoRoute());
			insertRecipe.executeUpdate();

			recipe.setRecipeId(getRecipeID(recipe.getRecipeName()));

			insertStep(recipe);
			insertIngreident(recipe);
			insertCategory(recipe);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("data inserted");
		}
	}

	/*
	 * for edit by idiot Xiao
	 * updated by luoyifan
	 */
	public static void editRecipe(Recipe recipe)
	{
		try
		{

			ArrayList<String> sql = new ArrayList<String>();// 罗一凡你高兴就封装一下方法谢谢
			sql.add("update recipe set recipeName =" + "\"" + recipe.getRecipeName() + "\"" + "where recipeId = " + "\""
					+ recipe.getRecipeId() + "\"");
			sql.add("update recipe set serveNum =" + "\"" + recipe.getServeNum() + "\"" + "where recipeId = " + "\""
					+ recipe.getRecipeId() + "\"");
			sql.add("update recipe set cookingTime =" + "\"" + recipe.getCookingTime() + "\"" + "where recipeId = "
					+ "\"" + recipe.getRecipeId() + "\"");
			sql.add("update recipe set preparationTime =" + "\"" + recipe.getPreparationTime() + "\""
					+ "where recipeId = " + "\"" + recipe.getRecipeId() + "\"");

			for (int i = 0; i < sql.size(); i++)
			{
				PreparedStatement editStatement = con.prepareStatement(sql.get(i));
				editStatement.execute();
			}

			ArrayList<String> sqlCategory = new ArrayList<String>();
			sqlCategory.add("update recipecategory set city =" + "\"" + recipe.getCategory().getCity() + "\""
					+ "where recipeId = " + "\"" + recipe.getRecipeId() + "\"");
			sqlCategory.add("update recipecategory set taste =" + "\"" + recipe.getCategory().getTaste() + "\""
					+ "where recipeId = " + "\"" + recipe.getRecipeId() + "\"");
			sqlCategory.add("update recipecategory set mealTime =" + "\"" + recipe.getCategory().getMealtime() + "\""
					+ "where recipeId = " + "\"" + recipe.getRecipeId() + "\"");
			sqlCategory.add("update recipecategory set meat =" + "\"" + recipe.getCategory().getMeat() + "\""
					+ "where recipeId = " + "\"" + recipe.getRecipeId() + "\"");
			int vege = 0;
			if (recipe.getCategory().isVegetarian() == true)
			{
				vege = 1;
			}
			else
			{
				vege = 0;
			}
			sqlCategory.add("update recipecategory set vegetarian =" + "\"" + vege + "\"" + "where recipeId = " + "\""
					+ recipe.getRecipeId() + "\"");

			for (int i = 0; i < sql.size(); i++)
			{
				PreparedStatement editStatement = con.prepareStatement(sqlCategory.get(i));
				editStatement.execute();
			}

			PreparedStatement deleteIngredient = con
					.prepareStatement("delete from recipeIngredient where recipeID=" + recipe.getRecipeId());
			deleteIngredient.executeUpdate();
			
			DataBaseControl.insertIngreident(recipe);
			
			PreparedStatement deleteStep = con
					.prepareStatement("delete from Step where recipeID=" + recipe.getRecipeId());
			deleteStep.executeUpdate();
			
			DataBaseControl.insertStep(recipe);
			
			PreparedStatement editStatement = con.prepareStatement("update recipe set picture = ? " + " where recipeId = ? ");
			editStatement.setString(1, recipe.getPhotoRoute());
			editStatement.setInt(2, recipe.getRecipeId());
			editStatement.execute();

		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}

		// try
		// {
		//
		// for (int i = 0; i < sql.size(); i++)
		// {
		// PreparedStatement editStatement = con.prepareStatement(sql.get(i));
		// editStatement.execute();
		// }
		//
		//
		// }
		// catch (Exception e)
		// {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
	}

	public static void deleteRecipe(Recipe recipe)
	{
		try
		{
			PreparedStatement deleteRecipe = con
					.prepareStatement("delete from Recipe where recipeID=" + recipe.getRecipeId());
			PreparedStatement deleteIngredient = con
					.prepareStatement("delete from recipeIngredient where recipeID=" + recipe.getRecipeId());
			PreparedStatement deleteCategory = con
					.prepareStatement("delete from recipeCategory where recipeID=" + recipe.getRecipeId());
			PreparedStatement deleteStep = con
					.prepareStatement("delete from Step where recipeID=" + recipe.getRecipeId());
			deleteRecipe.executeUpdate();
			deleteIngredient.executeUpdate();
			deleteCategory.executeUpdate();
			deleteStep.executeUpdate();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("delete " + recipe.getRecipeName());
		}
	}

	public static void createTable()
	{
		try
		{
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

	public static void getConnection()
	{
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?&useSSL=false", "root", "961211");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			// System.out.println("error");
		}
		finally
		{
			System.out.println("Connected");
		}
	}

	public static void closeConnection()
	{
		try
		{
			con.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Closed");
		}
	}
}
