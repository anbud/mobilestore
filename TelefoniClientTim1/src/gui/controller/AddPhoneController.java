package gui.controller;

import gui.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
	private ChoiceBox<String> externalStorage;
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

	@FXML
	public void addPhoneImagesAction(Event event) {

	}

	@FXML
	public void publishPhoneAction(Event event) {

	}

}