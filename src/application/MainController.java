package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.Node;

public class MainController {
	@FXML
	public AnchorPane root;
	
	/*
	 * This method will check which button was clicked then switch the Scene to the proper page
	 */
	@FXML
	public void handleAttractionsClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Attractions.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
	}
	
	@FXML
	public void handleRentalsClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("RentCar.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
		
	}
	
	@FXML
	public void handleHotelsClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("BookHotel.fxml"));
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
	}
	
	@FXML
	public void handleFlightsClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Flights.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
		
	}
	
}
