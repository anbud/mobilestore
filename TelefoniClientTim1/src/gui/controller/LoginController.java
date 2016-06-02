package gui.controller;

import exceptions.IncorrectPasswordException;
import exceptions.NotRegisteredException;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginController extends Controller {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label error;

	@FXML
	private void loginAction(Event event) {
		error.setText("");
		
		String usernames = username.getText().trim();
		String passwords = password.getText().trim();
		
		if(usernames.isEmpty() || passwords.isEmpty())
			return;
		
		try {
			if( getGui().userManager.login(usernames, passwords) ) {
				
				getGui().openBoardView();
				
			}
		}
		catch(IncorrectPasswordException e) {
			error.setText("Incorrect password!");
		}
		catch(NotRegisteredException e) {
			error.setText("User not found!");
		}
	}
	
	@FXML
	private void signUpAction(Event event) {
		getGui().openSignupView();
	}
	
}