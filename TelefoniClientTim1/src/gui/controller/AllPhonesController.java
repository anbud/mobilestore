package gui.controller;

import java.util.List;

import javax.naming.NamingException;

import beans.filter.FilterManager;
import gui.Controller;
import gui.Gui;
import gui.custom.PhoneCard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.Auction;
import model.Phone;

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
	@FXML
	private FlowPane auctionHolder;
	
	private final String[] osesarr = {"Android", "iOS", "Windows"};
	private final String[] contactorsarr = {"MTS", "Telenor", "VIP", "sim free", "other"};
	private final String[] screenresarr = {"240x320", "240x400", "320x480", "480x800", "540x960", "640x960", "720x1280", "1080x1920", "1440x2560", "2160x3840"};
	
	@FXML
	private void initialize() {
		oses.getItems().addAll(osesarr);
		contractors.getItems().addAll(contactorsarr);
		screenResolutions.getItems().addAll(screenresarr);
	}

	@FXML
	private void filterPhonesAction(Event event) {
		Phone p = new Phone(phoneName.getText(), oses.getValue() == null ? "" : oses.getValue(), osVersion.getText(), processor.getText(), ramSizeFrom.getText().equals("") ? 0 : Integer.parseInt(ramSizeFrom.getText()), ramSizeTo.getText().equals("") ? 0 : Integer.parseInt(ramSizeTo.getText()),
				storageFrom.getText().equals("") ? 0 : Integer.parseInt(storageFrom.getText()), storageTo.getText().equals("") ? 0 : Integer.parseInt(storageTo.getText()), screenResolutions.getValue() == null ? "" : screenResolutions.getValue(), 
				inchesFrom.getText().equals("") ? 0 : Integer.parseInt(inchesFrom.getText()), inchesTo.getText().equals("") ? 0 : Integer.parseInt(inchesTo.getText()), cameraFrom.getText().equals("") ? 0 : Integer.parseInt(cameraFrom.getText()), cameraTo.getText().equals("") ? 0 : Integer.parseInt(cameraTo.getText()), 
				cameraFrom.getText().equals("") ? 0 : Integer.parseInt(cameraFrom.getText()), cameraTo.getText().equals("") ? 0 : Integer.parseInt(cameraTo.getText()), contractors.getValue() == null ? "" : contractors.getValue(), 
				priceFrom.getText().equals("") ? 0 : Integer.parseInt(priceFrom.getText()), priceTo.getText().equals("") ? 0 : Integer.parseInt(priceTo.getText()));
		
		try {
			FilterManager fm = (FilterManager) Gui.get().context.lookup(Gui.FILTER_BEAN);
			List<Auction> l = fm.findAuctionsByPhone(p);
			
			l.stream().forEach(i -> {
				//do something
			});
			//auctionHolder.getChildren().addAll(new PhoneCard(l), new PhoneCard());
		} catch (NamingException e) {}
	}
	
}