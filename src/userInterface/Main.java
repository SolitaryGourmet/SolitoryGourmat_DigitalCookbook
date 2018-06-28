package userInterface;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Main extends Application
{
	private Stage stage;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			stage = primaryStage;
			stage.setTitle("Digital Cookbook");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
			stage.setMinWidth(800);
			stage.setMinHeight(600);
			gotoMainInterface();
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * go to display window
	 * 
	 * @param recipe
	 */
	public void goToDisplayForm(Recipe recipe, ArrayList<Recipe> recipeList, String flag_source,
			Recipe recipeForBackToCategory)
	{
		try
		{
			DisplayFormController dc = replaceSceneContent("DisplayForm.fxml");
			dc.setApp(this);
			dc.setRecipe(recipe);
			dc.setDisplayFormView();
			dc.setRecipeList(recipeList);
			dc.setFlag_source(flag_source);
			dc.setRecipeForBackToCategory(recipeForBackToCategory);

			if (flag_source.equals("Add recipe"))
			{
				dc.getBackToResultForm().setVisible(false);
			}

		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * go to search by category window
	 */
	public void gotoCategory(String flag_source, Recipe recipe)
	{
		try
		{
			CategoryController cc = replaceSceneContent("Category.fxml");
			cc.setApp(this);
			cc.setFlag_source(flag_source);
			cc.setRecipe(recipe);
			if (recipe != null)
			{
				cc.setCategoryForm(recipe);
				System.out.println("setcate");
			}
			if (flag_source.equals("Search Recipe"))
			{
				cc.getMainTitle_Label().setText("Search by category");
				cc.getNextStep_Button().setText("Search");
			}
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * go to main window
	 */
	public void gotoMainInterface()
	{
		try
		{
			MainInterfaceController mic = replaceSceneContent("MainInterface.fxml");
			mic.setApp(this);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * go to add recipe window
	 */
	public void gotoAddRecipeForm(String flag_source, Recipe recipe)
	{
		try
		{
			AddRecipeFormController arfc = replaceSceneContent("AddRecipeForm.fxml");
			arfc.setApp(this);
			arfc.setFlag_source(flag_source);
			arfc.initalNext_flag();
			if (flag_source.equals("Edit Recipe"))
			{
				arfc.setRecipe(recipe);
				arfc.initialRecipeForm(recipe);
				arfc.setIngredientList(recipe.getIngredientList());
				arfc.setStepBufferList(recipe.getStepList());
				arfc.setCategory(recipe.getCategory());
			}
			arfc.setPrompt();
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void gotoEditStep(String flag_source, Recipe recipe) throws Exception
	{
		// currentStage.close();
		AddStepFormController asfc = replaceSceneContent("AddStepForm.fxml");
		asfc.setApp(this);
		asfc.setRecipe(recipe);
		asfc.setFlag_source(flag_source);
		for (int i = 0; i < recipe.getStepList().size(); i++)
		{
			asfc.setStep(recipe.getStepList().get(i));
			asfc.addNewStep();
		}
		asfc.setStep(new String());
	}

	public void gotoSetIngredient(String flag_source, Recipe recipe) throws Exception
	{
		AddIngredientFormController aifc = replaceSceneContent("AddIngredientForm.fxml");
		aifc.setApp(this);
		aifc.setRecipe(recipe);
		aifc.setFlag_source(flag_source);
		for (int i = 0; i < recipe.getIngredientList().size(); i++)
		{
			aifc.setIngredient(recipe.getIngredientList().get(i));
			aifc.addNewIngredient();
		}
		aifc.setIngredient(new Ingredient());

	}

	/**
	 * after adding ingredients in add recipe form, go back to the add recipe form
	 * and transmit ingredient list
	 */
	public void goBackToAddRecipeForm(String flag_source, Recipe recipe)
	{
		try
		{
			AddRecipeFormController arfc = replaceSceneContent("AddRecipeForm.fxml");
			arfc.setApp(this);
			arfc.setIngredientList(recipe.getIngredientList());
			arfc.setStepBufferList(recipe.getStepList());
			arfc.setCategory(recipe.getCategory());
			arfc.setRecipe(recipe);
			arfc.initialRecipeForm(recipe);
			arfc.setFlag_source(flag_source);
			arfc.initalNext_flag();
			arfc.setPrompt();
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * go to search window including search by name and search by category button
	 */
	public void gotoSearchForm()
	{
		try
		{
			SearchFormController sfc = replaceSceneContent("SearchForm.fxml");
			sfc.setApp(this);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * go to search result window which includes list of recipes
	 */
	public void gotoSearchResult(String flag_source, ArrayList<Recipe> recipeList, Recipe recipeForBackToCategory)
	{
		try
		{
			SearchResultFromController srfc = replaceSceneContent("SearchResultFrom.fxml");
			srfc.setApp(this);
			srfc.setSearchResultView(recipeList);
			srfc.setRecipeList(recipeList);
			srfc.setFlag_source(flag_source);
			srfc.setRecipeForBackToCategory(recipeForBackToCategory);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

	/**
	 * method to replace scene, used for the methods above
	 * 
	 * @param fxml
	 * @return
	 * @throws Exception
	 */
	private <T> T replaceSceneContent(String fxml) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource(fxml));
		Pane page;
		try
		{
			page = (AnchorPane) loader.load(in);
		}
		finally
		{
			in.close();
		}
		Scene scene = new Scene(page, 800, 600);

		stage.setScene(scene);
		stage.sizeToScene();
		return loader.getController();
	}

	// private <T> T showStage(String route)
	// {
	//
	// FXMLLoader Loader = new FXMLLoader();
	// Loader.setLocation(getClass().getResource(route));
	// try
	// {
	// Loader.load();
	// }
	// catch (Exception ex)
	// {
	// Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	// }
	//
	// Stage stage1 = new Stage();
	// stage1.setScene(new Scene(Loader.getRoot()));
	// stage1.show();
	// setCurrentStage(stage1);
	// return Loader.getController();
	// }

	// private void setCurrentStage(Stage stage)
	// {
	// this.currentStage = stage;
	// }

	public static void main(String[] args)
	{
		launch(args);
	}
}
