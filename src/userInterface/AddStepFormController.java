package userInterface;

import java.util.ArrayList;

import businessLayer.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class AddStepFormController
{
	@FXML
	private Button addStep_Button;

	@FXML
	private VBox vBox;

	@FXML
	private Button submit_Button;

	@FXML
	private Button next_Button;

	private Main application;
	private ArrayList<TextField> bufferStepList = new ArrayList<TextField>();
	private ArrayList<String> bufferList = new ArrayList<String>();
	private ArrayList<Label> bufferOrder = new ArrayList<Label>();
	private ArrayList<Pane> upPaneList = new ArrayList<Pane>();
	private ArrayList<Pane> downPaneList = new ArrayList<Pane>();
	private String Step = new String();
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

	public String getStep()
	{
		return Step;
	}

	public void setStep(String step)
	{
		Step = step;
	}

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	void goToAddCategory(ActionEvent event)
	{
		for (int i = 0; i < bufferStepList.size(); i++)
		{
			bufferList.add(bufferStepList.get(i).getText());
		}
		recipe.setStepList(bufferList);
		application.gotoCategory(flag_source, recipe);
	}

	@FXML
	void submitStep(ActionEvent event) throws Exception
	{
		for (int i = 0; i < bufferStepList.size(); i++)
		{
			bufferList.add(bufferStepList.get(i).getText());
		}
		recipe.setStepList(bufferList);
		application.gotoSetIngredient(flag_source,recipe);
	}

	public void addNewStep() throws Exception
	{
		Pane pane = new Pane();
		HBox hBox = new HBox();
		Pane orderPane = new Pane();
		Pane stepPane = new Pane();
		Pane deletePane = new Pane();

		Pane upPane = new Pane();
		Pane downPane = new Pane();

		upPaneList.add(upPane);
		downPaneList.add(downPane);

		Button up = new Button("up");
		Button down = new Button("down");
		up.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
		up.setMinHeight(20);
		up.setMinWidth(40);
		down.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
		down.setMinHeight(20);
		down.setMinWidth(40);
		upPane.getChildren().add(up);
		downPane.getChildren().add(down);// 这些是添加上下按钮的

		Label order = new Label(String.valueOf(bufferOrder.size() + 1));
		bufferOrder.add(order);

		TextField stepTextField = new TextField(Step);
		bufferStepList.add(stepTextField);
		isNullPromptForString(stepTextField, String.valueOf(bufferStepList.indexOf(stepTextField)+1));

		stepTextField.setMinWidth(500);

		

		Button delete = new Button("delete");

		DropShadow shadow = new DropShadow();
		order.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
		order.setTextFill(Color.BROWN);
		delete.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
		delete.setMinHeight(20);
		delete.setMinWidth(40);
		delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delete.setEffect(shadow);
		});
		delete.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delete.setEffect(null);
		});
		delete.setStyle("-fx-background-color: LightGray; -fx-text-fill: brown;");

		orderPane.getChildren().add(order);
		stepPane.getChildren().add(stepTextField);
		deletePane.getChildren().add(delete);

		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: brown;");
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setMinHeight(40);
		hBox.setMinWidth(785);
		hBox.setSpacing(18);
		hBox.getChildren().addAll(orderPane, stepPane, deletePane, upPane, downPane);

		for (int i = 0; i < upPaneList.size(); i++)
		{
			upPaneList.get(i).setVisible(true);
			downPaneList.get(i).setVisible(true);
		}

		upPaneList.get(0).setVisible(false);
		downPaneList.get(upPaneList.size() - 1).setVisible(false);

		pane.getChildren().add(hBox);
		vBox.getChildren().add(pane);
		pane.setMinWidth(785);
		pane.setStyle("-fx-background-color: BEIGE;");

		delete.setOnAction(ex -> {
			vBox.getChildren().remove(pane);
			bufferStepList.remove(stepTextField);
			bufferOrder.remove(order);
			upPaneList.remove(upPane);
			downPaneList.remove(downPane);
			for (int i = 0; i < bufferOrder.size(); i++)
			{
				bufferOrder.get(i).setText(String.valueOf(i + 1));
			}
			
			for (int i = 0; i < upPaneList.size(); i++)
			{
				upPaneList.get(i).setVisible(true);
				downPaneList.get(i).setVisible(true);
			}

			if(upPaneList.size()>0)
			{
			upPaneList.get(0).setVisible(false);
			downPaneList.get(upPaneList.size() - 1).setVisible(false);
			}
		});

		up.setOnAction(ex1 -> {
			String temp = new String();
			int currentStepIndex = bufferStepList.indexOf(stepTextField);
			temp = bufferStepList.get(currentStepIndex - 1).getText();
			bufferStepList.get(currentStepIndex - 1).setText(bufferStepList.get(currentStepIndex).getText());
			bufferStepList.get(currentStepIndex).setText(temp);
		});
		down.setOnAction(ex2 -> {
			String temp = new String();
			int currentStepIndex = bufferStepList.indexOf(stepTextField);
			temp = bufferStepList.get(currentStepIndex + 1).getText();
			bufferStepList.get(currentStepIndex + 1).setText(bufferStepList.get(currentStepIndex).getText());
			bufferStepList.get(currentStepIndex).setText(temp);
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
					System.out.println("第" + location + "行输入值不能为空！！！");
				}

			}

		});
	}

}
