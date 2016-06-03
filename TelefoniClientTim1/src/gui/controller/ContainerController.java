package gui.controller;

import javax.naming.NamingException;

import beans.user.UserManager;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;

public class ContainerController extends Controller {
	
	@FXML
	public void mainBoardAction(Event event) {
		getGui().openBoardView();
	}
	
	@FXML
	public void allPhonesAction(Event event) {
		getGui().openAllPhonesView();
	}
	
	@FXML
	public void addPhoneAction(Event event) {
		getGui().openAddPhoneView();
	}
	
	@FXML
	public void logoutAction(Event event) {
		getGui().userManager.logout();
		getGui().openLoginView();
		
		try {
			getGui().userManager = (UserManager) getGui().context.lookup(Gui.USER_BEAN);
		} catch (NamingException e) { }
	}

}