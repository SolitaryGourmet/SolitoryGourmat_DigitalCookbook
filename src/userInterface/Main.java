package userInterface;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application
{
	private Stage stage;
	private Stage currentStage = new Stage();

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			stage = primaryStage;
			stage.setTitle("Digital Cookbook");
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
	public void goToDisplayForm(Recipe recipe)
	{
		try
		{
			DisplayFormController dc = replaceSceneContent("DisplayForm.fxml");
			dc.setApp(this);
			dc.setRecipe(recipe);
			dc.setDisplayFormView();

		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		// FXMLLoader Loader = new FXMLLoader();
		// Loader.setLocation(getClass().getResource("DisplayForm.fxml"));
		// try
		// {
		// Loader.load();
		// }
		// catch (Exception ex)
		// {
		// Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		// }
		// DisplayFormController dc = Loader.getController();
		//
		// stage.setScene(new Scene(Loader.getRoot()));

	}

	/**
	 * go to search by category window
	 */
	public void gotoCategory()
	{
		try
		{
			CategoryController cc = replaceSceneContent("Category.fxml");
			cc.setApp(this);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		// FXMLLoader Loader = new FXMLLoader();
		// Loader.setLocation(getClass().getResource("Category.fxml"));
		// try
		// {
		// Loader.load();
		// }
		// catch (Exception ex)
		// {
		// Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		// }
		// CategoryController cc = Loader.getController();
		// cc.setApp(this);
		// Scene scene = new Scene(Loader.getRoot());
		// scene.getStylesheets().add(getClass().getResource("Category.css").toExternalForm());
		// stage.setScene(scene);

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
	public void gotoAddRecipeForm()
	{
		try
		{
			AddRecipeFormController arfc = replaceSceneContent("AddRecipeForm.fxml");
			arfc.setApp(this);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void gotoEditStep(Recipe recipe) throws Exception
	{
		// currentStage.close();
		AddStepFormController asfc = replaceSceneContent("AddStepForm.fxml");
		asfc.setApp(this);
		asfc.setRecipe(recipe);
		for (int i = 0; i < recipe.getStepList().size(); i++)
		{
			asfc.setStep(recipe.getStepList().get(i));
			asfc.addNewStep();
		}
		asfc.setStep(new String());
	}

	public void gotoSetIngredient(Recipe recipe) throws Exception
	{
		AddIngredientFormController aifc = replaceSceneContent("AddIngredientForm.fxml");
		aifc.setApp(this);
		aifc.setRecipe(recipe);
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
	public void goBackToAddRecipeForm(Recipe recipe)
	{
		try
		{
			AddRecipeFormController arfc = replaceSceneContent("AddRecipeForm.fxml");
			arfc.setApp(this);
			arfc.setIngredientList(recipe.getIngredientList());
			arfc.setStepBufferList(recipe.getStepList());
			arfc.setRecipe(recipe);
			arfc.initialRecipeForm(recipe);
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void stepGoBackToAddRecipeForm(Recipe recipe)
	{
		try
		{
			AddRecipeFormController arfc = replaceSceneContent("AddRecipeForm.fxml");
			arfc.setApp(this);
			arfc.setIngredientList(recipe.getIngredientList());
			arfc.setStepBufferList(recipe.getStepList());
			arfc.setRecipe(recipe);
			arfc.initialRecipeForm(recipe);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void gotoSearchResult(ArrayList<Recipe> recipeList)
	{
		try
		{
			SearchResultFromController srfc = replaceSceneContent("SearchResultFrom.fxml");
			srfc.RequestFromSearch();
			srfc.setApp(this);
			srfc.setSearchResultView(recipeList);
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

	private <T> T showStage(String route)
	{

		FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(getClass().getResource(route));
		try
		{
			Loader.load();
		}
		catch (Exception ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		Stage stage1 = new Stage();
		stage1.setScene(new Scene(Loader.getRoot()));
		stage1.show();
		setCurrentStage(stage1);
		return Loader.getController();
	}

	private void setCurrentStage(Stage stage)
	{
		this.currentStage = stage;
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
