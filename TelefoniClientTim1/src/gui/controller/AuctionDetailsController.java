package gui.controller;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import javax.naming.NamingException;

import beans.filter.FilterManager;
import beans.post.PostManager;
import gui.Controller;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Auction;
import model.Phone;
import model.User;

public class AuctionDetailsController extends Controller {
	@FXML
	private Label phoneName;
	@FXML
	private ImageView phonePhoto;
	@FXML
	private ImageView ownerAvatar;
	@FXML
	private Label ownerUsername;
	@FXML
	private Label ownerName;
	@FXML
	private Label ownerEmail;
	@FXML
	private Label initPrice;
	@FXML
	private Label currentBid;
	@FXML
	private TextField bid;
	@FXML
	private Button raiseBid;
	@FXML
	private Button confirmBid;
	@FXML
	private Button acceptBid;
	@FXML
	private TextField osVersion;
	@FXML
	private TextField processorName;
	@FXML
	private TextField ramSize;
	@FXML
	private TextField internalStorage;
	@FXML
	private TextField primaryCamera;
	@FXML
	private TextField frontCamera;
	@FXML
	private TextField screenResolution;
	@FXML
	private TextField screenSize;
	@FXML
	private TextField contractor;
	@FXML
	private TextField wifi;
	@FXML
	private TextField dualsim;
	@FXML
	private TextField bluetooth;
	@FXML
	private Label description;
	@FXML
	private AnchorPane comment;
	@FXML
	private TextArea commentInput;
	@FXML
	private AnchorPane bidHolder;
	@FXML
	private FlowPane imageHolder;
	
	private Auction auction;

	@FXML
	private void increaseAction(Event event) {
		this.setBid(this.getBid()+1);
	}
	
	@FXML
	private void confirmAction(Event event) {
		if(this.getBid() <= this.getCurrentBid()) {
			Alert alert = new Alert(AlertType.ERROR, "Your bid must be higher than the current bid!");
			alert.showAndWait();
			return;
		}
		
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.YES) {
				PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
				if(pm.postBid(Gui.get().userManager.getUser(), auction, this.getBid())) {
					this.setCurrentBid(this.getBid());
					this.setBid(this.getBid()+1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void acceptAction(Event event) {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.YES) {
				PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
				pm.postAuctionClosed(auction);
				Gui.get().openAuctionDetailsView(auction);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void commentAction(Event event) {
		
	}
	
	public void setAuction(Auction a) {
		auction = a;
		
		Phone p = a.getPhone();
		User u = a.getUser();
		
		this.phoneName.setText(p.getName());
		
		if(p.getPictures() != null && p.getPictures().size() > 0)
			this.phonePhoto.setImage(
				new Image(
					new ByteArrayInputStream(p.getPictures().iterator().next().getPicture())
				)
			);
		
		this.initPrice.setText("$ "+p.getPrice());
		this.currentBid.setText("$ "+a.getBid());
		this.bid.setText(""+(a.getBid()+1));
		
		this.ownerUsername.setText(u.getUsername());
		this.ownerName.setText(u.getName());
		this.ownerEmail.setText(u.getEMail());
		
		if(u.getAvatar() != null && u.getAvatar().length > 0)
			this.ownerAvatar.setImage(
				new Image(
					new ByteArrayInputStream(u.getAvatar())
				)
			);
		
		this.osVersion.setText(p.getOs()+" "+p.getOsVersion());
		this.processorName.setText(p.getProcessor());
		this.ramSize.setText(p.getRamString());
		this.internalStorage.setText(p.getInternalStorage()+" GB");
		
		if(p.getPrimaryCamera() > 0) {
			String camera = "";
			if(Math.abs(p.getPrimaryCamera() - (int) p.getPrimaryCamera()) == 0)
				camera += (int) p.getPrimaryCamera();
			else
				camera += String.format("%.1f", p.getPrimaryCamera());
			
			this.primaryCamera.setText(camera + " MP");
		}
		else {
			this.primaryCamera.setText("No");
		}
		
		if(p.getFrontCamera() > 0) {
			String camera = "";
			if(Math.abs(p.getFrontCamera() - (int) p.getFrontCamera()) == 0)
				camera += (int) p.getFrontCamera();
			else
				camera += String.format("%.1f", p.getFrontCamera());
			
			this.frontCamera.setText(camera + " MP");
		}
		else {
			this.frontCamera.setText("No");
		}
		
		this.screenResolution.setText(p.getScreenRes());
		this.screenSize.setText(String.format("%.1f\"", p.getScreenSize()));
		this.contractor.setText(p.getContractor());
		this.wifi.setText(p.getWiFi() ? "Yes" : "No");
		this.dualsim.setText(p.getDualSim() ? "Yes" : "No");
		this.bluetooth.setText(p.getBluetooth() ? "Yes" : "No");
		
		this.description.setText(p.getDescription());
		
		
		boolean myauction = Gui.get().userManager.getUser().getUsername().equals(u.getUsername());
		boolean enabled = myauction ? false : !a.getClosed();

		this.setBidEnabled(enabled);
		this.setAcceptEnabled(myauction && !a.getClosed() && this.getInitialPrice() < this.getCurrentBid());
		
		
		// pictures
			
		imageHolder.getChildren().clear();
			
		p.getPictures().forEach((pic) -> {
			Image img = new Image(
				new ByteArrayInputStream(
					pic.getPicture()
				)
			);
			ImageView imgView = new ImageView(img);
			imgView.setFitHeight(320);
			imgView.setFitWidth(500);
			imageHolder.getChildren().add(imgView);
		});
		
		
		// comments
		
		try {
			FilterManager fm = (FilterManager) Gui.get().context.lookup(Gui.FILTER_BEAN);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void setBidEnabled(boolean enabled) {
		confirmBid.setVisible(enabled);
		bidHolder.setVisible(enabled);
	}
	
	private void setAcceptEnabled(boolean enabled) {
		acceptBid.setVisible(enabled);
	}
	
	private int getCurrentBid() {
		return Integer.parseInt(currentBid.getText().replace("$ ", ""));
	}
	
	private void setCurrentBid(int currentBid) {
		this.currentBid.setText("$ " + currentBid);
	}
	
	private int getInitialPrice() {
		return Integer.parseInt(initPrice.getText().replace("$ ", ""));
	}
	
	private int getBid() {
		return Integer.parseInt(bid.getText());
	}
	
	private void setBid(int bid) {
		this.bid.setText(""+bid);
	}
}
