package application;

import java.io.BufferedReader;
import java.io.FileReader;

public class AttractionsModel {
	
	public String getMonuments(String city) {
		
		String monumentsString = "";
		
		try {
			FileReader fr = new FileReader("data/Monuments.txt");
        	BufferedReader br = new BufferedReader(fr);
        	String currentLine;
        	
        	
        	while ((currentLine = br.readLine()) != null) {
        		
        		if (currentLine.startsWith(city)) {
        			for (int i = 0; i < 3; i++) {
        				currentLine = br.readLine();
        				for (int j = 0; j < currentLine.length(); j++) {
        					monumentsString += currentLine.charAt(j);
        				}
        				monumentsString += '\n';
        			}
        			break;
        		}
        	}
        	br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return monumentsString;
	}
	
	public String getEvents(String city) {
		String eventsString = "";
		
		try {
			FileReader fr = new FileReader("data/Events.txt");
        	BufferedReader br = new BufferedReader(fr);
        	String currentLine;
        	
        	
        	while ((currentLine = br.readLine()) != null) {
        		
        		if (currentLine.startsWith(city)) {
        			for (int i = 0; i < 3; i++) {
        				currentLine = br.readLine();
        				for (int j = 0; j < currentLine.length(); j++) {
        					eventsString += currentLine.charAt(j);
        				}
        				eventsString += '\n';
        			}
        			break;
        		}
        	}
        	br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventsString;
	}
}
