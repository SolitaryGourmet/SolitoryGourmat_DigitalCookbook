package userInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import businessLayer.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

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
	private boolean[][] nextFlag = new boolean[200][3];
	private Main application;
	ArrayList<Ingredient> bufferList = new ArrayList<Ingredient>();
//	private ArrayList<Pane> paneList = new ArrayList<Pane>();
	private ArrayList<TextField> nameList = new ArrayList<TextField>();
	private ArrayList<Label> nameAknList = new ArrayList<Label>();
	private ArrayList<TextField> amountList = new ArrayList<TextField>();
	private ArrayList<Label> amountAknList = new ArrayList<Label>();
	private ArrayList<TextField> unitList = new ArrayList<TextField>();
	private ArrayList<Label> unitAknList = new ArrayList<Label>();
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
		for(int i =0;i<nameList.size();i++)
		{
			if(nameList.get(i).getText()==null||nameList.get(i).getLength()==0)
			{
				nameAknList.get(i).setText("This value cannot be empty!");
				nextFlag[i][0]=false;
			}
			else
			{
				nextFlag[i][0]=true;
			}
			if(amountList.get(i).getText()==null||amountList.get(i).getLength()==0)
			{
				amountAknList.get(i).setText("This value cannot be empty!");
				nextFlag[i][1]=false;
			}
			else
			{
				nextFlag[i][1]=true;
			}
			if(unitList.get(i).getText()==null||unitList.get(i).getLength()==0)
			{
				unitAknList.get(i).setText("This value cannot be empty!");
				nextFlag[i][2]=false;
			}
			else
			{
				nextFlag[i][2]=true;
			}
		}
		
		boolean next = true;
		for(int i =0;i<nameList.size();i++)
		{
			next=nextFlag[i][0]&&nextFlag[i][1]&&nextFlag[i][2]&&next;
		}
		
		
		if (next == true && nameList.size()>0)
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
			application.gotoEditStep(flag_source, recipe);
		}
		else
		{
			System.out.println("something must be missing");
		}
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
		application.goBackToAddRecipeForm(flag_source, recipe);
	}

	public void addNewIngredient() throws Exception
	{
		Pane pane = new Pane();
		VBox vBox_item = new VBox();
		HBox descripH = new HBox();
		GridPane gridPane = new GridPane();

		Label ingredientName_akn = new Label();
		Label ingredientAmount_akn = new Label();
		Label ingredientUnit_akn = new Label();

		Label nameLable = new Label("Name");
		Label amountLable = new Label("Amount");
		Label unitLable = new Label("Unit");
		Label description = new Label("Description");

		TextField nameTextField = new TextField(ingredient.getIngredientName());
		TextField amountTextField = new TextField(String.valueOf(ingredient.getIngredientAmount()));
		TextField unitTextField = new TextField(ingredient.getUnit());
		TextField descriptionTextField = new TextField(ingredient.getDescription());

		Button delete = new Button("delete");

		nameList.add(nameTextField);
		nameAknList.add(ingredientName_akn);
		amountList.add(amountTextField);
		amountAknList.add(ingredientAmount_akn);
		unitList.add(unitTextField);
		unitAknList.add(ingredientUnit_akn);
		descriptionList.add(descriptionTextField);

		ingredientName_akn.setFont(Font.font("Ebrima", FontPosture.ITALIC, 12));
		ingredientName_akn.setStyle("-fx-text-fill: #a42e2e");
		ingredientAmount_akn.setFont(Font.font("Ebrima", FontPosture.ITALIC, 12));
		ingredientAmount_akn.setStyle("-fx-text-fill: #a42e2e");
		ingredientUnit_akn.setFont(Font.font("Ebrima", FontPosture.ITALIC, 12));
		ingredientUnit_akn.setStyle("-fx-text-fill: #a42e2e");

		nameLable.setFont(Font.font("Ebrima", FontPosture.ITALIC, 13));
		isNullPromptForString(nameTextField, "nameTextField", ingredientName_akn);

		amountLable.setFont(Font.font("Ebrima", FontPosture.ITALIC, 13));
		isNullPromptForString(amountTextField, "amountField", ingredientAmount_akn);
		inputOnlyNumber(amountTextField, ingredientAmount_akn);

		unitLable.setFont(Font.font("Ebrima", FontPosture.ITALIC, 13));
		isNullPromptForString(unitTextField, "unitField", ingredientUnit_akn);

		description.setFont(Font.font("Ebrima", FontPosture.ITALIC, 13));
		descriptionTextField.setPrefWidth(550);

		DropShadow shadow = new DropShadow();
		delete.setFont(Font.font("Ebrima", 14));
		delete.setMinHeight(40);
		delete.setMinWidth(80);
		delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delete.setEffect(shadow);
		});
		delete.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delete.setEffect(null);
		});
		delete.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14pt; -fx-padding: 5");

		gridPane.add(nameLable, 0, 0);
		gridPane.add(nameTextField, 1, 0);
		gridPane.add(amountLable, 2, 0);
		gridPane.add(amountTextField, 3, 0);
		gridPane.add(unitLable, 4, 0);
		gridPane.add(unitTextField, 5, 0);
		gridPane.add(delete, 6, 0);
		gridPane.add(ingredientName_akn, 1, 1);
		gridPane.add(ingredientAmount_akn, 3, 1);
		gridPane.add(ingredientUnit_akn, 5, 1);
		gridPane.setMinHeight(60);
		gridPane.setMinWidth(750);
		gridPane.setHgap(15);

		descripH.getChildren().addAll(description, descriptionTextField);
		descripH.setSpacing(10);
		descripH.setPadding(new Insets(5, 5, 5, 5));

		vBox_item.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: FireBrick;");
		vBox_item.setMinWidth(750);
		vBox_item.getChildren().addAll(gridPane, descripH);
		pane.getChildren().add(vBox_item);
		vBox.getChildren().add(pane);
		pane.setMinWidth(750);
		pane.setStyle("-fx-background-color: BEIGE;");

		delete.setOnAction(ex -> {
			vBox.getChildren().remove(pane);
			nameList.remove(nameTextField);
			amountList.remove(amountTextField);
			unitList.remove(unitTextField);
			descriptionList.remove(descriptionTextField);
			nameAknList.remove(ingredientName_akn);
			amountAknList.remove(ingredientAmount_akn);
			unitAknList.remove(ingredientUnit_akn);
			
		});

	}

	public void isNullPromptForString(TextField tf, String location, Label l)
	{
		
		tf.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				// TODO Auto-generated method stub
				if (tf.getText() == null || tf.getText().length() == 0)
				{
					l.setText("This value cannot be empty!");
				}
				else
				{
					l.setText("");
				}
			}

		});
	}

	public void inputOnlyNumber(TextField tf, Label l)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (tf.getText().matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?"))
				{
					l.setText("");
				}
				else
				{
					l.setText("This value must be a number!");
					tf.setText("");
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
