package solitoryGourmet_DigitalCookbook;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
	private Pane pane_ingredientPane;

	@FXML
	private Label label_addIngredientName;

	@FXML
	private Label label_addIngredientAmount;

	@FXML
	private Label label_addIngredientUnit;

	@FXML
	private Label label_addIngredientDescription;

	@FXML
	private TextField textField_ingredientNameField;

	@FXML
	private TextField textField_ingredientAmountField;

	@FXML
	private TextField textField_ingredientUnitField;

	@FXML
	private TextField textField_ingredientDescriptionField;

	@FXML
	private Button button_addIngredient;

	@FXML
	private Label label_addSteps;

	@FXML
	private Pane pane_stepPane;

	@FXML
	private TextField textField_stepField1;

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

	@FXML
	private VBox vBoxIngredient;

	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<String> stepBufferList = new ArrayList<String>();
	private Recipe recipe = new Recipe();

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
			application.gotoSetIngredient(ingredientList);
			
//			recipe.setRecipeName(textField_recipeNameField.getText());
//			recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
//			recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
//			recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));
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
		String step = textField_stepField1.getText();
		stepBufferList.add(step);
		application.gotoEditStep(stepBufferList);

	}

	@FXML
	public void Submit()
	{
		try
		{
			recipe.setIngredientList(ingredientList);
			recipe.setStepList(stepBufferList);
			
//			recipe.setRecipeName(textField_recipeNameField.getText());
//			recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
//			recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
//			recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));

			System.out.println(recipe);

			// 一定需要使用try-catch，不然编译器不会让你过的，Trust me!
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
		// TODO Auto-generated method stub
		
	}

}
