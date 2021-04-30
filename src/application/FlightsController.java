package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.*;
//import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.*;

public class FlightsController {
	@FXML
	private Button btnHome;
	@FXML
	private TextField txtDepartureCity;
	@FXML
	private TextField txtArrivalCity;
	@FXML
	private ListView<String> lvDepartureAirport;
	@FXML
	private ListView<String> lvArrivalAirport;
	@FXML
	private ListView<String> lvFlights;
	@FXML
	private DatePicker dpDepartureDate;
	@FXML
	private DatePicker dpArrivalDate;
	@FXML
	private Button btnFind;	
	
	HashMap<String, Object> departureLocations = new HashMap<String,Object>();
	HashMap<String, Object> arrivalLocations = new HashMap<String,Object>();
	
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
	public void showFlights() throws IOException
	{
		//hmap.get(lbxLookup.getSelectionModel().getSelectedItem().toString())
		
		//Check to see if an departure and arrival location has been set
		try
		{
			if ((lvDepartureAirport.getSelectionModel().getSelectedItem().toString() != "")&&(lvArrivalAirport.getSelectionModel().getSelectedItem().toString() != ""))
			{
				System.out.println("Selected: " + lvDepartureAirport.getSelectionModel().getSelectedItem().toString());
			}
			else
			{
				System.out.println("Nothing Selected");
			}
			if (dpDepartureDate.getValue()==null)
			{
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.INFORMATION);
				a.setContentText("You must have at least a departure date set!");
				a.show();
				return;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText("Please select both a destination and arrival airport!");
			a.show();
			return;
		}
		
		int departureindex = lvDepartureAirport.getSelectionModel().getSelectedIndex();
		int arrivalindex = lvArrivalAirport.getSelectionModel().getSelectedIndex();
		
		String departureAirport;
		String arrivalAirport;
		
		ArrayList departure_locations = (ArrayList)(departureLocations.get("Places"));
		ArrayList arrival_locations = (ArrayList)(arrivalLocations.get("Places"));
		
		departureAirport = ((Map)departure_locations.get(departureindex)).get("PlaceId").toString();
		arrivalAirport = ((Map)arrival_locations.get(departureindex)).get("PlaceId").toString();

		System.out.println(departureAirport);
		System.out.println(arrivalAirport);
		
		
		
		System.out.println("Attempting to send GET request");
		//https://rapidapi.com/skyscanner/api/skyscanner-flight-search?endpoint=5aa1eab3e4b00687d3574279
		try
		{
			URL url;
			//Set the connection URL including any variables to post
			if (dpArrivalDate.getValue()!= null)
			{
				url = new URL("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US/" + departureAirport + "/" + arrivalAirport + "/" + dpDepartureDate.getValue() + "?inboundpartialdate=" + dpArrivalDate.getValue());
			}
			else
			{
				url = new URL("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US/" + departureAirport + "/" + arrivalAirport + "/" + dpDepartureDate.getValue() + "?inboundpartialdate=anytime");	
			}
			//Create our connection
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//Set the request type to GET
			con.setRequestMethod("GET");
			
			//Add the API key and extra header information needed in order to obtain a response
			con.addRequestProperty("x-rapidapi-key", "25896e8c12msh6e1b15fa4912c2ap192c48jsn16bc3a506c74");
			con.addRequestProperty("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
			
			//Commit the GET request and return to the buffered reader
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String buffer = "";
			
			//capture our result
			while (input.ready())
			{
				buffer += input.readLine();				
			}
			
			//close our connection
			input.close();
			
			
			System.out.println(buffer);
			System.out.println("GET Request Success");
			
			//Next we need to send this JSON data to an JSON structure to access easily
			
			Map<String, Object> result = new ObjectMapper().readValue(buffer, HashMap.class);

			System.out.println("A");

			ArrayList quotes = (ArrayList)(result.get("Quotes"));

			lvFlights.getItems().clear();
			
			for (int i = 0; i < quotes.size(); i++)
			{
				System.out.println(quotes.get(i));
				lvFlights.getItems().add(" - $" + ((Map)quotes.get(i)).get("MinPrice").toString() + " on " + dpDepartureDate.getValue() + " Direct Flight: " + ((Map)quotes.get(i)).get("Direct").toString());
			}
			
			System.out.println("A: " + quotes);
			
			System.out.println("departure date: " + dpDepartureDate.getValue());
			System.out.println("arrival date: " + dpArrivalDate.getValue());
			
		}
		catch (Exception e)
		{
			System.out.println("GET Request Failed. " + e.getMessage());
		}
	}
	
	@FXML
	public void txtDepartureCity_onChange()
	{
		try
			{
				//Set the connection URL including any variables to post
				URL url = new URL("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/US/USD/en-US/?query=" + txtDepartureCity.getText().replace(" ", "%20"));
				
				//Create our connection
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				
				//Set the request type to GET
				con.setRequestMethod("GET");
				
				//Add the API key and extra header information needed in order to obtain a response
				con.addRequestProperty("x-rapidapi-key", "25896e8c12msh6e1b15fa4912c2ap192c48jsn16bc3a506c74");
				con.addRequestProperty("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
				
				//Commit the GET request and return to the buffered reader
				BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String buffer = "";
				
				//capture our result
				while (input.ready())
				{
					buffer += input.readLine();				
				}
				
				//close our connection
				input.close();
				
				
				System.out.println(buffer);
				System.out.println("GET Request Success");
				
				//Next we need to send this JSON data to an JSON structure to access easily
				
				Map<String, Object> result = new ObjectMapper().readValue(buffer, HashMap.class);
				
				ArrayList places = (ArrayList)(result.get("Places"));
	
				lvDepartureAirport.getItems().clear();
				for (int i = 0; i < places.size(); i++)
				{
					lvDepartureAirport.getItems().add(((Map)places.get(i)).get("PlaceName").toString());
				}

				departureLocations = (HashMap<String, Object>) result;
				
				System.out.println(departureLocations);
			}
			catch (Exception e)
			{
				System.out.println("GET Request Failed. " + e.getMessage());
			}
		}
	
	@FXML
	public void txtArrivalCity_onChange()
	{
		try
			{
				//Set the connection URL including any variables to post
				URL url = new URL("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/US/USD/en-US/?query=" + txtArrivalCity.getText().replace(" ", "%20"));
				
				//Create our connection
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				
				//Set the request type to GET
				con.setRequestMethod("GET");
				
				//Add the API key and extra header information needed in order to obtain a response
				con.addRequestProperty("x-rapidapi-key", "25896e8c12msh6e1b15fa4912c2ap192c48jsn16bc3a506c74");
				con.addRequestProperty("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
				
				//Commit the GET request and return to the buffered reader
				BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String buffer = "";
				
				//capture our result
				while (input.ready())
				{
					buffer += input.readLine();				
				}
				
				//close our connection
				input.close();
				
				
				System.out.println(buffer);
				System.out.println("GET Request Success");
				
				//Next we need to send this JSON data to an JSON structure to access easily
				
				Map<String, Object> result = new ObjectMapper().readValue(buffer, HashMap.class);
				
				ArrayList places = (ArrayList)(result.get("Places"));
	
				lvArrivalAirport.getItems().clear();
				for (int i = 0; i < places.size(); i++)
				{
					lvArrivalAirport.getItems().add(((Map)places.get(i)).get("PlaceName").toString());
				}
				
				arrivalLocations = (HashMap<String, Object>) result;
				System.out.println(arrivalLocations);
			}
			catch (Exception e)
			{
				System.out.println("GET Request Failed. " + e.getMessage());
			}
		}
}
