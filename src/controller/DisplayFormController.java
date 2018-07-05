package controller;
/**
 * this interface will display the recipe  
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import databaseLayer.DataBaseControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import userInterface.Main;

public class DisplayFormController
{
	private Recipe recipe;
	private static Recipe recipe1;// deepClone of the recipe for change servenum
	
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

	/**
	 * by changing the number the ingredient will show the amount according to the serve number
	 * @param event
	 * @throws CloneNotSupportedException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@FXML
	void changeServeNum(ActionEvent event) throws ClassNotFoundException, IOException, NumberFormatException, CloneNotSupportedException
	{
//		recipe1 = (Recipe) recipe.deepClone();
		recipe1 = changeServeNum(recipe1, Integer.parseInt(serveNumTextField.getText()));
		serveNumTextField.setText(Integer.toString(recipe1.getServeNum()));
		//remove the origin elements 
		for (int i = 0; i < recipe1.getIngredientList().size(); i++)
		{
			ingredientListView.getItems().remove(recipe1.getIngredientList().get(i));
		}
		//set new changed elements
		ingredientListView.setItems(ingredientList);
		for (int i = 0; i < recipe1.getIngredientList().size(); i++)
		{
			ingredientList.add(i, recipe1.getIngredientList().get(i));
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
//		FXMLLoader Loader = new FXMLLoader();
//		Loader.setLocation(getClass().getResource("DeleteRecipeConfirm.fxml"));
//		try
//		{
//			Loader.load();
//		}
//		catch (Exception ex)
//		{
//
//		}

		FXMLLoader Loader = application.getRoot("DeleteRecipeConfirm.fxml");
		
		Stage deleteRecipeConfirmStage = new Stage();
		deleteRecipeConfirmStage.setTitle("Confirm Delete");
		deleteRecipeConfirmStage.setScene(new Scene(Loader.getRoot()));
		deleteRecipeConfirmStage.getIcons().add(new Image(getClass().getResourceAsStream("/picture/Logo.png")));
		deleteRecipeConfirmStage.show();
		DeleteRecipeConfirmController drcc = Loader.getController();
		drcc.getDelete_Button().setOnAction(e -> {
			System.out.println("delete");

			DataBaseControl.getConnection();
			DataBaseControl.deleteRecipe(recipe);
			DataBaseControl.closeConnection();

			deleteRecipeConfirmStage.close();
			
			application.gotoMainInterface();
			
//			FXMLLoader root = new FXMLLoader();
//			root.setLocation(getClass().getResource("SuccessDelete.fxml"));
//			try
//			{
//				root.load();
//			}
//			catch (Exception ex)
//			{
//
//			}

			FXMLLoader root = application.getRoot("SuccessDelete.fxml");
			
			Stage successDeleteStage = new Stage();
			successDeleteStage.setTitle("Confirm Delete");
			successDeleteStage.setScene(new Scene(root.getRoot()));
			successDeleteStage.getIcons().add(new Image(getClass().getResourceAsStream("/picture/Logo.png")));
			successDeleteStage.setResizable(false);
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void setDisplayFormView() throws ClassNotFoundException, IOException
	{
		recipe1 = (Recipe) recipe.deepClone();
		recipeNameLabel.setText(recipe1.getRecipeName());
		serveNumTextField.setText(Integer.toString(recipe1.getServeNum()));
		preparationTimeLable.setText(Integer.toString(recipe1.getPreparationTime()));
		cookingTimeLabel.setText(Integer.toString(recipe1.getCookingTime()));

		ingredientListView.setItems(ingredientList);
		for (int i = 0; i < recipe1.getIngredientList().size(); i++)
		{
			ingredientList.add(recipe1.getIngredientList().get(i));
		}

		stepListView.setItems(stepList);
		for (int j = 0; j < recipe1.getStepList().size(); j++)
		{
			stepList.add(recipe1.getStepList().get(j));
		}

		ArrayList<Label> categoryLabel = new ArrayList<Label>();

		Label city = new Label(recipe1.getCategory().getCity());
		if (city.getText() != null)
		{
			categoryLabel.add(city);
		}

		String str = new String();
		String[] arr;
		str = recipe1.getCategory().getTaste();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (String ss : arr)
			{
				Label temp = new Label(ss);
				categoryLabel.add(temp);
			}

		}

		str = recipe1.getCategory().getMealtime();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (String ss : arr)
			{
				Label temp = new Label(ss);
				categoryLabel.add(temp);
			}

		}

		if (recipe1.getCategory().isVegetarian() == false)
		{
			str = recipe1.getCategory().getMeat();
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

		if (recipe1.getPhotoRoute() != null)
		{
			FileInputStream fis = new FileInputStream(recipe1.getPhotoRoute());
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
		addTextLimiter(serveNumTextField, 9);
		isNullPromptForString(serveNumTextField);
		inputOnlyNumber(serveNumTextField);
	}

	/**
	 * changeServeNum create a new Recipe object which represents the changed recipe
	 * 
	 * @param recipe
	 * @param serveNum
	 * @return Recipe
	 * @author Liu Yanran
	 * @throws CloneNotSupportedException 
	 */
	public static Recipe changeServeNum(Recipe recipe, int serveNum) throws CloneNotSupportedException
	{
		
		int n = recipe.getServeNum();
		recipe.setServeNum(serveNum);
		
		ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>) recipe.getIngredientList().clone();
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
	
	/**
	 * by set the textfield the length limit to avoid the length error with database.
	 * int(11)in database means maximum is 2147483647
	 * @param tf
	 * @param maxLength
	 * @param l
	 * @param location
	 */
	public void addTextLimiter( TextField tf,int maxLength ) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	               
	            }
	        }
	    });
	}
	/*
	 * add Listener to the textfield and set restrictions
	 */
	public void isNullPromptForString(TextField tf)
	{

		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (tf.getText() == null || tf.getText().length() == 0)
				{
					tf.setText(String.valueOf(recipe.getServeNum()));
				}
				else
				{
					
				}
			}

		});
	}
	
	/*
	 * add Listener to the textfield and set restrictions
	 */
	
	public void inputOnlyNumber(TextField tf)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (tf.getText().matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
				{
					
				}
				else
				{
					
					tf.setText("");
				}
			}
		});
	}
	

}
