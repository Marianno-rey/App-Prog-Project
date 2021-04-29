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
			Scene scene = new Scene(apMain,800,500);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
	public void scnFindAttractions()
	{
		try {
			AnchorPane apFindAttractions = (AnchorPane)FXMLLoader.load(getClass().getResource("FindAttractions.fxml"));
			Scene scene = new Scene(apFindAttractions,800,500);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
	public void scnBookHotel()
	{
		try {
			AnchorPane apBookHotel = (AnchorPane)FXMLLoader.load(getClass().getResource("BookHotel.fxml"));
			Scene scene = new Scene(apBookHotel,800,500);
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
			Scene scene = new Scene(apFlights,800,500);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
	public void scnRentCar()
	{
		try {
			AnchorPane apRentCar = (AnchorPane)FXMLLoader.load(getClass().getResource("RentCar.fxml"));
			Scene scene = new Scene(apRentCar,800,500);
			application.Main.setScene(scene);
		} catch(Exception e) {
			e.getSuppressed();
			System.out.println(e.getMessage());
		}
	}
	
}
