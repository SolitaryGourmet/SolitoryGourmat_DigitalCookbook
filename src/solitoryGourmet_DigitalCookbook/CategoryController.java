package solitoryGourmet_DigitalCookbook;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class CategoryController implements Initializable
{
	private Main application;

	public void setApp(Main application)
	{
		this.application = application;
	}

	
	    @FXML
	    private VBox vbox1;
	    
	    @FXML
	    private Label label_taste;

	    @FXML
	    private CheckBox Sweet;

	    @FXML
	    private CheckBox Sour;

	    @FXML
	    private CheckBox Spicy;

	    @FXML
	    private CheckBox Salty;

	    @FXML
	    private CheckBox Light;

	    @FXML
	    private Label label_city;

	    @FXML
	    private RadioButton Sichuan;

	    @FXML
	    private RadioButton Hunan;

	    @FXML
	    private RadioButton Shanghai;

	    @FXML
	    private RadioButton Beijing;

	    @FXML
	    private RadioButton Guangdong;

	    @FXML
	    private RadioButton Other;

	    @FXML
	    private Label label_mealtime;

	    @FXML
	    private CheckBox Breakfast;

	    @FXML
	    private CheckBox Lunch;

	    @FXML
	    private CheckBox Supper;
	    
	    @FXML
	    private Label label_meet;

	    @FXML
	    private CheckBox Pork;

	    @FXML
	    private CheckBox Chicken;

	    @FXML
	    private CheckBox Lamb;

	    @FXML
	    private RadioButton Vegetarian;

	    @FXML
	    private CheckBox Beef;

	    @FXML
	    private CheckBox Fish;

	    @FXML
	    private Button Search;

	    @FXML
	    void Serach(MouseEvent event) {

	    }

	    @FXML
	    void SerachtoResult(ActionEvent event) {

	    }

	    @FXML
	    void backtoSearchForm(ActionEvent event) {

	    }

	    @FXML
	    void searchByCategory(ActionEvent event) {

	    }

	    @FXML
	    void setCity(ActionEvent event) {

	    }

	    @FXML
	    void setFlavor(ActionEvent event) {

	    }

	    @FXML
	    void setMealTime(ActionEvent event) {

	    }

	    @FXML
	    void setMeat(ActionEvent event) {

	    }

	    @FXML
	    void setVegetarian(ActionEvent event) {

	    }


	public void Init()
	{
		
	}

	
/*
	public void Submit()
	{

		StepList.add(StepField.getText());

		System.out.println(StepList);

		StepField.clear();
	}
*/
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		this.Init();
	}
}
