package userInterface;

/**
 * this controller will show the search interface
 */

import java.util.ArrayList;
import businessLayer.Recipe;
import databaseLayer.DataBaseControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchFormController
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
		addTextLimiter(textField, 30);// intial the textfield's length limitation
	}

	@FXML
	public void gotoHome()
	{
		application.gotoMainInterface();
	}

	/**
	 * goto search by category interface
	 * 
	 * @param event
	 */
	@FXML
	void searchByCategory(ActionEvent event)
	{
		// null means this is not in a add or edit process
		application.gotoCategory("Search Recipe", null);
	}

	/**
	 * get result of search by name send the result list and go to the result
	 * interface
	 * 
	 */
	@FXML
	void searchByName(ActionEvent event)
	{
		DataBaseControl.getConnection();
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

		recipeList = DataBaseControl.searchByName(textField.getText());
		for (int i = 0; i < recipeList.size(); i++)
		{
			System.out.println(recipeList.get(i).getRecipeName());
		}
		application.gotoSearchResult("Search Name", recipeList, null);

		DataBaseControl.closeConnection();
	}

	/**
	 * by set the textfield the length limit to avoid the length error with
	 * database. int(11)in database means maximum is 2147483647
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
