package gui.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import beans.post.PostManager;
import gui.Controller;
import gui.Gui;
import gui.custom.PhoneCard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import model.Auction;
import model.Phone;

public class BoardController extends Controller {

	@FXML
	private ListView<PhoneCard> myActiveList;
	@FXML
	private ListView<PhoneCard> myClosedList;
	@FXML
	private ListView<PhoneCard> participatingActiveList;
	@FXML
	private ListView<PhoneCard> participatingClosedList;

	@FXML
	public void expandList(Event event) {
		
	}
	
	@FXML
	private void initialize() {
		try {
			List<Auction> myActive = Gui.get().userManager.getActiveAuctions();
			List<Auction> myClosed = Gui.get().userManager.getClosedAuctions();
			List<Auction> partActive = Gui.get().userManager.getActiveParticipations();
			List<Auction> partClosed = Gui.get().userManager.getClosedParticipations();
			
			setList(myActiveList, myActive);
			setList(myClosedList, myClosed);
			setList(participatingActiveList, partActive);
			setList(participatingClosedList, partClosed);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setList(ListView<PhoneCard> listview, List<Auction> list) {
		listview.getItems().clear();
		
		list.forEach((a) -> {
			Phone p = a.getPhone();
			
			PhoneCard pc = new PhoneCard();
			
			pc.setPhoneName(p.getName());
			
			if(!p.getPictures().isEmpty())
				pc.setImage(
					new Image(
						new ByteArrayInputStream(
							p.getPictures().iterator().next().getPicture()
						)
					)
				);
			else 
				pc.setImage(new Image(Gui.class.getResourceAsStream("res/ico/no-phone-photo.png")));
			
			pc.setOs(p.getOs() + " " + p.getOsVersion());
			pc.setStorage(p.getInternalStorage()+ " GB storage");
			pc.setRam(p.getRamString() + " RAM");
			pc.setScreen(String.format("%.1f | %s", p.getScreenSize(), p.getScreenRes()));
			
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
			
			pc.setCamera(camera);
			
			pc.setMinimalBid(a.getBid());
			pc.setCurrentBid(a.getBid());
			pc.setUsername(a.getUser().getUsername());
			
			if(a.getUser().getAvatar().length > 0)
				pc.setAvatar(
					new Image(
						new ByteArrayInputStream(
							a.getUser().getAvatar()
						)
					)
				);
			
			pc.setOnConfirm((event) -> {
				try {
					PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
					if(pm.postBid(Gui.get().userManager.getUser(), a, pc.getCurrentBid())) {
						pc.setMinimalBid(pc.getCurrentBid()+1);
						pc.setCurrentBid(pc.getCurrentBid()+1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			listview.getItems().add(pc);
			
		});
	}

}