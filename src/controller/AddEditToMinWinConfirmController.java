package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AddEditToMinWinConfirmController
{
	@FXML
	private Button goToHome_Button;

	@FXML
	private Button cancel_Button;

	public Button getGoToHome_Button()
	{
		return goToHome_Button;
	}

	public void setGoToHome_Button(Button goToHome_Button)
	{
		this.goToHome_Button = goToHome_Button;
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
