package gui.custom;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.naming.NamingException;

import beans.post.PostManager;
import gui.Gui;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.User;

public class Comment extends VBox {
	@FXML
	private ImageView userAvatar;
	@FXML
	private Label username;
	@FXML
	private Button reply;
	@FXML
	private Text text;
	@FXML
	private AnchorPane inputHolder;
	@FXML
	private TextArea commentInput;
	@FXML
	private AnchorPane bigHolder;
	
	private model.Comment comment;
	
	public Comment(model.Comment comment) {
		this();
		this.setComment(comment);
	}
	
	public Comment() {
		FXMLLoader loader = new FXMLLoader(Gui.class.getResource("res/comment.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		text.wrappingWidthProperty().bind(bigHolder.widthProperty().subtract(150));
	}
	
	public void setComment(model.Comment comment) {
		this.comment = comment;
		
		User user = comment.getUser();
		
		this.username.setText(user.getUsername());
		
		if(user.getAvatar() != null && user.getAvatar().length > 0)
			this.userAvatar.setImage(
				new Image(
					new ByteArrayInputStream(user.getAvatar())
				)
			);
		
		this.text.setText(comment.getText());
		
		this.reply.setVisible(comment.getParent() == null && !comment.getAuction().getClosed());
		
		if(comment.getParent() != null) {
			this.setPadding(new Insets(0, 0, 0, 80));
		}
		
		this.getChildren().remove(1);
	}
	
	public model.Comment getComment() {
		return comment;
	}
	
	@FXML
	private void commentAction(Event event) {
		try {
			PostManager pm = (PostManager) Gui.get().context.lookup(Gui.POST_BEAN);
			
			User u = Gui.get().userManager.getUser();
			
			model.Comment c = new model.Comment();
			c.setUser(u);
			c.setText(commentInput.getText());
			c.setDate(new Date());
			c.setParent(this.comment);
			
			if(pm.postComment(u, this.comment.getAuction(), c, this.comment)) {
				Gui.get().openAuctionDetailsView(this.comment.getAuction(), 2);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void replyAction(Event event) {
		if(this.getChildren().size() == 1)
			this.getChildren().add(inputHolder);
		else
			this.getChildren().remove(1);
	}
}
