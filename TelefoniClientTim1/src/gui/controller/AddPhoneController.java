package gui.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import gui.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddPhoneController extends Controller {
	@FXML
	private TextField phoneName;
	@FXML
	private ChoiceBox<String> os;
	@FXML
	private TextField osVersion;
	@FXML
	private TextField processor;
	@FXML
	private ChoiceBox<String> ramSize;
	@FXML
	private ChoiceBox<String> primaryCamera;
	@FXML
	private ChoiceBox<String> frontCamera;
	@FXML
	private ChoiceBox<String> internalStorage;
	@FXML
	private ChoiceBox<String> screenResolution;
	@FXML
	private TextField screenSize;
	@FXML
	private ChoiceBox<String> contractor;
	@FXML
	private CheckBox wifi;
	@FXML
	private CheckBox bluetooth;
	@FXML
	private CheckBox dualSim;
	@FXML
	private TextField price;
	@FXML
	private TextArea description;
	@FXML
	private GridPane picsHolder;
	
	private List<File> images = new ArrayList<File>(3);

	@FXML
	public void addPhoneImagesAction(Event event) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose avatar");
		fc.getExtensionFilters().add(new ExtensionFilter("Image Files",	"*.jpg", "*.jpeg", "*.bmp", "*.png", "*.gif"));
		File file = fc.showOpenDialog(getGui().getStage());
		if(file != null) {
			try {
				ImageView image = new ImageView(new Image(file.toURI().toString()));
				image.setFitHeight(120);
				image.setFitWidth(120);
				( (FlowPane) picsHolder.getChildren().get(images.size()) ).getChildren().add(image);
				images.add(file);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void publishPhoneAction(Event event) {

	}

}