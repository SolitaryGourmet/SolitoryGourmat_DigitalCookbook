package userInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteRecipeConfirmController
{
	@FXML
	private Button delete_Button;
	@FXML
	private Button cancel_Button;
	
	public Button getDelete_Button()
	{
		return delete_Button;
	}

	public void setDelete_Button(Button delete_Button)
	{
		this.delete_Button = delete_Button;
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
