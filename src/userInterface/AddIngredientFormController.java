package userInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.tools.DocumentationTool.Location;

import org.omg.CORBA.PUBLIC_MEMBER;

import businessLayer.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class AddIngredientFormController implements Initializable
{
	@FXML
	private VBox vBox;

	@FXML
	private Button addIngredient_Button;

	@FXML
	private Button ingredientSubmit;

	@FXML
	private Button next_Button;

	private Main application;
	public Boolean deteleFlag = false;
	ArrayList<Ingredient> bufferList = new ArrayList<Ingredient>();
	private ArrayList<TextField> nameList = new ArrayList<TextField>();
	private ArrayList<TextField> amountList = new ArrayList<TextField>();
	private ArrayList<TextField> unitList = new ArrayList<TextField>();
	private ArrayList<TextField> descriptionList = new ArrayList<TextField>();

	private Ingredient ingredient = new Ingredient();
	private Recipe recipe = new Recipe();
	private String flag_source = new String();

	public String getFlag_source()
	{
		return flag_source;
	}

	public void setFlag_source(String flag_source)
	{
		this.flag_source = flag_source;
	}

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
	}

	public Ingredient getIngredient()
	{
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient)
	{
		this.ingredient = ingredient;
	}

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	void goToAddStep(ActionEvent event) throws Exception
	{
		for (int i = 0; i < nameList.size(); i++)
		{
			Ingredient temp = new Ingredient();
			temp.setIngredientName(nameList.get(i).getText());
			temp.setIngredientAmount(Double.valueOf(amountList.get(i).getText()));
			temp.setUnit(unitList.get(i).getText());
			temp.setDescription(descriptionList.get(i).getText());
			bufferList.add(temp);
		}
		recipe.setIngredientList(bufferList);
		application.gotoEditStep(flag_source,recipe);
	}

	@FXML
	void ingredientSubmit(ActionEvent event)
	{
		for (int i = 0; i < nameList.size(); i++)
		{
			Ingredient temp = new Ingredient();
			temp.setIngredientName(nameList.get(i).getText());
			temp.setIngredientAmount(Double.valueOf(amountList.get(i).getText()));
			temp.setUnit(unitList.get(i).getText());
			temp.setDescription(descriptionList.get(i).getText());
			bufferList.add(temp);
		}
		recipe.setIngredientList(bufferList);
		application.goBackToAddRecipeForm(flag_source,recipe);
	}

	public void addNewIngredient() throws Exception
	{

		Pane pane = new Pane();
		HBox hBox = new HBox();
		VBox vBox_item = new VBox();
		HBox descripH = new HBox();

		Label nameLable = new Label("Name");
		TextField nameTextField = new TextField(ingredient.getIngredientName());
		isNullPromptForString(nameTextField, "nameTextField");

		Label amountLable = new Label("Amount");
		TextField ammountTextField = new TextField(String.valueOf(ingredient.getIngredientAmount()));
		isNullPromptForString(ammountTextField, "amountField");
		inputOnlyNumber(ammountTextField);

		Label unitLable = new Label("Unit");
		TextField unitTextField = new TextField(ingredient.getUnit());
		isNullPromptForString(unitTextField, "unitField");

		Label description = new Label("Description");
		TextField descriptionTextField = new TextField(ingredient.getDescription());
		isNullPromptForString(descriptionTextField, "descriptionField");

		Button delete = new Button("delete");

		nameList.add(nameTextField);
		amountList.add(ammountTextField);
		unitList.add(unitTextField);
		descriptionList.add(descriptionTextField);

		DropShadow shadow = new DropShadow();
		delete.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
		delete.setMinHeight(40);
		delete.setMinWidth(80);
		delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delete.setEffect(shadow);
		});
		delete.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delete.setEffect(null);
		});
		delete.setStyle("-fx-background-color: LightGray; -fx-text-fill: brown;");

		vBox_item.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: brown;");
		vBox_item.setMinWidth(785);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setMinHeight(80);
		hBox.setMaxWidth(785);
		hBox.setSpacing(10);

		hBox.getChildren().addAll(nameLable, nameTextField, amountLable, ammountTextField, unitLable, unitTextField,
				delete);
		descripH.getChildren().addAll(description, descriptionTextField);
		descripH.setSpacing(10);
		descripH.setPadding(new Insets(10, 10, 10, 10));
		vBox_item.getChildren().addAll(hBox, descripH);
		pane.getChildren().add(vBox_item);
		vBox.getChildren().add(pane);
		pane.setMinWidth(785);
		pane.setStyle("-fx-background-color: BEIGE;");

		delete.setOnAction(ex -> {
			vBox.getChildren().remove(pane);
			nameList.remove(nameTextField);
			amountList.remove(ammountTextField);
			unitList.remove(unitTextField);
			descriptionList.remove(descriptionTextField);
		});

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

	public void Init() throws IOException
	{

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}

}
