package userInterface;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import businessLayer.Category;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddRecipeFormController implements Initializable
{
	private Main application;

	@FXML
	private AnchorPane pane_add;

	@FXML
	private Label label_addRecipeName;

	@FXML
	private Label label_addServeNum;

	@FXML
	private Label label_addPrepTime;

	@FXML
	private Label label_addCookTime;

	@FXML
	private TextField textField_recipeNameField;

	@FXML
	private TextField textField_serveNumField;

	@FXML
	private TextField textField_prepTimeField;

	@FXML
	private TextField textField_cookTimeField;

	@FXML
	private Label lable_prepMin;

	@FXML
	private Label label_cookMin;

	@FXML
	private Button button_returnHome;

	@FXML
	private Button button_addIngredient;

	@FXML
	private Button upLoadPicture_Button;

	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<String> stepBufferList = new ArrayList<String>();
	private Category category = new Category();
	private String flag_source = new String();
	
	public String getFlag_source()
	{
		return flag_source;
	}

	public void setFlag_source(String flag_source)
	{
		this.flag_source = flag_source;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	private Recipe recipe = new Recipe();
	private File photoFile;

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;

	}

	public void initialRecipeForm(Recipe recipe)
	{
		textField_recipeNameField.setText(recipe.getRecipeName());
		textField_serveNumField.setText(String.valueOf(recipe.getServeNum()));
		textField_prepTimeField.setText(String.valueOf(recipe.getPreparationTime()));
		textField_cookTimeField.setText(String.valueOf(recipe.getCookingTime()));

	}

	public ArrayList<String> getStepBufferList()
	{
		return stepBufferList;
	}

	public void setStepBufferList(ArrayList<String> stepBufferList)
	{
		this.stepBufferList = stepBufferList;
	}

	public ArrayList<Ingredient> getIngredientList()
	{
		return ingredientList;
	}

	public void setIngredientList(ArrayList<Ingredient> ingredientList)
	{
		this.ingredientList = ingredientList;
	}

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	public void gotoHome()
	{
		application.gotoMainInterface();
	}

	public void isNullPromptForString(TextField tf, String location)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				// TODO Auto-generated method stub
				if (tf.getText() == null || tf.getText().length() == 0)
				{
					System.out.println(location + "输入值不能为空！！！");
				}
			}
		});
	}

	public void inputOnlyNumber(TextField tf)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				// TODO Auto-generated method stub
				if (tf.getText().matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?"))
				{

				}
				else
				{
					System.out.println("只能输入数字！！！");
					// tf.setText("");
				}

			}

		});
	}

	public void setPrompt()
	{
		this.isNullPromptForString(textField_recipeNameField, "recipeName");
		this.isNullPromptForString(textField_cookTimeField, "cookTime");
		this.isNullPromptForString(textField_prepTimeField, "prepTime");
		this.isNullPromptForString(textField_serveNumField, "serveNum");
		this.inputOnlyNumber(textField_prepTimeField);
		this.inputOnlyNumber(textField_cookTimeField);
		this.inputOnlyNumber(textField_serveNumField);
	}

	public void addIngredient()
	{
		try
		{
			recipe.setRecipeName(textField_recipeNameField.getText());
			recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
			recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
			recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));
			recipe.setIngredientList(ingredientList);
			recipe.setStepList(stepBufferList);
			recipe.setCategory(category);
			
			application.gotoSetIngredient(flag_source,recipe);
			System.out.println(recipe.getPhotoRoute());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @FXML
	// public void addStep() throws Exception
	// {
	// recipe.setRecipeName(textField_recipeNameField.getText());
	// recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
	// recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
	// recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));
	// recipe.setIngredientList(ingredientList);
	// recipe.setStepList(stepBufferList);
	// application.gotoEditStep(recipe);
	// }

	@FXML
	void upLoadPicture(ActionEvent event) throws Exception
	{
		getPhotoRoute();
		showPhoto();
		System.out.println(recipe.getPhotoRoute());
	}

	/*
	 * filter FileChoser with only the photo
	 */
	private void getPhotoRoute()
	{
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		fc.setTitle("plz select one photo");
		Stage photoStage = null;
		photoFile = fc.showOpenDialog(photoStage);
		recipe.setPhotoRoute(String.valueOf(photoFile.getAbsolutePath()));
	}

	private void showPhoto() throws Exception
	{

		Pane photoPane = new Pane();
		FileInputStream fis = new FileInputStream(String.valueOf(recipe.getPhotoRoute()));

		Image image = new Image(fis);
		ImageView iv = new ImageView(image);
		iv.setFitWidth(200);
		iv.setFitHeight(150);
		photoPane.getChildren().add(iv);
		Scene scene = new Scene(photoPane, 200, 150);
		Stage stagePhoto = new Stage();
		stagePhoto.setTitle("photoPreview");
		stagePhoto.setScene(scene);
		stagePhoto.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}

}
