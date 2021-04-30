package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML
	public AnchorPane root;
	
	/*
	 * This method will check which button was clicked then switch the Scene to the proper page
	 */
	@FXML
	public void handleBtnClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Attractions.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
	}
	
	@FXML
	public void handleRentalsClick(ActionEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Rentals.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
		
	}
	
	@FXML
	public void handleHotelsClick(MouseEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Hotel.fxml"));
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
	}
	
	@FXML
	public void handleFlightsClick(ActionEvent event) throws IOException {
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Flights.fxml"));
		
		Scene newScene = new Scene(root, 800, 500);
		newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		currentWindow.setScene(newScene);
		currentWindow.show();
		
	}
	
}
