package solitoryGourmet_DigitalCookbook;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchFormController implements Initializable
{
	private Main application;

	@FXML
	private TextField textField;

	@FXML
	private Button search;

	@FXML
	private Button SearchbyCategory;

	public void setApp(Main application)
	{
		this.application = application;
	}

	@FXML
	public void gotoHome()
	{
		application.gotoMainInterface();
	}

	@FXML
    void searchByCategory(ActionEvent event)
	{
		application.gotoCategory();
	}

	@FXML
	void searchByName(ActionEvent event)
	{
		DataBaseControl.getConnection();
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList = SearchControl.searchName(textField.getText());
		for (int i = 0; i < recipeList.size(); i++)
		{
			System.out.println(recipeList.get(i).getRecipeName());
		}
		application.gotoSearchResult(recipeList);

		DataBaseControl.closeConnection();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}

}
