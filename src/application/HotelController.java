
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
	// FXML private variables
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
	private boolean found = false;
	@FXML
	private AnchorPane mainPane;

	@FXML
	public void getResultsFunc() throws FileNotFoundException {
		found = false; // Everytime the button is pressed we set the "Found" to
						// false
		String userCountry = countryField.getText(); // Get User Input
		countryField.clear(); // Clear field
		String userCity = cityField.getText();// Get User Input
		cityField.clear();// Clear field
		String userPrice1 = priceR1.getText();// Get User Input
		String userPrice2 = priceR2.getText();// Get User Input
		priceR1.clear();// Clear field
		priceR2.clear();// Clear field

		File file = new File("hotels.txt"); // Open hotels.txt file
		String country = ""; // Empty string variables
		String city = "";
		String price = "";

		scan = new Scanner(new File("hotels.txt"));
		scan.useDelimiter("[,\n]"); // Use comma or new line as a delimeter to
									// separate values

		while (scan.hasNext()) {
			country = scan.next(); // Assign the country to the variable
			city = scan.next(); // Assign the city to the variable
			price = scan.next(); // Assign the price to the variable
			if (userCountry.equals(country) && userCity.equals(city)
					&& (Integer.parseInt(userPrice1) <= Integer.parseInt(price)
							|| Integer.parseInt(price) <= Integer.parseInt(userPrice2))) { // Compare
																							// every
																							// field
																							// the
																							// user
																							// input
																							// and
																							// compare
																							// inside
																							// the
																							// file
																							// in
																							// a
																							// while
																							// loop
				results.setText(
						"Found result: Country: " + country + ", City: " + city + " for the price of: " + price + "\n");
				found = true; // Change the value of found to true
			}
		}
		if (found == false) { // if found has not been changed; we display a not
								// found message
			results.setText("No results found");

		}
	}

	public void goHomeFunc(ActionEvent e) throws IOException { // Go to the main
																// fxml file if
																// pressed
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
