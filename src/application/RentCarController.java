package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import application.RentCarModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class RentCarController {
	
	RentCarModel model = new RentCarModel();
	
	@FXML
	private Button btnHome;
	
	@FXML
    	private TextField priceField;

    	@FXML
    	private Button searchBtn;
	
	@FXML
    	private TextArea resultsField;
	
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
