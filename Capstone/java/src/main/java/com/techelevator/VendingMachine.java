package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class VendingMachine {
private Boolean VendingMachineSwitch;
private Double moneyOwed;
private Map <String, Item> itemsWithLocations = new TreeMap<String,Item>();

private Double moneyIn;
private Double changeGiven;

	public Boolean getVendingMachineSwitch() {
	return VendingMachineSwitch;
	
}

public void setVendingMachineSwitch(Boolean vendingMachineSwitch) {
	VendingMachineSwitch = vendingMachineSwitch;
	
}

public Double getMoneyOwed() {
	return moneyOwed;
}

public void setMoneyOwed(Double moneyOwed) {
	this.moneyOwed = moneyOwed;
}


public Map<String, Item> getItemsWithLocations() {
	return itemsWithLocations;
}

public void setItemsWithLocations(Map<String, Item> itemsWithLocations) {
	this.itemsWithLocations = itemsWithLocations;
}


public Double getMoneyIn() {
	return moneyIn;
}

public void setMoneyIn(Double moneyIn) {
	this.moneyIn = moneyIn;
}

public Double getChangeGiven() {
	return changeGiven;
}

public void setChangeGiven(Double changeGiven) {
	this.changeGiven = changeGiven;
}

	public VendingMachine() {
		VendingMachineSwitch = true;
		moneyOwed = 0.00;
		
		moneyIn = 0.00;
		changeGiven = 0.00;
		
	}
	
	public VendingMachine(double moneyIn) {
		this.moneyIn = moneyIn;
	}
	
	public void loadFile() throws FileNotFoundException {
		if(VendingMachineSwitch) {
			File theFile = new File("./vendingmachine.csv");
			Scanner fileReader = new Scanner(theFile);
			while (fileReader.hasNextLine()) {
				String aLine = fileReader.nextLine();
				String[] theItems = aLine.split("\\|");
				Item newVar = new Item(theItems[0],theItems[1],Double.parseDouble (theItems[2]), theItems[3]);
				itemsWithLocations.put(theItems[0], newVar);
				System.out.println(theItems[0] + " "+ newVar.getName() + " " + newVar.getPrice());
			}
			fileReader.close();
		}
	}
	
	public String dispense(Item i) {
	String message = " " ;
	if		(i.getType().equals("Chip")) {
		message = "Crunch Crunch Yum!";
	} 
	else if (i.getType().equals("Candy")){
		message = "Munch Munch Yum!";
	}
	else if (i.getType().equals("Drink")) {
		message =  "Glug Glug Yum!";
	}
	else if (i.getType().equals("Gum")) {
		message = "Chew Chew Yum!";
	}
	System.out.println(i.getName() + " " + i.getPrice() + " " + (moneyIn - i.getPrice()) + " " + message);
	return i.getName() + " " + i.getPrice() + " " + (moneyIn - i.getPrice()) + " " + message;
	}
	
	public double giveChange() {
		double changeOwed = moneyIn - moneyOwed;
		final double FIVE_CENT = 0.05;
		final double TEN_CENT = 0.10;
		final double TWENTYFIVE_CENT = 0.25;
		
		// changeOwed
		// break it into quarters, dimes and nickels
		// break it into increments of 0.25, 0.10 and 0.05
		double changeLeft = changeOwed;
		int quartersGiven = (int) (changeOwed / TWENTYFIVE_CENT);
		int dimesGiven = 0;
		int nickelsGiven = 0;
		while (changeLeft > 0.0) {
			quartersGiven = (int) (changeOwed / TWENTYFIVE_CENT);
			for (int i=0; i < quartersGiven; i++) {
				changeLeft -= .25;
			}
			double changeOwedAfterQuarters = changeOwed % TWENTYFIVE_CENT;
			dimesGiven = (int) (changeOwedAfterQuarters / TEN_CENT);
			double changeOwedAfterDimes = changeOwedAfterQuarters % TEN_CENT;
			nickelsGiven = (int) (changeOwedAfterDimes / FIVE_CENT);
		}
		System.out.println(changeOwed + "\nQuarters: " + quartersGiven + "\nDimes: " + dimesGiven + "\nNickels: " + nickelsGiven);
		return changeOwed;
		}
		
	 public void audit(String userInput, String userInput2) throws IOException {
			File f = new File("./log.txt");
			f.createNewFile();
			
			Scanner theFile = new Scanner(f);
			FileWriter aFileWriter = new FileWriter(f, true);
			BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
			PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);
			Timestamp timeStampNow = Timestamp.valueOf(LocalDateTime.now());
			String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timeStampNow);
			String message1 = " ";
			Double message2 = 0.0;
			if (userInput.equals("1")) {
				message1 = "FEED MONEY: ";
				message2 = Double.parseDouble(userInput2);
			}
			if (userInput.equals("3")) {
				//message1 = 
			}
			diskFileWriter.println(s + " " + message1 + " " + message2 + " " + moneyIn);
			
			diskFileWriter.close();
	 }
	private void recieveMoney() {
		// TODO Auto-generated method stub
		
	}	
	}