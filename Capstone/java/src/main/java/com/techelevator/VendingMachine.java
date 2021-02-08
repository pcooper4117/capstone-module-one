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
private Map <String, Integer> itemsWithQuantitiesSold = new TreeMap<String, Integer>();
private Map <String, String> itemsWithMessages = new TreeMap<String, String>();
private Double totalSales = 0.00;
/**
 * @return the totalSales
 */
public Double getTotalSales() {
	return totalSales;
}

/**
 * @param totalSales the totalSales to set
 */
public void setTotalSales(Double totalSales) {
	this.totalSales = totalSales;
}

/**
 * @return the itemsWithMessages
 */
public Map<String, String> getItemsWithMessages() {
	return itemsWithMessages;
}

/**
 * @param itemsWithMessages the itemsWithMessages to set
 */
public void setItemsWithMessages(Map<String, String> itemsWithMessages) {
	this.itemsWithMessages = itemsWithMessages;
}

/**
 * @return the itemsWithQuantitiesSold
 */
public Map<String, Integer> getItemsWithQuantitiesSold() {
	return itemsWithQuantitiesSold;
}

/**
 * @param itemsWithQuantitiesSold the itemsWithQuantitiesSold to set
 */
public void setItemsWithQuantitiesSold(Map<String, Integer> itemsWithQuantitiesSold) {
	this.itemsWithQuantitiesSold = itemsWithQuantitiesSold;
}


private Double balanceLeft = 0.0;
private Double moneyIn;
private Double changeGiven;
private ArrayList<String> messages = new ArrayList();
private ArrayList<Double> balances = new ArrayList();
private ArrayList<String> userSelections = new ArrayList<String>();
private ArrayList<Item> ourItems = new ArrayList();
private ArrayList<String> salesReportMessages = new ArrayList();
/**
 * @return the salesReportMessages
 */
public ArrayList<String> getSalesReportMessages() {
	return salesReportMessages;
}

/**
 * @param salesReportMessages the salesReportMessages to set
 */
public void setSalesReportMessages(ArrayList<String> salesReportMessages) {
	this.salesReportMessages = salesReportMessages;
}

/**
 * @return the ourItems
 */
public ArrayList<Item> getOurItems() {
	return ourItems;
}

/**
 * @param ourItems the ourItems to set
 */
public void setOurItems(ArrayList<Item> ourItems) {
	this.ourItems = ourItems;
}

/**
 * @return the balances
 */
public ArrayList<Double> getBalances() {
	return balances;
}

/**
 * @param balances the balances to set
 */
public void setBalances(ArrayList<Double> balances) {
	this.balances = balances;
}

/**
 * @return the messages
 */
public ArrayList<String> getMessages() {
	return messages;
}

/**
 * @param messages the messages to set
 */
public void setMessages(ArrayList<String> messages) {
	this.messages = messages;
}

	public ArrayList<String> getUserSelections() {
	return userSelections;
}

public void setUserSelections(ArrayList<String> userSelections) {
	this.userSelections = userSelections;
}

	public Double getBalanceLeft() {
	return balanceLeft;
}

public void setBalanceLeft(Double balanceLeft) {
	this.balanceLeft = balanceLeft;
}

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
	
	DecimalFormat format = new DecimalFormat("0.##");
	
	System.out.printf(i.getName() + " " + i.getPrice() + " " + format.format(balanceLeft) + " " + message);
	return i.getName() + " " + i.getPrice() + " " + format.format(balanceLeft) + " " + message;
	}
	
	public double giveChange() {
		double changeOwed = balanceLeft;
		final int FIVE_CENT = 5;
		final int TEN_CENT = 10;
		final int TWENTYFIVE_CENT = 25;
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		// changeOwed
		// break it into quarters, dimes and nickels
		// break it into increments of 0.25, 0.10 and 0.05
		int change = (int) (changeOwed * 100);
		int quartersGiven = (change / TWENTYFIVE_CENT);
		int dimesGiven = 0;
		int nickelsGiven = 0;
		
	
		quartersGiven = (change / TWENTYFIVE_CENT);
		int changeOwedAfterQuarters = change % TWENTYFIVE_CENT;
		dimesGiven = changeOwedAfterQuarters / TEN_CENT;
		int changeOwedAfterDimes = changeOwedAfterQuarters % TEN_CENT;
		nickelsGiven = changeOwedAfterDimes / FIVE_CENT;
		
		System.out.println("Change owed: " + formatter.format(changeOwed) + "\nQuarters: " + quartersGiven + "\nDimes: " + dimesGiven + "\nNickels: " + nickelsGiven);
		return changeOwed;
		}
		
	 public void audit(String userInput, String userInput2) throws IOException {
		 	DecimalFormat formatter = new DecimalFormat("0.00");
			File f = new File("./log.txt");
			f.createNewFile();
			Scanner theFile = new Scanner(f);
			FileWriter aFileWriter = new FileWriter(f, true);
			BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
			PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);
			Timestamp timeStampNow = Timestamp.valueOf(LocalDateTime.now());
			String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timeStampNow);
			String message1 = " ";
			String message2 = " ";
			Double message3 = 0.0;
			String message4 = "";
			DecimalFormat format = new DecimalFormat("0.##");
			if (userInput.equals("1")) {
				message1 = "FEED MONEY: ";
				message2 = userInput2 + ".00";
				message3 = moneyIn;
				diskFileWriter.println(s + " " + message1 + " " + "$" + formatter.format(Double.parseDouble(message2)) + " " + "$" + formatter.format(message3));
				diskFileWriter.close();
			}
			if (userInput.equals("3")) {
				
				// ArrayList of messages
				// go through the for loop and add the message for each userSelection
				// 
				message2 =  format.format(moneyIn);
				// 
				for (int i = 0; i < messages.size(); i++) {
					message1 = messages.get(i);
					message3 = balances.get(i);
					diskFileWriter.println(s + " " + message1 + " " + "$" + formatter.format(Double.parseDouble(message2)) + " " + "$" + formatter.format(message3));
					message2 = balances.get(i).toString();
					message4 = (s + " GIVE CHANGE: " + " $" + formatter.format(balances.get(i)) + " $0.00");
				}
				diskFileWriter.println(message4);
				balanceLeft = 0.00;
				setBalances(new ArrayList<Double>());
				setMessages(new ArrayList<String>());
				
				diskFileWriter.close();
			}
			}
			
	 
	 public void  salesReport() throws IOException {
		Timestamp timeStampNow = Timestamp.valueOf(LocalDateTime.now());
		String s = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(timeStampNow);
		File g = new File("./salesReport" + s + ".txt");
		g.createNewFile();
		FileWriter aFileWriter = new FileWriter(g, true);
		BufferedWriter aBufferedWriter = new BufferedWriter(aFileWriter);
		PrintWriter diskFileWriter = new PrintWriter(aBufferedWriter);
		Set<String> theKeys = itemsWithMessages.keySet();
		for (String key : theKeys) {
			// it doesn't print out the same name within the message
			diskFileWriter.println(itemsWithMessages.get(key));
		}
		diskFileWriter.printf("\nTotal Sales: $%.2f", getTotalSales());
		diskFileWriter.close();	
		return;
	 }
}