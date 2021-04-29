

package application;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HotelController {
	@FXML
	private TextField countryField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField priceR1, priceR2;
	@FXML
	private TextField results;
	@FXML
	private Button getResults;
	@FXML
	private static Scanner scan;
	@FXML
	private Button goHomeBtn;
	@FXML
	private boolean found=false;
	@FXML
	private AnchorPane mainPane;
	@FXML
	public void getResultsFunc() throws FileNotFoundException {
		found=false;
		String userCountry = countryField.getText();
		countryField.clear();
		String userCity = cityField.getText();
		cityField.clear();
		String userPrice1 = priceR1.getText();
		String userPrice2 = priceR2.getText();
		priceR1.clear();
		priceR2.clear();

		File file = new File("hotels.txt");
		String country = "";
		String city = "";
		String price = "";

		scan = new Scanner(new File("hotels.txt"));
		scan.useDelimiter("[,\n]");

		while (scan.hasNext()) {
			country = scan.next();
			city = scan.next();
			price = scan.next();
			if (userCountry.equals(country) && userCity.equals(city) && (Integer.parseInt(userPrice1) <= Integer.parseInt(price) || Integer.parseInt(price) <= Integer.parseInt(userPrice2))){
				results.setText("Found result: " + country + " "+city+" "+price + "\n");
				found = true;
			}
		}
		if (found==false){
			results.setText("No results found");

		}
	}
	public void goHomeFunc(ActionEvent e) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
