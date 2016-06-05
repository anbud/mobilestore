package gui.controller;

import java.util.List;

import beans.post.PostManager;
import gui.Controller;
import gui.Gui;
import gui.custom.PhoneCard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Auction;
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
			PhoneCard pc = new PhoneCard();
			pc.setAuction(a);
			listview.getItems().add(pc);
			
		});
	}

}