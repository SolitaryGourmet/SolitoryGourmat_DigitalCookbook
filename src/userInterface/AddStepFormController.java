package userInterface;

import java.util.ArrayList;

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
	private Main application;
	public ArrayList<String> bufferList;

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	private VBox vBox;

	public void setStep() throws Exception
	{

		vBox.setPadding(new Insets(5, 5, 5, 5));

		for (int i = 0; i < bufferList.size(); i++)
		{

			Pane pane = new Pane();
			HBox hBox = new HBox();
			Pane orderPane = new Pane();
			Pane IngredientPane = new Pane();
			Pane deletePane = new Pane();

			Pane upPane = new Pane();
			Pane downPane = new Pane();
			Button up = new Button("up");
			Button down = new Button("down");
			up.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
			up.setMinHeight(40);
			up.setMinWidth(40);
			down.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
			down.setMinHeight(40);
			down.setMinWidth(40);
			upPane.getChildren().add(up);
			downPane.getChildren().add(down);// 这些是添加上下按钮的

			Label order = new Label(String.valueOf(i));
			TextField ingredient = new TextField();
			ingredient.setText(String.valueOf(bufferList.get(i)));
			Button delete = new Button("delete");

			DropShadow shadow = new DropShadow();
			order.setFont(Font.font("verdana", FontPosture.REGULAR, 22));
			order.setTextFill(Color.BROWN);
			delete.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
			delete.setMinHeight(40);
			delete.setMinWidth(40);
			delete.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				delete.setEffect(shadow);
			});
			delete.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				delete.setEffect(null);
			});
			delete.setStyle("-fx-background-color: LightGray; -fx-text-fill: brown;");

			orderPane.getChildren().add(order);
			IngredientPane.getChildren().add(ingredient);
			deletePane.getChildren().add(delete);

			hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: brown;");
			hBox.setPadding(new Insets(10, 10, 10, 10));
			hBox.setMinHeight(80);
			hBox.setMinWidth(785);
			hBox.setSpacing(10);
			hBox.getChildren().addAll(orderPane, IngredientPane, deletePane, upPane, downPane);

			pane.getChildren().add(hBox);
			vBox.getChildren().add(pane);
			pane.setMinWidth(785);
			pane.setStyle("-fx-background-color: BEIGE;");
			int index = i;

			delete.setOnAction(ex -> {
				bufferList.remove(index);
				try
				{
					application.gotoEditStep(bufferList);
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 删除动作 删掉list的项并重新读取页面

			});
			up.setOnAction(ex1 -> {
				if (index == 0)
				{

				}
				else
				{
					bufferList.add(index - 1, bufferList.get(index));
					bufferList.remove(index + 1);
					try
					{
						application.gotoEditStep(bufferList);
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			down.setOnAction(ex2 -> {
				if (index == bufferList.size() - 1)
				{

				}
				else
				{
					bufferList.add(index + 2, bufferList.get(index));
					bufferList.remove(index);
					try
					{
						application.gotoEditStep(bufferList);
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});// 上下实现方法同上

		}

	}
}
