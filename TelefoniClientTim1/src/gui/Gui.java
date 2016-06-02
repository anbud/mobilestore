package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {
	
	private class Loaded {
		public Node node;
		public Controller controller;
	}
	
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		stage.setTitle("Mobile Store");
		
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
		catch(Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void openLoginView() {
		Loaded l = loadView("res/login.fxml");
		stage.setScene(new Scene((Parent) l.node));
		stage.centerOnScreen();
	}
	
	public void openSignupView() {
		Loaded l = loadView("res/register.fxml");
		stage.setScene(new Scene((Parent) l.node));
		stage.centerOnScreen();
	}
	
	

	public static void main(String[] args) {
		Gui.launch(args);
	}

}