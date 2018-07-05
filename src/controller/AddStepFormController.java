package controller;

/*
 * this controller set the step of the recipe
 */
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
import userInterface.Main;

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
	private ArrayList<Label> aknList = new ArrayList<Label>();
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
		boolean[] nextFlag = new boolean[100];
		boolean next = true;
		for (int i = 0; i < bufferStepList.size(); i++)
		{
			if (bufferStepList.get(i).getText() == null || bufferStepList.get(i).getLength() == 0)
			{
				aknList.get(i).setText("            This line cannot be empty!");
				nextFlag[i] = false;
			}
			else
			{
				nextFlag[i] = true;
			}
		}

		for (int i = 0; i < bufferStepList.size(); i++)
		{
			next = nextFlag[i] && next;
		}

		if (next == true && bufferStepList.size() > 0)
		{
			for (int i = 0; i < bufferStepList.size(); i++)
			{
				bufferList.add(bufferStepList.get(i).getText());
			}
			recipe.setStepList(bufferList);
			application.gotoCategory(flag_source, recipe);
		}
	}

	/**
	 * go to the next step and pass the recipe and sourceFlag to the next step
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void submitStep(ActionEvent event) throws Exception
	{
		for (int i = 0; i < bufferStepList.size(); i++)
		{
			bufferList.add(bufferStepList.get(i).getText());
		}
		recipe.setStepList(bufferList);
		application.gotoSetIngredient(flag_source, recipe);
	}

	/*
	 * initial the interface of add step user can change the order of step
	 */
	public void addNewStep() throws Exception
	{
		Pane pane = new Pane();
		HBox hBox = new HBox();
		VBox vBox_item = new VBox();
		Pane orderPane = new Pane();
		Pane stepPane = new Pane();
		Pane deletePane = new Pane();
		Pane upPane = new Pane();
		Pane downPane = new Pane();

		Pane aknPane = new Pane();
		Label akn = new Label();
		akn.setFont(Font.font("Ebrima", FontPosture.ITALIC, 12));
		akn.setStyle("-fx-text-fill: #a42e2e");
		aknList.add(akn);
		aknPane.getChildren().add(akn);
		aknPane.setMinHeight(15);
		aknPane.setMinWidth(750);

		upPaneList.add(upPane);
		downPaneList.add(downPane);

		Button up = new Button("up");
		Button down = new Button("down");
		up.setFont(Font.font("Ebrima", 12));
		up.setMinHeight(20);
		up.setMinWidth(40);
		up.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5");
		down.setFont(Font.font("Ebrima", 12));
		down.setMinHeight(20);
		down.setMinWidth(40);
		down.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5");
		upPane.getChildren().add(up);
		downPane.getChildren().add(down);

		Label order = new Label(String.valueOf(bufferOrder.size() + 1));
		bufferOrder.add(order);

		TextField stepTextField = new TextField(Step);
		addTextLimiter(stepTextField, 255);
		bufferStepList.add(stepTextField);
		isNullPromptForString(stepTextField, akn);

		stepTextField.setMinWidth(550);

		if (bufferOrder.size() >= 10)
		{
			stepTextField.setMinWidth(539);
		}

		Button delete = new Button("delete");

		DropShadow shadow = new DropShadow();
		order.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
		order.setTextFill(Color.BROWN);
		delete.setFont(Font.font("Ebrima", 12));
		delete.setMinHeight(20);
		delete.setMinWidth(40);
		delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			delete.setEffect(shadow);
		});
		delete.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			delete.setEffect(null);
		});
		delete.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5");

		orderPane.getChildren().add(order);
		stepPane.getChildren().add(stepTextField);
		deletePane.getChildren().add(delete);

		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: FireBrick;");
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setMinHeight(40);
		hBox.setMinWidth(750);
		hBox.setSpacing(15);
		hBox.getChildren().addAll(orderPane, stepPane, deletePane, upPane, downPane);

		for (int i = 0; i < upPaneList.size(); i++)
		{
			upPaneList.get(i).setVisible(true);
			downPaneList.get(i).setVisible(true);
		}

		upPaneList.get(0).setVisible(false);
		downPaneList.get(upPaneList.size() - 1).setVisible(false);

		vBox_item.getChildren().addAll(hBox, aknPane);
		pane.getChildren().add(vBox_item);
		vBox.getChildren().add(pane);
		pane.setMinWidth(750);
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

				if (i >= 9)
				{
					bufferStepList.get(i).setMinWidth(539);
				}
				else
				{
					bufferStepList.get(i).setMinWidth(550);
				}

			}

			for (int i = 0; i < upPaneList.size(); i++)
			{
				upPaneList.get(i).setVisible(true);
				downPaneList.get(i).setVisible(true);
			}

			if (upPaneList.size() > 0)
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

	/*
	 * add Listener to the textfield and set restrictions
	 */
	public void isNullPromptForString(TextField tf, Label l)
	{
		tf.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (tf.getText() == null || tf.getText().length() == 0)
				{
					l.setText("            This line cannot be empty!");
				}
				else
				{
					l.setText("");
				}

			}

		});
	}

	/**
	 * by set the textfield the length limit to avoid the length error with
	 * database.
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
