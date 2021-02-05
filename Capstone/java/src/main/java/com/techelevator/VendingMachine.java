package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class VendingMachine {
private Boolean VendingMachineSwitch;
private Double moneyOwed = 1.0;
private Map <String, Item> itemsWithLocations = new TreeMap<String,Item>();

private Double moneyIn = 5.0;
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
	
	public void loadFile() throws FileNotFoundException {
		if(VendingMachineSwitch) {
			File theFile = new File("./vendingmachine.csv");
			Scanner fileReader = new Scanner(theFile);
			while (fileReader.hasNext()) {
				String aLine = fileReader.nextLine();
				String[] theItems = aLine.split("\\|");
					Item newVar = new Item(theItems[0],theItems[1],Double.parseDouble (theItems[2]), theItems[3]);
						itemsWithLocations.put(theItems[0], newVar);
					Set<String>itemKeys = itemsWithLocations.keySet();
						System.out.println(itemsWithLocations.get("A1").getName());
					
					
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
	System.out.println(i.getName() + " " + i.getPrice() + " " + moneyOwed + " " + message );
	return i.getName() + i.getPrice() + moneyOwed + message;
	
	}
	public double giveChange() {
		double changeOwed = moneyIn - moneyOwed;
		moneyOwed = 0.0;
		System.out.println(changeOwed);
		
		return changeOwed;
	
	}
	 public void audit() throws IOException {
			File f = new File("./log.txt");
			f.createNewFile();
			
			
	 }
	private void recieveMoney() {
		// TODO Auto-generated method stub
		
	}	
	}