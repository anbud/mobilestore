package gui.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.naming.NamingException;

import beans.post.PostManager;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import model.Phone;
import model.PhonePicture;

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
	private final String[] osarr = {"Android", "iOS", "Windows"};
	private final String[] ramSizearr = {"128 MB", "256 MB", "512 MB", "768 MB", "1 GB", "1.5 GB", "2 GB", "3 GB", "4 GB", "6 GB", "8 GB"};
	private final String[] primaryCameraarr = {"", "0.3 MP", "1 MP", "1.2 MP", "2 MP", "5 MP", "6 MP", "8 MP", "12 MP", "13 MP", "14 MP", "16 MP", "23 MP"};
	private final String[] frontCameraarr = {"", "0.3 MP", "1 MP", "1.2 MP", "2 MP", "5 MP", "6 MP", "8 MP", "13 MP"};
	private final String[] internalStoragearr = {"4 GB", "8 GB", "16 GB", "32 GB", "64 GB", "128 GB"};
	private final String[] contractorarr = {"MTS", "Telenor", "VIP", "sim free", "other"};
	private final String[] screenResarr = {"240x320", "240x400", "320x480", "480x800", "540x960", "640x960", "720x1280", "1080x1920", "1440x2560", "2160x3840"};
	

	@FXML
	private void initialize() {
		os.getItems().addAll(osarr);
		ramSize.getItems().addAll(ramSizearr);
		primaryCamera.getItems().addAll(primaryCameraarr);
		frontCamera.getItems().addAll(frontCameraarr);
		internalStorage.getItems().addAll(internalStoragearr);
		contractor.getItems().addAll(contractorarr);
		screenResolution.getItems().addAll(screenResarr);
	}
	
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
				((FlowPane) picsHolder.getChildren().get(images.size())).getChildren().add(image);
				images.add(file);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void publishPhoneAction(Event event) {
		try {
			String name = phoneName.getText().trim();
			String osname = os.getSelectionModel().getSelectedItem();
			String osversion = osVersion.getText().trim();
			String processorname = processor.getText().trim();
			String ramsizestring = ramSize.getSelectionModel().getSelectedItem();
			
			int rammul = 1;
			if(ramsizestring.endsWith("GB"))
				rammul = 1024;
			
			int ramsize = (int) (rammul * Double.parseDouble(ramsizestring.substring(0, ramsizestring.indexOf(" "))));
			
			String primarycamerastring = primaryCamera.getSelectionModel().getSelectedItem();
			double primarycamera = 
					(primaryCamera.getValue() == null || primarycamerastring.equals("")) ? 0 :
					Double.parseDouble(primarycamerastring.substring(0, primarycamerastring.indexOf(" ")));
			String frontcamerastring = frontCamera.getSelectionModel().getSelectedItem();
			double frontcamera = 
					(frontCamera.getValue() == null || frontcamerastring.equals("")) ? 0 :
					Double.parseDouble(frontcamerastring.substring(0, frontcamerastring.indexOf(" ")));
			String internalstoragestring = internalStorage.getSelectionModel().getSelectedItem();
			int internalstorage = 
					Integer.parseInt(internalstoragestring.substring(0, internalstoragestring.indexOf(" ")));
			String screenres = screenResolution.getSelectionModel().getSelectedItem();
			String screensizestring = screenSize.getText().trim();
			double screensize = 
					Double.parseDouble(screensizestring);
			boolean hasWifi = wifi.isSelected();
			boolean hasBluetooth = bluetooth.isSelected();
			boolean isDualSim = dualSim.isSelected();
			String contractorname = contractor.getSelectionModel().getSelectedItem();
			int howmuch = Integer.parseInt(price.getText().trim());
			String desc = description.getText().trim();
	
			Phone phone = new Phone(name, osname, osversion, processorname, ramsize, primarycamera, frontcamera, 
					internalstorage, screenres, screensize, hasWifi, hasBluetooth, isDualSim, contractorname,
					howmuch, desc);

			images.forEach(i -> {
				try {
					ImageInputStream stream = ImageIO.createImageInputStream(i);
					byte[] bytes = new byte[(int) stream.length()];
					stream.readFully(bytes);
					
					PhonePicture pp = new PhonePicture();
					pp.setPicture(bytes);
					pp.setPhone(phone);
					phone.addPictures(pp);
				} catch (Exception e) { }
			});
			
			PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
			if(pm.postAuction(Gui.get().userManager.getUser(), phone, howmuch)) {
				Gui.get().openBoardView();
			}
		} catch (NullPointerException e) {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Missed info");
			a.setContentText("You haven't filled all the informations");
			a.showAndWait();
		} catch (NumberFormatException e) {
			Alert a = new Alert(AlertType.WARNING);
			a.setHeaderText("Wrong input");
			a.setContentText("It's not a valid number!");
			a.showAndWait();
		} catch (NamingException e) { }
	}

}