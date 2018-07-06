package controller;

/**
 * this controller set the name,number,preparation time,cooking time and picture of the recipe
 * @author LUO YIFAN, CUI XIAO, LIU YANRAN
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import businessLayer.Category;
import businessLayer.Ingredient;
import businessLayer.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import userInterface.Main;

public class AddRecipeFormController
{

	private Main application;

	@FXML
	private AnchorPane pane_add;

	@FXML
	private Button button_returnHome;

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
	private Button upLoadPicture_Button;

	@FXML
	private Label picture_path;

	@FXML
	private Label recipeName_akn;

	@FXML
	private Label serveNum_akn;

	@FXML
	private Label prepTime_akn;

	@FXML
	private Label cookTime_akn;

	@FXML
	private Button button_addIngredient;

	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<String> stepBufferList = new ArrayList<String>();
	private Category category = new Category();
	private String flag_source = new String();
	private Recipe recipe = new Recipe();
	private File photoFile;
	private Boolean[] next_flag = new Boolean[4];

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

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
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

	/**
	 * for edit process and set the data according to the transfered recipe
	 * 
	 * @param recipe
	 */
	public void initialRecipeForm(Recipe recipe)
	{
		textField_recipeNameField.setText(recipe.getRecipeName());
		textField_serveNumField.setText(String.valueOf(recipe.getServeNum()));
		textField_prepTimeField.setText(String.valueOf(recipe.getPreparationTime()));
		textField_cookTimeField.setText(String.valueOf(recipe.getCookingTime()));
		picture_path.setText(recipe.getPhotoRoute());
	}

	/**
	 * go back to main interface and also jump an extra confirm window
	 */
	@FXML
	public void gotoHome()
	{
		FXMLLoader root = application.getRoot("AddEditToMinWinConfirm.fxml");
		Stage stage = new Stage();
		stage.setTitle("Confirm Exit");
		stage.setScene(new Scene(root.getRoot()));
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/picture/Logo.png")));
		stage.show();

		AddEditToMinWinConfirmController c = root.getController();
		c.getGoToHome_Button().setOnAction(ex -> {
			application.gotoMainInterface();
			stage.close();
		});
		
		c.getCancel_Button().setOnAction(ex -> {
			stage.close();
		});		
	}

	/**
	 * initialize all the flag for next button
	 */
	public void initalNext_flag()
	{
		for (int i = 0; i < next_flag.length; i++)
		{
			next_flag[i] = true;
		}
	}

	/**
	 * add Listener to the textfield and set restrictions
	 */
	public void isNullPromptForString(TextField tf, String location)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				/**
				 * if textField has no content a text will appear to show the type of error and
				 * set the next_flag to false until the error is corrected
				 */

				if (tf == textField_recipeNameField)
				{
					if (tf.getText() == null || tf.getText().length() == 0)
					{
						recipeName_akn.setText("This value cannot be empty!");
						next_flag[0] = false;
					}
					else
					{
						recipeName_akn.setText("");
						next_flag[0] = true;
					}
				}

				if (tf == textField_serveNumField)
				{
					if (tf.getText() == null || tf.getText().length() == 0)
					{
						serveNum_akn.setText("This value cannot be empty!");
						next_flag[1] = false;
					}
					else
					{
						serveNum_akn.setText("");
						next_flag[1] = true;
					}
				}

				if (tf == textField_prepTimeField)
				{
					if (tf.getText() == null || tf.getText().length() == 0)
					{
						prepTime_akn.setText("This value cannot be empty!");
						next_flag[2] = false;
					}
					else
					{
						prepTime_akn.setText("");
						next_flag[2] = true;
					}
				}

				if (tf == textField_cookTimeField)
				{
					if (tf.getText() == null || tf.getText().length() == 0)
					{
						cookTime_akn.setText("This value cannot be empty!");
						next_flag[3] = false;
					}
					else
					{
						cookTime_akn.setText("");
						next_flag[3] = true;
					}
				}
			}
		});
	}

	/**
	 * add Listener to the textfield and set restrictions
	 */
	public void inputOnlyNumber(TextField tf)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				/**
				 * if textField's content are not numbers a text will appear to show the type of
				 * error and set the next_flag to false until it is corrected
				 * 
				 */
				if (tf == textField_serveNumField)
				{
					if (tf.getText().matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
					{
						serveNum_akn.setText("");
						next_flag[1] = true;
					}
					else
					{
						serveNum_akn.setText("This value must be a positive number!");
						tf.setText("");
						next_flag[1] = false;
					}
				}

				if (tf == textField_prepTimeField)
				{
					if (tf.getText().matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
					{
						prepTime_akn.setText("");
						next_flag[2] = true;
					}
					else
					{
						prepTime_akn.setText("This value must be a positive number!");
						tf.setText("");
						next_flag[2] = false;

					}
				}

				if (tf == textField_cookTimeField)
				{
					if (tf.getText().matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
					{
						cookTime_akn.setText("");
						next_flag[3] = true;
					}
					else
					{
						cookTime_akn.setText("This value must be a positive number!");
						tf.setText("");
						next_flag[3] = false;
					}
				}

			}

		});
	}

	/**
	 * add Listener to these textfields
	 */
	public void setPrompt()
	{
		this.isNullPromptForString(textField_recipeNameField, "recipeName");
		this.isNullPromptForString(textField_cookTimeField, "cookTime");
		this.isNullPromptForString(textField_prepTimeField, "prepTime");
		this.isNullPromptForString(textField_serveNumField, "serveNum");
		this.inputOnlyNumber(textField_prepTimeField);
		this.inputOnlyNumber(textField_cookTimeField);
		this.inputOnlyNumber(textField_serveNumField);
		this.addTextLimiter(textField_recipeNameField, 30);// set the length limit is 30
		this.addTextLimiter(textField_cookTimeField, 9);// set the length limit is 9
		this.addTextLimiter(textField_prepTimeField, 9);// set the length limit is 9
		this.addTextLimiter(textField_serveNumField, 9);// set the length limit is 9

	}

	/**
	 * go to add ingredient step (interface)
	 */
	public void addIngredient()
	{
		try
		{
			boolean[] flag = new boolean[4];
			if (textField_recipeNameField.getText() == null || textField_recipeNameField.getText().length() == 0)
			{
				recipeName_akn.setText("This value cannot be empty!");
				flag[0] = false;
			}
			else
			{
				flag[0] = true;
			}
			if (textField_serveNumField.getText() == null || textField_serveNumField.getText().length() == 0)
			{
				serveNum_akn.setText("This value cannot be empty!");
				flag[1] = false;
			}
			else
			{
				flag[1] = true;
			}
			if (textField_prepTimeField.getText() == null || textField_prepTimeField.getText().length() == 0)
			{
				prepTime_akn.setText("This value cannot be empty!");
				flag[2] = false;
			}
			else
			{
				flag[2] = true;
			}
			if (textField_cookTimeField.getText() == null || textField_cookTimeField.getText().length() == 0)
			{
				cookTime_akn.setText("This value cannot be empty!");
				flag[3] = false;
			}
			else
			{
				flag[3] = true;
			}

			if ((flag[0] && flag[1] && flag[2] && flag[3] && next_flag[0] && next_flag[1] && next_flag[2]
					&& next_flag[3]) == true)
			{
				recipe.setRecipeName(textField_recipeNameField.getText());
				recipe.setServeNum(Integer.valueOf(textField_serveNumField.getText()));
				recipe.setPreparationTime(Integer.valueOf(textField_prepTimeField.getText()));
				recipe.setCookingTime(Integer.valueOf(textField_cookTimeField.getText()));
				recipe.setIngredientList(ingredientList);
				recipe.setStepList(stepBufferList);
				recipe.setCategory(category);
				application.gotoSetIngredient(flag_source, recipe);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * submit a picture
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void upLoadPicture(ActionEvent event) throws Exception
	{
		getPhotoRoute();
		picture_path.setText(recipe.getPhotoRoute());
	}

	/**
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

	/**
	 * by set the textfield the length limit to avoid the length error with
	 * database. int(11)in database means maximum is 2147483647
	 * 
	 * @param tf
	 * @param maxLength
	 * @param l
	 * @param location
	 */
	public void addTextLimiter(TextField tf, int maxLength)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue)
			{
				if (tf.getText().length() > maxLength)
				{
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}
			}
		});
	}
}
