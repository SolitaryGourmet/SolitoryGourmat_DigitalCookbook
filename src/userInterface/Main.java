/**
 * this Main class extends the application which serves like the main controller of all the controllers
 * each controller can initial the interface and exchange data by this class
 * @author LUO_YIFAN CUI_XIAO
 */

package userInterface;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Main extends Application
{
	private Stage stage;

	/**
	 * initial the begining interface
	 */
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
			stage.setResizable(false);
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

	/**
	 * from display interface to the add recipe interface and set the flag type
	 * to"edit"
	 */
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

	/**
	 * after add the name,serve time,preparation time and picture go to the
	 */
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
	 * go to search result window which includes list of recipes and set the type of
	 * method by using flag_source
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
		stage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent event)
			{
				event.consume();// by using this method to avoid the close rightly
				WindowsClosePage windowsClosePage = new WindowsClosePage(stage);
				windowsClosePage.activateProgressBar();

			}
		});
		return loader.getController();
	}

	/**
	 * make a pop-up before close the software to get a alert to user
	 * 
	 * @author Noin
	 *
	 */
	public class WindowsClosePage
	{

		private Stage dialogStage;
		private Stage primaryStage;

		public WindowsClosePage(Stage stage)
		{

			Button confirmButton = new Button("Yes");
			Button cancelButton = new Button("No");

			DropShadow shadow = new DropShadow();
			confirmButton.setFont(Font.font("Ebrima", 14));
			confirmButton.setMinHeight(40);
			confirmButton.setMinWidth(80);
			confirmButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				confirmButton.setEffect(shadow);
			});
			confirmButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				confirmButton.setEffect(null);
			});
			confirmButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14pt; -fx-padding: 5; "
					+ "-fx-background-color: FireBrick; -fx-background-radius: 25; -fx-border-radius: 25; "
					+ "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0.0, 0, 1)");

			DropShadow shadow1 = new DropShadow();
			cancelButton.setFont(Font.font("Ebrima", 14));
			cancelButton.setMinHeight(40);
			cancelButton.setMinWidth(80);
			cancelButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				cancelButton.setEffect(shadow1);
			});
			cancelButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				cancelButton.setEffect(null);
			});
			cancelButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14pt; -fx-padding: 5; "
					+ "-fx-background-color: FireBrick; -fx-background-radius: 25; -fx-border-radius: 25; "
					+ "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0.0, 0, 1)");

			dialogStage = new Stage();
			primaryStage = stage;

			dialogStage.setWidth(400);
			dialogStage.setHeight(300);
			dialogStage.setResizable(false);
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
			BorderPane borderPaneLayout = new BorderPane();
			borderPaneLayout.getStyleClass().add("root");

			confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event)
				{
					dialogStage.close();
					primaryStage.close();
				}
			});

			cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event)
				{
					dialogStage.close();
				}
			});

			HBox hBox = new HBox();
			hBox.setSpacing(30);
			hBox.getChildren().addAll(confirmButton, cancelButton);
			hBox.setAlignment(Pos.CENTER);

			VBox vBox = new VBox();
			Label text = new Label("Are you sure to exit?");
			text.setFont(Font.font("Ebrima", FontPosture.REGULAR, 28));
			text.setStyle("-fx-text-fill: #a42e2e");

			vBox.setSpacing(40);
			vBox.getChildren().addAll(text, hBox);
			vBox.setAlignment(Pos.CENTER);

			borderPaneLayout.setCenter(vBox);
			borderPaneLayout.setStyle("-fx-background-color: white");

			Scene scene = new Scene(borderPaneLayout);
			dialogStage.setTitle("Confirm");
			dialogStage.setScene(scene);
		}

		public void activateProgressBar()
		{
			dialogStage.show();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
