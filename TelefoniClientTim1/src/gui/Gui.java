package gui;

import javax.naming.InitialContext;

import beans.filter.FilterEJB;
import beans.filter.FilterManager;
import beans.post.PostEJB;
import beans.post.PostManager;
import beans.user.UserEJB;
import beans.user.UserManager;
import gui.controller.AuctionDetailsController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Auction;

public class Gui extends Application {

	private class Loaded {
		public Node node;
		public Controller controller;
	}

	private static Gui instance;

	public InitialContext context;
	public UserManager userManager;

	public static String USER_BEAN = "ejb:/TelefoniServerTim1//" + UserEJB.class.getSimpleName() + "!"
			+ UserManager.class.getName() + "?stateful";
	public static String POST_BEAN = "ejb:/TelefoniServerTim1//" + PostEJB.class.getSimpleName() + "!"
			+ PostManager.class.getName();
	public static String FILTER_BEAN = "ejb:/TelefoniServerTim1//" + FilterEJB.class.getSimpleName() + "!"
			+ FilterManager.class.getName();

	private Stage stage;

	public Gui() {
		instance = this;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			context = new InitialContext();

			userManager = (UserManager) context.lookup(USER_BEAN);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "The server is not online. Application closing...");
			alert.showAndWait();
			Platform.exit();
			return;
		}

		stage = primaryStage;

		stage.setTitle("Mobile store");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("res/ico/logo.png")));

		openLoginView();

		stage.show();
	}

	@Override
	public void stop() {
		userManager.logout();
	}

	public static Gui get() {
		return instance;
	}

	private Loaded loadView(String file) {
		Loaded l = new Loaded();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		try {
			l.node = loader.load();
			l.controller = loader.<Controller> getController();
			l.controller.setGui(this);
		} catch (Exception e) {
		}
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
		if (stage.getScene().getRoot().getId().equals("container"))
			return;

		Loaded l = loadView("res/container.fxml");
		stage.setScene(new Scene((Parent) l.node));
		stage.centerOnScreen();
	}

	public void openBoardView() {
		openContainerView();

		Loaded l = loadView("res/board.fxml");
		((BorderPane) stage.getScene().getRoot()).setCenter(l.node);

		stage.setResizable(true);
	}

	public void openAddPhoneView() {
		openContainerView();

		Loaded l = loadView("res/add-phone.fxml");
		((BorderPane) stage.getScene().getRoot()).setCenter(l.node);

		stage.setResizable(true);
	}

	public void openAllPhonesView() {
		openContainerView();

		Loaded l = loadView("res/all-phones.fxml");
		((BorderPane) stage.getScene().getRoot()).setCenter(l.node);

		stage.setResizable(true);
	}

	public void openAuctionDetailsView(Auction a) {
		openAuctionDetailsView(a, 0);
	}
	
	public void openAuctionDetailsView(Auction a, int tab) {
		openContainerView();

		Loaded l = loadView("res/auction-details.fxml");
		((BorderPane) stage.getScene().getRoot()).setCenter(l.node);

		((AuctionDetailsController) l.controller).setAuction(a);
		((AuctionDetailsController) l.controller).setTab(tab);
		
		stage.setResizable(true);
	}

	public Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		Gui.launch(args);
	}

}