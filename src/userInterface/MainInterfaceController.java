package userInterface;

/**
 * this controller will show the main interface of the software
 * @author CUI_XIAO
 */

import businessLayer.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MainInterfaceController
{
	private Main application;
	
	@FXML
	private Label label_title;
	@FXML
	private Pane picture_homepage;
	@FXML
	private Button button_search;
	@FXML
	private Button button_add;
	
	public void setApp(Main application)
	{
		this.application = application;
	}

	/**
	 * go to Add recipe interface
	 */
	@FXML
	public void gotoAddRecipeFrom()
	{
		application.gotoAddRecipeForm("Add Recipe",new Recipe());
	}

	/**
	 * go to search interface
	 */
	@FXML
	public void gotoSearchForm()
	{
		application.gotoSearchForm();
	}
}
