package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML
	private Button btnFindAttractions;
	
	@FXML
	private Button btnBookHotel;
	
	@FXML
	private Button btnBookFlights;
	
	@FXML
	private Button btnRentCar;
	
	@FXML
	public void showMain()
	{
		try {
			//Load the main page
			AnchorPane apMain = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(apMain,800,600);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
	public void scnFlights()
	{
		try {
			AnchorPane apFlights = (AnchorPane)FXMLLoader.load(getClass().getResource("Flights.fxml"));
			Scene scene = new Scene(apFlights,800,600);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
}
