package gui.controller;

import gui.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	@FXML
	private void loginAction(Event event) {
		if(username.getText().trim().isEmpty() || password.getText().trim().isEmpty())
			return;
	}
	
	@FXML
	private void signUpAction(Event event) {
		getGui().openSignupView();
	}
	
}