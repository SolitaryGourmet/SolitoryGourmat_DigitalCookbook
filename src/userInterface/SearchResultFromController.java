package userInterface;

import java.io.FileInputStream;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class SearchResultFromController implements Initializable
{
	private Main application;
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private String flag_source = new String();
	private Recipe recipeForBackToCategory = new Recipe();

	public Recipe getRecipeForBackToCategory()
	{
		return recipeForBackToCategory;
	}

	public void setRecipeForBackToCategory(Recipe recipeForBackToCategory)
	{
		this.recipeForBackToCategory = recipeForBackToCategory;
	}

	public String getFlag_source()
	{
		return flag_source;
	}

	public void setFlag_source(String flag_source)
	{
		this.flag_source = flag_source;
	}

	@FXML
	private VBox vbox;

	public ArrayList<Recipe> getRecipeList()
	{
		return recipeList;
	}

	public void setRecipeList(ArrayList<Recipe> recipeList)
	{
		this.recipeList = recipeList;
	}

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
			GridPane gridPane = new GridPane();
			Pane imagePane = new Pane();
			Pane labelPane = new Pane();
			Pane buttonPane = new Pane();
			Button display = new Button("display");

			if (recipeList.get(i).getPhotoRoute() != null)
			{
				FileInputStream inputstream = new FileInputStream(recipeList.get(i).getPhotoRoute());
				Image image = new Image(inputstream);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(80);
				imageView.setFitWidth(100);
				imageView.setPreserveRatio(true);
				imagePane.getChildren().add(imageView);
			}

			Label recipeNameLabel = new Label(recipeList.get(i).getRecipeName());
			DropShadow shadow = new DropShadow();

			recipeNameLabel.setFont(Font.font("Ebrima", FontPosture.REGULAR, 22));
			recipeNameLabel.setTextFill(Color.BROWN);

			display.setFont(Font.font("Ebrima", FontPosture.REGULAR, 18));
			display.setMinHeight(40);
			display.setMinWidth(80);
			display.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				display.setEffect(shadow);
			});
			display.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				display.setEffect(null);
			});
			display.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14pt; -fx-padding: 5");
			labelPane.getChildren().add(recipeNameLabel);
			buttonPane.getChildren().add(display);
			hbox.setPadding(new Insets(10, 10, 10, 10));
			hbox.setMinHeight(60);
			hbox.setMinWidth(600);
			hbox.setSpacing(120);
			hbox.getChildren().addAll(imagePane, labelPane);
			gridPane.setMinHeight(80);
			gridPane.setMinWidth(785);
			gridPane.setHgap(50);
			gridPane.setVgap(50);
			gridPane.add(hbox, 0, 0);
			gridPane.add(buttonPane, 1, 0);
			gridPane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: FireBrick;");
			pane.getChildren().add(gridPane);
			vbox.getChildren().add(pane);

			pane.setMinWidth(785);
			pane.setStyle("-fx-background-color: BEIGE;");

			Recipe r = recipeList.get(i);
			display.setOnAction(ex -> {
				application.goToDisplayForm(r, recipeList, flag_source, recipeForBackToCategory);
			});
		}
	}

	@FXML
	public void BacktoFormer()
	{
		if (flag_source.equals("Search Category"))
		{
			application.gotoCategory("Search Recipe", recipeForBackToCategory);
		}
		else if (flag_source.equals("Search Name"))
		{
			application.gotoSearchForm();
		}
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
