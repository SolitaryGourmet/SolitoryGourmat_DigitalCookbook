package userInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import businessLayer.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainInterfaceController implements Initializable
{
	private Main application;
	
	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	public void gotoAddRecipeFrom()
	{
		application.gotoAddRecipeForm("Add Recipe",new Recipe());
	}

	@FXML
	public void gotoSearchForm()
	{

		application.gotoSearchForm();
	}

	@FXML
	private Label label_title;
	@FXML
	private ImageView picture_homepage;
	@FXML
	private Button button_search;
	@FXML
	private Button button_add;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub

	}
}
