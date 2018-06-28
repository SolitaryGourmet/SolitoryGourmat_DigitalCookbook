package userInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import databaseLayer.DataBaseControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class DisplayFormController
{
	private Recipe recipe;
	private Main application;

	@FXML
	private Label recipeNameLabel;

	@FXML
	private Button returnToMinWin;

	@FXML
	private TextField serveNumTextField;

	@FXML
	private Button change;

	@FXML
	private Label preparationTimeLable;

	@FXML
	private Label cookingTimeLabel;

	// @FXML
	// private Label categoryLabel;

	@FXML
	private FlowPane flowPane;

	@FXML
	private Pane picturePane;

	private ImageView image;

	@FXML
	private ListView ingredientListView;

	@FXML
	private ListView stepListView;

	private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();

	private ObservableList<String> stepList = FXCollections.observableArrayList();

	@FXML
	private Button delete;

	@FXML
	private Button backToResultForm;

	@FXML
	private Button edit;

	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private String flag_source = new String();
	private Recipe recipeForBackToCategory = new Recipe();

	public Button getBackToResultForm()
	{
		return backToResultForm;
	}

	public void setBackToResultForm(Button backToResultForm)
	{
		this.backToResultForm = backToResultForm;
	}

	public Recipe getRecipeForBackToCategory()
	{
		return recipeForBackToCategory;
	}

	public void setRecipeForBackToCategory(Recipe recipeForBackToCategory)
	{
		this.recipeForBackToCategory = recipeForBackToCategory;
	}

	public String getFlag_source()
	{
		return flag_source;
	}

	public void setFlag_source(String flag_source)
	{
		this.flag_source = flag_source;
	}

	public ArrayList<Recipe> getRecipeList()
	{
		return recipeList;
	}

	public void setRecipeList(ArrayList<Recipe> recipeList)
	{
		this.recipeList = recipeList;
	}

	@FXML
	void changeServeNum(ActionEvent event)
	{
		recipe = changeServeNum(recipe, Integer.parseInt(serveNumTextField.getText()));
		serveNumTextField.setText(Integer.toString(recipe.getServeNum()));

		for (int i = 0; i < recipe.getIngredientList().size(); i++)
		{
			ingredientListView.getItems().remove(recipe.getIngredientList().get(i));
		}

		ingredientListView.setItems(ingredientList);
		for (int i = 0; i < recipe.getIngredientList().size(); i++)
		{
			ingredientList.add(i, recipe.getIngredientList().get(i));
		}
	}

	@FXML
	void backToResultForm(ActionEvent event)
	{
		application.gotoSearchResult(flag_source, recipeList, recipeForBackToCategory);
	}

	@FXML
	void deleteRecipe(ActionEvent event)
	{
		FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(getClass().getResource("DeleteRecipeConfirm.fxml"));
		try
		{
			Loader.load();
		}
		catch (Exception ex)
		{

		}

		Stage deleteRecipeConfirmStage = new Stage();
		deleteRecipeConfirmStage.setTitle("Confirm Delete");
		deleteRecipeConfirmStage.setScene(new Scene(Loader.getRoot()));
		deleteRecipeConfirmStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
		deleteRecipeConfirmStage.show();
		DeleteRecipeConfirmController drcc = Loader.getController();
		drcc.getDelete_Button().setOnAction(e -> {
			System.out.println("delete");

			DataBaseControl.getConnection();
			DataBaseControl.deleteRecipe(recipe);
			DataBaseControl.closeConnection();

			deleteRecipeConfirmStage.close();
			
			application.gotoMainInterface();
			
			FXMLLoader root = new FXMLLoader();
			root.setLocation(getClass().getResource("SuccessDelete.fxml"));
			try
			{
				root.load();
			}
			catch (Exception ex)
			{

			}

			Stage successDeleteStage = new Stage();
			successDeleteStage.setTitle("Confirm Delete");
			successDeleteStage.setScene(new Scene(root.getRoot()));
			successDeleteStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
			successDeleteStage.show();
			
			SuccessDeleteController sdc = root.getController();
			sdc.getOk_Button().setOnAction(ex -> {
				successDeleteStage.close();
			});
			
			
		});

		drcc.getCancel_Button().setOnAction(e -> {
			System.out.println("cancel");
			deleteRecipeConfirmStage.close();
		});

	}

	@FXML
	void editRecipe(ActionEvent event)
	{
		application.gotoAddRecipeForm("Edit Recipe", recipe);
	}

	@FXML
	void goToMinWin(ActionEvent event)
	{
		application.gotoMainInterface();
	}

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
	}

	/**
	 * initialize all the default value in the DisplayForm
	 * 
	 * @throws FileNotFoundException
	 */
	public void setDisplayFormView() throws FileNotFoundException
	{
		// System.out.println(recipe);

		recipeNameLabel.setText(recipe.getRecipeName());
		serveNumTextField.setText(Integer.toString(recipe.getServeNum()));
		preparationTimeLable.setText(Integer.toString(recipe.getPreparationTime()));
		cookingTimeLabel.setText(Integer.toString(recipe.getCookingTime()));

		ingredientListView.setItems(ingredientList);
		for (int i = 0; i < recipe.getIngredientList().size(); i++)
		{
			ingredientList.add(recipe.getIngredientList().get(i));
		}

		stepListView.setItems(stepList);
		for (int j = 0; j < recipe.getStepList().size(); j++)
		{
			stepList.add(recipe.getStepList().get(j));
		}

		ArrayList<Label> categoryLabel = new ArrayList<Label>();

		Label city = new Label(recipe.getCategory().getCity());
		if (city.getText() != null)
		{
			categoryLabel.add(city);
		}

		String str = new String();
		String[] arr;
		str = recipe.getCategory().getTaste();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (String ss : arr)
			{
				Label temp = new Label(ss);
				categoryLabel.add(temp);
			}

		}

		str = recipe.getCategory().getMealtime();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (String ss : arr)
			{
				Label temp = new Label(ss);
				categoryLabel.add(temp);
			}

		}

		if (recipe.getCategory().isVegetarian() == false)
		{
			str = recipe.getCategory().getMeat();
			if (str != null)
			{
				arr = str.split("\\s+");
				for (String ss : arr)
				{
					Label temp = new Label(ss);
					categoryLabel.add(temp);
				}
			}
		}
		else
		{
			Label vege = new Label("vegetarian");
			categoryLabel.add(vege);
		}

		for (int i = 0; i < categoryLabel.size(); i++)
		{
			categoryLabel.get(i).setPadding(new Insets(2, 4, 2, 4));
			categoryLabel.get(i).setStyle("-fx-background-color: beige; -fx-text-fill: #a42e2e;");
			categoryLabel.get(i).setFont(Font.font("Ebrima", FontPosture.REGULAR, 18));
			flowPane.getChildren().add(categoryLabel.get(i));
		}

		flowPane.setHgap(10);
		flowPane.setVgap(10);

		if (recipe.getPhotoRoute() != null)
		{
			FileInputStream fis = new FileInputStream(recipe.getPhotoRoute());
			Image picture = new Image(fis);
			image = new ImageView(picture);

			double width = picture.getWidth();
			double height = picture.getHeight();

			image.setFitHeight(200);
			image.setFitWidth(200 * width / height);

			picturePane.getChildren().add(image);
		}

	}

	public void setApp(Main application)
	{
		this.application = application;
	}

	/**
	 * changeServeNum create a new Recipe object which represents the changed recipe
	 * 
	 * @param recipe
	 * @param serveNum
	 * @return Recipe
	 * @author Liu Yanran
	 */
	public static Recipe changeServeNum(Recipe recipe, int serveNum)
	{
		int n = recipe.getServeNum();
		recipe.setServeNum(serveNum);
		ArrayList<Ingredient> ingredientList = recipe.getIngredientList();
		int i;
		for (i = 0; i < ingredientList.size(); i++)
		{
			double ingredientAmount = ingredientList.get(i).getIngredientAmount();
			double ingredientAmountUnit = ingredientAmount / n;
			double ingredientAmountNew = ingredientAmountUnit * serveNum;
			ingredientList.get(i).setIngredientAmount(ingredientAmountNew);
		}
		recipe.setIngredientList(ingredientList);
		return recipe;
	}

}
