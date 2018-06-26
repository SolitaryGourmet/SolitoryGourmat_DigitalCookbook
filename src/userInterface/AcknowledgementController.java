package userInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AcknowledgementController
{

	@FXML
    private Button viewRecipe_Button;
	@FXML
    private Button cancel_Button;
	
    public Button getViewRecipe_Button()
	{
		return viewRecipe_Button;
	}

	public void setViewRecipe_Button(Button viewRecipe_Button)
	{
		this.viewRecipe_Button = viewRecipe_Button;
	}

	public Button getCancel_Button()
	{
		return cancel_Button;
	}

	public void setCancel_Button(Button cancel_Button)
	{
		this.cancel_Button = cancel_Button;
	}

	
}
