package application;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File; 

public class RentCarModel{
	public String getCars(int preferred_price) {

		String carsString = "";
		try {
			Scanner input = new Scanner(new File("data/cars.txt"));
			while(input.hasNext()){
				String car = input.nextLine();
				int price = input.nextInt();
				if((price-15) <= preferred_price && (price+15) >= preferred_price ){
					for (int i = 0; i < car.length(); i++) {
						carsString += car.charAt(i);
					}
					carsString += '\n';
				}
				input.nextLine();
			}
			input.close();

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return carsString;
	}
}
