package gui.controller;

import java.util.LinkedList;
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
	private TextField frontCameraFrom;
	@FXML
	private TextField frontCameraTo;
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
		Phone p = new Phone(phoneName.getText().trim(), oses.getSelectionModel().getSelectedItem() == null ? "" : oses.getSelectionModel().getSelectedItem().trim(), osVersion.getText().trim(), processor.getText().trim(), ramSizeFrom.getText().equals("") ? 0 : Integer.parseInt(ramSizeFrom.getText().trim()), ramSizeTo.getText().equals("") ? 0 : Integer.parseInt(ramSizeTo.getText().trim()),
				storageFrom.getText().equals("") ? 0 : Integer.parseInt(storageFrom.getText().trim()), storageTo.getText().equals("") ? 0 : Integer.parseInt(storageTo.getText().trim()), screenResolutions.getSelectionModel().getSelectedItem() == null ? "" : screenResolutions.getSelectionModel().getSelectedItem().trim(), 
				inchesFrom.getText().equals("") ? 0 : Integer.parseInt(inchesFrom.getText()), inchesTo.getText().equals("") ? 0 : Integer.parseInt(inchesTo.getText()), frontCameraFrom.getText().equals("") ? 0 : Integer.parseInt(frontCameraFrom.getText()), frontCameraTo.getText().equals("") ? 0 : Integer.parseInt(frontCameraTo.getText().trim()), 
				cameraFrom.getText().equals("") ? 0 : Integer.parseInt(cameraFrom.getText().trim()), cameraTo.getText().equals("") ? 0 : Integer.parseInt(cameraTo.getText().trim()), contractors.getSelectionModel().getSelectedItem() == null ? "" : contractors.getSelectionModel().getSelectedItem().trim(), 
				priceFrom.getText().equals("") ? 0 : Integer.parseInt(priceFrom.getText().trim()), priceTo.getText().equals("") ? 0 : Integer.parseInt(priceTo.getText().trim()));
		
		try {
			FilterManager fm = (FilterManager) Gui.get().context.lookup(Gui.FILTER_BEAN);
			List<Auction> l = fm.findAuctionsByPhone(p);
			
			List<PhoneCard> ph = new LinkedList<>();
			l.stream().forEach(i -> {
				 ph.add(new PhoneCard(i));
			});
			auctionHolder.getChildren().addAll(ph);//UX
		} catch (NamingException e) {}
	}
	
}