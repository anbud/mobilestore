package gui.custom;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import beans.post.PostManager;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Auction;
import model.Phone;

public class PhoneCard extends Pane {
	@FXML
	private ImageView image;
	@FXML
	private Label hoverMsg;
	@FXML
	private Text name;
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
	private Text username;
	@FXML
	private ImageView avatar;
	@FXML
	private Text initialPrice;
	@FXML
	private Text currentBid;
	@FXML
	private TextField bid;
	@FXML
	private AnchorPane bidHolder;
	@FXML
	private Button bidButton;
	@FXML
	private Button acceptBid;
	
	private Auction auction;
	
	public PhoneCard() throws RuntimeException {
		FXMLLoader loader = new FXMLLoader(Gui.class.getResource("res/phone-card.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public PhoneCard(Auction a) throws RuntimeException {
		this();
		this.setAuction(a);
	}
	
	@FXML
	public void openAuctionAction(Event event) {
		Gui.get().openAuctionDetailsView(auction);
	}
	
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
	public void acceptAction(Event event) {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get() == ButtonType.YES) {
				PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
				pm.postAuctionClosed(auction);
				Gui.get().openBoardView();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void initialize() {
		image.setOnMouseEntered(e -> {
			hoverMsg.setVisible(true);
		});
		
		hoverMsg.setOnMouseExited(e -> {
			hoverMsg.setVisible(false);
		});
	}

	public Image getImage() {
		return image.getImage();
	}
	public void setImage(Image image) {
		this.image.setImage(image);
	}
	
	public String getHoverMsg() {
		return hoverMsg.getText();
	}
	public void setHoverMsg(String hoverMsg) {
		this.hoverMsg.setText(hoverMsg);
	}

	public String getPhoneName() {
		return name.getText();
	}

	public void setPhoneName(String name) {
		this.name.setText(name);
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

	public int getCurrentBid() {
		return Integer.parseInt(currentBid.getText().replace("$ ", ""));
	}
	public void setCurrentBid(int currentBid) {
		this.currentBid.setText("$ " + currentBid);
	}
	
	public int getInitialPrice() {
		return Integer.parseInt(initialPrice.getText().replace("$ ", ""));
	}
	public void setInitialPrice(int initialPrice) {
		this.initialPrice.setText("$ " + initialPrice);
		//System.out.println(this.initialPrice);
	}

	public int getBid() {
		return Integer.parseInt(bid.getText());
	}
	
	public void setBid(int bid) {
		this.bid.setText(""+bid);
	}
	
	public void setBidEnabled(boolean enabled) {
		bidButton.setVisible(enabled);
		bidHolder.setVisible(enabled);
	}
	
	public void setAcceptEnabled(boolean enabled) {
		acceptBid.setVisible(enabled);
	}
	
	public void setAuction(Auction a) {
		auction = a;
		
		Phone p = a.getPhone();
		
		this.setPhoneName(p.getName());
		
		if(!p.getPictures().isEmpty())
			this.setImage(
				new Image(
					new ByteArrayInputStream(
						p.getPictures().iterator().next().getPicture()
					)
				)
			);
		
		this.setOs(p.getOs() + " " + p.getOsVersion());
		this.setStorage(p.getInternalStorage()+ " GB storage");
		this.setRam(p.getRamString() + " RAM");
		this.setScreen(String.format("%.1f\" | %s", p.getScreenSize(), p.getScreenRes()));
		
		String camera = "";
		
		if(p.getPrimaryCamera() > 0) {
			if(Math.abs(p.getPrimaryCamera() - (int) p.getPrimaryCamera()) == 0)
				camera += (int) p.getPrimaryCamera();
			else
				camera += String.format("%.1f", p.getPrimaryCamera());
			camera += "MP";
			
			if(p.getFrontCamera() > 0)
				camera += " | ";
		}
		
		if(p.getFrontCamera() > 0) {
			if(Math.abs(p.getFrontCamera() - (int) p.getFrontCamera()) == 0)
				camera += (int) p.getFrontCamera();
			else
				camera += String.format("%.1f", p.getFrontCamera());
			camera += "MP";
		}
		
		if(camera.length() == 0) {
			camera = "No";
		}
		
		this.setCamera(camera);
		
		this.setInitialPrice(a.getPhone().getPrice());
		this.setCurrentBid(a.getBid());
		this.setBid(a.getBid()+1);
		this.setUsername(a.getUser().getUsername());
		
		if(a.getUser().getAvatar() != null && a.getUser().getAvatar().length > 0)
			this.setAvatar(
				new Image(
					new ByteArrayInputStream(
						a.getUser().getAvatar()
					)
				)
			);
		
		boolean myauction = Gui.get().userManager.getUser().getUsername().equals(a.getUser().getUsername());
		boolean enabled = myauction ? false : !a.getClosed();

		this.setBidEnabled(enabled);
		this.setAcceptEnabled(myauction && !a.getClosed() && this.getInitialPrice() < this.getCurrentBid());
	}
	
	public Auction getAuction() {
		return auction;
	}
	
}