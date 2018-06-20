package userInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import businessLayer.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class SearchResultFromController implements Initializable
{
	private Main application;
	private int requestNumber = 0;

	@FXML
	private VBox vbox;

	public void setApp(Main application)
	{
		this.application = application;
	}

	public void setSearchResultView(ArrayList<Recipe> recipeList) throws Exception
	{
		vbox.setPadding(new Insets(5, 5, 5, 5));
		for (int i = 0; i < recipeList.size(); i++)
		{
			Pane pane = new Pane();
			HBox hbox = new HBox();
			Pane imagePane = new Pane();
			Pane labelPane = new Pane();
			Pane buttonPane = new Pane();
			Button display = new Button("Display recipes");
			
			FileInputStream inputstream = new FileInputStream("E:\\College\\Courseware\\software engineering II\\Digital cook book - Solitary Gourmet\\QQͼƬ20180613104404.jpg");
			Image image = new Image(inputstream); 
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(80);
			imageView.setFitWidth(100);
			imageView.setPreserveRatio(true);
			imagePane.getChildren().add(imageView);	
					
					
			Label recipeNameLabel = new Label(recipeList.get(i).getRecipeName());
			DropShadow shadow = new DropShadow();

			recipeNameLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 22));
			recipeNameLabel.setTextFill(Color.BROWN);
			
			display.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
			display.setMinHeight(40);
			display.setMinWidth(80);
			display.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {display.setEffect(shadow);});
			display.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {display.setEffect(null);});  
			display.setStyle("-fx-background-color: LightGray; -fx-text-fill: brown;");
			labelPane.getChildren().add(recipeNameLabel);
			buttonPane.getChildren().add(display);
			hbox.setStyle("-fx-padding: 10;" + 
                    "-fx-border-style: solid inside;" + 
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" + 
                    "-fx-border-radius: 5;" + 
                    "-fx-border-color: brown;");
			hbox.setPadding(new Insets(10,10,10,10));
			hbox.setMinHeight(80);
			hbox.setMinWidth(785);
			hbox.setSpacing(120);
			hbox.getChildren().addAll(imagePane, labelPane, buttonPane);
			pane.getChildren().add(hbox);
			vbox.getChildren().add(pane);

			pane.setMinWidth(785);
			pane.setStyle("-fx-background-color: BEIGE;"); 
			
			Recipe r = recipeList.get(i);
			display.setOnAction(ex -> {
				application.goToDisplayForm(r);
			});
		}
	}

	public void RequestFromSearch()
	{
		requestNumber = 1;// to specify where the request come from and then go back
	}

	@FXML
	public void BacktoFormer()
	{
//		if (requestNumber == 1)
//		{
//			application.gotoCategory();
//		}
		application.gotoSearchForm();
		
	}

	public void Init()
	{

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// TODO Auto-generated method stub
		this.Init();
	}

}
