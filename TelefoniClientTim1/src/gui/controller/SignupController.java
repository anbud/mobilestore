package gui.controller;

import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import gui.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SignupController extends Controller {
	@FXML
	private DatePicker birthdate;
	@FXML
	private TextField fullname;
	@FXML
	private TextField address;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextArea about;
	@FXML
	private ImageView avatar;

	@FXML
	public void avatarAction(Event event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose avatar");
		fc.getExtensionFilters().add(new ExtensionFilter("Image Files",	"*.jpg", "*.jpeg", "*.bmp", "*.png", "*.gif"));
		File file = fc.showOpenDialog(getGui().getStage());
		if(file != null) {
			try {
				avatar.setImage(new Image(file.toURI().toString()));
				ImageInputStream stream = ImageIO.createImageInputStream(file);
				byte[] bytes = new byte[(int) stream.length()];
				stream.readFully(bytes);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void signupAction(Event event) {
		String fullnames = fullname.getText().trim();
		String usernames = username.getText().trim();
		String passwords = password.getText().trim();
		String emails = email.getText().trim();
		String abouts = about.getText().trim();
		String addresss = address.getText().trim();
		
		if(fullnames.isEmpty() ||
			usernames.isEmpty() ||
			passwords.isEmpty() ||
			emails.isEmpty()) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Full name, username, password and email can't be empty!");
			a.setHeaderText("Missing info");
			a.showAndWait();
			return;
		}
		
		if(passwords.length() < 3) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Password should be at least 3 characters!");
			a.setHeaderText("Password too short");
			a.showAndWait();
			password.setText("");
			return;
		}
		
		if(!emails.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("This is not a valid email address!");
			a.setHeaderText("Invalid email");
			a.showAndWait();
			email.setText("");
			return;
		}
	}

	@FXML
	public void backAction(Event event) {
		getGui().openLoginView();
	}

}