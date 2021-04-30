package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import AttractionsModel;

public class AttractionsController {
	
	AttractionsModel model = new AttractionsModel();
	
	@FXML
	private Button btnHome;
	
	@FXML
    	private TextField cityInput;
	
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
	
	@FXML
	public void handleSearch(MouseEvent event) {
    		String city = cityInput.getText();
   
    		try {
    			String monuments = model.getMonuments(city);
        		String events = model.getEvents(city);
        	
        		monumentsField.setText(monuments);
        		eventsField.setText(events);
    		}
    		catch (Exception e) {
    		System.out.print(e);
    		}
    	}

}
