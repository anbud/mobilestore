package gui.controller;

import gui.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AllPhonesController extends Controller {
	@FXML
	private TextField phoneName;
	@FXML
	private ChoiceBox<String> oses;
	@FXML
	private TextField osVersion;
	@FXML
	private TextField processor;
	@FXML
	private TextField ramSizeFrom;
	@FXML
	private TextField ramSizeTo;
	@FXML
	private TextField storageFrom;
	@FXML
	private TextField storageTo;
	@FXML
	private ChoiceBox<String> screenResolutions;
	@FXML
	private TextField inchesFrom;
	@FXML
	private TextField inchesTo;
	@FXML
	private TextField cameraFrom;
	@FXML
	private TextField cameraTo;
	@FXML
	private ChoiceBox<String> contractors;
	@FXML
	private TextField priceFrom;
	@FXML
	private TextField priceTo;
	@FXML
	private Button refreshResults;
	
	//private String[] osesarr = {"Android", "iOS", "Windows"};
	private String[] contactorsarr = {"mts", "telenor", "VIP", "sim free", "other"};
	private String[] screenresarr = {"480x800", "640x960", "720x1080", "1080x1920", "1440x2560 (QHD)", "2160x3840 (4K)"};
	
	@FXML
	private void initialize() {
		//oses.getItems().addAll(osesarr);
		//contractors.getItems().addAll(contactorsarr);
		//screenResolutions.getItems().addAll(screenresarr);
	}

}