package com.internshala.connectfour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class HelloApplication extends Application {

	private Controller controller;

	public void start(Stage PrimaryStage) throws Exception {
		try {
			VBox rootnode1 = new VBox();

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
			GridPane rootnode2 = fxmlLoader.load();

			controller = fxmlLoader.getController();
			controller.createPlayground();

			MenuBar menuBar = createMenu();
			menuBar.prefWidthProperty().bind(PrimaryStage.widthProperty());

			Pane menuPane = (Pane) rootnode2.getChildren().get(0);
			menuPane.getChildren().add(menuBar);


			// VBox rootnode2 = new VBox();

			// rootnode1.setAlignment(Pos.CENTER);
			// rootnode2.setAlignment(Pos.CENTER);

			Scene scene1 = new Scene(rootnode1,1000, 560);
			Scene scene2 = new Scene(rootnode2,900,590);


			// Create Input Stream
			FileInputStream input = new FileInputStream("E:\\Connect_Four_3.jpg");

			// Create image

			Image image = new Image(input);

			// Create Background Image

			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			// create Background
			Background background = new Background(backgroundImage);

			// set Background
			rootnode1.setBackground(background);


			Button button = new Button("Start Game");
			VBox vBox = new VBox(button);
			Font font = Font.font("Courrier New", FontWeight.BOLD, 18);
			button.setFont(font);
			button.setMaxWidth(200);
			button.setStyle("-fx-background-color: #ADD8E6 ; -fx-text-fill: #000000; -fx-border-width : 10px");
			// button.setStyle("-fx-text-fill: #0000ff");
			// For Color
			VBox.setMargin(button, new Insets(510, 421, 500, 421));

			rootnode1.getChildren().addAll(button);

			// For Scene Two

			Group group = new Group();
			Canvas canvas = new Canvas();
			group.getChildren().addAll(canvas);
			// Scene scene2 = new Scene(group);

			// To Call Scene Two

			button.setOnAction(event ->
			{
				PrimaryStage.setScene(scene2);
			});


			PrimaryStage.setTitle("Connect Four");
			PrimaryStage.setScene(scene1);
			PrimaryStage.show();
			PrimaryStage.setResizable(false);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private MenuBar createMenu() {

		// File Menu
		Menu fileMenu = new Menu("File");

		MenuItem newGame = new MenuItem("New game");
		newGame.setOnAction(event -> controller.resetGame());

		MenuItem resetGame = new MenuItem("Reset game");
		resetGame.setOnAction(event -> controller.resetGame());

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		MenuItem exitGame = new MenuItem("Exit game");
		exitGame.setOnAction(event -> exitGame());

		fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

		// Help Menu
		Menu helpMenu = new Menu("Help");

		MenuItem aboutGame = new MenuItem("About Connect4");
		aboutGame.setOnAction(event -> aboutConnect4());

		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem aboutMe = new MenuItem("About Me");
		aboutMe.setOnAction(event -> aboutMe());

		helpMenu.getItems().addAll(aboutGame, separator, aboutMe);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, helpMenu);

		return menuBar;
	}

	private void exitGame() {
		System.exit(0);
	}

	private void aboutConnect4() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About Connect Four");
		alert.setHeaderText("How To Play?");
		alert.setContentText("Connect Four is a two-player connection game in which the " +
				"players first choose a color and then take turns dropping colored discs " +
				"from the top into a seven-column, six-row vertically suspended grid. "+
				"The pieces fall straight down, occupying the next available space within the column. "+
				"The objective of the game is to be the first to form a horizontal, vertical, " +
				"or diagonal line of four of one's own discs. Connect Four is a solved game. " +
				"The first player can always win by playing the right moves.");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

		alert.show();
	}

	private void aboutMe()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About The Developer");
		alert.setHeaderText("Priyansh Jain");
		alert.setContentText("I love to play around with code and create games. " +
				"Connect 4 is one of them. In free time " +
				"I like to spend time with nears and dears.");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}


