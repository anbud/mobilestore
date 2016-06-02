package gui.controller;

import exceptions.IncorrectPasswordException;
import exceptions.NotRegisteredException;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController extends Controller {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	@FXML
	private void loginAction(Event event) {
		String usernames = username.getText().trim();
		String passwords = password.getText().trim();
		
		if(usernames.isEmpty() || passwords.isEmpty())
			return;
		
		try {
			if( Gui.userManager.login(usernames, passwords) ) {
				
				// TODO open home view
				
			}
		}
		catch(IncorrectPasswordException e) {
			
		}
		catch(NotRegisteredException e) {
			
		}
	}
	
	@FXML
	private void signUpAction(Event event) {
		getGui().openSignupView();
	}
	
}