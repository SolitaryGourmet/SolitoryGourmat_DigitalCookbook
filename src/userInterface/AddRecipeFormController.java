package userInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	private Label label_addIngredients;

	@FXML
	private Button button_addIngredient;

	@FXML
	private Label label_addSteps;

	@FXML
	private Button button_addStep;

	@FXML
	private Label label_addPicture;

	@FXML
	private Label label_addCategory;

	@FXML
	private Button button_editPicture;

	@FXML
	private Button button_editCategory;

	@FXML
	private Button button_returnHome;

	@FXML
	private Button button_submitButton;

	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<String> stepBufferList = new ArrayList<String>();
	private Recipe recipe = new Recipe();

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
			application.gotoSetIngredient(recipe);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void addStep() throws Exception
	{
		recipe.setRecipeName(textField_recipeNameField.getText());
		recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
		recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
		recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));
		recipe.setIngredientList(ingredientList);
		recipe.setStepList(stepBufferList);
		application.gotoEditStep(recipe);
	}

	@FXML
	public void Submit()
	{
		try
		{
			recipe.setIngredientList(ingredientList);
			recipe.setStepList(stepBufferList);
			System.out.println(recipe);

			// һ����Ҫʹ��try-catch����Ȼ����������������ģ�Trust me!
			Parent anotherRoot = FXMLLoader.load(getClass().getResource("Acknowledgement.fxml"));
			Stage anotherStage = new Stage();
			anotherStage.setTitle("Another Window Triggered by Clicking");
			anotherStage.setScene(new Scene(anotherRoot, 600, 329));
			anotherStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		
	}

}
