package com.techelevator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT          = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
													    MAIN_MENU_OPTION_SALES_REPORT
													    };
	VendingMachine ourVendingMachine = new VendingMachine();
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws IOException 
	*
	***************************************************************************************************************************/

	public void run() throws IOException {

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			Scanner userInput = new Scanner(System.in);
			
			
			
			VendingMachine ourVendingMachine = new VendingMachine();
			//ourVendingMachine.loadFile();
			//ourVendingMachine.dispense(ourVendingMachine.getItemsWithLocations().get("A2"));
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
					
				case MAIN_MENU_OPTION_SALES_REPORT:
					ourVendingMachine.salesReport();
					break;
					
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 * @throws IOException 
 ********************************************************************************************************/
	public void displayItems() throws IOException {      // static attribute used as method is not associated with specific object instance
		// Code to display items in Vending Machine
		VendingMachine ourVendingMachine = new VendingMachine();
		ourVendingMachine.loadFile();
		
		
		
		//VendingMachine ourVendingMachine = new VendingMachine();
			//ourVendingMachine.dispense(ourVendingMachine.getItemsWithLocations().get(""));
			//ourVendingMachine.giveChange();
			//ourVendingMachine.audit();
			
	}
	
	boolean shouldProcess = true;
	public void feedMoney() throws IOException {
		while (shouldProcess) {
		System.out.println("Please enter money in the following dollar amounts: $1, $2, $5, $10 (press quit to return to menu) >>> ");
		Scanner newScanner2 = new Scanner(System.in);
		String userInput2 = newScanner2.nextLine();
		
		// $1
		if (userInput2.equals("1")) {
			double newMoneyIn = ourVendingMachine.getMoneyIn() + 1;
			ourVendingMachine.setMoneyIn(newMoneyIn);
			ourVendingMachine.audit("1", userInput2);
			Double newBalanceLeft = (ourVendingMachine.getBalanceLeft() + 1.0);
			ourVendingMachine.setBalanceLeft(newBalanceLeft);
			purchaseItems();
		}
		// $2
		else if (userInput2.equals("2")) {
			double newMoneyIn = ourVendingMachine.getMoneyIn() + 2;
			ourVendingMachine.setMoneyIn(newMoneyIn);
			ourVendingMachine.audit("1", userInput2);
			Double newBalanceLeft = (ourVendingMachine.getBalanceLeft() + 2.0);
			ourVendingMachine.setBalanceLeft(newBalanceLeft);
			purchaseItems();
		}
		// $5
		else if (userInput2.equals("5")) {
			double newMoneyIn = ourVendingMachine.getMoneyIn() + 5;
			ourVendingMachine.setMoneyIn(newMoneyIn);
			ourVendingMachine.audit("1", userInput2);
			Double newBalanceLeft = (ourVendingMachine.getBalanceLeft() + 5.0);
			ourVendingMachine.setBalanceLeft(newBalanceLeft);
			purchaseItems();
		}
		// $10
		else if (userInput2.equals("10")) {
			double newMoneyIn = ourVendingMachine.getMoneyIn() + 10;
			ourVendingMachine.setMoneyIn(newMoneyIn);
			ourVendingMachine.audit("1", userInput2);
			Double newBalanceLeft = (ourVendingMachine.getBalanceLeft() + 10.0);
			ourVendingMachine.setBalanceLeft(newBalanceLeft);
			purchaseItems();
		}
		if (userInput2.equals("quit")) {
			shouldProcess = false;
			purchaseItems();
			return;
		}
		//newScanner2.close();
		return;
		}
	}
	
	
	public void selectProduct() throws IOException {
		ourVendingMachine.loadFile();
		
		System.out.println("Please enter code of desired item: ");
	
		Scanner newScanner3 = new Scanner(System.in);
		String userInput3 = newScanner3.nextLine();
		Map<String, Item> itemsWithLocations = new TreeMap<String,Item>();
		itemsWithLocations = ourVendingMachine.getItemsWithLocations();
		Set<String> theKeys = itemsWithLocations.keySet();

		
		// Check if item code is valid
		
		if (!theKeys.contains(userInput3)) {
			System.out.println("Invalid item code");
			purchaseItems();
		} 
		// Check if item is sold out
		if (itemsWithLocations.get(userInput3).getItemsLeft() == 0) {
			System.out.println("Item is sold out");
			purchaseItems();
		} 
		// If item is valid, dispense it
		else {
			Item selectedItem = itemsWithLocations.get(userInput3);
			// add all the slot locations to userSelections
		
			
			ourVendingMachine.getUserSelections().add(userInput3);
			ourVendingMachine.setMoneyOwed(ourVendingMachine.getMoneyOwed() + selectedItem.getPrice());
			ourVendingMachine.setBalanceLeft(ourVendingMachine.getBalanceLeft() - selectedItem.getPrice());
			ourVendingMachine.dispense(selectedItem);
			purchaseItems();
		}
		return;
	}
	
	public void purchaseItems() throws IOException {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
		
		// How do you return to this menu instead of the main menu after you make a choice
		
		System.out.println("Current Money Provided: " + ourVendingMachine.getMoneyIn() + "\n");
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		
		System.out.println("Please choose an option >>> ");
		Scanner newScanner = new Scanner(System.in);
		String userInput = newScanner.nextLine();
		
		
		
		// (1) Feed Money
		
		if (userInput.equals("1")) {
			feedMoney();
		}
		
		// (2) Select Product
		if (userInput.equals("2")) {
			selectProduct();
		}
		
		// (3) Finish Transaction
		
		if (userInput.equals("3")) {
			ourVendingMachine.giveChange();
			
			ourVendingMachine.audit(userInput, "");
			}
		
			
			
		
		
	}
		
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
