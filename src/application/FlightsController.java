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
		System.out.println("Attempting to send GET request");
		//https://rapidapi.com/skyscanner/api/skyscanner-flight-search?endpoint=5aa1eab3e4b00687d3574279
		try
		{
			//Set the connection URL including any variables to post
			URL url = new URL("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US/SFO-sky/JFK-sky/anytime?inboundpartialdate=anytime");
			
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
			//ObjectMapper mapper = new ObjectMapper();
			
			
			//Map<String, Object> jsonMap = new HashMap<String, Object>();
			
			//
			//jsonMap = mapper.readValue(buffer, new TypeReference<Map<String,String>>(){});
			
			System.out.println("A");
			
			//for (int i = 0; i < ((Map)result.get("Quotes")).size(); i++)
			//{
			ArrayList test = (ArrayList)(result.get("Quotes"));
			//}
			
			for (int i = 0; i < test.size(); i++)
			{
				System.out.println(test.get(i));
			}
			
			System.out.println("A: " + test);
			
			//After sending to JSON structure, loop through the quotes and list them in a ListView
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
				
				ArrayList test = (ArrayList)(result.get("Places"));
	
				lvDepartureAirport.getItems().clear();
				for (int i = 0; i < test.size(); i++)
				{
					lvDepartureAirport.getItems().add(((Map)test.get(i)).get("PlaceName").toString());
				}
				
				System.out.println("A: " + test);
				
				//After sending to JSON structure, loop through the quotes and list them in a ListView
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
				
				ArrayList test = (ArrayList)(result.get("Places"));
	
				lvArrivalAirport.getItems().clear();
				for (int i = 0; i < test.size(); i++)
				{
					lvArrivalAirport.getItems().add(((Map)test.get(i)).get("PlaceName").toString());
				}
				
				System.out.println("A: " + test);
				
				//After sending to JSON structure, loop through the quotes and list them in a ListView
			}
			catch (Exception e)
			{
				System.out.println("GET Request Failed. " + e.getMessage());
			}
		}
}
