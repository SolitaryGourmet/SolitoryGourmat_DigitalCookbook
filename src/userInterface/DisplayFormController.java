package userInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class DisplayFormController
{
	private Recipe recipe;
	private Main application;

	@FXML
	private Label recipeNameLabel;

	@FXML
	private ImageView image;

	@FXML
	private TextField serveNumTextField;

	@FXML
	private Button change;

	@FXML
	private Label preparationTimeLable;

	@FXML
	private Label cookingTimeLabel;

	@FXML
	private Label categoryLabel;

	@FXML
	private TextArea ingredientTextArea;

	@FXML
	private TextArea discriptionTextArea;

	@FXML
	private Button returnToMinWin;

	@FXML
	private Button backToResultForm;

	@FXML
	private Button edit;

	@FXML
	private Button delete;

	@FXML
	void changeServeNum(ActionEvent event)
	{
		recipe=changeServeNum(recipe,  Integer.parseInt(serveNumTextField.getText()));
		serveNumTextField.setText(Integer.toString(recipe.getServeNum()));
		ingredientTextArea.setText(recipe.getIngredientList().toString());
	}

	@FXML
	void backToResultForm(ActionEvent event)
	{
		//application.gotoSearchResult();
	}

	@FXML
	void deleteRecipe(ActionEvent event)
	{

	}

	@FXML
	void editRecipe(ActionEvent event)
	{

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
	 */
	public void setDisplayFormView()
	{
		recipeNameLabel.setText(recipe.getRecipeName());
		serveNumTextField.setText(Integer.toString(recipe.getServeNum()));
		preparationTimeLable.setText(Integer.toString(recipe.getPreparationTime()));
		cookingTimeLabel.setText(Integer.toString(recipe.getCookingTime()));
		ingredientTextArea.setText(recipe.getIngredientList().toString());
		discriptionTextArea.setText(recipe.getStepList().toString());
	}

	public void setApp(Main application)
	{
		this.application = application;
	}

	/**
	 * changeServeNum
	 * create a new Recipe object which represents the changed recipe
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
