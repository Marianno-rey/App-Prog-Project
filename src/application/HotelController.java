package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HotelController {
	@FXML
	private Button btnHome;
	
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

}
