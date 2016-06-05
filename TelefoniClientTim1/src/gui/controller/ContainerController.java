package gui.controller;

import java.io.ByteArrayInputStream;

import javax.naming.NamingException;

import beans.user.UserManager;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ContainerController extends Controller {

	@FXML
	private ImageView userAvatar;

	@FXML
	private void initialize() {
		byte[] array = Gui.get().userManager.getUser().getAvatar();
		if ((array != null) && (array.length > 0)) {
			Image avatar = new Image(new ByteArrayInputStream(array));
			userAvatar.setImage(avatar);
		}
	}

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
		} catch (NamingException e) {
		}
	}
}