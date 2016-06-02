package gui.custom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;
import gui.Gui;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhoneCard extends Pane {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	@FXML
	private Text description;
	@FXML
	private Text os;
	@FXML
	private Text storage;
	@FXML
	private Text ram;
	@FXML
	private Text screen;
	@FXML
	private Text camera;
	@FXML
	private Text battery;
	@FXML
	private Text username;
	@FXML
	private ImageView avatar;
	@FXML
	private Text minimalBid;
	@FXML
	private TextField bid;
	
	private EventHandler<Event> increaseHandler;
	private EventHandler<Event> confirmHandler;
	
	public PhoneCard() throws Exception {
		FXMLLoader loader = new FXMLLoader(Gui.class.getResource("res/phone-card.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		loader.load();
	}
	
	
	@FXML
	private void increaseAction(Event event) {
		if(increaseHandler != null)
			increaseHandler.handle(event);
	}
	
	@FXML
	private void confirmAction(Event event) {
		if(confirmHandler != null)
			confirmHandler.handle(event);
	}
	
	public void setOnIncrease(EventHandler<Event> handler) {
		increaseHandler = handler;
	}
	
	public void setOnConfirm(EventHandler<Event> handler) {
		confirmHandler = handler;
	}


	public Image getImage() {
		return image.getImage();
	}


	public void setImage(Image image) {
		this.image.setImage(image);
	}


	public String getPhoneName() {
		return name.getText();
	}


	public void setPhoneName(String name) {
		this.name.setText(name);
	}


	public String getDescription() {
		return description.getText();
	}


	public void setDescription(String description) {
		this.description.setText(description);
	}


	public String getOs() {
		return os.getText();
	}


	public void setOs(String os) {
		this.os.setText(os);
	}


	public String getStorage() {
		return storage.getText();
	}


	public void setStorage(String storage) {
		this.storage.setText(storage);
	}


	public String getRam() {
		return ram.getText();
	}


	public void setRam(String ram) {
		this.ram.setText(ram);
	}


	public String getScreen() {
		return screen.getText();
	}


	public void setScreen(String screen) {
		this.screen.setText(screen);
	}


	public String getCamera() {
		return camera.getText();
	}


	public void setCamera(String camera) {
		this.camera.setText(camera);
	}


	public String getBattery() {
		return battery.getText();
	}


	public void setBattery(String battery) {
		this.battery.setText(battery);
	}


	public String getUsername() {
		return username.getText();
	}


	public void setUsername(String username) {
		this.username.setText(username);
	}


	public Image getAvatar() {
		return avatar.getImage();
	}


	public void setAvatar(Image avatar) {
		this.avatar.setImage(avatar);
	}


	public int getMinimalBid() {
		return Integer.parseInt(minimalBid.getText().replace("$ ", ""));
	}


	public void setMinimalBid(int minimalBid) {
		this.minimalBid.setText("$ "+minimalBid);
	}


	public int getCurrentBid() {
		return Integer.parseInt(bid.getText());
	}


	public void setCurrentBid(int bid) {
		this.bid.setText(""+bid);
	}
	
	
}
