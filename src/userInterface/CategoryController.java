package userInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import businessLayer.Category;
import businessLayer.Recipe;
import databaseLayer.DataBaseControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class CategoryController implements Initializable
{
	private Main application;

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	private Label mainTitle_Label;

	@FXML
	private Pane pane_category;

	@FXML
	private VBox vbox1;

	@FXML
	private Pane pane_taste;

	@FXML
	private Label label_taste;

	@FXML
	private CheckBox Sweet;

	@FXML
	private CheckBox Sour;

	@FXML
	private CheckBox Spicy;

	@FXML
	private CheckBox Salty;

	@FXML
	private CheckBox Light;

	@FXML
	private Pane pane_city;

	@FXML
	private Label label_city;

	@FXML
	private RadioButton Sichuan;

	@FXML
	private RadioButton Hunan;

	@FXML
	private RadioButton Shanghai;

	@FXML
	private RadioButton Beijing;

	@FXML
	private RadioButton Guangdong;

	@FXML
	private RadioButton Other;

	@FXML
	private Pane pane_mealtime;

	@FXML
	private Label label_mealtime;

	@FXML
	private CheckBox Breakfast;

	@FXML
	private CheckBox Lunch;

	@FXML
	private CheckBox Supper;

	@FXML
	private Label label_vegetarian;

	@FXML
	private RadioButton VegeTrue;

	@FXML
	private RadioButton VegeFalse;

	@FXML
	private Pane pane_meet;

	@FXML
	private Label label_meet;

	@FXML
	private CheckBox Pork;

	@FXML
	private CheckBox Chicken;

	@FXML
	private CheckBox Lamb;

	@FXML
	private CheckBox Beef;

	@FXML
	private CheckBox Fish;

	@FXML
	private Button NextStep_Button;

	@FXML
	private Button back;

	private boolean flag_comeFromAddForm = false;
	private Recipe recipe = new Recipe();
	private Category category = new Category();
	ArrayList<CheckBox> flavour = new ArrayList<CheckBox>();
	ArrayList<String> flavourCategory = new ArrayList<String>();
	ArrayList<RadioButton> city = new ArrayList<RadioButton>();
	ToggleGroup groupRadioButton = new ToggleGroup();
	String cityCategory = new String();
	ArrayList<CheckBox> mealTime = new ArrayList<CheckBox>();
	ArrayList<String> mealTimeCategory = new ArrayList<String>();
	ToggleGroup groupVege = new ToggleGroup();
	Boolean vegeCategory = false;
	ArrayList<CheckBox> meat = new ArrayList<CheckBox>();
	ArrayList<String> meatCategory = new ArrayList<String>();
	private String flag_source = new String();

	public String getFlag_source()
	{
		return flag_source;
	}

	public void setFlag_source(String flag_source)
	{
		this.flag_source = flag_source;
	}

	public Button getNextStep_Button()
	{
		return NextStep_Button;
	}

	public void setNextStep_Button(Button nextStep_Button)
	{
		NextStep_Button = nextStep_Button;
	}

	public Label getMainTitle_Label()
	{
		return mainTitle_Label;
	}

	public void setMainTitle_Label(Label mainTitle_Label)
	{
		this.mainTitle_Label = mainTitle_Label;
	}

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
	}

	public boolean isFlag_comeFromAddForm()
	{
		return flag_comeFromAddForm;
	}

	public void setFlag_comeFromAddForm(boolean flag_comeFromAddForm)
	{
		this.flag_comeFromAddForm = flag_comeFromAddForm;
	}

	@FXML
	void backtoPreviousForm(ActionEvent event) throws Exception
	{
		if (flag_source.equals("Add Recipe") || flag_source.equals("Edit Recipe"))
		{
			if (cityCategory.equals(""))
			{
				category.setCity(null);
			}
			else
			{
				category.setCity(cityCategory);
			}

			category.setMealtime(setCategory(mealTimeCategory));
			category.setTaste(setCategory(flavourCategory));
			category.setVegetarian(vegeCategory);

			if (vegeCategory == false)
			{
				category.setMeat(setCategory(meatCategory));
			}
			else
			{
				category.setMeat(null);
			}
			recipe.setCategory(category);
			application.gotoEditStep(flag_source, recipe);
		}
		else if (flag_source.equals("Search Recipe"))
		{
			application.gotoSearchForm();
		}
	}

	public void setCategoryForm(Recipe recipe)
	{
		if (recipe.getCategory().getCity() != null)
		{
			cityCategory = recipe.getCategory().getCity();
			for (int i = 0; i < city.size(); i++)
			{
				if (city.get(i).getText().equals(recipe.getCategory().getCity()))
				{
					city.get(i).setSelected(true);
				}
			}
		}

		String str = new String();
		String[] arr;

		str = recipe.getCategory().getTaste();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (int i = 0; i < flavour.size(); i++)
			{
				for (String ss : arr)
				{

					if (flavour.get(i).getText().equals(ss))
					{
						flavourCategory.add(ss);
						flavour.get(i).setSelected(true);
					}
				}
			}
		}

		str = recipe.getCategory().getMealtime();
		if (str != null)
		{
			arr = str.split("\\s+");
			for (int i = 0; i < mealTime.size(); i++)
			{
				for (String ss : arr)
				{

					if (mealTime.get(i).getText().equals(ss))
					{
						mealTimeCategory.add(ss);
						mealTime.get(i).setSelected(true);
					}
				}
			}
		}

		if (recipe.getCategory().isVegetarian() == false)
		{
			vegeCategory = false;
			VegeFalse.setSelected(true);

			str = recipe.getCategory().getMeat();
			if (str != null)
			{
				arr = str.split("\\s+");
				for (int i = 0; i < meat.size(); i++)
				{
					for (String ss : arr)
					{

						if (meat.get(i).getText().equals(ss))
						{
							meatCategory.add(ss);
							meat.get(i).setSelected(true);
						}
					}
				}
			}
		}
		else
		{
			vegeCategory = true;
			VegeTrue.setSelected(true);
			pane_meet.setVisible(false);
		}

	}

	@FXML
	void setCity(ActionEvent event)
	{
		cityCategory = new String();
		for (int i = 0; i < city.size(); i++)
		{
			if (city.get(i).isSelected())
			{
				cityCategory = city.get(i).getText();
			}
		}
	}

	@FXML
	void setFlavor(ActionEvent event)
	{
		flavourCategory = new ArrayList<String>();
		for (int i = 0; i < flavour.size(); i++)
		{
			if (flavour.get(i).isSelected())
			{
				flavourCategory.add(flavour.get(i).getText());
			}
		}
	}

	@FXML
	void setMealTime(ActionEvent event)
	{
		mealTimeCategory = new ArrayList<String>();
		for (int i = 0; i < mealTime.size(); i++)
		{
			if (mealTime.get(i).isSelected())
			{
				mealTimeCategory.add(mealTime.get(i).getText());
			}
		}
	}

	@FXML
	void setMeat(ActionEvent event)
	{
		meatCategory = new ArrayList<String>();
		for (int i = 0; i < meat.size(); i++)
		{
			if (meat.get(i).isSelected())
			{
				meatCategory.add(meat.get(i).getText());
			}
		}
	}

	@FXML
	void setVegetarian(ActionEvent event)
	{
		if (VegeTrue.isSelected() == true)
		{
			pane_meet.setVisible(false);
			vegeCategory = true;
		}
		else if (VegeFalse.isSelected() == true)
		{
			pane_meet.setVisible(true);
			vegeCategory = false;
		}
	}

	public void Init()
	{
		flavour.add(Sweet);
		flavour.add(Sour);
		flavour.add(Spicy);
		flavour.add(Salty);
		flavour.add(Light);

		city.add(Sichuan);
		city.add(Hunan);
		city.add(Shanghai);
		city.add(Beijing);
		city.add(Guangdong);
		city.add(Other);
		for (int i = 0; i < city.size(); i++)
		{
			city.get(i).setToggleGroup(groupRadioButton);
		}

		mealTime.add(Breakfast);
		mealTime.add(Lunch);
		mealTime.add(Supper);

		VegeTrue.setToggleGroup(groupVege);
		VegeFalse.setToggleGroup(groupVege);
		VegeFalse.setSelected(true);

		meat.add(Pork);
		meat.add(Chicken);
		meat.add(Lamb);
		meat.add(Beef);
		meat.add(Fish);

	}

	public static String setCategory(ArrayList<String> cateList)
	{
		String s = new String();
		if (cateList.size() == 0)
		{
			return null;
		}
		for (int i = 0; i < cateList.size(); i++)
		{
			s += cateList.get(i) + " ";
		}
		return s;
	}

	public void Submit()
	{
		if (cityCategory.equals(""))
		{
			category.setCity(null);
		}
		else
		{
			category.setCity(cityCategory);
		}

		category.setMealtime(setCategory(mealTimeCategory));
		category.setTaste(setCategory(flavourCategory));
		category.setVegetarian(vegeCategory);

		if (vegeCategory == false)
		{
			category.setMeat(setCategory(meatCategory));
		}
		else
		{
			category.setMeat(null);
		}
		System.out.println(category);

		/**
		 * check if the category window is calls from add recipe window if so, the
		 * submit button will submit new recipe to database
		 */
		if (flag_source.equals("Add Recipe"))
		{
			try
			{
				recipe.setCategory(category);
				application.gotoMainInterface();
				System.out.println("Add Recipe");
				DataBaseControl.getConnection();
				DataBaseControl.insertRecipe(recipe);
				DataBaseControl.closeConnection();

				FXMLLoader Loader = new FXMLLoader();
				Loader.setLocation(getClass().getResource("Acknowledgement.fxml"));
				try
				{
					Loader.load();
				}
				catch (Exception ex)
				{

				}

				Stage submitRecipeConfirmStage = new Stage();
				submitRecipeConfirmStage.setTitle("Confirm submit");
				submitRecipeConfirmStage.setScene(new Scene(Loader.getRoot()));
				submitRecipeConfirmStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
				submitRecipeConfirmStage.show();
				AcknowledgementController ac = Loader.getController();
				ac.getCancel_Button().setOnAction(e -> {
					submitRecipeConfirmStage.close();
				});
				ac.getViewRecipe_Button().setOnAction(e -> {
					application.goToDisplayForm(recipe, null, "Add recipe", null);
					submitRecipeConfirmStage.close();
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (flag_source.equals("Search Recipe"))
		{
			recipe = new Recipe();
			recipe.setCategory(category);
			System.out.println("Search by category");
			DataBaseControl.getConnection();
			ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
			recipeList = DataBaseControl.searchByCategory(category);
			for (int i = 0; i < recipeList.size(); i++)
			{
				System.out.println(recipeList.get(i).getRecipeName());
			}
			application.gotoSearchResult("Search Category",recipeList,recipe);

			DataBaseControl.closeConnection();
		}
		else if (flag_source.equals("Edit Recipe"))
		{
			recipe.setCategory(category);
			System.out.println("Edit Recipe");
			application.gotoMainInterface();
			
			DataBaseControl.getConnection();
			DataBaseControl.editRecipe(recipe);
			DataBaseControl.closeConnection();

			FXMLLoader Loader = new FXMLLoader();
			Loader.setLocation(getClass().getResource("Acknowledgement.fxml"));
			try
			{
				Loader.load();
			}
			catch (Exception ex)
			{

			}

			Stage submitRecipeConfirmStage = new Stage();
			submitRecipeConfirmStage.setTitle("Confirm submit");
			submitRecipeConfirmStage.setScene(new Scene(Loader.getRoot()));
			submitRecipeConfirmStage.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
			submitRecipeConfirmStage.show();
			AcknowledgementController ac = Loader.getController();
			ac.getCancel_Button().setOnAction(e -> {
				submitRecipeConfirmStage.close();
			});
			ac.getViewRecipe_Button().setOnAction(e -> {
				application.goToDisplayForm(recipe, null, "Add recipe", null);
				submitRecipeConfirmStage.close();
			});
			
			
		}

	}

	public void initialize(URL location, ResourceBundle resources)
	{
		this.Init();
	}
}
