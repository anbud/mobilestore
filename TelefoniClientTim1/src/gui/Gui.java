package gui;

import javax.naming.InitialContext;

import beans.post.PostEJB;
import beans.post.PostManager;
import beans.user.UserEJB;
import beans.user.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {
	
	private class Loaded {
		public Node node;
		public Controller controller;
	}
	
	public UserManager userManager;
	public InitialContext context;
	
	public static String USER_BEAN = "ejb:/TelefoniServerTim1//" + UserEJB.class.getSimpleName() + "!" + UserManager.class.getName() + "?stateful";
	public static String POST_BEAN = "ejb:/TelefoniServerTim1//" + PostEJB.class.getSimpleName() + "!" + PostManager.class.getName();
	
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		stage.setTitle("Mobile store");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("res/ico/logo.png")));
		
		context = new InitialContext();
		
		userManager = (UserManager) context.lookup(USER_BEAN);
		
		openLoginView();
		
		stage.show();
	}
	
	private Loaded loadView(String file) {
		Loaded l = new Loaded();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		try {
			l.node = loader.load();
			l.controller = loader.<Controller>getController();
			l.controller.setGui(this);
		}
		catch(Exception e) { }
		return l;
	}
	
	public void openLoginView() {
		Loaded l = loadView("res/login.fxml");
		stage.setScene(new Scene((Parent) l.node));
		stage.setResizable(false);
		stage.centerOnScreen();
	}
	
	public void openSignupView() {
		Loaded l = loadView("res/register.fxml");
		stage.setScene(new Scene((Parent) l.node));
		stage.setResizable(false);
		stage.centerOnScreen();
	}
	
	private void openContainerView() {
		if( stage.getScene().getRoot().getId().equals("container") )
			return;
		
		Loaded l = loadView("res/container.fxml");
		stage.setScene(new Scene( (Parent) l.node ));
		stage.centerOnScreen();
	}
	
	public void openBoardView() {
		openContainerView();
		
		Loaded l = loadView("res/board.fxml");
		( (BorderPane) stage.getScene().getRoot() ).setCenter(l.node);
		
		stage.setResizable(true);
	}
	
	public Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		Gui.launch(args);
	}

}